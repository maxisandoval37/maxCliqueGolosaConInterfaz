package negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	private ArrayList<Set<Integer>> _vecinos;

	private ArrayList<Nodo> listaNodos;
	private int _vertice;

	public Grafo(int vertices){ //cantidad de arista
		_vecinos = new ArrayList<Set<Integer>>(vertices);
		setListaNodos(new ArrayList <Nodo>());

		for (int i = 0; i < vertices; i++)
			_vecinos.add(new HashSet<Integer>());

		_vertice = vertices;
	}

	public void agregarNodo(Nodo nodo) {
		if (!(listaNodos.contains(nodo))) {
			listaNodos.add(nodo);
		}
	}
	
	public void agregarArista(int i, int j) {
		verificarArista(i, j);

		_vecinos.get(i).add(j);
		_vecinos.get(j).add(i);
	}

	public void eliminarArista(int i, int j) {
		verificarArista(i, j);

		_vecinos.get(i).remove(j);
		_vecinos.get(j).remove(i);

	}

	public boolean existeArista(int i, int j) {
		verificarArista(i, j);

		return _vecinos.get(i).contains(j);
	}

	public Set<Integer> getVecinos(int i) {
		verificarVertice(i);

		return _vecinos.get(i);
	}

	public int grado(int i) {
		return _vecinos.get(i).size();
	}

	private void verificarArista(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("no se puede operar con dos aristas iguales");

		verificarVertice(i);

		verificarVertice(j);

	}

	private void verificarVertice(int i) {
		if (i < 0 || i >= _vertice)
			throw new IllegalArgumentException("Se intento usar valores fuera de rango");
	}

	public int cantidadVertices() {
		return _vertice;
	}

	public boolean esClique(Set<Integer> conjunto) {
		if (conjunto == null)
			throw new IllegalArgumentException("El conjunto no puede ser null");

		for (int v : conjunto)
			verificarVertice(v);

		if (conjunto.isEmpty())
			return true;

		for (int v : conjunto)
			for (int otro : conjunto)
				if (v != otro)
					if (existeArista(v, otro) == false)
						return false;

		return true;
	}

	public ArrayList<Nodo> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(ArrayList<Nodo> listaNodos) {
		this.listaNodos = listaNodos;
	}


}
