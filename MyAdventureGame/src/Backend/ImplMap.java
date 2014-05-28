package Backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ImplMap
	{
		World _world;

		public ImplMap(World worldi)
			{
				_world = worldi;
			}

		public void drawRaum(String raumtyp, Graphics g, int x, int y)
			{
				int blockgroesse = 11;

				if (raumtyp.toLowerCase().equals("leer"))
				{
					g.setColor(Color.BLACK);
					g.drawRect(x, y, blockgroesse, blockgroesse);
					g.setColor(Color.WHITE);
					g.fillRect(x, y, blockgroesse, blockgroesse);

				}
				if (raumtyp.toLowerCase().equals("!leer"))
				{
					g.setColor(Color.black);
					g.drawRect(x, y, blockgroesse, blockgroesse);

					g.setColor(Color.GREEN);
					g.fillRect(x, y, blockgroesse, blockgroesse);
				}
				if (raumtyp.toLowerCase().equals("frei"))
				{
					g.setColor(Color.GREEN);
					g.fillRect(x, y, blockgroesse, blockgroesse);
				}

			}

		public void drawMap(Graphics g)
			{

				int blockgroesse = 11;
				for (int i = 0; i < 20; ++i)
				{
					for (int j = 0; j < 20; ++j)
					{
						int x = i * (blockgroesse + 3) + 10;
						int y = j * (blockgroesse + 3) + 10;

						if (_world.welt[i][j] == null)
						{
							drawRaum("leer", g, x, y);
						} else
							drawRaum("!leer", g, x, y);

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
