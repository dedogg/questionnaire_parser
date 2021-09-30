import models.Person;
import service.FileService;
import service.ParseService;
import service.PersonService;
import service.TopicService;

import java.util.*;

public class Main {
    private static final TopicService topicService = new TopicService();
    private static final PersonService personService = new PersonService();

    public static void main(String... args) throws Exception {
        String path = "questionnaires/";
        Set<String> files = FileService.listFiles(path, 1);
        HashMap<String, ArrayList<Integer>> results = new HashMap<>();
        for (String file : files) {
            System.out.println(file);
//            int personID = 0;
            HashMap<String, ArrayList<String>> data = ParseService.parse(path + file);
            Optional<Person> existPerson = personService.findByName(file);

            int personID = existPerson.map(Person::getId).orElse(0);
            if (existPerson.isEmpty()) {
                personID = personService.create(file);
            }
            int level = 0;
            int read = 0;
            //if (personID != 0) {
            for (HashMap.Entry<String, ArrayList<String>> entry : data.entrySet()) {
                int topicID = 0;
                int k = 0;
                String topicName = entry.getKey();
                ArrayList<String> knowledge = entry.getValue();
                //пропускаем ненужные строки
                switch (knowledge.get(0)) {
                    case "":
                        continue;
                    case "Знаю на базовом уровне":
                        k = 1;
                        break;
                    case "Уверенное владение информацией и практикой":
                        k = 2;
                        break;
                    case "Знаю абсолютно все по теме и могу применить":
                        k = 3;
                        break;

                }
                level = level + k;
//                System.out.println(knowledge.get(0));
//                System.out.println(knowledge.get(1));
                if (Objects.equals(knowledge.get(1), "Читал")) {
                    read = read + 1;
                }


                //                System.out.println(entry.getKey());
//                    topicID = topicService.create(entry.getKey());

//                    if (topicID) {
//
//                    }
            }
            //}
            ArrayList<Integer> levelRead = new ArrayList<>();

            Person person = new Person();
            person.setId(personID);
            person.setName(file);
            person.setPoints(level);
            person.setReading(read);
            levelRead.add(level);
            levelRead.add(read);
            personService.update(person);
            results.put(file, levelRead);
            if (args.length != 0 && (Objects.equals(args[0], "true"))) {
                break;
            }
        }
        for (HashMap.Entry<String, ArrayList<Integer>> result : results.entrySet()) {
            System.out.println(result.getKey() + " " + result.getValue() + "\n");
        }
        System.out.println(results);
    }
}
