package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
	{
		private JPanel _currentPanel;

		public MainFrame()
			{
				super();
				this.setSize(1200, 600);
				this.setVisible(true);
				this.setLocationRelativeTo(null);
				this.setResizable(true);

				// Handle the window close request similarly
				this.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
						{
							System.exit(0);
						}
				});
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
