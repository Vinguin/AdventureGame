package Backend.Global;

import java.awt.Image;
import java.awt.Toolkit;

public class TexturepackManager
	{
		public  Image baum, wiese, sand, schnee, wasser, berg, wall;

		public TexturepackManager()
			{
				baum = Toolkit.getDefaultToolkit().getImage("res/Floor/Baum.png");
				sand = Toolkit.getDefaultToolkit().getImage("res/Floor/Sand.png");
				wiese = Toolkit.getDefaultToolkit().getImage("res/Floor/Wiese.png");
				wasser = Toolkit.getDefaultToolkit().getImage("res/Floor/Wasser1.png");
				wall = Toolkit.getDefaultToolkit().getImage("res/Floor/Wall1.png");
				schnee = Toolkit.getDefaultToolkit().getImage("res/Floor/Schnee1.png");
			}


	}
