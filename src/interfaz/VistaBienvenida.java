package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VistaBienvenida {

	public JFrame ventanaBienvenida;
	private JFrame ventanaGrafo;
	private JButton btnEnviar;
	private JLabel lblFondo;
	private JTextField tFCantNodos;
	private TextoTranslucido textoTranslucido;
	static Integer cantidadNodosLimite;

	public VistaBienvenida() {
		initialize();
		inicializarTextfieldCantNodosACrear();
		inicializarBotonEnviar();
		asignarFondo();
	}

	private void initialize() {
		ventanaBienvenida = new JFrame();
		ventanaBienvenida.setBounds(300, 100, 1000, 800);
		ventanaBienvenida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaBienvenida.getContentPane().setLayout(null);
	}
	
	private void inicializarTextfieldCantNodosACrear() {
		tFCantNodos = new JTextField();
		setTextoTranslucido(new TextoTranslucido("NUMERO MAX DE NODOS A CREAR", tFCantNodos));
		tFCantNodos.setBounds(347, 515, 343, 52);
		ventanaBienvenida.getContentPane().add(tFCantNodos);
		tFCantNodos.setColumns(10);
		limitarInputUsuario(tFCantNodos);
	}
	
	private void inicializarBotonEnviar() {
		btnEnviar = new JButton("ENVIAR");
		btnEnviar.setBounds(443, 624, 164, 52);
		ventanaBienvenida.getContentPane().add(btnEnviar);
		accionBotonEnviar();
	}
	
	private void accionBotonEnviar() {
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tFCantNodos.getText().toString().equals("")) {
					cantidadNodosLimite = Integer.parseInt(tFCantNodos.getText().toString());
					cambiarVentana();
				} else
					JOptionPane.showMessageDialog(null, "INGRESE UN VALOR PARA CONTINUAR");
			}
		});
	}
	
	private void asignarFondo(){
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1000, 800);
		ventanaBienvenida.getContentPane().add(lblFondo);
		
		lblFondo.setIcon(new ImageIcon(VistaBienvenida.class.getResource("/interfaz/INICIO.png")));
	}

	private void cambiarVentana() {
		ventanaGrafo = new VistaGrafo();
		ventanaBienvenida.setVisible(false);
		ventanaGrafo.setVisible(true);
		ventanaGrafo.setResizable(false);
	}
	
	private void limitarInputUsuario(JTextField tf) {
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if((e.getKeyChar() < '0') || (e.getKeyChar() > '9') && (e.getKeyChar() != '\b')){
					e.consume(); 
				} else {
					btnEnviar.setEnabled(true);
					if (tf.getText().length() >= 7)
						e.consume();
				}
			}
		});
	}
	
	public TextoTranslucido getTp() {
		return textoTranslucido;
	}

	public void setTextoTranslucido(TextoTranslucido tt) {
		this.textoTranslucido = tt;
	}
}
