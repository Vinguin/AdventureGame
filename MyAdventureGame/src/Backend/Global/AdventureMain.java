package Backend.Global;

import java.util.ArrayList;
import java.util.List;


import Backend.World.World;
import GUI.GamePanel;
import GUI.MainFrame;
import GUI.MapFrame;
import GUI.MenuPanel;
import GUI.SplashScreen;

public class AdventureMain
	{
		public AdventureMain _adventure;
		public SplashScreen _splashScreen;
		public MenuPanel _menuPanel;
		public MainFrame _frame;
		public GamePanel _gamePanel;
		public int _gameTime;
		public World _world;
		public MapFrame mapFrame;
		public Player _spieler;

		public static void main(String[] args)
			{
				new AdventureMain();
			}

		public AdventureMain()
			{
				_adventure = this;
				_splashScreen = new SplashScreen(this);				
				_world = new World(this);
				_spieler = new Player("Vinh", "Spawnraum", this);
			}
		
		
		
		
	}