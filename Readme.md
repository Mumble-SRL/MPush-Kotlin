<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">



## MPush-Kotlin

If your project it configured to send push notifications you must use this set of API to proper register your users' devices tor receive push notifications. Also you'll need to have basic knowledge of Google Firebase and Firebase Cloud Messaging.



### Before you start

You must **create a Firebase project** for your application, this way you'll have the push server key to insert in your `MPush settings on MBurger`. This will generate a push API KEY.

To create a new Firebase project, please reference [this](https://firebase.google.com/docs/android/setup) documentation.
Remember that you'll need at least these Firebase dependecies:

```java
com.google.firebase:firebase-core
com.google.firebase:firebase-messaging
```

For **FCM client setup** refer [this](https://firebase.google.com/docs/cloud-messaging/android/client) documentation. Long story short you'll ned to extend a new `FirebaseMessagingService`  which will trigger `onNewToken` to obtain the Firebase token which you'll need to send to the MBurger API and `onMessageReceived`, an event triggered when a push message is received.
Be aware that **MPush uses the "data" message** types in order to permit the developers to completely customize notifications and behaviour on receiveing notifications (you'll find all about data messages [here](https://firebase.google.com/docs/cloud-messaging/concept-options))

After you have set up your project for Firebase you can start using Nooko SDK to register users receive FCM messages.

### Setup

First thing, you can get this sdk via **Gradle** through **MavenCentral** repo, adding this to your top `build.gradle`:

```
allprojects {
    mavenCentral()
}
```



Then add to your dependencies:

```
implementation 'cloud.mburger.mpush_android_sdk_kt:0.0.2'
```



### Register a device

To register a new device you'll need to have a Firebase token, which you can obtain one from your `FirebaseMessagingService` method  `onNewToken`:

```kotlin
override fun onNewToken(token:String) {
}
```

 Then is a best practice to register your device calling the registration API:

```kotlin
override fun onNewToken(token: String) {
    MPushTasks.sendToken(context, OPTIONAL_LISTENER, getDeviceID(), token)
}
```

Where `getDeviceID()` is your method to obtain the **Android ID**  which will be your unique identifier. Pay attention to the changes Oreo makes to this data, refer to [this documentation](https://developer.android.com/reference/android/provider/Settings.Secure#ANDROID_ID).
Now your device is ready to receive push messages with your `FirebaseMessagingService` method `onMessageReceived`, but if you need to differentiate push groups you may need to use **topics**.



### Subscribe to topics

A topic is like a group you can subscribe in order to send push notifications specifically to that topic, you can subscribe to multiple topics creating a `MPTopic` Arraylist with the topic names, then call the API:

```kotlin
val topics = ArrayList<MPTopic>()
topics.add(MPTopic(
    TOPIC_CODE, //The main topic String to which subscribe (eg. sport)
    TOPIC_NAME, //A familiar name to give your topic (eg. Sport Club)
    false       //If it's a topic where only one user should subscribe
))
MPushTasks.registerTopics(context, getDeviceId(context), topics)
```

From the `MBurger dashboard` then you can send push notification only to some topics of make an app send push notifications to a specific topic. While it's not necessary to subscribe to topics at every startup, it can be useful to resubscribe anytime your `InstanceID` changes in order to mantain data coherence.

You can use some constant Topics to let your users subscrie to a "All Users" channel (a Project channel) or for a single device:

```kotlin
MPush.projectPushTopic(context) //All users
MPush.devicePushTopic(context)  //Only this device
```

When you receive a push notification then your `FirebaseMessagingService` will be triggered and you will find all the data you inserted on the "**data**" field of the **RemoteMessage** object.

```kotlin
@Override
override fun onMessageReceived(remoteMessage: RemoteMessage) {
     val map = remoteMessage.data;

     //The title for the message is inside the "title" field (OPTIONAL)	
     val title = map.get("title");

     //The body of the message is inside the "body" field (OPTIONAL)
     val msg = map.get("body");
     if(map.containsKey("custom")) {
         val custom = map.get("custom");
         if(custom != null){
             val jCustom = JSONObject(custom);
			//Take out the data you inserted inside the notification 
             //and create your notification with Android SDK.
         }
     }
}
```

To create a notification with the Andorid SDK refer [this documentation](https://developer.android.com/training/notify-user/build-notification).



### Push topics management

To unsubscribe from a push topic you'll need to call the **unregisterTopics**() API with the same fields you used with **registerTopics**(). You can also unsubscribe to all topics you subscribed by calling:

```kotlin
MPushTaks.unregisterAllTopics(context, getDeviceId())
```

This way you'll no more receive push messages from any topic you registered the device before.

