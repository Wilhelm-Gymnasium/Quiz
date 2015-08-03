package de.wilhelmgym.quiz.validator;

import android.util.Log;
import android.util.Pair;

import de.wilhelmgym.quiz.MainActivity;
import de.wilhelmgym.quiz.structure.Question;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import info.debatty.java.stringsimilarity.SorensenDice;

//Börgi & Heini

public class Validator {

    //TODO Improve these weights (write you suggestion behind the current weights): (All)
    private static final double WEIGHT_NORMALIZED_LEVENSHTEIN = 1; //Luci: 1, Wiwi: 1, Börgi: 1, Heini: 1
    private static final double WEIGHT_JARO_WINKLER = 1; //Luci: 1, Wiwi: 1, Börgi: 1, Heini: 1
    private static final double WEIGHT_COSINE = 1; //Luci: 1, Wiwi: 1, Börgi: 1, Heini: 1
    private static final double WEIGHT_JACCARD = 1; //Luci: 1, Wiwi: 1, Börgi: 1, Heini: 1
    private static final double WEIGHT_SORENSEN_DICE = 1; //Luci: 1, Wiwi: 1, Börgi: 1, Heini: 1
    
    /**
     * Checks the congruence of an answer with the right answer of a given question.
     * Compares:
     * - Normalized Levenshtein similarity
     * - Jaro-Winkler similarity
     * - Cosine similarity
     * - Jaccard similarity
     * - Sorensen-Dice similarity
     *
     * @param question The question with the right answer in it
     * @param userAnswer The answer to check
     * @return A double value where 0 <= x <= 1.
     * Zero should be returned with no congruence. One should be returned if the answer {@code equals()} the right answer.
     */
    public Pair<String, Double> validate(Question question, String userAnswer) {
        Log.d(MainActivity.TAG, "User answer: " + userAnswer);

        Pair<String, Double> maxSimilarity = new Pair<>(null, 0.0);

        for(String answer : question.getAnswers()){
            Log.d(MainActivity.TAG, "Answer: " + answer);

            NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();
            JaroWinkler jaroWinkler = new JaroWinkler();
            Cosine cosine = new Cosine();
            Jaccard jaccard = new Jaccard();
            SorensenDice sorensenDice = new SorensenDice();

            double similarity = (
                    normalizedLevenshtein.similarity(userAnswer, answer) * WEIGHT_NORMALIZED_LEVENSHTEIN
                            + jaroWinkler.similarity(userAnswer, answer) * WEIGHT_JARO_WINKLER
                            + cosine.similarity(userAnswer, answer) * WEIGHT_COSINE
                            + jaccard.similarity(userAnswer, answer) * WEIGHT_JACCARD
                            + sorensenDice.similarity(userAnswer, answer) * WEIGHT_SORENSEN_DICE
            ) / (
                    WEIGHT_NORMALIZED_LEVENSHTEIN
                            + WEIGHT_JARO_WINKLER
                            + WEIGHT_COSINE
                            + WEIGHT_JACCARD
                            + WEIGHT_SORENSEN_DICE
            );

            Log.d(MainActivity.TAG, "Similarity: \"" + answer + "\"->" + similarity);

            if (similarity > maxSimilarity.second) {
                maxSimilarity = new Pair<>(answer, similarity);
            }
        }

        Log.d(MainActivity.TAG, "Maximal similarity: \"" + maxSimilarity.first + "\"->" + maxSimilarity.second);

        return maxSimilarity;
    }
}