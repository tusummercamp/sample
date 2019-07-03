# TU Summer Camp Sample Project

The following project should serve as a sample blueprint for creating AWS Lambda-compatible Java-based projects.

# Development Environment Setup

Follow the instructions here: https://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-and-eclipse.html

Use the following artifact information:
* Group Id: com.visteoncloud
* Artifact Id: tusc-sample
* Version: 0.0.1-SNAPSHOT
* Packaging: jar
* Name: tusc-sample

# Maven Dependencies
You will need the following Maven dependencies

### aws-lambda-java-core
* Group Id: com.amazonaws
* Artifact Id: aws-lambda-java-core
* Version: 1.1.0

### maven-shade-plugin
* Group Id: org.apache.maven.plugins
* Artifact Id: maven-shade-plugin
* Version: 2.3

# Building Deployment package

You will need to create a `shaded` package that is ready for deployment.
Create a new Maven build with `package shade:shade` goal.
