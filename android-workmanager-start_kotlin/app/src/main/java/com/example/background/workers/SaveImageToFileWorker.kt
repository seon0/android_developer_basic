package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "SaveImageToFileWorker"
class SaveImageToFileWorker(ctx: Context, params: WorkerParameters) :Worker(ctx, params) {

    private val Title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss Z", Locale.getDefault())

    override fun doWork(): Result {
        makeStatusNotification("Saving Image", applicationContext)
        sleep()

        val resolver = applicationContext.contentResolver
        return try {
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            val bitmap = BitmapFactory.decodeStream(resolver.openInputStream( Uri.parse(resourceUri) ))
            val imageUrl = MediaStore.Images.Media.insertImage(resolver, bitmap, Title, dateFormatter.format(Date()))
            if ( imageUrl.isNullOrEmpty() ) {
                val output = workDataOf(KEY_IMAGE_URI to imageUrl)
                Result.success(output)
            }
            else {
                Log.e(TAG, "Writing to Media Store Failed")
                Result.failure()
            }


        }
        catch (e: Exception) {
            Log.e(TAG, "Occured Error While Writing to Media Store Failed", e)
            Result.failure()
        }
    }
}