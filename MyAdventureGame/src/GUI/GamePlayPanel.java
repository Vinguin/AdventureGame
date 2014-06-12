package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.Global.AdventureMain;

// Gameplay Panel - Hier findet die Dynamik statt.
public class GamePlayPanel extends JPanel implements ActionListener
	{
		private Timer tm = new Timer(200, this);
		private GamePaneImpl _gPaneImpl;
		private AdventureMain _adventure;

		public GamePlayPanel(AdventureMain adv)
			{
				_gPaneImpl = new GamePaneImpl(this, adv);
				_adventure = adv;
				tm.start();

			}

		public void paintComponent(Graphics g)
			{

				_gPaneImpl.draw(g);
			

			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				++_adventure._gameTime;
				paintComponent(this.getGraphics());

			}
	}