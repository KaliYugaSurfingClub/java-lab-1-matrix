import java.util.Arrays;

class ComplexMatrix {
    private final Complex[][] matrix;

    ComplexMatrix(int rows, int cols) {
        matrix = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Complex(0, 0);
            }
        }
    }

    ComplexMatrix(Complex[][] matrix) {
        this.matrix = matrix;
    }

    int getRowsCount() {
        return matrix.length;
    }

    int getColumnsCount() {
        return matrix[0].length;
    }

    ComplexMatrix add(ComplexMatrix other) {
        if (getColumnsCount() != other.getColumnsCount() || getRowsCount() != other.getRowsCount()) {
            throw new ArithmeticException();
        }

        ComplexMatrix result = new ComplexMatrix(getRowsCount(), getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[i][j] = this.matrix[i][j].add(other.matrix[i][j]);
            }
        }

        return result;
    }

    ComplexMatrix subtract(ComplexMatrix other) {
        return add(other.multiply(-1));
    }

    ComplexMatrix multiply(int num) {
        ComplexMatrix result = new ComplexMatrix(getRowsCount(), getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[i][j] = this.matrix[i][j].multiply(num);
            }
        }

        return result;
    }

    ComplexMatrix multiply(ComplexMatrix other) {
        if (getColumnsCount() != other.getRowsCount()) {
            throw new ArithmeticException("Invalid matrix dimensions for multiplication");
        }

        ComplexMatrix result = new ComplexMatrix(getRowsCount(), other.getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < other.getColumnsCount(); j++) {
                Complex sum = new Complex(0, 0);

                for (int k = 0; k < getColumnsCount(); k++) {  // здесь должно быть getColumnsCount() первой матрицы
                    sum = sum.add(this.matrix[i][k].multiply(other.matrix[k][j]));
                }
                result.matrix[i][j] = sum;
            }
        }

        return result;
    }

    ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(getColumnsCount(), getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result.matrix[j][i] = this.matrix[i][j];
            }
        }

        return result;
    }

    Complex determinant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new ArithmeticException("Matrix must be square to calculate determinant");
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Complex[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}
