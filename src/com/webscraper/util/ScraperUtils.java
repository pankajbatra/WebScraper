package com.webscraper.util;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 2:27:43 PM
 */
public class ScraperUtils {

    public static boolean containsElements(Collection collection)
    {
        return collection != null && collection.size() > 0;
    }
}
