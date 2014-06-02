package Backend.Commands;

import Backend.Global.AdventureMain;
import Backend.Global.MyChatClient;

public class ButtonJoin
	{
		private AdventureMain _adventure;

		public ButtonJoin(AdventureMain adv)
			{
				_adventure = adv;
			}

		public void execute()
			{
				try
				{
					MyChatClient.main(null);
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}
