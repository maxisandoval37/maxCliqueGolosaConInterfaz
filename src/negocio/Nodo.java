package negocio;

public class Nodo {

	private int x, y;
	public static final int diametroCirculo = 100;
	private Integer _peso;
	private int _indice_nodo;
	private int _cantidadVecinos;

	public Nodo(int indice_nodo, int x, int y, int peso) {
		super();
		this.x = x;
		this.y = y;
		this._indice_nodo = indice_nodo;
		this.setPeso(peso);
		this._cantidadVecinos = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Integer getPeso() {
		return _peso;
	}

	public void setPeso(Integer peso) {
		this._peso = peso;
	}

	public int getIndiceNodo() {
		return _indice_nodo;
	}

	public void aumentarCantVecinos() {
		this.setCantidadVecinos(this.getCantidadVecinos() + 1);
	}

	private void setCantidadVecinos(int i) {
		this._cantidadVecinos = i;
	}

	public int getCantidadVecinos() {
		return _cantidadVecinos;
	}

}
