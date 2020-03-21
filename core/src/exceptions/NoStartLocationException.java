package exceptions;

public class NoStartLocationException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoStartLocationException(){
		System.out.println("Start location not defined! Invalid map.tmx!");
	}
}
