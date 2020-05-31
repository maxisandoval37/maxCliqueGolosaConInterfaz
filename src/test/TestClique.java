package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import negocio.Grafo;

public class TestClique {

	private Grafo grafoGenerico() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);
		g.agregarArista(1, 3);
		g.agregarArista(2, 3);
		g.agregarArista(3, 0);
		g.agregarArista(2, 4);
		return g;
	}

	@Test
	public void cliqueVacioTest() {
		Grafo g = new Grafo(5);
		Set<Integer> conjunto = new HashSet<Integer>();
		assertTrue(g.esClique(conjunto));
	}

	@Test
	public void cliqueUnitarieTest() {
		Grafo g = new Grafo(5);
		Set<Integer> conjunto = new HashSet<Integer>();
		conjunto.add(1);
		assertTrue(g.esClique(conjunto));
	}

	@Test
	public void cliqueVariosTest() {
		Grafo g = new Grafo(5);
		g.agregarArista(1, 3);

		Set<Integer> conjunto = new HashSet<Integer>();
		conjunto.add(1);
		conjunto.add(3);

		assertTrue(g.esClique(conjunto));
	}

	@Test
	public void cliqueVariosFalseTest() {
		Grafo g = new Grafo(5);
		g.agregarArista(1, 3);

		Set<Integer> conjunto = new HashSet<Integer>();
		conjunto.add(0);
		conjunto.add(1);

		assertFalse(g.esClique(conjunto));
	}

	@Test
	public void cliqueVecinosDeTodoTest() {
		Grafo g = grafoGenerico();

		Set<Integer> conjunto = new HashSet<Integer>();
		conjunto.add(0);
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);

		assertTrue(g.esClique(conjunto));
	}

	@Test
	public void cliqueVecinosDeTodoFalseTest() {
		Grafo g = grafoGenerico();

		Set<Integer> conjunto = new HashSet<Integer>();
		conjunto.add(0);
		conjunto.add(1);
		conjunto.add(3);
		conjunto.add(4);

		assertFalse(g.esClique(conjunto));
	}

	// TEST DE EXCEPCIONES:
	public void testExcepcionCliqueConjuntoNullTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			g.esClique(null);
		});
	}

	public void testExcepcionCliqueConjuntoNegativoTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			Set<Integer> conjunto = new HashSet<Integer>();
			conjunto.add(-1);
			g.esClique(conjunto);
		});
	}

	public void testExcepcionCliqueConjuntoExcedidoTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(5);
			Set<Integer> conjunto = new HashSet<Integer>();
			conjunto.add(5);
			g.esClique(conjunto);
		});
	}
}
