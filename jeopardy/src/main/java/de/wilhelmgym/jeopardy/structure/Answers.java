package de.wilhelmgym.jeopardy.structure;

//Luci & Yanni

import java.util.ArrayList;

public class Answers extends ArrayList<String> {

    //The idea is to have multiple possible answers but one that will be shown if the answer was incorrect.

    private int preferredAnswerPosition; //TODO keep that in mind when updating the list of right answers

    // TODO override add(), remove(), ... because of preferredAnswerPosition
    @Override
    public void add(int index, String object) {
        super.add(index, object);
        if (index <= preferredAnswerPosition) preferredAnswerPosition++;
    }
}
