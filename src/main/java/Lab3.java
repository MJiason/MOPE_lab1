import Jama.Matrix;
import com.sun.java_cup.internal.runtime.Scanner;

import java.util.Arrays;
import java.util.Random;

public class Lab3 {
    float x_min1, x_min2, x_max1, x_max2, x_min3, x_max3;
    int[][] matrix_x_cod = {{-1, -1, -1}, {-1, 1, 1}, {1, -1, 1}, {1, 1, -1}, {-1, -1, 1}, {-1, 1, -1}, {1, -1, -1}, {1, 1, 1}};
    int[][] matrix_x_cod_1 = {{1, -1, -1, -1}, {1, -1, 1, 1}, {1, 1, -1, 1}, {1, 1, 1, -1}, {1, -1, -1, 1}, {1, -1, 1, -1}, {1, 1, -1, -1}, {1, 1, 1, 1}};
    float y_min;
    float y_max;
    double Sb_2;
    int m, n;
    private static int countCoef = 3;
    MatrixFunc matrixFunc = new MatrixFunc();

    public Lab3(int m, int n, float x_min1, float x_min2, float x_min3, float x_max1, float x_max2, float x_max3, float y_min, float y_max) {
        this.x_min1 = x_min1;
        this.x_min2 = x_min2;
        this.x_min3 = x_min3;
        this.x_max1 = x_max1;
        this.x_max2 = x_max2;
        this.x_max3 = x_max3;
        this.y_min = y_min;
        this.y_max = y_max;
        this.m = m;
        this.n = n;
    }

    private float randFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    Random random = new Random();

    public double[][] genMatrix() {
        double[][] matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = randFloat(y_min, y_max);
            }
        }
        return matrix;
    }

    public double[][] genMatrix_x(int n, int[][] codedMatrix) {
        double[][] matrix_x = new double[n][countCoef];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < countCoef; j++) {
                switch (j) {
                    case 0:
                        if (codedMatrix[i][j] == -1) {
                            matrix_x[i][j] = x_min1;
                        } else matrix_x[i][j] = x_max1;
                        break;
                    case 1:
                        if (codedMatrix[i][j] == -1) {
                            matrix_x[i][j] = x_min2;
                        } else matrix_x[i][j] = x_max2;
                        break;
                    case 2:
                        if (codedMatrix[i][j] == -1) {
                            matrix_x[i][j] = x_min3;
                        } else matrix_x[i][j] = x_max3;
                        break;
                    default:
                        break;
                }
            }

        }
        return matrix_x;
    }

    public double[] countAver_y(double[][] matrix_y) {
        double[] averY = new double[matrix_y.length];
        for (int i = 0; i < matrix_y.length; i++) {
            double currentSum = 0;
            for (int j = 0; j < matrix_y[0].length; j++) {
                currentSum += matrix_y[i][j];
            }
            averY[i] = currentSum / matrix_y[0].length;
        }
        return averY;
    }

    public double[] countMathExp_x(double[][] matrix_x) {
        double[] mathExp_x = new double[matrix_x.length];
        for (int i = 0; i < matrix_x.length; i++) {
            double currentSum = 0;
            for (int j = 0; j < matrix_x[0].length; j++) {
                currentSum += matrix_x[i][j];
            }
            mathExp_x[i] = currentSum / matrix_x[0].length;
        }
        return mathExp_x;
    }

    public double countMathExp_y(double[] averY) {
        double currentSum = 0;
        for (int i = 0; i < averY.length; i++) {
            currentSum += averY[i];
        }
        return currentSum / averY.length;
    }

    public double[] countA_1(double[][] matrix_x, double[] aver_y) {
        double[] A_1 = new double[matrix_x[0].length];
        for (int i = 0; i < matrix_x[0].length; i++) {
            double currentSum = 0;
            for (int j = 0; j < matrix_x.length; j++) {
                currentSum += matrix_x[j][i] * aver_y[j];
            }
            A_1[i] = currentSum / aver_y.length;
        }
        return A_1;
    }

    public double[] countA_2(double[][] matrix_x) {
        double[] A_2 = new double[matrix_x[0].length];
        for (int i = 0; i < matrix_x[0].length; i++) {
            double currentSum = 0;
            for (int j = 0; j < matrix_x.length; j++) {
                currentSum += Math.pow(matrix_x[j][i], 2);
            }
            A_2[i] = currentSum / matrix_x[0].length;
        }
        return A_2;
    }

    public double[] countA_3(double[][] matrix_x) {
        double[] A_3 = new double[matrix_x[0].length];
        double currentSum = 0;
        for (int i = 0; i < matrix_x[0].length - 1; i++) {
            currentSum = 0;
            for (int j = 0; j < matrix_x.length; j++) {
                currentSum += matrix_x[j][i] * matrix_x[j][i + 1];
            }
            A_3[i] = currentSum / 4;
        }
        currentSum = 0;
        for (int i = 0; i < matrix_x.length; i++) {
            currentSum += matrix_x[i][1] * matrix_x[i][2];
        }
        A_3[2] = currentSum;
        return A_3;
    }

    public double[] calcCoef(double[] mathExp_x, double[] A_1, double[] A_2, double[] A_3, double mathExp_y) {
        double[] coef = new double[4];
        double[][] lhsArr = {{1, mathExp_x[0], mathExp_x[1], mathExp_x[2]}, {mathExp_x[0], A_2[0], A_3[0], A_3[2]}, {mathExp_x[1], A_3[0], A_2[1], A_3[1]}, {mathExp_x[2], A_3[2], A_3[1], A_2[2]}};
        double[] rhsArr = {mathExp_y, A_1[0], A_1[1], A_3[2]};
        Matrix lhs = new Matrix(lhsArr);
        Matrix rhs = new Matrix(rhsArr, 4);
        //Calculate Solved Matrix
        Matrix ans = lhs.solve(rhs);
        //Printing Answers
        for (int i = 0; i < coef.length; i++) {
            coef[i] = (float) ans.get(i, 0);
        }
        return coef;
    }

    public double[] naturCoef(double[] coef) {
        float delX1 = Math.abs(this.x_max1 - this.x_min1) / 2;
        float delX2 = Math.abs(this.x_max2 - this.x_min2) / 2;
        float delX3 = Math.abs(this.x_max3 - this.x_min3) / 2;
        float X10 = (this.x_max1 + this.x_min1) / 2;
        float X20 = (this.x_max2 + this.x_min2) / 2;
        float X30 = (this.x_max2 + this.x_min2) / 2;
        double a0 = coef[0] - coef[1] * (X10 / delX1) - coef[2] * (X20 / delX2) - coef[3] * (X30 / delX3);
        double a1 = coef[1] / delX1;
        double a2 = coef[2] / delX2;
        double a3 = coef[3] / delX3;
        return new double[]{a0, a1, a2, a3};
    }

    public double[] calcDispersion(double[][] matrix, double[] averMatrix) {
        double currValue;
        double[] Dispersion = new double[averMatrix.length];
        for (int i = 0; i < matrix.length; i++) {
            currValue = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                currValue += Math.pow((matrix[i][j] - averMatrix[i]), 2);
            }
            Dispersion[i] = currValue / matrix[0].length;
        }
        return Dispersion;
    }

    public double criterionCochrane(double[] dispersion) {
        return matrixFunc.max(dispersion) / matrixFunc.sum(dispersion);
    }

    public double[] studentCriterion(int n, double[] dispersion, double[] averY, int[][] coded_x) {
        double[] beta = new double[4];
        double Sb_1 = matrixFunc.sum(dispersion) / n;
        Sb_2 = Sb_1 / (n * m);
        double Sb_3 = Math.sqrt(Sb_2);
        double current;
        for (int i = 0; i < beta.length; i++) {
            current = 0;
            for (int j = 0; j < n; j++) {
                current += coded_x[j][i] * averY[j];
            }
            beta[i] = current / beta.length;
        }
        double[] T = new double[beta.length];
        for (int i = 0; i < beta.length; i++) {
            T[i] = Math.abs(beta[i])/Sb_3;
        }
        return T;
    }
    public double fisherCriterion(double[] T, double[][] array_x, double[] coef, double[] averY){
        double[] y_arr_1 = new double[4];
        double [][] currentArr = array_x;
        double currentSum;
        int d = 0;
        for (int i = 0; i < T.length; i++) {
            if (T[i] > 2.306){
                d++;
            }
        }
        System.out.println("d:" + d);
        for (int i = 1; i < currentArr.length; i++) {
            for (int j = 0; j < currentArr[0].length; j++) {
                if (T[i] > 2.306){
                    currentArr[i][j] *= coef[i];
                }
            }
        }
        for (int i = 0; i < currentArr.length; i++) {
            currentSum = 0;
            for (int j = 0; j < currentArr[0].length; j++) {
                currentSum += currentArr[i][j];
            }
            if (T[0] > 2.306){
                currentSum += coef[0];
            }
            y_arr_1[i] = currentSum;

        }
       double Sad =0;
        for (int i = 0; i < averY.length; i++) {
            Sad += Math.pow(y_arr_1[i]-averY[i], 2);
        }
        Sad = Sad*m/(n-d);
//        System.out.println(Sb_2);
        return  Sad/Sb_2;
    }
}
