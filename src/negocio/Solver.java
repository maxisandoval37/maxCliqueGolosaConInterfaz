//import java.util.ArrayList;
//import java.util.Collections;
//
//public class Solver {
//
//	Clique maximaClique;
//	int pesoMaximoClique;
//	ComparadorPorPeso mayorPeso;
//	ArrayList<Clique> listaCliques;
//	Clique resultadoFinal;
//	int tamano;
//
//	public Solver() {
//		maximaClique = new Clique();
//		mayorPeso = new ComparadorPorPeso();
//		listaCliques = new ArrayList<Clique>();
//		resultadoFinal = new Clique();
//
//	}
//
//	public Clique resolver(Grafo grafo) {
//		ArrayList<Nodo> nodosOrdporMayorPeso = grafo.getListaNodos();
//		Collections.sort(nodosOrdporMayorPeso, mayorPeso); // Caso de poda 1
//		Collections.reverse(nodosOrdporMayorPeso);
//
//		for (int i = 0; i < nodosOrdporMayorPeso.size(); i++) {
//			this.tamano = 1;
//			Nodo primero = nodosOrdporMayorPeso.get(0);
//			nodosOrdporMayorPeso.remove(0); // Caso de poda 2
//			maximaClique.agregarNodo(primero);
//			Collections.sort(primero.getListaNodos(), mayorPeso);
//			Collections.reverse(primero.getListaVecinos());
//			Nodo segundo = primero.getListaVecinos().get(0);
//			maximaClique.agregarNodo(segundo);
//			ArrayList<Nodo> listaEnComun = Interseccion.interseccion(nodosOrdporMayorPeso.get(i).getListaVecinos(),segundo.getListaVecinos());
//			BuscarClique(listaEnComun,maximaClique);
//		}
//		
//		return resultadoFinal;
//		
//		
//	}
//
//	public void BuscarClique(ArrayList<Nodo> listaInterseccion, Clique cliqueMaxima) {
//		while (!listaInterseccion.isEmpty()) {
//			
//			
//			Collections.sort(listaInterseccion, mayorPeso);
//			Collections.reverse(listaInterseccion);
//			maximaClique.agregarNodo(listaInterseccion.get(0));
//			listaInterseccion.remove(0);
//
//			BuscarClique(Interseccion.interseccion(listaInterseccion, listaInterseccion.get(0).getListaVecinos()),
//					cliqueMaxima);
//
//		}
//
//	}
//
//}
