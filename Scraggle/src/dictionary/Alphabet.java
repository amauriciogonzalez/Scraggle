/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Random;

/**
 *
 * @author Adrian
 */
public enum Alphabet
{
    // Each of the 26 letters has an associated score.
    A(1),
    B(3),
    C(3),
    D(2),
    E(1),
    F(4),
    G(2),
    H(4),
    I(1),
    J(8),
    K(5),
    L(1),
    M(3),
    N(1),
    O(1),
    P(3),
    Q(10),
    R(1),
    S(1),
    T(1),
    U(1),
    V(4),
    W(4),
    X(8),
    Y(4),
    Z(10);
    
    private final int score;
    
    /* We need to randomly select letters into a four by four grid.
       So, we set the scale here. */
    private static final int LETTERS = 26;
    
    /* We cannot instantiate an object of Alphabet, an enum type, so
       we leave the public modifier off. It only needs to be used in this
       enum, so we don't need a modifier. This is the same as making this
       constructor private. */
    // This constructor sets the attribute, score, equal to the parameter.
    Alphabet(int score)
    {
        this.score = score;
    }
    
    // This method returns a string representing an object in lowercase characters.
    public String get()
    {
        return this.toString().toLowerCase();
    }
    
    // This getter returns the score attribute.
    public int getScore()
    {
        return this.score;
    }
    
    /* This method generates a random constant (a letter with its score)
       from this enumeration. The random letter and its value is then
       returned. This is useful for generating the randomly generated grid
       of letters and their score. */
    public static Alphabet newRandom()
    {
        Random random = new Random();
        int value = random.nextInt(LETTERS);
        Alphabet alphabet = Alphabet.values()[value];
        return alphabet; 
    }
    
}
