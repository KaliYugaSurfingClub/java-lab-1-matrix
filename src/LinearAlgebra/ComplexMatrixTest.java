package LinearAlgebra;

public class ComplexMatrixTest {
    public static void run() {
        testAdd();
        testSubtract();
        testMultiplyByScalar();
        testMultiplyByMatrix();
        testTranspose();
        testDeterminant();
        testMatrixAdditionWithDifferentDimensions();
    }

    private static void testAdd() {
        Complex[][] data1 = {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        };

        Complex[][] data2 = {
                { new Complex(1, 1), new Complex(1, 1) },
                { new Complex(1, 1), new Complex(1, 1) }
        };

        ComplexMatrix matrix1 = new ComplexMatrix(data1);
        ComplexMatrix matrix2 = new ComplexMatrix(data2);

        ComplexMatrix expected = new ComplexMatrix(new Complex[][]{
                { new Complex(2, 3), new Complex(4, 5) },
                { new Complex(6, 7), new Complex(8, 9) }
        });

        ComplexMatrix result = matrix1.add(matrix2);
        assertEqual(expected, result, "testAdd");
    }

    private static void testSubtract() {
        Complex[][] data1 = {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        };

        Complex[][] data2 = {
                { new Complex(1, 1), new Complex(1, 1) },
                { new Complex(1, 1), new Complex(1, 1) }
        };

        ComplexMatrix matrix1 = new ComplexMatrix(data1);
        ComplexMatrix matrix2 = new ComplexMatrix(data2);

        ComplexMatrix expected = new ComplexMatrix(new Complex[][]{
                { new Complex(0, 1), new Complex(2, 3) },
                { new Complex(4, 5), new Complex(6, 7) }
        });

        ComplexMatrix result = matrix1.subtract(matrix2);
        assertEqual(expected, result, "testSubtract");
    }

    private static void testMultiplyByScalar() {
        Complex[][] data = {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        };

        ComplexMatrix matrix = new ComplexMatrix(data);
        ComplexMatrix expected = new ComplexMatrix(new Complex[][]{
                { new Complex(2, 4), new Complex(6, 8) },
                { new Complex(10, 12), new Complex(14, 16) }
        });

        ComplexMatrix result = matrix.multiply(2);
        assertEqual(expected, result, "testMultiplyByScalar");
    }

    private static void testMultiplyByMatrix() {
        Complex[][] data1 = {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        };

        Complex[][] data2 = {
                { new Complex(1, 1), new Complex(1, 1) },
                { new Complex(1, 1), new Complex(1, 1) }
        };

        ComplexMatrix matrix1 = new ComplexMatrix(data1);
        ComplexMatrix matrix2 = new ComplexMatrix(data2);

        ComplexMatrix expected = new ComplexMatrix(new Complex[][]{
                { new Complex(-2, 10), new Complex(-2, 10) },
                { new Complex(-2, 26), new Complex(-2, 26) }
        });

        ComplexMatrix result = matrix1.multiply(matrix2);
        assertEqual(expected, result, "testMultiplyByMatrix");
    }

    private static void testTranspose() {
        Complex[][] data = {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        };

        ComplexMatrix matrix = new ComplexMatrix(data);
        ComplexMatrix expected = new ComplexMatrix(new Complex[][]{
                { new Complex(1, 2), new Complex(5, 6) },
                { new Complex(3, 4), new Complex(7, 8) }
        });

        ComplexMatrix result = matrix.transpose();
        assertEqual(expected, result, "testTranspose");
    }

    private static void testDeterminant() {
        Complex[][] data = {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        };

        ComplexMatrix matrix = new ComplexMatrix(data);
        Complex expected = new Complex(0, -16);
        Complex det = matrix.determinant();
        assertEqual(expected, det, "testDeterminant");
    }

    private static void testMatrixAdditionWithDifferentDimensions() {
        ComplexMatrix matrix1 = new ComplexMatrix(2, 2);
        ComplexMatrix matrix2 = new ComplexMatrix(3, 3);
        try {
            matrix1.add(matrix2);
            System.out.println("testMatrixAdditionWithDifferentDimensions failed: expected exception not thrown");
        } catch (ArithmeticException e) {
            System.out.println("testMatrixAdditionWithDifferentDimensions passed");
        }
    }

    private static void assertEqual(ComplexMatrix expected, ComplexMatrix actual, String testName) {
        if (!expected.toString().equals(actual.toString())) {
            System.out.println(testName + " failed: expected " + expected + ", but got " + actual);
        } else {
            System.out.println(testName + " passed");
        }
    }

    private static void assertEqual(Complex expected, Complex actual, String testName) {
        if (!expected.equals(actual)) {
            System.out.println(testName + " failed: expected " + expected + ", but got " + actual);
        } else {
            System.out.println(testName + " passed");
        }
    }
}
