# RealmAndroid
Weather Realm, Online and Offline MVP Project available on GooglePlay

Sample MVP Project containing Unit Tests, Realm implementation and DI with Dagger2. 

This is how a proper non-spagetti code should look like! It have a good architecture a slight defects here and there 

which can be fixed but i wanted to share my knowlege with the rest and see how they can manage to fix this if they can actually see the problems. 

This project contains a proper Dagger2 implementation and a good MVP architecture which can be easy maintained and tested.

In the project you have Realm to store some data from the OpenWeather api and i left the key so you can test it right away.

Please if you use this project add you own api key profile on openweather. It can be a good step for someone that is just getting in to these libraries and into the android world.

Rx Subscription is already handled here but in future if you use it unsubscribe and make sure to make a subscription 

since you will understand what will happen if you don't :) simply your observable will just push or request data while there is none.

Pointer?

- This project need to be changed from Rx1 to Rx2 which will not be that ard just read the forked project that i have and check the official documentation on RxJava - RxAndroid repo and you will find all you need to know. A lot stuff have being removed and changed. It is totally rewriten seems like for the better.

- Change to Rx2 well you can just implement Observer from the new lib and just subscribe(this) for the observable.

What can be better and what not?

- The Project is made like this on purpose and it is just a sample to get you started with Realm and all the other libs.

- Currently RxLibs have a lot problems since they are moving to a diffrent spot right now and ProGuard doesn't work at all.

- Dagger have some too left these for you to find.

Project not Running? 

- Check everything maybe you got some configs on your Android Studio version wrong. I am using 2.2 right now.

Project work?

- added simple edit text and some buttons and changeble gif background not to be that simple ^^

Project Structure? Updated for the latest 2.8 libs

- Component and Module -> Dagger2 -> DI
- Presenter -> Presentation Layer
- Activity -> View Layer
- Realm and Model classes -> Model Layer

Documentation?

- The prject is documented and you can remove that with a simple Android Studio Plugin if you don't want the documentation.

Removed transitions?

- Yes there is no need for any animations that's why we remove them completely. Any diffrence between this and a fragment? Answer that one by yourself.

Want to remove the white screen on the beggining of the app?

- Well you have to create a proper Splash Screen one that doesn't involve Threads ^^

- There is a good guide how to do that it is simple and you don't have to remove the preview of the activity since it is an important part of your app lifecycle.

- Well seems like even the big names like Facebook, Google and other big companies are loving the white activity preview and
doesn't want to handle that and keep showing the users white screens as much as possible. That is really a great way of giving and example!

- Well there is a solution for this that you can manage to solve it yourself and it is pretty easy to use it as it should and not just ignore it.

Why Realm?

- Realm contains almoust everything you may need to integrate it with your projects.

- Realm is build on Reactive and Functional programming and it is fully compatible with Java 1.8 and Rx feautures.

- Realm is 100 times faster for read from SQL and ORM. 

- There is no where that says you have to use it and you don't actually it is therefor you completely free.

- You can get it and try it for your self a lot people have problems with it and don't want to work with it and they stick with SQL.

- As everything Realm have a lot problems too. Currently RealmBrowser and all of the tools that you can use to check your DB after it is beign
created are not working. 

- Realm doesn't allow relations as it should and it wants always a primaryKey. You can see in this project how we handled this.

- Other than that almoust every big company out there is working with Realm there are a lot big names and probably Google too.

- There are a lot of more in-depth infos about realm of their website, it is very well documented and have everything there.

- There are a lot of similarities with Firebase as you can see.

Project link:

- https://play.google.com/store/apps/details?id=com.realm.nikiizvorski.realmandroid


#### Author

- Niki Izvorski (nikiizvorski@gmail.com)
