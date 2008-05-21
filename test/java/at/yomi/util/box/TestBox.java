package at.yomi.util.box;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestBox {
	@Test
	public void testOpenBox() {
		Box<String> fullBox = new FullBox<String>("foo");
		Box<String> emptyBox = new EmptyBox<String>();

		assertEquals("foo", fullBox.openOr("bar"));
		assertEquals("bar", emptyBox.openOr("bar"));

	}
}
