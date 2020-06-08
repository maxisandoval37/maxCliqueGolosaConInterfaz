package interfaz;

import negocio.Arista;
import negocio.Nodo;

import java.awt.Color;
import java.awt.Graphics;

public class Dibujo {

	public static void pintarEnlance(Graphics g, Arista e) {
		g.drawLine(e.getX1(), e.getY1(), e.getX2(), e.getY2());

		if (e.getX1() > e.getX2() && e.getY1() > e.getY2()) {
			g.drawString(e.getNombre(), e.getX1() - Math.abs((e.getX1() - e.getX2()) / 2),
					e.getY1() - Math.abs((e.getY1() - e.getY2()) / 2));
		}
		if (e.getX1() < e.getX2() && e.getY1() < e.getY2()) {
			g.drawString(e.getNombre(), e.getX2() - Math.abs((e.getX1() - e.getX2()) / 2),
					e.getY2() - Math.abs((e.getY1() - e.getY2()) / 2));
		}

		if (e.getX1() > e.getX2() && e.getY1() < e.getY2()) {
			g.drawString(e.getNombre(), e.getX1() - Math.abs((e.getX1() - e.getX2()) / 2),
					e.getY2() - Math.abs((e.getY1() - e.getY2()) / 2));
		}

		if (e.getX1() < e.getX2() && e.getY1() > e.getY2()) {
			g.drawString(e.getNombre(), e.getX2() - Math.abs((e.getX1() - e.getX2()) / 2),
					e.getY1() - Math.abs((e.getY1() - e.getY2()) / 2));
		}
	}

	public static void pintarNodo(Graphics g, Nodo n,Color color) {
			g.setColor(color);
			g.drawOval(n.getX() - Nodo.diametroCirculo / 2, n.getY() - Nodo.diametroCirculo / 2, 
			Nodo.diametroCirculo,Nodo.diametroCirculo);

			if (n.getPeso() == null)
				g.drawString("", n.getX(), n.getY());
			else
				g.drawString("P(" + n.getPeso() + ") // i:(" + n.getIndiceNodo() + ")",
				n.getX() - Nodo.diametroCirculo / 3, n.getY());
		}
	

}
