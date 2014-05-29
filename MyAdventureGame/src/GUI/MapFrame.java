package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import Backend.Global.AdventureMain;
import Backend.Global.MapImpl;
import Backend.Global.MapImplMouseListener;

@SuppressWarnings("serial")
public class MapFrame extends JFrame implements ActionListener
	{
		private MapImpl implMap;
		private Timer tm = new Timer(100, this);
		private AdventureMain _adventure;
		public JTextField koordiX, koordiY, raumBez;

		public MapFrame(AdventureMain adv)
			{
				_adventure = adv;
				implMap = new MapImpl(adv);

				this.setSize(810 + 200, 830);
				this.setVisible(true);

				this.setLayout(new BorderLayout());
				this.add(new MapPanel(adv), BorderLayout.CENTER);
				this.add(new InfoPanel(), BorderLayout.LINE_END);
				this.setResizable(false);
				tm.start();

			}

		public class MapPanel extends JPanel
			{

				public MapPanel(AdventureMain adv)
					{
						// this.setSize(300, 300);
						this.setVisible(true);
						this.addMouseMotionListener(new MapImplMouseListener(_adventure, implMap));

					}

				public void paintComponent(Graphics g)
					{
						implMap.getMapData(g);
						implMap.drawNextFreeRooms(g, _adventure._world.freieRaeume, Color.WHITE);
					}

			}

		public class InfoPanel extends JPanel
			{
				public InfoPanel()
					{
						this.setPreferredSize(new Dimension(200, getHeight()));
						this.setLayout(new BorderLayout());

						GridBagConstraints c = new GridBagConstraints();
						JPanel textInfos = new JPanel();
						textInfos.setLayout(new GridBagLayout());
						 koordiX = new JTextField("-");
						 koordiY = new JTextField("-");
						 raumBez = new JTextField("-");

						koordiX.setPreferredSize(new Dimension(75, 20));
						koordiY.setPreferredSize(new Dimension(75, 20));
						raumBez.setPreferredSize(new Dimension(75, 20));
						
						koordiX.setEditable(false);
						koordiY.setEditable(false);
						raumBez.setEditable(false);

						
						//Label Informationen
						c.anchor = GridBagConstraints.CENTER;
						c.insets = new Insets(15, 5, 15, 5);
						textInfos.add(new JLabel("Informationen"), c);

						c.insets = new Insets(5, 5, 5, 5);

						// Label
						c.gridy = 1;
						c.gridx = 0;
						c.anchor = GridBagConstraints.LINE_END;
						textInfos.add(new JLabel("X-Koordinate:"), c);

						// Textarea
						c.gridx = 1;
						c.anchor = GridBagConstraints.LINE_START;

						textInfos.add(koordiX, c);

						// Label
						c.gridy = 2;
						c.gridx = 0;
						c.anchor = GridBagConstraints.LINE_END;
						textInfos.add(new JLabel("Y-Koordinate:"), c);

						// Textarea
						c.gridx = 1;
						c.anchor = GridBagConstraints.LINE_START;
						textInfos.add(koordiY, c);

						// Label
						c.gridy = 3;
						c.gridx = 0;
						c.anchor = GridBagConstraints.LINE_END;
						textInfos.add(new JLabel("Ort:"), c);

						// Textarea
						c.gridx = 1;
						c.anchor = GridBagConstraints.LINE_START;
						textInfos.add(raumBez, c);

						this.add(textInfos, BorderLayout.PAGE_START);

					}
			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				repaint();
			}

		
	}