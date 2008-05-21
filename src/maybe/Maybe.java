package maybe;

public abstract class Maybe<A> {
	protected A a;

	public Maybe(A a) {
		this.a = a;
	}

	public abstract A getOr(A a);
}
