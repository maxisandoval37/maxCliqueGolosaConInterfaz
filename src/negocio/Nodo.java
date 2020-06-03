package negocio;



public class Nodo {

	private int x,y;
	public static final int diametroCirculo=100;
	private Integer peso;
	private int indice_nodo;
	private int cantidadVecinos;
	

	public Nodo(int indice_nodo,int x, int y,int peso) {
		super();
		this.x = x;
		this.y = y;
		this.indice_nodo = indice_nodo;
		this.setPeso(peso);
		this.cantidadVecinos = 0;
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
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public int getIndiceNodo() {
		return indice_nodo;
	}

	public void aumentarCantVecinos() {
		this.setCantidadVecinos(this.getCantidadVecinos() + 1);
		
	}

	private void setCantidadVecinos(int i) {
		this.cantidadVecinos =  i;
		
	}

	public int getCantidadVecinos() {
		return cantidadVecinos;
	}



	
	
	
	
	
	
}
