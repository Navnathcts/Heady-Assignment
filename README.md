# HeadyAssignment
Heady Assignment


1. Retrofit for api calling

Retrofit is an awesome type-safe HTTP client for Android and Java built by awesome folks at Square. Retrofit makes it easy to consume JSON or XML data which is parsed into Plain Old Java Objects (POJOs).


2.MVVM architecture

Model:

Model represents the data and business logic of the app. One of the recommended implementation strategies of this layer, is to expose its data through observables to be decoupled completely from ViewModel or any other observer/consumer (This will be illustrated in our MVVM sample app below).

ViewModel:

ViewModel interacts with model and also prepares observable(s) that can be observed by a View. ViewModel can optionally provide hooks for the view to pass events to the model.
One of the important implementation strategies of this layer is to decouple it from the View, i.e, ViewModel should not be aware about the view who is interacting with.

View:
Finally, the view role in this pattern is to observe (or subscribe to) a ViewModel observable to get data in order to update UI elements accordingly.

3.Kotlin language is used for development. 





