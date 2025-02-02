# EducateMe&You

[![CodeFactor](https://www.codefactor.io/repository/github/lkeey/ecucateme/badge)](https://www.codefactor.io/repository/github/lkeey/ecucateme)

### **::Technologies::**

> Navigation

Jetbrains Compose Navigation is used to navigate between screens. An example of the organization of the authentication graph:

```cpp
sealed interface Route {
    // graph Auth
    @Serializable
    data object Auth: Route

    @Serializable
    data object Splash: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Register: Route
}
```

To navigate between screens we use:

```cpp
navController.navigate(Route.Register)
```

> Dependency Injection

Koin is used for dependency injection.

To use Koin in android we must add this to Manifest.xml

```cpp
<application  
  android:name=".EducateMeApp"
  ...
</application>
```

And init koin

```cpp
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}
```

For example, in order to transfer a ViewModel to the screen, we need to write the following:

```cpp
val sharedModule = module {
  viewModelOf(::ProfileViewModel)
}
```

> Network

Ktor is used to work with the network. To process requests to the network, you need to create an HttpClient:

```cpp
object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Any
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}
```

This is a function that saves the refresh token and looks at the status of the request.

```cpp
suspend inline fun <reified T> safeCallWithCookies(
    saveToLocalDB: (String) -> Unit,
    execute: () -> HttpResponse,
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch(e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    val cookies = response.setCookie()[0].value
    saveToLocalDB(cookies)

    return responseToResult(response)
}
```

To get a student profile model:

```cpp
@Serializable
data class ProfileModel(
    @SerialName("name") val name : String,
    @SerialName("username") val username : String,
    @SerialName("avatar_url") val profileLogoUrl : String,
)
```

You need to send the following request:

```cpp
override suspend fun getTeacherProfile(
        access: String,
        username: String
    ): Result<ProfileModel, DataError.Remote> {
        return safeCall<ProfileModel> {
            httpClient.get(
                urlString = "$BASE_URL/auth/user/$username"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }
```

> Images

Coil is used to upload images. For example, to load teacher logos

```cpp
AsyncImage(
    model = teacher.profileLogoUrl,
    contentDescription = "teacher logo",
    contentScale = ContentScale.Crop,
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .clip(CircleShape),
    alignment = Alignment.Center
)
```

