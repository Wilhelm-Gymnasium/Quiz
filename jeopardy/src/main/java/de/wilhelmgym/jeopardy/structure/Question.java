package de.wilhelmgym.jeopardy.structure;

//Luci & Yanni

public class Question {

    public static final int NOOB = 0;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int ADVANCED = 3;
    public static final int HARD = 4;
    public static final int EXTREME = 5;

    private String question;
    private Answers answers;
    private String category;
    private int level;

    //TODO JSON parsing

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
