package de.wilhelmgym.quiz.validator;

import android.util.Log;

import de.wilhelmgym.quiz.MainActivity;
import de.wilhelmgym.quiz.structure.Question;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.LongestCommonSubsequence;
import info.debatty.java.stringsimilarity.NGram;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import info.debatty.java.stringsimilarity.QGram;
import info.debatty.java.stringsimilarity.SorensenDice;

//Börgi & Nilsi

public class Validator {

    //TODO Implement this method.
    //TODO Tip: create helper classes in the de.wilhelmgym.quiz.validator package.
    /**
     * Checks the congruence of an answer with the right answer of a given question.
     * Compares:
     * - String length //TODO
     * - Case //TODO
     * - Swapped letters //TODO
     * - Forgotten letters //TODO
     * - Other Typos //TODO Please specify more precisely.
     *
     * @param question The question with the right answer in it
     * @param userAnswer The answer to check
     * @return A double value where 0 < x <= 1.
     * Zero should be returned with no congruence. One should be returned if the answer {@code equals()} the right answer.
     */
    public double validate(Question question, String userAnswer) {
        Log.d(MainActivity.TAG, "User answer: " + userAnswer);

        for(String answer : question.getAnswers()){
            Log.d(MainActivity.TAG, "Answer: " + answer);

            //Testing
            Levenshtein levenshtein = new Levenshtein();
            Log.d(MainActivity.TAG, "Levenshtein distance: " + levenshtein.distance(userAnswer, answer));

            NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();
            Log.d(MainActivity.TAG, "Normalized Levenshtein distance: " + normalizedLevenshtein.distance(userAnswer, answer));
            Log.d(MainActivity.TAG, "Normalized Levenshtein similarity: " + normalizedLevenshtein.similarity(userAnswer, answer));

            Damerau damerau = new Damerau();
            Log.d(MainActivity.TAG, "Damerau Levenshtein distance: " + damerau.distance(userAnswer, answer));

            JaroWinkler jaroWinkler = new JaroWinkler();
            Log.d(MainActivity.TAG, "Jaro-Winkler distance: " + jaroWinkler.distance(userAnswer, answer));
            Log.d(MainActivity.TAG, "Jaro-Winkler similarity: " + jaroWinkler.similarity(userAnswer, answer));

            LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
            Log.d(MainActivity.TAG, "Longest common subsequence distance: " + longestCommonSubsequence.distance(userAnswer, answer));

            NGram nGram = new NGram();
            Log.d(MainActivity.TAG, "N-Gram (Kondrak) distance: " + nGram.distance(userAnswer, answer));

            QGram qGram = new QGram();
            Log.d(MainActivity.TAG, "Q-Gram distance: " + qGram.distance(userAnswer, answer));

            Cosine cosine = new Cosine();
            Log.d(MainActivity.TAG, "Cosine distance: " + cosine.distance(userAnswer, answer));
            Log.d(MainActivity.TAG, "Cosine similarity: " + cosine.similarity(userAnswer, answer));

            Jaccard jaccard = new Jaccard();
            Log.d(MainActivity.TAG, "Jaccard distance: " + jaccard.distance(userAnswer, answer));
            Log.d(MainActivity.TAG, "Jaccard similarity: " + jaccard.similarity(userAnswer, answer));

            SorensenDice sorensenDice = new SorensenDice();
            Log.d(MainActivity.TAG, "Sorensen-Dice distance: " + sorensenDice.distance(userAnswer, answer));
            Log.d(MainActivity.TAG, "Sorensen-Dice similarity: " + sorensenDice.similarity(userAnswer, answer));


            //Relevant:
            /*
            Log.d(MainActivity.TAG, "Normalized Levenshtein similarity: " + normalizedLevenshtein.similarity(userAnswer, answer));
            Log.d(MainActivity.TAG, "Jaro-Winkler similarity: " + jaroWinkler.similarity(userAnswer, answer));
            Log.d(MainActivity.TAG, "Cosine similarity: " + cosine.similarity(userAnswer, answer));
            Log.d(MainActivity.TAG, "Jaccard similarity: " + jaccard.similarity(userAnswer, answer));
            Log.d(MainActivity.TAG, "Sorensen-Dice similarity: " + sorensenDice.similarity(userAnswer, answer));
            */

        }

        return -1;
    }
}