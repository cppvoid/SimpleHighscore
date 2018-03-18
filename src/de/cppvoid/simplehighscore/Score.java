package de.cppvoid.simplehighscore;

public class Score {
    private String name;
    private int score;


    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "de.cppvoid.simplehighscore.Score{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
