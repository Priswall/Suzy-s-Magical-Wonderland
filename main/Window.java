package main;

import javax.swing.JFrame;

public class Window {
	public JFrame frame;
	public Window(Game game, String title, int width, int height) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.requestFocus();
		game.start();
	}
}
