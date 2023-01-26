/* Scraggle - A program that allows any given user to play scraggle, a combination of
   scrabble and boggle.

   This program is to demonstrate Java coding proficiency.

   Adrian Mauricio-Gonzalez, 2021 */
package scraggle;

import dictionary.Dictionary;
import game.Game;
import userInterface.ScraggleUi;

/**
 *
 * @author Adrian
 */
public class Scraggle
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /* An instance of class Dictionary is instantiated, which contains
           the word data from the input file and their score. */
        Dictionary dictionary = new Dictionary();
        Game game = new Game(dictionary);
        game.displayGrid();
        
        /* An instance of class ScraggleUi is instantiated, which contains
           the user interface the user will interact with to play Scraggle. */ 
        ScraggleUi scraggleUi = new ScraggleUi(game);
            
    }
    
}
