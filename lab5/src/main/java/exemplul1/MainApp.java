package exemplul1;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class MainApp {

    public static List<Angajat> citire() {
        try {

            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> angajati = mapper.readValue(file, new TypeReference<List<Angajat>>(){});
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();

            File file=new File("src/main/resources/angajati.json");
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

//        LocalDate d1 = LocalDate.of(2017, Month.FEBRUARY, 18);
//        LocalDate d2 = LocalDate.of(2014, Month.FEBRUARY, 20);
//        LocalDate d3 = LocalDate.of(2022, Month.APRIL, 19);
//        LocalDate d4 = LocalDate.of(2010, Month.FEBRUARY, 11);
//        LocalDate d5 = LocalDate.of(2018, Month.DECEMBER, 18);
//        List<Angajat> angajati = new ArrayList<>();
//        angajati.add(new Angajat("Persoana 1","Programator", d1 , 3000));
//        angajati.add(new Angajat("Persoana 2","Profesor", d2 , 300));
//        angajati.add(new Angajat("Persoana 3","Sef", d3 , 20));
//        angajati.add(new Angajat("Persoana 4","Student", d4 , 1100));
//        angajati.add(new Angajat("Persoana 5","Sef", d5 , 500));
//        scriere(angajati);

//        List<Angajat> angajati=citire();
//
//        for(Angajat p:angajati){
//            System.out.println(p);
//        }
        List<Angajat> angajati = citire();
        System.out.println("1----------------");

        angajati.forEach(System.out::println);

        System.out.println("2----------------");

        angajati.stream().filter(a->a.getSalariu()>1000f).forEach(System.out::println);

        System.out.println("3----------------");

        LocalDate d = LocalDate.of(2022, Month.APRIL, 01);
        LocalDate d6 = LocalDate.of(2022, Month.MAY, 01);


        angajati.stream()
                .filter(a->a.getData_angajarii().isAfter(d))
                .filter(a->a.getData_angajarii().isBefore(d6))
                .filter(a->a.getPost().toLowerCase().contains("sef"))
                //.filter(a->a.getPost().toLowerCase().contains("director"))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }
}