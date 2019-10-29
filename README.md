# Sodoku

This repository shows an example of using JUnit and Mockito on Eclipse Maven project

## Prerequisites

What things you need to install the software and how to install them

```
1. Eclipse
2. JRE 1.8
3. Mockito
```

## Set up
### Step 1: create a Maven project
go to File -> New -> Project -> Maven -> Maven project -> continue -> (Group ID: idv.yourname.projectname; Artifact ID: projectname) -> finish


### Step 2: Update JRE
default setting of compiler is JRE 1.5, to update compiler to JRE 1.8
go to pom.xml file -> add 
``` 
<properties>
   <maven.compiler.source>1.8</maven.compiler.source>
   <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```
Refer from [stackoverflow] (https://stackoverflow.com/questions/28509928/java-version-automatically-change-to-java-1-5-after-maven-update)

### Step 3: Update JUnit
default setting of testing framework is JUnit 1.4, way to update to JUnit 1.5
go to pom.xml file -> modify dependence as below
```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
``` 
Refer from [maven apache] (https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html)

### Step 4: refresh project setting
right click on the project -> Maven -> update project...
You should see the correct JRE, JUnit version on the project

### Step 5: add unit test class
If we wanna test Problem193.java, right click on it -> New -> JUnit Test Case -> specify the name -> Finish
A new Project193Test.java is created under src/test/java
you're all set, start writing testing function

### Step 6: add mock object if required
if the class to be tested has certain dependence with other class, to make things easier we could create a mock object
just focus on the class we'd like to test.
"SudokuServiceTest.java" shows an example of using Mockito to create a mocking object. SudokuService is the class to be tested. BackgroundCheck is its
dependence. It may take a while to really get back from the really database, or query itself is expensive. To avoid querying the database each time we doing the
unit test, a mocking object comes to help. 

Details on Mockito, please check [Mockito](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html)

## Running the tests

right click on "Project193Test.java" -> Run as -> JUnit test

## Reference & Note
```
@Mock is an annotation in Mockito to idicate the object to be mocked (usually is the dependeny class of to-be-tested class)
@InjectMocks is an annotation in Mockito to idicate the object to be injected with mock object (usually is the class to-be-tested)
MockitoAnnotations.initMocks(this) is to initialized all the mock objects
@Before, @After are two annotations in JUnit, @Before will be executed each time before @Test function and @After is executed everytime after @Test function
```

[Mockito tutorial] (https://dzone.com/articles/a-guide-to-mocking-with-mockito)



