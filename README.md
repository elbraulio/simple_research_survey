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