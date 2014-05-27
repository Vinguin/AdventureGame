package Backend;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World
	{
		private int weltBreite = 20, weltH�he = 20;
		public Raum[][] welt = new Raum[weltBreite][weltH�he];
		private List<Raum> r�ume = new ArrayList<>();
		private int test, test1;

		public World()
			{
				createWorld();
				System.out.println("Anzahl der R�ume, die Abstand 1 zum Raum haben: " + getAllAvailableRooms().size());
				System.out.println("Anzahl der R�ume in der Welt: " + test);

			}

		public void createWorld()
			{
				r�ume = getNewRoom(20);
				welt[10][10] = new SpawnRaum("Spawnraum");
				Set<Point> potencialRooms = new HashSet<>();
				potencialRooms.addAll(getAvailableRooms(10, 10));
				
				List<Point> verf�gbareR�ume= new ArrayList<>(potencialRooms);

				for (int i = 0; i < r�ume.size(); ++i)
				{
					Raum raum = r�ume.get(i);

					// Alle verf�gbaren R�ume
					int zufallsIndex = getRandomXY(0, verf�gbareR�ume.size() - 1);

					// F�ge Zufallsraum hinzu
					Point koordinate = verf�gbareR�ume.get(zufallsIndex);
					welt[koordinate.x][koordinate.y] = raum;

					// Update PotentialRooms
					potencialRooms.addAll(getAvailableRooms(koordinate.x, koordinate.y));

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
		 * @return
		 */
		public Set<Point> getAllAvailableRooms()
			{
				Set<Point> temp = new HashSet<>();

				// L�uft die das WeltArray ab und findet es einen Raum, f�gt er
				// alle freien Nachbarr�ume in das HashSet hinzu.

				for (int i = 0; i < weltBreite; ++i)
				{
					for (int j = 0; j < weltH�he; ++j)
					{
						++test1;

						if (welt[i][j] instanceof Raum)
						{
							++test;

							// Darunter
							if (welt[i + 1][j] == null && i + 1 <= weltBreite)
								temp.add(new Point(i + 1, j));
							// Dar�ber
							else if (welt[i - 1][j] == null && i - 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Rechts
							else if (welt[i][j + 1] == null && j + 1 <= weltH�he)
								temp.add(new Point(i, j + 1));
							// Links
							else if (welt[i][j - 1] == null && j - 1 <= weltH�he)
								temp.add(new Point(i, j - 1));
						}
					}
				}

				return temp;
			}

		public Set<Point> getAvailableRooms(int i, int j)
			{

				if (welt[i][j] instanceof Raum)
				{
					Set<Point> temp = new HashSet<>();

					// Darunter
					if (welt[i + 1][j] == null && i + 1 <= weltH�he && j <= weltBreite)
						temp.add(new Point(i + 1, j));
					// Dar�ber
					if (welt[i - 1][j] == null && i - 1 <= weltH�he && j <= weltBreite)
						temp.add(new Point(i - 1, j));
					// Rechts
					if (welt[i][j + 1] == null && i <= weltH�he && j + 1 <= weltBreite)
						temp.add(new Point(i, j + 1));
					// Links
					if (welt[i][j - 1] == null && i <= weltH�he && j - 1 <= weltBreite)
						temp.add(new Point(i, j - 1));
					return temp;
				} else
					return null;
			}
	}
