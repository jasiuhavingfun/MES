import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grid {
    int Nodes_number; // ilosc wezlow
    int Elements_number; // ilosc elementow

    List<Node> Nodes = new ArrayList<Node>(Nodes_number);
    List<Element> Elements = new ArrayList<Element>(Elements_number);

    public void printElements() {
        for (int i = 0; i < this.Elements_number; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(this.Elements.get(i).ID[j] + "\t");
            }
            System.out.println();
        }
    }

    public void printNodes() {
        for (int i = 0; i < this.Nodes_number; i++) {
            System.out.print(this.Nodes.get(i).x + "\t" + this.Nodes.get(i).y + "\t" + this.Nodes.get(i).BC);
            System.out.println();
        }
    }

    public void read(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String trash;
            String val;

            for (int i = 0; i < 8; i++) {
                trash = scanner.nextLine();
            }
            trash = scanner.next();
            trash = scanner.next();
            this.Nodes_number = scanner.nextInt();
            trash = scanner.next();
            trash = scanner.next();
            this.Elements_number = scanner.nextInt();
            trash = scanner.next();

            for (int i = 0; i < this.Nodes_number; i++) {
                Node varN = new Node();
                trash = scanner.next();
                val = scanner.next();
                val = val.replaceAll(",", "");
                varN.x = Double.parseDouble(val);
                varN.y = scanner.nextDouble();

                this.Nodes.add(varN);
            }

            trash = scanner.nextLine();
            trash = scanner.nextLine();

            for (int i = 0; i < this.Elements_number; i++) {

                Element varE = new Element();
                trash = scanner.next();

                for (int j = 0; j < 4; j++) {
                    val = scanner.next();

                    val = val.replaceAll(",", "");
                    varE.ID[j] = Integer.parseInt(val);
                }
                this.Elements.add(varE);
            }

            trash = scanner.nextLine();
            trash = scanner.nextLine();
            int valBC;

            while (scanner.hasNext()) {

                val = scanner.next();
                val = val.replaceAll(",", "");

                valBC = Integer.parseInt(val);
                this.Nodes.get(valBC - 1).BC = true;
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error reading grid occurred.");
            e.printStackTrace();
        }

    }

}

