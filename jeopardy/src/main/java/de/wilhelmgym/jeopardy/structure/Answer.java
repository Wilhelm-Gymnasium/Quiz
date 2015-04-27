package de.wilhelmgym.jeopardy.structure;

//Luci & Yanni

import java.util.ArrayList;
import java.util.List;

public class Answer {

    //The idea is to have multiple possible answers but one that will be shown if the answer was incorrect.
    private List<String> rightAnswers = new ArrayList<>(); //TODO add, remove, ...
    private int preferedAnswerPosition; //TODO keep that in mind when updating the list of right answers

}
