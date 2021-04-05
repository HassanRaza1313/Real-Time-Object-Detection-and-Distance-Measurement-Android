package com.example.fyp

import android.content.Context
import android.os.Bundle
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.otaliastudios.cameraview.Frame
import java.io.File
import java.io.InputStream
import kotlin.coroutines.coroutineContext

class Classifier {
    lateinit var labeler: ImageLabeler
    lateinit var labelmap: String

    public fun load_model(context: Context)
    {
        val img = ImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.7f)
                .build()

        labeler = ImageLabeling.getClient(img)

        labelmap = context.applicationContext.assets.open("labelmap.txt").bufferedReader().use {
            it.readText()
        }

    }

    public fun classify(input: InputImage ,callback: (String) -> Unit) {

        var result: String = ""
        var temp: String = ""

        labeler.process(input)
                .addOnSuccessListener { labels ->
                    for (label in labels) {
                        val text = label.text
                        val confidence = label.confidence
                        val index = label.index

                        if(labelmap.contains(text))
                        {
                            result += text + "\n"
                        }
                    }
                    temp = result
                    result = ""

                    callback(temp)
                }
                .addOnFailureListener { e ->

                }

    }
}