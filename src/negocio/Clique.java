package negocio;

import java.util.ArrayList;

public class Clique {

	ArrayList<Nodo> _listaNodo;
	private int _peso;

	public Clique() {
		_listaNodo = new ArrayList<Nodo>();
		this.setPeso(0);

	}

	public void agregarNodoAClique(Nodo nodo, Grafo grafo) {

		if (!_listaNodo.contains(nodo) && esVecinodeTodos(nodo, grafo)) {
			_listaNodo.add(nodo);
			this.setPeso(this.getPeso() + nodo.getPeso());
		}
	}

	private boolean esVecinodeTodos(Nodo nodo, Grafo grafo) {
		boolean ret = true;
		for (Nodo iteracion : _listaNodo) {
			ret = ret && grafo.getindiceConVecinos().get(iteracion.getIndiceNodo()).contains(nodo);
		}
		return ret;
	}

	public ArrayList<Nodo> getListaNodo() {
		return _listaNodo;
	}

	public int getPeso() {
		return _peso;
	}

	public void setPeso(int peso) {
		this._peso = peso;
	}

}
