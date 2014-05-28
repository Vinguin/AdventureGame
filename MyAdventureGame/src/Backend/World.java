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
		private int weltBreite = 20, weltHöhe = 20;
		public Raum[][] welt = new Raum[weltBreite][weltHöhe];
		private List<Raum> räume = new ArrayList<>();
		private int test, test1;
		public Set<Point> freieRaeume = new HashSet<>();

		public World(AdventureMain adv)
			{
				createWorld();
				_adventure = adv;
				System.out.println("Anzahl der Räume, die Abstand 1 zum Raum haben: " + getAllAvailableRooms().size());
				System.out.println("Anzahl der Räume in der Welt: " + test);
				freieRaeume = getAllAvailableRooms();

			}

		public void createWorld()
			{
				räume = getNewRoom(100);
				welt[10][10] = new SpawnRaum("Spawnraum", _adventure);
				Set<Point> potencialRooms = new HashSet<>();
				potencialRooms.addAll(getAvailableRooms(10, 10));
				List<Point> verfügbareRäume = new ArrayList<>(potencialRooms);

				for (int i = 0; i < räume.size(); ++i)
				{
					Raum raum = räume.get(i);
					verfügbareRäume = new ArrayList<>(potencialRooms);

					// Alle verfügbaren Räume
					int zufallsIndex = getRandomXY(0, verfügbareRäume.size() - 1);

					// Füge Zufallsraum hinzu
					Point koordinate = verfügbareRäume.get(zufallsIndex);
					welt[koordinate.x][koordinate.y] = raum;
					verfügbareRäume.remove(zufallsIndex);

					// Update PotentialRooms
					potencialRooms.addAll(getAvailableRooms(koordinate.x, koordinate.y));
					potencialRooms.remove(new Point(koordinate.x, koordinate.y));

				}
				System.out.println(verfügbareRäume.size());
				System.out.println(potencialRooms.size());

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
					temp.add(new Raum("Raum" + Integer.toString(i), _adventure));
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
		 * @return
		 */
		public Set<Point> getAllAvailableRooms()
			{
				Set<Point> temp = new HashSet<>();

				// Läuft die das WeltArray ab und findet es einen Raum, fügt er
				// alle freien Nachbarräume in das HashSet hinzu.

				for (int i = 0; i < weltBreite; ++i)
				{
					for (int j = 0; j < weltHöhe; ++j)
					{

						if (welt[i][j] instanceof Raum)
						{
							++test;

							// Darunter
							if (welt[i + 1][j] == null && i + 1 <= weltBreite)
								temp.add(new Point(i + 1, j));
							// Darüber
							if (welt[i - 1][j] == null && i - 1 <= weltBreite)
								temp.add(new Point(i - 1, j));
							// Rechts
							if (welt[i][j + 1] == null && j + 1 <= weltHöhe)
								temp.add(new Point(i, j + 1));
							// Links
							if (welt[i][j - 1] == null && j - 1 <= weltHöhe)
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
					// Darüber
					if (i - 1 <= weltBreite && welt[i - 1][j] == null)
						temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltHöhe && welt[i][j + 1] == null)
						temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltHöhe && welt[i][j - 1] == null)
						temp.add(new Point(i, j - 1));
					System.out.println(temp.size());
					return temp;
				} else
					return null;
			}

		public void setWorldSize(int breite, int höhe)
			{
				weltBreite = breite;
				weltHöhe = höhe;
			}

		public Dimension getWorldSize()
			{
				return new Dimension(weltBreite, weltHöhe);
			}
	}
