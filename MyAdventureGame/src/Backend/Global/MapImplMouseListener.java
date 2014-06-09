package Backend.Global;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MapImplMouseListener implements MouseMotionListener
	{

		private AdventureMain _adventure;
		private MapImpl implMap;

		public MapImplMouseListener(AdventureMain adv, MapImpl impl)
			{
				_adventure = adv;
				implMap = impl;
			}

		@Override
		public void mouseDragged(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void mouseMoved(MouseEvent e)
			{

				implMap.updateXYAnzeige(_adventure.mapFrame.koordiX, _adventure.mapFrame.koordiY,
						_adventure.mapFrame.raumBez, new Point(e.getX(), e.getY()));
				// Cursor Labels mit Coordinaten füllen
				implMap.showCursor(e.getX(), e.getY());

			}
	}
