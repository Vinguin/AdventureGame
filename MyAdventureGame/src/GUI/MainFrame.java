package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
	{
		private JPanel _currentPanel;

		public MainFrame()
			{
				super();
				this.setSize(800, 600);
				this.setVisible(true);
				this.setLocationRelativeTo(null);
			}

		/**
		 * Füllt das JFrame mit Content
		 * 
		 * @param panel
		 */
		public void setContent(JPanel panel)
			{
				_currentPanel = panel;
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
