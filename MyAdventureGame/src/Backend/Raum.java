package Backend;

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
				return  this == _adventure._spieler.getRaum();
			}


		public String getBezeichnung()
			{
				// TODO Auto-generated method stub
				return _raumBezeichnung;
			}
	}
