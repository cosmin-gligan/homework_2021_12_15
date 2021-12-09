package ro.sci.readingdifficulty;

import ro.sci.readingdifficulty.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ro.sci.readingdifficulty.common.Constant.CalculateReadingDifficulty.REQUEST_USER_INPUT;


/**
 * Write a program that evaluates the difficulty of reading a sentence in English.
 * Things that make words hard to read are:
 * - Occurrences of "ch", "ei", "ie" (ex. "ei" in "Receive")
 * - More than two vowels in a row (ex. "uai" in "Acquaintance")
 * - Double consonants (ex. "mm" and "tt" in "Committee")
 * In addition, sentences are hard to read if such words are close to each other.
 * <p>
 * Calculate a score for a sentence using the following rules:
 * - 1 point for each string that makes a word hard to read
 * - 2 points for each pair of words that have difficult to read strings
 * - 1 point for two words that have difficult to read strings and are separated by one other word
 * <p>
 * Try to use functions where appropriate. Ex. isVowelSequence, isDoubleConsonant, countDifficultStrings
 * <p>
 * Examples:
 * <p>
 * My acquaintance addresses the committee
 * Score from each word: 0 + 1 + 2 + 0 + 2 = 5
 * Score from sentence: 2 (acquaintance addresses) + 1 (addresses the committee)
 * Final score: 5 + 3 = 8
 * <p>
 * We perceive the quay's occasional accessibility as an issue
 * Note: "quay's" is considered a word
 * Score: 0 + 1 + 0 + 1 + 1 + 2 + 0 + 0 + 1 (from words) + 1+1 + 2 + 2 (from sentence) = 12
 */
public class Main {

    public static void main(String[] args) {

        System.out.println(REQUEST_USER_INPUT);
        Scanner sc = new Scanner(System.in);

        List<Word> wordList = getWordList(sc);

        while (wordList.size() < 3) {
            System.out.println(REQUEST_USER_INPUT);
            wordList = getWordList(sc);
            System.out.println(wordList.size());
        }

        for (Word word : wordList) {
            System.out.println(word + " difficulty is " + word.getReadingDifficultyScore());
        }


    }

    public static List<Word> getWordList(Scanner sc) {
        List<Word> tempWordList = new ArrayList<>();
        for (String sword : sc.nextLine().split(" ")) {
            tempWordList.add(new Word(sword));
        }
        return tempWordList;
    }


}
