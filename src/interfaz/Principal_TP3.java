package interfaz;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Principal_TP3 {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					JFrame frame = new JFrame();
					frame.add(new Lienzo());
					frame.setBounds(250, 250, 1000, 800);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					frame.setResizable(false);
					// frame.getContentPane().setLayout(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

