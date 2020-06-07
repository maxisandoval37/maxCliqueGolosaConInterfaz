package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.Clique;
import negocio.Grafo;
import negocio.Nodo;

class TestClique {
	Clique clique,otraClique;
	Nodo a,b,c,d;
	Grafo grafo;

	@BeforeEach
	 void antesquetodo(){

		grafo=new Grafo(4);
		clique=new Clique();
		otraClique = new Clique();
		
		a=new Nodo(0,10,10,100);
		b=new Nodo(1,20,20,50);
		c=new Nodo(2,30,30,100);
		
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		
		grafo.agregarArista(0, 1);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 0);
		
		grafo.agregarNodoAindiceConVecinos(a, b);
		grafo.agregarNodoAindiceConVecinos(b, c);
		grafo.agregarNodoAindiceConVecinos(a, c);

		clique.agregarNodoAClique(a, grafo);
		clique.agregarNodoAClique(b, grafo);
		clique.agregarNodoAClique(c, grafo);
		
		otraClique.agregarNodoAClique(a, grafo);
		otraClique.agregarNodoAClique(c, grafo);	
	}
	
	@Test
	void testCliquePeso() {
		assertEquals(clique.getPeso(),250);
	}
	
	@Test
	void testNoAgregar() {
		d=new Nodo(3,110,10,20);
		grafo.agregarNodo(d);
		grafo.agregarArista(0, 3);
		grafo.agregarNodoAindiceConVecinos(a, d);
		clique.agregarNodoAClique(d, grafo);
		
		assertEquals(clique.getPeso(), 250);
	}
	
	@Test
	void testListaDeNodos() {
		assertEquals(clique.getListaNodo().size(),3);	
	}
	
	@Test
	void testCliqueConMasPeso() {
		assertEquals(clique.cliqueConMasPeso(otraClique),clique);
	}

}
