package GUI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.AdventureMain;
import Backend.ImplMap;
import Backend.World;


@SuppressWarnings("serial")
public class MapFrame extends JFrame
	{

		public MapFrame(AdventureMain adv)
			{
				this.setSize(300, 300);
				this.setVisible(true);
				this.setContentPane(new MapPanel(adv));
			}

		public class MapPanel extends JPanel
			{
				private ImplMap implMap;

				public MapPanel(AdventureMain adv)
					{
						super();
						this.setSize(300, 300);
						this.setVisible(true);
						implMap = new ImplMap(adv);

					}

				public void paintComponent(Graphics g)
					{
						implMap.getMapData(g);
						// drawNextFreeRooms(g, _world.freieRaeume);
					}

			}
	}