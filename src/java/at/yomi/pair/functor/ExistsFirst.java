package at.yomi.pair.functor;

import at.yomi.functor.Exists;
import at.yomi.functor.f.ExistsFunctor;
import at.yomi.pair.Pair;

public class ExistsFirst<A,B> extends Exists<Pair<A,B>> {

	public ExistsFirst(final ExistsFunctor<Pair<A,B>> functor) {
		super(functor);
		// TODO Auto-generated constructor stub
	}

}
