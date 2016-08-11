package Backend.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTextField;

import Backend.World.Kueste;
import Backend.World.Wiese;

public class MapImpl
	{
		AdventureMain _adventure;
		int blockgroesse = 3;
		int blockabstand = 1;

		public MapImpl(AdventureMain adv)
			{
				_adventure = adv;
			}

		public int getBlockgroesse()
			{
				return blockgroesse;
			}

		public int getBlockabstand()
			{
				return blockabstand;
			}

		/**
		 * Werkzeug. Zeichnet einen Raum aus Raumtyp, getGraphics und Loc x,y.
		 * 
		 * @param raumtyp
		 * @param g
		 * @param x
		 * @param y
		 */
		public void drawRaum(String raumtyp, Graphics g, int x, int y, int blocksize, int blockdistance)
			{
				switch (raumtyp.toLowerCase())
					{
					case "wasser":
						// g.setColor(Color.BLACK);
						// g.drawRect(x, y, blockgroesse, blockgroesse);
						g.setColor(new Color(0, 178, 238));
						g.fillRect(x, y, blocksize, blocksize);
						break;
					case "wiese":
						// g.setColor(Color.black);
						// g.drawRect(x, y, blockgroesse, blockgroesse);

						g.setColor(Color.GREEN);
						g.fillRect(x, y, blocksize, blocksize);
						break;
					case "frei":
						g.setColor(Color.GREEN);
						g.fillRect(x, y, blocksize, blocksize);
						break;
					case "spieler":
						g.setColor(Color.RED);
						g.fillRect(x, y, blocksize, blocksize);

						break;
					case "border":
						// g.setColor(Color.BLACK);
						// g.drawRect(x, y, blockgroesse, blockgroesse);

						g.setColor(Color.YELLOW);
						g.fillRect(x, y, blocksize, blocksize);
						break;
					case "embedded":
						// g.setColor(Color.BLACK);
						// g.drawRect(x, y, blockgroesse, blockgroesse);

						g.setColor(new Color(205, 133, 63));
						g.fillRect(x, y, blocksize, blocksize);
						break;
					case "cursor":
						// g.setColor(Color.BLACK);
						// g.drawRect(x, y, blockgroesse, blockgroesse);
						g.setColor(Color.WHITE);
						g.fillRect(x, y, blocksize, blocksize);
						break;

					default:
						break;
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
				g.fillRect(0, 0, _adventure._world.getWorldSize().height * (blockgroesse + blockabstand) + 2,
						_adventure._world.getWorldSize().height * (blockgroesse + blockabstand) + 2);

				// Iteriere durch jeden Raum
				for (int i = 0; i < _adventure._world.getWorldSize().width; ++i)
				{
					for (int j = 0; j < _adventure._world.getWorldSize().height; ++j)
					{
						int x = i * (blockgroesse + blockabstand) + 2;
						int y = j * (blockgroesse + blockabstand) + 2;

						// Wenn nichts
						if (_adventure._world.alpha[i][j] == null)
						{
							drawRaum("leer", g, x, y, blockgroesse, blockabstand);

						} else if (_adventure._world.alpha[i][j] instanceof Wiese)
						// wenn Raum
						{
							drawRaum("wiese", g, x, y, blockgroesse, blockabstand);

							if (_adventure._world.alpha[i][j].isPlayerHere())
							{
								drawRaum("spieler", g, x, y, blockgroesse, blockabstand);
							}

							if (_adventure._world.alpha[i][j] instanceof Kueste
									&& !_adventure._world.alpha[i][j].isPlayerHere())
								drawRaum("border", g, x, y, blockgroesse, blockabstand);
						}

					}
				}

			}

		/**
		 * Zeichne alle R�ume ein, die Abstand 1 zu einem gesetzten Raum haben,
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
					int x = koordinate.x * (blockgroesse + blockabstand) + 2;
					int y = koordinate.y * (blockgroesse + blockabstand) + 2;

					// Wenn sich Raum an der K�ste befindet.
					if (_adventure._world.getAvailableRooms(koordinate.x, koordinate.y, true).size() > 0)
						drawRaum("border", g, x, y, blockgroesse, blockabstand);

					// Wenn Raum eingeschlossen. Dann mal nichts.
					if (_adventure._world.getAvailableRooms(koordinate.x, koordinate.y, false).size() == 4)
						drawRaum("leer", g, x, y, blockgroesse, blockabstand);

				}
			}

		public void updateXYAnzeige(JTextField tfX, JTextField tfY, JTextField tfRZ, Point xy)
			{
				tfX.setText(Integer.toString(xy.x));
				tfY.setText(Integer.toString(xy.y));
				tfRZ.setText(getRaumBezByXY(xy.x, xy.y));
			}

		private String getRaumBezByXY(int x, int y)
			{
				// Richtig runden, sodass X und Y die Ecke oben Links eines
				// K�astchens wiedergibt.

				int i = (int) ((x - 2) / (blockgroesse + blockabstand));
				int j = (int) ((y - 2) / (blockgroesse + blockabstand));

				if (_adventure._world.alpha[i][j] instanceof Wiese)
					return _adventure._world.alpha[i][j].getBezeichnung();
				else
					return "Leer";
			}

		/**
		 * Updated die Infoanzeige des Spielers
		 * 
		 * @param tfX
		 * @param tfY
		 * @param tfRZ
		 */
		public void updatePlayInfo(Player player, JTextField tfX, JTextField tfY, JTextField tfRZ)
			{
				tfX.setText(Integer.toString(player.getRaumLocation().x * (blockgroesse + blockabstand) + 2));
				tfY.setText(Integer.toString(player.getRaumLocation().y * (blockgroesse + blockabstand) + 2));
				tfRZ.setText(player.getRaum().getBezeichnung());
			}

		public void showCursor(int x, int y)
			{
				int i = (int) ((x - 2) / (blockgroesse + blockabstand));
				int j = (int) ((y - 2) / (blockgroesse + blockabstand));

				if (_adventure._world.alpha[i][j] instanceof Wiese)
					drawRaum("cursor", _adventure.mapFrame.mapPanel.getGraphics(), i * (blockgroesse + blockabstand)
							+ 2, j * (blockgroesse + blockabstand) + 2, blockgroesse, blockabstand);
			}

		public void updateMap()
			{
				_adventure._mapFrame.repaint();
			}
	}
