package com.synelience.weatherapi.util;

public class URLBuilder {

    public static String getAddress(String resource) {
        return String.join("", ApiConstants.ADDRESS, ":", String.valueOf(port), "/", ApiConstants.API_NAME, resource);
    }

}
