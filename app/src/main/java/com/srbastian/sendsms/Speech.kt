package com.srbastian.sendsms


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.srbastian.sendsms.databinding.ActivitySpeechBinding
import java.util.Locale

class Speech : AppCompatActivity() {
    lateinit var speechBinding: ActivitySpeechBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        speechBinding = ActivitySpeechBinding.inflate(layoutInflater)
        var view = speechBinding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { result ->
                    val resultCode = result.resultCode
                    val data = result.data
                    if(resultCode == RESULT_OK && data != null){
                        val speakResult : ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                        speechBinding.tvResult.text = speakResult[0]
                    }
                })

        speechBinding.ibSpeech.setOnClickListener {
            convertSpeech()
        }
    }

    fun convertSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        activityResultLauncher.launch(intent)
    }
}