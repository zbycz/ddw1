package cz.zby.ddw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author Pavel
 */
public class GithubApiClient {

    public Commit[] getCommitsForRepo(String repo_user) {
        JSONObject obj = getJson("https://api.github.com/repos/"+repo_user+"/commits");
        JSONArray arr = obj.getJSONArray("root");
        
        Commit[] commits = new Commit[arr.length()];
        for (int i = 0; i < arr.length(); i++)
        {
            Commit c = new Commit();
            c.sha = arr.getJSONObject(i).getString("sha");
            c.author = arr.getJSONObject(i).getJSONObject("author").getString("login");
            c.message = arr.getJSONObject(i).getJSONObject("commit").getString("message");
            
            commits[i] = c;
        }
        
        return commits;
    }
    
    private JSONObject getJson(String url) {
       //http://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
        return new JSONObject("{'root':"+fetchUrl(url)+"}");
        //String pageName = obj.getString("hi");
        //JSONArray arr = obj.getJSONArray("posts");

    }

    private String fetchUrl(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream response = connection.getInputStream();

            StringBuilder sb = new StringBuilder();
            InputStreamReader in = new InputStreamReader(response, Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(in);
            int cp;
            while ((cp = bufferedReader.read()) != -1) {
                sb.append((char) cp);
            }
            bufferedReader.close();
            in.close();

            return sb.toString();

        } catch (Exception ex) {
            throw new RuntimeException("Exception while calling URL:" + url, ex);
        }
    }
}
