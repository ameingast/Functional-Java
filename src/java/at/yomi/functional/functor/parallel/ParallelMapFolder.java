package at.yomi.functional.functor.parallel;

import java.util.List;

import at.yomi.functional.functor.Folder;
import at.yomi.functional.functor.Functor;
import at.yomi.functional.functor.MapFolder;
import at.yomi.functional.functor.parallel.aggregator.Aggregator;
import at.yomi.functional.functor.parallel.aggregator.FolderAggregator;

public abstract class ParallelMapFolder<A,B,C> extends MapFolder<A,B,C> {

	private MapFolder<A,B,C> self = this;

	private int workerCount;

	protected Folder<B,C> folder = new Folder<B,C>() {
		public C fold(B b, C e) {
			return self.fold(b, e);
		}
	};

	public ParallelMapFolder(Integer workerCount) {
		this.workerCount = workerCount;
	}

	protected Aggregator<B,C> getFolderAggregator(Integer itemCount, C e) {
		return new FolderAggregator<B,C>(itemCount, folder, e);
	}

	public C apply(List<A> as, C... cs) {

		Aggregator<B,C> aggregator = getFolderAggregator(as.size(), cs[0]);

		Worker.createWorkers(workerCount, aggregator, as, true, new Functor<A,B,Object>() {
			public B apply(A a, Object... cs) {
				return map(a);
			}
		});

		try {
			return aggregator.getResult();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
