package csc.summer2018.cscc01;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class UserUserMatrixTest {
	
	@Test
	 public void testConstructorFloat() {
		UserUserMatrix matrix = new UserUserMatrix(3,4);
		assertTrue(matrix.cols == 4 && matrix.rows == 3);
	}
	
	@Test
	 public void testPrintMatrix() {
		UserUserMatrix matrix = mock(UserUserMatrix.class);
		matrix.rows = 3;
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,false);
		when(iter1.next()).thenReturn((float)0.2322333,(float)0.33212,(float)0.222211);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,false);
		when(iter2.next()).thenReturn((float)0.333322,(float)0.11213,(float)0.21213);
		MatrixRowIterator iter3 = mock(MatrixRowIterator.class);
		when(iter3.hasNext()).thenReturn(true,true,true,false);
		when(iter3.next()).thenReturn((float)0.31132,(float)0.13221,(float)0.01071);
		when(matrix.createRowIterator(0)).thenReturn(iter1);
		when(matrix.createRowIterator(1)).thenReturn(iter2);
		when(matrix.createRowIterator(2)).thenReturn(iter3);
		doCallRealMethod().when(matrix).print();
		doCallRealMethod().when(matrix).setDFTestMode();
		matrix.setDFTestMode();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    matrix.print();
	    System.out.flush();
	    System.setOut(old);
	    String expectedOut = "[0.2322, 0.3321, 0.2222]\n";
	    expectedOut += "[0.3333, 0.1121, 0.2121]\n";
	    expectedOut += "[0.3113, 0.1322, 0.0107]\n";
		assertTrue(baos.toString().equals(expectedOut));
	}
	
	@Test
	 public void testPrintMatrixDifferentSize() {
		UserUserMatrix matrix = mock(UserUserMatrix.class);
		matrix.rows = 2;
		MatrixRowIterator iter1 = mock(MatrixRowIterator.class);
		when(iter1.hasNext()).thenReturn(true,true,true,false);
		when(iter1.next()).thenReturn((float)0.2322333,(float)0.33212,(float)0.222211);
		MatrixRowIterator iter2 = mock(MatrixRowIterator.class);
		when(iter2.hasNext()).thenReturn(true,true,true,true,false);
		when(iter2.next()).thenReturn((float)0.333322,(float)0.11213,(float)0.21213,(float)0.1111111);
		when(matrix.createRowIterator(0)).thenReturn(iter1);
		when(matrix.createRowIterator(1)).thenReturn(iter2);
		doCallRealMethod().when(matrix).print();
		doCallRealMethod().when(matrix).setDFTestMode();
		matrix.setDFTestMode();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    matrix.print();
	    System.out.flush();
	    System.setOut(old);
	    String expectedOut = "[0.2322, 0.3321, 0.2222]\n";
	    expectedOut += "[0.3333, 0.1121, 0.2121, 0.1111]\n";
		assertTrue(baos.toString().equals(expectedOut));
	}

}
