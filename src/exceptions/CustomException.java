package exceptions;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomException() {
		super("Não é possível inserir o mesmo pedido mais de 1 vez.");
	}
}