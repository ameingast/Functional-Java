package at.yomi.functor.parallel.utils;

public final class ApplyStatus {
	public Boolean applied = false;

	public final Boolean update() {
		Boolean fork = false;

		synchronized (applied) {
			fork = applied;
			applied = true;
		}
		return fork;
	}
}
