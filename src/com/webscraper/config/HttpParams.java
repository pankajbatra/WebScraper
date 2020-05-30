package com.webscraper.config;

import com.webscraper.exception.ScraperException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 2:19:57 PM
 */
public class HttpParams {
    private final Properties params;

    public HttpParams(String paramsFileName) throws ScraperException {
        params = new Properties();
        try {
            params.load(new FileInputStream(paramsFileName));
        }
        catch (IOException e) {
            throw new ScraperException("Error reading request parameters.", e);
        }
    }

    public HttpParams(Properties params){
        this.params = params;
    }

    public Properties getParams() {
        return params;
    }
}
