package com.webscraper.util;

import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebForm;

import java.util.Properties;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 2:25:48 PM
 */
public class HttpUtils {

    public static void putRequestParams(WebRequest request, Properties params)
    {
        if(request!=null && params!=null)
        {
            Enumeration propertyNames = params.propertyNames();
            while(propertyNames.hasMoreElements())
            {
                String key = (String) propertyNames.nextElement();
                String valueForKey =  params.getProperty(key);

                String values[];
                if(valueForKey.indexOf(",")==-1)
                    values = new String[]{valueForKey};
                else
                    values = valueForKey.split(",");

                if(values!=null)
                    request.setParameter(key, values);
            }
        }
    }

    public static void putRequestParams(WebForm request, Properties params)
    {
        if(request!=null && params!=null)
        {
            Enumeration propertyNames = params.propertyNames();
            while(propertyNames.hasMoreElements())
            {
                String key = (String) propertyNames.nextElement();
                String valueForKey =  params.getProperty(key);

                String values[];
                if(valueForKey.indexOf(",")==-1)
                    values = new String[]{valueForKey};
                else
                    values = valueForKey.split(",");

                if(values!=null)
                    request.setParameter(key, values);
            }
        }
    }
}
