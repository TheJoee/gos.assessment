## External dependencies

For this project to run, you would need to install below 3 dependencies (minimum) on your machine:

- **[Java 11](https://www.oracle.com/eg/java/technologies/downloads/#java11 or https://openjdk.java.net/projects/jdk/11/)** (as the core programming language)
- **[Maven 3.8.5](https://maven.apache.org/download.cgi)** (for dependency management)
- **[Google Chrome latest version](https://www.google.com/chrome/?brand=CHBD&gclid=Cj0KCQjwr-SSBhC9ARIsANhzu15P0PA-n9Zp4NpxKaOHVGtBD1TZQH0HlQQE6hUfsOFAU1nf-Rzdlf4aAoTJEALw_wcB&gclsrc=aw.ds)** (browser to run your tests)

> If your JAVA_HOME is set to anything other than JDK 11, you would need to update the path. Else your project
> will not run. Also, do remember to set the correct JDK settings in your IDE.


# Getting started

> For easiest way to getting started, clone and import on IntelliJ.

## Configuration file (env.conf)

> Note: This file needs to be created based on env.conf.sample

> Modify the included parameters based on the explanation below, DO NOT INCLUDE ' nor " for the values :  

RUN_METHOD : Values can be 'local', 'docker', 'grid'

BROWSER : Values can be 'chrome', 'firefox' , 'edge'

BASE_URL : URL to run the web tests from, for the assessment its defaulted to https://github.com/

GRID_URL : URL to run on a grid server, by default http://localhost:4444/wd/hub

HEADLESS : 'true' or 'false', run displaying the browser or headless if the browser supports it.

ACCEPT_INSECURE_CERTIFICATES : 'true' or 'false', selenium config by default true

SELENIUM_LOG_LEVEL : 'severe' or 'info', selenium config by default severe

DOWNLOADS_DIR : selenium config by default 'downloads'

API_BASE_URL : URL to run the API tests from, for the assignment https://api.github.com/

API_VERSION : Parameter required by github API, by default 2022-11-28

ORG_NAME : Organization name to use on the tests, no spaces, as defined on URL. (E.g.: Joe-Astudillo-Org)

TOKEN : Personal token needed to access API. Create this one and add before running API tests.
Format is similar to github_pat_XXXXXXXXXXXXXXXXXXXXXXXXXX

USERNAME : Github username.

PASSWORD = Github password

OTP : Set to true if the used GitHub account uses Code Genarator OTP. By default false. 

OTP_KEY : Private key for the code generator. Format: ABCDEFGHIJKLMNOP

> All the configuration files have default values to run the proposed site on local env. 
> Please change needed variables to run either on docker or cloud.  

### Local Execution
- Just execute via Maven test or right-click 'run' any of the two features: 'e2eAPI.feature' 
- or 'githubWeb.feature'

### Grid Execution
- Set the correct values on env.conf and execute as described above. 

### Dockerfile Execution
> You must have the last version of Docker installed and docker command line working before continuing.

- Open terminal on the root of the project
- On the OS console execute: 'docker build -t gsseleniumcontainer .'
- On the OS console execute: 'docker run -it gsseleniumcontainer'
- On the container command line execute: 
- - 'mvn test' -- for all tests 
- - 'mvn test -Dcucumber.options="src/test/resources/apiTests/e2eAPI.feature"' -- For API tests
- - 'mvn test -Dcucumber.options="src/test/resources/webTests/githubWeb.feature"' -- For WEB tests

