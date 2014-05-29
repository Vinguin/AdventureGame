package Backend.Commands;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

import Backend.Global.AdventureMain;

public class ButtonCreate
	{
		private AdventureMain _adventure;

		public ButtonCreate(AdventureMain adv)
			{
				_adventure = adv;
				// TODO Auto-generated constructor stub
			}

		public void execute()
			{
				// TODO Auto-generated method stub
				if (_adventure._mpPanel.nick.getText().equals("") || _adventure._mpPanel.nick.getText().equals(" "))
				{
					try
					{
						JOptionPane.showMessageDialog(null, "You did not input your nickname!");
					} catch (ExceptionInInitializerError exc)
					{
					}
					return;
				}

				_adventure._mpImpl.createCreateObject();
				
//				new CreateButtonThread("CreateButton"); // we need
														// thread
														// while we
														// wait for
														// client,
														// because
														// we don't
														// want
														// frozen
														// frame
			}

		

	}
