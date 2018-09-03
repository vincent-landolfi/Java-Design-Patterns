package csc.summer2018.cscc01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Matrix<T> {
	
	private List<List<T>> matrix;
	public int rows;
	public int cols;
	
	/**
	 * Creates a new matrix
	 * @param rows The number of rows in the matrix
	 * @param cols The number of cols in the matrix
	 */
	public Matrix(int rows, int cols) {
		// new matrix
		matrix = new ArrayList<List<T>>();
		// go through number of rows
		for (int i = 0; i < rows; i++) {
			// create a new row
			List<T> row = new ArrayList<T>();
			// go through number of cols
			for (int j = 0; j < cols; j++) {
				// instantiate value in that row/col spot
				row.add(null);
			}
			// add row to the list
			matrix.add(row);
		}
		this.rows = rows;
		this.cols = cols;
	}
	
	/**
	 * Sets the value at the given index of the matrix
	 * @param row The row to set the value at
	 * @param col The col to set the value at
	 * @param val The value to set the value to
	 */
	public void setValue(int row, int col, T val) {
		// set the value
		this.matrix.get(row).set(col, val);
	}

	/**
	 * Gets the value at the given index
	 * @param row Row of the value to get
	 * @param col Col of the value to get
	 * @return Value of type T at the given index
	 */
	public T getValue(int row, int col) {
		// return the value
		return this.matrix.get(row).get(col);
	}

	/**
	 * Getter for the 2d array of the matrix
	 * @return The 2d array representation of the matrix
	 */
	public List<List<T>> getMatrix() {
		// return matrix
		return matrix;
	}
	
	/**
	 * Creates row iteartor given a row.
	 * @param row The row number to iterate through
	 * @return The rowiterator for the given row
	 */
	public Iterator<T> createRowIterator(int row) {
		// return a new row iterator
		return new MatrixRowIterator<T>(this.matrix, row);
	}

	/**
	 * Sets the 2d array of the matrix
	 * @param matrix The 2d array representation of the matrix
	 */
	public void setMatrix(List<List<T>> matrix) {
		// set the matrix
		this.matrix = matrix;
	}
	
}
