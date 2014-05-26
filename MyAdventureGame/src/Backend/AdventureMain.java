package Backend;

import GUI.MainFrame;
import GUI.MenuPanel;
import GUI.SplashScreen;

public class AdventureMain
	{
		public AdventureMain _adventure;
		public SplashScreen _splashScreen;
		public MenuPanel _menuPanel;
		public MainFrame _frame;

		public static void main(String[] args)
			{
				new AdventureMain();
			}

		public AdventureMain()
			{
				_adventure = this;
				_splashScreen = new SplashScreen(this);
			}
	}
