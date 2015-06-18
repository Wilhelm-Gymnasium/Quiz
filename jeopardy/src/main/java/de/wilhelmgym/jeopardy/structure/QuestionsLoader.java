package de.wilhelmgym.jeopardy.structure;

import android.content.res.Resources;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.wilhelmgym.jeopardy.R;

public class QuestionsLoader {

    public static List<Question> load(String json){
        List<Question> questions = new ArrayList<>();
        try {
            JSONArray questionsJson = new JSONArray(json);
            for (int i = 0; i < questionsJson.length(); i++) {
                questions.add(Question.fromJson(questionsJson.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static List<Question> load(Resources resources){
        InputStream raw = resources.openRawResource(R.raw.questions);
        BufferedReader r = new BufferedReader(new InputStreamReader(raw));
        try {
            StringBuilder total = new StringBuilder(raw.available());
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return load(total.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
