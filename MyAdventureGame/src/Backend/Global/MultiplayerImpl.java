package Backend.Global;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


import GUI.MultiplayerPanel;

public class MultiplayerImpl
	{
		private MultiplayerPanel multiplayerPanel;
		private AdventureMain _adventure;

		public String nick1, nick2, message1;
		public String safeSing = "pingupingu";

		// ServerSocket, Socket, Input and Output Streams
		public ServerSocket serverSocket = null;
		public Socket socket = null;
		public ObjectInputStream inputStream = null;
		public ObjectOutputStream outputStream = null;


		public MultiplayerImpl(MultiplayerPanel mp, AdventureMain adv)
			{
				multiplayerPanel = mp;
				_adventure = adv;

			}

		/**
		 * Gibt die IP adresse des aktuellen Benutzers heraus.
		 * 
		 * @return
		 */
		public String getMyIP()
			{
				try
				{
					InetAddress thisIp = InetAddress.getLocalHost();
					return thisIp.getHostAddress();
				} catch (Exception e)
				{
					return "127.0.0.1";
				}
			}

	}
