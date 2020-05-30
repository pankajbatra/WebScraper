package com.webscraper.config;

import com.webscraper.exception.ScraperException;
import com.webscraper.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 27, 2007
 * Time: 12:59:20 AM
 */
public class ProxyList {
    private final List<ProxyServer> proxies;

    public ProxyList(String paramsFileName) throws ScraperException {
        proxies = new ArrayList<ProxyServer>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paramsFileName));
            String proxy;
            int port;
            while ((proxy = reader.readLine()) != null) {
                String[] proxyTemp = proxy.split(":");
                if (proxyTemp.length < 1) {
                    port = 80;
                } else {
                    port = StringUtils.parseInt(proxyTemp[1], 80);
                }
                proxies.add(new ProxyServer(proxyTemp[0], port));
            }
        }
        catch (IOException e) {
            throw new ScraperException("Error reading proxy list.", e);
        }
    }

    public List<ProxyServer> getProxies() {
        return proxies;
    }
}
