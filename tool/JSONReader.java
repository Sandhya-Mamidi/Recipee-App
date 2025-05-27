import java.io.*;
import org.json.*;

public class JSONReader {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("JSONExample.txt"));
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            JSONObject obj = new JSONObject(jsonContent.toString());

            System.out.println(obj.getString("semo_id"));
            System.out.println(obj.getString("firstName"));
            System.out.println(obj.getString("lastName"));
            System.out.println(obj.getString("semester"));

            JSONObject courses = obj.getJSONObject("course");
            for (String key : courses.keySet()) {
                System.out.println(key + " : " + courses.getString(key));
            }

            JSONArray approaches = obj.getJSONArray("approach");
            for (int i = 0; i < approaches.length(); i++) {
                JSONObject a = approaches.getJSONObject(i);
                System.out.println("course : " + a.getString("course"));
                System.out.println("type : " + a.getString("type"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
