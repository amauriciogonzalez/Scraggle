package dictionary;

public class TrieNode 
{
    private static final int ALPHABET_COUNT = 26;

    private final TrieNode[] children;
    private int score;

    // A node has an associated score and 26 possible children for 26 letters.
    TrieNode() 
    {
        this.children = new TrieNode[ALPHABET_COUNT];
        this.score = 0;
    }

    // This method returns the array of children.
    public TrieNode[] getChildren() 
    {
        return children;
    }
    
    // This getter returns the score of the node / letter.
    public int getScore() 
    {
        return score;
    }

    /* This setter instantiates the score of the node. Score increases as the
       depth from the root increases. */
    public void setScore(int score) 
    {
        this.score = score;
    }
}
