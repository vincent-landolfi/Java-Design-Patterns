package csc.summer2018.cscc01;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SimilarityFinder {
	
	private List<String> similarUsers = new ArrayList<String>();
	private List<String> dissimilarUsers = new ArrayList<String>();
	private DecimalFormat df4 = new DecimalFormat("0.0000");
	public float highestVal = -1;
	public float lowestVal = 2;
	
	/**
	 * Adds the given similarity score to the similar users list
	 * @param simScore The similarity score to add
	 * @param user1 The first compared users number
	 * @param user2 The second compared users number
	 * @return The highest value simscore that has been passed to this instance
	 */
	public float addToSimilarList(float simScore, int user1, int user2) {
		// if the given simscore is higher than the highest val
		if (simScore > highestVal) {
			// clear out the similar users list
			similarUsers.clear();
			// set the simscore to the new highest score
			highestVal = simScore;
		}
		// add the pair of users to the similar list
		similarUsers.add("User" + (user1 + 1) + " and " + "User" + (user2 + 1));
		// return the highest value
		return highestVal;
	}
	
	/**
	 * Adds the given similarity score to the dissimilar users list
	 * @param simScore The similarity score to add
	 * @param user1 The first compared users number
	 * @param user2 The second compared users number
	 * @return The lowest value simscore that has been passed to this instance
	 */
	public float addToDissimilarList(float simScore, int user1, int user2) {
		// if the given simscore is lower than the lowest val
		if (simScore < lowestVal) {
			// clear out the dissimilar users list
			dissimilarUsers.clear();
			// set the simscore to the new lowest score
			lowestVal = simScore;
		}
		// add the pair of users to the dissimilar list
		dissimilarUsers.add("User" + (user1 + 1) + " and " + "User" + (user2 + 1));
		// return the lowest value
		return lowestVal;
	}
	
	/**
	 * Gets the highest similarity score this instance has been passed
	 * @return Highest similarity score passed to this instance, 4 decimal places
	 */
	public String getHighestValueSimilarUsers() {
		// highest similarity score, 4 decimal places
		return df4.format(highestVal);
	}
	
	/**
	 * Gets the lowest similarity score this instance has been passed
	 * @return Lowest similarity score passed to this instance, 4 decimal places
	 */
	public String getLowestValueDissimilarUsers() {
		// lowest similarity score, 4 decimal places
		return df4.format(lowestVal);
	}
	
	/**
	 * Gives this list of most similar users
	 * @return The list of similar users
	 */
	public List<String> getSimilarUsers() {
		// return list of similar users
		return similarUsers;
	}
	
	/**
	 * Gives this list of most dissimilar users
	 * @return The list of dissimilar users
	 */
	public List<String> getDissimilarUsers() {
		// list of dissimilar users
		return dissimilarUsers;
	}

}