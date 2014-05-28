package Backend;

public class Raum
	{
		private String _raumBezeichnung;
		private int borderX, borderY;
		private boolean haveCurrentPlayer = false;
		private AdventureMain _adventure;
		private Raum raum;

		public Raum(String raumtyp, AdventureMain adv)
			{
				_raumBezeichnung = raumtyp;
				_adventure = adv;
				raum = this;
			}
		
		
		public boolean isPlayerHere()
			{
				return haveCurrentPlayer;
			}


		public String getBezeichnung()
			{
				// TODO Auto-generated method stub
				return _raumBezeichnung;
			}


		public void setPlayerHere(boolean b)
			{
				// TODO Auto-generated method stub
				haveCurrentPlayer = b;
			}
	}
