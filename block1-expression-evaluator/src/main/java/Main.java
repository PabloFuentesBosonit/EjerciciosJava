import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        read_file();
    }

    private static void revisarCodigo (String infoPersonas) {
        long count = infoPersonas.chars().filter(character -> character == ':').count();

        if(count == 2){ //Te enseña el primer erroneo;
            String [] datos = infoPersonas.split(":");

            if (datos[0].isBlank() || datos[0].isEmpty()){
                System.out.println("Faltaria el nombre de uno de los campos");
            }
        }
    }

    public static void filtrarEdad(ArrayList<Person> Personas){
        List<Person> listPersonFiltrada = Personas.stream()
                .filter(persona -> persona.getAge() < 25 && persona.getAge() != 0).collect(Collectors.toList());

        List<Person> empiezaPorA = Personas.stream()
                .filter(person -> !person.getName().startsWith("A")).collect(Collectors.toList());

        Optional<Person> townIsMadrid = Personas.stream()
                .filter(person -> person.getTown().equals("Madrid")).findFirst();

        Optional<Person> townIsBarcelona = Personas.stream()
                .filter(person -> person.getTown().equals("Barcelona")).findFirst();

        listPersonFiltrada.forEach(System.out::println);// listPersonFiltrada.forEach(System.out::println);

        for(Person persona: Personas){

            if (persona.getName() == null){
                persona.setName("Unknow");
            }
            if (persona.getTown() == null){
                persona.setTown("Unknow");
            }

            //System.out.println(persona.toString() + "este es el que quiero"); // Este los muestra todos
        }

    }

    private static Person createPerson (String infoPersonas){
        Person person = new Person();

        String[] datosPersona = infoPersonas.split(":");

        person.setName(datosPersona[0]);
        person.setTown(datosPersona[1]);
        person.setAge ((datosPersona.length < 3) ? 0 : Integer.parseInt(datosPersona[2]));

        ArrayList<Person> arrayPersonas = new ArrayList<>();
        arrayPersonas.add(person);

        filtrarEdad(arrayPersonas);
        revisarCodigo(infoPersonas);

        // System.out.println(person.toString());
        return person;
    }


    public static void read_file() throws FileNotFoundException {

        try {
            Path entrada = Paths.get("C:/Users/pablo.fuentes/Desktop/people.csv.txt");
            BufferedReader mibuffer = Files.newBufferedReader(entrada); //recoge el texto
            String line = mibuffer.readLine();//lee el documento


            while (line != null) { // hasta que no encuentre un null no para

                String[] personasData = line.split(":");
                line = mibuffer.readLine();// que me lea cada linea recortada con split
                createPerson(line);// a esta información le aplicamos el metodo crear persona
            }

        }   catch (IOException e){
            e.printStackTrace();
        }
    }
}
