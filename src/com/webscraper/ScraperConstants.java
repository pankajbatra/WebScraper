package com.webscraper;
/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 23, 2007
 * Time: 12:16:05 AM
 */


public interface ScraperConstants {

    public static final int GET_REQUEST_METHOD_CONST = 0;
    public static final String GET_REQUEST_METHOD_STRING_CONST = "GET";

    public static final int POST_REQUEST_METHOD_CONST = 1;
    public static final String POST_REQUEST_METHOD_STRING_CONST = "POST";

    public static final String SCRAPPER_CONFIG_FILE= "scraper.config";

    public static final String REQUEST_PARAMS_FILE= "searchForm.params";

    public static final String LOGIN_PARAMS_FILE= "loginForm.params";

    public static final String PROXY_LIST_FILE= "proxy.lst";

    public static final String IS_PROXY_REQUIRED= "IS_PROXY_REQUIRED";
    public static final boolean IS_PROXY_REQUIRED_DEF_VALUE = false;

    public static final String PROXY_TEST_POST_URL ="PROXY_TEST_POST_URL";

    public static final String PROXY_SPEED_TEST_URL ="PROXY_SPEED_TEST_URL";

    public static final String PROXY_TEST_SUCCESS_CONTENT ="PROXY_TEST_SUCCESS_CONTENT";
    public static final String PROXY_TEST_SUCCESS_CONTENT_DEF_VALUE ="Proxy_Working_fine";

    public static final String IS_PROXY_SPEED_TEST_REQUIRED ="IS_PROXY_SPEED_TEST_REQUIRED";
    public static final boolean IS_PROXY_SPEED_TEST_REQUIRED_DEF_VALUE = false;

    public static final String IS_PROXY_ANONYMITY_TEST_REQUIRED ="IS_PROXY_ANONYMITY_TEST_REQUIRED";
    public static final boolean IS_PROXY_ANONYMITY_TEST_REQUIRED_DEF_VALUE = false;
    
    public static final String PROXY_IP_CHECK_URL ="PROXY_IP_CHECK_URL";

    public static final String SLEEP_INTERVAL_AFTER_EACH_REQUEST ="SLEEP_INTERVAL_AFTER_EACH_REQUEST";
    public static final int SLEEP_INTERVAL_AFTER_EACH_REQUEST_DEF_VALUE = 10;

    public static final String IS_USING_BASIC_HTTP_AUTH ="IS_USING_BASIC_HTTP_AUTH";
    public static final boolean IS_USING_BASIC_HTTP_AUTH_DEF_VLAUE = false;

    public static final String IS_DIRECT_URL_REQUEST = "IS_DIRECT_URL_REQUEST";
    public static final boolean IS_DIRECT_URL_REQUEST_DEF_VALUE = true;

    public static final String DIRECT_PAGE_URL= "DIRECT_PAGE_URL";

    public static final String DIRECT_URL_METHOD= "DIRECT_URL_METHOD";
    public static final int DIRECT_URL_METHOD_DEF_VALUE= GET_REQUEST_METHOD_CONST;

    public static final String IS_FORM_SUBMISSION_REQUIRED= "IS_FORM_SUBMISSION_REQUIRED";
    public static final boolean IS_FORM_SUBMISSION_REQUIRED_DEF_VALUE= false;

    public static final String SEARCH_FORM_URL= "SEARCH_FORM_URL";

    public static final String SEARCH_FORM_METHOD= "SEARCH_FORM_METHOD";
    public static final int SEARCH_FORM_METHOD_DEF_VALUE= POST_REQUEST_METHOD_CONST;

    public static final String SEARCH_FORM_INDEX= "SEARCH_FORM_INDEX";
    public static final int SEARCH_FORM_INDEX_DEF_VALUE= 0;

    public static final String IS_LOGIN_REQUIRED= "IS_LOGIN_REQUIRED";
    public static final boolean IS_LOGIN_REQUIRED_DEF_VALUE = false;

    public static final String LOGIN_PAGE_URL= "LOGIN_PAGE_URL";

    public static final String LOGIN_PAGE_METHOD= "LOGIN_PAGE_METHOD";
    public static final int LOGIN_PAGE_METHOD_DEF_VALUE= POST_REQUEST_METHOD_CONST;

    public static final String LOGIN_PAGE_FORM_INDEX= "LOGIN_PAGE_FORM_INDEX";
    public static final int LOGIN_PAGE_FORM_INDEX_DEF_VALUE= 0;

    public static final String LOGIN_PAGE_SUCCESS_CONTENT= "LOGIN_PAGE_SUCCESS_CONTENT";
    public static final String LOGIN_PAGE_SUCCESS_CONTENT_DEF_VALUE= "Login Successful";

    public static final String LOGIN_RETRY_COUNT = "LOGIN_RETRY_COUNT";
    public static final int LOGIN_RETRY_COUNT_DEF_VALUE = 0;

    public static final String LOGIN_RETRY_INTERVAL = "LOGIN_RETRY_INTERVAL";
    public static final int LOGIN_RETRY_INTERVAL_DEF_VALUE = 10; //in seconds

    public static final String LOGIN_RESET_LINK = "LOGIN_RESET_LINK";
    public static final String LOGIN_RESET_LINK_DEF_VALUE = "Reset";

    public static final String IS_LOGIN_RESET_REQUIRED = "IS_LOGIN_RESET_REQUIRED";
    public static final boolean IS_LOGIN_RESET_REQUIRED_DEF_VALUE = false;

    public static final String IS_MULTIPLE_REQUESTS = "IS_MULTIPLE_REQUESTS";
    public static final boolean IS_MULTIPLE_REQUESTS_DEF_VALUE = false;
    public static final String MULIPLE_REQUESTS_UNIQUE_KEY_PARAM_NAME = "MULIPLE_REQUESTS_UNIQUE_KEY_PARAM_NAME";

    public static final String HTTP_PARAMS_DELIMITER = "##";
    public static final String HTTP_PARAM_NAME_VALUE_DELIMITER = "=";

    public static final String LOG_OUT_LINK = "LOG_OUT_LINK";
    public static final String LOG_OUT_LINK_DEF_VALUE = "Log Out";   

    public static final String CONTENT_FILES= "CONTENT_FILES";

    public static final String HTTP_USER_AGENT= "HTTP_USER_AGENT";  
    public static final String HTTP_USER_AGENT_DEF_VALUE = "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)";

    public static final String IS_XPATH_UPPER_CASE_REQUIRED = "IS_XPATH_UPPER_CASE_REQUIRED";
    public static final boolean IS_XPATH_UPPER_CASE_REQUIRED_DEF_VALUE = true;    

}
