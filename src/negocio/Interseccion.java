package negocio;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Interseccion {

    public static ArrayList<Nodo> interseccion (ArrayList<Nodo> arrayList,ArrayList<Nodo> arrayList2) {
        ArrayList<Nodo> intersect = (ArrayList<Nodo>) arrayList.stream().filter(arrayList2::contains).collect(Collectors.toList());
        return intersect;
    }


}