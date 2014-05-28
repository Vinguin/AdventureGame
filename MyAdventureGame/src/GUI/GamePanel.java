package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
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

				//Button 
				JButton getMap = new JButton("Map");
				getMap.addActionListener(new iCommandListener("map", adv));
				buttonPanel.add(getMap);
				
				JButton goNorth= new JButton("go-North");
				JButton goSouth= new JButton("go-South");
				JButton goEast= new JButton("go-East");
				JButton goWest= new JButton("go-West");
				
				goNorth.addActionListener(new SteuerZentrale("goNorth", adv));
				goSouth.addActionListener(new SteuerZentrale("goSouth", adv));
				goEast.addActionListener(new SteuerZentrale("goEast", adv));
				goWest.addActionListener(new SteuerZentrale("goWest", adv));

				buttonPanel.add(goNorth);
				buttonPanel.add(goWest);
				buttonPanel.add(goEast);
				buttonPanel.add(goSouth);
				

				this.add(buttonPanel, BorderLayout.NORTH);

				// GameplayPanel, hier findet die Dynamik statt.
				gameplayPanel = new GamePlayPanel();
				this.add(gameplayPanel, BorderLayout.CENTER);

			}

		public class GamePlayPanel extends JPanel implements ActionListener
			{
				public Timer tm = new Timer(100, this);

				public GamePlayPanel()
					{
						super();
						tm.start();
						this.setSize(800, 600);
					}

				public void paintComponent(Graphics g)
					{
						g.setColor(Color.ORANGE);
						g.fillRect(0, 0, _adventure._frame.getWidth(), _adventure._frame.getHeight());
					}

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
						++_adventure._gameTime;
						paintComponent(this.getGraphics());
					}
			}
		
		
		
		

	}
