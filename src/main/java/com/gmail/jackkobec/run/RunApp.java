package com.gmail.jackkobec.run;

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

    public static void main(String[] args) throws IOException {

        System.out.println("****WELCOME TO THE PAGE WORDS COUNTER APP****\n");
        descriptor();

        try {
//            String url = args[0];
//            file://TestPage.html
//            http://www.cyberforum.ru
//            file://C:/Users/Jack/IdeaProjects/PageWordsCounter/src/main/resources/TestPage.html
//            file://C:/Users/Jack/IdeaProjects/PageWordsCounter/src/main/resources/FailPage.txt
            String url = "test";
            //check URL entered as CMD parameter
            if (!urLutils.checkUrl(url)) {
                throw new MalformedURLException("Incorrect URL. Try again.");

            } else {

                System.out.println("URL CORRECT");
                System.out.println("REPORT for: " + url);

                boolean isHtml = htmLutils.checkIsHtml(url);
                System.out.println(isHtml);

                if (isHtml) {
                    List<String> parsedCleanedAndSortedWords = new PageWordsParserImpl().splitAndCleanString(htmLutils.loadPage(url));
                    new PageWordsParserImpl().countOfTheRepeatedWords(parsedCleanedAndSortedWords);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

            readUrlFromConsole();
        }
    }

    private static void descriptor() {
        System.out.println("Please use URL without spaces and wrong characters. \nExample: https://www.google.com.ua" +
                "\nOr absolute path to FILE: \nExample: file://C:/Users/Jack/TestPage.html\n");
        System.out.print("Enter you URL: ");
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

                    System.out.println("URL CORRECT");
                    System.out.println("REPORT for: " + url);

                    boolean isHtml = htmLutils.checkIsHtml(url);
                    System.out.println(isHtml);

                    if (isHtml) {
                        List<String> parsedCleanedAndSortedWords = new PageWordsParserImpl().splitAndCleanString(htmLutils.loadPage(url));
                        new PageWordsParserImpl().countOfTheRepeatedWords(parsedCleanedAndSortedWords);
                    }

                    break;
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
}