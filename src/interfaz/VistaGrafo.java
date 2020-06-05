package interfaz;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import negocio.*;
import javax.swing.*;

public class VistaGrafo extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<Nodo> _vectorNodos;
	private Vector<Arista> _vectorAristas;
	private Point p1, p2;
	private int _indiceAuxCrearNodo = -1;
	static int indice1auxNodo = -1;
	static int indice2auxNodo = -1;
	private JPanel panelBotones;
	private JPanel panelDibujar;
	private JButton botonResolver;
	public JFrame ventanaGrafo;
	ComparadorPorPeso comparaPorPeso;
	ComparadorPorGrado comparaPorGrado;
	private Color colorNodos;
	public Grafo _grafo;
	private JLabel _lblPesoTotalClique;
	private JLabel _lblGradoClique;
	private JLabel _lblTimepoEmpleadoClique;

	public VistaGrafo() {
		initialize();
		
		comparaPorPeso = new ComparadorPorPeso();
		comparaPorGrado = new ComparadorPorGrado();
		colorNodos = Color.black;
		_grafo = new Grafo(VistaBienvenida.cantidadNodosLimite);
		this._vectorNodos = new Vector<>();
		this._vectorAristas = new Vector<>();
		this.addMouseListener(this);
		getContentPane().setLayout(null);
	}
	
	private void initialize() {
		ventanaGrafo = new JFrame();
		ventanaGrafo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			Dibujo.pintarNodo(g, nodo, panelDibujar.getWidth(), panelDibujar.getHeight(), colorNodos);
		}
		for (Arista enlaces : _vectorAristas) {
			Dibujo.pintarEnlance(g, enlaces);
		}
		colorNodos = Color.black;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {// nodo
			String inputPesoNodo = JOptionPane.showInputDialog("INGRESE UN PESO PARA EL NODO: ") + "0";
			if (inputPesoNodo != null && sonTodosNumeros(inputPesoNodo)) {
				if (_indiceAuxCrearNodo+1 < VistaBienvenida.cantidadNodosLimite) {
					cargarNodo(e, Integer.parseInt(inputPesoNodo));
				} else
					seExcedioLimiteNodos();
			} else
				JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN DATO NUMERICO PARA EL NODO");
		}

		if (e.getButton() == MouseEvent.BUTTON3)// arista
			accionClickDerecho(e);
	}
	
	private void cargarNodo(MouseEvent e,int pesoNodo) {
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
			
			if (new Rectangle(_vectorNodos.get(i).getX() - Nodo.diametroCirculo / 2,
					_vectorNodos.get(i).getY() - Nodo.diametroCirculo / 2, Nodo.diametroCirculo,
					Nodo.diametroCirculo).contains(e.getPoint())) {
				if (p1 == null) {
					p1 = new Point(_vectorNodos.get(i).getX(), _vectorNodos.get(i).getY());
					indice1auxNodo = _vectorNodos.get(i).getIndiceNodo();
				} else {
					p2 = new Point(_vectorNodos.get(i).getX(), _vectorNodos.get(i).getY());
					String nombre = JOptionPane.showInputDialog("INGRESE UN NOMBRE PARA LA ARISTA: ");
					if (nombre != null) {
						this._vectorAristas.add(new Arista(p1.x, p1.y, p2.x, p2.y, nombre));
						indice2auxNodo = _vectorNodos.get(i).getIndiceNodo();
						if (indice1auxNodo != indice2auxNodo) {
							agregarAristas(indice1auxNodo,indice2auxNodo);
							if (indice1auxNodo != -1 && indice2auxNodo != -1
								&& indice1auxNodo <= _grafo.getListaNodos().size()
								&& indice2auxNodo <= _grafo.getListaNodos().size()) {
								_grafo.agregarNodoAindiceConVecinos(_grafo.getListaNodos().get(indice1auxNodo),
								_grafo.getListaNodos().get(indice2auxNodo));
							}
						}
						p1 = null;p2 = null;
					} else 
						JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NOMBRE PARA LA ARISTA");
				}
			}
		}
	}
	
	private void inicializarPanelBotones() {
		panelBotones= new JPanel();
		panelBotones.setBackground(Color.GRAY);
		panelBotones.setBounds(780, 15, 201, 745);
		getContentPane().add(panelBotones);
		inicializarBotonResolver();
	}
	
	private void inicializarPanelDibujar() {
		panelDibujar = new JPanel();
		panelDibujar.setBackground(Color.GRAY);
		panelDibujar.setBounds(10, 15, 700, 745);
		getContentPane().add(panelDibujar);
	}
	private void inicializarBotonResolver() {
		panelBotones.setLayout(null);
		botonResolver = new JButton("Resolver clique max peso");
		botonResolver.setBounds(24, 5, 153, 23);
		panelBotones.add(botonResolver);
		accionBotonResolver();
	}

	private void accionBotonResolver() {
		botonResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Solver solver = new Solver(comparaPorPeso,_grafo);
				long startTime = System.nanoTime();
				Clique clique = solver.resolver();
				long endTime = System.nanoTime()-startTime;
				endTime=TimeUnit.MILLISECONDS.convert(endTime, TimeUnit.NANOSECONDS);
				_vectorNodos.clear();
				_vectorAristas.clear();
				repaint();
				actualizarInfoLabelsClique(clique.getPeso(),clique.getListaNodo().size(),endTime);
				repintarObjetos (clique.getListaNodo());
			}
		});
	}
	
	private void inicializarLabelPesoTotalClique() {
			_lblPesoTotalClique = new JLabel("PESO TOTAL MAX CLIQUE:");
			_lblPesoTotalClique.setBounds(24, 125, 153, 160);
			panelBotones.add(_lblPesoTotalClique);
	}
	
	private void inicializarLabelGradoClique() {
		_lblGradoClique = new JLabel("GRADO DE MAX CLIQUE:");
		_lblGradoClique.setBounds(24, 250, 153, 160);
		panelBotones.add(_lblGradoClique);
	}
	
	private void inicializarLabelTiempoEnResolverClique() {
		_lblTimepoEmpleadoClique = new JLabel("TIEMPO EMPLEADO:");
		_lblTimepoEmpleadoClique.setBounds(24, 375, 153, 160);
		panelBotones.add(_lblTimepoEmpleadoClique);
	}
	
	private void actualizarInfoLabelsClique(Integer pesoClique,Integer gradoClique,long tiempoEjecu) {
		String infoPeso="<html><body>PESO TOTAL MAX CLIQUE:<center><h1><br>"+pesoClique.toString()+"</br></h1></center></body></html>";
		_lblPesoTotalClique.setText(infoPeso);
		
		String infoGrado="<html><body>GRADO DE MAX CLIQUE:<center><h1><br>"+gradoClique.toString()+"</br></h1></center></body></html>";
		_lblGradoClique.setText(infoGrado);
		
		String infoTiempo="<html><body>TIEMPO EMPLEADO(mili-seg):<center><h1><br>"+Long.toString(tiempoEjecu)+"'</br></h1></center></body></html>";
		_lblTimepoEmpleadoClique.setText(infoTiempo);
	}

	private void repintarObjetos(ArrayList<Nodo> objetos) {
		for (int i = 0; i < objetos.size() - 1; i++) {
			colorNodos = Color.blue;
			_vectorNodos.add(objetos.get(i));
			_vectorNodos.add(objetos.get(objetos.size() - 1));

			_vectorAristas.add(new Arista(objetos.get(i).getX(), objetos.get(i).getY(), objetos.get(i + 1).getX(),
			objetos.get(i + 1).getY(), ""));

			_vectorAristas.add(new Arista(objetos.get(0).getX(), objetos.get(0).getY(),
			objetos.get(objetos.size() - 1).getX(), objetos.get(objetos.size() - 1).getY(), ""));
		}repaint();
	}
	
	private void agregarAristas(int ari1, int ari2) {
		try {
			_grafo.agregarArista(ari1, ari2);
			repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+" "+_grafo.capacidadDeAristas());
			resetearGrafo();
		}
	}

	private boolean sonTodosNumeros(String cad) {
		try {
			Integer.parseInt(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private void resetearGrafo() {
		_vectorNodos.clear();
		_vectorAristas.clear();
		repaint();
		_grafo = new Grafo(VistaBienvenida.cantidadNodosLimite);
		//grafo.resetearGrafo();
		indice1auxNodo=-1;
		indice2auxNodo=-1;
		_indiceAuxCrearNodo=-1;
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