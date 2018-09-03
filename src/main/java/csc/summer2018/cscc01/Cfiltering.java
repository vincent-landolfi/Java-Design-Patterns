package csc.summer2018.cscc01;

import java.util.List;

public class Cfiltering {
    // this is a 2d matrix i.e. user*movie
    private UserMovieMatrix userMovieMatrix;
	// this is a 2d matrix i.e. user*movie
    private UserUserMatrix userUserMatrix;
    // create a decimal format instance
    // to get all the values of the userUserMatrix in 4 decimal places
    private UserUserMatrixPopulator populator;
    
    /**
     * Default Constructor.
     */
    public Cfiltering() {
        // this is 2d matrix of size 1*1
        userMovieMatrix = new UserMovieMatrix(1,1); // mine
        // this is 2d matrix of size 1*1
        userUserMatrix = new UserUserMatrix(1,1); // mine
        // populator to populate userUserMatrix
        populator = new UserUserMatrixPopulator(userUserMatrix,userMovieMatrix);
    }
    
    /**
     * Constructs an object which contains two 2d matrices, one of size
     * users*movies which will store integer movie ratings and one of size
     * users*users which will store float similarity scores between pairs of
     * users.
     *
     * @param numberOfUsers Determines size of matrix variables.
     * @param numberOfMovies Determines size of matrix variables.
     */
    public Cfiltering(int numberOfUsers, int numberOfMovies) {
        // this is a 2d matrix of size numberOfUsers*numberOfMovies
        userMovieMatrix = new UserMovieMatrix(numberOfUsers, numberOfMovies);
        // this is a 2d matrix of size numberOfUsers*numberOfUsers
        userUserMatrix = new UserUserMatrix(numberOfUsers, numberOfUsers);
        // populator to populate userUserMatrix
        populator = new UserUserMatrixPopulator(userUserMatrix,userMovieMatrix);
    }
    
    /**
     * The purpose of this method is to populate the UserMovieMatrix. As input
     * parameters it takes in a rowNumber, columnNumber and a rating value. The
     * rating value is then inserted in the UserMovieMatrix at the specified
     * rowNumber and the columnNumber.
     *
     * @param rowNumber The row number of the userMovieMatrix.
     * @param columnNumber The column number of the userMovieMatrix.
     * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
     */
    public void populateUserMovieMatrix(int rowNumber, int columnNumber,
                                        int ratingValue) {
    	// set the value at that index
        userMovieMatrix.setValue(rowNumber, columnNumber, ratingValue);
    }
    
    /**
     * Determines how similar each pair of users is based on their ratings. This
     * similarity value is represented with with a float value between 0 and 1,
     * where 1 is perfect/identical similarity. Stores these values in the
     * userUserMatrix.
     */
    public void calculateSimilarityScore() {
    	// populate UserUserMatrix
    	populator.populateUserUserMatrix();
    }
    
    /**
     * Prints out the similarity scores of the userUserMatrix, with each row and
     * column representing each/single user and the cell position (i,j)
     * representing the similarity score between user i and user j.
     */
    public void printUserUserMatrix() {
    	// make sure the UUM is populated
    	checkForPopulation();
    	// print out the UUM
    	userUserMatrix.print();
    }

    /**
     * This function finds and prints the most similar pair of users in the
     * userUserMatrix.
     */
    
    public void findAndprintMostSimilarPairOfUsers() {
    	// make sure UUM is populated
    	checkForPopulation();
        // opener for printing similar users
        System.out.println("The most similar pairs of users from above userUserMatrix are: ");
        // get list of similar users
        List<String> similarUsers = populator.getSimilarityFinder().getSimilarUsers();
        // go through pairs of similar users
        for (String simPair: similarUsers) {
        	// print out the pair of similar users
        	System.out.println(simPair);
        }
        // print out highest similarity score
        System.out.println("with similarity score of " + populator.getSimilarityFinder().getHighestValueSimilarUsers());
    }
    
    /**
     * This function finds and prints the most dissimilar pair of users in the
     * userUserMatrix.
     */
    public void findAndprintMostDissimilarPairOfUsers() {
    	// make sure UUM is populated
    	checkForPopulation();
        // print the first two users with the lowest similarity score
        System.out.println("The most dissimilar pairs of users from above userUserMatrix are: ");
        // get list of dissimilar users
        List<String> dissimilarUsers = populator.getSimilarityFinder().getDissimilarUsers();
        // go through pairs of dissimilar users
        for (String dissimPair: dissimilarUsers) {
        	// print out the pair
        	System.out.println(dissimPair);
        }
        // print out lowest similarity score
        System.out.println("with similarity score of " + populator.getSimilarityFinder().getLowestValueDissimilarUsers());
    }
    
    /**
     * Checks if the userUserMatrix has been populated. If it has not been populated,
     * populates it.
     */
    public void checkForPopulation() {
    	// if the userUserMatrix has not been populated
    	if (!populator.hasPopulated()) {
    		// populate it
    		calculateSimilarityScore();
    	}
    }
    
    /**
     * Getter method for the userMovieMatrix
     * @return The userMovieMatrix this instance is using.
     */
    public UserMovieMatrix getUserMovieMatrix() {
		return userMovieMatrix;
	}

    /**
     * Sets the user movie matrix for this instance
     * @param userMovieMatrix The userMovieMatrix for this instance
     */
	public void setUserMovieMatrix(UserMovieMatrix userMovieMatrix) {
		this.userMovieMatrix = userMovieMatrix;
		this.populator = new UserUserMatrixPopulator(userUserMatrix,userMovieMatrix);
	}

	/**
	 * Getter for the UserUserMatrix
	 * @return The UserUserMatrix for this instance
	 */
	public UserUserMatrix getUserUserMatrix() {
		return userUserMatrix;
	}
	
	/**
	 * Sets the UserUserMatrix for this instance
	 * @param userUserMatrix The userUserMatrix for this instance
	 */
	public void setuserUserMatrix(UserUserMatrix userUserMatrix) {
		this.userUserMatrix = userUserMatrix;
		this.populator = new UserUserMatrixPopulator(userUserMatrix,userMovieMatrix);
	}
}
