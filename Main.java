package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Output(new Triangle(Input()).getHeights());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static Sides Input() {
        var lengths = new Sides();
        var scanner = new Scanner(System.in);

        System.out.println("Длина стороны A:");
        lengths.sideA = scanner.nextFloat();

        System.out.println("Длина стороны B:");
        lengths.sideB = scanner.nextFloat();

        System.out.println("Длина стороны C:");
        lengths.sideC = scanner.nextFloat();

        return lengths;
    }

    public static void Output(@NotNull Sides heights) {
        System.out.printf("\nВысота треугольника к стороне A: %.2f", heights.sideA);
        System.out.printf("\nВысота треугольника к стороне B: %.2f", heights.sideB);
        System.out.printf("\nВысота треугольника к стороне C: %.2f", heights.sideC);
    }
}

class Sides implements Cloneable {
    public float sideA, sideB, sideC;

    public Sides() {
    }

    public Sides(float sideA, float sideB, float sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public Sides clone() throws CloneNotSupportedException {
        return (Sides) super.clone();
    }
}

class Triangle {
    private Sides lengths;

    public Triangle() {
        lengths = new Sides();
    }

    public Triangle(Sides lengths) throws IllegalArgumentException {
        this();
        setSideLengths(lengths);
    }

    public void setSideLengths(@NotNull Sides lengths) throws IllegalArgumentException {
        //CHECK
        if (!(lengths.sideA > 0 && lengths.sideB > 0 && lengths.sideC > 0)) {
            throw new IllegalArgumentException("Длина каждой из сторон треугольника должны быть > 0.");
        }
        if (lengths.sideA > (lengths.sideB + lengths.sideC) || lengths.sideB > (lengths.sideC + lengths.sideA) || lengths.sideC > (lengths.sideB + lengths.sideA)) {
            throw new IllegalArgumentException("Длина каждой из сторон треугольника не может быть больше суммы двух других сторон.");
        }
        //SET
        try {
            this.lengths = lengths.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("УПС");
        }
    }

    public float getArea() {
        float semiPerimeter = (lengths.sideA + lengths.sideB + lengths.sideC) / 2;
        return (float) Math.sqrt(semiPerimeter * (semiPerimeter - lengths.sideA) * (semiPerimeter - lengths.sideB) * (semiPerimeter - lengths.sideC));
    }

    public Sides getHeights() {
        float area = getArea();
        return new Sides(2 * area / lengths.sideA, 2 * area / lengths.sideB, 2 * area / lengths.sideC);
    }

}