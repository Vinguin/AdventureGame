package Backend;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World
	{
		private int weltBreite = 20, weltHöhe = 20;
		private Raum[][] welt = new Raum[weltBreite][weltHöhe];
		private List<Raum> räume = new ArrayList<>();
		private int test;

		public World()
			{
				createWorld();
				System.out.println(test);
			}

		public void createWorld()
			{
				räume = getNewRoom(20);
				welt[10][10] = new SpawnRaum("Spawnraum");

				for (int i = 0; i < räume.size(); ++i)
				{
					Raum raum = räume.get(i);

					// Alle verfügbaren Räume
					List<Point> temp1 = new ArrayList<>(getAvailableRooms());
					int zufallsIndex = getRandomXY(0, temp1.size() - 1);

					// Füge Zufalls raum hinzu
					Point koordinate = temp1.get(zufallsIndex);
					welt[koordinate.x][koordinate.y] = raum;
					
					System.out.println(temp1.size());
					
				}
			}

		/**
		 * Erzeuge Liste, die mit x Räumen initialisiert wird.
		 * 
		 * @param anzahl
		 * @return
		 */
		public List<Raum> getNewRoom(int anzahl)
			{
				List<Raum> temp = new ArrayList<Raum>();
				// Initialisiere Arraylist mit 20 Räume.

				for (int i = 0; i < anzahl; ++i)
				{
					temp.add(new Raum("Raum" + Integer.toString(i)));
				}

				return temp;

			}

		/**
		 * Gibt eine zufällige Zeit zwischen a und b wieder.
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
		 * Gibt alle Räume aus, dessen Distanz zu einem Raum 1 betragen.
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public Set<Point> getAvailableRooms()
			{
				Set<Point> temp = new HashSet<>();

				// Läuft die das WeltArray ab und findet es einen Raum, fügt er
				// alle freien Nachbarräume in das HashSet hinzu.

				for (int i = 0; i < weltHöhe; ++i)
				{
					for (int j = 0; j < weltBreite; ++j)
					{
						if (welt[i][j] instanceof Raum)
						{
							
							// Darunter
							if (welt[i + 1][j] == null && i + 1 <= weltHöhe && j <= weltBreite)
								temp.add(new Point(i + 1, j));
							// Darüber
							else if (welt[i - 1][j] == null && i - 1 <= weltHöhe && j <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Rechts
							else if (welt[i][j + 1] == null && i <= weltHöhe && j + 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Links
							else if (welt[i][j - 1] == null && i <= weltHöhe && j - 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
						} else
							++test;
					}
				}

				return temp;
			}
	}
