package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Surround4Panel extends JPanel {
    /**
     * this method creates all the buttons on the panel and all the menu items and the surrround4game
     */
    private JButton[][] board;
    private JPanel panel1;
    private int player;
    private ButtonListener listen;
    private JMenuItem quitItem;
    private Surround4Game game;
    private JMenuItem newGameItem;




    public Surround4Panel(JMenuItem pQuitItem) {
        //this quitItem quits the game and newGame item creates a new game.
        quitItem = pQuitItem;
        listen = new ButtonListener();
/**
 * this method sets the layout of the buttons on the board and a new panel for the board
 */
        setLayout(new BorderLayout());
        //raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.Raised)
        //Surround4Panel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.cyan));
        panel1 = new JPanel();
/**
 * This method sets the default board size to 10
 * This also asks the player what size board they want as long as its larger than three and smaller than twenty
 * @exception try-throw-catch
 */
        int bSize = 10;
        try{
        String brdSize = JOptionPane.showInputDialog("Enter in the size of the board: ", 10);
        bSize = Integer.parseInt(brdSize);
            while (bSize < 3 || bSize > 20) {
                try {
                    throw new IllegalArgumentException("Input for board size not valid");

                } catch (IllegalArgumentException te) {
                    JOptionPane.showMessageDialog(null, "\"Invalid input value. Must be 3-20.", "Alert", JOptionPane.ERROR_MESSAGE);
                } finally {

                    //always executing allowing for user to try entering in new size
                    bSize = Integer.parseInt(JOptionPane.showInputDialog("Enter board size"));
                }
            }
/**
 * this catch say if the player doesn't use a number between 3-20 in the above question this gives them an error message
 * then it asks them for the board size again until they choose a number between 3-20
 */
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer only between 3-15.", "Alert", JOptionPane.ERROR_MESSAGE);
            bSize = Integer.parseInt(JOptionPane.showInputDialog("Enter board size"));
        }
/**
 * This method sets the default players to two players before asking how many players there are in the game.
 * this also states that there has to be between 2 and 99 players
 * It gives an error message if someone inputs to many players or 1 player that it is invalid
 * @exception try-catch-throw
 */
        int pNumber = 2;
        try{
            String players = JOptionPane.showInputDialog("Enter number of Players: ", 2);
            pNumber = Integer.parseInt(players);
            while (pNumber < 2 || pNumber > 99) {
                try {
                    throw new IllegalArgumentException("Input for board size not valid");

                } catch (IllegalArgumentException te) {
                    JOptionPane.showMessageDialog(null, "\"Invalid input value. Must be greater than 0.", "Alert", JOptionPane.ERROR_MESSAGE);
                } finally {

                    //always executing allowing for user to try entering in new size
                    pNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Players: "));
                }
            }
/**
 *this method shows if someone enters 0 players there is a separate error message saying that there must be greater than 0 players
 * this also asks them how many players there are in the game and continues until there are a correct amount of players
 */
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer greater than 0.", "Alert", JOptionPane.ERROR_MESSAGE);
            pNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Players: "));
        }
/**
 * this try-catch-throw asks which player starts first with a default player number which is 0, which is assigned to player number one
 * this asks who will be the first person to start
 * it goes through to make sure that the board size is valid and that there are a valid number of players.
 * @exception try-catch-throw
 */
        int pNum = 0;

        try{
            String play = JOptionPane.showInputDialog("Who starts first?", 0);
            pNum = Integer.parseInt(play);
            while (pNum < 0 || pNum > pNumber) {
                try {
                    throw new IllegalArgumentException("Input for board size not valid");

                } catch (IllegalArgumentException te) {
                    JOptionPane.showMessageDialog(null, "\"Invalid input value. Must be between 0-"+ pNumber, "Alert", JOptionPane.ERROR_MESSAGE);
                } finally {

                    //always executing allowing for user to try entering in new size
                    pNum = Integer.parseInt(JOptionPane.showInputDialog("Who starts first?"));
                }
            }
/**
 * this catch warns the player that the board size is invalid and then it asks which player will start first until it is valid.
 */
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer greater than 0.", "Alert", JOptionPane.ERROR_MESSAGE);
            pNum = Integer.parseInt(JOptionPane.showInputDialog("Who starts first?"));
        }
/**
 * this method creates the boards physical attributes after the size of the board has been selected
 * also adds the dropdown menu and the quit item in the menu.
 */
        createBoard(bSize);
        add(panel1, BorderLayout.CENTER);
        game = new Surround4Game();
		quitItem.addActionListener(listen);

	}

	/**
     * This method adds active buttons to the game, each one of the tiles has to be chosen so it can be clicked
     * this also implements the ActionListener so it can actually do something.
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
			if (e.getSource() == quitItem)
				System.exit(1);
/**
 * this method creates a new game, by clearing the current game and re-asking the questions from above and re-printing
 */
            if (e.getSource() == newGameItem){
                panel1.removeAll();

                panel1.revalidate();
/**
 * this method adds or takes away rows depending on if the player says there are more or less rows than the default of 10
 * once the rows are added or taken away, columns are added or taken away depending on how many rows added or taken away
 * There is also an error that says that the player didn't make a valid square so there aren't as many rows
 * as there are columns.
 */
            }
			for (int row = 0; row < board.length; row++)
                for (int col = 0; col < board[0].length; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            		board[row][col].setText(""+game.getCurrentPlayer());
                            player = game.nextPlayer();
                        } else
                        {JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");}
/**
 * once the game is over this method displays the winner on the screen, it displays as long as the winner didn't get
 * negative one
 */
            displayBoard();
            int winner = game.getWinner();
            if (winner != -1) {
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");}
            else{
//                for (int row = 0; row < 10; row++)
//                    for (int col = 0; col < 10; col++)
//                            if (board [row][col] == e.getSource())
//                                if (game.select(row, col))
//                                    player = game.nextPlayer();
//                                else
//                                    JOptionPane.showMessageDialog(null, "Pick again.");
                //game = new Surround4Game();
                displayBoard();

            }
        }
    }

    /**
     * this method creates the actual player board and what the size it's going to be during game play
     * @param size
     */
    private void createBoard(int size) {

        board = new JButton[size][size];
        panel1.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) // rows
            for (int j = 0; j < size; j++) {
                board[i][j] = new JButton("");
                board[i][j].addActionListener(listen);
                panel1.add(board[i][j]);
            }
    }

    /**
     *This method displays the board and everything that is going to be on the board.
     */
    private void displayBoard() {

        for (int row = 0; row < 10; row++)
            for (int col = 0; col < 10; col++) {
                Cell c = game.getCell(row, col);
                if (c != null)
                    board[row][col].setText("" + c.getPlayerNumber());
                else
                    board[row][col].setText("");
            }

    }




}


