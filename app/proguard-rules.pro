# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class android.support.v7.widget.** { *; }
-keep interface android.support.v7.widget.** { *; }
-keep class android.arch.** { *; }
-dontwarn android.arch.**
-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**
-keep class android.support.v7.** { *; }
-dontwarn android.support.v7.**

-keep class com.eqst.vulnlab.** { *; }
-keepclassmembers class com.eqst.vulnlab.** { *; }

-dontwarn kotlin.**
-keep class kotlin.** { *; }
-keepclassmembers class kotlin.** { *; }
-dontwarn kotlinx.**
-dontwarn androidx.**
-dontwarn org.intellij.lang.annotations.*

-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keep class okio.** { *; }
-keep public class * implements java.lang.reflect.Type
-keep class **$GsonConverter { *; }
-keepattributes Signature
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

-dontwarn org.reactivestreams.**
-dontwarn java.util.concurrent.Flow*
-dontwarn java.util.concurrent.flow.**
-dontwarn java.util.concurrent.**
-dontwarn com.facebook.stetho.**

-keepclassmembers class com.eqst.vulnlab.CodeObfuscationActivity { *; }
