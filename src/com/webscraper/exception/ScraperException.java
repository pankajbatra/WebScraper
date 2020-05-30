package com.webscraper.exception;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 2:13:28 PM
 */
public class ScraperException extends Exception{


    public ScraperException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ScraperException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ScraperException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ScraperException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
