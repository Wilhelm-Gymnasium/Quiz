package de.wilhelmgym.quiz.structure;

import android.text.TextUtils;

public class AnswerableQuestion extends Question {
    String answer = "";

    public AnswerableQuestion(String question, Answers answer, String category, int level) {
        super(question, answer, category, level);
    }

    public AnswerableQuestion(String question, Answers answer, String category) {
        super(question, answer, category);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return !TextUtils.isEmpty(answer);
    }
}
