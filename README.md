# The Cat App
A cute cat app to learn about [**Retrofit**](https://square.github.io/retrofit/)

Retrofit is a type-safe HTTP client for Android and Java. It's a standard and easy tool to consume APIs used by Android developers community

This App use [TheCatApi](https://thecatapi.com/) to make requests, check the [docs](https://docs.thecatapi.com/) and get your API Key for free

To make request using Retrofit follow the steps below:


**1. Add Retrofit dependencies (build.gradle)**
```python
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```
**2. Give permission (AndroidManifest.xml)**
```xml  
<uses-permission android:name="android.permission.INTERNET"/>
```
**3. Create your data class**
```kotlin
data class Kitty(
	@field:SerializedName("name")
	val name: String
)
```
**4. Declare your End Points**
```kotlin
interface CatAPI {
    @GET("breeds")
    fun getBreeds(): Call<List<Kitty>>

    @Headers("x-api-key: xxxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxx")
    @GET("breeds/search")
    fun getKitty(@Query("q") breedCode: String): Call<List<Kitty>>
}
```
**5. Build your client**
```kotlin
val retrofitClient = Retrofit.Builder()
    .baseUrl("https://api.thecatapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val endpoint = retrofitClient.create(CatAPI::class.java)
```
**6. Implement your callbacks**
```kotlin
val callback = endpoint.getBreeds()
callback.enqueue(object : Callback<List<Kitty>> {
    override fun onFailure(call: Call<List<Kitty>>, t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<Kitty>>, response: Response<List<Kitty>>) {
        response.body()?.let {
            myAdapter.setListItems(it)
        }
    }
})
```

Now, have fun! :heart_eyes_cat: :heart_eyes_cat: :heart_eyes_cat:
