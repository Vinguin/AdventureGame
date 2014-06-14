package Backend.World;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Backend.Global.AdventureMain;
import Backend.Global.Player;

public class World
	{
		private AdventureMain _adventure;
		private int raumnummer;
		private int weltBreite = 1000, weltHöhe = 1000;
		public Raum[][] alpha = new Raum[weltBreite][weltHöhe];
		public Raum[][] beta = new Raum[weltBreite][weltHöhe];
		public Raum[][] gamma = new Raum[weltBreite][weltHöhe];

		private List<Wiese> räume = new ArrayList<>();
		public Set<Point> freieRaeume = new HashSet<>();

		public World(AdventureMain adv)
			{
				_adventure = adv;
				createWorld(alpha);

			}

		public void createWorld(Raum[][] welt)
			{
				createContinent(welt, "Spawnraum", getRandomXY(1000, 2000));

				// Erstellt in Welt welt 200 Räume der Größe 20-3000.
				createXContinent(welt, 20, 3000, 200);

				drawKüste(alpha);
				drawKüste(alpha);
				drawKüste(alpha);
				drawKüste(alpha);
				drawKüste(alpha);

			}

		public void recreateWorld(String weltbezeichnung)
			{
				clearWorld(weltbezeichnung);

				switch (weltbezeichnung)
					{
					case "alpha":
						createWorld(alpha);
						break;
					case "beta":
						createWorld(beta);
					case "gamma":
						createWorld(gamma);

					default:
						break;
					}
			}

		public void clearWorld(String weltbezeichnung)
			{
				switch (weltbezeichnung)
					{
					case "alpha":
						alpha = new Wiese[weltBreite][weltHöhe];
						break;
					case "beta":
						beta = new Wiese[weltBreite][weltHöhe];
					case "gamma":
						gamma = new Wiese[weltBreite][weltHöhe];

					default:
						break;
					}

			}

		public void setBiome(Raum[][] kontinent)
			{
				for (int i = 0; i < weltHöhe; ++i)
					for (int j = 0; j < weltBreite; ++j)
					{

					}

			}

		/**
		 * Erschafft einen Kontinent auf Zufallsbasis.
		 * 
		 * @param kontinent
		 * @param raum0
		 * @param kontinentgoesse
		 */
		public void createContinent(Raum[][] kontinent, String raum0, int kontinentgoesse)
			{
				räume = getNewRoom(kontinentgoesse);
				int y = getRandomXY(0, weltHöhe - 1);
				int x = getRandomXY(0, weltBreite - 1);
				kontinent[x][y] = new Wiese(raum0, _adventure);
				Set<Point> potencialRooms = new HashSet<>();

				potencialRooms.addAll(getAvailableRooms(x, y, true));
				List<Point> verfügbareRäume = new ArrayList<>(potencialRooms);

				for (int i = 0; i < räume.size(); ++i)
				{
					Wiese raum = räume.get(i);
					verfügbareRäume = new ArrayList<>(potencialRooms);
					if (verfügbareRäume.size() == 0)
						i = räume.size();
					else
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

		public void createXContinent(Raum[][] welt, int minSize, int maxSize, int anzahl)
			{
				for (int i = 0; i < anzahl; ++i)
					createContinent(welt, "Romeo" + raumnummer, getRandomXY(minSize, maxSize));

				// Füllt den Rest mit "Meer"
				for (int i = 0; i < weltBreite - 1; ++i)
					for (int j = 0; j < weltHöhe - 1; ++j)

						if (welt[i][j] == null)
							welt[i][j] = new Meer("Meer" + raumnummer, _adventure);
			}

		/**
		 * Erzeuge Liste, die mit x Räumen initialisiert wird.
		 * 
		 * @param anzahl
		 * @return
		 */
		public List<Wiese> getNewRoom(int anzahl)
			{
				List<Wiese> temp = new ArrayList<Wiese>();
				// Initialisiere Arraylist mit 20 Räume.

				for (int i = 0; i < anzahl; ++i)
					temp.add(new Wiese("Wiese" + ++raumnummer, _adventure));

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

		public void drawKüste(Raum[][] welt)
			{

				for (int i = 0; i < weltBreite - 1; ++i)
					for (int j = 0; j < weltHöhe - 1; ++j)

						if (welt[i][j] instanceof Wiese)
						{
							if (isPotencialKüste(i, j))
							{
								welt[i][j] = new Küste("Küste" + raumnummer, _adventure);
							}
						} else
							welt[i][j] = new Meer("Meer" + raumnummer, _adventure);

			}

		public boolean isPotencialKüste(int i, int j)
			{
				List<Point> temp1 = new ArrayList<>(getNextRooms(i, j));

				for (int h = 0; h < temp1.size() - 1; ++h)
					if (alpha[temp1.get(h).x][temp1.get(h).y] instanceof Meer)
						return true;

				return false;
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
						if (alpha[i + 1][j] instanceof Wiese)
							temp.add(new Point(i + 1, j));
					// Darüber
					if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
						if (alpha[i - 1][j] instanceof Wiese)
							temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltHöhe - 1 && j + 1 >= 0)
						if (alpha[i][j + 1] instanceof Wiese)
							temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltHöhe - 1 && j - 1 >= 0)
						if (alpha[i][j - 1] instanceof Wiese)
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
					for (int j = 0; j < weltHöhe; ++j)
						if (alpha[i][j] instanceof Wiese)
							return alpha[i][j].isPlayerHere();

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
				return alpha[x][y] instanceof Wiese;
			}

		public Raum[][] getWorld(String string)
			{
				switch (string.toLowerCase())
					{
					case "aplha":
						return alpha;
					case "beta":
						return beta;
					case "gamma":
						return gamma;

					default:
						break;
					}
				return null;
			}

		public Set<Point> getNextRooms(int i, int j)
			{
				Set<Point> temp = new HashSet<>();
				if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
					temp.add(new Point(i + 1, j));

				if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
					temp.add(new Point(i - 1, j));

				if (j + 1 <= weltBreite - 1 && j + 1 >= 0)
					temp.add(new Point(i + 1, j + 1));

				if (j - 1 <= weltBreite - 1 && j - 1 >= 0)
					temp.add(new Point(i + 1, j - 1));

				return temp;

			}
	}
