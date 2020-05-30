package com.webscraper;

import com.meterware.httpunit.*;
import com.webscraper.config.*;
import com.webscraper.exception.ScraperException;
import com.webscraper.util.HttpUtils;
import static com.webscraper.util.ProxyUtils.getBestProxy;
import com.webscraper.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.apache.xerces.dom.DOMImplementationImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 20, 2007
 * Time: 2:33:39 AM
 */
public class WebScraper {

    private ScraperConfig scraperConfig;

    private HttpParams searchPageParams;

    private HttpParams loginPageParams;

    private String configFileDir;
    private String loginParamsFileName;
    private String searchParamsFileName;
    private String proxyLisFileName;

    private ProxyList proxyList;

    private WebResponse response = null;
    private WebRequest request;
    private String logOutUrl = null;
    private WebConversation webConversation = null;

    public HttpParams getSearchPageParams() {
        return searchPageParams;
    }

    public void setSearchPageParams(HttpParams searchPageParams) {
        this.searchPageParams = searchPageParams;
    }

    public HttpParams getLoginPageParams() {
        return loginPageParams;
    }

    public void setLoginPageParams(HttpParams loginPageParams) {
        this.loginPageParams = loginPageParams;
    }

    public ScraperConfig getScraperConfig() {
        return scraperConfig;
    }

    public void setScraperConfig(ScraperConfig scraperConfig) {
        this.scraperConfig = scraperConfig;
    }


    public ProxyList getProxyList() {
        return proxyList;
    }

    public void setProxyList(ProxyList proxyList) {
        this.proxyList = proxyList;
    }

    public String getConfigFileDir() {
        return configFileDir;
    }

    public void setConfigFileDir(String configFileDir) {
        this.configFileDir = configFileDir;
    }

    public WebScraper() throws ScraperException {
        setScraperConfig(new ScraperConfig(ScraperConstants.SCRAPPER_CONFIG_FILE));
    }

    public WebScraper(String scraperConfigFileName) throws ScraperException {
        if (scraperConfigFileName != null)
            setScraperConfig(new ScraperConfig(scraperConfigFileName));
    }

    public WebScraper(String configFileDir, String scraperConfigFileName, String loginParamsFileName, String searchParamsFileName, String proxyLisFileName)
            throws ScraperException {
        this.configFileDir = configFileDir;
        if (scraperConfigFileName != null)
            setScraperConfig(new ScraperConfig(configFileDir + File.separator + scraperConfigFileName));
        this.loginParamsFileName = loginParamsFileName;
        this.searchParamsFileName = searchParamsFileName;
        this.proxyLisFileName = proxyLisFileName;
    }

    public void execute() throws ScraperException {

        Runtime.getRuntime().addShutdownHook(new Thread(new ShutDownThread(this)));

        //set HTTPUnit options
        HttpUnitOptions.setExceptionsThrownOnScriptError(false);
        HttpUnitOptions.setExceptionsThrownOnErrorStatus(false);

        //start a new web conversation
        webConversation = new WebConversation();

        webConversation.getClientProperties().setUserAgent(scraperConfig.getHttpUserAgent());
        webConversation.getClientProperties().setAutoRedirect(true);
        webConversation.getClientProperties().setIframeSupported(true);
        webConversation.getClientProperties().setAcceptGzip(false);

        //set proxy, if required
        if (scraperConfig.isProxyRequired())
        {           
            if (proxyLisFileName != null)
                setProxyList(new ProxyList(configFileDir + File.separator + proxyLisFileName));
            setProxy(webConversation);
        }


        if (scraperConfig.isLoginRequired()) {
            if (loginParamsFileName != null)
                setLoginPageParams(new HttpParams(configFileDir + File.separator + loginParamsFileName));
            try {
                boolean isLoginSuccess = false;
                int loginTryCount = 0;
                while (!isLoginSuccess) {
                    if (scraperConfig.getLoginPageMethod() == ScraperConstants.GET_REQUEST_METHOD_CONST) {
                        request = new GetMethodWebRequest(scraperConfig.getLoginPageUrl());
                    } else {
                        request = new PostMethodWebRequest(scraperConfig.getLoginPageUrl());
                    }
                    response = webConversation.getResponse(request);
                    WebForm loginForm = response.getForms()[scraperConfig.getLoginPageFormIndex()];
                    HttpUtils.putRequestParams(loginForm, loginPageParams.getParams());
                    response = loginForm.submit();
                    if (response.getText().indexOf(scraperConfig.getLoginPageSuccessContent()) == -1) {
                        if (loginTryCount >= scraperConfig.getLoginRetryCount()) {
                            throw new ScraperException("Unable to login.");
                        }
                        if (scraperConfig.isLoginResetRequired()) {
                            WebLink resetLink = response.getLinkWith(scraperConfig.getLoginResetLink());
                            if (resetLink != null) {
                                resetLink.click();
                                WebLink logOutLink = webConversation.getCurrentPage().getLinkWith(scraperConfig.getLogOutLink());
                                if (logOutLink != null)
                                    logOutLink.click();
                            }
                        }
                        isLoginSuccess = false;
                        loginTryCount++;
                        Thread.sleep(scraperConfig.getLoginRetryInterval() * 1000);
                    } else {
                        isLoginSuccess = true;
                        logOutUrl = response.getLinkWith(scraperConfig.getLogOutLink()).getURLString();
                    }
                }
            } catch (Exception e) {
                throw new ScraperException("Error in login.", e);
            }
        }

        List<ScrapContent> scrapContents = new ArrayList<ScrapContent>();
        for (String contentFile : scraperConfig.getContentFiles()) {
            ScrapContent contentMapping = new ScrapContent(configFileDir + File.separator + contentFile);
            contentMapping.setContentFileName(configFileDir + File.separator + contentFile + ".csv");
            scrapContents.add(contentMapping);
            
            StringBuffer fieldNamesRow = new StringBuffer();
            for (String fieldName : contentMapping.getColumnNames()) {
                fieldNamesRow.append("\"").append(fieldName).append("\",");
            }
            if(scraperConfig.isMultipleRequests())
            {
               fieldNamesRow.append("\"").append(scraperConfig.getMulipleRequestsUniqueKeyParamName()).append("\",");
            }
            fieldNamesRow.deleteCharAt(fieldNamesRow.length() - 1);
            try
            {
                contentMapping.getFileWriter().write(fieldNamesRow.toString()+ "\r\n");
                contentMapping.getFileWriter().flush();
            }catch (Exception e) {
                throw new ScraperException("Error in writing content file.", e);
            }
        }

        if(!scraperConfig.isMultipleRequests())
        {
            if (searchParamsFileName != null)
                setSearchPageParams(new HttpParams(configFileDir + File.separator + searchParamsFileName));

            getResponse(searchPageParams);
            writeContent(scrapContents, searchPageParams);
        }
        else if (searchParamsFileName != null)
        {
            MultipleHttpParams httpParams = new MultipleHttpParams(configFileDir + File.separator + searchParamsFileName);
            for(HttpParams httpParam : httpParams.getParams())
            {
                getResponse(httpParam);
                writeContent(scrapContents, httpParam);
            }
        }
        try
        {
            for (ScrapContent contentMapping : scrapContents) {
                contentMapping.getFileWriter().flush();
                contentMapping.getFileWriter().close();
            }
        }
        catch (Exception e) {
            throw new ScraperException("Error in closing content file.", e);
        }
        finishUp();
    }

    private void finishUp() throws ScraperException {
        logOut();
        closeWindows();
    }

    private void logOut() throws ScraperException {
        if (scraperConfig.isLoginRequired()) {
            //try to logout the user
            request = new GetMethodWebRequest(logOutUrl);
            try {
                webConversation.getResponse(request);
            } catch (Exception e) {
                throw new ScraperException("Unable to log out", e);
            }
        }
    }

    private void closeWindows() {
        WebWindow[] windows = webConversation.getOpenWindows();
        for (int i = 0; i < windows.length; i++) {
            if (!windows[i].equals(webConversation.getMainWindow())) {
                windows[i].close();
                windows[i] = null;
            }
        }
    }

    private int getMaximumValues(List<List<String>> contentList) {
        int size = 0;
        for (List<String> contents : contentList) {
            if (size < contents.size())
                size = contents.size();
        }
        return size;
    }

    private void setProxy(WebConversation webConversation) throws ScraperException {
        if (webConversation != null) {
            ProxyServer proxy = getBestProxy(proxyList.getProxies(), scraperConfig.isProxySpeedTestRequired(), scraperConfig.getProxySpeedTestUrl(), scraperConfig.isProxyAnonymityTestRequired(),
                    scraperConfig.getProxyIpCheckUrl(), scraperConfig.getProxyTestPostURL(), scraperConfig.getProxyTestSuccessContent());
            if(proxy!=null)
            {
                System.out.println("Using proxy: "+ proxy.getServerName()+":"+proxy.getPortNumber());
                webConversation.setProxyServer(proxy.getServerName(), proxy.getPortNumber());
            }
        }
    }

    private void getResponse(HttpParams requestParams) throws ScraperException {

        if (scraperConfig.isDirectUrlRequest()) {
            if (scraperConfig.getDirectUrlMethod() == ScraperConstants.GET_REQUEST_METHOD_CONST) {
                request = new GetMethodWebRequest(scraperConfig.getDirectPageUrl());
            } else {
                request = new PostMethodWebRequest(scraperConfig.getDirectPageUrl());
            }
            // put params
            HttpUtils.putRequestParams(request, requestParams.getParams());

            try {
                response = webConversation.getResponse(request);
            }
            catch (Exception e) {
                throw new ScraperException("Error in getting response.", e);
            }
        } else if (scraperConfig.isFormSubmissionRequired()) {
            if (scraperConfig.getSearchFormMethod() == ScraperConstants.GET_REQUEST_METHOD_CONST) {
                request = new GetMethodWebRequest(scraperConfig.getSearchFormUrl());
            } else {
                request = new PostMethodWebRequest(scraperConfig.getSearchFormUrl());
            }
            try {
                response = webConversation.getResponse(request);
                WebForm searchForm = response.getForms()[scraperConfig.getSearchFormIndex()];
                HttpUtils.putRequestParams(searchForm, requestParams.getParams());
                response = searchForm.submit();
            } catch (Exception e) {
                throw new ScraperException("Error in getting search page.", e);
            }
        }
    }

    private void writeContent(List<ScrapContent> scrapContents, HttpParams httpParam) throws ScraperException {
        try {
                System.out.println(DOMImplementationImpl.getDOMImplementation().hasFeature("XML", "2.0"));
                
                Document document = response.getDOM();
                String xPath;
                for (ScrapContent contentMapping : scrapContents) {
                    List<List<String>> contentList = new ArrayList<List<String>>();
                    for (String columnName : contentMapping.getColumnNames()) {
                        xPath = scraperConfig.isXpathUpperCaseRequired()
                                ?contentMapping.getPatternForColumn(columnName).replaceAll("/tbody", "").toUpperCase()
                                :contentMapping.getPatternForColumn(columnName).replaceAll("/tbody", ""); 
                        NodeList nodeList = org.apache.xpath.XPathAPI.selectNodeList(document, xPath);
                        List<String> parsedColumnValues = new ArrayList<String>();
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            parsedColumnValues.add(XmlUtils.getTextValueForHtmlNode(nodeList.item(i)));
                        }
                        contentList.add(parsedColumnValues);
                    }
                    int maxValues = getMaximumValues(contentList);

                    String[] csvValues = new String[maxValues];

                    for (int i = 0; i < csvValues.length; i++) {
                        StringBuffer rowValue = new StringBuffer();
                        for (List<String> content : contentList) {
                            if (content.size() <= i)
                                rowValue.append("\"\",");
                            else
                                rowValue.append("\"").append(content.get(i)).append("\",");
                        }
                        if(scraperConfig.isMultipleRequests())
                        {
                           rowValue.append("\"").append(httpParam.getParams().getProperty(scraperConfig.getMulipleRequestsUniqueKeyParamName())).append("\",");
                        }
                        rowValue.deleteCharAt(rowValue.length() - 1);
                        csvValues[i] = rowValue.toString();
                    }

                    for (String csvRow : csvValues) {
                        contentMapping.getFileWriter().write(csvRow + "\r\n");
                    }
                    contentMapping.getFileWriter().flush();
                }
            } catch (Exception e) {
                throw new ScraperException("Error in parsing result page.", e);
            }
    }

    class ShutDownThread implements Runnable {
        WebScraper webScraper;

        ShutDownThread(WebScraper webScraper) {
            this.webScraper = webScraper;
        }

        public void run() {
            try {
                webScraper.finishUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
