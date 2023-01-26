/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Adrian
 */
public class Dictionary
{
    // Here, we set the name of the text file to read into a string.
    private static final String WORDS_FILE = "words.txt";
    
    // We declare an instance of class Trie to insert the words found in each line from words.txt.
    private final Trie trie;
    
    // This constructor reads the text file for words to insert into the trie.
    public Dictionary()
    {
        Scanner inputFile = null;
        String word;
        
        try
        {
            // The trie attribute is now an object to be used.
            this.trie = new Trie();
            
            // The input file is opened.
            URL url = getClass().getResource(WORDS_FILE);
            File file = new File(url.toURI());
            inputFile = new Scanner(file);
            
            // If the file was not detected, an error occurs.
            if (inputFile == null)
            {
                throw new IOException("Invalid URL specified");             
            }
            
            // The data from the input file is read until there is none to collect.
            while (inputFile.hasNext())
            {
                // After a word is read, it is set lowercase and inserted into the trie.
                word = inputFile.next();
                word = word.trim().toLowerCase();
                trie.insert(word);
            }
            
            System.out.println("Loaded all words into the trie");
        }
        catch (IOException | URISyntaxException e)
        {
            System.out.println("There was an error reading the file or loading the word(s) into the trie.");
            throw new RuntimeException(e);
        }   
        finally 
        {
            // The input file is then closed if an input file was detected.
            try 
            {
                if (inputFile != null)
                {
                    inputFile.close();
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Error while closing reader - " + e.getMessage());
            }
        }
    }
    
    // This method searches a word's score from the trie.
    public int search(String word)
    {
        return this.trie.search(word);
    }
    
    /* This method returns the truth value of the given
       prefix if it is a prefix of some other word in the
       trie. */
    public boolean prefix(String word)
    {
        return this.trie.prefix(word);
    }
}
