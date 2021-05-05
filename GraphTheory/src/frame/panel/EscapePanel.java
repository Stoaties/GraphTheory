package frame.panel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

//import listener.EscapePanelListener;
import save.DataModel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import save.Save;

public class EscapePanel extends JPanel {
	private boolean isVisible = false;
	private DataModel dataModel = new DataModel();
	//private ArrayList<EscapePanelListener> listeEcouteurs = new ArrayList<EscapePanelListener>();
	
	public EscapePanel() {
		setLayout(null);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Impact", Font.PLAIN, 20));
		lblMenu.setBounds(72, 11, 46, 39);
		add(lblMenu);
		
		JLabel lblNewLabel = new JLabel("Save");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				leverEvenSave();
				setVisible(false);
				isVisible = false;
			}
		});
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNewLabel.setBounds(79, 61, 31, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Load");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				leverEvenLoad();
				setVisible(false);
				isVisible = false;
			}
		});
		lblNewLabel_2.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(79, 86, 31, 14);
		add(lblNewLabel_2);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setContentAreaFilled(false);
		exitButton.setBounds(50, 196, 89, 23);
		add(exitButton);
		
		JButton returnButton = new JButton("Return");
		returnButton.setContentAreaFilled(false);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				isVisible=false;
			}
		});
		returnButton.setBounds(50, 169, 89, 23);
		add(returnButton);
		
	
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	//public void addEscapePanelListener(EscapePanelListener ecouteur) {
	//	listeEcouteurs.add(ecouteur);
	//}
	
	private void leverEvenLoad() {
		//for(EscapePanelListener ecouteur : listeEcouteurs) {
		//	ecouteur.load();
		//}
	}
	
	private void leverEvenSave() {
		//for(EscapePanelListener ecouteur: listeEcouteurs) {
		//	ecouteur.save();
		//}
	}
}


