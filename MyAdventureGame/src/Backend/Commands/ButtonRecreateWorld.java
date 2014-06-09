package Backend.Commands;

import Backend.Global.AdventureMain;

public class ButtonRecreateWorld
	{	

	private AdventureMain _adventure;

	public ButtonRecreateWorld(AdventureMain adv)
		{
		 _adventure = adv;
		}

	public void execute()
		{
			// TODO Auto-generated method stub
			_adventure._world.recreateWorld("alpha");
			_adventure._spieler1.setRaumByName(_adventure._world.alpha, "Spawnraum");
		}

	}
