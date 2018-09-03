package csc.summer2018.cscc01;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimilarityFinderTest {
	
	@Test
	 public void testGetHighestValueSimilarUsers() {
		SimilarityFinder finder = new SimilarityFinder();
		finder.addToSimilarList(0.111134f, 1, 1);
		finder.addToSimilarList(0.0107423f, 3, 1);
		finder.addToSimilarList(0.20324324f, 1, 2);
		finder.addToSimilarList(0.4532432f, 2, 2);
		finder.addToSimilarList(0.222243f, 2, 3);
		finder.addToSimilarList(0.3222432f, 1, 4);
		assertTrue(finder.getHighestValueSimilarUsers().equals("0.4532"));
	}
	
	@Test
	 public void testGetLowestValueSimilarUsers() {
		SimilarityFinder finder = new SimilarityFinder();
		finder.addToDissimilarList(0.111134f, 1, 1);
		finder.addToDissimilarList(0.0107423f, 3, 1);
		finder.addToDissimilarList(0.20324324f, 1, 2);
		finder.addToDissimilarList(0.4532432f, 2, 2);
		finder.addToDissimilarList(0.222243f, 2, 3);
		finder.addToDissimilarList(0.3222432f, 1, 4);
		assertTrue(finder.getLowestValueDissimilarUsers().equals("0.0107"));
	}
	
	@Test
	 public void testAddGreaterClearsOutList() {
		SimilarityFinder finder = new SimilarityFinder();
		finder.addToSimilarList(0.111134f, 1, 1);
		finder.addToSimilarList(0.0107423f, 4, 1);
		finder.addToSimilarList(0.10324324f, 1, 2);
		// add one that is greater
		finder.addToSimilarList(0.222232432f, 3, 3);
		boolean clearedOutList = true;
		for (String val: finder.getSimilarUsers()) {
			// user values are i+1
			clearedOutList = clearedOutList && (!val.contains("User2") 
					|| !val.contains("User5") || !val.contains("User3"));
			clearedOutList = clearedOutList && val.contains("User4");
		}
		clearedOutList = clearedOutList && finder.getSimilarUsers().size() == 1;
		assertTrue(clearedOutList);
	}
	
	@Test
	 public void testAddEqualAddsToSimilarList() {
		SimilarityFinder finder = new SimilarityFinder();
		finder.addToSimilarList(0.1111f, 2, 1);
		finder.addToSimilarList(0.1111f, 4, 1);
		finder.addToSimilarList(0.1111f, 3, 1);
		boolean containsAllAdded = true;
		for (String val: finder.getSimilarUsers()) {
			// user values are i+1
			containsAllAdded = containsAllAdded && (val.contains("User5") 
					|| val.contains("User4") || val.contains("User3"));
		}
		containsAllAdded = containsAllAdded && finder.getSimilarUsers().size() == 3;
		assertTrue(containsAllAdded);
	}
	
	@Test
	 public void testAddLesserClearsOutList() {
		SimilarityFinder finder = new SimilarityFinder();
		finder.addToDissimilarList(0.111134f, 1, 1);
		finder.addToDissimilarList(0.4332423f, 4, 1);
		finder.addToDissimilarList(0.64434324f, 1, 2);
		// add one that is greater
		finder.addToDissimilarList(0.02132432f, 3, 3);
		boolean clearedOutList = true;
		for (String val: finder.getDissimilarUsers()) {
			// user values are i+1
			clearedOutList = clearedOutList && (!val.contains("User2") 
					|| !val.contains("User5") || !val.contains("User3"));
			clearedOutList = clearedOutList && val.contains("User4");
		}
		clearedOutList = clearedOutList && finder.getDissimilarUsers().size() == 1;
		assertTrue(clearedOutList);
	}
	
	@Test
	 public void testAddEqualAddsToDissimilarList() {
		SimilarityFinder finder = new SimilarityFinder();
		finder.addToDissimilarList(0.1111f, 2, 1);
		finder.addToDissimilarList(0.1111f, 4, 1);
		finder.addToDissimilarList(0.1111f, 3, 1);
		boolean containsAllAdded = true;
		for (String val: finder.getDissimilarUsers()) {
			// user values are i+1
			containsAllAdded = containsAllAdded && (val.contains("User5") 
					|| val.contains("User4") || val.contains("User3"));
		}
		containsAllAdded = containsAllAdded && finder.getDissimilarUsers().size() == 3;
		assertTrue(containsAllAdded);
	}

}