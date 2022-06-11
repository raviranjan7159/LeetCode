import java.util.Iterator;

/*
 * https://leetcode.com/problems/peeking-iterator/
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
	private Integer peekElement = null;
	Iterator<Integer> iterator;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (peekElement == null) {
			peekElement = iterator.next();
		}
		return peekElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer val = null;
		if (peekElement != null) {
			val = peekElement;
			peekElement = null;
		} else {
			val = iterator.next();
		}
		return val;
	}

	@Override
	public boolean hasNext() {
		return peekElement != null || iterator.hasNext();
	}
}
