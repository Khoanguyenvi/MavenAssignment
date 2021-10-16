import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {

    public static ArrayList<Students> parseJSOn(String url) throws ParseException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse =webResource.accept("application/json").get(ClientResponse.class);
        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed" + clientResponse.toString());
        }

        JSONArray jsonArray =(JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it = jsonArray.iterator();
        StudentList listStu = new StudentList();

        while (it.hasNext()){
            JSONObject jsonObject = (JSONObject)it.next();
            String iD  = jsonObject.get("id").toString();
            int id = Integer.parseInt(iD);
            String name =   jsonObject.get("first_name").toString();
            String gender =   jsonObject.get("gender").toString();
            String email =   jsonObject.get("email").toString();
            String gpa =   (String)jsonObject.get("gpa");

            Students stu = new Students( id,name,gender, email,gpa);
            listStu.addStudent(stu);
//            System. out.println(jsonObject.get("id"));
//            System.out.println(jsonObject.get("first_name"));
//            System.out.println(jsonObject.get("gender"));
//            System.out.println(jsonObject.get("email"));
//            System.out.println(jsonObject.get("gpa"));

        }
        System.out.println(listStu.getStudentsList());
        return  listStu.getStudentsList();
    }


    public static void main(String[] args) throws ParseException {
        StudentList studentList = new StudentList();
        studentList.setStudentsList(parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json"));
      System.out.println(studentList.nameStudentSearch("aida"));
    }


}
