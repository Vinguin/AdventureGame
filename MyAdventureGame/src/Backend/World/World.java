package Backend.World;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Backend.Global.AdventureMain;

public class World {
	private AdventureMain _adventure;
	private int raumnummer;
	private int weltBreite = 500, weltHoehe = 500;
	public Raum[][] alpha = new Raum[weltBreite][weltHoehe];
	public Raum[][] beta = new Raum[weltBreite][weltHoehe];
	public Raum[][] gamma = new Raum[weltBreite][weltHoehe];

	private List<Wiese> raeume = new ArrayList<>();
	public Set<Point> freieRaeume = new HashSet<>();

	public World(AdventureMain adv) {
		_adventure = adv;
		createWorld(alpha);

	}

	public void createWorld(Raum[][] welt) {
		createContinent(welt, "Spawnraum", getRandomXY(1000, 2000));

		// Erstellt in Welt welt 200 R�ume der Gr��e 20-3000.
		createXContinent(welt, 100, 3000, 75);

		drawKueste(alpha);
		drawKueste(alpha);
		drawKueste(alpha);
		drawKueste(alpha);


	}

	public void recreateWorld(String weltbezeichnung) {
		clearWorld(weltbezeichnung);

		switch (weltbezeichnung) {
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

	public void clearWorld(String weltbezeichnung) {
		switch (weltbezeichnung) {
		case "alpha":
			alpha = new Wiese[weltBreite][weltHoehe];
			break;
		case "beta":
			beta = new Wiese[weltBreite][weltHoehe];
		case "gamma":
			gamma = new Wiese[weltBreite][weltHoehe];

		default:
			break;
		}

	}

	public void setBiome(Raum[][] kontinent) {
		for (int i = 0; i < weltHoehe; ++i)
			for (int j = 0; j < weltBreite; ++j) {

			}

	}

	/**
	 * Erschafft einen Kontinent auf Zufallsbasis.
	 * 
	 * @param kontinent
	 * @param raum0
	 * @param kontinentgoesse
	 */
	public void createContinent(Raum[][] kontinent, String raum0, int kontinentgoesse) {
		raeume = getNewRoom(kontinentgoesse);
		int y = getRandomXY(0, weltHoehe - 1);
		int x = getRandomXY(0, weltBreite - 1);
		kontinent[x][y] = new Wiese(raum0, _adventure);
		Set<Point> potencialRooms = new HashSet<>();

		potencialRooms.addAll(getAvailableRooms(x, y, true));
		List<Point> verfuegbareRaeume = new ArrayList<>(potencialRooms);

		for (int i = 0; i < raeume.size(); ++i) {
			Wiese raum = raeume.get(i);
			verfuegbareRaeume = new ArrayList<>(potencialRooms);
			if (verfuegbareRaeume.size() == 0)
				i = raeume.size();
			else {
				// Alle verf�gbaren R�ume
				int zufallsIndex = getRandomXY(0, verfuegbareRaeume.size() - 1);

				// F�ge Zufallsraum hinzu
				Point koordinate = verfuegbareRaeume.get(zufallsIndex);
				kontinent[koordinate.x][koordinate.y] = raum;
				verfuegbareRaeume.remove(zufallsIndex);

				// Update PotentialRooms
				potencialRooms.addAll(getAvailableRooms(koordinate.x, koordinate.y, true));
				potencialRooms.remove(new Point(koordinate.x, koordinate.y));
			}

		}

	}

	public void createXContinent(Raum[][] welt, int minSize, int maxSize, int anzahl) {
		for (int i = 0; i < anzahl; ++i)
			createContinent(welt, "Romeo" + raumnummer, getRandomXY(minSize, maxSize));

		// F�llt den Rest mit "Meer"
		for (int i = 0; i < weltBreite - 1; ++i)
			for (int j = 0; j < weltHoehe - 1; ++j)

				if (welt[i][j] == null)
					welt[i][j] = new Meer("Meer" + raumnummer, _adventure);
	}

	/**
	 * Erzeuge Liste, die mit x R�umen initialisiert wird.
	 * 
	 * @param anzahl
	 * @return
	 */
	public List<Wiese> getNewRoom(int anzahl) {
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
	public int getRandomXY(int a, int b) {
		return (int) (Math.random() * (b + 1 - a) + a);
	}

	public void drawKueste(Raum[][] welt) {

		for (int i = 0; i < weltBreite - 1; ++i)
			for (int j = 0; j < weltHoehe - 1; ++j)

				if (welt[i][j] instanceof Wiese) {
					if (isPotencialKueste(i, j)) {
						welt[i][j] = new Kueste("K�ste" + raumnummer, _adventure);
					}
				} else
					welt[i][j] = new Meer("Meer" + raumnummer, _adventure);

	}

	public boolean isPotencialKueste(int i, int j) {
		List<Point> temp1 = new ArrayList<>(getNextRooms(i, j));

		for (int h = 0; h < temp1.size() - 1; ++h)
			if (alpha[temp1.get(h).x][temp1.get(h).y] instanceof Meer)
				return true;

		return false;
	}

	public Set<Point> getAvailableRooms(int i, int j, boolean isRaum) {
		Set<Point> temp = new HashSet<>();

		// Wenn die Koordinate ein Raum geh�rt.
		if (isRaum) {

			// Darunter
			if (i + 1 <= weltBreite - 1 && i + 1 >= 0)
				if (alpha[i + 1][j] == null)
					temp.add(new Point(i + 1, j));
			// Dar�ber
			if (i - 1 <= weltBreite - 1 && i - 1 >= 0)
				if (alpha[i - 1][j] == null)
					temp.add(new Point(i - 1, j));
			// Rechts
			if (j + 1 <= weltHoehe - 1 && j + 1 >= 0)
				if (alpha[i][j + 1] == null)
					temp.add(new Point(i, j + 1));
			// Links
			if (j - 1 <= weltHoehe - 1 && j - 1 >= 0)
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
			if (j + 1 <= weltHoehe - 1 && j + 1 >= 0)
				if (alpha[i][j + 1] instanceof Wiese)
					temp.add(new Point(i, j + 1));
			// Links
			if (j - 1 <= weltHoehe - 1 && j - 1 >= 0)
				if (alpha[i][j - 1] instanceof Wiese)
					temp.add(new Point(i, j - 1));
			return temp;
		}
		return null;
	}

	public void setWorldSize(int breite, int hoehe) {
		weltBreite = breite;
		weltHoehe = hoehe;
	}

	/**
	 * Gibt die Gr��e der Welt wieder.
	 * 
	 * @return
	 */
	public Dimension getWorldSize() {
		return new Dimension(weltBreite, weltHoehe);
	}

	public boolean isPlayerInWorld() {
		for (int i = 0; i < weltBreite; ++i)
			for (int j = 0; j < weltHoehe; ++j)
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
	public boolean istRaum(int x, int y) {
		return alpha[x][y] instanceof Wiese;
	}

	public Raum[][] getWorld(String string) {
		switch (string.toLowerCase()) {
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

	public Set<Point> getNextRooms(int i, int j) {
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
