# survey for [ros_gh](https://github.com/elbraulio/ros_gh)

[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/elbraulio/simple_research_survey/blob/master/LICENSE)  [![Build Status](https://travis-ci.org/elbraulio/simple_research_survey.svg?branch=master) ](https://travis-ci.org/elbraulio/simple_research_survey) [![codecov](https://codecov.io/gh/elbraulio/simple_research_survey/branch/master/graph/badge.svg)](https://codecov.io/gh/elbraulio/simple_research_survey) [![codebeat badge](https://codebeat.co/badges/245f4884-26f1-4c8d-b86b-8f7c185ab766)](https://codebeat.co/projects/github-com-elbraulio-simple_research_survey-master)



# Install

## config sqlite db

The repo includes a [model]() to create the db where the results will be saved and the survey request must be added. You can create a empty __survey.db__.

```bash
$ sqlite3 survey.db
sqlite> .read {PATH_MODEL}/model
```

## deploy a war 

```bash
$ mvn package
```

then deploy the `war` in your server. For instance if you are using [Tomcat](https://tomcat.apache.org) you can do it by leaving the war in your tomcat's `webapp` folder.

# How it works

There is a __job__ that run every 5 minutes. It checks if exist a survey request to send a mail survey. The only thing you have to do to make a survey is to follow this guide.

## create a survey

1. Add all the aspirants to every ROS question  you have got from your Algorithm to __Aspirant__ table. Including `ros_user_id` and `ros_question_id` (from the db included in resources).

2. Add the __ros_user_id__ (from the db included in resources) to the __survey__ table. 

3. Wait to the __job__ to take your survey (job runs every 5 minutes remenber).

## check results

You can check results in the __answers__ table, that's all. 

# Disclaimer

The source does not have a mail for each user, so be aware of using users that have mails.

