package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Backend.Commands.iCommandListener;
import Backend.Global.AdventureMain;
import Backend.Global.MultiplayerImpl;

public class MultiplayerPanel extends JPanel
	{
		private AdventureMain _adventure;
		public JTextField ip, port, nick;
		public JButton join, create;
		private String safeSing = "!pass123!#$%&/()!";

		public MultiplayerPanel(AdventureMain adv)
			{
				_adventure = adv;
				// Initialisierung in der Gottklasse
				adv._mpImpl = new MultiplayerImpl(this, adv);

				this.setLayout(new FlowLayout());

				join = new JButton("Join");
				create = new JButton("Create");
				nick = new JTextField();
				ip = new JTextField(10);
				port = new JTextField();

				//JComponent mit Backend verbinden
				join.addActionListener(new iCommandListener("join", adv));
				create.addActionListener(new iCommandListener("create", adv));

				port.setText("9876");
				port.setPreferredSize(new Dimension(75, 25));

				ip.setText(adv._mpImpl.getMyIP());
				ip.setPreferredSize(new Dimension(0, 25));

				nick.setPreferredSize(new Dimension(100, 25));

				// Auf das Panel klatschen
				this.add(new JLabel("IP:"));
				this.add(ip);
				this.add(new JLabel("Port:"));
				this.add(port);
				this.add(new JLabel("Nickname:"));
				this.add(nick);
				this.add(create);
				this.add(join);

			}
	}
