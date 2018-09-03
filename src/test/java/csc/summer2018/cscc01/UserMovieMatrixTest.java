package csc.summer2018.cscc01;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserMovieMatrixTest {
	
	@Test
	 public void testConstructor() {
		UserMovieMatrix matrix = new UserMovieMatrix(3,4);
		assertTrue(matrix.cols == 4 && matrix.rows == 3);
	}

}