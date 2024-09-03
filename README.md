# LevelingUpPractice
-  exploring latest selenium features and apply different approaches of design patterns

## Env
- **Java Version**: 21
- **Build Tool**: Maven

## Tasks Applied

### #1 Basic
- **Open**: Google Chrome
- **Navigate to**: [https://duckduckgo.com/]
- **Assert**: The page title is `[Google]`
- **Close**: Google Chrome

### #2 Basic
- **Open**: Google Chrome
- **Navigate to**: [https://duckduckgo.com/]
- **Assert**: The DuckDuckGo logo is displayed
- **Close**: Google Chrome

### #3 Basic
- **Open**: Google Chrome
- **Navigate to**: [https://duckduckgo.com/]
- **Search for**: `[Selenium WebDriver]`
- **Assert**: The link of the first result is `[https://www.selenium.dev/documentation/webdriver/]`
- **Close**: Google Chrome

### #4 Moderate
- **Open**: Mozilla Firefox
- **Navigate to**: [https://duckduckgo.com/]
- **Search for**: `[TestNG]`
- **Assert**: The text of the fourth result is `[TestNG Tutorial]`
- **Close**: Mozilla Firefox

### #5 Moderate*
- **Open**: Google Chrome
- **Navigate to**: [https://duckduckgo.com/]
- **Search for**: `[Cucumber IO]`
- **Navigate to the**: Second results page
- **Assert**: The link of the second result contains `[https://www.linkedin.com]`
- **Close**: Google Chrome

### #6 Basic
- **Open**: Google Chrome
- **Navigate to**: [http://the-internet.herokuapp.com/checkboxes]
- **Check**: Checkbox 1
- **Assert**: Both Checkboxes are checked
- **Close**: Google Chrome

### #7 Moderate
- **Open**: Google Chrome
- **Navigate to**: [https://www.w3schools.com/html/html_tables.asp]
- **Assert**: The Country for the Company `[Ernst Handel]` is `[Austria]`
- **Close**: Google Chrome

### #8 Moderate
- **Open**: Google Chrome
- **Navigate to**: [http://the-internet.herokuapp.com/upload]
- **Upload**: A small image file
- **Assert**: The file was uploaded successfully
- **Close**: Google Chrome

### #9 Moderate
- **Open**: Google Chrome
- **Navigate to**: [https://jqueryui.com/resources/demos/droppable/default.html]
- **Drag**: `[Drag me to my target]`
- **Drop it on**: `[Drop here]`
- **Assert**: The text has been changed to `[Dropped!]`
- **Close**: Google Chrome

### #10 Moderate
- **Refactor**: All tasks to apply the Page Object Model design pattern

### #11 Advanced
- **Repeat**: Task #3 but read the text values from an external file (e.g., txt, xml, json, or excel)

### #12 Advanced
- **Refactor**: All tasks to read the “TargetBrowser” from an external `.properties` file, with support for Chrome and Firefox, running on Windows

### #13 Basic
- **Recreate**: All tasks using SHAFT_Engine

### #14 Moderate
- **Create**: A `TestSuite.xml` file that will execute all the created test classes
- **Execute**: The TestSuite using command-line (Maven Surefire plugin)

### #15 Advanced
- **Create**: A local Selenium Grid on your machine
- **Use**: RemoteWebDriver to execute your tests through the Selenium grid

### #16 Advanced
- **Create**: A local Jenkins server on your machine
- **Create**: A Jenkins job to trigger the execution of your tests via command-line
