package com.gmail.jackkobec.utils.impl;

import com.gmail.jackkobec.utils.interfaces.URLutils;

import java.util.regex.Pattern;

/**
 * Created by Jack on 03.04.2017.
 */
public class URLutilsImpl implements URLutils {

    //URL pattern
    public static final String HTTP_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    @Override
    public boolean checkUrl(String url) {

        return Pattern.compile(HTTP_PATTERN).matcher(url).matches();
    }
}
