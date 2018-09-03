package csc.summer2018.cscc01;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class MatrixTest {
	
	@Test
	 public void testConstructorSetsSizes() {
		UserMovieMatrix matrix = new UserMovieMatrix(3,4);
		assertTrue(matrix.cols == 4 && matrix.rows == 3);
	}
	
	
	@Test
	 public void testSetValue() {
		Matrix<Integer> matrix = new UserMovieMatrix(3,3);
		matrix.setValue(1, 1, 45);
		assertTrue(matrix.getValue(1, 1) == 45);
	}
	
	@Test
	 public void testGetValue() {
		Matrix<Integer> matrix = new UserMovieMatrix(3,3);
		matrix.setValue(0, 2, 45);
		assertTrue(matrix.getValue(0, 2) == 45);
	}
	
	@Test
	 public void testSetAndGetMatrix() {
		Matrix<Integer> matrix = new UserMovieMatrix(3,3);
		Integer[][] test = {{1,2,3},{4,5,6}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		matrix.setMatrix(testList);
		assertTrue(matrix.getValue(0, 2) == 3);
	}
	
	@Test
	 public void testCreateRowIterator() {
		Matrix<Integer> matrix = new UserMovieMatrix(3,3);
		Integer[][] test = {{1,2,3},{4,5,6}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		matrix.setMatrix(testList);
		Iterator<Integer> iter = matrix.createRowIterator(0);
		int[] row = {1,2,3};
		int counter = 0;
		boolean rowEqual = true;
		while(iter.hasNext()) {
			rowEqual = rowEqual && (iter.next() == row[counter]);
			counter++;
		}
		assertTrue(rowEqual);
	}
	
}
