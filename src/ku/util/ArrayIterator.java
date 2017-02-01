package ku.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayIterator is an array collector that can call for a next value, remove
 * the value.
 * 
 * @author Chawakorn Suphepre
 * @version 2017.02.02
 * @param <T>
 *            is a type parameter that can be changed to data type.
 */
public class ArrayIterator<T> implements Iterator<T> {
	/** attribute for the array we want to iterate over */
	/** array is the array to iterate over */
	private T[] array;
	/** count is the number that used to define */
	private int count;
	/**
	 * canRemove is a boolean used to check can we use remove at that time. true
	 * if can, false if not.
	 */
	private boolean canRemove;

	/**
	 * Initialize a new array iterator with the array to process.
	 * 
	 * @param array
	 *            is the array to iterate over
	 */
	public ArrayIterator(T[] array) {
		this.array = array;
		this.count = 0;
		this.canRemove = false;
	}

	/**
	 * Return the next non-null element from array, if any.
	 * 
	 * @return the next non-null element in the array.
	 * @throws NoSuchElementException
	 *             if there are no more elements to return.
	 */
	public T next() {
		if (hasNext()) {
			canRemove = true;
			return array[count++];
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Avoid null element and make the cursor point into next non-null element.
	 * Return boolean to check is there a next element.
	 * 
	 * @return true if there is a next element. false if there is no more
	 *         element.
	 */
	@Override
	public boolean hasNext() {
		for (int i = count; i < this.array.length; i++) {
			if (array[i] != null) {
				count = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove the element that the cursor point at that time.
	 * 
	 * @throws IllegalStateException
	 *             if the user doesn't call next() before and already call
	 *             remove() before.
	 */
	public void remove() {
		if (canRemove) {
			array[count - 1] = null;
			canRemove = false;
		} else {
			throw new IllegalStateException();
		}
	}
}