import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class GaussianElimination
 **/
public class GaussianElimination {
    List<Double> t1 = new ArrayList<>();

    public void solve(double[][] A, Double[] B) {

        int N = B.length;

        t1.clear();
        for (int k = 0; k < N; k++) {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            /** swap row in A matrix **/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++) {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }

        /** Print row echelon form **/
        //printRowEchelonForm(A, B);

        /** back substitution **/
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        for (int i = 0; i < N; i++) {
            t1.add(i, solution[i]);
        }
        /** Print solution **/
        //printSolution(solution);
    }

    /**
     * function to print in row    echleon form
     **/
    public void printRowEchelonForm(double[][] A, double[] B) {
        int N = B.length;
        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%.3f ", A[i][j]);
            System.out.printf("| %.3f\n", B[i]);
        }
        System.out.println();
    }

    /**
     * function to print solution
     **/
    public void printSolution(double[] sol) {
        int N = sol.length;
        System.out.println("\nSolution : ");
        for (int i = 0; i < N; i++)
            System.out.printf("%.3f ", sol[i]);
        System.out.println();
    }
}