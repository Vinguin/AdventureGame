package Backend.Global;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A simple Swing-based client for the chat server. Graphically it is a frame
 * with a text field for entering messages and a textarea to see the whole
 * dialog.
 * 
 * The client follows the Chat Protocol which is as follows. When the server
 * sends "SUBMITNAME" the client replies with the desired screen name. The
 * server will keep sending "SUBMITNAME" requests as long as the client submits
 * screen names that are already in use. When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start sending the server
 * arbitrary strings to be broadcast to all chatters connected to the server.
 * When the server sends a line beginning with "MESSAGE " then all characters
 * following this string should be displayed in its message area.
 */
public class MyChatClient
	{

		BufferedReader in;
		PrintWriter out;

		/**
		 * Constructs the client by laying out the GUI and registering a
		 * listener with the textfield so that pressing Return in the listener
		 * sends the textfield contents to the server. Note however that the
		 * textfield is initially NOT editable, and only becomes editable AFTER
		 * the client receives the NAMEACCEPTED message from the server.
		 */
		public MyChatClient()
			{

				// Layout GUI
				AdventureMain._adventure._gamePanel.textInput.setEditable(true);
				AdventureMain._adventure._gamePanel.textArea.setEditable(false);

				// Add Listeners
				AdventureMain._adventure._gamePanel.textInput.addActionListener(new ActionListener()
				{
					/**
					 * Responds to pressing the enter key in the textfield by
					 * sending the contents of the text field to the server.
					 * Then clear the text area in preparation for the next
					 * message.
					 */
					public void actionPerformed(ActionEvent e)
						{
							out.println(AdventureMain._adventure._gamePanel.textInput.getText());
							AdventureMain._adventure._gamePanel.textInput.setText("");
						}
				});
			}

		/**
		 * Prompt for and return the address of the server.
		 */
		private String getServerAddress()
			{
				return AdventureMain._adventure._mpPanel.ip.getText();

			}

		public String getIP()
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

		/**
		 * Prompt for and return the desired screen name.
		 */
		private String getName()
			{
				return AdventureMain._adventure._mpPanel.nick.getText();
			}

		/**
		 * Connects to the server then enters the processing loop.
		 */
		public void run() throws IOException
			{
				
				// Make connection and initialize streams
				String serverAddress = getServerAddress();
				Socket socket = new Socket(serverAddress, 9876);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// Process all messages from server, according to the protocol.
				while (true)
				{
					String line = in.readLine();
					if (line.startsWith("SUBMITNAME"))
					{
						out.println(getName());
					}
					if (line.startsWith("NAMEACCEPTED"))
					{
						AdventureMain._adventure._gamePanel.textInput.setEditable(true);
					}
					if (line.startsWith("MESSAGE"))
					{
						AdventureMain._adventure._gamePanel.textArea.append(line.substring(8) + "\n");
					}
				}
			}

		/**
		 * Runs the client as an application with a closeable frame.
		 */
		public static void main(String[] args) throws Exception
			{
				MyChatClient client = new MyChatClient();
				client.run();
			}
	}