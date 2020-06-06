package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.Clique;
import negocio.ComparadorPorGrado;
import negocio.ComparadorPorPeso;
import negocio.Grafo;
import negocio.Nodo;
import negocio.SolverGoloso;

class TestSolver {
	Clique cliqueMasPesada,cliqueConMayorGrado;
	Nodo a,b,c,d;
	Grafo grafo;
	ComparadorPorGrado comparadorgrado;
	ComparadorPorPeso  comparadorpeso;
	SolverGoloso resultado;

	@BeforeEach
	 void antesquetodo(){

		grafo=new Grafo(4);
		
		cliqueMasPesada=new Clique();
		cliqueConMayorGrado=new Clique();
		comparadorgrado=new ComparadorPorGrado();
		comparadorpeso=new ComparadorPorPeso();
		
		
		a=new Nodo(0,10,10,100);
		b=new Nodo(1,20,20,50);
		c=new Nodo(2,30,30,100);
		d=new Nodo(3,40,40,200);
		
		
		grafo.agregarNodo(a);
		grafo.agregarNodo(b);
		grafo.agregarNodo(c);
		grafo.agregarNodo(d);
		
		grafo.agregarArista(0, 1);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 0);
		grafo.agregarArista(3, 2);

		
		grafo.agregarNodoAindiceConVecinos(a, b);
		grafo.agregarNodoAindiceConVecinos(b, c);
		grafo.agregarNodoAindiceConVecinos(a, c);
		grafo.agregarNodoAindiceConVecinos(c, d);


		cliqueConMayorGrado.agregarNodoAClique(a, grafo);
		cliqueConMayorGrado.agregarNodoAClique(b, grafo);
		cliqueConMayorGrado.agregarNodoAClique(c, grafo);
		
		
		cliqueMasPesada.agregarNodoAClique(c, grafo);
		cliqueMasPesada.agregarNodoAClique(d, grafo);
		
	}
	@Test
	void testCliqueMaximaPorGrado() {
		resultado=new SolverGoloso(comparadorgrado, grafo);
		assertEquals(resultado.resolver().getGrado(),cliqueConMayorGrado.getListaNodo().size());
	}
	
	@Test
	void testCliqueMaximaPorPeso() {
		resultado=new SolverGoloso(comparadorpeso, grafo);		
		assertEquals(resultado.resolver().getPeso(),cliqueMasPesada.getPeso());
	}

}
