-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class javax.annotation.**

-keep class io.reactivex.** { *; }
-keepclassmembers class io.reactivex.** { *; }

-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

-keep interface ru.example.simplemosdiaryclient.**{ *; }
-keep class ru.example.simplemosdiaryclient.** { *; }

-keepattributes Signature
-keepattributes Exceptions

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.backup.BackupAgent

-keepclassmembers public class * extends android.view.View {
  void set*(***);
  *** get*();
}
-keepclassmembers class * {
    native <methods>;
}

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** w(...);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }
-keep class android.support.** { *; }
-keep interface android.support.** { *; }
