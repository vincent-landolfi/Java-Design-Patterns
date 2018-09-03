package csc.summer2018.cscc01;

import java.util.Iterator;
import java.util.List;

public class MatrixRowIterator<T> implements Iterator<T> {
	private List<List<T>> matrix;
	private int col;
	private int row;
	
	/**
	 * Creates a new matrix row iterator
	 * @param matrix 2d array to store the matrix
	 * @param row Row number to iterator through
	 */
	public MatrixRowIterator(List<List<T>> matrix,int row) {
		// set class variables
		this.matrix = matrix;
		this.col = 0;
		this.row = row;
	}
	
	/**
	 * Gets the next element in the iterator
	 */
	public T next() {
		// get the next element in the 2d array
		T next = matrix.get(row).get(col);
		// increment the column for next val
		col++;
		// return the value
		return next;
	}

	/**
	 * Returns true iff there is another elmt in the iterator
	 */
	public boolean hasNext() {
		// if col var is pass the number of cols
		if (col == matrix.get(row).size()) {
			// no more vals to get
			return false;
		}
		// otherwise, more vals to get
		return true;
	}
	
}