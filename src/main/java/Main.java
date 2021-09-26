import service.File;
import service.Parse;
import service.TopicService;
import java.util.Set;

public class Main {
    private static final TopicService topicService = new TopicService();

    public static void main(String... args) throws Exception {
//        List<Topic> topics = topicService.getAll();
//        System.out.println(topics);
//        for (Topic topic : topics) {
//            System.out.println(topic.getName());
////             topicService.deleteTopic(topic);
//        }
        String path = "questionnaires/";
        Set<String> files = File.listFiles(path, 1);
        for (String file: files) {
            System.out.println(file);
            System.out.println(Parse.parse(path + file));
        }
    }


}
