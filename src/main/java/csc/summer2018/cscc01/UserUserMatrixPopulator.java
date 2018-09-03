package csc.summer2018.cscc01;

public class UserUserMatrixPopulator {
	
	private UserUserMatrix userUserMatrix;
	private SimilarityFinder finder;
	private SimScoreCalculator calculator;
	private boolean hasPopulated = false;
	
	/**
	 * Creates a new UserUserMatrixPopulator
	 * @param userUserMatrix The matrix to be populated
	 * @param userMovieMatrix The matrix to get data from
	 */
	public UserUserMatrixPopulator(UserUserMatrix userUserMatrix,UserMovieMatrix userMovieMatrix) {
		// set class vals
		this.userUserMatrix = userUserMatrix;
		this.calculator = new SimScoreCalculator(userMovieMatrix);
	}
	
	/**
	 * Populates this classes UserUserMatrix
	 */
	public void populateUserUserMatrix() {
		// new similarity finder
		finder = new SimilarityFinder();
		// go through UUM rows
		for (int i = 0; i < userUserMatrix.rows; i++) {
			// go through UUM cols, same number as rows
			for (int j = i; j < userUserMatrix.rows; j++) {
				// get the similarity score
				float simScore = calculator.findSimScore(i,j);
				// since matrices are symmetric, set values at
				// ij and ji
				userUserMatrix.setValue(i, j, simScore);
				userUserMatrix.setValue(j, i, simScore);
				// if we have a simscore that is higher, or equal
				// to the highest one we've seen
				if (simScore >= finder.highestVal && i != j) {
					// add it to the highest similarity list
					finder.addToSimilarList(simScore, i, j);
				}
				// if we have a simscore that is lower, or equal
				// to the lowest one we've seen
				if (simScore <= finder.lowestVal && i != j) {
					// add it to the lowest similarity list
					finder.addToDissimilarList(simScore, i, j);
				}
			}
		}
		// we have populated this UUM
		hasPopulated = true;
	}
	
	/**
	 * Gets the similarity finder for this instance
	 * @return The SimilarityFinder for this instance
	 */
	public SimilarityFinder getSimilarityFinder() {
		return this.finder;
	}
	
	/**
	 * Returns true iff this UUM has been populated
	 * @return If this UUM have been populated
	 */
	public boolean hasPopulated() {
		return hasPopulated;
	}
	
	/**
	 * Sets the UUM for this instance
	 * @param userUserMatrix The UUM to set this UUM to
	 */
	public void setUUM(UserUserMatrix userUserMatrix) {
		this.userUserMatrix = userUserMatrix;
	}
	
}