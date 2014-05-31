package Backend.Commands;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Backend.Global.AdventureMain;
import Backend.Global.MyChatClient;

public class ButtonJoin
	{
		private AdventureMain _adventure;

		public ButtonJoin(AdventureMain adv)
			{
				_adventure = adv;
			}

		public void execute()
			{
				MyChatClient cclient = new MyChatClient(_adventure);
				try
				{
					cclient.run();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
				
	}
