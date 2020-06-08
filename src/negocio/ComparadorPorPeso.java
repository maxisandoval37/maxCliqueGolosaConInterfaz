package negocio;

import java.util.Comparator;

public class ComparadorPorPeso implements Comparator<Nodo> {

	@Override
	public int compare(Nodo uno, Nodo otro) {
		if (uno.getPeso() == otro.getPeso())
			return 0;
		if (uno.getPeso() > otro.getPeso())
			return -1;
		else
			return 1;
	}

}