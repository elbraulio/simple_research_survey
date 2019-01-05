<%@ page import="com.elbraulio.survey.utils.Question" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    Question question = questions.get(0);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/png" href="assets/img/survey.png"/>
    <link rel="stylesheet" type="text/css" href="assets/css/footer.css">
    <link rel="stylesheet" type="text/css" href="assets/css/home.css">
    <title>Survey</title>
</head>
<body>
<div class="content">
    <a
            href="<%=question.getUrl()%>"><h1><%=question.getTitle()%>
    </h1></a>
    <div class="q-content">
        <p>
            <%=question.getSummary()%>
        </p>
    </div>
    <h2>1 minute survey</h2>
    <p class="center">(*) : answer required</p>
    <form action="survey" method="POST">
        <input name="aspirant-id" value="<%=question.getAspirantId()%>" hidden>
        <input name="ghuser-id" value="<%=question.getGhUserId()%>" hidden>
        <div class="question">
            <p>Given the topic and question above. Do you have knowledge about
                the content of the question? <strong>(*)</strong></p>
            <input name="q1" type="radio" value="yes" required/> <label>Yes</label>
            <br>
            <input name="q1" type="radio" value="no"/> <label>No</label> <br>
        </div>
        <div class="question">
            <p>Would you be willing to answer the question? <strong>(*)</strong></p>
            <input name="q2" type="radio" value="yes" required/> <label>Yes</label> <br>
            <input name="q2" type="radio" value="no"/> <label>No</label> <br>
        </div>
        <div class="question">
            <p>
                This survey was sent according to your profile,
                collaborating with ROS in Github and ROS Answers. From 1 to 4, where
                1
                is very bad and 4 is excellent, how accurate do you think our
                prediction
                was about your knowledge to answer the question above? <strong>(*)
            </strong>
            </p>
            <input name="q3" type="radio" value="1" required/> <label>1</label> <br>
            <input name="q3" type="radio" value="2"/> <label>2</label> <br>
            <input name="q3" type="radio" value="3"/> <label>3</label> <br>
            <input name="q3" type="radio" value="4"/> <label>4</label> <br>
        </div>
        <div class="question">
            <p>Please give us feedback about why you can answer or not the question
                above</p>
            <textarea name="feedback" rows="3"></textarea>
        </div>
        <br>
        <div class="submit-btn">
            <%if (questions.size() == 1) {%>
            <button>Submit</button>
            <%} else {%>
            <button>Next</button>
            <%}%>
        </div>
    </form>
</div>

<%@include file='footer.jsp' %>
</body>
</html>
