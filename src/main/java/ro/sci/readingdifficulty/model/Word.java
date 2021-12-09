package ro.sci.readingdifficulty.model;

import java.util.ArrayList;
import java.util.List;

import static ro.sci.readingdifficulty.common.Constant.consoleColloring.*;

public class Word {
    private String word;
    private int readingDifficultyScore;
    private List<String> vowelSequenceList = new ArrayList<>();
    private List<String> doubleConsonantList = new ArrayList<>();
    private List<String> hardToReadSequencesList = new ArrayList<>();

    private char[] lettersList;

    private String[] hardToReadSequences = {"ch", "ei", "ie"};

    public Word(String word) {
        this.word = word;
        calculateReadingDifficultyForWord();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getReadingDifficultyScore() {
        return readingDifficultyScore;
    }

    public void setReadingDifficultyScore(int readingDifficultyScore) {
        this.readingDifficultyScore = readingDifficultyScore;
    }

    public char[] getLettersList() {
        return getWord().toCharArray();
    }

    public void calculateReadingDifficultyForWord() {

        lettersList = getLettersList();
        String vowelSequence = "";
        String doubleConsonantSequence = "";

        char previousLetter = Character.MIN_VALUE;
        for (char letter : lettersList) {
            if (isVowel(letter)) {
                vowelSequence += letter;
                doubleConsonantSequence = "";
            } else {
                if (vowelSequence.length() >= 3) {
                    vowelSequenceList.add(vowelSequence);
                }
                vowelSequence = "";
                if (letter == previousLetter) {
                    doubleConsonantSequence += letter;
                    if (doubleConsonantSequence.length() >= 2) {
                        doubleConsonantList.add(doubleConsonantSequence);
                    }
                } else {
                    doubleConsonantSequence = "";
                }
            }
            previousLetter = letter;
        }

        for (String sequence : hardToReadSequences) {
            if (word.indexOf(sequence) >= 0) {
                hardToReadSequencesList.add(sequence);
            }
        }

        this.setReadingDifficultyScore(doubleConsonantList.size() + vowelSequenceList.size() + hardToReadSequencesList.size());

    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' ||
                ch == 'i' || ch == 'o' ||
                ch == 'u');
    }

    private String detailReadingDifficulty() {

        StringBuilder sb = new StringBuilder();

        if (doubleConsonantList.size() > 0) {
            sb.append("double consonants : " + doubleConsonantList);
        }
        if (vowelSequenceList.size() > 0) {
            sb.append((sb.length() > 0 ? " " : "") + "vowel sequences: " + vowelSequenceList);
        }
        if (hardToReadSequencesList.size() > 0) {
            sb.append((sb.length() > 0 ? " " : "") + "hard to read sequences: " + hardToReadSequencesList);
        }

        int sbLength = sb.length();

        return (sbLength > 1 ? " (" : "") + sb.toString() + (sbLength > 1 ? ")" : "");

    }

    @Override
    public String toString() {
        return TEXT_RED + this.getWord() + TEXT_GREEN + detailReadingDifficulty() + TEXT_RESET;
    }
}
