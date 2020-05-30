package com.webscraper.config;

import com.webscraper.exception.ScraperException;
import com.webscraper.ScraperConstants;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Feb 13, 2007
 * Time: 2:37:45 AM
 */
public class MultipleHttpParams {
    private final List<HttpParams> httpParams;

    public MultipleHttpParams(String paramsFileName) throws ScraperException {
        httpParams = new ArrayList<HttpParams>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paramsFileName));
            String paramsString;
            while((paramsString=reader.readLine())!=null)
            {
                Properties param= new Properties();
                String[] params = paramsString.split(ScraperConstants.HTTP_PARAMS_DELIMITER);
                for (String paramString : params)
                {
                    String[] paramNameValue=paramString.split(ScraperConstants.HTTP_PARAM_NAME_VALUE_DELIMITER);
                    param.setProperty(paramNameValue[0], paramNameValue[1]);
                }
                HttpParams httpParam = new HttpParams(param);
                httpParams.add(httpParam);
            }
        }
        catch (IOException e) {
            throw new ScraperException("Error reading request parameters.", e);
        }
    }

    public List<HttpParams> getParams() {
        return httpParams;
    }
}
