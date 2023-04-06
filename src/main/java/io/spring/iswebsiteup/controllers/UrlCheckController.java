package io.spring.iswebsiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "Site is up";
    private final String SITE_IS_DOWN = "Site is down";
    private final String SITE_IS_NOT_VALID = "Site is not valid";

    @GetMapping("/checkUrl")
    public String checkUrl(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code == 200 || code == 300) {
                returnMessage = SITE_IS_UP;
            }
            else {
                returnMessage = SITE_IS_DOWN;
            }
        }
        catch (MalformedURLException e) {
            returnMessage = SITE_IS_NOT_VALID;
        }
        catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }
    
}
