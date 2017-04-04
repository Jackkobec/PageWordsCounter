package com.gmail.jackkobec.parser;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Jack on 04.04.2017.
 */
public class PageWordsParserImpl implements PageWordsParser {

    private static final String ONLY_LETTERS_PATTERN = "[A-Za-z]*";
    private static final String DELETE_TAGS_PATTERN = "<[a-zA-Z\\s/]+>";
    private static final String DELETE_STYLE_TAG_PATTERN = "<style.*?>.*?</style>";
    private static final String DELETE_SCRIPT_TAG_PATTERN = "<script.*?>.*?</script>";
    private static final String DELETE_IMG_TAG_PATTERN = "<img.*?/>.*?";
    private static final String DELETE_OBJECT_TAG_PATTERN = "<object.*?>.*?</object>";

    private static final String SPLIT_DELIMITER = " \t\n\r,.:-;/<>";

    /**
     * Delete HTML tag words and content of the some tags.
     * Split words by SPLIT_DELIMITER and add to the list.
     *
     * @param string
     * @return list of the sorted words from the page
     */
    @Override
    public List<String> splitAndCleanString(String string) {

        ArrayList<String> allWords = new ArrayList<>();

        string = someTagsContentKiller(string);
        string = htmlTagKiller(string);

        StringTokenizer stringTokenizer = new StringTokenizer(string, SPLIT_DELIMITER);

        while (stringTokenizer.hasMoreTokens()) {

            String currentWord = stringTokenizer.nextToken();

            if (checkOnlyLetters(currentWord)) {
                allWords.add(currentWord);
            }
        }

        System.out.println("\nParsed " + allWords.size() + " words.");
        return sortWords(allWords.get(0).equalsIgnoreCase("html")
                ? allWords.subList(1, allWords.size())
                : allWords);
    }

    /**
     * Count all uniqie repeated words.
     *
     * @param listOfAllWords
     * @return
     */
    @Override
    public Map<String, Integer> countOfTheRepeatedWords(List<String> listOfAllWords) {

        Set<String> words = new LinkedHashSet<>();//set for add any word and check is it unique
        Set<String> uniqueRepeatedWords = new LinkedHashSet<>();//set for save unique repeated words
        int count = 0;

//        System.out.println("listOfAllWords: " + listOfAllWords);
        for (String s : listOfAllWords) {

            if (words.add(s)) {

            } else {
                uniqueRepeatedWords.add(s);
                count++;
            }
        }

        System.out.println("\nCount of repeated words = " + (count + uniqueRepeatedWords.size()));
        System.out.println("\nUnique repeated words: " + uniqueRepeatedWords);

        return countRepeatsForEachWordFromList(listOfAllWords, uniqueRepeatedWords);
    }


    /**
     * Count repeats for each unique word.
     *
     * @param listOfAllWords
     * @param uniqueRepeatedWords
     * @return
     */
    @Override
    public Map<String, Integer> countRepeatsForEachWordFromList(List<String> listOfAllWords, Set<String> uniqueRepeatedWords) {

        Map<String, Integer> wordsWithCountMap = new LinkedHashMap<>();

//        for (String key : uniqueRepeatedWords) {
//            wordsWithCountMap.put(key, (int) listOfAllWords.stream().filter(el -> el.equals(key)).count());
//        }
        uniqueRepeatedWords.forEach(k -> wordsWithCountMap.put(k, (int) listOfAllWords.stream()
                .filter(el -> el.equals(k)).count()));

        System.out.println("\nStatistics for every unique repeated word:");
        wordsWithCountMap.forEach((k, v) -> System.out.println(k + " - " + v + " repeats;"));

        return wordsWithCountMap;
    }


    /**
     * Sorting words by alphabet order and Upper before Lower case.
     *
     * @param words
     * @return
     */
    public List<String> sortWords(List<String> words) {

        return words.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Select only words consisting on letters
     *
     * @param word
     * @return
     */
    private boolean checkOnlyLetters(String word) {

        return Pattern.compile(ONLY_LETTERS_PATTERN).matcher(word).matches();
    }

    /**
     * Replaces all HTML tags with spaces.
     *
     * @param string
     * @return
     */
    private String htmlTagKiller(String string) {

        return string.replaceAll(DELETE_TAGS_PATTERN, " ");
    }

    /**
     * Replaces some HTML tags with spaces.
     *
     * @param string
     * @return
     */
    private String someTagsContentKiller(String string) {

        string = string.replaceAll(DELETE_STYLE_TAG_PATTERN, " ").replaceAll(DELETE_SCRIPT_TAG_PATTERN, " ")
        .replaceAll(DELETE_IMG_TAG_PATTERN, " ").replaceAll(DELETE_OBJECT_TAG_PATTERN, " ");

        return string;
    }
}
