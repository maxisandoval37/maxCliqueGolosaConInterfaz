package negocio;

import java.util.Comparator;

public class ComparadorPorGrado implements Comparator<Nodo> {

	@Override
	public int compare(Nodo uno, Nodo otro) {
		if (uno.getCantidadVecinos() == otro.getCantidadVecinos())
			return 0;
		if (uno.getCantidadVecinos() > otro.getCantidadVecinos())
			return -1;
		else
			return 1;
	}

}
