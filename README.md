# Handling Pop-ups in Appium Web Tests (on Cloud Grid)

<img width="634" alt="appium-banner" src="https://github.com/user-attachments/assets/8bfc8aa4-66d2-4d00-8fea-a3ff042d04d2">

<div><a href="https://i.ytimg.com/vi/rQyi9v6d5i8/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDG5wRcydWIDO3CBbYW-Fenao3N-w">Image Credit</a></div>
<br/>

## Pre-requisites for test execution

This repo is a modified version of the [official Appium TestNG repo](https://github.com/LambdaTest/LT-appium-java-testng/). We have modified the repo for handling alerts (particulalry 'location preference alerts') that are triggered when web tests are triggered with Appium on the Safari browser.

<img width="1439" alt="location_preferrence" src="https://github.com/user-attachments/assets/0ede34f3-7f35-4669-b1f2-262705008960">

For demonstration, we are using Appium 2.x, Selenium 4.x, and TestNG v7.7.0. Follow the steps along for package installation & test execution:

**Step 1**

Create a virtual environment by triggering the *virtualenv venv* command on the terminal

```bash
virtualenv venv
```
<img width="1418" alt="VirtualEnvironment" src="https://github.com/hjsblogger/web-scraping-with-python/assets/1688653/89beb6af-549f-42ac-a063-e5f715018ef8">

**Step 2**

Navigate the newly created virtual environment by triggering the *source venv/bin/activate* command on the terminal

```bash
source venv/bin/activate
```

**Step 3**

Procure the LambdaTest User Name and Access Key by navigating to [LambdaTest Account Page](https://accounts.lambdatest.com/security). You might need to create an an account on LambdaTest since it is used for running tests on the cloud Grid.

<img width="1288" alt="LambdaTestAccount" src="https://github.com/hjsblogger/web-scraping-with-python/assets/1688653/9b40c9cb-93a1-4239-9fe5-99f33766a23a">

Set LambdaTest **Username** and **Access Key** in environment variables.

* For **Linux/macOS**:
  
```bash
export LT_USERNAME="LT_USERNAME" 
export LT_ACCESS_KEY="LT_ACCESS_KEY"
```
* For **Windows**:
```bash
set LT_USERNAME="LT_USERNAME" 
set LT_ACCESS_KEY="LT_ACCESS_KEY"
```

**Step 4**

Run the command ```mvn install``` (or ```mvn clean install```) for installing the project dependencies (i.e. Appium, TestNG, SureFire plugin, etc.) 

## Steps for test execution

With this, all the dependencies and environment variables are set. Let's run the tests in serial and parallel modes.

***Serial test execution***

Trigger the command ```mvn test -P ios-single``` for running the Appium Java tests on iPhone 13 (v15).

Shown below is the terminal screenshot of the test execution:

<img width="1282" alt="ios-single-console" src="https://github.com/user-attachments/assets/976d26d8-ece6-44fb-bbef-be1d6ad4e64d">
<br></br>

As seen below, the test execution was successful and the status is "Completed". You can find the status of test execution in the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/build).

<img width="1440" alt="ios-single" src="https://github.com/user-attachments/assets/ee72b2cb-1e91-4d0a-8cd0-16414085c175">

***Parallel test execution***

Trigger the command ```mvn test -P ios-parallel``` for running the Appium Java tests on Safari browser installed on two different iPhone devices (located on LambdaTest cloud grid).

Shown below is the terminal screenshot of the test execution:

<img width="1402" alt="ios-parallel-console" src="https://github.com/user-attachments/assets/423c3fd0-12fc-4788-9187-0cf99e82721e">
<br></br>

As seen below, the test execution was successful and the status is "Completed". You can find the status of test execution in the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/build).

<img width="1431" alt="ios-parallel" src="https://github.com/user-attachments/assets/2c307912-bb52-42d4-8869-8959d3035050">

## Have feedback or need assistance?
Feel free to fork the repo and contribute to make it better! Email to [himanshu[dot]sheth[at]gmail[dot]com](mailto:himanshu.sheth@gmail.com) for any queries or ping me on the following social media sites:

<b>LinkedIn</b>: [@hjsblogger](https://linkedin.com/in/hjsblogger)<br/>
<b>Twitter</b>: [@hjsblogger](https://www.twitter.com/hjsblogger)