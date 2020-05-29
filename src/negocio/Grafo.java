package negocio;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
	
	public List<Nodo> listaNodos;

	public Grafo() {
		listaNodos = new LinkedList<Nodo>();
	}

	public void agregarNodo(Nodo nodo) {
		if (!(listaNodos.contains(nodo))) {
			listaNodos.add(nodo);
		}
	}
	
	public LinkedList<Nodo> getNodos() {
		return (LinkedList<Nodo>) listaNodos;
	}
	
}
