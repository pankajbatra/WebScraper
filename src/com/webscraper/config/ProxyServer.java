package com.webscraper.config;

/**
 * Created by IntelliJ IDEA.
 * User: Pankaj Batra
 * Date: Jan 27, 2007
 * Time: 1:00:32 AM
 */
public class ProxyServer implements Comparable<ProxyServer>{
    private final String serverName;
    private final int portNumber;
    private int speed;

    public ProxyServer(String serverName, int portNumber) {
        this.portNumber = portNumber;
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int compareTo(ProxyServer that) {
        int thisVal = this.speed;
	    int thatVal = that.speed;
	    return (thisVal>thatVal ? -1 : (thisVal==thatVal ? 0 : 1));
    }
}
