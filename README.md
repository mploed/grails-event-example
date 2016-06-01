# grails-event-example
This is the example code for my presentation on the Grails 3 event system.

Link to slides: The slidedeck to this demo can be seen at https://speakerdeck.com/mploed/building-loosely-coupled-and-reactive-applications-with-the-grails-3-event-system

The example consists of two Grails Applications:
- our-awesome-cms is the central application
- somewhere-publishing-plugin is a plug-in

In order to run the demo you first have to run grails install in somewhere-publishing-plugin after that you can run grails run-app in our-awesome-cms

Please mind that the ArcitcleController#save is sending an event over Reactor's EventBus (which is tread pooled, see application.yml in the our-awesome-cms project).
The name of the vent is our.awesome.cms.article_published and it's payload is the actual Article. 
Inside our-awesome-cms there are two services TwitterService, EmailNotificationService that are consuming the event (see the annotations)
In addition to that there is another Service called FacebookPublishingService in the somewhere-publishing-plugin which is consuming the given event as well.

This demoes the feature that we can now loosely couple plugins and applications that are using them. 


