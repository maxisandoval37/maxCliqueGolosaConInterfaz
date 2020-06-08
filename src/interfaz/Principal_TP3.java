package interfaz;

import java.awt.EventQueue;

public class Principal_TP3 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaBienvenida frame = new VistaBienvenida();
					frame._ventanaBienvenida.setVisible(true);
					frame._ventanaBienvenida.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
