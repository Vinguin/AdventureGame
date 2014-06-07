package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
	{
		private JPanel _currentPanel;
		private Image img;

		public MainFrame()
			{
				super();

				this.setSize(800, 600);
				this.setLayout(new BorderLayout());
				this.setVisible(true);
				this.setLocationRelativeTo(null);
				this.setResizable(false);

				// Handle the window close request similarly
				this.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
						{
							System.exit(0);
						}
				});
				paint(getGraphics());
			}
		
		
		

		/**
		 * Füllt das JFrame mit Content
		 * 
		 * @param panel
		 */
		public void setContent(JPanel panel)
			{
				_currentPanel = panel;
				this.getContentPane().removeAll();
				this.setContentPane(panel);
			}

		/**
		 * Gibt das aktuelle Panel wieder
		 * 
		 * @return
		 */
		public JPanel getCurrentPanel()
			{

				return _currentPanel;
			}

	}
