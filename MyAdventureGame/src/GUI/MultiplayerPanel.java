package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Backend.Global.AdventureMain;
import Backend.Global.MultiplayerImpl;

public class MultiplayerPanel extends JPanel
	{
		private AdventureMain _adventure;
		private JTextField ip, port, nick;
		private JButton join, create;

		public MultiplayerPanel(AdventureMain adv)
			{
				_adventure = adv;
				//Initialisierung in der Gottklasse
				adv._mpImpl = new MultiplayerImpl(this);
				

				this.setLayout(new FlowLayout());

				join = new JButton("Join");
				create = new JButton("Create");
				nick = new JTextField();
				ip = new JTextField(10);
				port = new JTextField();
				
				
			

				
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
