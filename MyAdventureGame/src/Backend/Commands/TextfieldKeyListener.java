package Backend.Commands;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Backend.Global.AdventureMain;

public class TextfieldKeyListener implements KeyListener
	{
		private AdventureMain _adventure;

		public TextfieldKeyListener(AdventureMain adv)
			{
				_adventure = adv;
			}

		@Override
		public void keyPressed(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == 'p')
				{

				}

			}

	}