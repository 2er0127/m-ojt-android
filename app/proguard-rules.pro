-keepattributes *Annotation*

-keep class com.eqst.vulnlab.MainActivity { *; }
-keep class com.eqst.vulnlab.AppHashActivity { *; }
-keep class com.eqst.vulnlab.DatabaseController { *; }
-keep class com.eqst.vulnlab.DebugActivity { *; }
-keep class com.eqst.vulnlab.DeepLinkActivity { *; }
-keep class com.eqst.vulnlab.DeepLinkViewActivity { *; }
-keep class com.eqst.vulnlab.ExportedActivity { *; }
-keep class com.eqst.vulnlab.FlagSecureActivity { *; }
-keep class com.eqst.vulnlab.IsRootedActivity { *; }
-keep class com.eqst.vulnlab.LoginActivity { *; }
-keep class com.eqst.vulnlab.RegisterActivity { *; }
-keep class com.eqst.vulnlab.SharedPreActivity { *; }
-keep class com.eqst.vulnlab.TcpRequestActivity { *; }
-keep class com.eqst.vulnlab.TCPServer { *; }
-keep class com.eqst.vulnlab.TCPClient { *; }
-keep class com.eqst.vulnlab.UserListActivity { *; }
-keep class com.eqst.vulnlab.User { *; }
-keep class com.eqst.vulnlab.UserDatabase { *; }
-keep class com.eqst.vulnlab.UserAdapter { *; }

-keepclassmembers class com.eqst.vulnlab.CodeObfuscationActivity {
    public *;
}

-keepnames class !com.eqst.vulnlab.CodeObfuscationActivity
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
