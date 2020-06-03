package negocio;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Solver {

	private Clique maximaClique;
	private ArrayList<Nodo> listaNodosTienenVecinos;
	Comparator<Nodo> comparador;
	Grafo grafo;

	public Solver(Comparator<Nodo> comparador, Grafo grafo) {
		this.maximaClique = new Clique();
		this.listaNodosTienenVecinos = new ArrayList<Nodo>();
		this.comparador = comparador;
		this.grafo = grafo;

	}

	public Clique resolver() {

		filtrar(grafo);

		ordenarNodosPorPeso(this.listaNodosTienenVecinos);

		for (int i = 0; i < this.listaNodosTienenVecinos.size(); i++) {

			Nodo nodoMasPesado = this.listaNodosTienenVecinos.get(0);
			this.listaNodosTienenVecinos.remove(0);
			maximaClique.agregarNodoAClique(nodoMasPesado, grafo);
			grafo.ordenarVecinos(nodoMasPesado, comparador);

			Nodo vecinoMasPesado = (grafo.obtenerPrimerVecino(nodoMasPesado));
			grafo.ordenarVecinos(vecinoMasPesado, comparador);
			maximaClique.agregarNodoAClique(vecinoMasPesado, grafo);
			this.listaNodosTienenVecinos.remove(vecinoMasPesado);

			ArrayList<Nodo> listaEnComun = interseccion(grafo.obtenerVecinos(nodoMasPesado),
					grafo.obtenerVecinos(vecinoMasPesado));
			BuscarClique(listaEnComun);

		}

		return maximaClique;
	}

	

	private void filtrar(Grafo grafo) {
		for (int i = 0; i < grafo.getListaNodos().size(); i++) {
			if (grafo.tieneVecinos(grafo.getListaNodos().get(i))) {
				this.listaNodosTienenVecinos.add(grafo._listaNodos.get(i));
			}
		}
	}

	private void BuscarClique(ArrayList<Nodo> listaInterseccion) {
		while (!listaInterseccion.isEmpty()) {
			ordenarNodosPorPeso(listaInterseccion);
			maximaClique.agregarNodoAClique(listaInterseccion.get(0), grafo);
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
		return this.maximaClique;
	}

}
