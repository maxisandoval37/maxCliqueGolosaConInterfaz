package test;

import org.junit.jupiter.api.*;
import negocio.Grafo;
import negocio.Nodo;

import static org.junit.Assert.*;
import java.util.HashSet;

public class TestGrafo {
	Grafo g;
	Nodo a,b,c;

	@BeforeEach
	void antesQueTodo() {
		g = new Grafo(5);
		
		
		
		a=new Nodo(0,10,10,100);
		b=new Nodo(1,20,20,50);
		c=new Nodo(2,30,30,100);
		
		
		g.agregarNodo(a);
		g.agregarNodo(b);
		
		
		
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(2, 3);
		g.agregarArista(3, 0);
		
		g.agregarNodoAindiceConVecinos(a, b);
	}
	@Test
	public void testContieneUnNodo() {
		
//		g.agregarNodo(c);
		assertTrue(g._listaNodos.contains(a)); 
	}
	@Test
	public void testContieneVariosNodo() {
		
		g.agregarNodo(c);
		assertTrue(g._listaNodos.contains(a)); 
		assertTrue(g._listaNodos.contains(b));
		assertTrue(g._listaNodos.contains(c));
	}

	@Test
	public void testAgregarArista() {
		
		g.agregarArista(0, 1); 
		assertTrue(g.existeArista(0, 1)); 
	}

	@Test
	public void testAgregarAristaSimetria() {
		
		g.agregarArista(0, 1); 
		assertTrue(g.existeArista(1, 0)); 
	}

	@Test
	public void testEliminarArista() {		
		g.agregarArista(0, 1);
		g.eliminarArista(0, 1);
		assertFalse(g.existeArista(0, 1));
	}

	@Test
	public void testGrado() {
		assertEquals(2, g.grado(1));
	}

	@Test
	public void testGradoCero() {
		assertEquals(0, g.grado(4));
	}

	@Test
	public void testVecino() {
		Grafo g = new Grafo(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(0, 3);

		HashSet<Integer> vecinos = (HashSet<Integer>) g.getVecinos(0);

		assertEquals(3, vecinos.size());
		assertTrue(g.getVecinos(0).contains(1));
		assertTrue(g.getVecinos(0).contains(2));
		assertTrue(g.getVecinos(0).contains(3));
	}
	
	//TEST DE EXCEPCIONES:
	@Test
	public void testExcepcionAregarAristaVerticeIgual() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			g.agregarArista(0, 0);
		});
	}

	public void testExcepcionAgregarAristaFueraDeRangoSuperior() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			g.agregarArista(5, 0);
		});
	}

	public void testExcepcionAgregarAristaFueraDeRangoSuperiorJ() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			g.agregarArista(0, 5);
		});
	}

	public void testExcepcionAgregarAristaFueraDeRangoInferior() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			g.agregarArista(-1, 0);
		});
	}

	public void testExcepcionAristaFueraDeRangoInferiorJ() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			g.agregarArista(0, -1);
		});
	}


}
