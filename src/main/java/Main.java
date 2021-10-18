import models.Person;
import models.Topic;
import service.FileService;
import service.ParseService;
import service.PersonService;
import service.TopicService;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static final TopicService topicService = new TopicService();
    private static final PersonService personService = new PersonService();

    public static void main(String... args) throws Exception {
//        topicService.create("sda");
//        Optional<Topic> exTopic = Optional.of(topicService.get(534).orElse(new Topic()));
//        Topic topic = exTopic.get();
//        topic.setName("zxc");
//        topicService.update(topic);
//        Topic topic = exTopic.ifPresent(exTopic.get());
//        Topic topic = new Topic();

        String path = "questionnaires/";
        Set<String> files = FileService.listFiles(path, 1);
        HashMap<String, ArrayList<Integer>> results = new HashMap<>();
        for (String file : files) {
            System.out.println(file);
            HashMap<String, ArrayList<String>> data = ParseService.parse(path + file);
            Optional<Person> checkPerson = Optional.of(personService.findByName(file).orElseGet(() -> personService.create(file)));
            Person person = checkPerson.get();
            int level = 0;
            int read = 0;
            ArrayList<Integer> topics = new ArrayList<>();
            for (HashMap.Entry<String, ArrayList<String>> entry : data.entrySet()) {
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
                if (Objects.equals(knowledge.get(1), "Читал")) {
                    read = read + 1;
                }

                Optional<Topic> checkTopic = Optional.of(topicService.findByName(topicName)
                        .orElseGet(() -> topicService.create(topicName)));
                Topic topic = checkTopic.get();
                int topicID = topic.getId();

                topics.add(topicID);
            }
            ArrayList<Integer> levelRead = new ArrayList<>();

            person.setName(file);
            person.setPoints(level);
            person.setReading(read);

            levelRead.add(level);
            levelRead.add(read);

            personService.update(person);

            for (Integer top : topics) {

            }

            // прилепим перки к юзеру


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
