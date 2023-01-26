/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import dictionary.Alphabet;
import dictionary.Dictionary;
import model.GridPoint;
import model.GridUnit;

/* Game creates the 4 x 4 array to store the random letters. */
public class Game
{
    /* An array is declared to be used as the game grid. */
    private final GridUnit[][] grid;
    private final Dictionary dictionary;
    
    /* This constructor populates the 4 x 4 grid with random letters. */
    public Game(Dictionary dictionary)
    {
        this.grid = new GridUnit[4][4];
        this.dictionary = dictionary;
        populateGrid();
    }
    
    /* This getter returns the grid. */
    public GridUnit[][] getGrid()
    {
        return this.grid;
    }
    
    /* This getter returns a specific GridUnit, or tile, from the grid. */
    public GridUnit getGridUnit(GridPoint point)
    {
        return this.grid[point.x][point.y];
    }
    
    /* The 4 x 4 grid is populated by this method here. */
    public void populateGrid()
    {
        /* A nested for loop is required to access every coordinate of
           the grid. */
        for (int row = 0; row < 4; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                /* A letter is randomly generated for the tile, thus generating
                   a new GridPoint that needs to be instantiated by registering
                   coordinate data.*/
                this.grid[row][col] = new GridUnit(Alphabet.newRandom(), new GridPoint(row, col));       
            }
        }
    }
    
    /* The whole grid is displayed here, neatly formatted. */
    public void displayGrid()
    { 
        for (int row = 0; row < 4; row++)
        {
            System.out.println("-------------------------");
            
            for (int col = 0; col < 4; col++)
            {
                // We need a nested for loop to access every element in this 2D array, grid.
                System.out.printf("|  %s  ", this.grid[row][col].getLetter());
            }
            
            System.out.println("|");
        }
        
        System.out.println("-------------------------");
    }
    
    public Dictionary getDictionary()
    {
        return this.dictionary;
    }
}
