# Scold App ProGuard Rules

# Keep Firebase classes
-keep class com.google.firebase.** { *; }

# Keep Room entities
-keep class com.scold.app.data.local.** { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }
