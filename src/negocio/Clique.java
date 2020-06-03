package negocio;

import java.util.ArrayList;

public class Clique {

	ArrayList<Nodo> listaNodo;
	private int peso;

	public Clique() {
		listaNodo = new ArrayList<Nodo>();
		this.setPeso(0);

	}

	public void agregarNodoAClique(Nodo nodo, Grafo grafo) {

		if (!listaNodo.contains(nodo) && esVecinodeTodos(nodo, grafo)) {
			listaNodo.add(nodo);
			this.setPeso(this.getPeso() + nodo.getPeso());
		}
	}

	private boolean esVecinodeTodos(Nodo nodo, Grafo grafo) {
		boolean ret = true;
		for (Nodo iteracion : listaNodo) {
			ret = ret && grafo.getindiceConVecinos().get(iteracion.getIndiceNodo()).contains(nodo);
		}
		return ret;
	}

	public ArrayList<Nodo> getListaNodo() {
		return listaNodo;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

}
