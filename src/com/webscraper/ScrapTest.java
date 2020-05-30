package com.webscraper;

import com.webscraper.exception.ScraperException;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 1:43:58 PM
 */
public class ScrapTest {
    public static void main(String args[]) throws ScraperException {
        String configDir = "F:\\My documents-2\\Projects handled\\SirfTaaza\\WebScraper\\config\\googleAdRepeated";
        if(args.length>0)
            configDir =  args[0];
        WebScraper scraper = new WebScraper(configDir, ScraperConstants.SCRAPPER_CONFIG_FILE,
                ScraperConstants.LOGIN_PARAMS_FILE,
                ScraperConstants.REQUEST_PARAMS_FILE, ScraperConstants.PROXY_LIST_FILE);
        scraper.execute();
    }
}
