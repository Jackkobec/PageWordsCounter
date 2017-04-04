package com.gmail.jackkobec.utils.impl;

import com.gmail.jackkobec.exeptions.HtmlException;
import com.gmail.jackkobec.utils.interfaces.HTMLutils;

import java.io.*;
import java.net.URL;

/**
 * Created by Jack on 04.04.2017.
 */
public class HTMLutilsImpl implements HTMLutils {

    /**
     * Load page to the String from URL.
     *
     * @param url
     * @return
     * @throws IOException
     */
    @Override
    public String loadPage(String url) throws IOException {

        String stringLine = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        //url.substring(0, 4) - protocol place in the url
        if (url.substring(0, 4).equalsIgnoreCase("file")) {
            //substring(7) - path place without protocol and special symbols
            bufferedReader = new BufferedReader(new FileReader(new File(url.substring(7))));
        } else {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new URL(url).openConnection().getInputStream(), "UTF-8"));
        }

        while (true) {
            stringLine = bufferedReader.readLine();
            if (stringLine == null)
                break;

            stringBuilder.append(stringLine);
        }

        return stringBuilder.toString();
    }

    /**
     * Check page for HTML content.
     *
     * @param url
     * @return
     * @throws IOException
     */
    @Override
    public boolean checkIsHtml(String url) throws IOException {

        //clean URL from spaces
        String urlTrimed = url.replaceAll("\\s*", "");
        String page = loadPage(urlTrimed);

        try {
            if (page.contains("<!DOCTYPE html") || page.contains("<!doctype html")) {

                return true;

            } else {

                throw new HtmlException("This URL: " + url + " doesn't contains HTML content!");
            }

        } catch (HtmlException e) {
            e.printStackTrace();

            return false;
        }
    }
}
