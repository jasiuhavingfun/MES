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
        //element4.calculate(3);

        MatrixH matrixH = new MatrixH();
        matrixH.calculateH(element4, globalData, grid, 2);

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


//        DecimalFormat dec = new DecimalFormat("#0.00");
//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(matrixH.globalH[i][j]) +"\t");
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

//        for (int i = 0; i < grid.Nodes_number; i++) {
//            for (int j = 0; j < grid.Nodes_number; j++) {
//                System.out.print(dec.format(matrixH.Hglobal.get(i).get(j)) +"\t");
//            }
//            System.out.println();
//        }
        //System.out.println(element4.eta);
        //System.out.println(element4.ksi);
    }
}


