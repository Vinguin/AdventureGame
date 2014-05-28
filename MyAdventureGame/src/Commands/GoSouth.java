package Commands;

import java.awt.Point;

import World.Raum;

import Backend.AdventureMain;

public class GoSouth extends SteuerZentrale implements GoManager
	{

		public GoSouth(String string, AdventureMain adv)
			{
				super(string, adv);
				// TODO Auto-generated constructor stub
			}

		@Override
		public void go()
			{
				Point playerKoordinaten = _adventure._spieler.getRaumLocation();
				int x = playerKoordinaten.x;
				int y = playerKoordinaten.y;
				Raum currentRoom = _adventure._spieler.getRaum();

				if (_adventure._world.istRaum(x, y + 1))
					_adventure._spieler.setRaumLocation(x, y + 1);

			}

	}
