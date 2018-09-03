package csc.summer2018.cscc01;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class SimScoreCalculatorTest {
	
	@Test
	 public void testCalcDistance() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,false);
		when(iter.next()).thenReturn(1,2,3);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,false);
		when(iter1.next()).thenReturn(4,3,5);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findDistance(0, 1), 3.7417,0.0001);
	}
	
	@Test
	 public void testCalcDistanceGivenExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(5,4,2,5,4);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(4,5,3,1,3);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findDistance(0, 1), 4.4721,0.0001);
	}
	
	@Test
	 public void testCalcDistanceGivenExampleDifferentRows() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(2,3,4,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(1,1,1,5,5);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(3)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findDistance(0, 3), 6.7823,0.0001);
	}
	
	@Test
	 public void testCalcDistanceGivenExampleZero() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(1,1,1,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(1,1,1,1,1);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findDistance(0, 1), 0,0.0001);
	}
	
	@Test
	 public void testSimScoreRandomValues() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,false);
		when(iter.next()).thenReturn(1,2,3);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,false);
		when(iter1.next()).thenReturn(4,3,5);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findSimScore(0, 1), 0.2109,0.0001);
	}
	
	@Test
	 public void testSimScoreGivenExample() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(5,4,2,5,4);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(4,5,3,1,3);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findSimScore(0, 1), 0.1827,0.0001);
	}
	
	@Test
	 public void testSimScoreGivenExampleDifferentRows() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(2,3,4,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(1,1,1,5,5);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(3)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findSimScore(0, 3), 0.1285,0.0001);
	}
	
	@Test
	 public void testSimScoreGivenExampleWhereScoreIsOne() {
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(1,1,1,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(1,1,1,1,1);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(3)).thenReturn(iter1);
		SimScoreCalculator simCalc = new SimScoreCalculator(uMM);
		assertEquals(simCalc.findSimScore(0, 3), 1,0.0001);
	}

}
