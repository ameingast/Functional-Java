package at.yomi.functor.f;

public interface Functor<A,B> {
	B apply(A a);
}
