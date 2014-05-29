package Backend.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTextField;

import Backend.World.Raum;

public class MapImpl
	{
		AdventureMain _adventure;
		int blockgroesse = 7;

		public MapImpl(AdventureMain adv)
			{
				_adventure = adv;
			}

		/**
		 * Werkzeug. Zeichnet einen Raum aus Raumtyp, getGraphics und Loc x,y.
		 * 
		 * @param raumtyp
		 * @param g
		 * @param x
		 * @param y
		 */
		public void drawRaum(String raumtyp, Graphics g, int x, int y)
			{

				if (raumtyp.toLowerCase().equals("wasser"))
				{
					g.setColor(Color.BLACK);
					g.drawRect(x, y, blockgroesse, blockgroesse);

					g.setColor(new Color(0, 178, 238));
					g.fillRect(x, y, blockgroesse, blockgroesse);

				} else if (raumtyp.toLowerCase().equals("!leer"))
				{
					g.setColor(Color.black);
					g.drawRect(x, y, blockgroesse, blockgroesse);

					g.setColor(Color.GREEN);
					g.fillRect(x, y, blockgroesse, blockgroesse);
				} else if (raumtyp.toLowerCase().equals("frei"))
				{
					g.setColor(Color.GREEN);
					g.fillRect(x, y, blockgroesse, blockgroesse);
				} else if (raumtyp.toLowerCase().equals("spieler"))
				{
					g.setColor(Color.RED);
					g.fillRect(x, y, blockgroesse, blockgroesse);
				} else if (raumtyp.toLowerCase().equals("border"))
				{
					g.setColor(Color.BLACK);
					g.drawRect(x, y, blockgroesse, blockgroesse);

					g.setColor(Color.YELLOW);
					g.fillRect(x, y, blockgroesse, blockgroesse);
				} else if (raumtyp.toLowerCase().equals("embedded"))
				{
					g.setColor(Color.BLACK);
					g.drawRect(x, y, blockgroesse, blockgroesse);

					g.setColor(new Color(205, 133, 63));
					g.fillRect(x, y, blockgroesse, blockgroesse);
				}

			}

		/**
		 * Zeichne die Karte.
		 * 
		 * @param g
		 */
		public void getMapData(Graphics g)
			{
				// Male alles Blau
				g.setColor(new Color(0, 178, 238));
				g.fillRect(0, 0, _adventure._world.getWorldSize().height * (blockgroesse + 3) + 2,
						_adventure._world.getWorldSize().height * (blockgroesse + 3) + 2);

				// Iteriere durch jeden Raum
				for (int i = 0; i < _adventure._world.getWorldSize().height; ++i)
				{
					for (int j = 0; j < _adventure._world.getWorldSize().width; ++j)
					{
						int x = i * (blockgroesse + 3) + 2;
						int y = j * (blockgroesse + 3) + 2;

						// Wenn nichts
						if (_adventure._world.alpha[i][j] == null)
						{
							drawRaum("leer", g, x, y);

						} else
						// wenn Raum
						{
							drawRaum("!leer", g, x, y);

							if (_adventure._world.alpha[i][j].isPlayerHere())
								drawRaum("spieler", g, x, y);
						}

					}
				}

			}

		/**
		 * Zeichne alle Räume ein, die Abstand 1 zu einem gesetzten Raum haben,
		 * 
		 * @param g
		 * @param set
		 */
		public void drawNextFreeRooms(Graphics g, Set<Point> set, Color color)
			{
				List<Point> temp = new ArrayList<>(set);
				for (int i = 0; i < temp.size(); ++i)
				{
					Point koordinate = temp.get(i);
					g.setColor(color);
					int x = koordinate.x * (blockgroesse + 3) + 2;
					int y = koordinate.y * (blockgroesse + 3) + 2;

					// Wenn sich Raum an der Küste befindet.
					if (_adventure._world.getAvailableRooms(koordinate.x, koordinate.y, true).size() > 0)
						drawRaum("border", g, x, y);

					// Wenn Raum eingeschlossen. Dann mal nichts.
					if (_adventure._world.getAvailableRooms(koordinate.x, koordinate.y, false).size() == 4)
						drawRaum("leer", g, x, y);

				}
			}

		public void updateXYAnzeige(JTextField tfX, JTextField tfY, JTextField tfRZ, Point xy)
			{
				tfX.setText(Integer.toString(xy.x));
				tfY.setText(Integer.toString(xy.y));

				tfRZ.setText(getRaumBez(xy.x, xy.y));
			}

		private String getRaumBez(int x, int y)
			{
				// TODO Auto-generated method stub
			

				// Richtig runden, sodass X und Y die Ecke oben Links eines
				// Käastchens wiedergibt.
				// x und y wurden invertiert, da das Array anders rum
				// funktioniert.
				int y_gerundet = (int) (x / (blockgroesse + 3) - 2) * y;
				int x_gerundet = (int) (y / (blockgroesse + 3) - 2) * y;

				int i = (int) ((x-2) / (blockgroesse + 3));
				int j = (int) ((y-2) / (blockgroesse + 3));
				System.out.println(i+ " "+ j);
				if(_adventure._world.alpha[i][j] instanceof Raum)
				return _adventure._world.alpha[i][j].getBezeichnung();
				else
					return "Leer";
			}

	}
