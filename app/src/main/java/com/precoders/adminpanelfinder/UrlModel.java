package com.precoders.adminpanelfinder;

/**
 * Created by Farsheel on 6/8/17.
 */

public class UrlModel {

    public String url,status;

    public UrlModel(String urlText, String status) {
        this.url=urlText;
        this.status=status;
    }

    public String getUrl() {
        return url;
    }


    public String getStatus() {
        return status;
    }
}
