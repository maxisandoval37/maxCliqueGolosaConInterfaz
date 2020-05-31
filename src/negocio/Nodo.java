package negocio;

public class Nodo {

	private int x,y;
	public static final int diametroCirculo=60;
	private Integer peso;
	private int indice_nodo;
	//private static Integer indiceNodo=-1;

	public Nodo(int indice_nodo,int x, int y,int peso) {
		super();
		this.x = x;
		this.y = y;
		this.indice_nodo = indice_nodo;
		this.setPeso(peso);
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
	
}
