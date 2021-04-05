package com.example.fyp

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class Audio: TextToSpeech.OnInitListener {

    private val ctx: Context
    private val loc: Locale
    private val tts : TextToSpeech

    constructor(context : Context, countryCode: String, regionCode : String) {
        ctx = context
        loc = Locale(countryCode, regionCode)
        tts = TextToSpeech(ctx, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(loc)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("TTS", "This Language is not supported")
            } else {
                Log.i("TTS", "Initilization Success!")
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    fun generate_audio(value: String) {
        tts.speak(value, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}