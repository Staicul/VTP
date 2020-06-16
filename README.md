# 1. Prerequisites

Required components for project execution: <br>
	- Java JDK8<br>
	- Maven<br>
	- Chrome/Firefox browsers - for Selenium UI tests <br>

## 2. Command Line Run

Note: all run commands must be executed from the project folder root ( folder that contains the pom.xml file) <br>

Run command for Serenity + Selenium Tests <br>

### Local run

```
mvn -DconfigFile=local -DtestSuite=ViatorSuite verify
```
Notes:  <br>

testSuite - property can be either test or testsuite class <br>
configFile - will point the solution on which environment it should run, so all configuration accounts and urls will be stored in the config.properties file  under 'src/test/resources/config' path <br>


Run against selenium grid ( Selenium Grid or Zalenium setup needed)

```
mvn -DconfigFile=local -DtestSuite=ViatorSuite -Dwebdriver.driver=${browser} verify -Dwebdriver.remote.url=${selenium_grid_URL}
```
Notes:  <br>

For runs with Zalenium:  <br>
```
browser = chrome/firefox
selenium_grid_url = http://localhost:4444/wd/hub
```
Command I run with:<br>
```
mvn -DconfigFile=local -DtestSuite=ViatorSuite -Dwebdriver.driver=chrome verify -Dwebdriver.remote.url=http://localhost:4444/wd/hub
```


Run command for Serenity report generation <br>

```
mvn serenity:aggregate
```

Notes: Will generate a test report that can be accessed under the path ```/target/site/serenity/index.html``` <br>

## 3. Selenium UI Tests - JUnit (short description)

The framework contains two types of jUnit tests: straight up jUnit tests and data driven tests. The Data Driven tests use .csv files (located in ```src/test/resources/testdata``` ).<br>
Tests rely on the ```BASE_URL``` variable to be set in the config file in order to point to the correct application.<br>
Test object mappings from web pages are captured in ```Page``` classes that define the object locator and the action related to it (click, input, select and so on). The ```Step``` classes will define groups of actions (e.g.: logInWithCredentials - input user, input password, click login). Step method annotations have the role to create report entries for each method. The tests will never call page classes directly, thus it will always rely on steps to perform actions. 


## 3.1 Zalenium Grid 


It was required for the tests to be run on Selenium Grid.
I used the Zalenium docker images to provision the infrastructure.

Documentation: https://opensource.zalando.com/zalenium/

Steps to configure:

```
    # Pull docker-selenium
    docker pull elgalu/selenium
    
    # Pull Zalenium
    docker pull dosel/zalenium
    
    # Run it!

    Linux:

    docker run --rm -ti --name zalenium -p 4444:4444 \
          -v /var/run/docker.sock:/var/run/docker.sock \
          -v /tmp/videos:/home/seluser/videos \
          --privileged dosel/zalenium start

    OSX:

    docker run --rm -ti --name zalenium -p 4444:4444 \
          -e DOCKER=17.06.2-ce \
          -v /var/run/docker.sock:/var/run/docker.sock \
          -v /tmp/videos:/home/seluser/videos \
          --privileged dosel/zalenium start

    Windows:

    docker run --rm -ti --name zalenium -p 4444:4444 ^
          -v /var/run/docker.sock:/var/run/docker.sock ^
          -v /c/Users/your_user_name/temp/videos:/home/seluser/videos ^
          --privileged dosel/zalenium start
      
    # Point your tests to http://localhost:4444/wd/hub and run them

    # Stop
    docker stop zalenium
```

Zalenium Management Link: 
http://localhost:4444/grid/console

Zalenium Machine VNC (where tests can be seen running in the containers):
http://localhost:4444/grid/admin/live