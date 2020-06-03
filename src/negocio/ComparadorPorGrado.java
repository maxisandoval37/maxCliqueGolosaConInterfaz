package negocio;

import java.util.Comparator;

public class ComparadorPorGrado implements Comparator<Nodo> {

	@Override
	public int compare(Nodo uno, Nodo otro) {
		return -uno.getCantidadVecinos()+otro.getCantidadVecinos();
	}

}
