import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static Double findMin(List<Double> list) {

        // check list is empty or not
        if (list == null || list.size() == 0) {
            return Double.MAX_VALUE;
        }

        // return minimum value of the ArrayList
        return Collections.min(list);
    }

    // function return maximum value in an unsorted
    // list in Java using Collection
    public static Double findMax(List<Double> list) {

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

        globalData.read(test2);
        grid.read(test2);
        //grid.printNodes();

        Element4 element4 = new Element4();

        MatrixH matrixH = new MatrixH();
        matrixH.calculateH(element4, globalData, grid, 2);

        DecimalFormat dec = new DecimalFormat("#0.000");

        for (int i = 0; i < grid.Nodes_number; i++) {
            for (int j = 0; j < grid.Nodes_number; j++) {
                System.out.print(dec.format(matrixH.globalC[i][j]) + "\t");
            }
            System.out.println();
        }

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(matrixH.globalH_list.get(i).get(j)) +"\t");
//            }
//            System.out.println();
//        }


        double[][] HCt = new double[961][961];
        double[][] Ct = new double[961][961];
        List<Double> Ctt_list = new ArrayList<>();
        double[] t0 = new double[961];

        for (int i = 0; i < grid.Nodes_number; i++) {
            t0[i] = globalData.InitialTemp;
        }

        for (int i = 0; i < grid.Nodes_number; i++) {
            for (int j = 0; j < grid.Nodes_number; j++) {
                HCt[i][j] = (matrixH.globalC[i][j] / globalData.SimulationStepTime) + matrixH.globalH[i][j];
                Ct[i][j] = matrixH.globalC[i][j] / globalData.SimulationStepTime;
            }
        }

        for (int i = 0; i < grid.Nodes_number; i++) {
            Ctt_list.add(.0);
            for (int j = 0; j < grid.Nodes_number; j++) {
                Ctt_list.set(i, Ctt_list.get(i) + Ct[i][j] * t0[i]);
            }
            Ctt_list.set(i, Ctt_list.get(i) + matrixH.globalVectorP[i]);
        }

        int times = globalData.SimulationTime / globalData.SimulationStepTime;

        GaussianElimination gaussianElimination = new GaussianElimination();

        Double[] Ctt = new Double[Ctt_list.size()];
        Ctt_list.toArray(Ctt);
        gaussianElimination.solve(HCt, Ctt);

        for (int k = 0; k < times; k++) {

            System.out.println("Time= " + (k + 1) * globalData.SimulationStepTime + "\tTmin= " + findMin(gaussianElimination.t1) + "\tTmax= " + findMax(gaussianElimination.t1));

            for (int i = 0; i < grid.Nodes_number; i++) {
                for (int j = 0; j < grid.Nodes_number; j++) {
                    HCt[i][j] = (matrixH.globalC[i][j] / globalData.SimulationStepTime) + matrixH.globalH[i][j];
                    Ct[i][j] = matrixH.globalC[i][j] / globalData.SimulationStepTime;
                }
            }

            for (int i = 0; i < grid.Nodes_number; i++) {
                Ctt[i] = .0;
            }
            Ctt_list.clear();
            for (int i = 0; i < grid.Nodes_number; i++) {
                Ctt_list.add(.0);
                for (int j = 0; j < grid.Nodes_number; j++) {
                    Ctt_list.set(i, Ctt_list.get(i) + Ct[i][j] * gaussianElimination.t1.get(j));
                }
                Ctt_list.set(i, Ctt_list.get(i) + matrixH.globalVectorP[i]);
            }
            Ctt_list.toArray(Ctt);
            gaussianElimination.solve(HCt, Ctt);
        }
    }
}


