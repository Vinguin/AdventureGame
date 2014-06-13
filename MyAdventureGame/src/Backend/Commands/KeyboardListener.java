package Backend.Commands;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GUI.GamePlayPanel;

public class KeyboardListener extends KeyAdapter implements KeyEventDispatcher
	{
		public KeyboardListener()
			{
			}

		@Override
		public boolean dispatchKeyEvent(KeyEvent arg0)
			{
				System.out.println("check");

				return false;
			}

	}
