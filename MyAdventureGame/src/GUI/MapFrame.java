package GUI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.ImplMap;
import Backend.World;


@SuppressWarnings("serial")
public class MapFrame extends JFrame
	{

		public MapFrame(World w)
			{
				this.setSize(300, 300);
				this.setVisible(true);
				this.setContentPane(new MapPanel(w));
			}

		public class MapPanel extends JPanel
			{
				private ImplMap implMap;

				public MapPanel(World worldi)
					{
						super();
						this.setSize(300, 300);
						this.setVisible(true);
						implMap = new ImplMap(worldi);

					}

				public void paintComponent(Graphics g)
					{
						implMap.drawMap(g);
						// drawNextFreeRooms(g, _world.freieRaeume);
					}

			}
	}