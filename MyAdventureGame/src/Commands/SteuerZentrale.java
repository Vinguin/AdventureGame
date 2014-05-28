package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Backend.AdventureMain;

public class SteuerZentrale implements ActionListener
	{
		protected String cmd;
		protected  AdventureMain _adventure;

		public SteuerZentrale(String string, AdventureMain adv)
			{
				cmd = string;
				_adventure = adv;

			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub

				switch (cmd)
					{
					case "goNorth":
						new GoNorth(cmd, _adventure).go();
						break;
					case "goSouth":
						new GoSouth(cmd, _adventure).go();
						break;
					case "goEast":
						new GoEast(cmd, _adventure).go();
						break;
					case "goWest":
						new GoWest(cmd, _adventure).go();
						break;

					default:
						break;
					}

			}

	}
