package frame.panel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import listener.EscapePanelListener;
import save.DataModel;
import save.Save;

public class EscapePanel extends JPanel {
	private boolean isVisible = false;
	private DataModel dataModel = new DataModel();
	private ArrayList<EscapePanelListener> listeEcouteurs = new ArrayList<EscapePanelListener>();
	
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
		
		JLabel lblNewLabel_1 = new JLabel("Exit");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);;
			}
		});
		lblNewLabel_1.setFont(new Font("Impact", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(84, 205, 22, 14);
		add(lblNewLabel_1);
		
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
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				isVisible = false;
			}
		});
		lblReturn.setFont(new Font("Impact", Font.PLAIN, 15));
		lblReturn.setBounds(74, 180, 42, 14);
		add(lblReturn);
		
	
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public void addEscapePanelListener(EscapePanelListener ecouteur) {
		listeEcouteurs.add(ecouteur);
	}
	
	private void leverEvenLoad() {
		for(EscapePanelListener ecouteur : listeEcouteurs) {
			ecouteur.load();
		}
	}
	
	private void leverEvenSave() {
		for(EscapePanelListener ecouteur: listeEcouteurs) {
			ecouteur.save();
		}
	}
	
	
}


