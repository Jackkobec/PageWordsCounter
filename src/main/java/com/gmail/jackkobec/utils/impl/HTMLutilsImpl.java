package com.gmail.jackkobec.utils.impl;

import com.gmail.jackkobec.exeptions.HtmlException;
import com.gmail.jackkobec.utils.interfaces.HTMLutils;

import java.io.*;
import java.net.URL;

/**
 * Created by Jack on 04.04.2017.
 */
public class HTMLutilsImpl implements HTMLutils {

    @Override
    public String loadPage(String url) throws IOException {

        String stringLine = null;
        BufferedReader bufferedReader = null;

//         bufferedReader = new BufferedReader(
//                new InputStreamReader(new URL(url).openConnection().getInputStream(), "UTF-8"));

        System.out.println("URL path: " + url);
        System.out.println("url.toString().substring(0, 4): " + url.substring(0, 4));
        System.out.println("url.substring(8): " + url.substring(7));

        if (url.substring(0, 4).equalsIgnoreCase("file")) {
            bufferedReader = new BufferedReader(new FileReader(new File(url.substring(7))));
        } else {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new URL(url).openConnection().getInputStream(), "UTF-8"));
        }

        while (true) {

            stringLine = bufferedReader.readLine();

            if (stringLine == null)
                break;

//            System.out.println(stringLine);
            return stringLine;
        }

        return null;
    }

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
