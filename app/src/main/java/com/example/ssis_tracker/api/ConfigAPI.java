package com.example.ssis_tracker.api;

public class ConfigAPI
{
    private String Port;
    private String IPExterna;
    private String IPinterna;
    private String URL;

    public ConfigAPI() {
        Port = "3007";
       // Port = "3005";
        IPExterna = "143.208.18.66";
        IPinterna = "192.168.41.9";
       // IPinterna = "172.16.221.60";
    }

    public String getPort() {
        return Port;
    }

    public String getIPExterna() {
        return IPExterna;
    }

    public String getURL() {
        String Protocolo = "http://";
        String Home = "/index.php/";
        String doublePoint = ":";

        URL = Protocolo + IPExterna + doublePoint + Port + Home;
        return URL;
    }
}
