package at.yomi.mp.prime;

import at.yomi.mp.message.Cast;
import at.yomi.mp.message.Info;
import at.yomi.mp.message.Message;
import at.yomi.mp.receiver.Receiver2;

public abstract class Messages {
	public static class StartCalculatingMessage extends Info {}

	public static class FoundPrimeCast extends Cast<Integer> {
		public FoundPrimeCast(final Integer content) {
			super(content);
		}
	}

	public static class NoPrimeCast extends Cast<Integer> {
		public NoPrimeCast(final Integer content) {
			super(content);
		}
	}

	public static class IsPrimeMessage extends Message<Integer,Integer> {
		public <T extends Receiver2<FoundPrimeCast,NoPrimeCast>> IsPrimeMessage(final T sender,
				final Integer content) {
			super(sender, content);
		}
	}
}
