# gptest

This is a framework for testing some of WH dev apis.

To execute test run 
```mvn clean test```

Output report can be found in:
```./target/surefire-reports/index.html```

Background:
I did not manage to finish requested main flow functionality test, because of malfunctioning Competitions endpoints.
Also, there are number of stubbed, and hundreds of not existing tests - the scope of API is huge and I didn't want to test it all.
However, you should get an idea.

There are couple of things to mention:
 * I wanted to push down different variations and edge cases from functional test to integration test.
 * I was limited by given user and state of dev sandbox - testing starts from test env. preparation. 
 * I didn't want to write cases, which I would never write - I had a temptation to write some conditions if events are empty or user balance is too small, but this shoudl be tested on integration level by using mocked environment and not some random data which I cannot trust 

If there is anything more you would like me to add here, just let me know :)
