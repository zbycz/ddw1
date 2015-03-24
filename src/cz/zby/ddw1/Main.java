package cz.zby.ddw1;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        GithubApiClient g = new GithubApiClient();
        Commit[] commitsForRepo = g.getCommitsForRepo("zbycz/npress");
        
        
        System.out.println(Arrays.toString(commitsForRepo));
        
//        GateClient client = new GateClient();
//        client.run();    
    }

    
}
