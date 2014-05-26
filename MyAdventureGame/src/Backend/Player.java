package Backend;

import java.awt.Point;

public class Player
	{
		private String spielername;
		private int currentX, currentY;

		public Player(String bezeichnung)
			{
				spielername = bezeichnung;
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
	}
