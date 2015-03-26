<%@page import="cz.zby.ddw1.GateClient"%>
<%@page import="cz.zby.ddw1.Commit"%>
<%@page import="cz.zby.ddw1.GithubApiClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Github sentiment analysis</title>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Github sentiment analysis</a>
                </div>
            </div>
        </nav>

        <div class="container" style="margin-top: 50px;">

            <div class="starter-template">
                <h1>Github sentiment analysis</h1>


                <form class="form-inline" action="?" method="get">
                    <div class="form-group">
                        <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-home"></span></div>
                            <input name="repo" value="<%=request.getParameter("repo")%>" type="text" class="form-control" id="exampleInputAmount" placeholder="user/repo">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Analyze commit messages</button>
                </form>


                <%

                    String repo = request.getParameter("repo");
                    if (repo != null) {

                        GithubApiClient g = new GithubApiClient();
                        Commit[] commits = g.getCommitsForRepo(repo);

                        GateClient client = new GateClient();
                        client.run(commits);

                        int totalPlus = 0, totalMinus = 0, positive = 0, negative = 0;
                        for (int i = 0; i < commits.length; i++) {
                            totalPlus += commits[i].positive;
                            totalMinus += commits[i].negative;

                            if (commits[i].negative > commits[i].positive) {
                                negative++;
                            }
                            if (commits[i].negative < commits[i].positive) {
                                positive++;
                            }
                        }
                %>                

                <div class="alert alert-success" role="alert" style="margin-top:30px">

                    <p>Commit messages fetched: <%= commits.length%>

                    <p>Total words: 
                        <span class="glyphicon glyphicon-plus-sign"></span> <%= totalPlus%>
                        <span class="glyphicon glyphicon-minus-sign"></span> <%= totalMinus%>

                    <p>Total commits: 
                        <span class="glyphicon glyphicon-plus-sign"></span> <%= positive%>
                        <span class="glyphicon glyphicon-minus-sign"></span> <%= negative%>
                </div>
                
                <a class="btn btn-info" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
  Show all commits
</a>

                <div class="collapse" id="collapseExample">
                    <div class="well">
                        <%
                            for (int i = 0; i < commits.length; i++) {
                                out.println(commits[i].positive + "+/-" + commits[i].negative + "  -- " + commits[i].message + "<hr>");
                            }
                        %>
                    </div>
                </div>


                <% } %>

            </div>

        </div><!-- /.container -->
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    </body>
</html>
