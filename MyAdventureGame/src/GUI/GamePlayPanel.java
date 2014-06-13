package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.Commands.KeyboardListener;
import Backend.Global.AdventureMain;

// Gameplay Panel - Hier findet die Dynamik statt.
public class GamePlayPanel extends JPanel
	{
		public GamePaneImpl _gPaneImpl;
		private AdventureMain _adventure;

		public GamePlayPanel(AdventureMain adv)
			{
				_gPaneImpl = new GamePaneImpl(this, adv);
				_adventure = adv;

			}

		public void paintComponent(Graphics g)
			{
				_gPaneImpl.draw(g);			

			}

	
	}