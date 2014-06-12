package Backend.World;

import Backend.Global.AdventureMain;

public class Raum
	{
		private String _raumBezeichnung;
		private int borderX, borderY;
		private boolean haveCurrentPlayer = false;
		private AdventureMain _adventure;

		public Raum(String raumtyp, AdventureMain adv)
			{
				_raumBezeichnung = raumtyp;
				_adventure = adv;
			}

		public boolean isPlayerHere()
			{
				return haveCurrentPlayer;
			}

		public String getBezeichnung()
			{
				return _raumBezeichnung;
			}

		public void isPlayerHere(boolean b)
			{
				haveCurrentPlayer = b;
			}

		
		public boolean getRoomInDirection(String direction, int currentX, int currentY)
			{
				switch (direction.toLowerCase())
					{
					case "north":
						return _adventure._world.istRaum(currentX - 1, currentY);
					case "south":
						return _adventure._world.istRaum(currentX + 1, currentY);
					case "east":
						return _adventure._world.istRaum(currentX, currentY + 1);
					case "west":
						return _adventure._world.istRaum(currentX, currentY - 1);
						
					default:
						return false;
					}
			}
	}
