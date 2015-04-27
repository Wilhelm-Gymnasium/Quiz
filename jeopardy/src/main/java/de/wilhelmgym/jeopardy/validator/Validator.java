package de.wilhelmgym.jeopardy.validator;

import de.wilhelmgym.jeopardy.structure.Question;

//Börgi & Nilsi

public class Validator {

    //TODO Implement this method.
    //TODO Tip: create helper classes in the de.wilhelmgym.jeopardy.validator package.
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
     * @param answer The answer to check
     * @return A double value where 0 < x <= 1.
     *         Zero should be returned with no congruence. One should be returned if the answer {@code equals()} the right answer.
     */
    public double validate(Question question, String answer){
        return 0;
    }
}
