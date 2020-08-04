/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;
    
    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
        // You need to fill in this method.
		super(N,N);
		diag = new double[N];
		upper = new double[N-1];
		lower = new double[N-1];
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // You need to fill in this method.
		if (i < 0 || i >= diag.length || j < 0 || j >= diag.length) {
			throw new MatrixException("Array element out of bounds");
		}
		
		if (i == j) {
			return diag[i];
		}
		
		else if (i == j+1) {
			return lower[j];
		}
		
		else if (j == i+1) {
			return upper[i];
		}
		
		else {
			return 0;
		}
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {
		
		if (i == j) {
			diag[i] = val;
		}
		
		else if (i == j+1) {
			lower[j] = val;
		}
		
		else if (j == i+1) {
			upper[i] = val;
		}
		
		else {
			throw new MatrixException("Array element not in tridiagonal");
		}
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
		double det = 1;
		TriMatrix lu = decomp();
		
		for (int i=0; i < lu.m; i++) {
			det = lu.diag[i]*det;
		}
		
		return det;
    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
        // You need to fill in this method.
		TriMatrix lu = new TriMatrix(diag.length);
		
		lu.upper[0] = this.upper[0]; //puting in the top three values into the matrix
		lu.diag[0] = this.diag[0];
		lu.lower[0] = this.lower[0]/lu.diag[0];
		
		
		for (int i=1;i < this.n-1; i++) { //performing the algorithm on everything apart from the very bottom right diag as it would
			double u = this.upper[i]	;//throw a arrayoutofbounds error for upper and lower
			double d = this.diag[i]-(lu.lower[i-1]*lu.upper[i-1]);
			double l = this.lower[i]/d;
			
			lu.upper[i] = u;
			lu.diag[i] = d;
			lu.lower[i] = l;
		}
		lu.diag[n-1] = this.diag[n-1]-lu.lower[n-2]*lu.upper[n-2];//putting in the bottom right value
		
		return lu;
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){
        // You need to fill in this method.
		if (this.m != A.m || this.n != A.n) {
			throw new MatrixException("Matrices are not same size");
		}
		
		
		GeneralMatrix add = new GeneralMatrix(this.m,this.n);
		
		for (int i = 0; i < this.m ; i++) {
			for (int j = 0; j < this.n; j++) {
				add.setIJ(i,j, getIJ(i,j) + A.getIJ(i,j));
			}
		}
		
		return add;
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
		if (this.n != A.m ) {
			throw new MatrixException("Matrices are not correct size");
		}
		
		GeneralMatrix Multiply = new GeneralMatrix(this.m, A.n);
		
		for (int i=0; i < this.m; i++) {
			for (int j=0; j < A.n; j++) {
				double sum = 0;
				
				for (int k=0; k < A.m; k++) {
					sum = sum + getIJ(i,k)*A.getIJ(k,j);
				}
				Multiply.setIJ(i,j,sum);
			}
		}		
	
		return Multiply;
    }
    
    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
		
		TriMatrix product = new TriMatrix(this.m);
		
		for (int i =0; i < this.m -1; i++) {
			product.diag[i] = this.diag[i]*a;
			product.upper[i] = this.upper[i]*a;
			product.lower[i] = this.lower[i]*a;
		}
		product.diag[this.m - 1] = this.diag[this.m -1]*a;
		
		return product;
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
		for (int i = 0; i < this.m -1; i++) {
			diag[i] = Math.random();
			lower[i] = Math.random();
			upper[i] = Math.random();
		}
		diag[this.m-1] = Math.random();
    }
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
		//TriMatrix a = new TriMatrix(6);
		//a.random();
		//System.out.println(a.toString());
    }
}