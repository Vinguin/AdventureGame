package Backend.Commands;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Backend.Global.AdventureMain;
import Backend.World.Raum;

public class SteuerZentrale implements ActionListener
	{
		protected String cmd;
		protected AdventureMain _adventure;

		public SteuerZentrale(String string, AdventureMain adv)
			{
				cmd = string;
				_adventure = adv;

			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub

				switch (cmd)
					{
					case "goNorth":
						new GoNorth(cmd, _adventure).go();
						break;
					case "goSouth":
						new GoSouth(cmd, _adventure).go();
						break;
					case "goEast":
						new GoEast(cmd, _adventure).go();
						break;
					case "goWest":
						new GoWest(cmd, _adventure).go();
						break;

					default:
						break;
					}

			}

		private class GoWest extends SteuerZentrale implements GoManager
			{

				public GoWest(String string, AdventureMain adv)
					{
						super(string, adv);
						// TODO Auto-generated constructor stub
					}

				@Override
				public void go()
					{
						Point playerKoordinaten = _adventure._spieler1.getRaumLocation();
						int x = playerKoordinaten.x;
						int y = playerKoordinaten.y;
						Raum currentRoom = _adventure._spieler1.getRaum();

						if (_adventure._world.istRaum(x - 1, y))
							_adventure._spieler1.setRaumLocation(x - 1, y);

					}

			}

		private class GoSouth extends SteuerZentrale implements GoManager
			{

				public GoSouth(String string, AdventureMain adv)
					{
						super(string, adv);
						// TODO Auto-generated constructor stub
					}

				@Override
				public void go()
					{
						Point playerKoordinaten = _adventure._spieler1.getRaumLocation();
						int x = playerKoordinaten.x;
						int y = playerKoordinaten.y;
						Raum currentRoom = _adventure._spieler1.getRaum();

						if (_adventure._world.istRaum(x, y + 1))
							_adventure._spieler1.setRaumLocation(x, y + 1);

					}

			}

		private class GoNorth extends SteuerZentrale implements GoManager
			{

				public GoNorth(String string, AdventureMain adv)
					{
						super(string, adv);
					}

				@Override
				public void go()
					{
						Point playerKoordinaten = _adventure._spieler1.getRaumLocation();
						int x = playerKoordinaten.x;
						int y = playerKoordinaten.y;
						Raum currentRoom = _adventure._spieler1.getRaum();

						if (_adventure._world.istRaum(x, y - 1))
							_adventure._spieler1.setRaumLocation(x, y - 1);

					}

			}

		private class GoEast extends SteuerZentrale implements GoManager
			{

				public GoEast(String string, AdventureMain adv)
					{
						super(string, adv);
					}

				@Override
				public void go()
					{
						Point playerKoordinaten = _adventure._spieler1.getRaumLocation();
						int x = playerKoordinaten.x;
						int y = playerKoordinaten.y;
						Raum currentRoom = _adventure._spieler1.getRaum();

						if (_adventure._world.istRaum(x + 1, y))
							_adventure._spieler1.setRaumLocation(x + 1, y);

					}

			}

	}
