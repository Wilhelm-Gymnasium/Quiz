package de.wilhelmgym.jeopardy.structure;

//Luci & Wiwi

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Question {

    public static final int NOOB = 0;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int ADVANCED = 3;
    public static final int HARD = 4;
    public static final int EXTREME = 5;

    private static final String KEY_QUESTION = "question";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_LEVEL_NOOB = "NOOB";
    private static final String KEY_LEVEL_EASY = "EASY";
    private static final String KEY_LEVEL_MEDIUM = "MEDIUM";
    private static final String KEY_LEVEL_ADVANCED = "ADVANCED";
    private static final String KEY_LEVEL_HARD = "HARD";
    private static final String KEY_LEVEL_EXTREME = "EXTREME";
    private static final String KEY_ANSWERS = "answers";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_PREFERRED_ANSWER_POSITION = "preferredAnswerPosition";

    private String question;
    private Answers answers;
    private String category;
    private int level;

    //TODO JSON parsing
    public static void fromJson(String json){
        try {
            fromJson(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static Question fromJson(JSONObject json){
        String question;
        Answers answers = new Answers();
        String category;
        int level = MEDIUM;

        if(!json.has(KEY_QUESTION) || !json.has(KEY_ANSWERS) || !json.has(KEY_CATEGORY) || !json.has(KEY_LEVEL)){
            return null;
        }

        try {

            //Question
            if(TextUtils.isEmpty(json.getString(KEY_QUESTION))){
                return null;
            }
            question = json.getString(KEY_QUESTION);

            //Answers
            JSONArray answersArray = json.getJSONArray(KEY_ANSWERS);
            if(answersArray == null || answersArray.length() < 1){
                return null;
            }
            for (int i = 0; i < answersArray.length(); i++) {
                if(TextUtils.isEmpty(answersArray.getString(i))){
                    return null;
                }
                answers.add(answersArray.getString(i));
            }

            //Category
            if(TextUtils.isEmpty(json.getString(KEY_CATEGORY))){
                return null;
            }
            category = json.getString(KEY_CATEGORY);

            //Level
            switch (json.getString(KEY_LEVEL)){
                case KEY_LEVEL_NOOB:
                    level = NOOB;
                    break;
                case KEY_LEVEL_EASY:
                    level = EASY;
                    break;
                case KEY_LEVEL_MEDIUM:
                    level = MEDIUM;
                    break;
                case KEY_LEVEL_ADVANCED:
                    level = ADVANCED;
                    break;
                case KEY_LEVEL_HARD:
                    level = HARD;
                    break;
                case KEY_LEVEL_EXTREME:
                    level = EXTREME;
                    break;
                default:
                    return null;
            }
            return new Question(question, answers, category, level);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Question(String question, Answers answer, String category, int level) {
        this.question = question;
        this.answers = answer;
        this.category = category;
        this.level = level;
    }

    public Question(String question, Answers answer, String category) {
        this(question, answer, category, MEDIUM);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public String getCategory(String category) {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level >= NOOB && level <= EXTREME) {
            this.level = level;
        }
    }
}
