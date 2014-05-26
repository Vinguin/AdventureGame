package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import Backend.AdventureMain;
import Commands.iCommandListener;

public class MenuPanel extends JPanel
	{
		AdventureMain _adventure;

		public MenuPanel(AdventureMain adv)
			{
				_adventure = adv;

				JButton singlePlay = new JButton("SinglePlayer");
				JButton multiPlay = new JButton("MultiPlayer");
				JButton quit = new JButton("Quit");

				singlePlay.addActionListener(new iCommandListener("singleplay", adv));
				multiPlay.addActionListener(new iCommandListener("multiplay", adv));
				quit.addActionListener(new iCommandListener("quit",adv));

				this.add(singlePlay);
				this.add(multiPlay);
				this.add(quit);
			}
	}
