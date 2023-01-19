import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixH {
    public double[][] H = new double[4][4];
    private List<List<Double>> Ncalc = new ArrayList<>();
    private List<Double> detJarr = new ArrayList<>();
    public double[][] C = new double[4][4];
    public double[][] globalH = new double[961][961];
    public double[][] globalC = new double[961][961];
    public double[] globalVectorP = new double[961];
    public List<Double> globalVectorP_list = new ArrayList<>();
    List<Matrix> matrixList = new ArrayList<>();

    private double kN(int number, double ksi, double eta) {
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

    public void calculateH(Element4 element4, GlobalData globalData, Grid grid, int number) {

        double[] x = {0, 0.025, 0.025, 0};
        double[] y = {0, 0, 0.025, 0.025};
        boolean[] HBC = new boolean[4];
        Node[] nodes = new Node[4];
        Side side = new Side();
        List<List<Double>> dNdX = new ArrayList<>(4);
        List<List<Double>> dNdY = new ArrayList<>(4);

        element4.calculate(number);
        if (number == 2) {

            for (int j = 0; j < 4; j++) {
                List<Double> arrayForN = new ArrayList<>();
                for (int k = 0; k < number * number; k++) {
                    arrayForN.add(k, kN(j, Element4.PC4eta[k], Element4.PC4ksi[k]));
                }
                Ncalc.add(j, arrayForN);
            }

            for (int i = 0; i < grid.Elements_number; i++) {

                calculations(element4, globalData, grid, number, x, y, dNdX, dNdY, i);
                addBC(globalData, grid, number, HBC, nodes, side, i);
                agregacja(grid, i);

            }

        }
        if (number == 3) {
            for (int i = 0; i < grid.Elements_number; i++) {

                for (int j = 0; j < 4; j++) {
                    List<Double> arrayForN = new ArrayList<>();
                    for (int k = 0; k < number * number; k++) {
                        arrayForN.add(k, kN(j, Element4.PC9eta[k], Element4.PC9ksi[k]));
                    }
                    Ncalc.add(j, arrayForN);
                }

                calculations(element4, globalData, grid, number, x, y, dNdX, dNdY, i);
                addBC(globalData, grid, number, HBC, nodes, side, i);
                agregacja(grid, i);
            }
        }
        if (number == 4) {
            for (int i = 0; i < grid.Elements_number; i++) {

                for (int j = 0; j < 4; j++) {
                    List<Double> arrayForN = new ArrayList<>();
                    for (int k = 0; k < number * number; k++) {
                        arrayForN.add(k, kN(j, Element4.PC16eta[k], Element4.PC16ksi[k]));
                    }
                    Ncalc.add(j, arrayForN);
                }

                calculations(element4, globalData, grid, number, x, y, dNdX, dNdY, i);
                addBC(globalData, grid, number, HBC, nodes, side, i);
                agregacja(grid, i);

            }
        }
    }

    private void addBC(GlobalData globalData, Grid grid, int number, boolean[] HBC, Node[] nodes, Side side, int i) {
        for (int j = 0; j < 4; j++) {
            HBC[j] = grid.Nodes.get(grid.Elements.get(i).ID[j] - 1).BC;
            nodes[j] = grid.Nodes.get(grid.Elements.get(i).ID[j] - 1);
        }

        if (HBC[0] && HBC[1]) {
            side.matrix(number, nodes[0], nodes[1], globalData);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    this.H[j][k] += side.side1[j][k];
                }
                grid.Elements.get(i).vectorP[j] += side.vectorP1[j];
            }

        }
        if (HBC[1] && HBC[2]) {
            side.matrix(number, nodes[1], nodes[2], globalData);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    this.H[j][k] += side.side2[j][k];
                }
                grid.Elements.get(i).vectorP[j] += side.vectorP2[j];
            }
        }
        if (HBC[2] && HBC[3]) {
            side.matrix(number, nodes[2], nodes[3], globalData);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    this.H[j][k] += side.side3[j][k];
                }
                grid.Elements.get(i).vectorP[j] += side.vectorP3[j];
            }
        }
        if (HBC[3] && HBC[0]) {
            side.matrix(number, nodes[3], nodes[0], globalData);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    this.H[j][k] += side.side4[j][k];
                }
                grid.Elements.get(i).vectorP[j] += side.vectorP4[j];
            }
        }
    }

    private void agregacja(Grid grid, int i) {

        for (int j = 0; j < grid.Nodes_number; j++) {
            this.globalVectorP_list.add(.0);
        }

        for (int k = 0; k < 4; k++) {

            for (int l = 0; l < 4; l++) {
                this.globalH[grid.Elements.get(i).ID[k] - 1][grid.Elements.get(i).ID[l] - 1] += this.H[k][l];
                this.globalC[grid.Elements.get(i).ID[k] - 1][grid.Elements.get(i).ID[l] - 1] += this.C[k][l];
                this.C[k][l] = 0;
                this.H[k][l] = 0;
            }

            this.globalVectorP_list.set(grid.Elements.get(i).ID[k] - 1, this.globalVectorP_list.get(grid.Elements.get(i).ID[k] - 1) + grid.Elements.get(i).vectorP[k]);
            this.globalVectorP[grid.Elements.get(i).ID[k] - 1] += grid.Elements.get(i).vectorP[k];
        }

    }

    private void printHlocal() {
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                System.out.print(this.H[k][l] + "\t");
                this.H[k][l] = 0;
            }
            System.out.println();
        }

        System.out.println("--------------------------------");
    }

    private void printClocal() {
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                System.out.print(this.C[k][l] + "\t");
                //this.C[k][l] = 0;
            }
            System.out.println();
        }

        System.out.println("--------------------------------");
    }

    private void Jacobi(List<Double> J1, List<Double> J2, List<Double> J3, List<Double> J4, int number, double[] x, double[] y, Element4 element4, List<Double> detJ_list) {
        for (int i = 0; i < number * number; i++) {
            double[][] J = {{0, 0}, {0, 0}};
            double detJ;
            for (int k = 0; k < 4; k++) {  // obliczanie jakobianu
                J[0][0] += element4.ksi.get(i).get(k) * x[k];
                J[1][0] += element4.ksi.get(i).get(k) * y[k];
                J[0][1] += element4.eta.get(i).get(k) * x[k];
                J[1][1] += element4.eta.get(i).get(k) * y[k];
            }

            detJ = (J[0][0] * J[1][1]) - (J[1][0] * J[0][1]);

            detJ_list.add(i, detJ);

            double temp = J[0][0] * (1 / detJ); // obracanie jakobianu
            J1.add(i, J[1][1] * (1 / detJ));
            J2.add(i, temp);
            J3.add(i, -J[0][1] * (1 / detJ));
            J4.add(i, -J[1][0] * (1 / detJ));

        }
    }

    private void calculations(Element4 element4, GlobalData globalData, Grid grid, int number, double[] x, double[] y, List<List<Double>> dNdX, List<List<Double>> dNdY, int i) {
        for (int j = 0; j < 4; j++) {  // porzypisywanie x i y z node
            x[j] = grid.Nodes.get(grid.Elements.get(i).ID[j] - 1).x;
            y[j] = grid.Nodes.get(grid.Elements.get(i).ID[j] - 1).y;
        }

        Matrix test = new Matrix();

        List<Double> J1 = new ArrayList<>();
        List<Double> J2 = new ArrayList<>();
        List<Double> J3 = new ArrayList<>();
        List<Double> J4 = new ArrayList<>();
        List<Double> detJ_list = new ArrayList<>();
        Jacobi(J1, J2, J3, J4, number, x, y, element4, detJ_list);

        for (int j = 0; j < number * number; j++) {

            Matrix matrix = new Matrix();

            for (int k = 0; k < number * number; k++) {
                List<Double> arrayFordNdX = new ArrayList<>();
                List<Double> arrayFordNdY = new ArrayList<>();

                for (int l = 0; l < 4; l++) {
                    arrayFordNdX.add(l, J1.get(k) * element4.ksi.get(k).get(l) + J4.get(k) * element4.eta.get(k).get(l));
                    arrayFordNdY.add(l, J3.get(k) * element4.ksi.get(k).get(l) + J2.get(k) * element4.eta.get(k).get(l));
                }

                dNdX.add(k, arrayFordNdX);
                dNdY.add(k, arrayFordNdY);
            }

            for (int l = 0; l < 4; l++) {
                for (int m = 0; m < 4; m++) {
                    matrix.H[l][m] = (dNdX.get(j).get(m) * dNdX.get(j).get(l) + dNdY.get(j).get(m) * dNdY.get(j).get(l)) * detJ_list.get(j) * globalData.Conductivity;
                    matrix.C[l][m] = Ncalc.get(l).get(j) * Ncalc.get(m).get(j) * detJ_list.get(j) * globalData.Density * globalData.SpecificHeat;
                }
            }

            int pom = 0;
            if (number == 2) {
                for (int l = 0; l < 4; l++) {
                    for (int m = 0; m < 4; m++) {
                        test.H[l][m] += matrix.H[l][m];
                        test.C[l][m] += matrix.C[l][m];
                    }

                }
            }
            if (number == 3) {
                if (j > 2) pom = 1;
                if (j > 5) pom = 2;
                for (int l = 0; l < 4; l++) {
                    for (int m = 0; m < 4; m++) {
                        test.H[l][m] += matrix.H[l][m] * SC.W3[j % 3] * SC.W3[pom];
                        test.C[l][m] += matrix.C[l][m] * SC.W3[j % 3] * SC.W3[pom];
                    }

                }
            }
            if (number == 4) {
                if (j > 3) pom = 1;
                if (j > 7) pom = 2;
                if (j > 11) pom = 3;
                for (int l = 0; l < 4; l++) {
                    for (int m = 0; m < 4; m++) {
                        test.H[l][m] += matrix.H[l][m] * SC.W4[j % 4] * SC.W4[pom];
                        test.C[l][m] += matrix.C[l][m] * SC.W4[j % 4] * SC.W4[pom];
                    }

                }
            }

            matrixList.add(matrix);

        }

        for (int l = 0; l < 4; l++) {
            for (int m = 0; m < 4; m++) {
                this.H[l][m] = test.H[l][m];
                test.H[l][m] = 0;
                this.C[l][m] = test.C[l][m];
                test.C[l][m] = 0;
            }
        }
    }
}
