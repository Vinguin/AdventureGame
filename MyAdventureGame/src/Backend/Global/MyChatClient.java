package Backend.Global;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		private AdventureMain _adventure;

		/**
		 * Constructs the client by laying out the GUI and registering a
		 * listener with the textfield so that pressing Return in the listener
		 * sends the textfield contents to the server. Note however that the
		 * textfield is initially NOT editable, and only becomes editable AFTER
		 * the client receives the NAMEACCEPTED message from the server.
		 */
		public MyChatClient(AdventureMain adv)
			{
				_adventure = adv;
				// Layout GUI
//				adv._gamePanel.textInput.setEditable(false);
//				adv._gamePanel.textArea.setEditable(false);
				

				// Add Listeners
				adv._gamePanel.textInput.addActionListener(new ActionListener()
				{
					/**
					 * Responds to pressing the enter key in the textfield by
					 * sending the contents of the text field to the server.
					 * Then clear the text area in preparation for the next
					 * message.
					 */
					public void actionPerformed(ActionEvent e)
						{
							out.println(_adventure._gamePanel.textInput.getText());
							_adventure._gamePanel.textInput.setText("");
						}
				});
				
			}

	

		/**
		 * Connects to the server then enters the processing loop.
		 */
		public void run() throws IOException
			{

				// Make connection and initialize streams
				String serverAddress = _adventure._mpPanel.ip.getText();
				Socket socket = new Socket(serverAddress, Integer.parseInt(_adventure._mpPanel.port.getText()));
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// Process all messages from server, according to the protocol.
				while (true)
				{
					String line = in.readLine();
					if (line.startsWith("SUBMITNAME"))
					{
						out.println(_adventure._mpPanel.nick.getText());
					} else if (line.startsWith("NAMEACCEPTED"))
					{
						_adventure._gamePanel.textInput.setEditable(true);
					} else if (line.startsWith("MESSAGE"))
					{
						_adventure._gamePanel.textArea.append(line.substring(8) + "\n");
					}
				}
			}

	
	}