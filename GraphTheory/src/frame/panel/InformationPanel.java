package frame.panel;

import javax.swing.JPanel;

import object.Node;

import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class InformationPanel extends JPanel {
	private JLabel posXlbl;
	private JLabel posYlbl;
	private JLabel degreeLbl;
	private JLabel nameLbl;
	
	public InformationPanel() {
		setLayout(null);
		
		posXlbl = new JLabel("x: ");
		posXlbl.setBounds(10, 36, 173, 14);
		add(posXlbl);
		
		posYlbl = new JLabel("y: ");
		posYlbl.setBounds(10, 61, 173, 14);
		add(posYlbl);
		
		degreeLbl = new JLabel("Degree: ");
		degreeLbl.setBounds(10, 86, 173, 14);
		add(degreeLbl);
		
		nameLbl = new JLabel("Name: ");
		nameLbl.setBounds(10, 11, 173, 14);
		add(nameLbl);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(84, 82, 43, 23);
		add(btnNewButton);
	
		try {
		    Image img = ImageIO.read(getClass().getResource("..\\res\\settings.png"));
		    btnNewButton.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    ex.printStackTrace();
		  }
		
		
		
	}

	public void update(Node nodeTemp) {
		nameLbl.setText("Name: " + nodeTemp.getName());
		posXlbl.setText("x: " + nodeTemp.getPostion().getX());
		posYlbl.setText("y: " + nodeTemp.getPostion().getY());
		degreeLbl.setText("Degree: " + nodeTemp.getDegree());
	}
}
