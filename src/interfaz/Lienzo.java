package interfaz;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import negocio.Enlace;
import negocio.Nodo;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Lienzo extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Vector <Nodo> vectorNodos;
	private Vector <Enlace> vectorEnlaces;
	private Point p1,p2;
	
	public Lienzo() {
		this.vectorNodos = new Vector<>();
		this.vectorEnlaces = new Vector<>();
		this.addMouseListener(this);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Nodo nodo : vectorNodos) {
			Dibujo.pintarNodo(g, nodo);
		}
		for (Enlace enlaces : vectorEnlaces) {
			Dibujo.pintarEnlance(g, enlaces);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.vectorNodos.add(new Nodo(e.getX(),e.getY(),JOptionPane.showInputDialog("INGRESE UN NOMBRE: ")));
			repaint();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			for (Nodo nodo:vectorNodos) {
				if (new Rectangle (nodo.getX()-Nodo.d/2,nodo.getY()-Nodo.d/2,Nodo.d,Nodo.d).contains(e.getPoint())) {
					if (p1==null) {
						p1=new Point (nodo.getX(),nodo.getY());
					}
					else {
						p2=new Point (nodo.getX(),nodo.getY());
						this.vectorEnlaces.add(new Enlace(p1.x,p1.y,p2.x,p2.y,JOptionPane.showInputDialog("INGRESE UN NOMBRE: ")));
						repaint();
						p1 = null;
						p2 = null;
					}
				}
			}
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
