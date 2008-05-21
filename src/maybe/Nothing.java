package maybe;

public class Nothing<A> extends Maybe<A> {
	public Nothing(A a) {
		super(a);
	}
	
	public A getOr(A a) {
		return a;
	}
}