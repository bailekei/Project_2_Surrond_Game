package surroundpack;

import javax.swing.*;

public class Surround4 {
	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		/*  The JMenuBar is associated with the frame. The first step
		 *  is to create the menu items, and file menu.
		 */
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem newGameItem;


		JFrame frame = new JFrame ("Surround");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		newGameItem = new JMenuItem("New Game");

		fileMenu.add(quitItem);
		fileMenu.add(newGameItem);

		menus = new JMenuBar();
		menus.add(fileMenu);
		menus.add(newGameItem);
		
		frame.setJMenuBar(menus);

		Surround4Panel panel = new Surround4Panel(quitItem);


		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}
