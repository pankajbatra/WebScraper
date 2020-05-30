package com.webscraper.util;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 1:14:30 PM
 */
public class StringUtils {
    public static int parseInt(String src, int defaultValue)
    {
        if(src==null)
            return defaultValue;
        else
        {
            try
            {
                return Integer.parseInt(src);
            }
            catch(NumberFormatException ne)
            {
                return defaultValue;
            }                                   
        }
    }
}
