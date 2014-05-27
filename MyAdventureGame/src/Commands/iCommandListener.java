package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Backend.AdventureMain;

public class iCommandListener implements ActionListener
	{
		private String cmd;
		private AdventureMain _adventure;

		public iCommandListener(String command, AdventureMain adv)
			{
				cmd = command;
				_adventure = adv;
			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub

				switch (cmd)
					{
					case "singleplay": new ButtonSingleplay(_adventure).execute();
						break;
					case "multiplayer": new ButtonMultiplay(_adventure).execute();
						break;
					case "quit": new ButtonQuit().execute();
						break;
					case "map": new ButtonMap(_adventure).execute();
					break;
					default:
						break;
					}

			}

	}
