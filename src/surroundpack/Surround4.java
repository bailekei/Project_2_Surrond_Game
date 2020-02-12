package surroundpack;

import javax.swing.*;

public class Surround4 {
	public static void main (String[] args)
	{
		/*  The JMenuBar is associated with the frame. The first step
		 *  is to create the menu items, and file menu.
		 */

		/**
		 * this method creates the menu bar, the file menu/dropdown, the quit button and the new game button
		 */
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem newGameItem;

/**
 * The method below creates the title of the game and displays it on the board
 */
		JFrame frame = new JFrame ("Surround");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
/**
 * the method below prints out the file, quit and new game texts on the dropdown menu buttons
 */
		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		newGameItem = new JMenuItem("New Game");
/**
 * this method below adds quit and new game buttons to the dropdown menu
 */
		fileMenu.add(quitItem);
		fileMenu.add(newGameItem);
/**
 * this method adds the buttons of file and new game into the menu/ on the bar across the top
 */
		menus = new JMenuBar();
		menus.add(fileMenu);
		menus.add(newGameItem);
/**
 * this method adds the frame to the surround4 panel
  */
		frame.setJMenuBar(menus);

		Surround4Panel panel = new Surround4Panel(quitItem);

/**
 * this method creates the panel to a default of 600x600 and lets the players see it
 */
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);

		//timesWon = new JLabel();
	}
}
