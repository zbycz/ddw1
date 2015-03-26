package cz.zby.ddw1;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        GithubApiClient g = new GithubApiClient();
        Commit[] commits = g.getCommitsForRepo("zbycz/npress");
        
        
        GateClient client = new GateClient();
        client.run(commits);
        
        for (int i=0; i<commits.length; i++) {
            System.out.println(commits[i].positive + "+/-"+commits[i].negative + "  -- " + commits[i].message);
        }
        
    }

    
}
