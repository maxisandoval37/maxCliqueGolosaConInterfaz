package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import negocio.ComparadorPorPeso;
import negocio.Nodo;

class TestComparadorPorPeso {

	Nodo a,b,c,d,e;
	ComparadorPorPeso comparador;

	@BeforeEach
	 void antesquetodo(){
		comparador=new ComparadorPorPeso();
		a=new Nodo(0,10,10,100);
		b=new Nodo(1,20,20,50);
		c=new Nodo(2,30,30,0);
		d=new Nodo(3,40,40,0);
		e=new Nodo(4,50,50,50);
	}

	@Test
	void testPrimeroMasGrande() {
		assertEquals(comparador.compare(a, b),-1);
	}	
	
	
	@Test
	void testSegundoMasGrande() {
		assertEquals(comparador.compare(b, a),1);
	}
	
	
	@Test
	void testLosDosMismaCantidad() {
		assertEquals(comparador.compare(e, b),0);
	}
	
	
	@Test
	void testPrimeroEsCero() {
		assertEquals(comparador.compare(c, a),1);
	}
	
	@Test
	void testSegundoEsCero() {
		assertEquals(comparador.compare(a, c),-1);
	}
	
	
	@Test
	void testAmbosSonCero() {		
		assertEquals(comparador.compare(c, d),0);
	}
	
}
