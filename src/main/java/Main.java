public class Main {
    public static void main(String[] args) {
        MatrixFunc matrixFunc = new MatrixFunc();
        Lab3 lab3 = new Lab3(3, 4, -15, 25, -15, 30, 65, -5, 202, 230);
//        matrixFunc.printMatrix("x", lab3.matrix_x_cod);
        double[][] matrix_x = lab3.genMatrix_x(4, lab3.matrix_x_cod);
        double[][] matrix_y = lab3.genMatrix();
        double[] averY = lab3.countAver_y(matrix_y);
        double[] mathExp_x = lab3.countMathExp_x(matrix_x);
        double mathExp_y = lab3.countMathExp_y(averY);
        double[] A_1 = lab3.countA_1(matrix_x, averY);
        double[] A_2 = lab3.countA_2(matrix_x);
        double[] A_3 = lab3.countA_3(matrix_x);
        double[] coef = lab3.calcCoef(mathExp_x, A_1, A_2, A_3, mathExp_y);
        double[] naturCoef = lab3.naturCoef(coef);
        double[] dispersion = lab3.calcDispersion(matrix_y, averY);
        double criterionCochrane = lab3.criterionCochrane(dispersion);
        double[] criterionStudent = lab3.studentCriterion(4, dispersion, averY, lab3.matrix_x_cod_1);
        double fisherCriterion = lab3.fisherCriterion(criterionStudent, matrix_x, coef, averY);
        matrixFunc.printMatrix("X", matrix_x);
        matrixFunc.printMatrix("Y", matrix_y);
        matrixFunc.printMatrix("AverageY", averY);
        matrixFunc.printMatrix("MathExpectationX", mathExp_x);
        System.out.format("MathExpectationY %2.2f\n", mathExp_y);
        matrixFunc.printMatrix("A_1", A_1);
        matrixFunc.printMatrix("A_2", A_2);
        matrixFunc.printMatrix("A_3", A_3);
        matrixFunc.printMatrix("Coeficients", coef);
        matrixFunc.printMatrix("NaturCoef", naturCoef);
        matrixFunc.printMatrix("Dispersion", dispersion);
        System.out.format("criterionCochrane:\nf1: " + 3 + "\nf2: " + 4 + "\nGp: %2.2f", criterionCochrane);
        System.out.format("StudentCriterion:\nf3=f1*f2 =(m-1)*N: " + 8);
        matrixFunc.printMatrix("\nt", criterionStudent);
        System.out.println("FisherCriterion:\nf4 = N – d=2\nf3 = f1*f2=(m-1)*N=2*4=8: " + fisherCriterion/100);

    }

}
