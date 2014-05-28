package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import Backend.AdventureMain;
import Backend.World;
import Commands.SteuerZentrale;
import Commands.iCommandListener;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
	{
		private AdventureMain _adventure;
		public GamePlayPanel gameplayPanel;

		public GamePanel(AdventureMain adv)
			{
				super();
				this.setLayout(new BorderLayout());
				_adventure = adv;
				this.setVisible(true);
				this.setSize(adv._frame.getWidth(), adv._frame.getHeight());

				// ButtonPanel
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				buttonPanel.setVisible(true);
				this.add(buttonPanel, BorderLayout.PAGE_START);

				// InfoPanel
				InfoPanel infoPanel = new InfoPanel();
				this.add(infoPanel, BorderLayout.LINE_END);

				// Buttons
				JButton getMap = new JButton("Map");
				getMap.addActionListener(new iCommandListener("map", adv));
				buttonPanel.add(getMap);

				JButton goNorth = new JButton("go-North");
				JButton goSouth = new JButton("go-South");
				JButton goEast = new JButton("go-East");
				JButton goWest = new JButton("go-West");

				goNorth.addActionListener(new SteuerZentrale("goNorth", adv));
				goSouth.addActionListener(new SteuerZentrale("goSouth", adv));
				goEast.addActionListener(new SteuerZentrale("goEast", adv));
				goWest.addActionListener(new SteuerZentrale("goWest", adv));

				buttonPanel.add(goNorth);
				buttonPanel.add(goWest);
				buttonPanel.add(goEast);
				buttonPanel.add(goSouth);

				// GameplayPanel, hier findet die Dynamik statt.
				gameplayPanel = new GamePlayPanel();
				this.add(gameplayPanel, BorderLayout.LINE_START);

			}

		public class GamePlayPanel extends JPanel implements ActionListener
			{
				public Timer tm = new Timer(100, this);

				public GamePlayPanel()
					{

						tm.start();
						setPreferredSize(new Dimension(790, 600));
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
						this.setPreferredSize(new Dimension(400, 550));
						this.setVisible(true);

						this.setLayout(new GridBagLayout());
						GridBagConstraints c = new GridBagConstraints();

						// TextArea Output
						c.gridx = 0;
						c.gridy = 1;
						JTextArea textarea = new JTextArea("Chat");
						textarea.setPreferredSize(new Dimension(380, 100));
						textarea.setEditable(false);
						textarea.setVisible(true);
						this.add(textarea, c);

						// TextField Input
						c.gridy = 2;
						c.insets = new Insets(10, 0, 0, 0);
						c.ipady = 0; // reset to default
						c.weighty = 1.0; // request any extra vertical space
						c.anchor = GridBagConstraints.PAGE_END;
						JTextField textInput = new JTextField();
						textInput.setPreferredSize(new Dimension(380, 20));
						textInput.setVisible(true);
						textInput.setEditable(true);
						this.add(textInput, c);
						revalidate();
					

					}

			}

	}
