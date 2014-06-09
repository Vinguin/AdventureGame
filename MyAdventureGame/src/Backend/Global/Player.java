package Backend.Global;

import java.awt.Dimension;
import java.awt.Point;

import Backend.World.Raum;

public class Player
	{
		private String spielername;
		private int raumX, raumY;
		private Raum currentRaum;
		private AdventureMain _adventure;

		public Player(String bezeichnung, AdventureMain adv)
			{
				spielername = bezeichnung;
				_adventure = adv;

			}

		public Player(String bezeichnung, String raumname, AdventureMain adv)
			{
				spielername = bezeichnung;
				_adventure = adv;
				setRaumByName(adv._world.getWorld("aplha"), "Spawnraum");
			}

		/**
		 * Legt die Position des Spielers fest.
		 * 
		 * @param x
		 * @param y
		 */
		public void setRaumLocation(int x, int y)
			{
				// Spieler ist nicht mehr registriert im alten Raum

				currentRaum.isPlayerHere(false);

				// Packe Spieler in den neuen Raum.
				raumX = x;
				raumY = y;
				currentRaum = _adventure._world.alpha[x][y];
				currentRaum.isPlayerHere(true);

			}

		/**
		 * Gibt die Spielerposi als Typ- Point zurück
		 * 
		 * @return
		 */
		public Point getRaumLocation()
			{
				return new Point(raumX, raumY);
			}

		/**
		 * Wechselt den Raum des Spielers
		 * 
		 * @param neuRaum
		 */
		public void setRaumByName(Raum[][] welt, String raum_name)
			{
				Dimension worldsize = _adventure._world.getWorldSize();
				boolean raumEnthalten = false;
				int worldwidth = worldsize.width;
				int worldheight = worldsize.height;

				// Geh durch alle Felder der Welt
				for (int i = 0; i < worldwidth; ++i)
				{
					for (int j = 0; j < worldheight; ++j)
					{

						// Wenn ein Raum gefunden wird.
						if (welt[i][j] instanceof Raum)
						{
							String raumBez = welt[i][j].getBezeichnung();

							// Und der Name dem Konstruktur- Parameter des
							// Spielers entspricht.
							if (raumBez.equals(raum_name))
							{
								currentRaum = welt[i][j];
								welt[i][j].isPlayerHere(true);
								setRaumLocation(i, j);

							}

							raumEnthalten = true;
						}
					}
				}

				if (!raumEnthalten)
					throw new IllegalArgumentException();
			}

		/**
		 * Gibt Spielerbezeichnung wieder
		 * 
		 * @return
		 */
		public String getName()
			{
				return spielername;
			}

		/**
		 * Gibt den Aktuellen Raum des Spielers wieder.
		 * 
		 * @return
		 */
		public Raum getRaum()
			{
				return currentRaum;
			}

		public void putInRoom(int x, int y)
			{
				if (_adventure._world.istRaum(x, y))
				{
					currentRaum.isPlayerHere(false);
					currentRaum = _adventure._world.alpha[x][y];
					currentRaum.isPlayerHere(true);
				}
			}

		public boolean isOnWorld(Raum[][] welt)
			{
				for (int i = 0; i < _adventure._world.getWorldSize().getHeight(); ++i)
					for (int j = 0; j < _adventure._world.getWorldSize().getWidth(); ++j)
						if (welt[i][j] instanceof Raum)
							if (welt[i][j].isPlayerHere())
								return true;

				return false;
			}
	}
