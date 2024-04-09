import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BufferedReader reader;
        List<Creature> creatures=new ArrayList<>();

        try {

            reader = new BufferedReader(new FileReader("inp.txt"));
            try {
                int darab = Integer.parseInt(reader.readLine());
                String line ;
                for (int i = 0;i<darab;i++) {
                    line = reader.readLine();
                    String [] data=line.split(" ");
                    switch (data[1]) {
                        case "h" -> {
                            creatures.add(new Homokjaro(data[0], Integer.parseInt(data[2])));
                        }
                        case "s" -> {
                            creatures.add(new Szivacs(data[0], Integer.parseInt(data[2])));
                        }
                        case "l" -> {
                            creatures.add(new Lepegeto(data[0], Integer.parseInt(data[2])));
                        }
                        default -> {
                            System.out.println("A hibás lény neve: "+data[0]+" nincs ilyen fajtájú lény: "+data[1]);
                        }
                    }
                }

                String day = reader.readLine();
                char[] days=day.toCharArray();
                reader.close();

                for (char d : days) {
                    switch(d){
                        case 'n'->{
                            for (Creature c:creatures) {
                                c.napos();
                            }
                        }
                        case 'f'->{
                            for (Creature c:creatures) {
                                c.felhos();
                            }
                        }
                        case 'e'->{
                            for (Creature c:creatures) {
                                c.esos();
                            }
                        }
                        default -> {
                            System.out.println("Nincs ilyen időjárás");
                        }
                    }
                }

                int winnerDistance=0;
                String winnerName="";

                for (Creature c:creatures) {
                    if(c.isAlive()){
                        if(c.getDistance()>winnerDistance){
                            winnerDistance=c.getDistance();
                            winnerName=c.getName();
                        }
                    }
                }

                System.out.println(winnerName);
            }catch (NumberFormatException e){
                System.out.println("Nem számérték van a megadott helyen");
            }


        } catch (IOException e) {
            System.out.println("Nincs ilyen nevű fájl");
        }

    }
}
