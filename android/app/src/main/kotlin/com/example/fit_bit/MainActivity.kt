import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        // Register platform channel
        val firebaseMLService = FirebaseMLService(this)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "firebase_ml_service")
            .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                when (call.method) {
                    "downloadModel" -> {
                        firebaseMLService.downloadModel()
                        result.success(null)
                    }
                    else -> result.notImplemented()
                }
            }
    }
}
