import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        readFile();

    }

    //1º Leer el archivo

    public static void readFile() throws FileNotFoundException {

        try {
            FileReader fileReader = new FileReader("C:/Users/pablo.fuentes/Desktop/people.csv.txt");
            BufferedReader miBuffer = new BufferedReader(fileReader); //lee el documento

            String infoPersonas = miBuffer.readLine(); //En linea recojo la información que lee
            while (infoPersonas != null) { // hasta que no encuentre un null no para de leer
                infoPersonas = miBuffer.readLine();// que me lea cada linea despues de recortar con split
                if(infoPersonas != null){
                    createPerson(infoPersonas);// a esta información le aplicamos el metodo crear persona
                } else{
                   // System.out.println("Fin del documento");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2º Con esos datos creas personas

    private static void createPerson(String infoPersonas) {
        List<Person> personas = new ArrayList<>();
        Person person = new Person();
        try{
            String[] datosPersona = infoPersonas.split(":");
            person.setName(datosPersona[0]);

            if (datosPersona.length >= 2) {
                if (datosPersona[1].isEmpty()) {
                    person.setTown("Desconocida");
                } else {
                    person.setTown(datosPersona[1]);
                }
            }
            person.setAge((datosPersona.length < 3) ? 0 : Integer.parseInt(datosPersona[2]));
            personas.add(person);
            filtrarPersonas(personas);
        } catch(Exception e){
            System.out.println(e);
        }

    }

    //3º Con este metodo filtramos los datos

    public static void filtrarPersonas(List<Person> personas){
        List<Person> personList = personas;
        personList.stream()
                .filter(p -> p.getName() != null && p.getTown() != null && p.getAge() < 25 && p.getAge() != 0)
                .forEach(person -> System.out.println("Nombre: " + person.getName() + ", Ciudad: " + person.getTown() +
                        ", Edad: " + person.getAge()));
    }
}


