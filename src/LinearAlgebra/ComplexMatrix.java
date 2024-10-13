package LinearAlgebra;

import java.util.Arrays;

public class ComplexMatrix {
    private final Complex[][] matrix;

    ComplexMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive");
        }

        matrix = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Complex(0, 0); // Initialize with 0+0i
            }
        }
    }

    public ComplexMatrix(Complex[][] matrix) {
        this.matrix = matrix;
    }

    public int getRowsCount() {
        return matrix.length;
    }

    public int getColumnsCount() {
        return matrix[0].length;
    }

    public ComplexMatrix add(ComplexMatrix other) {
        checkDimensions(other);

        ComplexMatrix result = new ComplexMatrix(getRowsCount(), getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[i][j] = this.matrix[i][j].add(other.matrix[i][j]);
            }
        }

        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        checkDimensions(other);

        ComplexMatrix result = new ComplexMatrix(getRowsCount(), getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[i][j] = this.matrix[i][j].subtract(other.matrix[i][j]);
            }
        }

        return result;
    }

    public ComplexMatrix multiply(double scalar) {

        ComplexMatrix result = new ComplexMatrix(getRowsCount(), getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[i][j] = this.matrix[i][j].multiply(scalar);
            }
        }

        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (getColumnsCount() != other.getRowsCount()) {
            throw new ArithmeticException("Matrix multiplication not possible, invalid dimensions");
        }

        ComplexMatrix result = new ComplexMatrix(getRowsCount(), other.getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < other.getColumnsCount(); j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < getColumnsCount(); k++) {
                    sum = sum.add(this.matrix[i][k].multiply(other.matrix[k][j]));
                }
                result.matrix[i][j] = sum;
            }
        }

        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(getColumnsCount(), getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[j][i] = this.matrix[i][j];
            }
        }

        return result;
    }

    public Complex determinant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new ArithmeticException("Matrix must be square to compute determinant");
        }

        if (getRowsCount() == 1) return matrix[0][0];

        Complex det = new Complex(0, 0);

        for (int i = 0; i < getColumnsCount(); i++) {
            det = det.add(matrix[0][i].multiply(cofactor(0, i)));
        }

        return det;
    }

    private Complex cofactor(int row, int col) {
        Complex[][] minorMatrix = new Complex[getRowsCount() - 1][getColumnsCount() - 1];

        for (int i = 0, p = 0; i < getRowsCount(); i++) {
            if (i == row) continue;

            for (int j = 0, q = 0; j < getColumnsCount(); j++) {
                if (j == col) continue;
                minorMatrix[p][q] = matrix[i][j];
                q++;
            }

            p++;
        }

        ComplexMatrix minor = new ComplexMatrix(minorMatrix);
        Complex sign = ((row + col) % 2 == 0) ? new Complex(1, 0) : new Complex(-1, 0);

        return sign.multiply(minor.determinant());
    }

    private void checkDimensions(ComplexMatrix other) {
        if (this.getRowsCount() != other.getRowsCount() || this.getColumnsCount() != other.getColumnsCount()) {
            throw new ArithmeticException("Matrices must have the same dimensions");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Complex[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}
