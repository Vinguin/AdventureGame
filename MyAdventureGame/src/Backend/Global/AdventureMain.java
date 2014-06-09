package Backend.Global;

import Backend.World.World;
import GUI.GamePanel;
import GUI.MainFrame;
import GUI.MapFrame;
import GUI.MenuPanel;
import GUI.MultiplayerPanel;
import GUI.SplashScreen;

public class AdventureMain
	{
		public static AdventureMain _adventure;
		public static MyChatClient chatclient;
		public SplashScreen _splashScreen;
		public MenuPanel _menuPanel;
		public MainFrame _frame;
		public GamePanel _gamePanel;
		public int _gameTime;
		public World _world;
		public MapFrame mapFrame;
		public Player _spieler1;
		public MultiplayerImpl _mpImpl;
		public MultiplayerPanel _mpPanel;
		public MapFrame _mapFrame;

		public static void main(String[] args)
			{
				new AdventureMain();
			}

		public AdventureMain()
			{
				_adventure = this;
				_splashScreen = new SplashScreen(this);
				_world = new World(this);
				_spieler1 = new Player("Vinh", "Spawnraum", _adventure);

			}
	}
