# survey for [ros_gh](https://github.com/elbraulio/ros_gh)

[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/elbraulio/simple_research_survey/blob/master/LICENSE)  [![Build Status](https://travis-ci.org/elbraulio/simple_research_survey.svg?branch=master) ](https://travis-ci.org/elbraulio/simple_research_survey) [![codebeat badge](https://codebeat.co/badges/245f4884-26f1-4c8d-b86b-8f7c185ab766)](https://codebeat.co/projects/github-com-elbraulio-simple_research_survey-master)

# Install

## config sqlite db

The repo includes a [model](https://github.com/elbraulio/simple_research_survey/blob/master/src/main/resources/model) to create the db where the results will be saved and the survey request must be added. You can create a empty __survey.db__.

```bash
$ sqlite3 survey.db
sqlite> .read {PATH_MODEL}/model
```

## config properties

in the `resources` folder you can customize this configurations:

```properties
# path to the sqlite db that has the information for survey
sqlite.path=

# path to sqlite v1.2.db that has the rosgh information
rosgh.db.path=

# mail configuration
user=rosgh.survey.notreply@gmail.com
pass=...
smtp.ssl.trust=*
smtp.host=smtp.gmail.com
smtp.port=587
smtp.auth=true
smtp.starttls.enable=true
```

Also __you must define__ the log output in the `log4j.properties` file in resources.

## deploy a war 

```bash
$ mvn package
```

then deploy the `war` in your server. For instance if you are using [Tomcat](https://tomcat.apache.org) you can do it by leaving the war in your tomcat's `webapp` folder.

Alternatively you can use [ghcd](https://github.com/elbraulio/ghcd). This is a script to easily deploy a war on Tomcat or Wildly servers.

# How it works

The only thing you have to do to make a survey is to follow 3 steps.

## create a survey

1. Add all the aspirants to every ROS question  you have got from your Algorithm to __Aspirant__ table. Including `gh_user_id` and `ros_question_id` (from the db included in resources).

2. Add the __gh_user_id__ (from the db included in resources) to the __survey__ table. When the mail is sent this record is removed. 

3. Go to `{host}/sendmail` and all mails will be sent.

## check results

You can check results in the __answers__ table, that's all. 

# Disclaimer

The source does not have a mail for each user, so be aware of using users that have mails.

