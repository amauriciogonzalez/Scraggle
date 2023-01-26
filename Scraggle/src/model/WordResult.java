package model;

import java.util.List;

/* Word Result stores the result (score) of submitted words. */
public class WordResult 
{
    private final String word;
    private final int score;
    private final List<GridPoint> seq;

    /* A sequence of submitted words and their score is initialized in
       this constructor. */
    public WordResult(String word, int score, List<GridPoint> seq) 
    {
        this.word = word;
        this.score = score;
        this.seq = seq;
    }

    /* This method is a getter for the word. */
    public String getWord() 
    {
        return this.word;
    }

    /* This method is a getter for the word's score. */
    public int getScore() 
    {
        return this.score;
    }

    /* This method is a getter for the sequence of submitted words
       and their score. */
    public List<GridPoint> getSeq() 
    {
        return this.seq;
    }

    /* This method converts a word and the score into a string. */
    @Override
    public String toString() 
    {
        return '{' + "word='" + word + '\'' + ", score=" + score + '}';
    }
}
