package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import Backend.Commands.SteuerZentrale;
import Backend.Commands.iCommandListener;
import Backend.Global.AdventureMain;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
	{
		private AdventureMain _adventure;
		public GamePlayPanel gameplayPanel;
		
		

		public GamePanel(AdventureMain adv)
			{
				_adventure = adv;

				// Setze Layout für das GamePanel
				this.setLayout(new BorderLayout());
				this.setSize(adv._frame.getWidth(), adv._frame.getHeight());

				// Linker Panel. Beinhaltet das GamePanel, sowie das
				// MultiplayerPanel
				JPanel leftPanel = new JPanel();
				leftPanel.setLayout(new BorderLayout());
				this.add(leftPanel, BorderLayout.CENTER);

				// GameplayPanel, hier findet die Dynamik statt.
				gameplayPanel = new GamePlayPanel();
				leftPanel.add(gameplayPanel, BorderLayout.CENTER);

				// MultiplayerPanel
				MultiplayerPanel mp = new MultiplayerPanel(adv);
				leftPanel.add(mp, BorderLayout.PAGE_START);
				

				// InfoPanel
				InfoPanel infoPanel = new InfoPanel();
				this.add(infoPanel, BorderLayout.LINE_END);

			}

		//Gameplay Panel - Hier findet die Dynamik statt.
		public class GamePlayPanel extends JPanel implements ActionListener
			{
				public Timer tm = new Timer(100, this);

				public GamePlayPanel()
					{
						tm.start();

					}

				public void paintComponent(Graphics g)
					{
						g.setColor(Color.ORANGE);
						g.fillRect(0, 0, getWidth(), getHeight());
					}

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
						++_adventure._gameTime;
						paintComponent(this.getGraphics());
					}
			}

		public class InfoPanel extends JPanel
			{
				public InfoPanel()
					{
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
						TextArea textarea = new TextArea("Chat");
						textarea.setEditable(false);
						textarea.setVisible(true);
						this.add(textarea, BorderLayout.CENTER);

						// TextField Input
						JTextField textInput = new JTextField();
						textInput.setVisible(true);
						textInput.setEditable(true);
						this.add(textInput, BorderLayout.PAGE_END);
					}

			}

	}
