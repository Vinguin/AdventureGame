package Backend.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Backend.World.WasserBiom;

public class ImplMap
	{
		AdventureMain _adventure;
		int blockgroesse = 7;

		public ImplMap(AdventureMain adv)
			{
				_adventure = adv;
			}

		public void drawRaum(String raumtyp, Graphics g, int x, int y)
			{

				if (raumtyp.toLowerCase().equals("wasser"))
				{
					g.setColor(Color.BLACK);
					g.drawRect(x, y, blockgroesse, blockgroesse);
					
					
					g.setColor(new Color(0,178,238));
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
				}

			}

		public void getMapData(Graphics g)
			{

				for (int i = 0; i < _adventure._world.getWorldSize().height; ++i)
				{
					for (int j = 0; j < _adventure._world.getWorldSize().width; ++j)
					{
						int x = i * (blockgroesse + 3)+2;
						int y = j * (blockgroesse + 3)+2;

						if (_adventure._world.alpha[i][j] == null )
						{
							drawRaum("leer", g, x, y);

						} else
						{
							drawRaum("!leer", g, x, y);
							
							if (_adventure._world.alpha[i][j].isPlayerHere())
								drawRaum("spieler", g, x, y);
						}

					}
				}
			}

		public void drawNextFreeRooms(Graphics g, Set<Point> set)
			{
				List<Point> temp = new ArrayList<>(set);
				for (int i = 0; i < temp.size(); ++i)
				{
					Point koordinate = temp.get(i);
					g.setColor(Color.GREEN);
					int x = koordinate.x * (11 + 3) + 10;
					int y = koordinate.y * (11 + 3) + 10;
					drawRaum("frei", g, x, y);

				}
			}

	}