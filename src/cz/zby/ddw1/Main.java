package cz.zby.ddw1;

import org.json.*;

public class Main {

    public static void main(String[] args) {

        JSONObject obj = new JSONObject("{'hi': 'yeah'}");
        String pageName = obj.getString("hi");
        System.err.println(pageName);

//JSONArray arr = obj.getJSONArray("posts");
//for (int i = 0; i < arr.length(); i++)
//{
//    String post_id = arr.getJSONObject(i).getString("post_id");
//    ......
//}
//        GateClient client = new GateClient();        
//        client.run();    
    }
}
