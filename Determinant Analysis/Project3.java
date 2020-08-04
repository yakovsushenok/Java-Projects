/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class, as well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param m           The matrix object that will be filled with random
     *                    samples.
     * @param numSamples  The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix m, int numSamples) {
		double detSum = 0;
		double detsqrdSum = 0;
		double var;
		
		for (int i = 0; i < numSamples; i++) {
			m.random();
			detSum += m.determinant();
			detsqrdSum += m.determinant()*m.determinant();			
		}
		var = (detsqrdSum / numSamples) - ((detSum / numSamples)* (detSum / numSamples));
		
		return var;
    }
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50. See the formulation for more detail.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
		for (int i = 2; i < 51; i++) {
			GeneralMatrix g = new GeneralMatrix(i,i);
			TriMatrix t = new TriMatrix(i);
			
			double var1 = matVariance(g,15000);
			double var2 = matVariance(t,150000);
			
			System.out.println( i +" " + var1 +" "+var2);
			
		}
    }
}