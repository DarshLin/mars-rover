# Mars-Rover: brought to you by Darsh Lin

###Introduction:


###Brief description:
Mars rover project that allows user to set a rectangular plateau and then deploy rovers with coordinates on them. 
The plateau and coordinates are to be entered in html input boxes and are done in sequential order as follows:
1. Plateau size X and Y
2. Rover starting X and Y coordinates, starting cardinal direction, and instructions. Rovers can also be added and removed with no limit on the number of rovers that can be added.
3. Clicking the "Planet (Plan it) again" button brings user back to the Plateau page.

####Edge Cases Implemented:
* Plateau XY sizes cannot be negative - HTML page will not allow user to proceed with negative X or Y. Java code sets negative to 0 for redundancy.
* Rover coordinates cannot be negative - This is done the same way as with Plateau.
* Rover coordinates cannot exceed Plateau size - Java code sets entered coordinates to plateau size.
* Rover cannot move outside bounds of 0 -> plateau size X or Y - Java code will skip movement instruction.
* Rover instructions can be blank - Just means it will deploy and not move.
* Rover instructions can be any string - Will only take L,M,R,l,m,r as working instructions.
* Plateau and Rover X and Y cannot pass integer limit - Will default to integer max. Max on HTML and check in Java for movement.
* Limit on number of Rovers deployed - Only 1000 units can be deployed limited by HTML. This is because every machine is different, and the Java Map limit is about 2^30 seemed like a lot.
* Cardinal directions should only be N,E,S,W. This is done through HTML limiting it to 4 inputs through a drop box.

###Module Locations:
* Main Java/Spring-Boot code: `src/main/java/com/darsh/marsrover`
* HTML code: `src/main/resources/templates`
* Tests: `src/test/java/com/darsh/marsrover/service`

###Running the Project:
There are two ways to run the main project:
1. The easiest way is to access via Heroku hosted container. Note: This may be slow the first time the app gets accessed just due to the way the free tier of Heroku sleeps apps that haven't been accessed in a few hours.
    1. Link to App: http://fathomless-garden-70014.herokuapp.com/
2. If pulling the project from this GitHub page, certain software needs to be downloaded and set up
    1. Install Java 8. 
    2. Install Maven 3.6.3.
    3. Make sure IDE or machine can run Spring Boot 3.5.4. Can use Maven to install spring on machine.
    4. Clone project using `git clone https://github.com/DarshLin/mars-rover.git`
    5. Run using command `mvn spring-boot:run`
    6. Open browser and go to address `http://localhost:8080/`
   
###Running Unit Tests:
1. Follow steps i -> iv on option 2 of "Running the Project above" if haven't done already
   1.Run tests with command `mvn clean test`
   2.If using an IDE like IntelliJ or Eclipse, go to test directory and hit play button for each service test
   
##Final Thoughts

###Implementation Ideas:
* Making a game - If given more time, the first thing I thought of doing was making an actual game using Unity that can be hosted somewhere. The game would have the ability to both input instructions in beforehand and in real time. However, I found that writing the physics and actual movement of multiple rovers in a grid takes quite a bit of time and calculation.
* Using a framework to implement front end -  This could have probably saved me some time arguably. I decided it was best to just use plain HTML and Javascript which ended up making things tricky. it also meant Javascript had to be written in the HTML with a `<script>` tag.
* Making a game embedded to framework front end - If given all the time in the world, I would have used a framework like Angular or React to make a static web page. Then I would have embedded the unity game and show previous entries on the page.

###Edge Cases Considered:
* Deploying on top of another rover - I decided that it would be very tricky and inefficient to figure out the locations of other rovers before deploying a new one as the project description does not talk about how to best do this so it kind of would just come down to either not deploying the next rover or putting it in a random spot that's "safe" which could be a problem given that infinite rovers can be deployed.
* Crashing into another rover - Another case the project description didn't address is what happens when one rover ends up in the same spot as a previous rover. It would be as simple as just not executing the move instruction against a HashSet to see where each other rover is. However, following the logic of not deploying on top of another rover, I decided that it would make more sense and think that infinite rovers can occupy the same spot.

###Testing Thoughts:
* Selenium Testing - I did consider Selenium testing for the HTML. However, it takes quite a bit of time to set up both for myself and anyone else who wants to pull and test it.
* Unit Testing - There are unit tests for all the services but there isn't anything for the controller. This is because the controller's only purpose is to push data to be viewed on the front so as long as the front moves as it should, it's all fine.
I also didn't test a lot of the methods on the different services because they all served a single purpose and making them public would be bad practice. So I just tested the main public method as much as possible.
  
##Project Made using
* Java 8 - Main coding language
* Apache Maven 3.6.3 - Running console commands and packaging
* Spring-Boot 3.5.4 - Main framework
* Spring Boot Web - Deployment
* Spring Boot Thymeleaf - Link HTML to Spring
* Lombok - Readability annotations (E.g., @Data for getters and setters)
* JUnit 5 - Testing

## Enjoy moving all rOver Mars!
