package de.wilhelmgym.jeopardy.structure;

//Luci & Wiwi

import java.util.ArrayList;
import java.util.Collection;

public class Answers extends ArrayList<String> {

    //The idea is to have multiple possible answers but one that will be shown if the answer was incorrect.

    private int preferredAnswerPosition;

    public int getPreferredAnswerPosition() {
        return preferredAnswerPosition;
    }

    public void setPreferredAnswerPosition(int preferredAnswerPosition) {
        this.preferredAnswerPosition = preferredAnswerPosition;
    }

    @Override
    public void add(int index, String object) {
        super.add(index, object);
        if (index <= preferredAnswerPosition) preferredAnswerPosition++;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> collection) {
        boolean listModified = super.addAll(index, collection);
        if (index <= preferredAnswerPosition) preferredAnswerPosition += collection.size();
        return listModified;
    }

    @Override
    public String remove(int index) {
        String removedItem = super.remove(index);
        if (index < preferredAnswerPosition) preferredAnswerPosition--;
        else if (index == preferredAnswerPosition) preferredAnswerPosition = 0;
        return removedItem;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            int temp = toIndex;
            toIndex = fromIndex;
            fromIndex = temp;
        }
        if (fromIndex < preferredAnswerPosition && toIndex < preferredAnswerPosition) {
            preferredAnswerPosition -= toIndex - fromIndex;
        } else if (fromIndex <= preferredAnswerPosition && toIndex >= preferredAnswerPosition) {
            preferredAnswerPosition = 0;
        }
        super.removeRange(fromIndex, toIndex);
    }
}
