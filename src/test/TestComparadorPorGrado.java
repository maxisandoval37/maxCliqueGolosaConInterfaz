package test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import negocio.Nodo;
import negocio.ComparadorPorGrado;
class TestComparadorPorGrado {
	
	Nodo a,b;
	ComparadorPorGrado comparador;
	
	@BeforeEach
	 void antesquetodo(){
		comparador=new ComparadorPorGrado();
		a=new Nodo(0,10,10,100);
		b=new Nodo(1,20,20,50);
	}

	@Test
	void testPrimeroMasGrande() {
		a.aumentarCantVecinos();
		a.aumentarCantVecinos();
		b.aumentarCantVecinos();
		
		assertEquals(comparador.compare(a, b),-1);
	}	
	
	@Test
	void testSegundoMasGrande() {
		a.aumentarCantVecinos();
		a.aumentarCantVecinos();
		b.aumentarCantVecinos();
		
		assertEquals(comparador.compare(b, a),1);
	}
	
	@Test
	void testLosDosMismaCantidad() {
		a.aumentarCantVecinos();
		a.aumentarCantVecinos();
		b.aumentarCantVecinos();
		b.aumentarCantVecinos();
		
		assertEquals(comparador.compare(b, a),0);
	}
	
	
	@Test
	void testPrimeroEsCero() {
		a.aumentarCantVecinos();
		a.aumentarCantVecinos();
		
		assertEquals(comparador.compare(b, a),1);
	}
	
	@Test
	void testSegundoEsCero() {
		a.aumentarCantVecinos();
		a.aumentarCantVecinos();
		
		assertEquals(comparador.compare(a, b),-1);
	}
	@Test
	void testAmbosSonCero() {
				
		assertEquals(comparador.compare(a, b),0);
	}

}
