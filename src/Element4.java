import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Element4 {
    static double[] PC4eta = {-1 / sqrt(3), 1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3)};
    static double[] PC4ksi = {-1 / sqrt(3), -1 / sqrt(3), 1 / sqrt(3), 1 / sqrt(3)};
    static double[] PC9eta = {-sqrt(0.6), -sqrt(0.6), -sqrt(0.6), 0, 0, 0, sqrt(0.6), sqrt(0.6), sqrt(0.6)};
    static double[] PC9ksi = {-sqrt(0.6), 0, sqrt(0.6), -sqrt(0.6), 0, sqrt(0.6), -sqrt(0.6), 0, sqrt(0.6)};
    static double[] PC16eta = {-sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0))};
    static double[] PC16ksi = {-sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0))};
    List<List<Double>> ksi = new ArrayList<>();
    List<List<Double>> eta = new ArrayList<>();


    private double dNksi(int number, double x) {
        return switch (number) {
            case 0 -> -0.25 * (1 - x);
            case 1 -> 0.25 * (1 - x);
            case 2 -> 0.25 * (1 + x);
            case 3 -> -0.25 * (1 + x);
            default -> 0;
        };
    }

    private double dNeta(int number, double x) {
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
            for (int i = 0; i < numberOfArrays; i++) {
                List<Double> arrayForKsi = new ArrayList<>();
                List<Double> arrayForEta = new ArrayList<>();

                for (int j = 0; j < 4; j++) {
                    arrayForKsi.add(j, dNksi(j, PC4ksi[i]));
                    arrayForEta.add(j, dNeta(j, PC4eta[i]));
                }

                ksi.add(i, arrayForKsi);
                eta.add(i, arrayForEta);
            }
        }

        if (numberOfArrays == 9) {
            for (int i = 0; i < numberOfArrays; i++) {
                List<Double> arrayForKsi = new ArrayList<>();
                List<Double> arrayForEta = new ArrayList<>();

                for (int j = 0; j < 4; j++) {
                    arrayForKsi.add(j, dNksi(j, PC9ksi[i]));
                    arrayForEta.add(j, dNeta(j, PC9eta[i]));
                }

                ksi.add(i, arrayForKsi);
                eta.add(i, arrayForEta);
            }
        }

        if (numberOfArrays == 16) {
            for (int i = 0; i < numberOfArrays; i++) {
                List<Double> arrayForKsi = new ArrayList<>();
                List<Double> arrayForEta = new ArrayList<>();

                for (int j = 0; j < 4; j++) {
                    arrayForKsi.add(j, dNksi(j, PC16ksi[i]));
                    arrayForEta.add(j, dNeta(j, PC16eta[i]));
                }

                ksi.add(i, arrayForKsi);
                eta.add(i, arrayForEta);
            }
        }

//        for (int i = 0; i < numberOfArrays; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.println(ksi.get(j)+"\t");
//            }
//            System.out.println();
//        }
//
//        for (int i = 0; i < numberOfArrays; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.println(eta.get(j)+"\t");
//            }
//            System.out.println();
//        }

//        System.out.println(ksi);
//        System.out.println(eta);
    }
}
