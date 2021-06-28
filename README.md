# TheProfileOrderExperiment

### App Walkthrough
1. When the app is launched, loader is shown and we make two api calls (for the profile order and to fetch the users).
  a. If call is successful, then user is shown the information about users in the order that we fetched.
  b. If call is failed, then we retry the call for 3 times and if it still fails, then we close the app.
2. Profile screen shows information regarding -
  * Name
  * Photo (optional)
  * Gender
  * About (optional)
  * School (optional)
  * Hobbies (optional)
3. Clicking on the next button should take the user to next profile. If we reach the end of the list, user is shown a message stating the same.
4. Clicking on the physical back button closes the app.

### Code Walkthrough
1. This application is implemented in Kotlin. I have used MVP architecture style to make unit testing easier and to increase simplicity and reusability of code.
2. Single activity architecture has been used to have the smooth transitions while switching profiles.
3. The kotlin modules are divided into 3 packages - Model, View and ViewModel
4. Retrofit with a custom CallBackWithRetry class has been used to make network calls.
5. MainViewModel class is responsible for making network calls and hold live data.
6. NetworkUtils class has classes needed to make the calls -
 a. RetrofitClient is a straightforward class which provides a client which is used to make calls.
 b. CallbackWithRetry is an abstract class used to provide retry logic for network calls. I have the set the retry limit to 3.
 c. CallsUtils has method enqueueWithRetry method which can be called to make network calls in viewmodel.
7. The main profile screens has been divided into components holding information for each section. Component style approach is used to provide dynamic ordering of sections and to provide extensibility, if in future we want other screens to use those.
8. In account for different screen sizes, I have created dimens files for 4 types of devices (hdpi, xhdpi, xxhdpi, and xxxhdpi). Currently we are using dimens file only for profile photo but it can be used for other sections too.
9. In current implementation, we are not storing the previously viewed profiles when you hit next button. If we want to add the functionality then simply adding addToBackStackTrace should help.

#### Future Work -
1. Implementing unit test for verifying network calls.
2. Providing back button functionality.
