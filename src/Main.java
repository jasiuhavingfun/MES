import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static Double findMin(List<Double> list)
    {

        // check list is empty or not
        if (list == null || list.size() == 0) {
            return Double.MAX_VALUE;
        }

        // return minimum value of the ArrayList
        return Collections.min(list);
    }

    // function return maximum value in an unsorted
    // list in Java using Collection
    public static Double findMax(List<Double> list)
    {

        // check list is empty or not
        if (list == null || list.size() == 0) {
            return Double.MIN_VALUE;
        }

        // return maximum value of the ArrayList
        return Collections.max(list);
    }
    public static void main(String[] args) {

        String test1 = "C:\\Users\\janzi\\IdeaProjects\\MES\\src\\Test1_4_4.txt";
        String test2 = "C:\\Users\\janzi\\IdeaProjects\\MES\\src\\Test2_4_4_MixGrid.txt";
        String test3 = "C:\\Users\\janzi\\IdeaProjects\\MES\\src\\Test3_31_31_kwadrat.txt";

        GlobalData globalData = new GlobalData();
        Grid grid = new Grid();

        globalData.read(test3);
        grid.read(test3);
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
//
//        System.out.println("-------------------------------------------");
//        for (int i = 0; i < 16; i++) {
//            System.out.println(i +"\t"+matrixH.globalVectorP_list.get(i));
//        }


//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                System.out.print(matrixH.globalH[i][j]+"\t");
//            }
//            System.out.println();
//        }

        DecimalFormat dec = new DecimalFormat("#0.0");

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(matrixH.globalH[i][j]) +"\t");
//            }
//            System.out.println();
//        }

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(matrixH.globalH_list.get(i).get(j)) +"\t");
//            }
//            System.out.println();
//        }


        double[][] HCt = new double[961][961]; // dynamiczne powiino byc
        double[][] Ct = new double[961][961]; // dynamiczne powiino byc
        //double[] Ctt = new double[16]; // dynamiczne powiino byc
        List<Double> Ctt_list = new ArrayList<>();
        double[] t0 = new double[961]; // dynamiczne powiino byc

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
            Ctt_list.add(.0);
            for (int j = 0; j < grid.Nodes_number; j++) {
                //Ctt[i] += Ct[i][j] * t0[i];
                Ctt_list.set(i, Ctt_list.get(i)+Ct[i][j] * t0[i]);
            }
            //Ctt[i] += matrixH.globalVectorP[i];
            Ctt_list.set(i, Ctt_list.get(i)+matrixH.globalVectorP[i]);
        }

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            System.out.print(dec.format(Ctt[i])+"\t");
//        }
//
//        System.out.println("---------------------------------------");
        int times = globalData.SimulationTime / globalData.SimulationStepTime;

        GaussianElimination gaussianElimination = new GaussianElimination();

//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                System.out.print(HCt[i][j]+"\t");
//            }
//            System.out.println();
//        }

        Double[]Ctt = new Double[Ctt_list.size()];
        Ctt_list.toArray(Ctt);
        gaussianElimination.solve(HCt, Ctt);

//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                System.out.print(HCt[i][j]+"\t");
//            }
//            System.out.println();
//        }

//        System.out.println("-------------------");
//        for (int i = 0; i < grid.Nodes_number; i++) {
//            System.out.print(dec.format(gaussianElimination.t1.get(i))+"\t");
//        }


        for (int k = 0; k < times; k++) {

            System.out.println("Time= "+(k+1)* globalData.SimulationStepTime + "\tTmin= "+ findMin(gaussianElimination.t1)+ "\tTmax= "+ findMax(gaussianElimination.t1));

            for (int i = 0; i < grid.Nodes_number; i++) {
                for (int j = 0; j < grid.Nodes_number; j++) {
                    HCt[i][j] = (matrixH.globalC[i][j] / globalData.SimulationStepTime)+matrixH.globalH[i][j];
                    Ct[i][j] = matrixH.globalC[i][j] / globalData.SimulationStepTime;
                }
            }

            //System.out.println(k);
            for (int i = 0; i < grid.Nodes_number; i++) {
                Ctt[i]=.0;
                //System.out.println(gaussianElimination.t1.get(i));
            }
            Ctt_list.clear();
            for (int i = 0; i < grid.Nodes_number; i++) {
                Ctt_list.add(.0);
                for (int j = 0; j < grid.Nodes_number; j++) {
                    //Ctt[i] += Ct[i][j] * gaussianElimination.t1.get(j);
                    Ctt_list.set(i, Ctt_list.get(i)+Ct[i][j] * gaussianElimination.t1.get(j));
                }
                Ctt_list.set(i, Ctt_list.get(i)+matrixH.globalVectorP[i]);
                //Ctt[i] += matrixH.globalVectorP[i];
            }
//            for (int i = 0; i < grid.Nodes_number; i++) {
//                System.out.print(dec.format(Ctt[i])+"\t");
//            }
            Ctt_list.toArray(Ctt);
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


