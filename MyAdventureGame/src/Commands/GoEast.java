package Commands;

import java.awt.Point;

import World.Raum;

import Backend.AdventureMain;

public class GoEast extends SteuerZentrale implements GoManager 
	{

	public GoEast(String string, AdventureMain adv)
		{
			super(string, adv);
		}

	@Override
	public void go()
		{
			Point playerKoordinaten = _adventure._spieler.getRaumLocation();
			int x = playerKoordinaten.x;
			int y = playerKoordinaten.y;
			Raum currentRoom = _adventure._spieler.getRaum();
			
			if(_adventure._world.istRaum(x+1, y))
				_adventure._spieler.setRaumLocation(x+1, y);
							
			
		}

	}
