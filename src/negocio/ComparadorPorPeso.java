package negocio;

import java.util.Comparator;

public class ComparadorPorPeso implements Comparator<Nodo>{

    @Override
    public int compare (Nodo uno , Nodo otro) {
        return -uno.getPeso() + otro.getPeso();
    }

}