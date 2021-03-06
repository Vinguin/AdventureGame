package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.Timer;
import Backend.AdventureMain;

public class SplashScreen extends JFrame implements ActionListener

	{
		private AdventureMain _adventure;
		public Timer timer = new Timer(2500, this);

		public SplashScreen(AdventureMain adv)
			{
				super("Splash");
				_adventure = adv;

				this.setSize(500, 350);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setContentPane(new Background("res/Wallpaper/splashscreen.jpg"));
				this.setLocationRelativeTo(null);
				this.setResizable(false);

				// Ohne Frame-Rand!
				this.setUndecorated(true);
				this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				this.setVisible(true);

				this.setVisible(true);
				timer.start();


			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// Sobald der Timer aubgelaufen ist, schlie�t sich der
				// SplashScreen und das Menu �ffnet sich
				this.dispose();

				_adventure._menuPanel = new MenuPanel(_adventure);
				_adventure._frame = new MainFrame();
				_adventure._frame.setContent(_adventure._menuPanel);
				timer.stop();
			}

		public class Background extends JPanel
			{
				Image img = null;

				public Background(String imagefile)
					{
						if (imagefile != null)
						{
							MediaTracker mt = new MediaTracker(this);
							img = Toolkit.getDefaultToolkit().getImage(imagefile);
							mt.addImage(img, 0);

							try
							{
								mt.waitForAll();
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}

				protected void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
					}
			}

	}