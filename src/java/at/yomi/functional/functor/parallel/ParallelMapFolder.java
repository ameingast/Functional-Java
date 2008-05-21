package at.yomi.functional.functor.parallel;

import java.util.List;

import at.yomi.functional.functor.MapFolder;

public abstract class ParallelMapFolder<A,B,C> extends MapFolder<A,B,C> {

	public C apply(List<A> as, C... cs) {
		return null;
	}
}
