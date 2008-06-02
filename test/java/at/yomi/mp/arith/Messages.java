package at.yomi.mp.arith;

import at.yomi.mp.message.Cast;

public abstract class Messages {
	public static class SubCast extends Cast<Number> {
		public SubCast(final Number content) {
			super(content);
		}
	}

	public static class AddCast extends Cast<Number> {
		public AddCast(final Number content) {
			super(content);
		}
	}

	public static class MulCast extends Cast<Number> {
		public MulCast(final Number content) {
			super(content);
		}
	}
}
