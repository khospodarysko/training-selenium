h4. Java

h5. Theory

* [Google Style Guide|https://google.github.io/styleguide/javaguide.html]
* The Java™ Tutorials
*# [Getting Started|https://docs.oracle.com/javase/tutorial/getStarted/index.html]
*# [Learning the Java Language|https://docs.oracle.com/javase/tutorial/java/index.html]
*# [Essential Java Classes|https://docs.oracle.com/javase/tutorial/essential/index.html]
*# [Collections|https://docs.oracle.com/javase/tutorial/collections/index.html]
*# [Date-Time APIs|https://docs.oracle.com/javase/tutorial/datetime/index.html]

h5. Theory should be covered in tasks

* immutable, final, static, constant, pass value by reference/value
* inheritance, polymorphism
* date/time
* exceptions
** checked/unchecked
** approaches used in tests
* stream, lambdas
** separate class (ExpectedConditions, like Command pattern)
** anonymous class
** lambda, functions, consumer, supplier
*** webelement to text transform
**** classic
**** stream
**** separate function
* generics overview
* basic reflection usage (TestNG listeners)

h5. Practice

# *Multiplication Matrix*
#* print multiplication matrix, separate header and leftmost column with some symbol, like \|
#** just hardcode matrix size in code
#** for-loop
#** streams (replace for-loop, int range etc.)
#** String.format()
#** mind how spaces are placed between numbers, try different matrix sizes, 10*10, 10*20, 100*100
Example output
{noformat}
1 | 2  3  4
--|-------
2 | 4  6  8
3 | 6  9 12
{noformat}
# *Happy Tickets*
#* happy tickets (8 digits) - ticket is happy if sum of left 4 digits of the number equal to sum of right 4 digits of that same number
#** projects
#*** classic with jars
#*** Maven with dependencies
#** use Java native methods to get random number
#** use Apache Commons Lang library
#** count % of happy tickets to all generated
#** timings
#*** millis vs nano (1 and 2 seconds are the same if System.currentTimeMillis() is used, try measure with nanoTime)
#*** given 10 seconds, how many of happy tickets could be generated
#**** generate list at first and then find happy tickets in it
#***** concerns
#***** memory to hold whole list (count how many MBs it takes)
#***** use smaller Xmx and run via cmd
#***** generate one random number and check for happiness at runtime
#**** given amount of ticket (1_000_000) to be generated, how much time will it take
#***** parameterize numbers to be generated, run via cmd, use system property
#***** find (has 11 in the middle), 121156
#***** predicates (combined, 3rd digit is 1 and 4th digit is 1)
#***** sort (by two middle digits, comparator)
#***** generate via stream, limit 100_000
#****** classic for loop
#****** streams
#* OOP
#** make a wrapper to generated number, interface MyRandomNumber
#** create isHappy() method to check if number is happy
#*** classic
#*** stream
#** create getType() method to return ODD/EVEN depending on a number
#** create factory class that returns OddRandomNumber/EvenRandomNumber wrapper implementation depending on a type (implement MyRandomNumber)
#** put all numbers in List<MyRandomNumber>
#** group list by type into map
#*** classic
#*** stream
#** generator
#*** use system property to pass generator (java, apache)
#*** create generator factory
#*** create two implementation Java, Apache that use appropriate algorithm to generate number
#*** use generator in place where random number is generated (think of a way to pass in generator where needed)

h4. Selenium

h5. Theory

* [Official Selenium Docs|https://www.seleniumhq.org/docs/]
* [Selenium GitHub Wiki|https://github.com/SeleniumHQ/selenium/wiki]
* [Guru99|https://www.guru99.com/selenium-tutorial.html]
* [XPath Locators|https://www.w3schools.com/xml/xpath_intro.asp]
* [CSS Locators|https://www.w3schools.com/cssref/css_selectors.asp]
* Ajax
* Chrome Dev Tools

h5. Theory should be covered in tasks

* PageObject/PageFactory
** approaches
** page's method returns void/this/page
** FindBy, FindBys vs. getLocator()/getElement()
*** dynamic locators
* StaleElementException
* waits
** implicit
** explicit
** sleep
* Actions
* JS Executor
* WebDriverEventListener
** highlight element before searching it
** logging
* RemoteWebDriver

h5. Practice

# *[Mini HTMLs|https://khospodarysko.github.io/training-selenium/index.html]*
#* *button*
#** {color:blue}wait for button, click 5 times on it and check that button's text is "Clicked 5"{color}
#** implicit wait
#** explicit wait
#*** built-in expected condition
#*** lambdas, predicate
#*** custom expected condition
#**** utility class with static method per condition
#**** separate class per condition
#** store html file in resources, don't use absolute path
#* *slider*
#** {color:blue}move slider to the left until value is 20{color}
#** actions
#** while-loop
#** infinite for-loop
#* *spinners*
#** {color:blue}print amount of different spinners, wait until all spinners are disappeared{color}
#** print time it took for small and large spinners to disappear
#** make screenshot when only large spinner is displayed
#*** save file in format "screenshot 2018-10-10 10:10:10.100" to "screenshots" folder in root project
#* *dynamic list*
#** {color:blue}check that all elements in a list contains "test" text{color}
#* *hover menu*
#** {color:blue}open 3rd level menu, click final level and check that date is the same as system's{color}
#** parse time and print that time with different format + date (year, month, day)
#* *multilevel menu*
#** {color:blue}navigate and click on menu/languages/time and check that date is the same as system's{color}
#* *time*
#** {color:blue}print five times time which ends on 10/20 etc. seconds{color}
#** if time is 10:00:01 - don't print
#** if time is 10:00:10 - print it
#* *drag below*
#** {color:blue}move button 50 just below green section, click on it, check "Button 50" menu appeared{color}
#** javascript executor
#** simulate slow scroll
#* *jenkins tree*
#** {color:blue}open all tests, print all that failed or skipped{color}
Example output
{noformat}
com.clearslide.manage.pro.FeatureRestrictionTest
    failed  - test method name
    skipped - test method name
{noformat}
#** implement opening with Selenium, Actions (double click), JavascriptExecutor
#** implement method that gets all classes' names (without package) that are closed
#** find class with longest execution time
#* *group children*
#** {color:blue}group menu texts into map of lists{color}
#* *datepicker*
#** {color:blue}TBD{color}
#* *tinymce*
#** {color:blue}TBD{color}

h4. TestNG

h5. Theory

* [Official Docs|http://testng.org/doc/documentation-main.html]
* [AssertJ|http://joel-costigliola.github.io/assertj]

h5. Theory should be covered in tasks

* test annotations
* groups
** run tests in order how groups came from cmd, e.g unit,api,ui - run all unit, all api, all ui tests in this sequence
* data provider
** read JSON
** return array
** return iterator
* parallel running
* listeners
* interceptors
** run all disabled tests
* screenshots on failure
* reporting (ReportNG as example)
* assertj
** custom assert methods for some page object

h4. Maven

h5. Theory

* Official documentation

h5. Theory should be covered in tasks

* surefire plugin
* passing parameters from cmd as Java system property

h4. Logging / Reporting

h5. Theory

* Logback Official Documentation

h5. Theory should be covered in tasks

* log all to single file
* log all action per class to separate file
** sifting appender
** MDC
* save screenshot on test failure
* connect ReportNG

h4. Jenkins

h5. Theory

* Official documentation

h5. Theory should be covered in tasks

* user war file locally
* configure build to run TestNG tests via Maven cmd with custom parameter passed from build via input field

h4. API

h5. Theory

* Official documentation of framework to be used
* RestAssured
* Apache Http Client

h5. Theory should be covered in tasks

* login
* get request
* post request
* multipart