package Commands;

import Backend.AdventureMain;
import GUI.GamePanel;

public class ButtonSingleplay
	{
		private AdventureMain _adventure;

	public ButtonSingleplay(AdventureMain adv)
		{
			// TODO Auto-generated constructor stub
			_adventure = adv;
		}

	public void execute()
		{
			// TODO Auto-generated method stub
		_adventure._gamePanel = new GamePanel(_adventure);
		_adventure._frame.setContent(_adventure._gamePanel);
		}

	}
