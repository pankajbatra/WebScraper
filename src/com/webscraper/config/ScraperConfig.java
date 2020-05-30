package com.webscraper.config;

import com.webscraper.ScraperConstants;
import com.webscraper.exception.ScraperException;
import com.webscraper.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 12:29:10 AM
 */
public class ScraperConfig {

    private boolean isProxyRequired = ScraperConstants.IS_PROXY_REQUIRED_DEF_VALUE;

    private boolean isDirectUrlRequest = ScraperConstants.IS_DIRECT_URL_REQUEST_DEF_VALUE;

    private String proxyTestPostURL;

    private String proxyTestSuccessContent = ScraperConstants.PROXY_TEST_SUCCESS_CONTENT_DEF_VALUE;

    private boolean isProxySpeedTestRequired = ScraperConstants.IS_PROXY_SPEED_TEST_REQUIRED_DEF_VALUE;

    private boolean isProxyAnonymityTestRequired = ScraperConstants.IS_PROXY_ANONYMITY_TEST_REQUIRED_DEF_VALUE;

    private String proxyIpCheckUrl;

    private int sleepIntervalAfterEachRequest = ScraperConstants.SLEEP_INTERVAL_AFTER_EACH_REQUEST_DEF_VALUE;

    private boolean isUsingBasicHttpAuth = ScraperConstants.IS_USING_BASIC_HTTP_AUTH_DEF_VLAUE;

    private String directPageUrl;

    private int directUrlMethod = ScraperConstants.DIRECT_URL_METHOD_DEF_VALUE;

    private boolean isFormSubmissionRequired = ScraperConstants.IS_FORM_SUBMISSION_REQUIRED_DEF_VALUE;

    private String searchFormUrl;

    private int searchFormMethod = ScraperConstants.SEARCH_FORM_METHOD_DEF_VALUE;

    private int searchFormIndex = ScraperConstants.SEARCH_FORM_INDEX_DEF_VALUE;

    private boolean isLoginRequired = ScraperConstants.IS_LOGIN_REQUIRED_DEF_VALUE;

    private String loginPageUrl;

    private int loginPageMethod = ScraperConstants.LOGIN_PAGE_METHOD_DEF_VALUE;

    private int loginPageFormIndex = ScraperConstants.LOGIN_PAGE_FORM_INDEX_DEF_VALUE;

    private String loginPageSuccessContent = ScraperConstants.LOGIN_PAGE_SUCCESS_CONTENT_DEF_VALUE;

    private String[] contentFiles;

    private String httpUserAgent = ScraperConstants.HTTP_USER_AGENT_DEF_VALUE;

    private int loginRetryCount = ScraperConstants.LOGIN_RETRY_COUNT_DEF_VALUE;

    private int loginRetryInterval = ScraperConstants.LOGIN_RETRY_INTERVAL_DEF_VALUE;

    private String loginResetLink = ScraperConstants.LOGIN_RESET_LINK_DEF_VALUE;

    private boolean isLoginResetRequired = ScraperConstants.IS_LOGIN_RESET_REQUIRED_DEF_VALUE;

    private String logOutLink = ScraperConstants.LOG_OUT_LINK_DEF_VALUE;

    private String proxySpeedTestUrl;

    private boolean isMultipleRequests = ScraperConstants.IS_MULTIPLE_REQUESTS_DEF_VALUE;

    private String mulipleRequestsUniqueKeyParamName;

    private boolean isXpathUpperCaseRequired = ScraperConstants.IS_XPATH_UPPER_CASE_REQUIRED_DEF_VALUE;

    public ScraperConfig(String scraperConfigFileName) throws ScraperException {
        Properties scraperProps = new Properties();
        try {
            scraperProps.load(new FileInputStream(scraperConfigFileName));
        }
        catch (IOException e) {
            throw new ScraperException("Error reading scraper config.",e);
        }

        setProxyRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_PROXY_REQUIRED, String.valueOf(ScraperConstants.IS_PROXY_REQUIRED_DEF_VALUE))));
        setProxyTestPostURL(scraperProps.getProperty(ScraperConstants.PROXY_TEST_POST_URL));
        setProxyTestSuccessContent(scraperProps.getProperty(ScraperConstants.PROXY_TEST_SUCCESS_CONTENT, ScraperConstants.PROXY_TEST_SUCCESS_CONTENT_DEF_VALUE));
        setProxySpeedTestRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_PROXY_SPEED_TEST_REQUIRED, String.valueOf(ScraperConstants.IS_PROXY_SPEED_TEST_REQUIRED_DEF_VALUE))));
        setProxyAnonymityTestRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_PROXY_ANONYMITY_TEST_REQUIRED, String.valueOf(ScraperConstants.IS_PROXY_ANONYMITY_TEST_REQUIRED_DEF_VALUE))));
        setProxyIpCheckUrl(scraperProps.getProperty(ScraperConstants.PROXY_IP_CHECK_URL));
        setProxySpeedTestUrl(scraperProps.getProperty(ScraperConstants.PROXY_SPEED_TEST_URL));

        setSleepIntervalAfterEachRequest(StringUtils.parseInt(
                scraperProps.getProperty(ScraperConstants.SLEEP_INTERVAL_AFTER_EACH_REQUEST), ScraperConstants.SLEEP_INTERVAL_AFTER_EACH_REQUEST_DEF_VALUE));
        setUsingBasicHttpAuth(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_USING_BASIC_HTTP_AUTH, String.valueOf(ScraperConstants.IS_USING_BASIC_HTTP_AUTH_DEF_VLAUE))));

        setDirectUrlRequest(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_DIRECT_URL_REQUEST, String.valueOf(ScraperConstants.IS_DIRECT_URL_REQUEST_DEF_VALUE))));
        setDirectPageUrl(scraperProps.getProperty(ScraperConstants.DIRECT_PAGE_URL));
        setDirectUrlMethod(scraperProps.getProperty(ScraperConstants.DIRECT_URL_METHOD));

        setFormSubmissionRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_FORM_SUBMISSION_REQUIRED, String.valueOf(ScraperConstants.IS_FORM_SUBMISSION_REQUIRED_DEF_VALUE))));
        setSearchFormUrl(scraperProps.getProperty(ScraperConstants.SEARCH_FORM_URL));
        setSearchFormMethod(scraperProps.getProperty(ScraperConstants.SEARCH_FORM_METHOD));
        setSearchFormIndex(StringUtils.parseInt(
                scraperProps.getProperty(ScraperConstants.SEARCH_FORM_INDEX), ScraperConstants.SEARCH_FORM_INDEX_DEF_VALUE));

        setLoginRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_LOGIN_REQUIRED, String.valueOf(ScraperConstants.IS_LOGIN_REQUIRED_DEF_VALUE))));
        setLoginPageUrl(scraperProps.getProperty(ScraperConstants.LOGIN_PAGE_URL));
        setLoginPageMethod(scraperProps.getProperty(ScraperConstants.LOGIN_PAGE_METHOD));
        setLoginPageFormIndex(StringUtils.parseInt(
                scraperProps.getProperty(ScraperConstants.LOGIN_PAGE_FORM_INDEX), ScraperConstants.LOGIN_PAGE_FORM_INDEX_DEF_VALUE));
        setLoginPageSuccessContent(scraperProps.getProperty(ScraperConstants.LOGIN_PAGE_SUCCESS_CONTENT, ScraperConstants.LOGIN_PAGE_SUCCESS_CONTENT_DEF_VALUE));

        setContentFiles(scraperProps.getProperty(ScraperConstants.CONTENT_FILES));

        setMultipleRequests(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_MULTIPLE_REQUESTS, String.valueOf(ScraperConstants.IS_MULTIPLE_REQUESTS_DEF_VALUE))));

        setMulipleRequestsUniqueKeyParamName(scraperProps.getProperty(ScraperConstants.MULIPLE_REQUESTS_UNIQUE_KEY_PARAM_NAME));

        setHttpUserAgent(scraperProps.getProperty(ScraperConstants.HTTP_USER_AGENT, ScraperConstants.HTTP_USER_AGENT_DEF_VALUE));

        setLoginRetryCount(StringUtils.parseInt(
                scraperProps.getProperty(ScraperConstants.LOGIN_RETRY_COUNT), ScraperConstants.LOGIN_RETRY_COUNT_DEF_VALUE));
        setLoginRetryInterval(StringUtils.parseInt(
                scraperProps.getProperty(ScraperConstants.LOGIN_RETRY_INTERVAL), ScraperConstants.LOGIN_RETRY_INTERVAL_DEF_VALUE));
        setLoginResetLink(scraperProps.getProperty(ScraperConstants.LOGIN_RESET_LINK, ScraperConstants.LOGIN_RESET_LINK_DEF_VALUE));
        setLoginResetRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_LOGIN_RESET_REQUIRED, String.valueOf(ScraperConstants.IS_LOGIN_RESET_REQUIRED_DEF_VALUE))));

        setLogOutLink(scraperProps.getProperty(ScraperConstants.LOG_OUT_LINK, ScraperConstants.LOG_OUT_LINK_DEF_VALUE));

        setXpathUpperCaseRequired(Boolean.parseBoolean(
                scraperProps.getProperty(ScraperConstants.IS_XPATH_UPPER_CASE_REQUIRED, String.valueOf(ScraperConstants.IS_XPATH_UPPER_CASE_REQUIRED_DEF_VALUE))));

    }

    public boolean isProxyRequired() {
        return isProxyRequired;
    }

    public void setProxyRequired(boolean proxyRequired) {
        isProxyRequired = proxyRequired;
    }


    public boolean isXpathUpperCaseRequired() {
        return isXpathUpperCaseRequired;
    }

    public void setXpathUpperCaseRequired(boolean xpathUpperCaseRequired) {
        isXpathUpperCaseRequired = xpathUpperCaseRequired;
    }

    public boolean isDirectUrlRequest() {
        return isDirectUrlRequest;
    }

    public void setDirectUrlRequest(boolean directUrl) {
        isDirectUrlRequest = directUrl;
    }


    public String getProxyTestPostURL() {
        return proxyTestPostURL;
    }

    public void setProxyTestPostURL(String proxyTestPostURL) {
        this.proxyTestPostURL = proxyTestPostURL;
    }


    public String getProxyTestSuccessContent() {
        return proxyTestSuccessContent;
    }

    public void setProxyTestSuccessContent(String proxyTestSuccessContent) {
        this.proxyTestSuccessContent = proxyTestSuccessContent;
    }

    public boolean isProxySpeedTestRequired() {
        return isProxySpeedTestRequired;
    }

    public void setProxySpeedTestRequired(boolean proxySpeedTestRequired) {
        isProxySpeedTestRequired = proxySpeedTestRequired;
    }


    public boolean isProxyAnonymityTestRequired() {
        return isProxyAnonymityTestRequired;
    }

    public void setProxyAnonymityTestRequired(boolean proxyAnonymityTestRequired) {
        isProxyAnonymityTestRequired = proxyAnonymityTestRequired;
    }

    public String getProxyIpCheckUrl() {
        return proxyIpCheckUrl;
    }

    public void setProxyIpCheckUrl(String proxyIpCheckUrl) {
        this.proxyIpCheckUrl = proxyIpCheckUrl;
    }


    public int getSleepIntervalAfterEachRequest() {
        return sleepIntervalAfterEachRequest;
    }

    public void setSleepIntervalAfterEachRequest(int sleepIntervalAfterEachRequest) {
        this.sleepIntervalAfterEachRequest = sleepIntervalAfterEachRequest;
    }


    public boolean isUsingBasicHttpAuth() {
        return isUsingBasicHttpAuth;
    }

    public void setUsingBasicHttpAuth(boolean usingBasicHttpAuth) {
        isUsingBasicHttpAuth = usingBasicHttpAuth;
    }

    public String getDirectPageUrl() {
        return directPageUrl;
    }

    public void setDirectPageUrl(String directPageUrl) {
        this.directPageUrl = directPageUrl;
    }

    public int getDirectUrlMethod() {
        return directUrlMethod;
    }

    public void setDirectUrlMethod(int directUrlMethod) {
        this.directUrlMethod = directUrlMethod;
    }

    public void setDirectUrlMethod(String directUrlMethod) {
        if (directUrlMethod != null && directUrlMethod.equalsIgnoreCase(ScraperConstants.POST_REQUEST_METHOD_STRING_CONST))
            this.directUrlMethod = ScraperConstants.POST_REQUEST_METHOD_CONST;
        else
            this.directUrlMethod = ScraperConstants.GET_REQUEST_METHOD_CONST;
    }

    public boolean isFormSubmissionRequired() {
        return isFormSubmissionRequired;
    }

    public void setFormSubmissionRequired(boolean formSubmissionRequired) {
        isFormSubmissionRequired = formSubmissionRequired;
    }

    public String getSearchFormUrl() {
        return searchFormUrl;
    }

    public void setSearchFormUrl(String searchFormUrl) {
        this.searchFormUrl = searchFormUrl;
    }

    public int getSearchFormMethod() {
        return searchFormMethod;
    }

    public void setSearchFormMethod(int searchFormMethod) {
        this.searchFormMethod = searchFormMethod;
    }

    public void setSearchFormMethod(String searchFormMethod) {
        if (searchFormMethod != null && searchFormMethod.equalsIgnoreCase(ScraperConstants.POST_REQUEST_METHOD_STRING_CONST))
            this.searchFormMethod = ScraperConstants.POST_REQUEST_METHOD_CONST;
        else
            this.searchFormMethod = ScraperConstants.GET_REQUEST_METHOD_CONST;
    }

    public int getSearchFormIndex() {
        return searchFormIndex;
    }

    public void setSearchFormIndex(int searchFormIndex) {
        this.searchFormIndex = searchFormIndex;
    }

    public boolean isLoginRequired() {
        return isLoginRequired;
    }

    public void setLoginRequired(boolean loginRequired) {
        isLoginRequired = loginRequired;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
    }

    public int getLoginPageMethod() {
        return loginPageMethod;
    }

    public void setLoginPageMethod(int loginPageMethod) {
        this.loginPageMethod = loginPageMethod;
    }

    public void setLoginPageMethod(String loginPageMethod) {
        if (loginPageMethod != null && loginPageMethod.equalsIgnoreCase(ScraperConstants.POST_REQUEST_METHOD_STRING_CONST))
            this.loginPageMethod = ScraperConstants.POST_REQUEST_METHOD_CONST;
        else
            this.loginPageMethod = ScraperConstants.GET_REQUEST_METHOD_CONST;
    }

    public int getLoginPageFormIndex() {
        return loginPageFormIndex;
    }

    public void setLoginPageFormIndex(int loginPageFormIndex) {
        this.loginPageFormIndex = loginPageFormIndex;
    }

    public String getLoginPageSuccessContent() {
        return loginPageSuccessContent;
    }

    public void setLoginPageSuccessContent(String loginPageSuccessContent) {
        this.loginPageSuccessContent = loginPageSuccessContent;
    }

    public String[] getContentFiles() {
        return contentFiles;
    }

    public void setContentFiles(String[] contentFiles) {
        this.contentFiles = contentFiles;
    }

    public void setContentFiles(String contentFiles) {
        if (contentFiles != null) {
            if (contentFiles.indexOf(",") == -1)
                this.contentFiles = new String[]{contentFiles};
            else
                this.contentFiles = contentFiles.split(",");
        }
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    public int getLoginRetryCount() {
        return loginRetryCount;
    }

    public void setLoginRetryCount(int loginRetryCount) {
        this.loginRetryCount = loginRetryCount;
    }

    public int getLoginRetryInterval() {
        return loginRetryInterval;
    }

    public void setLoginRetryInterval(int loginRetryInterval) {
        this.loginRetryInterval = loginRetryInterval;
    }

    public String getLoginResetLink() {
        return loginResetLink;
    }

    public void setLoginResetLink(String loginResetLink) {
        this.loginResetLink = loginResetLink;
    }

    public boolean isLoginResetRequired() {
        return isLoginResetRequired;
    }

    public void setLoginResetRequired(boolean loginResetRequired) {
        isLoginResetRequired = loginResetRequired;
    }

    public String getLogOutLink() {
        return logOutLink;
    }

    public void setLogOutLink(String logOutLink) {
        this.logOutLink = logOutLink;
    }


    public String getProxySpeedTestUrl() {
        return proxySpeedTestUrl;
    }

    public void setProxySpeedTestUrl(String proxySpeedTestUrl) {
        this.proxySpeedTestUrl = proxySpeedTestUrl;
    }

    public boolean isMultipleRequests() {
        return isMultipleRequests;
    }

    public void setMultipleRequests(boolean multipleRequests) {
        isMultipleRequests = multipleRequests;
    }


    public String getMulipleRequestsUniqueKeyParamName() {
        return mulipleRequestsUniqueKeyParamName;
    }

    public void setMulipleRequestsUniqueKeyParamName(String mulipleRequestsUniqueKeyParamName) {
        this.mulipleRequestsUniqueKeyParamName = mulipleRequestsUniqueKeyParamName;
    }
}
