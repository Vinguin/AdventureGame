package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Backend.World;

public class MapPanel extends JPanel
	{
		private World _world;

		public MapPanel(World worldi)
			{
				super();
				 _world = worldi;
				 this.setSize(800,600);
				 this.setVisible(true);
				 
			}

		public void paintComponent(Graphics g)
			{
				drawMap(g);
			}

		public void drawRaum(String raumtyp, Graphics g, int x, int y)
			{
				g.setColor(Color.BLACK);

				if (raumtyp.toLowerCase().equals("leer"))
					g.drawRect(x, y, 10, 10);
				if (raumtyp.toLowerCase().equals("!leer"))
				{
					g.setColor(Color.ORANGE);
					g.fillRect(x, y, 10, 10);
				}
				
		

			}
		
		public void drawMap(Graphics g)
			{
				for(int i = 0; i < 20; ++i)
				{
					for(int j = 0; j < 20; ++j )
					{
						int x = i*(10+2)+100;
						int y = j*(10+2)+100;

						if(_world.welt[i][j]== null)
						{
							drawRaum("leer", g, x, y);
						} else 
							drawRaum("!leer", g, x, y);

						
							
						
					}
				}
			}

	}
