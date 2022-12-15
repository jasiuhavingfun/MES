import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixH {
    public double[][] H = new double[4][4];
    List<Matrix> matrixList = new ArrayList<>();

    public void calculateH(Element4 element4, GlobalData globalData, Grid grid, int number) {

        double[] x = {0, 0.025, 0.025, 0};
        double[] y = {0, 0, 0.025, 0.025};
        List<List<Double>> dNdX = new ArrayList<>(4);
        List<List<Double>> dNdY = new ArrayList<>(4);

        System.out.println("grid.Elements_number");


        element4.calculate(number);
        if (number == 2) {
            for (int i = 0; i < grid.Elements_number; i++) {

                System.out.println(i);

                for (int j = 0; j < 4; j++) {
                    x[j] = grid.Nodes.get(grid.Elements.get(i).ID[j]).x;
                    y[j] = grid.Nodes.get(grid.Elements.get(i).ID[j]).y;
                }

                for (int j = 0; j < number * number; j++) {

                    Matrix matrix = new Matrix();
                    double[][] J = {{0, 0}, {0, 0}};
                    double detJ;

                    for (int k = 0; k < 4; k++) {
                        J[0][0] += element4.ksi.get(j).get(k) * x[k];
                        J[1][0] += element4.ksi.get(j).get(k) * y[k];
                        J[0][1] += element4.eta.get(j).get(k) * x[k];
                        J[1][1] += element4.eta.get(j).get(k) * y[k];
                    }

                    detJ = (J[0][0] * J[1][1]) - (J[1][0] * J[0][1]);

                    double temp = J[0][0] * (1 / detJ);
                    J[0][0] = J[1][1] * (1 / detJ);
                    J[1][1] = temp;
                    J[0][1] = -J[0][1] * (1 / detJ);
                    J[1][0] = -J[1][0] * (1 / detJ);

                    for (int k = 0; k < 4; k++) {
                        List<Double> arrayFordNdX = new ArrayList<>();
                        List<Double> arrayFordNdY = new ArrayList<>();

                        for (int l = 0; l < 4; l++) {
                            arrayFordNdX.add(l, J[0][0] * element4.ksi.get(k).get(l) + J[0][1] * element4.eta.get(k).get(l));
                            arrayFordNdY.add(l, J[1][0] * element4.ksi.get(k).get(l) + J[1][1] * element4.eta.get(k).get(l));
                        }

                        dNdX.add(k, arrayFordNdX);
                        dNdY.add(k, arrayFordNdY);
                    }


                    for (int l = 0; l < 4; l++) {
                        for (int m = 0; m < 4; m++) {
                            matrix.H[l][m] = (dNdX.get(j).get(m) * dNdX.get(j).get(l) + dNdY.get(j).get(m) * dNdY.get(j).get(l)) * detJ * globalData.Conductivity;
                        }

                    }

                    matrixList.add(matrix);

                }

                for (int a = 0; a < 4; a++) {
                    for (int b = 0; b < 4; b++) {
                        for (int k = 0; k < 4; k++) {
                            this.H[b][k] += matrixList.get(a).H[b][k] * SC.W2[k % 2];
                        }
                    }
                }

                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        System.out.print(this.H[k][l] + "\t");
                        this.H[k][l] = 0;
                    }
                    System.out.println();
                }

                System.out.println("--------------------------------");

            }
        }
        if (number == 3) {
            for (int i = 0; i < grid.Elements_number; i++) {

                System.out.println(i);

                for (int j = 0; j < 4; j++) {
                    x[j] = grid.Nodes.get(grid.Elements.get(i).ID[j]).x;
                    y[j] = grid.Nodes.get(grid.Elements.get(i).ID[j]).y;
                }

                for (int j = 0; j < number * number; j++) {

                    Matrix matrix = new Matrix();
                    double[][] J = {{0, 0}, {0, 0}};
                    double detJ;

                    for (int k = 0; k < 4; k++) {
                        J[0][0] += element4.ksi.get(j).get(k) * x[k];
                        J[1][0] += element4.ksi.get(j).get(k) * y[k];
                        J[0][1] += element4.eta.get(j).get(k) * x[k];
                        J[1][1] += element4.eta.get(j).get(k) * y[k];
                    }

                    detJ = (J[0][0] * J[1][1]) - (J[1][0] * J[0][1]);

                    double temp = J[0][0] * (1 / detJ);
                    J[0][0] = J[1][1] * (1 / detJ);
                    J[1][1] = temp;
                    J[0][1] = -J[0][1] * (1 / detJ);
                    J[1][0] = -J[1][0] * (1 / detJ);

                    for (int k = 0; k < 4; k++) {
                        List<Double> arrayFordNdX = new ArrayList<>();
                        List<Double> arrayFordNdY = new ArrayList<>();

                        for (int l = 0; l < 4; l++) {
                            arrayFordNdX.add(l, J[0][0] * element4.ksi.get(k).get(l) + J[0][1] * element4.eta.get(k).get(l));
                            arrayFordNdY.add(l, J[1][0] * element4.ksi.get(k).get(l) + J[1][1] * element4.eta.get(k).get(l));
                        }

                        dNdX.add(k, arrayFordNdX);
                        dNdY.add(k, arrayFordNdY);
                    }

                    //globalData.Conductivity = 30;

                    for (int l = 0; l < 4; l++) {
                        for (int m = 0; m < 4; m++) {
                            matrix.H[l][m] = (dNdX.get(j).get(m) * dNdX.get(j).get(l) + dNdY.get(j).get(m) * dNdY.get(j).get(l)) * detJ * globalData.Conductivity;
                        }

                    }
                    matrixList.add(matrix);
                }

                int pom = 0;
                for (int a = 0; a < 9; a++) {
                    if (a > 2) pom = 1;
                    if (a > 5) pom = 2;
                    for (int b = 0; b < 4; b++) {
                        for (int k = 0; k < 4; k++) {
                            this.H[b][k] += matrixList.get(a).H[b][k] * SC.W3[pom] * SC.W3[a % 3];
                            //System.out.println(this.H[b][k]+"\n");
                        }
                    }
                }

                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        System.out.print(this.H[k][l] + "\t");
                        this.H[k][l] = 0;
                    }
                    System.out.println();
                }

                System.out.println("--------------------------------");

            }
        }
        if (number == 4) {
            for (int i = 0; i < grid.Elements_number; i++) {

                System.out.println(i);

                for (int j = 0; j < 4; j++) {
                    x[j] = grid.Nodes.get(grid.Elements.get(i).ID[j]).x;
                    y[j] = grid.Nodes.get(grid.Elements.get(i).ID[j]).y;
                }

                for (int j = 0; j < number * number; j++) {

                    Matrix matrix = new Matrix();
                    double[][] J = {{0, 0}, {0, 0}};
                    double detJ;

                    for (int k = 0; k < 4; k++) {
                        J[0][0] += element4.ksi.get(j).get(k) * x[k];
                        J[1][0] += element4.ksi.get(j).get(k) * y[k];
                        J[0][1] += element4.eta.get(j).get(k) * x[k];
                        J[1][1] += element4.eta.get(j).get(k) * y[k];
                    }

                    detJ = (J[0][0] * J[1][1]) - (J[1][0] * J[0][1]);

                    double temp = J[0][0] * (1 / detJ);
                    J[0][0] = J[1][1] * (1 / detJ);
                    J[1][1] = temp;
                    J[0][1] = -J[0][1] * (1 / detJ);
                    J[1][0] = -J[1][0] * (1 / detJ);

                    for (int k = 0; k < 4; k++) {
                        List<Double> arrayFordNdX = new ArrayList<>();
                        List<Double> arrayFordNdY = new ArrayList<>();

                        for (int l = 0; l < 4; l++) {
                            arrayFordNdX.add(l, J[0][0] * element4.ksi.get(k).get(l) + J[0][1] * element4.eta.get(k).get(l));
                            arrayFordNdY.add(l, J[1][0] * element4.ksi.get(k).get(l) + J[1][1] * element4.eta.get(k).get(l));
                        }

                        dNdX.add(k, arrayFordNdX);
                        dNdY.add(k, arrayFordNdY);
                    }

                    //globalData.Conductivity = 30;

                    for (int l = 0; l < 4; l++) {
                        for (int m = 0; m < 4; m++) {
                            matrix.H[l][m] = (dNdX.get(j).get(m) * dNdX.get(j).get(l) + dNdY.get(j).get(m) * dNdY.get(j).get(l)) * detJ * globalData.Conductivity;
                        }

                    }
                    matrixList.add(matrix);
                }

                for (int a = 0; a < 4; a++) {
                    for (int b = 0; b < 4; b++) {
                        for (int k = 0; k < 4; k++) {
                            this.H[b][k] += matrixList.get(a).H[b][k] * SC.W4[k % 4];
                        }
                    }
                }

                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        System.out.print(this.H[k][l] + "\t");
                        this.H[k][l] = 0;
                    }
                    System.out.println();
                }

                System.out.println("--------------------------------");

            }
        }
    }
}
