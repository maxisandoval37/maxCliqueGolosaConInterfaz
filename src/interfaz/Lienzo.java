package interfaz;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Vector;
import negocio.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Lienzo extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Vector <Nodo> vectorNodos;
	private Vector <Arista> vectorAristas;
	private Point p1,p2;
	private int indiceAuxCrearNodo=-1;
	static int indice1auxNodo=0;
	static int indice2auxNodo=0;
	
	//private Arista arista;
	public Grafo grafo;
	
	public Lienzo() {
		this.vectorNodos = new Vector<>();
		this.vectorAristas = new Vector<>();
		grafo = new Grafo(20);
		this.addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Nodo nodo : vectorNodos) {
			Dibujo.pintarNodo(g, nodo);
		}
		for (Arista enlaces : vectorAristas) {
			Dibujo.pintarEnlance(g, enlaces);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e){
		
		if (e.getButton() == MouseEvent.BUTTON1) {//nodo
			Integer peso = Integer.parseInt(JOptionPane.showInputDialog("INGRESE UN PESO PARA EL NODO: ")+0);
			indiceAuxCrearNodo++;
			Nodo nodo = new Nodo(indiceAuxCrearNodo,e.getX(),e.getY(),peso/10);
			this.vectorNodos.add(nodo);
			
			grafo.agregarNodo(nodo);
			repaint();
		}

		if (e.getButton() == MouseEvent.BUTTON3) {//arista

				for (int i=0; i<vectorNodos.size();i++) {

				if (new Rectangle (vectorNodos.get(i).getX()-Nodo.diametroCirculo/2,vectorNodos.get(i).getY()-Nodo.diametroCirculo/2,Nodo.diametroCirculo,Nodo.diametroCirculo).contains(e.getPoint())) {
					if (p1==null) {
						p1=new Point (vectorNodos.get(i).getX(),vectorNodos.get(i).getY());
						
						indice1auxNodo=vectorNodos.get(i).getIndiceNodo();
					}
					else {
						p2=new Point (vectorNodos.get(i).getX(),vectorNodos.get(i).getY());
						String nombre = JOptionPane.showInputDialog("INGRESE UN NOMBRE PARA LA ARISTA: ");
						this.vectorAristas.add(new Arista(p1.x,p1.y,p2.x,p2.y,nombre));

						indice2auxNodo=vectorNodos.get(i).getIndiceNodo();
						
						if (indice1auxNodo != indice2auxNodo) { 
							System.out.println(indice1auxNodo+" "+indice2auxNodo);
							grafo.agregarArista(indice1auxNodo, indice2auxNodo);
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

			//System.out.println(grafo.getVecinos(1).toString());
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.add(0);
			hs.add(1);
			hs.add(2);
			System.out.println(grafo.esClique(hs));
			
		}
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
