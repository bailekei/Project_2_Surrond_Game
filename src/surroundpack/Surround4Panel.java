package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Surround4Panel extends JPanel {

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
 * this sets the layout of the buttons on the board and a new panel for the board
 */
        setLayout(new BorderLayout());
        //raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.Raised)
        //Surround4Panel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.cyan));
        panel1 = new JPanel();
/**
 * This sets the default board size to 10
 * This also asks the player what size board they want as long as its larger than three and smaller than twenty
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

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer only between 3-15.", "Alert", JOptionPane.ERROR_MESSAGE);
            bSize = Integer.parseInt(JOptionPane.showInputDialog("Enter board size"));
        }

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

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer greater than 0.", "Alert", JOptionPane.ERROR_MESSAGE);
            pNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Players: "));
        }

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

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer greater than 0.", "Alert", JOptionPane.ERROR_MESSAGE);
            pNum = Integer.parseInt(JOptionPane.showInputDialog("Who starts first?"));
        }

        createBoard(bSize);
        add(panel1, BorderLayout.CENTER);
        game = new Surround4Game();
		quitItem.addActionListener(listen);

	}

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
			if (e.getSource() == quitItem)
				System.exit(1);

            if (e.getSource() == newGameItem){
                panel1.removeAll();

                panel1.revalidate();

            }
			for (int row = 0; row < board.length; row++)
                for (int col = 0; col < board[0].length; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            		board[row][col].setText(""+game.getCurrentPlayer());
                            player = game.nextPlayer();
                        } else
                        {JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");}

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


