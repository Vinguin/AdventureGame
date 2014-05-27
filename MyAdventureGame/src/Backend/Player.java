package Backend;

import java.awt.Point;

public class Player
	{
		private String spielername;
		private int currentX, currentY;
		private Raum currentRaum;

		public Player(String bezeichnung)
			{
				spielername = bezeichnung;
				currentX = 300;
				currentY = 400;
				currentRaum = null;
			}

		
		/**
		 * Legt die Position des Spielers fest.
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
		 * @return
		 */
		public Point getPlayerPosi()
			{
				return new Point(currentX, currentY);
			}
		
		public void setNewRaum(Raum neuRaum)
			{
				currentRaum = neuRaum;
			}
		
		
		
		/**
		 * Gibt den Aktuellen Raum des Spielers wieder.
		 * @return
		 */
		public Raum getCurrentRaum()
			{
				return currentRaum;
			}
	}
