package Backend.Global;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import GUI.MultiplayerPanel;

public class MultiplayerImpl
	{
		MultiplayerPanel multiplayerPanel;

		public MultiplayerImpl(MultiplayerPanel mp)
			{
				multiplayerPanel = mp;

			}

		// ServerSocket, Socket, Input and Output Streams
		private ServerSocket serverSocket = null;
		private Socket socket = null;
		private ObjectInputStream inputStream = null;
		private ObjectOutputStream outputStream = null;

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
