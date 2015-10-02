package com.udacity.gradle.builditbigger.service;

/**
 * Convenient Class to provide settings.
 * Created by elnoxvie on 15/9/14.
 */
public class Settings {

    public enum Environment {
        DEBUG,
        PRODUCTION
    }

    /**
     * Debug or Production
     * Debug: link to local database
     * Production : link to the server
     */
    public static final Environment environment = Environment.DEBUG;

    /***
     * Define the local server IP
     */
    public static final String ENDPOINT_IP = "192.168.1.152";
    public static final String ENDPOINT_ROOT_URL = "http://" + ENDPOINT_IP + ":8080/_ah/api";

    public static final String PRODUCTION_URL = "mod-ads-dot-house-ads-server.appspot.com";
    public static final String ENDPOINT_PRODUCTION_ROOT_URL = "https://" + PRODUCTION_URL + "/_ah/api";

    public static boolean isProduction() {
        return (environment == Environment.PRODUCTION);
    }
}
