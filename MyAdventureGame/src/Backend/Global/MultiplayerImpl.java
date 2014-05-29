package Backend.Global;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

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

		private boolean sendSignal;
		public boolean signal;

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

		// --- Send Data over Internet ---
		public void sendMessage(String p)
			{
				try
				{
					if (sendSignal)
					{
						outputStream.writeObject(p);
						outputStream.flush();
					}
				} catch (SocketException e)
				{
					if (sendSignal)
					{
						sendSignal = false;
						deleteAll();
						runAll();
					}
				} catch (Exception e)
				{
					if (sendSignal)
					{
						sendSignal = false;
						deleteAll();
						runAll();
						try
						{
							JOptionPane.showMessageDialog(null, "Sending data/Disconnect:\n" + e);
						} catch (ExceptionInInitializerError exc)
						{
						}
					}
				}
			}

		// --- Turn OFF all streams ---
		public void deleteAll()
			{
				try
				{
					outputStream.flush();
				} catch (Exception e)
				{
				}
				try
				{
					outputStream.close();
				} catch (Exception e)
				{
				}
				try
				{
					inputStream.close();
				} catch (Exception e)
				{
				}
				try
				{
					serverSocket.close();
				} catch (Exception e)
				{
				}
				try
				{
					socket.close();
				} catch (Exception e)
				{
				}
			}

		public void runAll()
			{
				message1 = "";
				sendSignal = true;

				multiplayerPanel.ip.setEnabled(true);
				multiplayerPanel.port.setEnabled(true);
				multiplayerPanel.nick.setEnabled(true);
				multiplayerPanel.create.setEnabled(true);
				multiplayerPanel.join.setEnabled(true);

			}

		public void createCreateObject()
			{
				new CreateButtonThread("CreateButton");
			}

		public void createReveiceObject()
			{
				new ReceiveMessages("MsgFromServer");
			}

		private class CreateButtonThread implements Runnable
			{
				public CreateButtonThread(String name)
					{
						new Thread(this, name).start();
					}

				public void run()
					{
						try
						{
							_adventure._mpPanel.join.setEnabled(false);
							_adventure._mpPanel.create.setEnabled(false);
							_adventure._mpPanel.port.setEnabled(false);
							_adventure._mpPanel.nick.setEnabled(false);

							_adventure._mpImpl.serverSocket = new ServerSocket(
									Integer.parseInt(_adventure._mpPanel.port.getText()));

							_adventure._gamePanel.textArea.append("Waiting for client...\n");
							_adventure._mpImpl.socket = _adventure._mpImpl.serverSocket.accept();

							_adventure._mpImpl.outputStream = new ObjectOutputStream(
									_adventure._mpImpl.socket.getOutputStream());
							_adventure._mpImpl.outputStream.flush();
							_adventure._mpImpl.inputStream = new ObjectInputStream(
									_adventure._mpImpl.socket.getInputStream());
							_adventure._mpImpl.sendMessage(_adventure._mpPanel.nick.getText()
									+ ": Successfully connected!");
							_adventure._gamePanel.textArea.append("Client Successfully connected!\n");

							_adventure._mpImpl.signal = true;

							_adventure._mpImpl.nick1 = _adventure._mpPanel.nick.getText();

							_adventure._mpImpl.sendMessage(_adventure._mpImpl.nick1);

							_adventure._mpImpl.message1 = (String) _adventure._mpImpl.inputStream.readObject(); // prima
							// NICK
							// OD
							// SERVERA
							_adventure._mpImpl.nick2 = "" + _adventure._mpImpl.message1;

							_adventure._mpPanel.ip.setEnabled(false);

							_adventure._gamePanel.textArea.append("X plays first!\n");
							new ReceiveMessages("PorukaOdKlijenta");
						} catch (Exception e)
						{
							_adventure._mpImpl.deleteAll();
							_adventure._mpImpl.runAll();
							try
							{
								JOptionPane.showMessageDialog(null, "CreateButton: Error while creating game:\n" + e);
							} catch (ExceptionInInitializerError exc)
							{
								exc.printStackTrace();
							}
						}
					}
			}

		// --- Receive data/messages thread ---
		public class ReceiveMessages implements Runnable
			{
				private boolean nitSig;
				private String nameThread;

				public ReceiveMessages(String i)
					{
						nitSig = true;
						nameThread = i;
						new Thread(this, nameThread).start();
					}

				public void run()
					{
						while (nitSig)
						{
							try
							{
								_adventure._mpImpl.message1 = "";
								_adventure._mpImpl.message1 = (String) _adventure._mpImpl.inputStream.readObject(); // receive
								// messages
								// client receive data from host
								if (nameThread.equals("MsgFromServer"))
								{
									if (_adventure._mpImpl.message1.endsWith(_adventure._mpImpl.safeSing))
										_adventure._mpImpl.message1 = _adventure._mpImpl.message1.substring(
												0,
												_adventure._mpImpl.message1.length()
														- _adventure._mpImpl.safeSing.length());

									_adventure._gamePanel.textArea.append(_adventure._mpImpl.nick2 + ":"
											+ _adventure._mpImpl.message1 + "\n");

									if (message1.endsWith(safeSing))
									{
										message1 = message1.substring(0, message1.length() - safeSing.length());
									}
									_adventure._gamePanel.textArea.append(nick1 + ":" + message1 + "\n");
								}

							} catch (Exception e)
							{
								nitSig = false;
								_adventure._mpImpl.deleteAll();
								_adventure._mpImpl.runAll();
								try
								{
									JOptionPane.showMessageDialog(null, "Receiving Data Failed/Disconnect:\n" + e);
								} catch (ExceptionInInitializerError exc)
								{
								}
							}
						}

					}
			}
	}
