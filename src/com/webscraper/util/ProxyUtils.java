package com.webscraper.util;

import com.webscraper.config.ProxyServer;
import com.webscraper.exception.ScraperException;
import com.meterware.httpunit.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

import org.xml.sax.SAXException;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 27, 2007
 * Time: 1:26:23 AM
 */
public class ProxyUtils {
    public static ProxyServer getBestProxy(List<ProxyServer> proxies, boolean proxySpeedTestRequired, String proxySpeedTestUrl,
                                           boolean proxyAnonymityTestRequired, String proxyIpCheckUrl,
                                           String proxyTestPostURL, String proxyTestSuccessContent) throws ScraperException {

        List<ProxyServer> testsPassedServers = new ArrayList<ProxyServer>();

        WebConversation webConversation = new WebConversation();
        webConversation.getClientProperties().setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.4) Gecko/20070515 Firefox/2.0.0.4");
        webConversation.getClientProperties().setAutoRedirect(true);
        webConversation.getClientProperties().setIframeSupported(true);
        webConversation.getClientProperties().setAcceptGzip(false);

        for(ProxyServer server : proxies)
        {
            webConversation.clearProxyServer();
            webConversation.setProxyServer(server.getServerName(), server.getPortNumber());
            WebRequest request = new GetMethodWebRequest(proxyTestPostURL);
            try {
                String response = webConversation.getResponse(request).getText();
                if(response.equals(proxyTestSuccessContent))
                    testsPassedServers.add(server);
            } catch (Exception e) {
                //log it.
                // new ScraperException("Error in testing proxy", e);
            }
        }

        if(proxyAnonymityTestRequired)
        {
            webConversation.clearProxyServer();
            WebRequest request = new GetMethodWebRequest(proxyIpCheckUrl);

            String actualIP = null;
            try {
                actualIP = webConversation.getResponse(request).getText();
            } catch (Exception e) {
                throw new ScraperException("Unable to test proxies for Anonymity",e);
            }
            
            List<ProxyServer> anonymityPassedServers = new ArrayList<ProxyServer>();
            for(ProxyServer server : testsPassedServers)
            {
                webConversation.clearProxyServer();
                webConversation.setProxyServer(server.getServerName(), server.getPortNumber());
                request = new GetMethodWebRequest(proxyIpCheckUrl);
                try {
                    if(!webConversation.getResponse(request).getText().equals(actualIP))
                        anonymityPassedServers.add(server);
                } catch (Exception e) {
                    //log it.
                    // new ScraperException("Error in testing proxy anonymity", e);
                }
            }
            testsPassedServers = anonymityPassedServers;
        }

        if(proxySpeedTestRequired)
        {
            for(ProxyServer server : testsPassedServers)
            {
                webConversation.clearProxyServer();
                webConversation.setProxyServer(server.getServerName(), server.getPortNumber());
                WebRequest request = new GetMethodWebRequest(proxySpeedTestUrl);
                try {
                    long timeBefore = System.currentTimeMillis();
                    int contentLength = webConversation.getResponse(request).getContentLength();
                    long timeAfter = System.currentTimeMillis();
                    server.setSpeed(contentLength/(int)((timeAfter-timeBefore)/1000));
                } catch (Exception e) {
                    //log it
                    // new ScraperException("Error in testing proxy speed", e);
                }
            }
            Collections.sort(testsPassedServers);
        }
        if(ScraperUtils.containsElements(testsPassedServers))
            return testsPassedServers.get(0);
        else
            return null;
    }
}
