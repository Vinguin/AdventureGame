package Backend;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World
	{
		private int weltBreite = 20, weltH�he = 20;
		private Raum[][] welt = new Raum[weltBreite][weltH�he];
		private List<Raum> r�ume = new ArrayList<>();
		private int test;

		public World()
			{
				createWorld();
				System.out.println(test);
			}

		public void createWorld()
			{
				r�ume = getNewRoom(20);
				welt[10][10] = new SpawnRaum("Spawnraum");

				for (int i = 0; i < r�ume.size(); ++i)
				{
					Raum raum = r�ume.get(i);

					// Alle verf�gbaren R�ume
					List<Point> temp1 = new ArrayList<>(getAvailableRooms());
					int zufallsIndex = getRandomXY(0, temp1.size() - 1);

					// F�ge Zufalls raum hinzu
					Point koordinate = temp1.get(zufallsIndex);
					welt[koordinate.x][koordinate.y] = raum;
					
					System.out.println(temp1.size());
					
				}
			}

		/**
		 * Erzeuge Liste, die mit x R�umen initialisiert wird.
		 * 
		 * @param anzahl
		 * @return
		 */
		public List<Raum> getNewRoom(int anzahl)
			{
				List<Raum> temp = new ArrayList<Raum>();
				// Initialisiere Arraylist mit 20 R�ume.

				for (int i = 0; i < anzahl; ++i)
				{
					temp.add(new Raum("Raum" + Integer.toString(i)));
				}

				return temp;

			}

		/**
		 * Gibt eine zuf�llige Zeit zwischen a und b wieder.
		 * 
		 * @param a
		 * @param b
		 * @return
		 */
		public int getRandomXY(int a, int b)
			{
				return (int) (Math.random() * (b + 1 - a) + a);
			}

		/**
		 * Gibt alle R�ume aus, dessen Distanz zu einem Raum 1 betragen.
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public Set<Point> getAvailableRooms()
			{
				Set<Point> temp = new HashSet<>();

				// L�uft die das WeltArray ab und findet es einen Raum, f�gt er
				// alle freien Nachbarr�ume in das HashSet hinzu.

				for (int i = 0; i < weltH�he; ++i)
				{
					for (int j = 0; j < weltBreite; ++j)
					{
						if (welt[i][j] instanceof Raum)
						{
							
							// Darunter
							if (welt[i + 1][j] == null && i + 1 <= weltH�he && j <= weltBreite)
								temp.add(new Point(i + 1, j));
							// Dar�ber
							else if (welt[i - 1][j] == null && i - 1 <= weltH�he && j <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Rechts
							else if (welt[i][j + 1] == null && i <= weltH�he && j + 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Links
							else if (welt[i][j - 1] == null && i <= weltH�he && j - 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
						} else
							++test;
					}
				}

				return temp;
			}
	}
