
package negocio;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Solver {

	private Clique _maximaClique;
	private ArrayList<Nodo> _listaNodosTienenVecinos;
	Comparator<Nodo> comparador;
	Grafo grafo;

	public Solver(Comparator<Nodo> comparador, Grafo grafo) {
		this._maximaClique = new Clique();
		this._listaNodosTienenVecinos = new ArrayList<Nodo>();
		this.comparador = comparador;
		this.grafo = grafo;

	}

	public Clique resolver() {

		filtrar(grafo);

		ordenarNodosPorPeso(this._listaNodosTienenVecinos);

		for (int i = 0; i < this._listaNodosTienenVecinos.size(); i++) {

			Nodo nodoMasPesado = this._listaNodosTienenVecinos.get(0);
			this._listaNodosTienenVecinos.remove(0);
			_maximaClique.agregarNodoAClique(nodoMasPesado, grafo);
			grafo.ordenarVecinos(nodoMasPesado, comparador);

			Nodo vecinoMasPesado = (grafo.obtenerPrimerVecino(nodoMasPesado));
			grafo.ordenarVecinos(vecinoMasPesado, comparador);
			_maximaClique.agregarNodoAClique(vecinoMasPesado, grafo);
			this._listaNodosTienenVecinos.remove(vecinoMasPesado);

			ArrayList<Nodo> listaEnComun = interseccion(grafo.obtenerVecinos(nodoMasPesado),
					grafo.obtenerVecinos(vecinoMasPesado));
			BuscarClique(listaEnComun);

		}

		return _maximaClique;
	}

	

	private void filtrar(Grafo grafo) {
		for (int i = 0; i < grafo.getListaNodos().size(); i++) {
			if (grafo.tieneVecinos(grafo.getListaNodos().get(i))) {
				this._listaNodosTienenVecinos.add(grafo._listaNodos.get(i));
			}
		}
	}

	private void BuscarClique(ArrayList<Nodo> listaInterseccion) {
		while (!listaInterseccion.isEmpty()) {
			ordenarNodosPorPeso(listaInterseccion);
			_maximaClique.agregarNodoAClique(listaInterseccion.get(0), grafo);
			listaInterseccion.remove(0);
			if (listaInterseccion.isEmpty()) {
				return;
			} else {
				ArrayList<Nodo> nueva = interseccion(listaInterseccion, grafo.obtenerVecinos(listaInterseccion.get(0)));
				BuscarClique(nueva);
			}
		}
	}

	private ArrayList<Nodo> interseccion(ArrayList<Nodo> arrayList, ArrayList<Nodo> arrayList2) {
		ArrayList<Nodo> listaInter = (ArrayList<Nodo>) arrayList.stream().filter(arrayList2::contains)
				.collect(Collectors.toList());
		return listaInter;
	}
	
	private void ordenarNodosPorPeso(ArrayList<Nodo> lista) {
		Collections.sort(lista, comparador);
	}

	public Clique getClique() {
		return this._maximaClique;
	}

}
