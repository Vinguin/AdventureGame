package Backend;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World
	{
		private AdventureMain _adventure;
		private int weltBreite = 20, weltH�he = 20;
		public Raum[][] welt = new Raum[weltBreite][weltH�he];
		private List<Raum> r�ume = new ArrayList<>();
		private int test, test1;
		public Set<Point> freieRaeume = new HashSet<>();

		public World(AdventureMain adv)
			{
				createWorld();
				_adventure = adv;
				System.out.println("Anzahl der R�ume, die Abstand 1 zum Raum haben: " + getAllAvailableRooms().size());
				System.out.println("Anzahl der R�ume in der Welt: " + test);
				freieRaeume = getAllAvailableRooms();

			}

		public void createWorld()
			{
				r�ume = getNewRoom(100);
				welt[10][10] = new SpawnRaum("Spawnraum", _adventure);
				Set<Point> potencialRooms = new HashSet<>();
				potencialRooms.addAll(getAvailableRooms(10, 10));
				List<Point> verf�gbareR�ume = new ArrayList<>(potencialRooms);

				for (int i = 0; i < r�ume.size(); ++i)
				{
					Raum raum = r�ume.get(i);
					verf�gbareR�ume = new ArrayList<>(potencialRooms);

					// Alle verf�gbaren R�ume
					int zufallsIndex = getRandomXY(0, verf�gbareR�ume.size() - 1);

					// F�ge Zufallsraum hinzu
					Point koordinate = verf�gbareR�ume.get(zufallsIndex);
					welt[koordinate.x][koordinate.y] = raum;
					verf�gbareR�ume.remove(zufallsIndex);

					// Update PotentialRooms
					potencialRooms.addAll(getAvailableRooms(koordinate.x, koordinate.y));
					potencialRooms.remove(new Point(koordinate.x, koordinate.y));

				}
				System.out.println(verf�gbareR�ume.size());
				System.out.println(potencialRooms.size());

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
					temp.add(new Raum("Raum" + Integer.toString(i), _adventure));
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

						if (welt[i][j] instanceof Raum)
						{
							++test;

							// Darunter
							if (welt[i + 1][j] == null && i + 1 <= weltBreite)
								temp.add(new Point(i + 1, j));
							// Dar�ber
							if (welt[i - 1][j] == null && i - 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Rechts
							if (welt[i][j + 1] == null && j + 1 <= weltH�he)
								temp.add(new Point(i, j + 1));
							// Links
							if (welt[i][j - 1] == null && j - 1 <= weltH�he)
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
					if (i + 1 <= weltBreite && welt[i + 1][j] == null)
						temp.add(new Point(i + 1, j));
					// Dar�ber
					if (i - 1 <= weltBreite && welt[i - 1][j] == null)
						temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltH�he && welt[i][j + 1] == null)
						temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltH�he && welt[i][j - 1] == null)
						temp.add(new Point(i, j - 1));
					System.out.println(temp.size());
					return temp;
				} else
					return null;
			}

		public void setWorldSize(int breite, int h�he)
			{
				weltBreite = breite;
				weltH�he = h�he;
			}

		public Dimension getWorldSize()
			{
				return new Dimension(weltBreite, weltH�he);
			}
	}
