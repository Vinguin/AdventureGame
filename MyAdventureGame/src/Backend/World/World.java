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
		private int weltBreite = 1000, weltH�he = 1000;
		public Raum[][] alpha = new Raum[weltBreite][weltH�he];
		public Raum[][] beta = new Raum[weltBreite][weltH�he];
		public Raum[][] gamma = new Raum[weltBreite][weltH�he];

		private List<Wiese> r�ume = new ArrayList<>();
		public Set<Point> freieRaeume = new HashSet<>();

		public World(AdventureMain adv)
			{
				_adventure = adv;
				createWorld(alpha);

			}

		public void createWorld(Raum[][] welt)
			{
				createContinent(welt, "Spawnraum", getRandomXY(1000, 2000));

				// Erstellt in Welt welt 200 R�ume der Gr��e 20-3000.
				createXContinent(welt, 20, 3000, 200);

				drawK�ste(alpha);
				drawK�ste(alpha);
				drawK�ste(alpha);
				drawK�ste(alpha);
				drawK�ste(alpha);

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
						alpha = new Wiese[weltBreite][weltH�he];
						break;
					case "beta":
						beta = new Wiese[weltBreite][weltH�he];
					case "gamma":
						gamma = new Wiese[weltBreite][weltH�he];

					default:
						break;
					}

			}

		public void setBiome(Raum[][] kontinent)
			{
				for (int i = 0; i < weltH�he; ++i)
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
				r�ume = getNewRoom(kontinentgoesse);
				int y = getRandomXY(0, weltH�he - 1);
				int x = getRandomXY(0, weltBreite - 1);
				kontinent[x][y] = new Wiese(raum0, _adventure);
				Set<Point> potencialRooms = new HashSet<>();

				potencialRooms.addAll(getAvailableRooms(x, y, true));
				List<Point> verf�gbareR�ume = new ArrayList<>(potencialRooms);

				for (int i = 0; i < r�ume.size(); ++i)
				{
					Wiese raum = r�ume.get(i);
					verf�gbareR�ume = new ArrayList<>(potencialRooms);
					if (verf�gbareR�ume.size() == 0)
						i = r�ume.size();
					else
					{
						// Alle verf�gbaren R�ume
						int zufallsIndex = getRandomXY(0, verf�gbareR�ume.size() - 1);

						// F�ge Zufallsraum hinzu
						Point koordinate = verf�gbareR�ume.get(zufallsIndex);
						kontinent[koordinate.x][koordinate.y] = raum;
						verf�gbareR�ume.remove(zufallsIndex);

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

				// F�llt den Rest mit "Meer"
				for (int i = 0; i < weltBreite - 1; ++i)
					for (int j = 0; j < weltH�he - 1; ++j)

						if (welt[i][j] == null)
							welt[i][j] = new Meer("Meer" + raumnummer, _adventure);
			}

		/**
		 * Erzeuge Liste, die mit x R�umen initialisiert wird.
		 * 
		 * @param anzahl
		 * @return
		 */
		public List<Wiese> getNewRoom(int anzahl)
			{
				List<Wiese> temp = new ArrayList<Wiese>();
				// Initialisiere Arraylist mit 20 R�ume.

				for (int i = 0; i < anzahl; ++i)
					temp.add(new Wiese("Wiese" + ++raumnummer, _adventure));

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

		public void drawK�ste(Raum[][] welt)
			{

				for (int i = 0; i < weltBreite - 1; ++i)
					for (int j = 0; j < weltH�he - 1; ++j)

						if (welt[i][j] instanceof Wiese)
						{
							if (isPotencialK�ste(i, j))
							{
								welt[i][j] = new K�ste("K�ste" + raumnummer, _adventure);
							}
						} else
							welt[i][j] = new Meer("Meer" + raumnummer, _adventure);

			}

		public boolean isPotencialK�ste(int i, int j)
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

				// Wenn die Koordinate ein Raum geh�rt.
				if (isRaum)
				{

					// Darunter
					if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
						if (alpha[i + 1][j] == null)
							temp.add(new Point(i + 1, j));
					// Dar�ber
					if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
						if (alpha[i - 1][j] == null)
							temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltH�he - 1 && j + 1 >= 0)
						if (alpha[i][j + 1] == null)
							temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltH�he - 1 && j - 1 >= 0)
						if (alpha[i][j - 1] == null)
							temp.add(new Point(i, j - 1));
					return temp;
				} else if (!isRaum)

				{
					// Darunter
					if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
						if (alpha[i + 1][j] instanceof Wiese)
							temp.add(new Point(i + 1, j));
					// Dar�ber
					if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
						if (alpha[i - 1][j] instanceof Wiese)
							temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltH�he - 1 && j + 1 >= 0)
						if (alpha[i][j + 1] instanceof Wiese)
							temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltH�he - 1 && j - 1 >= 0)
						if (alpha[i][j - 1] instanceof Wiese)
							temp.add(new Point(i, j - 1));
					return temp;
				}
				return null;
			}

		public void setWorldSize(int breite, int h�he)
			{
				weltBreite = breite;
				weltH�he = h�he;
			}

		/**
		 * Gibt die Gr��e der Welt wieder.
		 * 
		 * @return
		 */
		public Dimension getWorldSize()
			{
				return new Dimension(weltBreite, weltH�he);
			}

		public boolean isPlayerInWorld()
			{
				for (int i = 0; i < weltBreite; ++i)
					for (int j = 0; j < weltH�he; ++j)
						if (alpha[i][j] instanceof Wiese)
							return alpha[i][j].isPlayerHere();

				return false;

			}

		/**
		 * �berpr�ft ob eine angegebene Koordinate einem Raum geh�rt.
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
