package GUI;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Backend.Commands.iCommandListener;
import Backend.Global.AdventureMain;

public class MenuPanel extends JPanel
	{
		AdventureMain _adventure;

		public MenuPanel(AdventureMain adv)
			{
				_adventure = adv;

				this.setLayout(new BorderLayout());
				JButton singlePlay = new JButton("SinglePlayer");
				JButton multiPlay = new JButton("MultiPlayer");
				JButton quit = new JButton("Quit");

				singlePlay.addActionListener(new iCommandListener("singleplay", adv));
				multiPlay.addActionListener(new iCommandListener("multiplay", adv));
				quit.addActionListener(new iCommandListener("quit",adv));
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(singlePlay);
				buttonPanel.add(multiPlay);
				buttonPanel.add(quit);
				buttonPanel.setVisible(true);
				this.add(buttonPanel, BorderLayout.CENTER);
			}
	}
