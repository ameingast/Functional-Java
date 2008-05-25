package at.yomi.functor.f;

/**
 * A transformation function of arity-3 from <code>A</code>, <code>B</code> and <code>C</code>
 * to <code>D</code>. This type can be represented using the Java 7 closure syntax.
 * 
 * @version 2.6<br>
 *          <ul>
 *          <li>$LastChangedRevision: 9595 $</li>
 *          <li>$LastChangedDate: 2008-04-16 14:47:05 +1000 (Wed, 16 Apr 2008) $</li>
 *          </ul>
 */
public interface F3<A,B,C,D> {
	/**
	 * Transform <code>A</code>, <code>B</code> and <code>C</code> to <code>D</code>.
	 * 
	 * @param a
	 *            The <code>A</code> to transform.
	 * @param b
	 *            The <code>B</code> to transform.
	 * @param c
	 *            The <code>C</code> to transform.
	 * @return The result of the transformation.
	 */
	D f(A a, B b, C c);
}
