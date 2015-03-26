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
        JSONObject obj = getJson("https://api.github.com/repos/"+repo_user+"/commits?per_page=200");
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
        if(false && !url.equals("xxx"))
            return "[\n" +
"  {\n" +
"    \"sha\": \"b54708da92713f85aa7f3a1219ae37b712aba56e\",\n" +
"    \"commit\": {\n" +
"      \"author\": {\n" +
"        \"name\": \"Kevin Renskers\",\n" +
"        \"email\": \"info@mixedcase.nl\",\n" +
"        \"date\": \"2015-03-24T18:40:35Z\"\n" +
"      },\n" +
"      \"committer\": {\n" +
"        \"name\": \"Kevin Renskers\",\n" +
"        \"email\": \"info@mixedcase.nl\",\n" +
"        \"date\": \"2015-03-24T18:40:35Z\"\n" +
"      },\n" +
"      \"message\": \"adore fuck two-faced fail 1.6.0\",\n" +
"      \"tree\": {\n" +
"        \"sha\": \"254630bffd68c27271bfe9b84ab9c1d263be836d\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/trees/254630bffd68c27271bfe9b84ab9c1d263be836d\"\n" +
"      },\n" +
"      \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/commits/b54708da92713f85aa7f3a1219ae37b712aba56e\",\n" +
"      \"comment_count\": 0\n" +
"    },\n" +
"    \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/b54708da92713f85aa7f3a1219ae37b712aba56e\",\n" +
"    \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/b54708da92713f85aa7f3a1219ae37b712aba56e\",\n" +
"    \"comments_url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/b54708da92713f85aa7f3a1219ae37b712aba56e/comments\",\n" +
"    \"author\": {\n" +
"      \"login\": \"kevinrenskers\",\n" +
"      \"id\": 229384,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/229384?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/kevinrenskers\",\n" +
"      \"html_url\": \"https://github.com/kevinrenskers\",\n" +
"      \"followers_url\": \"https://api.github.com/users/kevinrenskers/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/kevinrenskers/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/kevinrenskers/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/kevinrenskers/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/kevinrenskers/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/kevinrenskers/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/kevinrenskers/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/kevinrenskers/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/kevinrenskers/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"committer\": {\n" +
"      \"login\": \"kevinrenskers\",\n" +
"      \"id\": 229384,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/229384?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/kevinrenskers\",\n" +
"      \"html_url\": \"https://github.com/kevinrenskers\",\n" +
"      \"followers_url\": \"https://api.github.com/users/kevinrenskers/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/kevinrenskers/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/kevinrenskers/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/kevinrenskers/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/kevinrenskers/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/kevinrenskers/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/kevinrenskers/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/kevinrenskers/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/kevinrenskers/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"parents\": [\n" +
"      {\n" +
"        \"sha\": \"854aaea88be42087c81700a51281d9de65fd3158\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/854aaea88be42087c81700a51281d9de65fd3158\",\n" +
"        \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/854aaea88be42087c81700a51281d9de65fd3158\"\n" +
"      }\n" +
"    ]\n" +
"  },\n" +
"  {\n" +
"    \"sha\": \"854aaea88be42087c81700a51281d9de65fd3158\",\n" +
"    \"commit\": {\n" +
"      \"author\": {\n" +
"        \"name\": \"Kevin Renskers\",\n" +
"        \"email\": \"kevin@mixedcase.nl\",\n" +
"        \"date\": \"2015-03-24T18:36:14Z\"\n" +
"      },\n" +
"      \"committer\": {\n" +
"        \"name\": \"Kevin Renskers\",\n" +
"        \"email\": \"kevin@mixedcase.nl\",\n" +
"        \"date\": \"2015-03-24T18:36:14Z\"\n" +
"      },\n" +
"      \"message\": \"Merge pull request #131 from zbycz/patch-1\\n\\nURL navigation on modal dialogs\",\n" +
"      \"tree\": {\n" +
"        \"sha\": \"d4c6bb5df632d4b853c5fa8485a9aa7b58abfb9d\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/trees/d4c6bb5df632d4b853c5fa8485a9aa7b58abfb9d\"\n" +
"      },\n" +
"      \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/commits/854aaea88be42087c81700a51281d9de65fd3158\",\n" +
"      \"comment_count\": 0\n" +
"    },\n" +
"    \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/854aaea88be42087c81700a51281d9de65fd3158\",\n" +
"    \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/854aaea88be42087c81700a51281d9de65fd3158\",\n" +
"    \"comments_url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/854aaea88be42087c81700a51281d9de65fd3158/comments\",\n" +
"    \"author\": {\n" +
"      \"login\": \"kevinrenskers\",\n" +
"      \"id\": 229384,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/229384?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/kevinrenskers\",\n" +
"      \"html_url\": \"https://github.com/kevinrenskers\",\n" +
"      \"followers_url\": \"https://api.github.com/users/kevinrenskers/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/kevinrenskers/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/kevinrenskers/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/kevinrenskers/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/kevinrenskers/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/kevinrenskers/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/kevinrenskers/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/kevinrenskers/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/kevinrenskers/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"committer\": {\n" +
"      \"login\": \"kevinrenskers\",\n" +
"      \"id\": 229384,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/229384?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/kevinrenskers\",\n" +
"      \"html_url\": \"https://github.com/kevinrenskers\",\n" +
"      \"followers_url\": \"https://api.github.com/users/kevinrenskers/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/kevinrenskers/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/kevinrenskers/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/kevinrenskers/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/kevinrenskers/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/kevinrenskers/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/kevinrenskers/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/kevinrenskers/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/kevinrenskers/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"parents\": [\n" +
"      {\n" +
"        \"sha\": \"d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"        \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\"\n" +
"      },\n" +
"      {\n" +
"        \"sha\": \"55905f5762a4b05acee7d194fa236373dad6f3b3\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/55905f5762a4b05acee7d194fa236373dad6f3b3\",\n" +
"        \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/55905f5762a4b05acee7d194fa236373dad6f3b3\"\n" +
"      }\n" +
"    ]\n" +
"  },\n" +
"  {\n" +
"    \"sha\": \"55905f5762a4b05acee7d194fa236373dad6f3b3\",\n" +
"    \"commit\": {\n" +
"      \"author\": {\n" +
"        \"name\": \"zbycz\",\n" +
"        \"email\": \"zbytovsky@gmail.com\",\n" +
"        \"date\": \"2015-03-24T13:59:43Z\"\n" +
"      },\n" +
"      \"committer\": {\n" +
"        \"name\": \"zbycz\",\n" +
"        \"email\": \"zbytovsky@gmail.com\",\n" +
"        \"date\": \"2015-03-24T18:28:16Z\"\n" +
"      },\n" +
"      \"message\": \"Showing modals via #hash change and observing hashchange event\",\n" +
"      \"tree\": {\n" +
"        \"sha\": \"d4c6bb5df632d4b853c5fa8485a9aa7b58abfb9d\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/trees/d4c6bb5df632d4b853c5fa8485a9aa7b58abfb9d\"\n" +
"      },\n" +
"      \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/commits/55905f5762a4b05acee7d194fa236373dad6f3b3\",\n" +
"      \"comment_count\": 0\n" +
"    },\n" +
"    \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/55905f5762a4b05acee7d194fa236373dad6f3b3\",\n" +
"    \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/55905f5762a4b05acee7d194fa236373dad6f3b3\",\n" +
"    \"comments_url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/55905f5762a4b05acee7d194fa236373dad6f3b3/comments\",\n" +
"    \"author\": {\n" +
"      \"login\": \"zbycz\",\n" +
"      \"id\": 385047,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/385047?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/zbycz\",\n" +
"      \"html_url\": \"https://github.com/zbycz\",\n" +
"      \"followers_url\": \"https://api.github.com/users/zbycz/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/zbycz/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/zbycz/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/zbycz/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/zbycz/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/zbycz/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/zbycz/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/zbycz/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/zbycz/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"committer\": {\n" +
"      \"login\": \"zbycz\",\n" +
"      \"id\": 385047,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/385047?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/zbycz\",\n" +
"      \"html_url\": \"https://github.com/zbycz\",\n" +
"      \"followers_url\": \"https://api.github.com/users/zbycz/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/zbycz/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/zbycz/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/zbycz/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/zbycz/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/zbycz/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/zbycz/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/zbycz/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/zbycz/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"parents\": [\n" +
"      {\n" +
"        \"sha\": \"d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"        \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\"\n" +
"      }\n" +
"    ]\n" +
"  },\n" +
"  {\n" +
"    \"sha\": \"d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"    \"commit\": {\n" +
"      \"author\": {\n" +
"        \"name\": \"Kevin Renskers\",\n" +
"        \"email\": \"info@mixedcase.nl\",\n" +
"        \"date\": \"2015-03-11T10:23:58Z\"\n" +
"      },\n" +
"      \"committer\": {\n" +
"        \"name\": \"Kevin Renskers\",\n" +
"        \"email\": \"info@mixedcase.nl\",\n" +
"        \"date\": \"2015-03-11T10:23:58Z\"\n" +
"      },\n" +
"      \"message\": \"Update release date\",\n" +
"      \"tree\": {\n" +
"        \"sha\": \"609836cf7f757a63043b0922c3d25b654b1fc32a\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/trees/609836cf7f757a63043b0922c3d25b654b1fc32a\"\n" +
"      },\n" +
"      \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/git/commits/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"      \"comment_count\": 0\n" +
"    },\n" +
"    \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"    \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf\",\n" +
"    \"comments_url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/d6609d4ce78c146e34ba7cdacaddf447b4dc8cbf/comments\",\n" +
"    \"author\": {\n" +
"      \"login\": \"kevinrenskers\",\n" +
"      \"id\": 229384,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/229384?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/kevinrenskers\",\n" +
"      \"html_url\": \"https://github.com/kevinrenskers\",\n" +
"      \"followers_url\": \"https://api.github.com/users/kevinrenskers/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/kevinrenskers/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/kevinrenskers/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/kevinrenskers/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/kevinrenskers/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/kevinrenskers/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/kevinrenskers/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/kevinrenskers/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/kevinrenskers/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"committer\": {\n" +
"      \"login\": \"kevinrenskers\",\n" +
"      \"id\": 229384,\n" +
"      \"avatar_url\": \"https://avatars.githubusercontent.com/u/229384?v=3\",\n" +
"      \"gravatar_id\": \"\",\n" +
"      \"url\": \"https://api.github.com/users/kevinrenskers\",\n" +
"      \"html_url\": \"https://github.com/kevinrenskers\",\n" +
"      \"followers_url\": \"https://api.github.com/users/kevinrenskers/followers\",\n" +
"      \"following_url\": \"https://api.github.com/users/kevinrenskers/following{/other_user}\",\n" +
"      \"gists_url\": \"https://api.github.com/users/kevinrenskers/gists{/gist_id}\",\n" +
"      \"starred_url\": \"https://api.github.com/users/kevinrenskers/starred{/owner}{/repo}\",\n" +
"      \"subscriptions_url\": \"https://api.github.com/users/kevinrenskers/subscriptions\",\n" +
"      \"organizations_url\": \"https://api.github.com/users/kevinrenskers/orgs\",\n" +
"      \"repos_url\": \"https://api.github.com/users/kevinrenskers/repos\",\n" +
"      \"events_url\": \"https://api.github.com/users/kevinrenskers/events{/privacy}\",\n" +
"      \"received_events_url\": \"https://api.github.com/users/kevinrenskers/received_events\",\n" +
"      \"type\": \"User\",\n" +
"      \"site_admin\": false\n" +
"    },\n" +
"    \"parents\": [\n" +
"      {\n" +
"        \"sha\": \"c89903e51943c63564ae2d42170dd100504e404f\",\n" +
"        \"url\": \"https://api.github.com/repos/kevinrenskers/raml2html/commits/c89903e51943c63564ae2d42170dd100504e404f\",\n" +
"        \"html_url\": \"https://github.com/kevinrenskers/raml2html/commit/c89903e51943c63564ae2d42170dd100504e404f\"\n" +
"      }\n" +
"    ]\n" +
"  }]";
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
