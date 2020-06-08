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

	public JFrame _ventanaBienvenida;
	private JFrame _ventanaGrafo;
	private JButton _btnEnviar;
	private JLabel _lblFondo;
	private JTextField _tFCantNodos;
	private TextoTranslucido _textoTranslucido;
	static Integer _cantidadNodosLimite;

	public VistaBienvenida() {
		initialize();
		inicializarTextfieldCantNodosACrear();
		inicializarBotonEnviar();
		asignarFondo();
	}

	private void initialize() {
		_ventanaBienvenida = new JFrame();
		_ventanaBienvenida.setBounds(300, 100, 1000, 800);
		_ventanaBienvenida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_ventanaBienvenida.getContentPane().setLayout(null);
	}

	private void inicializarTextfieldCantNodosACrear() {
		_tFCantNodos = new JTextField();
		setTextoTranslucido(new TextoTranslucido("NUMERO MAX DE NODOS A CREAR", _tFCantNodos));
		_tFCantNodos.setBounds(347, 515, 343, 52);
		_ventanaBienvenida.getContentPane().add(_tFCantNodos);
		_tFCantNodos.setColumns(10);
		limitarInputUsuario(_tFCantNodos);
	}

	private void inicializarBotonEnviar() {
		_btnEnviar = new JButton("ENVIAR");
		_btnEnviar.setBounds(443, 624, 164, 52);
		_ventanaBienvenida.getContentPane().add(_btnEnviar);
		accionBotonEnviar();
	}

	private void accionBotonEnviar() {
		_btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!_tFCantNodos.getText().toString().equals("") && !_tFCantNodos.getText().toString().equals("1") && !_tFCantNodos.getText().toString().equals("0")) {
					_cantidadNodosLimite = Integer.parseInt(_tFCantNodos.getText().toString());
					cambiarVentana();
				} else
					JOptionPane.showMessageDialog(null, "INGRESE UN VALOR NUMERICO >1 PARA CONTINUAR");
			}
		});
	}

	private void asignarFondo() {
		_lblFondo = new JLabel("");
		_lblFondo.setBounds(0, 0, 1000, 800);
		_ventanaBienvenida.getContentPane().add(_lblFondo);

		_lblFondo.setIcon(new ImageIcon(VistaBienvenida.class.getResource("/interfaz/INICIO.png")));
	}

	private void cambiarVentana() {
		_ventanaGrafo = new VistaGrafo();
		_ventanaBienvenida.setVisible(false);
		_ventanaGrafo.setVisible(true);
		_ventanaGrafo.setResizable(false);
	}

	private void limitarInputUsuario(JTextField tf) {
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() < '0') || (e.getKeyChar() > '9') && (e.getKeyChar() != '\b')) {
					e.consume();
				} else {
					_btnEnviar.setEnabled(true);
					if (tf.getText().length() >= 7)
						e.consume();
				}
			}
		});
	}

	public TextoTranslucido getTp() {
		return _textoTranslucido;
	}

	public void setTextoTranslucido(TextoTranslucido tt) {
		this._textoTranslucido = tt;
	}
}
