# HeliosItunes
Sample Application to demonstrate MVVM with repository pattern that uses Dagger, RxJava, Retrofit, & Room (for data cache) libraries. It also has corresponding UNIT TEST for the track repository (I will update later to add Unit test for other classes). I used  mockito & junit for unit testing. Below are the complete details of the application:

<ul>
  <li>Retrofit with RxJava -> used to handle network calls using observable</li>
  <li>Room Library -> used to handle database transaction (to cache the last server response from the itunes search API)</li>
  <li>SharedPreference -> used to hold last known searched keyword value for data caching</li>
  <li>Fresco (Facebook library) -> used to download artwork images</li>
  <li>Gson (Google Library) -> used to parse json response from the service</li>
  <li>Delivering Object/Data to a fragment using Interface Callback</li>
  <li>MarginItemDecoration -> used to add spaces between items on recyclerview</li>
  <li>ExtensionFunction Class -> class that contains created extension function like String.supportHTMLTags(), Long.formatToDisplayTime(), String.formatStringDate(), & etc.</li>
  <li>Dimens.xml -> used to store all view sizes</li>
  <li>color-palette.xml -> used to store all custom-colors that is needed by the application</li>
  <li>text-style.xml -> used to store all text style that is needed by the application</li>
  <li>strings.xml -> used to store all string label that is needed by the application</li>
  <li>RecyclerView with SwipeRefreshLayout</li>
  <li>ConstraintLayout (Android Support)</li>
  <li>CardView (Android Support)</li>
  <li>SearchView (Android Support)</li>
</ul>

The application project also contains 5 different modules.

<ul>
  <li>buildSrc - module that holds build specific classes. Currently it only have the Dependencies class. The Dependencies class contains library names and versions object that is used on gradle files</li>
  <li>common - module that contains shared function, interface, classes & resources.</li>
  <li>trackClient - module that handles the API & database transaction.</li>
  <li>trackUI - module that handles the display/UI/UX of the track screens.</li>
  <li>appDB - module that handles the local database of the application.</li>
</ul>


I decided to use MVVM pattern for this sample application because it uses data binding. It is easier to update the display value of the views compare to MVP.
 It also has lesser code. One example is that, the data class can already be used to bind into the xml layout file and set display values.
 It is also easier to create unit test since there is no UI interaction on ViewModel. The disadvantage that I noticed on MVVM is that it is harder to design the correct flow for the ViewModel.

Here is the latest released APK build of the app: https://drive.google.com/open?id=1N3-5eqWuk5TF_g3XdZQRnCHaAsWharnS

<b>App Screenshots:</b>

<b>Track List Screen & Track Details Screen:</b><br />
<img src="https://raw.githubusercontent.com/HeliosSoftwareDeveloper/itunes/master/screenshots/list_searching.png" width="30%" />&nbsp;&nbsp;
<img src="https://raw.githubusercontent.com/HeliosSoftwareDeveloper/itunes/master/screenshots/track_details_new.png" width="30%" />
 <br /><br />

<b>Track List Screen (Cached Data), Track List Screen (Loading), & Track List Screen (No keyword match):</b><br />
<img src="https://raw.githubusercontent.com/HeliosSoftwareDeveloper/itunes/master/screenshots/list_cache.png" width="30%" />
 &nbsp;&nbsp;
 <img src="https://raw.githubusercontent.com/HeliosSoftwareDeveloper/itunes/master/screenshots/list_loading.png" width="30%" />
 &nbsp;&nbsp;
<img src="https://raw.githubusercontent.com/HeliosSoftwareDeveloper/itunes/master/screenshots/list_nomatch_new.png" width="30%" />
 <br /><br />

