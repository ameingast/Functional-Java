package at.yomi.mp.message;

public abstract class Cast<A> extends AbstractMessage<A> {
	public Cast(final A content) {
		super(content);
	}
}
