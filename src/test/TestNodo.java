package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.Grafo;
import negocio.Nodo;

class TestNodo {
	
	Nodo a,b;
	Grafo grafo;
	
	@BeforeEach
	void antesquetodo() {
		grafo=new Grafo(2);
		
		a=new Nodo(0,10,10,100);
		b=new Nodo(1,20,20,50);
		
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarArista(0, 1);
		grafo.agregarNodoAindiceConVecinos(a, b);
	}

	@Test
	void testVerificarCantidadDeVecinosA() {
		assertEquals(a.getCantidadVecinos(),1);
	}
	
	
	@Test
	void testVerificarCantidadDeVecinosB() {
		assertEquals(b.getCantidadVecinos(),1);
	}

}
