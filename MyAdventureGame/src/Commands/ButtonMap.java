package Commands;

import Backend.AdventureMain;
import GUI.MapPanel;

public class ButtonMap
	{
		AdventureMain _adventure;

	public ButtonMap(AdventureMain adv)
		{
			// TODO Auto-generated constructor stub
			_adventure = adv;
		}

	public void execute()
		{
			// TODO Auto-generated method stub
			_adventure.mapPanel = new MapPanel(_adventure._world);
			_adventure._gamePanel.gameplayPanel.tm.stop();
			_adventure._frame.setContent(_adventure.mapPanel);
			
			
		}

	}
