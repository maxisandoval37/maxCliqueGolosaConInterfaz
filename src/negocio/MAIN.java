package negocio;

import java.util.HashSet;

public class MAIN {

	public static void main(String[] args)
	{
		Grafo g = new Grafo(6);
		
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(1, 3);
		g.agregarArista(0, 3);
		g.agregarArista(3, 4);
		
		
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(0);
		hs.add(1);
		hs.add(3);
		
		System.out.println(g.esClique(hs));
		
		System.out.print("Existe arista 1,0 ");
		System.out.println(g.existeArista(1, 0));
		System.out.print("Existe arista 0,1 ");
		System.out.println(g.existeArista(0, 1));

	}

}
