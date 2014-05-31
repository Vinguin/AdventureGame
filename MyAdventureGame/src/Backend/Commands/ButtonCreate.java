package Backend.Commands;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

import Backend.Global.AdventureMain;
import Backend.Global.MyChatServer;

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

				try
				{
					MyChatServer.main(null);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}

	}
