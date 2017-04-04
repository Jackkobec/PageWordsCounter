package com.gmail.jackkobec.parser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by Jack on 04.04.2017.
 */
public class PageWordsParserImpl implements PageWordsParser {

    @Override
    public String splitString(String string) {

//        String res = "";
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> allWords = new ArrayList<>();
//        StringTokenizer stringTokenizer = new StringTokenizer(string, " \t\n\r,.");

        string = someTagsContentKiller(string);
        string = htmlTagKiller(string);

        StringTokenizer stringTokenizer = new StringTokenizer(string, " \t\n\r,.:-;/<>");

        while (stringTokenizer.hasMoreTokens()) {


            // Получаем слово и что-нибудь делаем с ним, например,
            // просто выводим на экран
            System.out.println(stringTokenizer.countTokens());
//            res += stringTokenizer.nextElement() + " ";
            String currentWord = stringTokenizer.nextToken();
            System.out.println(currentWord);
            if (checkOnlyLetters(currentWord)) {
                allWords.add(currentWord);
            }
        }
        System.out.println(allWords);
        System.out.println(allWords.size());

        return stringBuilder.toString();
    }

    /**
     * Select only words consisting on letters
     *
     * @param word
     * @return
     */
    private boolean checkOnlyLetters(String word) {
        String ONLY_LETTERS_PATTERN = "[A-Za-z]*";

        return Pattern.compile(ONLY_LETTERS_PATTERN).matcher(word).matches();
    }

    /**
     * Replaces all HTML tags with spaces
     *
     * @param string
     * @return
     */
    private String htmlTagKiller(String string) {

        return string.replaceAll("<[a-zA-Z\\s/]+>", " ");
    }

    /**
     * Replaces some HTML tags with spaces
     *
     * @param string
     * @return
     */
    private String someTagsContentKiller(String string) {

        string = string.replaceAll("<style.*?>.*?</style>", " ");

        string = string.replaceAll("<script.*?>.*?</script>", " ");

        string = string.replaceAll("<img.*?/>.*?", " ");

        string = string.replaceAll("<object.*?>.*?</object>", " ");

        return string;
    }
}
