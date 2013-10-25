HashTagSwoop
===============

This is the server side of the application. 
The client side is located here:

James Dagres
DESCRIPTION:
=============
This application was originally meant to display the top hashtags from twitter, facebook and instagram. However,
only data available from twitter was available, so as the 
project evolved it turned into an application that will
have a server that will process any longitude and latitude
and return nearby cities with trending hashtags from twitter.
Because of this, hipsters and twitter congregations are
easily findable. Followers for days!


FEATURES
=======================================
* Will use a server (this project) to receive and transmit data. More specifically it receives data from Android clients (the current client's location), stores that data in JSON format, and allows client's to download that data using the JSON API
* Uses three activities. The login to server activity, the main activity, and the shake activity.
* Currently uses three android sensors; the accelerometer, the vibration sensor and the GPS to get the location. 
* Accelerometer is used to pick up shake events
* GPS is used to get the location and post it to the main server
* Uses more than five user interfaces and GUI elements. A linear layout for the login activity, a relative layout for the main and shake activities, multiple TextViews, a toggle button to save the server login information, numerous images and a toast displaying the GPS location.
* The toggle button will save the server login settings
in a property file.
* Low battery usage

Future Implementations:
=======================================
* A toggle button to save the server information using project properties, or a file, instead of a fixed hardcoded string. 
* Pulling hashtags from twitter, this is the main goal of this app and is currently in production!

Additional Resources:
=======================================
* For information on the twitter api and trend pulling see the following site: //twitter4j.org/oldjavadocs/3.0.3/index.html

ADDITIONAL PROGRAMS TO RUN THE PROJECT:
=======================================
Eclipse
* The program can be made and executed from the terminal as well 
as from Eclipse

INSTRUCTIONS:
=======================================
Building the project
* You will need to connect it to an android device and run the source code through eclipse or use one of eclipses virtual devices. The APK is also available. 

NOTES:
=======================================
* the Nexus 7 and similar tablets do not have haptic feedback, IE it does not have vibration, so vibration features from this project will not work.