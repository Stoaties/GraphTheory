package frame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import save.DataModel;

public class Aapplication extends JFrame {

	private JPanel contentPane;
	private GraphingScene graphingScene;
	private DataModel dt = new DataModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aapplication frame = new Aapplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Aapplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1245, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		graphingScene = new GraphingScene(new Dimension(getWidth(),getHeight()));
		graphingScene.setBounds(0, 0, getWidth(), getHeight());
		graphingScene.setDt(dt);
		graphingScene.setFocusable(true); 
		graphingScene.requestFocus();
		graphingScene.start();
		contentPane.add(graphingScene);
	}
}
