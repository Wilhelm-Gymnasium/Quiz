package de.wilhelmgym.jeopardy.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Börge on 07.05.2015.
 */


public class ValidatorWorkingStructure {
    //ordanisation-structure of references of each Letter

    public static int REFERENCESEARCHDISTANCE = 3;

    private char[] RightLetters;
    private List<List<Integer>> references = new ArrayList<>();


    public ValidatorWorkingStructure (String right)
    {
        RightLetters=right.toCharArray();
    }

    public void setReferences(String answer)
    {
        char[] answerCharArray = answer.toCharArray();

        int start=0;
        int stop=0;

        for (int i=0; i<RightLetters.length; i++)
        {
            start=i-REFERENCESEARCHDISTANCE;
            stop=i+REFERENCESEARCHDISTANCE;

            if(start<0)
            {
                start=0;
            }

            if(stop>=answerCharArray.length)
            {
                stop=answerCharArray.length-1;
            }

            references.add(i, new ArrayList<Integer>());

            for(int a=start; a<=stop; a++)
            {
                if(answerCharArray[a]==RightLetters[i])
                {
                    references.get(i).add(a);
                }
            }
        }

    }

    public int getNearestMatch(int index)
    {
        int min=0;

        for(List<Integer> b : references)
        {
            for(int a=0; a<b.size(); a++)
            if(a==0)
            {
                min=b.get(a);
            }
            else
            {
                if(b.get(a)<min)
                {
                    min=b.get(a);
                }
            }
        }
        return min;
    }

    public List<List<Integer>> getReferences()
    {
        return this.references;
    }




}
