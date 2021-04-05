package com.example.fyp

import android.graphics.Rect
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.otaliastudios.cameraview.Frame

class Detector {

    lateinit var objectDetector: ObjectDetector

    public fun load_model()
    {
        val options = ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                .build()
        objectDetector = ObjectDetection.getClient(options)
    }

    public fun load_custom_model()
    {
        val localModel = LocalModel.Builder()
            .setAssetFilePath("detect.tflite")
            .build()

        val customObjectDetectorOptions =
            CustomObjectDetectorOptions.Builder(localModel)
                .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
                .enableClassification()
                .setClassificationConfidenceThreshold(0.6f)
                .setMaxPerObjectLabelCount(5)
                .build()

        val objectDetector =
            ObjectDetection.getClient(customObjectDetectorOptions)
    }

    public fun detect(input: InputImage): Rect {

        var result = Rect(0,0,0,0)
        objectDetector.process(input)
                .addOnSuccessListener { detectedObjects ->
                    for (detectedObject in detectedObjects) {
                        val boundingBox = detectedObject.boundingBox
                        val trackingId = detectedObject.trackingId
                        result = boundingBox
                    }
                }
                .addOnFailureListener { e ->
                }

        return result
    }
}