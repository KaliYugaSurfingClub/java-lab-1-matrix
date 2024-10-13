package LinearAlgebra;

public class Complex {
    private double real;
    private double imaginary;
    private static final double EPSILON = 1e-10;
    //так как при сравнении чисел с плавающей точкой могут возникать неточности,
    // считаем числа равными при неточности EPSILON

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    public Complex multiply(Complex other) {
        double realPart = this.real * other.real - this.imaginary * other.imaginary;
        double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;

        return new Complex(realPart, imaginaryPart);
    }

    public Complex multiply(double scalar) {
        return new Complex(this.real * scalar, this.imaginary * scalar);
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;

        if (denominator == 0) {
            throw new ArithmeticException("divide by zero");
        }

        double realPart = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double imaginaryPart = (this.imaginary * other.real - this.real * other.imaginary) / denominator;

        return new Complex(realPart, imaginaryPart);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Complex other = (Complex) obj;
        return Math.abs(real - other.real) < EPSILON && Math.abs(imaginary - other.imaginary) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("(%f + %fi)", real, imaginary);
    }
}