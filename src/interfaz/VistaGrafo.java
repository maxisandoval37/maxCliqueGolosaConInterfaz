package interfaz;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import negocio.*;
import javax.swing.*;

public class VistaGrafo extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Vector<Nodo> _vectorNodos;
	private Vector<Arista> _vectorAristas;
	private Point p1, p2;
	private int _indiceAuxCrearNodo = -1;
	static int _indice1auxNodo = -1;
	static int _indice2auxNodo = -1;
	private JPanel _panelBotones;
	private JPanel _panelDibujar;
	private JButton _botonResolver;
	private JButton _botonLimpiar;
	public JFrame _ventanaGrafo;
	private Color _colorObjetos;
	public Grafo _grafo;
	private JLabel _lblPesoTotalClique;
	private JLabel _lblGradoClique;
	private JLabel _lblTimepoEmpleadoClique;
	private ProblemaCliqueMaxima _solucionCliqueMax;
	private boolean _interruptorMouse = true;

	public VistaGrafo() {
		initialize();
		_colorObjetos = Color.black;
		_grafo = new Grafo(VistaBienvenida._cantidadNodosLimite);
		this._vectorNodos = new Vector<>();
		this._vectorAristas = new Vector<>();
		this.addMouseListener(this);
		getContentPane().setLayout(null);
		_solucionCliqueMax = new ProblemaCliqueMaxima(_grafo);
	}

	private void initialize() {
		_ventanaGrafo = new JFrame();
		_ventanaGrafo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1000, 800);
		inicializarPanelDibujar();
		inicializarPanelBotones();
		inicializarLabelPesoTotalClique();
		inicializarLabelGradoClique();
		inicializarLabelTiempoEnResolverClique();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Nodo nodo : _vectorNodos) {
			Dibujo.pintarNodo(g,nodo,_colorObjetos);
		}
		for (Arista enlaces : _vectorAristas) {
			Dibujo.pintarEnlance(g, enlaces);
		}
		_colorObjetos = Color.black;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (_interruptorMouse) {
			if (_panelDibujar.getWidth() > e.getX() && _panelDibujar.getHeight() > e.getY()) {
			if (e.getButton() == MouseEvent.BUTTON1) {// nodo
					String inputPesoNodo = JOptionPane.showInputDialog("INGRESE UN PESO PARA EL NODO: ") + "0";
					if (inputPesoNodo != null && sonTodosNumeros(inputPesoNodo)) {
						if (_indiceAuxCrearNodo + 1 < VistaBienvenida._cantidadNodosLimite)
							cargarNodo(e, Integer.parseInt(inputPesoNodo));
						 else
							seExcedioLimiteNodos();
					} else
						JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN DATO NUMERICO PARA EL NODO");
				} 
				if (e.getButton() == MouseEvent.BUTTON3)// arista
					accionClickDerecho(e);
			}
			else
				e.consume();
		}
	}

	private void cargarNodo(MouseEvent e, int pesoNodo) {
		_indiceAuxCrearNodo++;
		Nodo nodo = new Nodo(_indiceAuxCrearNodo, e.getX(), e.getY(), pesoNodo / 10);
		this._vectorNodos.add(nodo);
		_grafo.agregarNodo(nodo);
		repaint();
	}

	private void seExcedioLimiteNodos() {
		JOptionPane.showMessageDialog(null, "TE PASASTE DE LA CANT DE NODOS QUE PODES CREAR");
		resetearGrafo();
	}

	private void accionClickDerecho(MouseEvent e) {
		for (int i = 0; i < _vectorNodos.size(); i++) {

			if (new Rectangle(_vectorNodos.get(i).getX() - Nodo.diametroCirculo / 2,_vectorNodos.get(i).getY() - 
			Nodo.diametroCirculo / 2, Nodo.diametroCirculo, Nodo.diametroCirculo).contains(e.getPoint())) {
				if (p1 == null)
					crearPunto1Arista(i);
				else {
					crearPunto2Arista(i);
					String nombre = JOptionPane.showInputDialog("INGRESE UN NOMBRE PARA LA ARISTA: ");
					if (nombre != null) {
						this._vectorAristas.add(new Arista(p1.x, p1.y, p2.x, p2.y, nombre));
						_indice2auxNodo = _vectorNodos.get(i).getIndiceNodo();
						if (_indice1auxNodo != _indice2auxNodo) {
							agregarAristas(_indice1auxNodo, _indice2auxNodo);
							if (_indice1auxNodo != -1 && _indice2auxNodo != -1) {
								_grafo.agregarNodoAindiceConVecinos(_grafo.getListaNodos().get(_indice1auxNodo),
										_grafo.getListaNodos().get(_indice2auxNodo));
							}
						}p1 = null;p2 = null;
					} else
						JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NOMBRE PARA LA ARISTA");
				}
			}
		}
	}

	private void crearPunto1Arista(int i) {
		p1 = new Point(_vectorNodos.get(i).getX(), _vectorNodos.get(i).getY());
		_indice1auxNodo = _vectorNodos.get(i).getIndiceNodo();
	}

	private void crearPunto2Arista(int i) 
	{p2 = new Point(_vectorNodos.get(i).getX(), _vectorNodos.get(i).getY());}

	private void inicializarPanelBotones() {
		_panelBotones = new JPanel();
		_panelBotones.setBackground(Color.GRAY);
		_panelBotones.setBounds(780, 15, 201, 745);
		getContentPane().add(_panelBotones);
		inicializarBotonResolver();
		inicializarBotonLimpiar();
	}

	private void inicializarPanelDibujar() {
		_panelDibujar = new JPanel();
		_panelDibujar.setBackground(Color.GRAY);
		_panelDibujar.setBounds(10, 15, 700, 745);
		getContentPane().add(_panelDibujar);
	}

	private void inicializarBotonResolver() {
		_panelBotones.setLayout(null);
		_botonResolver = new JButton("Resolver clique max peso");
		_botonResolver.setBounds(15, 5, 180, 23);
		_panelBotones.add(_botonResolver);
		accionBotonResolver();
	}

	private void inicializarBotonLimpiar() {
		_panelBotones.setLayout(null);
		_botonLimpiar = new JButton("Limpiar dibujos y Grafo");
		_botonLimpiar.setBounds(15, 700, 170, 23);
		_panelBotones.add(_botonLimpiar);
		accionBotonLimpiar();
	}

	private void accionBotonLimpiar() {
		_botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearGrafo();
				_interruptorMouse = true;
			}
		});
	}

	private void accionBotonResolver() {
		_botonResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_solucionCliqueMax.hallarCliqueMaxima();
				_vectorNodos.clear();
				_vectorAristas.clear();
				repaint();
				actualizarInfoLabelsClique(_solucionCliqueMax.get_cliqueConMasPeso().getPeso(),
				_solucionCliqueMax.get_cliqueConMasPeso().getGrado(), _solucionCliqueMax.getTiempoFinal());
				repintarObjetos(_solucionCliqueMax.get_cliqueConMasPeso().getListaNodo());

				_solucionCliqueMax = new ProblemaCliqueMaxima(_grafo);
				_interruptorMouse = false;
			}
		});
	}

	private void inicializarLabelPesoTotalClique() {
		_lblPesoTotalClique = new JLabel("PESO TOTAL MAX CLIQUE:");
		_lblPesoTotalClique.setBounds(24, 125, 153, 160);
		_panelBotones.add(_lblPesoTotalClique);
	}

	private void inicializarLabelGradoClique() {
		_lblGradoClique = new JLabel("GRADO DE MAX CLIQUE:");
		_lblGradoClique.setBounds(24, 250, 153, 160);
		_panelBotones.add(_lblGradoClique);
	}

	private void inicializarLabelTiempoEnResolverClique() {
		_lblTimepoEmpleadoClique = new JLabel("TIEMPO EMPLEADO:");
		_lblTimepoEmpleadoClique.setBounds(24, 375, 153, 160);
		_panelBotones.add(_lblTimepoEmpleadoClique);
	}

	private void actualizarInfoLabelsClique(Integer pesoClique, Integer gradoClique, long tiempoEjecu) {
		String infoPeso="<html><body>PESO TOTAL MAX CLIQUE:<center><h1><br>" + pesoClique.toString()+"</br></h1></center></body></html>";
		_lblPesoTotalClique.setText(infoPeso);

		String infoGrado="<html><body>GRADO DE MAX CLIQUE:<center><h1><br>"+gradoClique.toString()+"</br></h1></center></body></html>";
		_lblGradoClique.setText(infoGrado);

		String infoTiempo = "<html><body>TIEMPO EMPLEADO(mili-seg):<center><h1><br>"+Long.toString(tiempoEjecu)+"'</br></h1></center></body></html>";
		_lblTimepoEmpleadoClique.setText(infoTiempo);
	}

	private void repintarObjetos(ArrayList<Nodo> objetos) {
		for (int i = 0; i < objetos.size(); i++) {
			for (int j = 0; j < objetos.size(); j++) {
				_colorObjetos = Color.blue;
				_vectorNodos.add(objetos.get(i));
				_vectorAristas.add(new Arista(objetos.get(i).getX(), objetos.get(i).getY(), 
				objetos.get(j).getX(),objetos.get(j).getY(), ""));
			}
		}repaint();
	}

	private void agregarAristas(int ari1, int ari2) {
		try {
			_grafo.agregarArista(ari1, ari2);
			repaint();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage() + " " + _grafo.capacidadDeAristas());
			resetearGrafo();
		}
	}

	private boolean sonTodosNumeros(String cad) {
		try {
			Integer.parseInt(cad);
			return true;
		} catch (NumberFormatException nfe) 
			{return false;}
	}

	private void resetearGrafo() {
		_vectorNodos.clear();
		_vectorAristas.clear();
		repaint();
		_grafo = new Grafo(VistaBienvenida._cantidadNodosLimite);
		_indice1auxNodo = -1;
		_indice2auxNodo = -1;
		_indiceAuxCrearNodo = -1;
		_solucionCliqueMax = new ProblemaCliqueMaxima(_grafo);
		_lblPesoTotalClique.setText("PESO TOTAL MAX CLIQUE:");
		_lblGradoClique.setText("GRADO DE MAX CLIQUE:");
		_lblTimepoEmpleadoClique.setText("TIEMPO EMPLEADO:");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}