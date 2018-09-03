package csc.summer2018.cscc01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.PrintStream;
import java.util.Iterator;

public class CfilteringTest {
	
	@Test
	 public void testConstructorNoParams() {
		Cfiltering cfilter = new Cfiltering();
		boolean UUMIsRightSize = cfilter.getUserUserMatrix().cols == 1 && cfilter.getUserUserMatrix().rows == 1;
		boolean UMMIsRightSize = cfilter.getUserMovieMatrix().cols == 1 && cfilter.getUserMovieMatrix().rows == 1;
		assertTrue(UUMIsRightSize && UMMIsRightSize);
	}
	
	@Test
	public void testConstructorParams() {
		Cfiltering cfilter = new Cfiltering(3,4);
		boolean UUMIsRightSize = cfilter.getUserUserMatrix().cols == 3 && cfilter.getUserUserMatrix().rows == 3;
		boolean UMMIsRightSize = cfilter.getUserMovieMatrix().cols == 4 && cfilter.getUserMovieMatrix().rows == 3;
		assertTrue(UUMIsRightSize && UMMIsRightSize);
	}
	
	@Test
	public void testPopulateUserMovieMatrix() {
		Cfiltering cfilter = new Cfiltering(3,4);
		cfilter.populateUserMovieMatrix(2, 1, 44);
		assertTrue(cfilter.getUserMovieMatrix().getValue(2, 1) == 44);
	}
	
	@Test
	public void testCalculateSimilarityScoreGivenExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(2,3,4,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(5,4,2,5,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(4,5,3,1,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,5,5);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		Float[][] uUMMatrix = {{1.0000f,0.1380f,0.2171f,0.1285f},
				{0.1380f,1.0000f,0.1827f,0.1614f},{0.2171f,0.1827f,1.0000f,0.1250f},
				{0.1285f,0.1614f,0.1250f,1.0000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 4;i++) {
			Iterator<Float> uUMVals = filter.getUserUserMatrix().createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues 
						&& UserUserMatrixPopulatorTest.round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(correctValues);
	}
	
	@Test
	public void testCalculateSimilarityScoreGivenExampleAllOnes() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		Float[][] uUMMatrix = {{1.0000f,1.0000f,1.0000f,1.0000f},
				{1.0000f,1.0000f,1.0000f,1.0000f},{1.0000f,1.0000f,1.0000f,1.0000f},
				{1.0000f,1.0000f,1.0000f,1.0000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 4;i++) {
			Iterator<Float> uUMVals = filter.getUserUserMatrix().createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues 
						&& UserUserMatrixPopulatorTest.round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(correctValues);
	}
	
	@Test
	 public void testCalculateSimilarityScoreRandomExampleSquare() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		Float[][] uUMMatrix = {{1.0000f,0.2109f,0.2500f},
				{0.2109f,1.0000f,0.3660f},
				{0.2500f,0.3660f,1.000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 3;i++) {
			Iterator<Float> uUMVals = filter.getUserUserMatrix().createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues && UserUserMatrixPopulatorTest.round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(correctValues);
	}
	
	@Test
	 public void testPopulateRandomExampleMoreRowsThanCols() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,2,5);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,5,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		Float[][] uUMMatrix = {{1.0000f,0.2109f,0.1907f,0.2403f},
				{0.2109f,1.0000f,0.1757f,0.1907f},
				{0.1907f,0.1757f,1.000f,0.1429f},
				{0.2403f,0.1907f,0.1429f,1.0000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 4;i++) {
			Iterator<Float> uUMVals = filter.getUserUserMatrix().createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues && UserUserMatrixPopulatorTest.round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(correctValues);
	}
	
	@Test
	public void testPrintUUMMatrixGivenExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(2,3,4,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(5,4,2,5,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(4,5,3,1,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,5,5);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.printUserUserMatrix();
	    System.out.flush();
	    System.setOut(old);
		String expected = "[1.0000, 0.1380, 0.2171, 0.1285]\n";
		expected += "[0.1380, 1.0000, 0.1827, 0.1614]\n";
		expected += "[0.2171, 0.1827, 1.0000, 0.1250]\n";
		expected += "[0.1285, 0.1614, 0.1250, 1.0000]\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintUUMMatrixGivenExampleAllOnes() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.printUserUserMatrix();
	    System.out.flush();
	    System.setOut(old);
		String expected = "[1.0000, 1.0000, 1.0000, 1.0000]\n";
		expected += "[1.0000, 1.0000, 1.0000, 1.0000]\n";
		expected += "[1.0000, 1.0000, 1.0000, 1.0000]\n";
		expected += "[1.0000, 1.0000, 1.0000, 1.0000]\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintUUMMatrixRandomExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.printUserUserMatrix();
	    System.out.flush();
	    System.setOut(old);
		String expected = "[1.0000, 0.2109, 0.2500]\n";
		expected += "[0.2109, 1.0000, 0.3660]\n";
		expected += "[0.2500, 0.3660, 1.0000]\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	 public void testPrintRandomExampleMoreRowsThanCols() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,2,5);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,5,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.printUserUserMatrix();
	    System.out.flush();
	    System.setOut(old);
		String expected = "[1.0000, 0.2109, 0.1907, 0.2403]\n";
		expected += "[0.2109, 1.0000, 0.1757, 0.1907]\n";
		expected += "[0.1907, 0.1757, 1.0000, 0.1429]\n";
		expected += "[0.2403, 0.1907, 0.1429, 1.0000]\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintUUMMatrixWithotCalculatingSimScoreFirst() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.printUserUserMatrix();
	    System.out.flush();
	    System.setOut(old);
		String expected = "[1.0000, 0.2109, 0.2500]\n";
		expected += "[0.2109, 1.0000, 0.3660]\n";
		expected += "[0.2500, 0.3660, 1.0000]\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostSimilarGivenExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(2,3,4,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(5,4,2,5,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(4,5,3,1,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,5,5);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostSimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most similar pairs of users from above userUserMatrix are: \n";
		expected += "User1 and User3\n";
		expected += "with similarity score of 0.2171\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostSimilarGivenExampleAllOnes() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostSimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most similar pairs of users from above userUserMatrix are: \n";
		expected += "User1 and User2\n";
		expected += "User1 and User3\n";
		expected += "User1 and User4\n";
		expected += "User2 and User3\n";
		expected += "User2 and User4\n";
		expected += "User3 and User4\n";
		expected += "with similarity score of 1.0000\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostSimilarRandomExampleSquare() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostSimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most similar pairs of users from above userUserMatrix are: \n";
		expected += "User2 and User3\n";
		expected += "with similarity score of 0.3660\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	 public void testPrintMostSimilarRandomExampleMoreRowsThanCols() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,2,5);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,5,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostSimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most similar pairs of users from above userUserMatrix are: \n";
		expected += "User1 and User4\n";
		expected += "with similarity score of 0.2403\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostSimilarWithoutCalculatingSimScoresFirst() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostSimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most similar pairs of users from above userUserMatrix are: \n";
		expected += "User2 and User3\n";
		expected += "with similarity score of 0.3660\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostDissimilarGivenExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(2,3,4,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(5,4,2,5,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(4,5,3,1,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,5,5);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostDissimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most dissimilar pairs of users from above userUserMatrix are: \n";
		expected += "User3 and User4\n";
		expected += "with similarity score of 0.1250\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostDissimilarGivenExampleAllOnes() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
				when(iter.next()).thenReturn(1,1,1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,5);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostDissimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most dissimilar pairs of users from above userUserMatrix are: \n";
		expected += "User1 and User2\n";
		expected += "User1 and User3\n";
		expected += "User1 and User4\n";
		expected += "User2 and User3\n";
		expected += "User2 and User4\n";
		expected += "User3 and User4\n";
		expected += "with similarity score of 1.0000\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostDissimilarRandomExampleSquare() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostDissimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most dissimilar pairs of users from above userUserMatrix are: \n";
		expected += "User1 and User2\n";
		expected += "with similarity score of 0.2109\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	 public void testPrintMostDissimilarRandomExampleMoreRowsThanCols() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,2,5);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,5,3);
				return iter;
			}
		});
		when(uMM.createRowIterator(3)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(1,1,1);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(4,3);
		filter.setUserMovieMatrix(uMM);
		filter.calculateSimilarityScore();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostDissimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most dissimilar pairs of users from above userUserMatrix are: \n";
		expected += "User3 and User4\n";
		expected += "with similarity score of 0.1429\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testPrintMostDissimilarRandomExampleSquareWithoutCalcSimScoreFirst() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    filter.findAndprintMostDissimilarPairOfUsers();
	    System.out.flush();
	    System.setOut(old);
		String expected = "The most dissimilar pairs of users from above userUserMatrix are: \n";
		expected += "User1 and User2\n";
		expected += "with similarity score of 0.2109\n";
		assertTrue(baos.toString().equals(expected));
	}
	
	@Test
	public void testCheckForPopulationBeforePopulating() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		filter.checkForPopulation();
		assertEquals(filter.getUserUserMatrix().getValue(0, 1),0.2109f,0.0001f);
	}
	
	@Test
	public void testCheckForPopulationAfterPopulating() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		when(uMM.createRowIterator(0)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(2,3,4);
				return iter;
			}
		});
		when(uMM.createRowIterator(1)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(5,4,2);
				return iter;
			}
		});
		when(uMM.createRowIterator(2)).thenAnswer(new Answer<Iterator>() {
			public Iterator answer(InvocationOnMock invocation) throws Throwable {
				MatrixRowIterator iter = mock(MatrixRowIterator.class);
				when(iter.hasNext()).thenReturn(true,true,true,false);
				when(iter.next()).thenReturn(4,5,3);
				return iter;
			}
		});
		Cfiltering filter = new Cfiltering(3,3);
		filter.setUserMovieMatrix(uMM);
		filter.checkForPopulation();
		assertEquals(filter.getUserUserMatrix().getValue(0, 1),0.2109f,0.0001f);
	}
}