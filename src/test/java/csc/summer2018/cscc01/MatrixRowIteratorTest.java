package csc.summer2018.cscc01;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MatrixRowIteratorTest {
	
	@Test
	 public void testConstructorDifferentTypesNoError() {
		Integer[][] test = {{1,2,3},{4,5,6}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		Float[][] test1 = {{(float)1,(float)2,(float)3},{(float)4,(float)5,(float)6}};
		List<List<Float>> testList1 = new ArrayList<List<Float>>();
		for (int i = 0; i < test.length; i++) {
			List<Float> row = new ArrayList<Float>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test1[i][j]);
			}
			testList1.add(row);
		}
		MatrixRowIterator iter = new MatrixRowIterator(testList,4);
		MatrixRowIterator iter2 = new MatrixRowIterator(testList1,2);
		assertTrue(iter != null && iter2 != null);
	}
	
	@Test
	 public void testNext() {
		Integer[][] test = {{1,2,3},{4,5,6}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		MatrixRowIterator iter = new MatrixRowIterator(testList,0);
		assertTrue((Integer)iter.next() == 1 && (Integer)iter.next() == 2 
				&& (Integer)iter.next() == 3 && !iter.hasNext());
	}
	
	@Test
	 public void testNextMiddleRow() {
		Integer[][] test = {{1,2,3},{4,5,6},{7,8,9}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		MatrixRowIterator iter = new MatrixRowIterator(testList,1);
		assertTrue((Integer)iter.next() == 4 && (Integer)iter.next() == 5
				&& (Integer)iter.next() == 6 && !iter.hasNext());
	}
	
	@Test
	 public void testHasNext() {
		Integer[][] test = {{1,2,3},{4,5,6},{7,8,9}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		MatrixRowIterator iter = new MatrixRowIterator(testList,0);
		boolean testResult = true;
		testResult = testResult && iter.hasNext();
		iter.next();
		testResult = testResult && iter.hasNext();
		iter.next();
		testResult = testResult && iter.hasNext();
		iter.next();
		assertTrue(testResult && !iter.hasNext());
	}
	
	@Test
	 public void testHasNextMiddleRow() {
		Integer[][] test = {{1,2,3},{4,5,6},{7,8,9}};
		List<List<Integer>> testList = new ArrayList<List<Integer>>();
		for (int i = 0; i < test.length; i++) {
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < test[0].length; j++) {
				row.add(test[i][j]);
			}
			testList.add(row);
		}
		MatrixRowIterator iter = new MatrixRowIterator(testList,1);
		boolean testResult = true;
		testResult = testResult && iter.hasNext();
		iter.next();
		testResult = testResult && iter.hasNext();
		iter.next();
		testResult = testResult && iter.hasNext();
		iter.next();
		assertTrue(testResult && !iter.hasNext());
	}

}
