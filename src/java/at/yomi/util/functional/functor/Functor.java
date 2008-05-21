package at.yomi.util.functional.functor;

public interface Functor<A,B,C> {
	public B apply(final A a, final C... cs);
}