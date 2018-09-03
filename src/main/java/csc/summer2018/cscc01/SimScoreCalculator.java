package csc.summer2018.cscc01;

import java.util.Iterator;

public class SimScoreCalculator {
	
	public UserMovieMatrix userMovieMatrix;
	
	/**
	 * 
	 * @param userMovieMatrix
	 */
	public SimScoreCalculator(UserMovieMatrix userMovieMatrix) {
		this.userMovieMatrix = userMovieMatrix;
	}
	
	/**
	 * Finds the distance between two users
	 * @param user1 The number of the first user
	 * @param user2 The number of the second user
	 * @return The distance between the two given users
	 */
	public double findDistance(int user1, int user2) {
		// instantiate distance
		double distance = 0;
		// row iterator for the first users row
		Iterator<Integer> iter1 = userMovieMatrix.createRowIterator(user1);
		// row iterator for the second users row
		Iterator<Integer> iter2 = userMovieMatrix.createRowIterator(user2);
		// while there are elements in both rows
		while(iter1.hasNext() && iter2.hasNext()) {
			// distance calculation
			distance += Math.pow((iter1.next() - iter2.next()), 2);
		}
		// sqrt to get distance
		return Math.sqrt(distance);
	}
	
	/**
	 * Finds the similarity score between two users
	 * @param user1 The number of the first user
	 * @param user2 The number of the second user
	 * @return The similarity score between the two users
	 */
	public float findSimScore(int user1, int user2) {
		// calculation for finding sim score
		return (float) (1 / (1 + findDistance(user1,user2)));
	}

}