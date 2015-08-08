package de.wilhelmgym.quiz.structure;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    Map<Pair<String, Integer>, AnswerableQuestion> questions = new HashMap<>();

    private Game(Map<Pair<String, Integer>, AnswerableQuestion> questions) {
        this.questions = questions;
    }

    public static Game loadFromResources(Resources resources) {
        List<Question> allQuestions = QuestionsLoader.load(resources);
        List<String> allCategories = QuestionsLoader.loadCategories(resources);

        Map<Pair<String, Integer>, AnswerableQuestion> questions = new HashMap<>();

        //6 categories
        List<String> categories = new ArrayList<>(6);

        for (int i = 0; i < 6; i++) {
            int r = (int) Math.round(1 + Math.random() * allCategories.size());

            categories.add(i, allCategories.get(r));
            allCategories.remove(r);
        }

        while (questions.size() < 6 * 6) {
            int r = (int) Math.round(1 + Math.random() * allCategories.size());

            Question question = allQuestions.get(r);
            if (!questions.containsKey(new Pair<>(question.getCategory(), question.getLevel()))
                    && categories.contains(question.getCategory())) {
                questions.put(
                        new Pair<>(question.getCategory(), question.getLevel()),
                        (AnswerableQuestion) question
                );
            }

            allQuestions.remove(r);
        }

        return new Game(questions);
    }

    public static Game load(Bundle in) {

        //TODO Load from bundle

        return new Game(null);
    }

    public Bundle save() {
        Bundle out = new Bundle();

        //TODO Save to bundle

        return out;
    }

    public boolean validate() {
        //TODO Are there exactly 6 categories and 6 levels? (Luci)
        return true;
    }

    public Map<Pair<String, Integer>, AnswerableQuestion> getQuestions() {
        return questions;
    }
}
