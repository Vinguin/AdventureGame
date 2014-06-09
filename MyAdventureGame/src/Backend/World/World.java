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

		private List<Raum> r�ume = new ArrayList<>();
		public Set<Point> freieRaeume = new HashSet<>();

		public World(AdventureMain adv)
			{
				_adventure = adv;
				createWorld(alpha);

			}

		public void createWorld(Raum[][] welt)
			{
				createContinent(welt, "Spawnraum", getRandomXY(20, 300));
				
				//Erstellt in Welt welt 200 R�ume der Gr��e 20-3000. 
				createXContinent(welt, 20, 3000, 200);
				

				freieRaeume = getAllAvailableRooms();
				drawK�ste(freieRaeume);

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
						alpha = new Raum[weltBreite][weltH�he];
						break;
					case "beta":
						beta = new Raum[weltBreite][weltH�he];
					case "gamma":
						gamma = new Raum[weltBreite][weltH�he];

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

		public void drawK�ste(Set<Point> set)
			{
				List<Point> temp = new ArrayList<>(set);
				for (int i = 0; i < temp.size() - 1; ++i)
				{
					Point point = temp.get(i);
					if (getAvailableRooms(point.x, point.y, true).size() > 0)
						alpha[point.x][point.y] = new K�ste("K�ste" + raumnummer, _adventure);
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
				int y = getRandomXY(0, weltBreite - 1);
				int x = getRandomXY(0, weltH�he - 1);
				kontinent[x][y] = new Raum(raum0, _adventure);
				Set<Point> potencialRooms = new HashSet<>();

				potencialRooms.addAll(getAvailableRooms(x, y, true));
				List<Point> verf�gbareR�ume = new ArrayList<>(potencialRooms);

				for (int i = 0; i < r�ume.size(); ++i)
				{
					Raum raum = r�ume.get(i);
					verf�gbareR�ume = new ArrayList<>(potencialRooms);
					if (verf�gbareR�ume.size() == 0)
					{
						i = r�ume.size();
					} else
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
				{
					createContinent(welt, "Romeo" + raumnummer, getRandomXY(minSize, maxSize));
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
					temp.add(new Raum("Raum" + ++raumnummer, _adventure));

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
					for (int j = 0; j < weltH�he; ++j)
						if (alpha[i][j] instanceof Raum)
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
						}

				return temp;
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
						if (alpha[i + 1][j] instanceof Raum)
							temp.add(new Point(i + 1, j));
					// Dar�ber
					if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
						if (alpha[i - 1][j] instanceof Raum)
							temp.add(new Point(i - 1, j));
					// Rechts
					if (j + 1 <= weltH�he - 1 && j + 1 >= 0)
						if (alpha[i][j + 1] instanceof Raum)
							temp.add(new Point(i, j + 1));
					// Links
					if (j - 1 <= weltH�he - 1 && j - 1 >= 0)
						if (alpha[i][j - 1] instanceof Raum)
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
				{
					for (int j = 0; j < weltH�he; ++j)
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
		 * �berpr�ft ob eine angegebene Koordinate einem Raum geh�rt.
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public boolean istRaum(int x, int y)
			{
				return alpha[x][y] instanceof Raum;
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
	}
