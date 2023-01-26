package model;

import game.UserResult;

/* GameStats stores the player's statistics. */
public class GameStats 
{
    private final UserResult userResult;

    /* This constructor instantiates an object of GameStats,
       instantiating the attribute userResult. */
    public GameStats(UserResult userResult) 
    {
        this.userResult = userResult;
    }

    @Override
    public String toString() 
    {
        String result = "Words - %d\nScore - %d";

        int userWordsCount = this.userResult.getWordCount();
        int userScore = this.userResult.getTotalScore();

        return String.format(result, this.userResult.getWordCount(),
                            this.userResult.getTotalScore());
    }
}
