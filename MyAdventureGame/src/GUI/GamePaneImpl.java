package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Backend.Global.AdventureMain;

public class GamePaneImpl
	{

		private AdventureMain _adventure;
		private GamePlayPanel _gPanel;
		private List<Point> rasta;
		private int x_klein, y_klein;

		public GamePaneImpl(GamePlayPanel gamePlayPanel, AdventureMain adv)
			{
				_gPanel = gamePlayPanel;
				_adventure = adv;

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
				int width = (int) getFieldSize().getWidth();
				int height = (int) getFieldSize().getHeight();
				
				for (int i = 0; i < rasta.size(); ++i)
				{
					Color color = getRandomColor();
					g.setColor(color);

					int x = rasta.get(i).x;
					int y = rasta.get(i).y;
					
					g.fillRect(x, y, width, height);
				}

			}

	}
