package com.gmail.jackkobec.run;

import com.gmail.jackkobec.parser.PageWordsParser;
import com.gmail.jackkobec.parser.PageWordsParserImpl;
import com.gmail.jackkobec.utils.impl.HTMLutilsImpl;
import com.gmail.jackkobec.utils.impl.URLutilsImpl;
import com.gmail.jackkobec.utils.interfaces.HTMLutils;
import com.gmail.jackkobec.utils.interfaces.URLutils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jack on 03.04.2017.
 */
public class RunApp {

    private static final URLutils urLutils = new URLutilsImpl();
    private static final HTMLutils htmLutils = new HTMLutilsImpl();
    private static final PageWordsParser pageWordsParser = new PageWordsParserImpl();

    public static void main(String[] args) throws IOException {

        System.out.println("****WELCOME TO THE PAGE WORDS COUNTER APP****");
        descriptor();

        try {
            String url = args[0];
//            file://TestPage.html
//            http://www.cyberforum.ru
//            file://C:/Users/Jack/IdeaProjects/PageWordsCounter/src/main/resources/TestPage.html
//            file://C:/Users/Jack/IdeaProjects/PageWordsCounter/src/main/resources/FailPage.txt
//            String url = "test";
            //check URL entered as CMD parameter
            if (!urLutils.checkUrl(url)) {
                throw new MalformedURLException("Incorrect URL. Try again.");

            } else {
                processCorrectUrl(url);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

            readUrlFromConsole();
        }
    }

    private static void processCorrectUrl(String url) throws IOException {
        System.out.println("URL CORRECT");
        System.out.println("REPORT for: " + url);

        boolean isPageHtml = htmLutils.checkIsHtml(url);
        System.out.println("Has page HTML content: " + isPageHtml);

        if (isPageHtml) {
            List<String> parsedCleanedAndSortedWords = pageWordsParser.splitAndCleanString(htmLutils.loadPage(url));
            pageWordsParser.countOfTheRepeatedWords(parsedCleanedAndSortedWords);
        }
    }

    private static void readUrlFromConsole() throws IOException {

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                descriptor();
                String url = sc.nextLine();

                if (!urLutils.checkUrl(url)) {
                    throw new MalformedURLException("Incorrect URL. Try again.");

                } else {
                    processCorrectUrl(url);
                    break;
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void descriptor() {
        System.out.println("\nPlease use URL without spaces and wrong characters. \nExample: https://www.google.com.ua" +
                "\nOr absolute path to FILE: \nExample: file://C:/Users/Jack/TestPage.html\n");
        System.out.print("Enter you URL: ");
    }
}