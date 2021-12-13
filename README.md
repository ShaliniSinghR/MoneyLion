# MoneyLion Web Automation
This project details the automation of MoneyLion Website and API components. The architecture consists of feature files, step def classes to implement them
other utilities class. The test can be run via junit runner or via CLI or via feature file directly. 
The segregation of UI tests are done in such a way, that all the object properties are kept in json file along with the type of locator. These objects
can be accessed via a unique name provided via feature file. 

For API, architecture is different, it is divided like this:  
* StoredReports: This folder contains API O/P which is divided in two sets: set1 and set2 and these sets are compared to idenitify the diff.
* requestPaylod.properties: The json body for diff type of HTTP request like POST, PUT are kept here.
* APIBase class reads the type of request and executes them and store the result.

## Tools and Libraries
This project using 2 main tools, Selenium and Cucumber.
The complete list of tools, you can see in the `pom.xml` file.

## Requirements
* Java Development Kit
* Maven
* WebDriver, using ChromeDriver

## Running Tests
* Open the project using any Java IDE
* Run the tests with the script below
```shell
$ mvn clean install
```
* If you want to run the specific test, use the cucumber tags like this
```shell
$ mvn clean install -Dcucumber.filter.tags="@REPLACE_WITH_ANY_TAGS_THAT_YOU_WANT"
```

## Test Results
* Test report automatically generated on `target` folder after finished the test execution
* See test report from `target/cucumber-reports/advanced-reports/cucumber-html-reports/overview-features.html`
* You can also share your Cucumber Report with another person at https://reports.cucumber.io, just go to `src/test/resources/cucumber.properties` then change the value to be `true`
```properties
cucumber.publish.enabled=true
```
