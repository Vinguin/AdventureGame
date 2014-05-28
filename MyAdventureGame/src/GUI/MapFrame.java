package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.AdventureMain;
import Backend.ImplMap;


@SuppressWarnings("serial")
public class MapFrame extends JFrame implements ActionListener
	{
		private ImplMap implMap;
		private Timer tm = new Timer(1000, this);

		public MapFrame(AdventureMain adv)
			{
				this.setSize(300, 300);
				this.setVisible(true);
				this.setContentPane(new MapPanel(adv));
				implMap = new ImplMap(adv);
				tm.start();

			}

		public class MapPanel extends JPanel
			{

				public MapPanel(AdventureMain adv)
					{
						this.setSize(300, 300);
						this.setVisible(true);

					}

				public void paintComponent(Graphics g)
					{
						implMap.getMapData(g);
						// drawNextFreeRooms(g, _world.freieRaeume);
					}

			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				repaint();
			}
	}