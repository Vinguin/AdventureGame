package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapPanel extends JPanel
	{
		public MapPanel()
			{
				super();

			}

		public void paintComponents(Graphics g)
			{
				
			}

		public void drawRaum(String raumtyp, Graphics g, int x, int y)
			{
				g.setColor(Color.BLACK);

				if (raumtyp.toLowerCase().equals("leer"))
					g.drawRect(x, y, 5, 5);
				if (raumtyp.toLowerCase().equals("!leer"))
				{
					g.setColor(Color.ORANGE);
					g.fillRect(x, y, 5, 5);
				}

			}

	}
