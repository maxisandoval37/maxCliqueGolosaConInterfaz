package test;

import org.junit.jupiter.api.*;
import negocio.Grafo;
import static org.junit.Assert.*;
import java.util.HashSet;

public class TestGrafo {
	
	private Grafo grafoGenerico() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(2, 3);
		g.agregarArista(3, 0);
		return g;
	}

	@Test
	public void agregarAristaTest() {
		Grafo g = new Grafo(5); 
		g.agregarArista(0, 1); 
		assertTrue(g.existeArista(0, 1)); 
	}

	@Test
	public void agregarAristaSimetriaTest() {
		Grafo g = new Grafo(5); 
		g.agregarArista(0, 1); 
		assertTrue(g.existeArista(1, 0)); 
	}

	@Test
	public void eliminarAristaTest() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.eliminarArista(0, 1);
		assertFalse(g.existeArista(0, 1));
	}

	@Test
	public void gradoTest() {
		Grafo g = grafoGenerico();
		assertEquals(2, g.grado(1));
	}

	@Test
	public void gradoCeroTest() {
		Grafo g = grafoGenerico();
		assertEquals(0, g.grado(4));
	}

	@Test
	public void vecinoTest() {
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
			Grafo g = new Grafo(5);
			g.agregarArista(0, 0);
		});
	}

	public void testExcepcionAgregarAristaFueraDeRangoSuperior() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			g.agregarArista(5, 0);
		});
	}

	public void testExcepcionAgregarAristaFueraDeRangoSuperiorJ() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			g.agregarArista(0, 5);
		});
	}

	public void testExcepcionAgregarAristaFueraDeRangoInferior() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			g.agregarArista(-1, 0);
		});
	}

	public void testExcepcionAristaFueraDeRangoInferiorJ() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			g.agregarArista(0, -1);
		});
	}


}
