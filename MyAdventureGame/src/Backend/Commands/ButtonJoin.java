package Backend.Commands;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Backend.Global.AdventureMain;

public class ButtonJoin
	{
		private AdventureMain _adventure;

		public ButtonJoin(AdventureMain adv)
			{
				_adventure = adv;
			}

		public void execute()
			{
				// TODO Auto-generated method stub
				try
				{
					if (_adventure._mpPanel.nick.getText().isEmpty())
					{
						try
						{
							JOptionPane.showMessageDialog(null, "You did not input your nickname!");
						} catch (ExceptionInInitializerError exc)
						{
						}
						return;
					}

					_adventure._mpImpl.socket = new Socket(_adventure._mpPanel.ip.getText(),
							Integer.parseInt(_adventure._mpPanel.port.getText()));

					_adventure._mpImpl.outputStream = new ObjectOutputStream(
							_adventure._mpImpl.socket.getOutputStream());
					_adventure._mpImpl.outputStream.flush();
					_adventure._mpImpl.inputStream = new ObjectInputStream(_adventure._mpImpl.socket.getInputStream());

					_adventure._mpImpl.message1 = (String) _adventure._mpImpl.inputStream.readObject();
					_adventure._gamePanel.textArea.append(_adventure._mpImpl.message1 + "\n");

					_adventure._mpImpl.nick2 = _adventure._mpPanel.nick.getText();
					_adventure._mpImpl.signal = false;

					_adventure._mpImpl.message1 = (String) _adventure._mpImpl.inputStream.readObject(); // get
					// nick
					// from
					// host
					_adventure._mpImpl.nick1 = "" + _adventure._mpImpl.message1;

					_adventure._mpImpl.sendMessage(_adventure._mpImpl.nick2);

					_adventure._gamePanel.textInput.setEditable(true);

					_adventure._mpPanel.ip.setEnabled(false);
					_adventure._mpPanel.port.setEnabled(false);
					_adventure._mpPanel.nick.setEnabled(false);

					_adventure._gamePanel.textArea.append("Hallo Spieler!");

					_adventure._mpPanel.join.setEnabled(false);
					_adventure._mpPanel.create.setEnabled(false);
					_adventure._mpPanel.ip.setEnabled(false);
					_adventure._mpPanel.port.setEnabled(false);
					_adventure._mpPanel.nick.setEnabled(false);
					
//					 new ReceiveMessages("MsgFromServer"); // thread
					 _adventure._mpImpl.createReveiceObject();
					 // for
					 // receive
					 // data
					 // from
					 // host
				} catch (Exception e)
				{
					try
					{
						JOptionPane.showMessageDialog(null, "JoinButton: Error: Server is offline: \n" + e);
					} catch (ExceptionInInitializerError exc)
					{
					}
				}

			}

	}
