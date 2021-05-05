package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.panel.EscapePanel;
import frame.panel.InformationPanel;
//import frame.panel.LoadPanel;
//import frame.panel.SavePanel;
//import listener.EscapePanelListener;
import object.Node;
import object.Path;
import save.DataModel;
//import save.Save;
import util.Vector;

public class GraphingScene extends JPanel implements Runnable {
	private Thread th;
	private Dimension dimension;
	//private Save save = new Save();
	private DataModel dt = new DataModel();
	private boolean isRunning = false;
	private final long SLEEP = 3;
	private InformationPanel informationPanel;
	private double xPrecedent, yPrecedent, dx, dy;
	private Node selectedNode = new Node();
	private Path tempPath;
	private boolean nodeSelected = false;
	private boolean mouseClicked = false;
	private boolean newPath = false;
	private boolean firstNodeSelected = false;
	private EscapePanel escapePanel;
	//private LoadPanel loadPanel;
	//private SavePanel savePanel;

	public GraphingScene(Dimension dimension) {
		setBounds(0,0,(int)dimension.getWidth(),(int)dimension.getHeight());
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(nodeSelected && mouseClicked) {
					
					
					dx = arg0.getX() - xPrecedent;
					
					
					System.out.println(arg0.getX() + " " + xPrecedent +  " " +  dx);
					//System.out.println("x:" + arg0.getX() + "|" + " y:" + arg0.getY());
					
					dy = arg0.getY() - yPrecedent;
					xPrecedent = arg0.getX();
					yPrecedent = arg0.getY();
					selectedNode.translate(dx,dy);
				}
				
			
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				if(firstNodeSelected) {
					tempPath.getNodes()[1].setPosition(new Vector(e.getX(),e.getY()));
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				xPrecedent = arg0.getX();
				yPrecedent = arg0.getY();
				nodeSelected = false;
				selectedNode = new Node();
				for (Node node : dt.getNodes()) { //Figure out if user clicked on a node with mouse position
					if (node.isInside(arg0.getPoint())) {
						nodeSelected = true;
						selectedNode = node;
						node.setIsSelected(true);
					} else {
						node.setIsSelected(false);
					}
				}
				if (nodeSelected && arg0.getButton() == MouseEvent.BUTTON1) { //Left click
					informationPanel.update(selectedNode);
					informationPanel.setVisible(true);
					xPrecedent = arg0.getX();
					yPrecedent = arg0.getY();
				} else {
					informationPanel.setVisible(false);
				}
				
				if(firstNodeSelected && nodeSelected) { //After second node is selected add it to the memory
					tempPath.setNodeTwo(selectedNode);
					firstNodeSelected = false;
					dt.addPath(tempPath);
					selectedNode.addPath(tempPath);
					tempPath = null;
				}
				
				if(nodeSelected && newPath) { //First node selection when creating a path
					tempPath = new Path();
					tempPath.setNodes(selectedNode, new Node(arg0.getX(),arg0.getY()));
					tempPath.getNodes()[1].setPosition(new Vector(arg0.getX(),arg0.getY()));
					selectedNode.addPath(tempPath);
					firstNodeSelected = true;
					newPath = false;
				}
				
				
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				mouseClicked = false;
				nodeSelected = false;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				for (Node node : dt.getNodes()) {
					if (node.isInside(e.getPoint())) {
						nodeSelected = true;
						mouseClicked = true;
						selectedNode = node;
					}
				}
				if(nodeSelected && e.getButton() == MouseEvent.BUTTON3) { //Right click
					Vector positionSouris = new Vector(e.getPoint());
					mouseClicked = true;
					xPrecedent = positionSouris.getX();
					yPrecedent = positionSouris.getY();
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int keyCode = arg0.getKeyCode();
				Point pos = MouseInfo.getPointerInfo().getLocation();
				pos.setLocation(pos.getX() - getLocationOnScreen().getX(), pos.getY() - getLocationOnScreen().getY());
				
				switch (keyCode) {
				case KeyEvent.VK_N:
					dt.addNode(new Node(pos));
					break;
				case KeyEvent.VK_D:
					dt.deleteNode(pos);
					break;
				case KeyEvent.VK_P:
					newPath = true;
					break;
				case KeyEvent.VK_ESCAPE:
					if(escapePanel.isVisible()) {
						escapePanel.setVisible(false);
						escapePanel.setIsVisible(false);
					} else {
						escapePanel.setVisible(true);
						escapePanel.setIsVisible(true);
					}
					break;
				}
			}

			
		});
		this.dimension = dimension;
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		informationPanel = new InformationPanel();
		informationPanel.setBounds(10, 10, 125, 107);
		add(informationPanel);
		informationPanel.setLayout(null);
		
		
		JLabel newLbl = new JLabel("N = New Node");
		newLbl.setBounds(10, getHeight()-115, 200, 14);
		add(newLbl);
		
		JLabel deleteLbl = new JLabel("D = Delete Node");
		deleteLbl.setBounds(10, getHeight()-95, 200, 14);
		add(deleteLbl);
		informationPanel.setVisible(false);
		
		JLabel dragLbl = new JLabel("Right Click = Drag Node");
		dragLbl.setBounds(10, getHeight()-75, 200, 14);
		add(dragLbl);
		informationPanel.setVisible(false);
		
		JLabel infoLbl = new JLabel("Left Click = Node's info");
		infoLbl.setBounds(10, getHeight()-55, 200, 14);
		add(infoLbl);
		
		escapePanel = new EscapePanel();
		
		/**
		escapePanel.addEscapePanelListener(new EscapePanelListener() {
			public void load() {
				escapePanel.setVisible(false);
				escapePanel.setIsVisible(false);
				//loadPanel.setVisible(true);
				//loadPanel.updateCombo();
			}

			@Override
			public void save() {
				escapePanel.setVisible(false);
				escapePanel.setIsVisible(false);
				//savePanel.setVisible(true);
			}
		});**/
		
		escapePanel.setBounds(getWidth()/2 - 190/2, getHeight()/2 - 230/2, 190, 230);
		add(escapePanel);
		escapePanel.setLayout(null);
		escapePanel.setVisible(false);
		informationPanel.setVisible(false);
		/*
		loadPanel = new LoadPanel();
		loadPanel.setBounds(getWidth()/2 - 176/2, getHeight()/2-80/2,176,80);
		add(loadPanel);
		loadPanel.setLayout(null);
		loadPanel.setVisible(false);
		loadPanel.setSave(save);
		loadPanel.setDt(dt);
		loadPanel.updateCombo();
		
		savePanel = new SavePanel();
		savePanel.setBounds(getWidth()/2 - 150/2, getHeight()/2-80/2,150,85);
		add(savePanel);
		savePanel.setLayout(null);
		savePanel.setVisible(false);
		savePanel.setSave(save);
		savePanel.setDt(dt);
		 */
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		if(tempPath != null) {
			tempPath.draw(g2d);
		}
		for(Path path : dt.getPaths()) {
			path.draw(g2d);
		}
		for (Node node : dt.getNodes()) {
			node.draw(g2d);
		}
		
		informationPanel.update(selectedNode);
	}

	@Override
	public void run() {
		while (isRunning) {
			repaint();

			try {
				th.sleep(SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void start() {
		if (!isRunning) {
			isRunning = true;
			th = new Thread(this);
			th.start();
		}
	}

	public void stop() {
		isRunning = false;
	}

	public void setDt(DataModel dt) {
		this.dt = dt;
	}
	
	//public void setSave(Save save) {
	//	this.save = save;
	//}
}
