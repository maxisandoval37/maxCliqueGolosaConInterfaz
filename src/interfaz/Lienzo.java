package interfaz;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import negocio.*;
import javax.swing.*;

public class Lienzo extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	public JFrame ventana;
	private Vector<Nodo> vectorNodos;
	private Vector<Arista> vectorAristas;
	private Point p1, p2;
	private int indiceAuxCrearNodo = -1;
	static int indice1auxNodo = -1;
	static int indice2auxNodo = -1;
	private JPanel panelBotones;
	private JPanel panelDibujar;
	private JButton botonResolver;
	ComparadorPorPeso comparaPorPeso;
	ComparadorPorGrado comparaPorGrado;
	private Color colorNodos;
	public Grafo grafo;

	public Lienzo() {
		initialize();
		
		colorNodos = Color.black;
		comparaPorPeso = new ComparadorPorPeso();
		comparaPorGrado = new ComparadorPorGrado();
		grafo = new Grafo(20);
		this.vectorNodos = new Vector<>();
		this.vectorAristas = new Vector<>();
		this.addMouseListener(this);
		getContentPane().setLayout(null);
	}
	
	private void initialize() {
		ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1000, 800);
		ventana.getContentPane().setLayout(null);
		inicializarPanelDibujar();
		inicializarPanelBotones();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Nodo nodo : vectorNodos) {
			Dibujo.pintarNodo(g, nodo, panelDibujar.getWidth(), panelDibujar.getHeight(), colorNodos);
		}
		for (Arista enlaces : vectorAristas) {
			Dibujo.pintarEnlance(g, enlaces);
		}
		colorNodos = Color.black;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {// nodo
			String peso = JOptionPane.showInputDialog("INGRESE UN PESO PARA EL NODO: ") + "0";
			if (peso != null && sonTodosNumeros(peso)) {
				indiceAuxCrearNodo++;
				Nodo nodo = new Nodo(indiceAuxCrearNodo, e.getX(), e.getY(), Integer.parseInt(peso) / 10);
				this.vectorNodos.add(nodo);

				grafo.agregarNodo(nodo);
				repaint();
			} else {
				JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN DATO NUMERICO PARA EL NODO");
			}
		}

		if (e.getButton() == MouseEvent.BUTTON3) {// arista
			for (int i = 0; i < vectorNodos.size(); i++) {

				if (new Rectangle(vectorNodos.get(i).getX() - Nodo.diametroCirculo / 2,
						vectorNodos.get(i).getY() - Nodo.diametroCirculo / 2, Nodo.diametroCirculo,
						Nodo.diametroCirculo).contains(e.getPoint())) {
					if (p1 == null) {
						p1 = new Point(vectorNodos.get(i).getX(), vectorNodos.get(i).getY());
						indice1auxNodo = vectorNodos.get(i).getIndiceNodo();
					} else {
						p2 = new Point(vectorNodos.get(i).getX(), vectorNodos.get(i).getY());
						String nombre = JOptionPane.showInputDialog("INGRESE UN NOMBRE PARA LA ARISTA: ");
						if (nombre != null) {
							this.vectorAristas.add(new Arista(p1.x, p1.y, p2.x, p2.y, nombre));
							indice2auxNodo = vectorNodos.get(i).getIndiceNodo();

							if (indice1auxNodo != indice2auxNodo) {
								grafo.agregarArista(indice1auxNodo, indice2auxNodo);
								if (indice1auxNodo != -1 && indice2auxNodo != -1
									&& indice1auxNodo <= grafo.getListaNodos().size()
									&& indice2auxNodo <= grafo.getListaNodos().size()) {

									grafo.agregarNodoAindiceConVecinos(grafo.getListaNodos().get(indice1auxNodo),
									grafo.getListaNodos().get(indice2auxNodo));
								}
							}
							repaint();
							p1 = null;
							p2 = null;
						} else {
							JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NOMBRE PARA LA ARISTA");
						}
					}
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
		botonResolver = new JButton("Resolver clique max peso");
		botonResolver.setBounds(500, 500, 100, 30);
		panelBotones.add(botonResolver);
		accionBotonResolver();
	}

	private void accionBotonResolver() {
		botonResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Solver solver = new Solver(comparaPorPeso,grafo);
				Clique clique = solver.resolver();
				limpiarElemsDeLaPantalla();
				repaint();
	
				for (int i=0;i<clique.getListaNodo().size()-1;i++) {
					colorNodos = Color.blue;
					vectorNodos.add(clique.getListaNodo().get(i));
					vectorNodos.add(clique.getListaNodo().get(clique.getListaNodo().size()-1));
					
					vectorAristas.add(new Arista(clique.getListaNodo().get(i).getX(),clique.getListaNodo().get(i).getY(), 
					clique.getListaNodo().get(i+1).getX(),clique.getListaNodo().get(i+1).getY(), ""));
					
					vectorAristas.add(new Arista(clique.getListaNodo().get(0).getX(),clique.getListaNodo().get(0).getY(), 
					clique.getListaNodo().get(clique.getListaNodo().size()-1).getX(),
					clique.getListaNodo().get(clique.getListaNodo().size()-1).getY(), ""));
				}repaint();
			}
		});
	}
	
	private void limpiarElemsDeLaPantalla() {//VER SI HAY QUE LIMPIAR OTRA COSA TAMBIEN
		vectorNodos.clear();
		vectorAristas.clear();
	}
	
	private boolean sonTodosNumeros(String cad) {
		try {
			Integer.parseInt(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
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