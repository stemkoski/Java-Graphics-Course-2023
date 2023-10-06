import graphics.math.*;

public class Test_Matrix
{

    public static void main()
    {       
        System.out.println("Testing matrix class:");
        
        Matrix m = new Matrix(2,3);
        m.setValues(1,2,3,4,5,6);
        System.out.println("Matrix M:");
        System.out.println(m);
    
        Matrix n = m.transpose();
        System.out.println("Matrix N = M^T:");
        System.out.println(n);

        System.out.println("Matrix M * N:");
        System.out.println(Matrix.multiply(m, n));
        System.out.println("Matrix N * M:");
        System.out.println(Matrix.multiply(n, m));
        System.out.println("Since M * N != N * M, matrix multiplication is not commutative.");
        
        Matrix p = new Matrix(2,2);
        p.setValues(1,2,3,4);
        System.out.println("Matrix P:");
        System.out.println(p);

        
        System.out.println("Determinant of P:");
        System.out.println( p.determinant() );
        Matrix pInv = p.inverse();
        System.out.println("Inverse: P^(-1):");
        System.out.println(pInv);
        System.out.println("P * P^(-1):");
        System.out.println( Matrix.multiply(p, pInv) );
        System.out.println("P^(-1) * P:");
        System.out.println( Matrix.multiply(pInv, p) );
        System.out.println("Both of the above should be the identity matrix I.");
    
        Vector v = new Vector(5, 6);
        System.out.println("Vector V:");
        System.out.println(v);
        
        System.out.println("Matrix-Vector product P * V:");
        System.out.println(p.multiplyVector(v));
        
    }

}
