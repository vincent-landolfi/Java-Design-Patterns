package csc.summer2018.cscc01;

import java.text.DecimalFormat;
import java.util.Iterator;

public class UserUserMatrix extends Matrix<Float>{
	
	private DecimalFormat df4 = new DecimalFormat("0.0000");

	/**
	 * Creates a new UserUserMatrix
	 * @param rows Number of rows in the matrix
	 * @param cols Number of cols in the matrix
	 */
	public UserUserMatrix(int rows, int cols) {
		// instatiate float matrix
		super(rows, cols);
	}

	/**
	 * Prints the data in the UserUserMatrix
	 */
	public void print() {
		// go through the rows in the matrix
		for (int i = 0; i < this.rows; i++) {
			// create an iterator for that row
			Iterator<Float> rowIterator = createRowIterator(i);
			// opening bracket
			String rowOutput = "[";
			// while elmts in the row
			while(rowIterator.hasNext()) {
				// print elmts contents and comma
				rowOutput += df4.format(rowIterator.next()) + ", ";
			}
			// remove the last comma
			rowOutput = rowOutput.substring(0,rowOutput.lastIndexOf(", "));
			// closing bracket
			rowOutput += "]\n";
			// print out the data
			System.out.print(rowOutput);
		}
	}
	
	/**
	 * Sets the decimal format, used for testing
	 */
	public void setDFTestMode() {
		// round to 4 decimal places
		df4 = new DecimalFormat("0.0000");
	}
	
}