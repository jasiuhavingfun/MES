import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Element4 {
    static double[] PC4eta = {-1 / sqrt(3), 1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3)};
    static double[] PC4ksi = {-1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3), 1 / sqrt(3)};
    static double[] PC9eta = {-sqrt(0.6), 0, sqrt(0.6), -sqrt(0.6), 0, sqrt(0.6), -sqrt(0.6), 0, sqrt(0.6)};
    static double[] PC9ksi = {-sqrt(0.6), -sqrt(0.6), -sqrt(0.6), 0, 0, 0, sqrt(0.6), sqrt(0.6), sqrt(0.6)};
    static double[] PC16ksi = {-sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0))};
    static double[] PC16eta = {-sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0))};
    List<List<Double>> ksi = new ArrayList<>();
    List<List<Double>> eta = new ArrayList<>();


    public double dNksi(int number, double x) {
        return switch (number) {
            case 0 -> -0.25 * (1 - x);
            case 1 -> 0.25 * (1 - x);
            case 2 -> 0.25 * (1 + x);
            case 3 -> -0.25 * (1 + x);
            default -> 0;
        };
    }

    public double dNeta(int number, double x) {
        return switch (number) {
            case 0 -> -0.25 * (1 - x);
            case 1 -> -0.25 * (1 + x);
            case 2 -> 0.25 * (1 + x);
            case 3 -> 0.25 * (1 - x);
            default -> 0;
        };
    }

    public void calculate(int number) {
        switch (number) {
            case 2 -> f(4);
            case 3 -> f(9);
            case 4 -> f(16);
        }
    }

    private void f(int numberOfArrays) {
        if (numberOfArrays == 4) {
            calc(numberOfArrays, PC4ksi, PC4eta);
        }

        if (numberOfArrays == 9) {
            calc(numberOfArrays, PC9ksi, PC9eta);
        }

        if (numberOfArrays == 16) {
            calc(numberOfArrays, PC16ksi, PC16eta);
        }
    }

    private void calc(int numberOfArrays, double[] pc16ksi, double[] pc16eta) {
        for (int i = 0; i < numberOfArrays; i++) {
            List<Double> arrayForKsi = new ArrayList<>();
            List<Double> arrayForEta = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                arrayForKsi.add(j, dNksi(j, pc16ksi[i]));
                arrayForEta.add(j, dNeta(j, pc16eta[i]));
            }

            ksi.add(i, arrayForKsi);
            eta.add(i, arrayForEta);
        }
    }
}
