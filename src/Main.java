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
        element4.calculate(3);

        MatrixH matrixH = new MatrixH();
        matrixH.calculateH(element4, globalData, grid, 3);
        //System.out.println(element4.eta);
        //System.out.println(element4.ksi);
    }
}


