package Backend;

import java.awt.Dimension;
import java.awt.Point;

public class Player
	{
		private String spielername;
		private int currentX, currentY;
		private Raum currentRaum;
		private AdventureMain _adventure;

		public Player(String bezeichnung, String raum, AdventureMain adv)
			{
				spielername = bezeichnung;
				currentX = 300;
				currentY = 400;
				_adventure = adv;
				setRaumByName(raum);

			}

		/**
		 * Legt die Position des Spielers fest.
		 * 
		 * @param x
		 * @param y
		 */
		public void setPlayerPosition(int x, int y)
			{
				currentX = x;
				currentY = y;
			}

		/**
		 * Gibt die Spielerposi als Typ- Point zurück
		 * 
		 * @return
		 */
		public Point getPlayerPosi()
			{
				return new Point(currentX, currentY);
			}

		/**
		 * Wechselt den Raum des Spielers
		 * 
		 * @param neuRaum
		 */
		public void setRaumByName(String raum_name)
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
						if (_adventure._world.welt[i][j] instanceof Raum)
						{
							String raumBez = _adventure._world.welt[i][j].getBezeichnung();

							// Und der Name dem Konstruktur- Parameter des
							// Spielers entspricht.
							if (raumBez.equals(raum_name))
							{
								currentRaum = _adventure._world.welt[i][j];
								_adventure._world.welt[i][j].setPlayerHere(true);
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
				if (_adventure._world.istRaum(x,y))
				{
					currentRaum.setPlayerHere(false);
					currentRaum = _adventure._world.welt[x][y];
					currentRaum.setPlayerHere(true);
				}
			}
	}
