package Day10Task;
import org.json.JSONObject;
import org.json.XML;

public class JsonToXmlConverter {
    public static void main(String[] args) {

        String jsonString = "{ \"employee\": { \"name\": \"John\", \"age\": 30, \"department\": \"Sales\" } }";
        JSONObject jsonObject = new JSONObject(jsonString);
        String xmlString = XML.toString(jsonObject);
        System.out.println("XML Output:\n" + xmlString);
    }
}
