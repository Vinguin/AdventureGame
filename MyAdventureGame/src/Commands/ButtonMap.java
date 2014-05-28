package Commands;

import Backend.AdventureMain;
import GUI.MapFrame;

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
			_adventure.mapFrame = new MapFrame(_adventure._world);
			
			
		}

	}
