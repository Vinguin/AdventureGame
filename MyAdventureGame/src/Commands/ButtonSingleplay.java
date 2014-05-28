package Commands;

import Backend.AdventureMain;
import GUI.GamePanel;

public class ButtonSingleplay
	{
		private AdventureMain _adventure;

	public ButtonSingleplay(AdventureMain adv)
		{
			_adventure = adv;
		}

	public void execute()
		{
		_adventure._gamePanel = new GamePanel(_adventure);
		_adventure._frame.setContent(_adventure._gamePanel);
		}

	}
