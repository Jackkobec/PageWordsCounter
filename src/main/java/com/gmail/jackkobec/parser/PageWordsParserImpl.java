package com.gmail.jackkobec.parser;

import java.util.StringTokenizer;

/**
 * Created by Jack on 04.04.2017.
 */
public class PageWordsParserImpl implements PageWordsParser {

    @Override
    public String splitString(String string) {

        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(string, " \t\n\r,.");

        while (stringTokenizer.hasMoreTokens()) {

            stringBuilder.append(stringTokenizer.nextToken());
            // Получаем слово и что-нибудь делаем с ним, например,
            // просто выводим на экран
            System.out.println(stringTokenizer.nextToken());

        }

        return stringBuilder.toString();
    }
}
