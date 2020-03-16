public class MatrixFunc {

    public void printMatrix(String matrixName, double[] matrix) {
        System.out.println(matrixName + ":");
        System.out.print("[");
        for (int i = 0; i < matrix.length; i++) {
            System.out.format("%2.2f ", matrix[i]);
        }
        System.out.println("]");
    }


    public void printMatrix(String matrixName, float[][] matrix) {
        System.out.println(matrixName + ":");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.format("%2.2f ", matrix[i][j]);
            }
            System.out.println("]");
        }

    }

    public void printMatrix(String matrixName, double[][] matrix) {
        System.out.println(matrixName + ":");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.format("%2.2f ", matrix[i][j]);
            }
            System.out.println("]");
        }

    }

    public void printMatrix(String matrixName, int[][] matrix) {
        System.out.println(matrixName + ":");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }

    }

    public int index(float[] a, float target) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == target)
                return i;

        return -1;
    }

    public double max(double[] array) {
        double currentElem = array[0];
        for (int i = 1; i < array.length; i++) {
            if (currentElem < array[i]) {
                currentElem = array[i];
            }
        }
        return currentElem;
    }

    double sum(double[] array){
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }


}
