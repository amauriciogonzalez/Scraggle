package model;

/* GameTime tracks the game play time. */
public class GameTime 
{
    private final int minutes;
    private final int seconds;

    /* This constructor initalizes the attributes minutes
       and seconds. */
    public GameTime(int minutes, int seconds) 
    {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /* This method takes a string, value, to store the minutes and
       seconds data from the string. */
    public static GameTime from(String value) 
    {
        String[] tokens = value.split("\\$");
        int minutes = Integer.parseInt(tokens[0]);
        int seconds = Integer.parseInt(tokens[1]);
        return new GameTime(minutes, seconds);
    }

    // This method is a getter for the attribute minutes.
    public int getMinutes() 
    {
        return minutes;
    }

    // This method is a getter for the attribute seconds.
    public int getSeconds() 
    {
        return seconds;
    }

    /* This method returns a string which displays minutes and
       seconds. */
    @Override
    public String toString() 
    {
        return this.minutes + "$" + this.seconds;
    }

    // This method calculates the total amount of seconds.
    public int getValueInSeconds() 
    {
        return this.minutes * 60 + this.seconds;
    }
}
