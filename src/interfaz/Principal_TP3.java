package interfaz;
import java.awt.EventQueue;

public class Principal_TP3 {
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaBienvenida frame = new VistaBienvenida();
                    frame.ventanaBienvenida.setVisible(true);
                    frame.ventanaBienvenida.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

