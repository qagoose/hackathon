# Applitools Hackathon Entry

## Deviations from assignment

There's very little difference between my V1 and V2 tests here, because I realised during the traditional tests in V1
 that hooking up assertions for each little detail on the login page was going to take far too much coding to be
 sustainable in any real test framework.  Visual testing makes far more sense here.

I've also organised the framework/tests how I usually would, just for familiarity while working with it, so:

* There are different test suites
* My V1 and V2 are on different branches
* I cheated at one point during the traditional tests and employed the AShot library to do some low budget visual testing.

## Usage

Traditional and visual tests are split out into individual packages.  These can be run via Maven using the command line
options:

* -Dtest=*.visual.*Test
* -Dtest=*.traditional.*Test

For visual tests the following properties are also required:
* -DAPPLITOOLS_API_KEY (this should also be able to pick up this from the same environment variable, but I'm not 100% on that)
* -DEYES_BATCH_ID (this keeps everything batched together even though they're in different suites.  You should change
this between separate runs)

So for a visual testing run via Maven, your command should look like this:

-Dtest=*.visual.*Test -DAPPLITOOLS_API_KEY=YOUR_KEY -DEYES_BATCH_ID=21309120 test -f pom.xml

## Branches

* v1 - Represents original tests against V1
* v2 - Represents altered tests against V2 of the site (really just URL changes because of the note above)

## Bugs found by traditional testing:

Traditional testing located a bunch of functional bugs on V2 that were very quick to debug and understand:

* Canvas chart has different values for January and July 2018
* Login page no longer has error messages for a blank form
* Login page no longer showed an error if the user didn't enter a password
* Recent transactions no longer sorts by amount correctly
* One of the ads on the dynamic version of the dashboard page no longer shows up

## Bugs found by visual testing:

As well as all the issues located by traditional testing, visual testing also noticed:

* A whole bunch of design issues on the login page that may or may not have been intended by the developers, and are difficult to notice
via traditional means.