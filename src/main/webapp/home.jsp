<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/png" href="assets/img/survey.png"/>
    <link rel="stylesheet" type="text/css" href="assets/css/footer.css">
    <link rel="stylesheet" type="text/css" href="assets/css/home.css">
    <title>Survey</title>
</head>
<body>
<h1>Run algorithm Dijkstra and A star</h1>
<div class="q-content">
    <p>
        I would like to know if someone can help me by indicating how the launch
        file is built in order to execute the Dijkstra and A starr algorithms
        available in ROS, since the page only brings the parameters and I am new
        to this.
    </p>
</div>
<h2>1 minute survey</h2>
<div class="question">
    <p>Do you have enough knowledge to answer this question?</p>
    <input name="q1" type="radio"/> <label>Yes</label> <br>
    <input name="q1" type="radio"/> <label>No</label> <br>
</div>
<div class="question">
    <p>Would you be willing to answer the question?</p>
    <input name="q2" type="radio"/> <label>Yes</label> <br>
    <input name="q2" type="radio"/> <label>No</label> <br>
</div>
<div class="question">
    <p>
        This survey was automatically sent according to your profile,
        collaborating with ROS in Github and ROS Answers. From 1 to 4, where 1
        is very bad and 4 is excellent, how accurate do you think our prediction
        was?
    </p>
    <input name="q3" type="radio"/> <label>1</label> <br>
    <input name="q3" type="radio"/> <label>2</label> <br>
    <input name="q3" type="radio"/> <label>3</label> <br>
    <input name="q3" type="radio"/> <label>4</label> <br>
</div>
<div class="question">
    <p>Please give us feedback</p>
    <textarea rows="3"></textarea>
</div>
<div class="submit-btn">
    <button type="button" onclick="x()">Submit</button>
</div>
<div class="footer">
    <div class="center">
        <a href="https://validator.w3.org/nu/?showsource=yes&showoutline=yes&checkerrorpages=yes&useragent=Validator.nu%2FLV+http%3A%2F%2Fvalidator.w3.org%2Fservices&acceptlanguage=en&doc=http%3A%2F%2Fsurvey.elbraulio.com%2F">
            <img class="img-center" src="assets/img/w3-logo-2.png"
                 alt="w3 validation"/>
        </a>
        <a href="https://github.com/elbraulio/simple_research_survey">
            <img class="img-center" src="assets/img/github-sign.png"
                 alt="github repo"/>
        </a>
    </div>
    <div class="credits"> Icons made by <a
            href="https://www.flaticon.com/authors/simpleicon"
            title="SimpleIcon">SimpleIcon</a> from <a
            href="https://www.flaticon.com/"
            title="Flaticon">www.flaticon.com</a> is
        licensed by <a href="http://creativecommons.org/licenses/by/3.0/"
                       title="Creative Commons BY 3.0" target="_blank">CC 3.0
            BY</a>
    </div>
</div>
<script>
    function x() {
        window.open("http://elbraulio.com", "_self");
    }
</script>
</body>
</html>
