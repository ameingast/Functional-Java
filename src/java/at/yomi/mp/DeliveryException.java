package at.yomi.mp;

public class DeliveryException extends Exception {
	private static final long serialVersionUID = -3884566864185263516L;

	public DeliveryException(final Exception e) {
		super(e);
	}

	public DeliveryException(final String s) {
		super(s);
	}
}
