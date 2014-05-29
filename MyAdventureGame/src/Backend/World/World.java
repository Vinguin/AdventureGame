package Backend.World;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Backend.Global.AdventureMain;

public class World
	{
		private AdventureMain _adventure;
		private int raumnummer;
		private int weltBreite = 80, weltHöhe = 80;
		public Raum[][] alpha = new Raum[weltBreite][weltHöhe];
		public Raum[][] beta = new Raum[weltBreite][weltHöhe];
		public Raum[][] gamma = new Raum[weltBreite][weltHöhe];

		private List<Raum> räume = new ArrayList<>();
		public Set<Point> freieRaeume = new HashSet<>();

		public World(AdventureMain adv)
			{
				createWorld(alpha, "Spawnraum", getRandomXY(20, 500));

				createWorld(alpha, "Romeo", getRandomXY(20, 300));
				createWorld(alpha, "Romeo1", getRandomXY(20, 300));
				createWorld(alpha, "Romeo2", getRandomXY(20, 300));
				createWorld(alpha, "Romeo3", getRandomXY(20, 300));
				createWorld(alpha, "Romeo4", getRandomXY(20, 300));
				createWorld(alpha, "Romeo5", getRandomXY(20, 300));
				createWorld(alpha, "Romeo6", getRandomXY(20, 300));
				createWorld(alpha, "Romeo7", getRandomXY(20, 300));

				_adventure = adv;
				freieRaeume = getAllAvailableRooms();
				
				drawKüste(freieRaeume);

			}

		public void setBiome(Raum[][] kontinent)
			{
				for (int i = 0; i < weltHöhe; ++i)
					for (int j = 0; j < weltBreite; ++j)
					{

					}

			}
		
		public void drawKüste(Set<Point> set)
			{
				List<Point>  temp = new ArrayList<>(set);
				for(int i = 0; i < temp.size()-1; ++i)
				{
					Point point = temp.get(i);
				if(getAvailableRooms(point.x, point.y, true).size()>0)
					alpha[point.x][point.y] = new Raum("Küste"+raumnummer, _adventure);
				}
			}

		/**
		 * Erschafft einen Kontinent auf Zufallsbasis.
		 * 
		 * @param kontinent
		 * @param raum0
		 * @param kontinentgoesse
		 */
		public void createWorld(Raum[][] kontinent, String raum0, int kontinentgoesse)
			{
				räume = getNewRoom(kontinentgoesse);
				int y = getRandomXY(0, weltBreite - 1);
				int x = getRandomXY(0, weltHöhe - 1);
				kontinent[x][y] = new Raum(raum0, _adventure);
				Set<Point> potencialRooms = new HashSet<>();

				potencialRooms.addAll(getAvailableRooms(x, y, true));
				List<Point> verfügbareRäume = new ArrayList<>(potencialRooms);

				for (int i = 0; i < räume.size(); ++i)
				{
					Raum raum = räume.get(i);
					verfügbareRäume = new ArrayList<>(potencialRooms);
					if (verfügbareRäume.size() == 0)
					{
						i = räume.size();
					} else
					{
						// Alle verfügbaren Räume
						int zufallsIndex = getRandomXY(0, verfügbareRäume.size() - 1);

						// Füge Zufallsraum hinzu
						Point koordinate = verfügbareRäume.get(zufallsIndex);
						kontinent[koordinate.x][koordinate.y] = raum;
						verfügbareRäume.remove(zufallsIndex);

						// Update PotentialRooms
						potencialRooms.addAll(getAvailableRooms(koordinate.x, koordinate.y, true));
						potencialRooms.remove(new Point(koordinate.x, koordinate.y));
					}

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
					temp.add(new Raum("Raum" + ++raumnummer, _adventure));

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
					for (int j = 0; j < weltHöhe; ++j)
						if (alpha[i][j] instanceof Raum)
						{

							// Darunter
							if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
								if (alpha[i + 1][j] == null)
									temp.add(new Point(i + 1, j));
							// Darüber
							if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
								if (alpha[i - 1][j] == null)
									temp.add(new Point(i - 1, j));
							// Rechts
							if (j + 1 <= weltHöhe - 1 && j + 1 >= 0)
								if (alpha[i][j + 1] == null)
									temp.add(new Point(i, j + 1));
							// Links
							if (j - 1 <= weltHöhe - 1 && j - 1 >= 0)
								if (alpha[i][j - 1] == null)
									temp.add(new Point(i, j - 1));
						}

				return temp;
			}

		public Set<Point> getAvailableRooms(int i, int j, boolean isRaum)
			{
				Set<Point> temp = new HashSet<>();

				// Wenn die Koordinate ein Raum gehört.
				if (isRaum)
				{

					// Darunter
					if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
						if (alpha[i + 1][j] == null)
							temp.add(new Point(i + 1, j));
					// Darüber
					if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
						if (alpha[i - 1][j] == null)
							temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltHöhe - 1 && j + 1 >= 0)
						if (alpha[i][j + 1] == null)
							temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltHöhe - 1 && j - 1 >= 0)
						if (alpha[i][j - 1] == null)
							temp.add(new Point(i, j - 1));
					return temp;
				} else if (!isRaum)

				{
					// Darunter
					if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
						if (alpha[i + 1][j] instanceof Raum)
							temp.add(new Point(i + 1, j));
					// Darüber
					if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
						if (alpha[i - 1][j] instanceof Raum)
							temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltHöhe - 1 && j + 1 >= 0)
						if (alpha[i][j + 1] instanceof Raum)
							temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltHöhe - 1 && j - 1 >= 0)
						if (alpha[i][j - 1] instanceof Raum)
							temp.add(new Point(i, j - 1));
					return temp;
				}
				return null;
			}

		public void setWorldSize(int breite, int höhe)
			{
				weltBreite = breite;
				weltHöhe = höhe;
			}

		/**
		 * Gibt die Größe der Welt wieder.
		 * 
		 * @return
		 */
		public Dimension getWorldSize()
			{
				return new Dimension(weltBreite, weltHöhe);
			}

		public boolean isPlayerInWorld()
			{
				for (int i = 0; i < weltBreite; ++i)
				{
					for (int j = 0; j < weltHöhe; ++j)
					{

						if (alpha[i][j] instanceof Raum)
						{

							return alpha[i][j].isPlayerHere();

						}
					}
				}
				return false;

			}

		/**
		 * Überprüft ob eine angegebene Koordinate einem Raum gehört.
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public boolean istRaum(int x, int y)
			{
				return alpha[x][y] instanceof Raum;
			}
	}
