package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor;
import at.yomi.functor.f.MapFunctor;

public class FlatMap<A,B> implements Functor<List<List<A>>,List<B>> {
	protected final MapFunctor<A,B> functor;

	public FlatMap(final MapFunctor<A,B> functor) {
		this.functor = functor;
	}

	protected MapFunctor<List<A>,List<B>> mapFunctor = new MapFunctor<List<A>,List<B>>() {
		@Override
		public List<B> apply(List<A> a) {
			return new Map<A,B>(functor).apply(a);
		}
	};

	protected FoldFunctor<List<B>,List<B>> foldFunctor = new FoldFunctor<List<B>,List<B>>() {
		@Override
		public List<B> apply(List<B> a, List<B> b) {
			b.addAll(a);
			return b;
		}
	};

	@Override
	public List<B> apply(final List<List<A>> aas) {
		return getMapFold().apply(aas, new ArrayList<B>());
	}

	protected MapFold<List<A>,List<B>,List<B>> getMapFold() {
		return new MapFold<List<A>,List<B>,List<B>>(mapFunctor, foldFunctor);
	}
}