package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Backend.Global.AdventureMain;
import Backend.Global.MapImpl;
import Backend.World.Küste;
import Backend.World.Meer;
import Backend.World.Wiese;

public class GamePaneImpl
	{

		private AdventureMain _adventure;
		private GamePlayPanel _gPanel;
		private List<Point> rasta;
		private int x_klein, y_klein;
		private MapImpl _implMap;

		public GamePaneImpl(GamePlayPanel gamePlayPanel, AdventureMain adv)
			{
				_gPanel = gamePlayPanel;
				_adventure = adv;
				_implMap = new MapImpl(adv);

				rasta = getRasta();

			}

		/**
		 * Liefert eine Liste mit den jeweiligen Koordinaten eines Feldes auf
		 * dem Panel.
		 * 
		 * @return
		 */
		public List<Point> getRasta()
			{
				int x_all = _gPanel.getWidth();
				int y_all = _gPanel.getHeight();
				List<Point> rasta = new ArrayList<>();

				x_klein = (int) (x_all * 0.066666);
				y_klein = (int) (y_all * 0.066666);

				// Spaltenweise vom Links nach Rechts.
				for (int i = 0; i < 15; ++i)
					for (int j = 0; j < 15; ++j)
					{
						int x = i * x_klein;
						int y = j * y_klein;

						rasta.add(new Point(x, y));
					}

				return rasta;
			}

		/**
		 * Gibt die größe eines Feldes wieder.
		 * 
		 * @return
		 */
		public Dimension getFieldSize()
			{

				return new Dimension(x_klein, y_klein);
			}

		public Color getRandomColor()
			{
				List<Color> colorlist = new ArrayList<>();

				colorlist.add(Color.red);
				colorlist.add(Color.blue);
				colorlist.add(Color.yellow);
				colorlist.add(Color.cyan);
				colorlist.add(Color.gray);
				colorlist.add(Color.magenta);
				colorlist.add(Color.orange);
				colorlist.add(Color.green);

				int random_index = getRandomXY(0, colorlist.size() - 1);

				return colorlist.get(random_index);
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

		public void draw(Graphics g)
			{
				rasta = getRasta();
				List<Point> rastaRoom = getDrawRooms();
				int width = (int) getFieldSize().getWidth();
				int height = (int) getFieldSize().getHeight();
				
				g.setColor(new Color(0, 178, 238));
				g.fillRect(0, 0, _gPanel.getWidth(), _gPanel.getHeight());

				for (int i = 0; i < rasta.size(); ++i)
				{
					// Color color = getRandomColor();
					// g.setColor(color);

					// Die Koordinaten für das Rasta auf dem Panel.
					int x = rasta.get(i).x;
					int y = rasta.get(i).y;

					// Koordinaten für den jeweiligen Raum
					int raumkoordX = rastaRoom.get(i).x;
					int raumkoordY = rastaRoom.get(i).y;

					if (_adventure._world.alpha[raumkoordX][raumkoordY] instanceof Meer)
						_implMap.drawRaum("Meer", g, x, y, width, height);
					if (_adventure._world.alpha[raumkoordX][raumkoordY] instanceof Wiese)
						_implMap.drawRaum("Wiese", g, x, y, width, height);
					if (_adventure._world.alpha[raumkoordX][raumkoordY].isPlayerHere())
						_implMap.drawRaum("Spieler", g, x, y, width, height);
					if (_adventure._world.alpha[raumkoordX][raumkoordY] instanceof Küste)
						_implMap.drawRaum("border", g, x, y, width, height);
					if (_adventure._world.alpha[raumkoordX][raumkoordY].isPlayerHere())
						_implMap.drawRaum("Spieler", g, x, y, width, height);

				}

			}

		public List<Point> getDrawRooms()
			{
				int x = _adventure._spieler1.getRaumLocation().x;
				int y = _adventure._spieler1.getRaumLocation().y;

				List<Point> temp = new ArrayList<>();

				if (x > 7 && y > 7 && x < 993 && y < 993)
				{
					Point obenlinks = new Point(x - 7, y - 7);

					for (int i = 0; i < 15; ++i)
						for (int j = 0; j < 15; ++j)
							temp.add(new Point(x - 7 + i, y - 7 + j));

				}

				return temp;
			}

		public void updateGUI()
			{
				_adventure._gamePanel.repaint();
			}

	}
