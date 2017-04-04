package com.gmail.jackkobec.parser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jack on 04.04.2017.
 */
public interface PageWordsParser {

    List<String> splitAndCleanString(String string);

    Map<String, Integer> countOfTheRepeatedWords(List<String> listOfAllWords);

    Map<String, Integer> countRepeatsForEachWordFromList(List<String> listOfAllWords, Set<String> uniqueRepeatedWords);
}
