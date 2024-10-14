-keep class org.bouncycastle.jsse.** { *; }
-keep class org.conscrypt.** { *; }
-keep class org.openjsse.** { *; }
-dontwarn okhttp3.internal.platform.**

-keepattributes *Annotation*

-keep class com.zero.vulnlab.MainActivity { *; }
-keep class com.zero.vulnlab.AppHashActivity { *; }
-keep class com.zero.vulnlab.DatabaseController { *; }
-keep class com.zero.vulnlab.DebugActivity { *; }
-keep class com.zero.vulnlab.DeepLinkActivity { *; }
-keep class com.zero.vulnlab.DeepLinkViewActivity { *; }
-keep class com.zero.vulnlab.ExportedActivity { *; }
-keep class com.zero.vulnlab.FlagSecureActivity { *; }
-keep class com.zero.vulnlab.IsRootedActivity { *; }
-keep class com.zero.vulnlab.LoginActivity { *; }
-keep class com.zero.vulnlab.RegisterActivity { *; }
-keep class com.zero.vulnlab.SharedPreActivity { *; }
-keep class com.zero.vulnlab.TCPRequestActivity { *; }
-keep class com.zero.vulnlab.TCPServer { *; }
-keep class com.zero.vulnlab.TCPClient { *; }
-keep class com.zero.vulnlab.UserListActivity { *; }
-keep class com.zero.vulnlab.User { *; }
-keep class com.zero.vulnlab.UserDatabase { *; }
-keep class com.zero.vulnlab.UserAdapter { *; }

-keepclassmembers class com.zero.vulnlab.CodeObfuscationActivity {
    public *;
}

-keepnames class !com.zero.vulnlab.CodeObfuscationActivity
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepattributes Signature,Exceptions,InnerClasses,EnclosingMethod,RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations,AnnotationDefault

-keep class **.R$* { *; }

-repackageclasses ''
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-allowaccessmodification
