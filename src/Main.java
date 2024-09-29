import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Complex[][] data1 = {
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        };
        Complex[][] data2 = {
                {new Complex(2, 0), new Complex(1, 1)},
                {new Complex(4, 0), new Complex(0, 2)}
        };

        ComplexMatrix mat1 = new ComplexMatrix(data1);
        ComplexMatrix mat2 = new ComplexMatrix(data2);

        System.out.println("Matrix 1:\n" + mat1);
        System.out.println("Matrix 2:\n" + mat2);

        ComplexMatrix sum = mat1.add(mat2);
        System.out.println("Sum:\n" + sum);

        ComplexMatrix product = mat1.multiply(mat2);
        System.out.println("Product:\n" + product);

        Complex det = product.determinant();
        System.out.println("Determinant of product: " + det);
    }
}