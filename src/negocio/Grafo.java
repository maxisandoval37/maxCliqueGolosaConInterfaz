package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	private ArrayList<Set<Integer>> _vecinos;
	public ArrayList<Nodo> _listaNodos;
	private HashMap<Integer, ArrayList<Nodo>> _indiceConVecinos;
	private int _capacidadAristas;
	private int _cantArisActuales;

	public Grafo(int vertices) {
		_capacidadAristas = (vertices * (vertices - 1)) / 2;

		_vecinos = new ArrayList<Set<Integer>>(_capacidadAristas);
		_indiceConVecinos = new HashMap<Integer, ArrayList<Nodo>>();
		_listaNodos = new ArrayList<Nodo>();
		setListaNodos(new ArrayList<Nodo>());

		for (int i = 0; i <= _capacidadAristas; i++)
			_vecinos.add(new HashSet<Integer>());
	}

	public void agregarNodo(Nodo nodo) {
		if (!(_listaNodos.contains(nodo))) {
			_listaNodos.add(nodo);
		}
	}

	public void agregarNodoAindiceConVecinos(Nodo nodo1, Nodo nodo2) {
		if (!getindiceConVecinos().containsKey(nodo1.getIndiceNodo())) {
			agregarNodoAuxI(nodo1, nodo2);
		} else {
			if (!obtenerVecinos(nodo1).contains(nodo2))
				agregarNodoAuxE(nodo1, nodo2);
		}

		if (!getindiceConVecinos().containsKey(nodo2.getIndiceNodo())) {
			agregarNodoAuxI(nodo2, nodo1);

		} else {
			if (!obtenerVecinos(nodo2).contains(nodo1))
				agregarNodoAuxE(nodo2, nodo1);
		}
	}

	private void agregarNodoAuxI(Nodo a, Nodo b) {
		ArrayList<Nodo> auxiliar = new ArrayList<Nodo>();
		auxiliar.add(b);
		this._indiceConVecinos.put(a.getIndiceNodo(), auxiliar);
		a.aumentarCantVecinos();
	}

	private void agregarNodoAuxE(Nodo a, Nodo b) {
		ArrayList<Nodo> auxiliar = new ArrayList<Nodo>();
		auxiliar.addAll(getindiceConVecinos().get(a.getIndiceNodo()));
		auxiliar.add(b);
		this._indiceConVecinos.put(a.getIndiceNodo(), auxiliar);
		a.aumentarCantVecinos();

	}

	public void agregarArista(int i, int j) {
		verificarArista(i, j);
		_cantArisActuales++;
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

	private void verificarVertice(int i) {
		if (i < 0 || i > _capacidadAristas)
			throw new IllegalArgumentException("Se intento usar valores fuera de rango");
	}

	private void verificarArista(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("no se puede operar con dos aristas iguales");

		verificarVertice(i);
		verificarVertice(j);
	}

	public int capacidadDeAristas() {
		return _capacidadAristas;
	}

	public int cantidadAristasActuales() {
		return _cantArisActuales;
	}

	public ArrayList<Nodo> getListaNodos() {
		return _listaNodos;
	}

	public void setListaNodos(ArrayList<Nodo> listaNodos) {
		this._listaNodos = listaNodos;
	}

	public HashMap<Integer, ArrayList<Nodo>> getindiceConVecinos() {
		return this._indiceConVecinos;
	}

	public boolean tieneVecinos(Nodo nodo) {
		return getindiceConVecinos().containsKey(nodo.getIndiceNodo());
	}

	public ArrayList<Nodo> obtenerVecinos(Nodo nodo) {
		return getindiceConVecinos().get(nodo.getIndiceNodo());
	}

	public void ordenarVecinos(Nodo nodo, Comparator<Nodo> comparador) {
		if (tieneVecinos(nodo))
			Collections.sort(getindiceConVecinos().get(nodo.getIndiceNodo()), comparador);
	}

	public Nodo obtenerPrimerVecino(Nodo nodo) {
		if (tieneVecinos(nodo)) {
			return getindiceConVecinos().get(nodo.getIndiceNodo()).get(0);
		}
		return null;
	}

}