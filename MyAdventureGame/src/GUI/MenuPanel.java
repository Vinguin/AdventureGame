package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.Commands.iCommandListener;
import Backend.Global.AdventureMain;

public class MenuPanel extends JPanel implements ActionListener
	{
		private AdventureMain _adventure;
		private Image img;
		private Timer timer = new Timer(200, this);
		

		public MenuPanel(AdventureMain adv)
			{
				_adventure = adv;
				img = Toolkit.getDefaultToolkit().getImage("res/Wallpaper/menuscreen.jpg");

				this.setLayout(new GridBagLayout());
				
				ButtonPanel buttonPanel = new ButtonPanel(adv);
				
				JButton singlePlay = new JButton("Play");
				JButton quit = new JButton("Quit");

				singlePlay.addActionListener(new iCommandListener("singleplay", adv));
				quit.addActionListener(new iCommandListener("quit", adv));
				
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 0;
				c.insets = new Insets(10, 10, 10, 10);
				this.add(singlePlay, c);
				c.gridx = 0;
				c.gridy = 1;						
				this.add(quit, c);
		
				timer.start();
				
			
				

			}

		public void paintComponent(Graphics g)
			{
				g.drawImage(img, 0, 0, getWidth(), getHeight(), null);				
			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				repaint();
			}
		
		
		
		public class ButtonPanel extends JPanel
		{
				public ButtonPanel(AdventureMain adv)
					{
						
						this.setLayout(new GridBagLayout());
						this.setVisible(true);
						
						JButton singlePlay = new JButton("Play");
						JButton quit = new JButton("Quit");

						singlePlay.addActionListener(new iCommandListener("singleplay", adv));
						quit.addActionListener(new iCommandListener("quit", adv));

//						this.setPreferredSize(new Dimension(150, 150));
						GridBagConstraints c = new GridBagConstraints();
						c.gridx = 0;
						c.gridy = 0;
						c.insets = new Insets(10, 10, 10, 10);
						this.add(singlePlay, c);
						c.gridx = 0;
						c.gridy = 1;						
						this.add(quit, c);
						
					
						
						
					}
		}
	}
