package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.AdventureMain;
import Backend.World;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener
	{
		private AdventureMain _adventure;
		private Timer tm = new Timer(100, this);

		public GamePanel(AdventureMain adv)
			{
				super();
				_adventure = adv;
				this.setVisible(true);
				this.setSize(adv._frame.getWidth(), adv._frame.getHeight());
				adv._world = new World();
				tm.start();

			}

		public void paintComponent(Graphics g)
			{
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, _adventure._frame.getWidth(), _adventure._frame.getHeight());

			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				++_adventure._gameTime;
				paintComponent(getGraphics());
			}
	}
