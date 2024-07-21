package com.NamanJain.TubeFetch;

import java.net.MalformedURLException;
import java.net.URL;

public class DownloadObject {

    public String url;

    public boolean isValidUrl() {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
