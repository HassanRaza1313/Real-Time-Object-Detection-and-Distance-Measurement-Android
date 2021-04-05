package com.example.fyp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.core.HitResult
import com.google.ar.sceneform.rendering.Renderable
import com.otaliastudios.cameraview.CameraView
import kotlinx.android.synthetic.main.activity_detection.*


class DetectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detection)
        val audio = Audio(this,"en","GB")
        val video = Video()
        val detector = Detector()
        val classifier = Classifier()
        val frameProcessor = FrameProcessor()
        val view = findViewById<CameraView>(R.id.cameraView)
        detector.load_model()
        classifier.load_model(applicationContext)
        video.video_capture(view,this)
        view.addFrameProcessor {
            val image = frameProcessor.prepare_frame(it)
            detector.detect(image)
            classifier.classify(image){  result ->
                tvDetectedObject.text = result
                audio.generate_audio(result)
            }
        }
    }



}
