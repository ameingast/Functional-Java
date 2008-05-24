package at.yomi.mp.prime;

import at.yomi.mp.AbstractReceiver;
import at.yomi.mp.Message;

public class Messages {

	@SuppressWarnings("unchecked")
	private static Messages instance = null;

	@SuppressWarnings("unchecked")
	public static Messages get() {
		if (instance == null)
			instance = new Messages();
		return instance;
	}

	public class FoundPrimeMessage extends Message<Integer,Integer> {
		public FoundPrimeMessage(final AbstractReceiver<Integer> sender, final Integer content) {
			super(sender, content);
		}
	}

	public class StartCalculatingMessage extends Message<Integer,Integer> {
		public StartCalculatingMessage(final AbstractReceiver<Integer> sender, final Integer content) {
			super(sender, content);
		}
	}

	public class NoPrimeMessage extends Message<Integer,Integer> {
		public NoPrimeMessage(final AbstractReceiver<Integer> sender, final Integer content) {
			super(sender, content);
		}
	}

	public class IsPrimeMessage extends Message<Integer,Integer> {
		public IsPrimeMessage(final AbstractReceiver<Integer> sender, final Integer content) {
			super(sender, content);
		}
	}
}
