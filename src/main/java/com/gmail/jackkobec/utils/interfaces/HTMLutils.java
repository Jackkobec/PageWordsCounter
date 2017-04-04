package com.gmail.jackkobec.utils.interfaces;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Jack on 04.04.2017.
 */
public interface HTMLutils {

    String loadPage(String url) throws IOException;

    boolean checkIsHtml(String url) throws IOException;
}
