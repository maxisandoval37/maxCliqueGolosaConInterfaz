package negocio;

public class ProblemaCliqueMaxima {

	private SolverGoloso _solucionPorPeso;
	private SolverGoloso _solucionPorGrado;
	private ComparadorPorPeso _comparaPorPeso;
	private ComparadorPorGrado _comparaPorGrado;
	private Grafo _grafo;
	private Clique _porCompararPeso;
	private Clique _porCompararGrado;
	private Clique _cliqueConMasPeso;
	Long _tiempoFinal;

	public ProblemaCliqueMaxima(Grafo grafo) {
		this._comparaPorPeso = new ComparadorPorPeso();
		this._comparaPorGrado = new ComparadorPorGrado();
		this._grafo = grafo;
		this._solucionPorPeso = new SolverGoloso(_comparaPorPeso, grafo);
		this._solucionPorGrado = new SolverGoloso(_comparaPorGrado, grafo);
		this._tiempoFinal = (long) 0;
	}

	public void hallarCliqueMaxima() {
		_porCompararPeso = _solucionPorPeso.resolver();
		_porCompararGrado = _solucionPorGrado.resolver();
		_cliqueConMasPeso = _porCompararPeso.cliqueConMasPeso(_porCompararGrado);
	}

	public Long getTiempoFinal() {
		return _solucionPorPeso.getTiempoTardado() + _solucionPorGrado.getTiempoTardado();
	}

	public SolverGoloso getSolucionPorGrado() {
		return _solucionPorGrado;
	}

	public SolverGoloso getSolucionPorPeso() {
		return _solucionPorPeso;
	}

	public Grafo getGrafo() {
		return _grafo;
	}

	public Clique get_cliqueConMasPeso() {
		return _cliqueConMasPeso;
	}

}
