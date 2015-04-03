package com.netcracker.edu.java.tasks.ComplexNumber;

import java.util.Arrays;

public class ComplexNumberImpl implements ComplexNumber{

    private double re;
    private double im;


    public ComplexNumberImpl() {
        this(0, 0);
    }


    public ComplexNumberImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumberImpl(String value) {
        this.set(value);
    }

    @Override
    public double getRe() {
        return this.re;
    }

    @Override
    public double getIm() {
        return this.im;
    }

    @Override
    public boolean isReal() {
        return im == 0;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {

        if (value.contains("*")) {
            throw new NumberFormatException();
        }
        String parseValue = value.replaceAll(" ","");
        if (!parseValue.contains("i") && !parseValue.contains("I")) {
            this.re = Double.parseDouble(parseValue);
            this.im = 0;
        } else {
            parseValue = parseValue.replaceAll("i","");
            parseValue = parseValue.replaceAll("I","");
            if(parseValue.contains("+") || (parseValue.contains("-") && parseValue.lastIndexOf('-') > 0)){
                String real = "";
                String imag = "";

                if(parseValue.indexOf('+') > 0) {
                    real = parseValue.substring(0, parseValue.indexOf('+'));
                    imag = parseValue.substring(parseValue.indexOf('+') + 1, parseValue.length());
                } else if(parseValue.lastIndexOf('-') > 0) {
                    real = parseValue.substring(0,parseValue.lastIndexOf('-'));
                    imag = parseValue.substring(parseValue.lastIndexOf('-'), parseValue.length());
                }

                if (imag.equals("") || imag.equals("-")){
                    imag = imag + "1";
                }

                this.im = Double.parseDouble(imag);
                this.re = Double.parseDouble(real);
            } else {
                this.re = 0;
                if (parseValue.equals("")){
                    this.im = 1;
                } else {
                    this.im = Double.parseDouble(parseValue);
                }
            }
        }
    }

    @Override
    public ComplexNumber copy() {

        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return (ComplexNumber)super.clone();
    }

    @Override
    public String toString() {
        if (im == 0) {
            return String.valueOf(re);
        } else if (re == 0) {
            return String.valueOf(im + "i");
        } else if (im < 0) {
            return String.valueOf(re) + String.valueOf(im) + "i";
        } else {
            return String.valueOf(re) + "+" + String.valueOf(im) + "i";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ComplexNumber) {
            return this.re == ((ComplexNumber) other).getRe() && this.im == ((ComplexNumber) other).getIm();
        }
        return false;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double tmp1 = Math.pow(this.re, 2) + Math.pow(this.im, 2);
        double tmp2 = Math.pow(other.getRe(), 2) + Math.pow(other.getIm(), 2);
        if(tmp1 < tmp2) {
            //  this < other
            return -1;
        }
        else if(tmp1 > tmp2) {
            //  this > other
            return 1;
        }
        // this == other
        return 0;
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    @Override
    public ComplexNumber negate() {
        if (this.re != 0 || this.im != 0) {
            this.re = - re;
            this.im = - im;
        }

        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {

        this.re += arg2.getRe();
        this.im += arg2.getIm();

        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double tmp;

        tmp  = this.re * arg2.getRe() - this.im * arg2.getIm();
        this.im  = this.im * arg2.getRe() + this.re * arg2.getIm();
        this.re = tmp;

        return this;
    }
}
