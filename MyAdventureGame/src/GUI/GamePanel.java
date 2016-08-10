package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Backend.Commands.SteuerZentrale;
import Backend.Commands.iCommandListener;
import Backend.Global.AdventureMain;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private AdventureMain _adventure;
	public GamePlayPanel gameplayPanel;
	private Image img;
	public TextArea textArea;
	public JTextField textInput;

	public GamePanel(AdventureMain adv) {
		_adventure = adv;

		// Setze Layout f�r das GamePanel
		this.setLayout(new BorderLayout());
		this.setSize(adv._frame.getWidth(), adv._frame.getHeight());

		// Linker Panel. Beinhaltet das GamePanel, sowie das
		// MultiplayerPanel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		this.add(leftPanel, BorderLayout.CENTER);

		// GameplayPanel, hier findet die Dynamik statt.
		gameplayPanel = new GamePlayPanel(adv);
		leftPanel.add(gameplayPanel, BorderLayout.CENTER);

		// MultiplayerPanel
		adv._mpPanel = new MultiplayerPanel(adv);
		leftPanel.add(adv._mpPanel, BorderLayout.PAGE_START);

		// InfoPanel
		InfoPanel infoPanel = new InfoPanel();
		this.add(infoPanel, BorderLayout.LINE_END);

	}

	public class InfoPanel extends JPanel {
		public InfoPanel() {
			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(220, getHeight()));

			// SteuerElement
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			JButton goNorth = new JButton("North");
			JButton goSouth = new JButton("South");
			JButton goEast = new JButton("East");
			JButton goWest = new JButton("West");

			c.gridx = 1;
			c.gridy = 0;
			c.insets = new Insets(10, 0, 0, 0);

			buttonPanel.add(goNorth, c);

			c.gridx = 0;
			c.gridy = 1;
			c.insets = new Insets(0, 10, 0, 0);
			buttonPanel.add(goWest, c);

			c.gridx = 2;
			c.gridy = 1;
			c.insets = new Insets(0, 0, 0, 10);
			buttonPanel.add(goEast, c);

			c.gridx = 1;
			c.gridy = 2;
			c.insets = new Insets(0, 0, 10, 0);
			buttonPanel.add(goSouth, c);

			JButton getMap = new JButton("Map");
			getMap.addActionListener(new iCommandListener("map", _adventure));

			c.gridx = 1;
			c.gridy = 1;
			c.insets = new Insets(10, 10, 10, 10);
			buttonPanel.add(getMap, c);

			goNorth.addActionListener(new SteuerZentrale("goNorth", _adventure));
			goSouth.addActionListener(new SteuerZentrale("goSouth", _adventure));
			goEast.addActionListener(new SteuerZentrale("goEast", _adventure));
			goWest.addActionListener(new SteuerZentrale("goWest", _adventure));

			this.add(buttonPanel, BorderLayout.PAGE_START);

			// TextArea Output
			textArea = new TextArea("Chat");
			textArea.setEditable(false);
			textArea.setVisible(true);
			this.add(textArea, BorderLayout.CENTER);

			// TextField Input
			textInput = new JTextField();
			textInput.setVisible(true);
			textInput.setEditable(true);
			this.add(textInput, BorderLayout.PAGE_END);

		}

	}

}
