package at.yomi.mp.message;

public abstract class Cast<A> extends Info {
	public final A content;

	public Cast(final A content) {
		this.content = content;
	}
}
