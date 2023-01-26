/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.LinkedHashMap;
import java.util.Map;
import model.WordResult;

/* UserResult contains the total score depending on the player's submitted
   words. */
public class UserResult
{
    // Stores the user score.
    private int totalScore;
    
    // Stores the user words.
    /* "A hashmap is unique in that you cannot put duplicates in them.
       They behave like sets. */
    private final Map<String, WordResult> wordToResultMap = new LinkedHashMap<>();
    
    /* This method is a getter for the total score. */
    public int getTotalScore()
    {
        return this.totalScore;
    }
    
    /* This method puts the word and result into the map, wordToResultMap
       and add's the result's score into the total score. */
    public void add(String word, WordResult result)
    {
        this.wordToResultMap.put(word, result);
        this.totalScore = this.totalScore + result.getScore();
    }
    
    /* This getter retrieves a word from the result map. */
    public WordResult get(String word)
    {
        return this.wordToResultMap.get(word);
    }
    
    // This method returns the whole result map.
    public Map<String, WordResult> all()
    {
        return this.wordToResultMap;
    }
    
    // This method checks if a given word exists as an inputted result.
    public boolean exists(String word)
    {
        return this.wordToResultMap.containsKey(word);
    }
    
    // This method counts the size of the map, thus retrieving the word count.
    public int getWordCount()
    {
        return this.wordToResultMap.size();
    }
}
