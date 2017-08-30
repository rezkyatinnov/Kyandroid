# Kyandroid

![license](https://img.shields.io/npm/l/node-readme.svg)

A reusable libraries wrapper to kick start an android project. Current version featuring Retrofit and Realm. Easier use of retrofit and realm. Easy session management (stored on realm)

## Features
- Rest client using Retrofit
- Local data management using Realm
- Session Management using LocalData on Realm

## Install
See complete guide of install by jitpack [here](https://jitpack.io/#rezkyatinnov/kyandroid)

### gradle
#### 1. Add the JitPack repository to your build file
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
#### 2. Add the dependency
```groovy
dependencies {
    compile 'com.github.rezkyatinnov:kyandroid:1.0.0-rc1'
}
```

## Usage

### Initiate in Custom Application Class or Launcher Activity
#### Kotlin Example
```kotlin
class ExampleApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Kyandroid.setDbKey("replace this with 64 long string")
        Kyandroid.init(this,
                "http://api.example.com/", ExampleRestService::class.java,
                "Shared Preff Name",Context.MODE_PRIVATE)
    }
}
```
#### Java Example
```java
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Kyandroid.setDbKey("replace this with 64 long string");
        Kyandroid.init(this,
                "http://api.example.com/", ExampleRestService.class,
                "Shared Preff Name",Context.MODE_PRIVATE);
    }
}
```
Currently an example project is in progress. you can check them [here](https://github.com/rezkyatinnov/kyandroid-sample)

###

## Contributing

Contributions welcome; Please submit all pull requests against the develop branch. This repo is following gitflow branching model [GitFLow](https://datasift.github.io/gitflow/IntroducingGitFlow.html). If your pull request contains JavaScript patches or features, you should include relevant unit tests. Thanks!

## Author

[Rezkya Tinnov](http://github.com/rezkyatinnov) : <mailme@rezkyatinnov.com> 

## License

 - **MIT** : http://opensource.org/licenses/MIT