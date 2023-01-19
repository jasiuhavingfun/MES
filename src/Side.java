import static java.lang.Math.sqrt;

public class Side {
    public double[][] side1 = { // N1 N2
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    public double[][] side2 = { // N2 N3
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    public double[][] side3 = { // N3 N4
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    public double[][] side4 = { // N4 N1
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    public double[] vectorP1 = {0, 0, 0, 0};
    public double[] vectorP2 = {0, 0, 0, 0};
    public double[] vectorP3 = {0, 0, 0, 0};
    public double[] vectorP4 = {0, 0, 0, 0};

    private double N(int number, double ksi, double eta) {
        switch (number) {
            case 0 -> {
                return 0.25 * (1 - ksi) * (1 - eta);
            }
            case 1 -> {
                return 0.25 * (1 + ksi) * (1 - eta);
            }
            case 2 -> {
                return 0.25 * (1 + ksi) * (1 + eta);
            }
            case 3 -> {
                return 0.25 * (1 - ksi) * (1 + eta);
            }
            default -> {
                return 0;
            }
        }
    }

    public void matrix(int number, Node first, Node second, GlobalData globalData) {

        double[] NforPC1 = new double[4];
        double[] NforPC2 = new double[4];
        double[] NforPC3 = new double[4];
        double[] NforPC4 = new double[4];

        double[][] pom1 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        double[][] pom2 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        double[][] pom3 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        double[][] pom4 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        double detJ = sqrt((second.y - first.y) * (second.y - first.y) + (second.x - first.x) * (second.x - first.x)) / 2;
        //globalData.Alfa = 25; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // dla 2 pc
        if (number == 2) {

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, SC.PC2[0], -1);
                NforPC2[i] = N(i, SC.PC2[1], -1);
            }

            calcVerctorPC2(globalData, NforPC1, NforPC2, detJ, vectorP1);
            calcSidePC2(globalData, NforPC1, NforPC2, detJ, side1);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, 1, SC.PC2[0]);
                NforPC2[i] = N(i, 1, SC.PC2[1]);
            }

            calcVerctorPC2(globalData, NforPC1, NforPC2, detJ, vectorP2);
            calcSidePC2(globalData, NforPC1, NforPC2, detJ, side2);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, SC.PC2[0], 1);
                NforPC2[i] = N(i, SC.PC2[1], 1);
            }

            calcVerctorPC2(globalData, NforPC1, NforPC2, detJ, vectorP3);
            calcSidePC2(globalData, NforPC1, NforPC2, detJ, side3);


            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, -1, SC.PC2[0]);
                NforPC2[i] = N(i, -1, SC.PC2[1]);
            }

            calcVerctorPC2(globalData, NforPC1, NforPC2, detJ, vectorP4);
            calcSidePC2(globalData, NforPC1, NforPC2, detJ, side4);

//            printSide(side1);
//            printSide(side2);
//            printSide(side3);
//            printSide(side4);
        }
        if (number == 3) {

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, SC.PC3[0], -1);
                NforPC2[i] = N(i, SC.PC3[1], -1);
                NforPC3[i] = N(i, SC.PC3[2], -1);
            }

            calcSidePC3(globalData, NforPC1, NforPC2, NforPC3, pom1, pom2, pom3, detJ, side1);
            calcVectorPC3(globalData, NforPC1, NforPC2, NforPC3, detJ, vectorP1);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, 1, SC.PC3[0]);
                NforPC2[i] = N(i, 1, SC.PC3[1]);
                NforPC3[i] = N(i, 1, SC.PC3[2]);
            }

            calcVectorPC3(globalData, NforPC1, NforPC2, NforPC3, detJ, vectorP2);
            calcSidePC3(globalData, NforPC1, NforPC2, NforPC3, pom1, pom2, pom3, detJ, side2);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, SC.PC3[0], 1);
                NforPC2[i] = N(i, SC.PC3[1], 1);
                NforPC3[i] = N(i, SC.PC3[2], 1);
            }

            calcSidePC3(globalData, NforPC1, NforPC2, NforPC3, pom1, pom2, pom3, detJ, side3);
            calcVectorPC3(globalData, NforPC1, NforPC2, NforPC3, detJ, vectorP3);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, -1, SC.PC3[0]);
                NforPC2[i] = N(i, -1, SC.PC3[1]);
                NforPC3[i] = N(i, -1, SC.PC3[2]);
            }

            calcSidePC3(globalData, NforPC1, NforPC2, NforPC3, pom1, pom2, pom3, detJ, side4);
            calcVectorPC3(globalData, NforPC1, NforPC2, NforPC3, detJ, vectorP4);

//            printSide(side1);
//            printSide(side2);
//            printSide(side3);
//            printSide(side4);
        }

        if (number == 4) {
            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, SC.PC4[0], -1);
                NforPC2[i] = N(i, SC.PC4[1], -1);
                NforPC3[i] = N(i, SC.PC4[2], -1);
                NforPC4[i] = N(i, SC.PC4[3], -1);
            }
            calcSidePC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, pom1, pom2, pom3, pom4, detJ, side1);
            calcVectorPC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, detJ, vectorP1);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, 1, SC.PC4[0]);
                NforPC2[i] = N(i, 1, SC.PC4[1]);
                NforPC3[i] = N(i, 1, SC.PC4[2]);
                NforPC4[i] = N(i, 1, SC.PC4[3]);
            }
            calcVectorPC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, detJ, vectorP2);
            calcSidePC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, pom1, pom2, pom3, pom4, detJ, side2);


            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, SC.PC4[0], 1);
                NforPC2[i] = N(i, SC.PC4[1], 1);
                NforPC3[i] = N(i, SC.PC4[2], 1);
                NforPC4[i] = N(i, SC.PC4[3], 1);
            }
            calcSidePC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, pom1, pom2, pom3, pom4, detJ, side3);
            calcVectorPC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, detJ, vectorP3);

            for (int i = 0; i < 4; i++) {
                NforPC1[i] = N(i, -1, SC.PC4[0]);
                NforPC2[i] = N(i, -1, SC.PC4[1]);
                NforPC3[i] = N(i, -1, SC.PC4[2]);
                NforPC4[i] = N(i, -1, SC.PC4[3]);
            }
            calcSidePC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, pom1, pom2, pom3, pom4, detJ, side4);
            calcVectorPC4(globalData, NforPC1, NforPC2, NforPC3, NforPC4, detJ, vectorP4);

//            printSide(side1);
//            printSide(side2);
//            printSide(side3);
//            printSide(side4);
        }

    }

    private void calcVectorPC4(GlobalData globalData, double[] nforPC1, double[] nforPC2, double[] nforPC3, double[] nforPC4, double detJ, double[] vectorP2) {
        for (int i = 0; i < 4; i++) {
            vectorP2[i] = nforPC1[i] * globalData.Tot * SC.W4[0];
            vectorP2[i] += nforPC2[i] * globalData.Tot * SC.W4[1];
            vectorP2[i] += nforPC3[i] * globalData.Tot * SC.W4[2];
            vectorP2[i] += nforPC4[i] * globalData.Tot * SC.W4[3];
            vectorP2[i] *= detJ * globalData.Alfa;
        }
    }

    private void printVectors(double[] vectorP1, double[] vectorP2) {
        System.out.println("---------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println(vectorP1[i] + "\t");
        }
        System.out.println("---------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println(vectorP2[i] + "\t");
        }
    }

    private void calcVerctorPC2(GlobalData globalData, double[] nforPC1, double[] nforPC2, double detJ, double[] vectorP3) {
        for (int i = 0; i < 4; i++) {
            vectorP3[i] = nforPC1[i] * globalData.Tot;
            vectorP3[i] += nforPC2[i] * globalData.Tot;
            vectorP3[i] *= detJ * globalData.Alfa;
        }
    }

    private void calcVectorPC3(GlobalData globalData, double[] nforPC1, double[] nforPC2, double[] nforPC3, double detJ, double[] vectorP2) {
        for (int i = 0; i < 4; i++) {
            vectorP2[i] = nforPC1[i] * globalData.Tot * SC.W3[0];
            vectorP2[i] += nforPC2[i] * globalData.Tot * SC.W3[1];
            vectorP2[i] += nforPC3[i] * globalData.Tot * SC.W3[2];
            vectorP2[i] *= detJ * globalData.Alfa;
        }
    }

    private void calcSidePC3(GlobalData globalData, double[] nforPC1, double[] nforPC2, double[] nforPC3, double[][] pom1, double[][] pom2, double[][] pom3, double detJ, double[][] side) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pom1[i][j] = nforPC1[i] * nforPC1[j] * SC.W3[0] * globalData.Alfa;
                pom2[i][j] += nforPC2[i] * nforPC2[j] * SC.W3[1] * globalData.Alfa;
                pom3[i][j] += nforPC3[i] * nforPC3[j] * SC.W3[2] * globalData.Alfa;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                side[i][j] = pom1[i][j] + pom2[i][j] + pom3[i][j];
                side[i][j] *= detJ;
                pom1[i][j] = 0;
                pom2[i][j] = 0;
                pom3[i][j] = 0;
            }
        }
    }

    private void calcSidePC4(GlobalData globalData, double[] nforPC1, double[] nforPC2, double[] nforPC3, double[] nforPC4, double[][] pom1, double[][] pom2, double[][] pom3, double[][] pom4, double detJ, double[][] side) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pom1[i][j] = nforPC1[i] * nforPC1[j] * SC.W4[0] * globalData.Alfa;
                pom2[i][j] = nforPC2[i] * nforPC2[j] * SC.W4[1] * globalData.Alfa;
                pom3[i][j] = nforPC3[i] * nforPC3[j] * SC.W4[2] * globalData.Alfa;
                pom4[i][j] = nforPC4[i] * nforPC4[j] * SC.W4[3] * globalData.Alfa;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                side[i][j] = pom1[i][j] + pom2[i][j] + pom3[i][j] + pom4[i][j];
                side[i][j] *= detJ;
                pom1[i][j] = 0;
                pom2[i][j] = 0;
                pom3[i][j] = 0;
                pom4[i][j] = 0;
            }
        }
    }

    private void calcSidePC2(GlobalData globalData, double[] nforPC1, double[] nforPC2, double detJ, double[][] side) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                side[i][j] = nforPC1[i] * nforPC1[j] * globalData.Alfa;
                side[i][j] += nforPC2[i] * nforPC2[j] * globalData.Alfa;
                side[i][j] *= detJ;
            }
        }
    }

    private void printSide(double[][] side2) {
        System.out.println("---------------------------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(side2[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
