# VisualfabriqAssignment #
## RestAssured with junit and bdd-cucumber integartion ##
### validate API endpoint that calculates the amount of time left before next birthday of user ###

**Prerqusite**
1. Java should be installed
2. Any one of the IDE Eclipse or Intellij should be installed

**Installation**
* Step1: Clone the git repository 'git clone https://github.com/prithabazani/VisualfabriqAssignment.git' 
* Step2 Import the project in any IDE 
* Step3: Run 'mvn clean' 
* Step4: Run 'mvn install' 

**Test Suit**
Refer feature file present under /VisualfabriqTest_apiAutomation/src/test/resources/VisualFabriqApiAssignment.feature

**Execute Test Suit**
Execute TestRunner file as JUnit Test, location - /VisualfabriqTest_apiAutomation/src/test/java/runners/TestRunner.java
* To execute all tc's present in test suit, mention 'tag=@runall'
* To execute each tc individually, mention 'tag=@test1', based on the tc number

**Execution Report**
Check the cucumber repot present in VisualfabriqTest_apiAutomation/target/cucumber-reports.html
