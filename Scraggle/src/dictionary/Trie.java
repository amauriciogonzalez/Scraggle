package dictionary;

public class Trie 
{
    private final TrieNode root;

    // This constructor instantiates the root as a node.
    Trie() 
    {
        this.root = new TrieNode();
    }

    /**
     * Add a new word from the English dictionary to this trie.
     *
     * @param word the word to add.
     */
    // This method inserts a word into the trie.
    public void insert(String word) 
    {
        // The String word is then converted into a sequence of characters.
        char[] arr = word.toCharArray();
        TrieNode node = root;
        int score = 0;

        /* Here, the colon is a shortcut for iterating over a collection. The left variable
           is a temporary variable containing an element from the array on the right. After
           every iteration, the next element is pulled from the collection and char c is
           assigned to it. */
        for (char c : arr) 
        {
            /* Here, we subtract the temporary variable c, with 'a' to locate an index to place
               the letter into the node's children array. */
            int idx = c - 'a';
            // The values() method returns the array of the enum constants.
            Alphabet alphabet = Alphabet.values()[idx];
            TrieNode[] children = node.getChildren();
            
            // If the node from the children array of letters is uninstantied, it is injected here.
            if (children[idx] == null) 
                children[idx] = new TrieNode();
            
            // We then traverse down.
            node = children[idx];
            
            // The sum total of each letters' score from the word is instantiated here.
            score += alphabet.getScore();
        }
        
        // The node now contains the sum total of the word's score from its letters.
        node.setScore(score);
    }

    /**
     * Find if the given word belongs in the trie by returning its score.
     *
     * @param word the input word.
     * @return the score of the word if it's valid, 0 otherwise.
     */
    // This method searches a word's score from the trie.
    public int search(String word) 
    {
        System.out.println("Trie.search: Searching for word " + word);
        TrieNode node = root;
        char[] arr = word.toCharArray();

        // We loop through each letter from the word that we search for.
        for (char c : arr) 
        {
            int idx = c - 'a';
            TrieNode[] children = node.getChildren();
            
            // If the word does not exist, we return the score = 0.
            if (children[idx] == null) 
                return 0;
            
            // We traverse down the trie.
            node = children[idx];
        }
        
        /* Once the trie is traversed, if the last letter exists, return the word's
           score. Otherwise, return 0 for the score. */
        return node != null ? node.getScore() : 0;
    }

    /**
     * Find if the given word is a prefix of some other word in the trie.
     *
     * @param word the input word.
     * @return true if the word is a prefix of some other word, false otherwise.
     */
    public boolean prefix(String word) 
    {
        System.out.println("Trie.search: Searching for prefix " + word);
        TrieNode node = root;
        char[] arr = word.toCharArray();

        // We loop through each letter from the prefix that we search for.
        for (char c : arr) 
        {
            int idx = c - 'a';
            TrieNode[] children = node.getChildren();
            
            /* If a letter from the prefix does not exist in current node's array of
               children, return false... */
            if (children[idx] == null) 
                return false;
            
            // ...otherwise traverse down.
            node = children[idx];
        }

        // Return true if the last letter of the prefix exists. Otherwise, return false.
        return node != null;
    }
}