import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        String test1 = "C:\\Users\\janzi\\IdeaProjects\\MES\\src\\Test1_4_4.txt";
        String test2 = "C:\\Users\\janzi\\IdeaProjects\\MES\\src\\Test2_4_4_MixGrid.txt";
        String test3 = "C:\\Users\\janzi\\IdeaProjects\\MES\\src\\Test3_31_31_kwadrat.txt";

        GlobalData globalData = new GlobalData();
        globalData.read(test1);

        Grid grid = new Grid();
        grid.read(test1);
        //grid.printNodes();

        Element4 element4 = new Element4();
//        element4.calculate(3);
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(element4.eta.get(i).get(j)+"\t");
//            }
//            System.out.println();
//        }

        MatrixH matrixH = new MatrixH();
        matrixH.calculateH(element4, globalData, grid, 4);

//        System.out.println("-------------------------------------------");
//        for (int i = 0; i < 16; i++) {
//            System.out.println(i +"\t"+matrixH.globalVectorP[i]);
//        }


//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                System.out.print(matrixH.globalH[i][j]+"\t");
//            }
//            System.out.println();
//        }

        DecimalFormat dec = new DecimalFormat("#0.000");

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(matrixH.globalC[i][j]) +"\t");
//            }
//            System.out.println();
//        }


        double[][] HCt = new double[16][16]; // dynamiczne powiino byc
        double[][] Ct = new double[16][16]; // dynamiczne powiino byc
        double[] Ctt = new double[16]; // dynamiczne powiino byc
        double[] t0 = new double[16]; // dynamiczne powiino byc

        for (int i = 0; i < grid.Nodes_number; i++) {
            t0[i] = globalData.InitialTemp;
        }

        for (int i = 0; i < grid.Nodes_number; i++) {
            for (int j = 0; j < grid.Nodes_number; j++) {
                HCt[i][j] = (matrixH.globalC[i][j] / globalData.SimulationStepTime)+matrixH.globalH[i][j];
                Ct[i][j] = matrixH.globalC[i][j] / globalData.SimulationStepTime;
            }
        }

        for (int i = 0; i < grid.Nodes_number; i++) {
            for (int j = 0; j < grid.Nodes_number; j++) {
                Ctt[i] += Ct[i][j] * t0[i];
            }
            Ctt[i] += matrixH.globalVectorP[i];
        }

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            System.out.println(Ctt[i]);
//        }

        int times = globalData.SimulationTime / globalData.SimulationStepTime;

        GaussianElimination gaussianElimination = new GaussianElimination();

        gaussianElimination.solve(HCt, Ctt);

//        System.out.println("-------------------");
//        for (int i = 0; i < grid.Nodes_number; i++) {
//            System.out.print(dec.format(gaussianElimination.t1.get(i))+"\t");
//        }



        for (int k = 0; k < times; k++) {
            //System.out.println(k);
            for (int i = 0; i < grid.Nodes_number; i++) {
                Ctt[i]=0;
            }
            for (int i = 0; i < grid.Nodes_number; i++) {
                for (int j = 0; j < grid.Nodes_number; j++) {
                    Ctt[i] += Ct[i][j] * gaussianElimination.t1.get(i);
                }
                Ctt[i] += matrixH.globalVectorP[i];
            }
            gaussianElimination.solve(HCt,Ctt);
        }

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(HCt[i][j]) + "\t");
//            }
//            System.out.println();
//        }


//        Node one = new Node();
//        one.x = 0;
//        one.y= 0;
//
//        Node two = new Node();
//        two.x = 0.025;
//        two.y= 0;
//
//        Side side = new Side();
//
//        side.matrix(4, one, two, globalData);

        //System.out.println(element4.eta);
        //System.out.println(element4.ksi);
    }
}


