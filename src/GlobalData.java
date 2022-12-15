import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GlobalData {
    int SimulationTime; // czas
    int SimulationStepTime; // przyrost czasu
    int Conductivity;
    int Alfa; // wspolczynnik przewodenia
    int Tot; // temp otoczenia
    int InitialTemp;
    int Density; // gestosc
    int SpecificHeat; // cieplo wlasiwe

    public void read(String fileName) {

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String trash = scanner.next();

            this.SimulationTime = scanner.nextInt();
            trash = scanner.next();
            this.SimulationStepTime = scanner.nextInt();
            trash = scanner.next();
            this.Conductivity = scanner.nextInt();
            trash = scanner.next();
            this.Alfa = scanner.nextInt();
            trash = scanner.next();
            this.Tot = scanner.nextInt();
            trash = scanner.next();
            this.InitialTemp = scanner.nextInt();
            trash = scanner.next();
            this.Density = scanner.nextInt();
            trash = scanner.next();
            this.SpecificHeat = scanner.nextInt();

        } catch (FileNotFoundException e) {
            System.out.println("An error reading global data occurred.");
            e.printStackTrace();
        }
    }

    public void print(){
        System.out.println("GlobalData{" +
                "SimulationTime=" + SimulationTime +
                ", SimulationStepTime=" + SimulationStepTime +
                ", Conductivity=" + Conductivity +
                ", Alfa=" + Alfa +
                ", Tot=" + Tot +
                ", InitialTemp=" + InitialTemp +
                ", Density=" + Density +
                ", SpecificHeat=" + SpecificHeat +
                '}');
    }
}

