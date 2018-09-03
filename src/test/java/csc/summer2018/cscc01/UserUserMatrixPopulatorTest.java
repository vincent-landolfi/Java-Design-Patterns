package csc.summer2018.cscc01;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.Test;

public class UserUserMatrixPopulatorTest {
	
	public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
	
	@Test
	 public void testHasPopulated() {
		UserUserMatrix uUM = new UserUserMatrix(4,4);
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(2,3,4,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(5,4,2,5,4);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter2.next()).thenReturn(4,5,3,1,3);
		MatrixRowIterator iter3 = mock(MatrixRowIterator.class);
		when(iter3.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter3.next()).thenReturn(1,1,1,5,5);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		when(uMM.createRowIterator(2)).thenReturn(iter2);
		when(uMM.createRowIterator(3)).thenReturn(iter3);
		UserUserMatrixPopulator populator = new UserUserMatrixPopulator(uUM,uMM);
		populator.populateUserUserMatrix();
		assertTrue(populator.hasPopulated());
	}
	
	@Test
	 public void testNotHasPopulated() {
		UserUserMatrix uUM = new UserUserMatrix(4,4);
		UserMovieMatrix uMM = new UserMovieMatrix(4,4);
		UserUserMatrixPopulator populator = new UserUserMatrixPopulator(uUM,uMM);
		assertTrue(!populator.hasPopulated());
	}
	
	@Test
	 public void testPopulateGivenExample() {
		UserUserMatrix uUM = new UserUserMatrix(4,4);
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(2,3,4,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(5,4,2,5,4);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter2.next()).thenReturn(4,5,3,1,3);
		MatrixRowIterator iter3 = mock(MatrixRowIterator.class);
		when(iter3.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter3.next()).thenReturn(1,1,1,5,5);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		when(uMM.createRowIterator(2)).thenReturn(iter2);
		when(uMM.createRowIterator(3)).thenReturn(iter3);
		UserUserMatrixPopulator populator = new UserUserMatrixPopulator(uUM,uMM);
		populator.populateUserUserMatrix();
		Float[][] uUMMatrix = {{1.0000f,0.1380f,0.2171f,0.1285f},
				{0.1380f,1.0000f,0.1827f,0.1614f},{0.2171f,0.1827f,1.0000f,0.1250f},
				{0.1285f,0.1614f,0.1250f,1.0000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 4;i++) {
			Iterator<Float> uUMVals = uUM.createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues && round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(populator.hasPopulated());
	}
	
	@Test
	 public void testPopulateGivenExampleAllOne() {
		UserUserMatrix uUM = new UserUserMatrix(4,4);
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter.next()).thenReturn(1,1,1,1,1);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter1.next()).thenReturn(1,1,1,1,1);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter2.next()).thenReturn(1,1,1,1,1);
		MatrixRowIterator iter3 = mock(MatrixRowIterator.class);
		when(iter3.hasNext()).thenReturn(true,true,true,true,true,false);
		when(iter3.next()).thenReturn(1,1,1,1,1);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		when(uMM.createRowIterator(2)).thenReturn(iter2);
		when(uMM.createRowIterator(3)).thenReturn(iter3);
		UserUserMatrixPopulator populator = new UserUserMatrixPopulator(uUM,uMM);
		populator.populateUserUserMatrix();
		Float[][] uUMMatrix = {{1.0000f,1.0000f,1.0000f,1.0000f},
				{1.0000f,1.0000f,1.0000f,1.0000f},{1.0000f,1.0000f,1.0000f,1.0000f},
				{1.0000f,1.0000f,1.0000f,1.0000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 4;i++) {
			Iterator<Float> uUMVals = uUM.createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues && round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(populator.hasPopulated());
	}
	
	@Test
	 public void testPopulateRandomExampleSquare() {
		UserUserMatrix uUM = new UserUserMatrix(3,3);
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,false);
		when(iter.next()).thenReturn(2,3,4);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,false);
		when(iter1.next()).thenReturn(5,4,2);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,false);
		when(iter2.next()).thenReturn(4,5,3);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		when(uMM.createRowIterator(2)).thenReturn(iter2);
		UserUserMatrixPopulator populator = new UserUserMatrixPopulator(uUM,uMM);
		populator.populateUserUserMatrix();
		Float[][] uUMMatrix = {{1.0000f,0.2109f,0.2500f},
				{0.2109f,1.0000f,0.3660f},
				{0.2500f,0.3660f,1.000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 3;i++) {
			Iterator<Float> uUMVals = uUM.createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues && round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(populator.hasPopulated());
	}
	
	@Test
	 public void testPopulateRandomExampleMoreRowsThanCols() {
		UserUserMatrix uUM = new UserUserMatrix(4,4);
		UserMovieMatrix uMM = mock(UserMovieMatrix.class);
		MatrixRowIterator iter = mock(MatrixRowIterator.class);
		when(iter.hasNext()).thenReturn(true,true,true,false);
		when(iter.next()).thenReturn(1,4,2);
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,false);
		when(iter1.next()).thenReturn(2,2,5);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,false);
		when(iter2.next()).thenReturn(5,5,3);
		MatrixRowIterator iter3 = mock(MatrixRowIterator.class);
		when(iter3.hasNext()).thenReturn(true,true,true,false);
		when(iter3.next()).thenReturn(1,1,1);
		when(uMM.createRowIterator(0)).thenReturn(iter);
		when(uMM.createRowIterator(1)).thenReturn(iter1);
		when(uMM.createRowIterator(2)).thenReturn(iter2);
		when(uMM.createRowIterator(3)).thenReturn(iter3);
		UserUserMatrixPopulator populator = new UserUserMatrixPopulator(uUM,uMM);
		populator.populateUserUserMatrix();
		Float[][] uUMMatrix = {{1.0000f,0.2109f,0.1907f,0.2403f},
				{0.2109f,1.0000f,0.1757f,0.1907f},
				{0.1907f,0.1757f,1.000f,0.1429f},
				{0.2403f,0.1907f,0.1429f,1.0000f}};
		boolean correctValues = true;
		int row = 0;
		int col = 0;
		for (int i = 0; i < 4;i++) {
			Iterator<Float> uUMVals = uUM.createRowIterator(i);
			while (uUMVals.hasNext()) {
				float curr = uUMVals.next();
				correctValues = correctValues && round(curr,4) == uUMMatrix[row][col];
				col++;
			}
			row++;
			col = 0;
		}
		assertTrue(populator.hasPopulated());
	}
	
}