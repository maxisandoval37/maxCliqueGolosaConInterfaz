package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Vector;
import negocio.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private JButton botonResolver;
	ComparadorPorPeso comparaPorPeso;
	ComparadorPorGrado comparaPorGrado;

	public Grafo grafo;

	public Lienzo() {

		initialize();
		
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
		inicializarPanelBotones();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Nodo nodo : vectorNodos) {
			Dibujo.pintarNodo(g, nodo);
		}
		for (Arista enlaces : vectorAristas) {
			Dibujo.pintarEnlance(g, enlaces);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {// nodo
			Integer peso = Integer.parseInt(JOptionPane.showInputDialog("INGRESE UN PESO PARA EL NODO: ") + 0);
			indiceAuxCrearNodo++;
			Nodo nodo = new Nodo(indiceAuxCrearNodo, e.getX(), e.getY(), peso / 10);
			this.vectorNodos.add(nodo);

			grafo.agregarNodo(nodo);
			repaint();
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
						this.vectorAristas.add(new Arista(p1.x, p1.y, p2.x, p2.y, nombre));

						indice2auxNodo = vectorNodos.get(i).getIndiceNodo();

						if (indice1auxNodo != indice2auxNodo) {
							System.out.println(indice1auxNodo + " " + indice2auxNodo);
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
					}
				}
			}

		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			System.out.println("boton 2");

			Solver solver = new Solver(comparaPorPeso,grafo);
			Solver solver2 = new Solver(comparaPorGrado,grafo);
			
			
			Clique clique = solver.resolver();
			Clique clique2 = solver2.resolver();
			Clique aux;
			
			
			if (clique.getPeso()>clique2.getPeso()) {
				 aux = clique;
			} else {
				aux = clique2;
				
			}
			
			

			for (Nodo cli : aux.getListaNodo()) {
				System.out.println(cli.getIndiceNodo() + " <-");
			}


		}

	}

	private void inicializarPanelBotones() {
		panelBotones= new JPanel();
		panelBotones.setBackground(Color.GRAY);
		panelBotones.setBounds(680, 11, 201, 362);
		getContentPane().add(panelBotones);
		inicializarBotonResolver();
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

				for (Nodo cli : clique.getListaNodo()) {
					System.out.println(cli.getIndiceNodo() + " <-");
				}

			}
		});
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
