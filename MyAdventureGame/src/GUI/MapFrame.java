package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import Backend.Commands.iCommandListener;
import Backend.Global.AdventureMain;
import Backend.Global.MapImpl;
import Backend.Global.MapImplMouseListener;

@SuppressWarnings("serial")
public class MapFrame extends JFrame implements ActionListener
	{
		public MapImpl implMap;
		public MapPanel mapPanel;

		private Timer tm = new Timer(10000, this);
		private AdventureMain _adventure;
		public JTextField koordiX, koordiY, raumBez;
		public JTextField spielerkoordiX, spielerkoordiY, spielerraumBez;

		public MapFrame(AdventureMain adv)
			{
				_adventure = adv;
				implMap = new MapImpl(adv);

				this.setSize(810 + 175, 840);
				this.setVisible(true);

				this.setLayout(new BorderLayout());
				adv._mapPanel = new MapPanel(adv);
				JScrollPane scrollPane = new JScrollPane(mapPanel);

				this.add(scrollPane, BorderLayout.CENTER);
				this.add(new InfoPanel(), BorderLayout.LINE_END);
				this.setResizable(true);

				adv._mapFrame = this;
				tm.start();

			}

		public class MapPanel extends JPanel
			{

				public MapPanel(AdventureMain adv)
					{
						int blocksize = implMap.getBlockgroesse();
						int blockdistance = implMap.getBlockabstand();

						int widthPanel = (int) adv._world.getWorldSize().getHeight() * (blocksize + blockdistance) + 2;
						int heightPanel = (int) adv._world.getWorldSize().getWidth() * (blocksize + blockdistance) + 2;
						setPreferredSize(new Dimension(widthPanel, heightPanel));
						this.setVisible(true);
						this.addMouseMotionListener(new MapImplMouseListener(_adventure, implMap));
						mapPanel = this;

					}

				public void paintComponent(Graphics g)
					{
						// implMap.drawNextFreeRooms(g,
						// _adventure._world.freieRaeume, Color.WHITE);
						implMap.getMapData(g);

						if (_adventure._spieler1.isOnWorld(_adventure._world.alpha))
							implMap.updatePlayInfo(_adventure._spieler1, spielerkoordiX, spielerkoordiY, spielerraumBez);

					}
			}

		public class InfoPanel extends JPanel
			{
				public InfoPanel()
					{
						this.setPreferredSize(new Dimension(175, getHeight()));
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

						// Label Informationen
						c.anchor = GridBagConstraints.LINE_START;
						c.insets = new Insets(15, 5, 5, 5);
						textInfos.add(new JLabel("Cursor:"), c);

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

						spielerkoordiX = new JTextField("-");
						spielerkoordiY = new JTextField("-");
						spielerraumBez = new JTextField("-");

						spielerkoordiX.setPreferredSize(new Dimension(75, 20));
						spielerkoordiY.setPreferredSize(new Dimension(75, 20));
						spielerraumBez.setPreferredSize(new Dimension(75, 20));

						spielerkoordiX.setEditable(false);
						spielerkoordiY.setEditable(false);
						spielerraumBez.setEditable(false);

						// Label Informationen
						c.gridx = 0;
						c.gridy = 4;
						c.anchor = GridBagConstraints.LINE_START;
						c.insets = new Insets(50, 5, 5, 5);
						textInfos.add(new JLabel("Du bist hier:"), c);

						c.insets = new Insets(5, 5, 5, 5);

						// Label
						c.gridy = 5;
						c.gridx = 0;
						c.anchor = GridBagConstraints.LINE_END;
						textInfos.add(new JLabel("X-Koordinate:"), c);

						// Textarea
						c.gridx = 1;
						c.anchor = GridBagConstraints.LINE_START;

						textInfos.add(spielerkoordiX, c);

						// Label
						c.gridy = 6;
						c.gridx = 0;
						c.anchor = GridBagConstraints.LINE_END;
						textInfos.add(new JLabel("Y-Koordinate:"), c);

						// Textarea
						c.gridx = 1;
						c.anchor = GridBagConstraints.LINE_START;
						textInfos.add(spielerkoordiY, c);

						// Label
						c.gridy = 7;
						c.gridx = 0;
						c.anchor = GridBagConstraints.LINE_END;
						textInfos.add(new JLabel("Ort:"), c);

						// Textarea
						c.gridx = 1;
						c.anchor = GridBagConstraints.LINE_START;
						textInfos.add(spielerraumBez, c);

						// Buttonpanel
						JPanel worldoperations = new JPanel();
						worldoperations.setLayout(new FlowLayout());

						JButton recreate = new JButton("Re-create");
						recreate.addActionListener(new iCommandListener("recreate", _adventure));

						worldoperations.add(recreate);

						this.add(textInfos, BorderLayout.PAGE_START);
						this.add(worldoperations, BorderLayout.PAGE_END);
					}
			}

		@Override
		public void actionPerformed(ActionEvent arg0)
			{
				repaint();
			}

	}