package Backend.Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Backend.Global.AdventureMain;

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
					case "join":
						new ButtonJoin(_adventure).execute();
						break;
					case "create":
						new ButtonCreate(_adventure).execute();
						break;
					case "singleplay":
						new ButtonSingleplay(_adventure).execute();
						break;
					case "multiplayer":
						new ButtonMultiplay(_adventure).execute();
						break;
					case "quit":
						new ButtonQuit().execute();
						break;
					case "map":
						new ButtonMap(_adventure).execute();
						break;
					case "recreate":
						new ButtonRecreateWorld(_adventure).execute();
						break;
					default:
						break;
					}

			}

	}
