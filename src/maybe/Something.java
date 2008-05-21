package maybe;

public class Something<A> extends Maybe<A> {
	public Something(A a) {
		super(a);
	}

	public A getOr(A a) {
		return this.a;
	}
}
