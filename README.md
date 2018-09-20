# Simple appengine project to show an error using handlebars.java on appengine

The [latest version of Handlebars](https://github.com/jknack/handlebars.java/releases/tag/v4.1.0) switched to Nashorn, 
which comes with Java8. I'm running [kontaktlinsen-preisvergleich.de](https://kontaktlinsen-preisvergleich.de) on appengine and use handlebars for years now.
 
Of course I use Handlebars-Helpers and other stuff, so the issue was not so obvious for me. For that reason I extended the [appengine-quickstart-example](https://cloud.google.com/appengine/docs/standard/java/quickstart)
with a Servlet doing some handlebar stuff to show the problem.

To see the problem, clone this repo, run 
`./gradlew appengineRun` 
and open [localhost:8080/hello-hbs](http://localhost:8080/hello-hbs)

You will see this: 

![screenshot from exception](https://github.com/mpoehler/hbs-test/raw/master/screenshots/NPE-on-localhost.png "screenshot from exception")

A NPE deep inside Handlebar.java when registering Helpers.

I wrote a test to execute the code outside of the local devserver environment and that works fine:

[TODO include sceenshot from successful test]
![sceenshot from successful test](https://github.com/mpoehler/hbs-test/raw/master/screenshots/testScreenshot.png "sceenshot from successful test")

or even deployed to production works fine: [https://my-weather-57c82.appspot.com/hello-hbs](https://my-weather-57c82.appspot.com/hello-hbs)

So it only seems to happen in the combination of handlebars.java helper registration and the appengine local devserver. 

The NPE breaks in this line:

![screenshot of handler registration](https://github.com/mpoehler/hbs-test/raw/master/screenshots/NPE-break.png "screenshot of handler registration")

and this brought me to [this issue](https://github.com/GoogleCloudPlatform/app-gradle-plugin/issues/160) here. But it still seems not to be fixed. I'll go and file some 
issues in [app-gradle-plugin](https://github.com/GoogleCloudPlatform/app-gradle-plugin) and [handlebars.java](https://github.com/jknack/handlebars.java) and 
referencing this project.

