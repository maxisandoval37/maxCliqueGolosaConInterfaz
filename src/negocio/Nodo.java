package negocio;

public class Nodo {

	private int x,y;
	public static final int d=60;//diametro del circulo
	private String nombre;
	
	
	public Nodo(int x, int y,String nombre) {
		super();
		this.x = x;
		this.y = y;
		this.setNombre(nombre);
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
