/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import game.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Adrian
 */

// This UI provides a front-end to play scraggle instead of the command line.
public class ScraggleUi
{
    // JFrame is the overall container for the user interface.
 	private JFrame frame;
    
    /* The menu bar provides a way to exit and start a new scraggle game without
       the need to exit the application. */
	private JMenuBar menuBar;
	private JMenu scraggleMenu;
	private JMenuItem newGame;
	private JMenuItem exit; 
    
    // This panel and its components below are for the scraggle board.
    private JPanel scraggleBoardPanel; 
    private JButton[][] scraggleDiceButtons;
    
    /* This panel and its components below are used to display the current word, score
       and provide a button to submit the current word. */
	private JPanel currentWordPanel;
    private JLabel currentWordLabel;
    private JButton currentWordSubmissionButton;   
    private JLabel playerScoreLabel;  
    
    /* This panel and its components are used to display all found words through
       a scrollable text pane, a label displaying time left, and a button to
       shake dice. */
	private JPanel foundWordsTimeAndShakeDicePanel;  
	private JScrollPane scrollPane;
	private JTextPane wordsEnteredPane;	
	private JLabel timeLeftLabel;
  	private JButton shakeDiceButton;    	    
    
    // Attribute game holds the current game's content and progression.
	private final Game game;
    
    // Stores the player's score.
    private int score = 0;
    
    // This setting allows one letter words to be entered.
    private final boolean ALLOW_ONE_LETTER_WORDS = false;
    
    // Attributes for the in-game timer.
    private Timer timer;
    private int minutes = 3;
    private int seconds = 0;
    
    // A list that stores the strings of found words.
    private final ArrayList<String> foundWords = new ArrayList<>();
    
    /* The best implementation is to create an attribute of the
       action listener if we are using more than once. Here, we
       use it for the new game menu item and the shake dice button.
       This action listener is used for multiple components. */
    // The event handler for resetting the board for a new game.
    private final ResetGameListener resetGameListener;
    
    /* This custom constructor initializes the attribute game
       with a game that is passed as an argument. It also initializes
       the components of the user interface. */
    public ScraggleUi(Game game)
    {
        this.game = game;
        resetGameListener = new ResetGameListener();
        initComponents();
    }
    
    /* This method initializes all the components for the UI and is
       called from the constructor. */
    private void initComponents()
    {
        /* The frame is initialized here, with its size defined as
           800 by 600 pixels. We also set it so that when the user
           interface is closed, the application in the back-end
           closes. The default layout manager is used, which is
           BorderLayout. */
        frame = new JFrame("Scraggle");
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* The menu bar is initialized here with one menu, Scraggle,
           with options to start a new game and exit. */
        initializeMenuBar();
        
        /* The south panel is initalized here in a flow layout, which
           is a layout to efficiently display components in a single
           row from left to right. This panel displays a current word
           label, a submit word button, and a score label. */
        initializeCurrentWordPanel();
        
        /* The west panel is initialized here in a 4 x 4 grid layout.
           Each grid entry is added to be a button, representing a die. */
        initializeBoard();
        
        /* The East panel is initialized here in a BoxLayout. This
           layout is used to display the text of the words entered,
           the time left in the game, and the shake dice button
           vertically. */
        initializeEastPanel();
        
        // The JMenuBar is then set on the JFrame.
        frame.setJMenuBar(menuBar);
        
        /* The timer is initialized and started below. The 1000 is in miliseconds. */
        this.timer = new Timer(1000, new TimerListener());
        this.timer.start();
        
        /* Action listeners are registered to menu items and buttons below. */
        newGame.addActionListener(resetGameListener);
        exit.addActionListener(new ExitListener());
        currentWordSubmissionButton.addActionListener(new SubmitListener());
        shakeDiceButton.addActionListener(resetGameListener);       
        
        /* All of the components/panels are added to the JFrame. */
        frame.add(currentWordPanel, BorderLayout.SOUTH);
        frame.add(scraggleBoardPanel, BorderLayout.WEST);
        frame.add(foundWordsTimeAndShakeDicePanel, BorderLayout.EAST);
        
        // The user interface is now visible to the user.
        frame.setVisible(true);
    }
    
    // This method initializes the components for the menu bar, menuBar.
    private void initializeMenuBar()
    {
        // The menu bar is instantiated here.
        menuBar = new JMenuBar();
        
        /* A menu called scraggle is set up with a mnemonic and is now
           accessible through the menu. */
        scraggleMenu = new JMenu("Scraggle");
        scraggleMenu.setMnemonic('s');
        menuBar.add(scraggleMenu);
        
        /* A menu item called New Game is now accessible through the
           menu with a mnemonic. */
        newGame = new JMenuItem("New Game");
        newGame.setMnemonic('w');
        scraggleMenu.add(newGame);
        
        /* A menu item called Exit is now accessible through the menu
           with a mnemonic. */
        exit = new JMenuItem("Exit");
        exit.setMnemonic('e');
        scraggleMenu.add(exit);
    }
    
    // This method initializes the components for the south panel, the currentwordpanel.
    private void initializeCurrentWordPanel()
    {
        /* The current word panel uses FlowLayout, which is good for efficiently display
           components in a single row from left to right. */
        currentWordPanel = new JPanel();
        currentWordPanel.setBorder(BorderFactory.createTitledBorder("Current Word"));
        FlowLayout flowLayout = new FlowLayout();
        currentWordPanel.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.CENTER);
        
        // The current word label is initialized.
        currentWordLabel = new JLabel("", JLabel.CENTER);
        currentWordLabel.setBorder(BorderFactory.createTitledBorder("Current Word"));
        currentWordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        currentWordLabel.setMinimumSize(new Dimension(350, 80));                            
        currentWordLabel.setPreferredSize(new Dimension(350, 80));
        
        // The current word submission button is initialized.
        currentWordSubmissionButton = new JButton("Submit Word");
        currentWordSubmissionButton.setPreferredSize(new Dimension(250, 80));
        
        // The player score label is initialized.
        playerScoreLabel = new JLabel(Integer.toString(score), JLabel.CENTER);
        playerScoreLabel.setBorder(BorderFactory.createTitledBorder("Score"));
        playerScoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        playerScoreLabel.setMinimumSize(new Dimension(150, 80));                           
        playerScoreLabel.setPreferredSize(new Dimension(150, 80));
        
        // All necessary componenets are added to the panel.
        currentWordPanel.add(currentWordLabel);
        currentWordPanel.add(currentWordSubmissionButton);
        currentWordPanel.add(playerScoreLabel);
    }
    
    // This method initializes the components for the east panel, the foundWordsTimeAndShakeDicePanel.
    private void initializeEastPanel()
    {
        /* The found words, time, and shake dice panel's layout is a BoxLayout, which is efficient
           to display components vertically. */
        foundWordsTimeAndShakeDicePanel = new JPanel();
        foundWordsTimeAndShakeDicePanel.setBorder(BorderFactory.createTitledBorder("Enter Words Found"));
        foundWordsTimeAndShakeDicePanel.setPreferredSize(new Dimension(280, 170));
        foundWordsTimeAndShakeDicePanel.setLayout(new BoxLayout(foundWordsTimeAndShakeDicePanel, BoxLayout.Y_AXIS));
        
        // The words entered pane is initialized here.
        wordsEnteredPane = new JTextPane();
        wordsEnteredPane.setEditable(false);
        
        // The scroll pane is initialized here.
        scrollPane = new JScrollPane(wordsEnteredPane);
        scrollPane.setMinimumSize(new Dimension(600, 150));
        scrollPane.setPreferredSize(new Dimension(600, 150));
        scrollPane.setMaximumSize(new Dimension(600, 150));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // The time left label is initialized here.
        timeLeftLabel = new JLabel("3:00", JLabel.CENTER);
        timeLeftLabel.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        timeLeftLabel.setBorder(BorderFactory.createTitledBorder("Time Left"));
        timeLeftLabel.setMinimumSize(new Dimension(8000, 125));
        timeLeftLabel.setPreferredSize(new Dimension(8000, 125));
        timeLeftLabel.setMaximumSize(new Dimension(8000, 125));
        
        // The shake dice button is initialized here.
        shakeDiceButton = new JButton("Shake Dice");
        shakeDiceButton.setMinimumSize(new Dimension(380, 125));
        shakeDiceButton.setPreferredSize(new Dimension(380, 125));
        shakeDiceButton.setMaximumSize(new Dimension(380, 125));
        
        // All necessary componenets are added to the panel.
        foundWordsTimeAndShakeDicePanel.add(scrollPane);
        foundWordsTimeAndShakeDicePanel.add(timeLeftLabel);
        foundWordsTimeAndShakeDicePanel.add(shakeDiceButton);
    }
    
    // This method initializes the west panel, the 4 x 4 board of buttons.
    private void initializeBoard()
    {
        /* The scraggle board panel uses GridLayout, which is efficient for
           creating a grid of buttons. */
        scraggleBoardPanel = new JPanel();
        scraggleBoardPanel.setBorder(BorderFactory.createTitledBorder("Scraggle Board"));
        scraggleBoardPanel.setLayout(new GridLayout(4, 4));
        scraggleBoardPanel.setPreferredSize(new Dimension(500, 0));       
        
        // The JButton grid is initilized.
        scraggleDiceButtons = new JButton[4][4];
        for (int row = 0; row < 4; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                /* We create a JButton for every slot in the 4 x 4 grid.
                   With icon, we add the image to the button. */
                URL imgURL = getClass().getResource(game.getGrid()[row][col].getImgPath());
                ImageIcon icon = new ImageIcon(imgURL);
                scraggleDiceButtons[row][col] = new JButton(icon);
                
                /* Each button is given client properties recording its corresponding
                   row and column number and their letter for the action listeners. */
                scraggleDiceButtons[row][col].putClientProperty("row", row);
                scraggleDiceButtons[row][col].putClientProperty("col", col);
                scraggleDiceButtons[row][col].putClientProperty("letter", game.getGrid()[row][col].getLetter());
                
                /* TileListener and LetterListener are registered to each dice button. */ 
                TileListener tileListener = new TileListener();
                LetterListener letterListener = new LetterListener();
                scraggleDiceButtons[row][col].addActionListener(tileListener);
                scraggleDiceButtons[row][col].addActionListener(letterListener);
           
                // All necessary componenets are added to the panel.
                scraggleBoardPanel.add(scraggleDiceButtons[row][col]);
            }
        }
    }

    
    /* Below are all the inner class event handlers / action listeners for our components.
       The terms event handler and action listener can be used interchangeably. We make
       these private inner classes since no other class outside of ScraggleUi should use
       these classes. */
    
    /* ExitListener is an ActionListener that is registered to the JMenuItem to exit, in which
       it exits the user interface. */
    private class ExitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // If the user clicks yes, .showConfirmDialog() returns 0. If the user clicks no, it returns 1.
            if (JOptionPane.showConfirmDialog(ScraggleUi.this.frame, "Confirm to exit Scraggle?", "Exit?",  JOptionPane.YES_NO_OPTION) == 0)
            {
                System.exit(0);
            }
        }     
    }
    
    /* ResetGameListener is an ActionListener that is registered to the JMenuItem new game and
       the shake dice button to completely reset the board, score, time, and the found words list.*/
    private class ResetGameListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {       
            // The score is reset to 0.
            ScraggleUi.this.score = 0;
            ScraggleUi.this.playerScoreLabel.setText(Integer.toString(score));
            
            /* The board resets and a new one is created. */
            ScraggleUi.this.game.populateGrid();            
            ScraggleUi.this.frame.remove(ScraggleUi.this.scraggleBoardPanel);
            ScraggleUi.this.scraggleBoardPanel.removeAll();
            initializeBoard();
            ScraggleUi.this.scraggleBoardPanel.revalidate();
            ScraggleUi.this.scraggleBoardPanel.repaint();
            ScraggleUi.this.frame.add(ScraggleUi.this.scraggleBoardPanel, BorderLayout.WEST);
            
            // The new grid is outputted to the console.
            ScraggleUi.this.game.displayGrid();
            
            // The time, the words entered pane, and the array list of found words are reset.
            ScraggleUi.this.wordsEnteredPane.setText("");
            ScraggleUi.this.timeLeftLabel.setText("3:00");
            ScraggleUi.this.foundWords.removeAll(ScraggleUi.this.foundWords);
            
            // The timer is then reset and started again from 3:00.
            ScraggleUi.this.timer.stop();
            ScraggleUi.this.minutes = 3;
            ScraggleUi.this.seconds = 0;
            ScraggleUi.this.timer.start();
        } 
    }
    
    /* SubmitListener is an ActionListener that is registered to the submit current word
       button, in which it submits the current word to the found words list and updates
       the score, assuming the word exists in the dictionary. */
    private class SubmitListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // The word's score is searched in the dictionary.
            int wordScore = game.getDictionary().search(ScraggleUi.this.currentWordLabel.getText().toLowerCase());
            System.out.println(wordScore);
            
            // If the word was already found, the user is notified.
            if (ScraggleUi.this.foundWords.contains(ScraggleUi.this.currentWordLabel.getText().toLowerCase()))
            {
                JOptionPane.showMessageDialog(ScraggleUi.this.frame, "Word found already.");
            }    
            // If the word does not exist, notify the user.
            else if (wordScore == 0)
            {
                JOptionPane.showMessageDialog(ScraggleUi.this.frame, "Not a valid word.");
            }
            /* If the word exists, the word is appended to the words entered pane and is
               added to the foundWords string array list. The score is then updated. */
            else
            {
                
                if (ScraggleUi.this.ALLOW_ONE_LETTER_WORDS)
                {
                    ScraggleUi.this.wordsEnteredPane.setText(ScraggleUi.this.wordsEnteredPane.getText() + ScraggleUi.this.currentWordLabel.getText()+ "\r\n");
                    ScraggleUi.this.foundWords.add(ScraggleUi.this.currentWordLabel.getText().toLowerCase());
                    ScraggleUi.this.score = ScraggleUi.this.score + wordScore;
                    ScraggleUi.this.playerScoreLabel.setText(Integer.toString(score));
                }
                else
                {
                    if (ScraggleUi.this.currentWordLabel.getText().toLowerCase().length() > 1)
                    {
                        ScraggleUi.this.wordsEnteredPane.setText(ScraggleUi.this.wordsEnteredPane.getText() + ScraggleUi.this.currentWordLabel.getText()+ "\r\n");
                        ScraggleUi.this.foundWords.add(ScraggleUi.this.currentWordLabel.getText().toLowerCase());
                        ScraggleUi.this.score = ScraggleUi.this.score + wordScore;
                        ScraggleUi.this.playerScoreLabel.setText(Integer.toString(score));
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(ScraggleUi.this.frame, "One letter words are not valid.");
                    } 
                }
            }
            
            // The current word label is then cleared.
            ScraggleUi.this.currentWordLabel.setText("");
            
            // All the buttons will now be enabled.
            for (int row = 0; row < 4; row++)
            {
                for (int col = 0; col < 4; col++)
                {
                    ScraggleUi.this.scraggleDiceButtons[row][col].setEnabled(true);
                }
            }
            
        }     
    }
    
    /* LetterListener is an ActionListener that is registered to each dice button, in which
       it appends a letter to the current word label. */
    private class LetterListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getSource() instanceof JButton)
            {
                /* The source is obtained and copied into tile below. Then from the action
                   property "letter", we obtain the button's associated letter, which is
                   then appended to the current word.*/
                JButton tile = (JButton)ae.getSource();
                String letter = (String)tile.getClientProperty("letter");
                ScraggleUi.this.currentWordLabel.setText(ScraggleUi.this.currentWordLabel.getText() + letter);          
            }
        }     
    }
    
    /* TileListener is an ActionListener that is registered to each dice button, in which
       it restricts the user to clicking tiles in a 1 tile proximity from a clicked button. */
    private class TileListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            JButton tile = (JButton)ae.getSource();
            int rowOfButtonClicked = (int)tile.getClientProperty("row");
            int columnOfButtonClicked = (int)tile.getClientProperty("col");
            
            /* If the tile is 1 tile away from the clicked tile, it is enabled. Otherwise,
               it is disabled. */
            for (int row = 0; row < 4; row++)
            {
                for (int col = 0; col < 4; col++)
                {
                    if ((row < rowOfButtonClicked - 1 || row > rowOfButtonClicked + 1) || 
                            (col < columnOfButtonClicked - 1 || col > columnOfButtonClicked + 1))
                    {
                        ScraggleUi.this.scraggleDiceButtons[row][col].setEnabled(false);
                    }
                    else
                    {
                        ScraggleUi.this.scraggleDiceButtons[row][col].setEnabled(true);
                    }
                    
                    /* The clicked tile is disabled. */
                    if (row == rowOfButtonClicked && col == columnOfButtonClicked)
                    {
                        ScraggleUi.this.scraggleDiceButtons[row][col].setEnabled(false);
                    }
                }
            }  
        }     
    }
    
    /* TimerListener is an ActionListener registered to the timer attribute, in which the timer
       in the timeLeftLabel is decremented every second until 0:00. */
    private class TimerListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            /* If the time is 0:00, the timer stops and the user is notified that time is up. */
            if (ScraggleUi.this.minutes == 0 && ScraggleUi.this.seconds == 0)
            {
                ScraggleUi.this.timer.stop();
                JOptionPane.showMessageDialog(ScraggleUi.this.frame, "Time is up! Game Over!");
            }
            /* If the time is not 0:00, the timer is decremented. */
            else
            { 
                if(ScraggleUi.this.seconds == 0)
                {
                    ScraggleUi.this.seconds = 59;
                    ScraggleUi.this.minutes--;
                }
                else
                {
                    ScraggleUi.this.seconds--;
                }
            }
            
            /* If there are x < 10 seconds left with y minutes then it is displayed as
               y:0x instead of y:x. */ 
            if(seconds < 10)
            {
                String strSeconds = "0" + String.valueOf(ScraggleUi.this.seconds);
                ScraggleUi.this.timeLeftLabel.setText(String.valueOf(minutes) + ":" + strSeconds);
            }
            /* Otherwise, the time is displayed normally. */ 
            else
            {
                ScraggleUi.this.timeLeftLabel.setText(String.valueOf(ScraggleUi.this.minutes) + ":" + String.valueOf(ScraggleUi.this.seconds));
            }
        }     
    }
}
