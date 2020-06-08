
package negocio;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SolverGoloso {

	private Clique _maximaClique;
	private ArrayList<Nodo> _listaNodosTienenVecinos;
	Comparator<Nodo> _comparador;
	Grafo _grafo;
	private Long _tiempoComienzo;
	Long _tiempoTardado;

	public SolverGoloso(Comparator<Nodo> comparador, Grafo grafo) {
		this._maximaClique = new Clique();
		this._listaNodosTienenVecinos = new ArrayList<Nodo>();
		this._comparador = comparador;
		this._grafo = grafo;
		this._tiempoTardado = (long) 0;
		this._tiempoComienzo = (long) 0;
	}

	public Clique resolver() {
		comenzarContarTiempo();
		filtrar();
		ordenarNodosPorPeso(this._listaNodosTienenVecinos);
		for (int i = 0; i < this._listaNodosTienenVecinos.size(); i++) {

			Nodo nodoMasPesado = this._listaNodosTienenVecinos.get(0);
			this._listaNodosTienenVecinos.remove(0);
			_maximaClique.agregarNodoAClique(nodoMasPesado, _grafo);
			_grafo.ordenarVecinos(nodoMasPesado, _comparador);
			Nodo vecinoMasPesado = (_grafo.obtenerPrimerVecino(nodoMasPesado));
			_grafo.ordenarVecinos(vecinoMasPesado, _comparador);
			_maximaClique.agregarNodoAClique(vecinoMasPesado, _grafo);
			this._listaNodosTienenVecinos.remove(vecinoMasPesado);
			ArrayList<Nodo> listaEnComun = interseccion(_grafo.obtenerVecinos(nodoMasPesado),
					_grafo.obtenerVecinos(vecinoMasPesado));
			BuscarClique(listaEnComun);
		}
		terminarContarTiempo();

		return _maximaClique;
	}

	private void BuscarClique(ArrayList<Nodo> listaInterseccion) {
		while (!listaInterseccion.isEmpty()) {
			ordenarNodosPorPeso(listaInterseccion);
			_maximaClique.agregarNodoAClique(listaInterseccion.get(0), _grafo);
			listaInterseccion.remove(0);
			if (listaInterseccion.isEmpty()) {
				return;
			} else {
				ArrayList<Nodo> nueva = interseccion(listaInterseccion,
				_grafo.obtenerVecinos(listaInterseccion.get(0)));
				BuscarClique(nueva);
			}
		}
	}

	private void filtrar() {
		for (int i = 0; i < this._grafo.getListaNodos().size(); i++) {
			if (this._grafo.tieneVecinos(this._grafo.getListaNodos().get(i))) {
				this._listaNodosTienenVecinos.add(this._grafo._listaNodos.get(i));
			}
		}
	}

	private ArrayList<Nodo> interseccion(ArrayList<Nodo> arrayList, ArrayList<Nodo> arrayList2) {
		ArrayList<Nodo> listaInter = (ArrayList<Nodo>) arrayList.stream().filter(arrayList2::contains)
				.collect(Collectors.toList());
		return listaInter;
	}

	private void comenzarContarTiempo() {
		this._tiempoComienzo = System.nanoTime();
	}

	private void terminarContarTiempo() {
		this._tiempoTardado = System.nanoTime() - this._tiempoComienzo;
		this._tiempoTardado = TimeUnit.MILLISECONDS.convert(_tiempoTardado, TimeUnit.NANOSECONDS);

	}

	private void ordenarNodosPorPeso(ArrayList<Nodo> lista) {
		Collections.sort(lista, _comparador);
	}

	public Clique getClique() {
		return this._maximaClique;
	}

	public Long getTiempoTardado() {
		return this._tiempoTardado;
	}

}
