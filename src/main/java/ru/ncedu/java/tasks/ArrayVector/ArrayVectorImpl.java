package ru.ncedu.java.tasks.ArrayVector;


import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {

    private double[] array = {};

    public ArrayVectorImpl() {

    }

    public ArrayVectorImpl(int size) {
        if (size >= 0) {
            array = new double[size];
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void set(double... elements) {
        if (elements.length != 0) {
            array = new double[elements.length];
            System.arraycopy(elements, 0, array, 0, elements.length);
        }
    }

    @Override
    public double[] get() {
        return array;
    }

    @Override
    public ArrayVector clone() {
        ArrayVector cloneVector = new ArrayVectorImpl();

        cloneVector.set(array.clone());

        return cloneVector;
    }

    @Override
    public int getSize() {
        return array.length;
    }

    @Override
    public void set(int index, double value) {
        if (index >= 0) {
            if (index < array.length) {
                array[index] = value;
            } else {
                double[] tem = new double[array.length + 1];
                System.arraycopy(array, 0, tem, 0, array.length);
                array = tem;

                array[array.length - 1] = value;
            }
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {

        return array[index];
    }

    @Override
    public double getMax() {
        double[] tem = array.clone();

        Arrays.sort(tem);

        return tem[array.length - 1];
    }

    @Override
    public double getMin() {
        double[] tem = array.clone();

        Arrays.sort(tem);

        return tem[0];
    }

    @Override
    public void sortAscending() {
        Arrays.sort(array);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {

        if (anotherVector != null) {
            for (int i = 0; i < array.length; i++) {
                if (anotherVector.get().length <= i) {
                    break;
                }
                this.array[i] += anotherVector.get(i);
            }
        }

        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double scalar = 0;
        if (anotherVector != null) {
            int length;
            if (this.array.length <= anotherVector.getSize()) {
                length = this.array.length;
            } else {
                length = anotherVector.getSize();
            }

            for (int i = 0; i < length; i++) {
                scalar += (this.array[i] * anotherVector.get(i));
            }
        }
        return scalar;
    }

    @Override
    public double getNorm() {
        return Math.sqrt(this.scalarMult(this));
    }
}




















