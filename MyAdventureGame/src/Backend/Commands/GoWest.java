package Backend.Commands;

import java.awt.Point;


import Backend.Global.AdventureMain;
import Backend.World.Raum;

public class GoWest extends SteuerZentrale implements GoManager
	{

		public GoWest(String string, AdventureMain adv)
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

				if (_adventure._world.istRaum(x - 1, y))
					_adventure._spieler.setRaumLocation(x - 1, y);

			}

	}
