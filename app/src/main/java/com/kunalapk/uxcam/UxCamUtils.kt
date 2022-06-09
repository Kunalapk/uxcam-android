package com.kunalapk.uxcam

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.uxcam.UXCam
import com.uxcam.datamodel.UXConfig

object UxCamUtils {

    private val uxCamWhiteListedActivities = HashMap<Class<*>, Boolean>().apply {
        set(MainActivity::class.java, true)
        
    }

    fun checkAndStartUxCam(context: Activity?) {
        Log.d("TAG","Activity - ${context?.localClassName}")
        if (isUnderUxCamScope(context)
        ) {
            if (!UXCam.isRecording()) {
                startUxCamSession(context, "Kyc")
            } else {
                Log.d("TAG","UxCam already recording - ${context?.localClassName}")
            }
        } else {
            stopUxCamSession()
            if (BuildConfig.DEBUG)
                Log.d("TAG",context?.localClassName + " This Activity is out of UxCam scope. - ${UXCam.isRecording()}")
        }
    }

    fun setUxCamScreenVisibility(context: Activity?) {
        when (isVisibleToUxCam(context)) {
            true -> resumeUxCamSession()
            false -> pauseUxCamSession()
            else -> stopUxCamSession()
        }
    }

    private fun isUnderUxCamScope(instance: Activity?): Boolean {
        uxCamWhiteListedActivities.forEach {
            if (it.key.isInstance(instance)) {
                return true
            }
        }
        return false
    }

    private fun isVisibleToUxCam(instance: Activity?): Boolean? {
        uxCamWhiteListedActivities.forEach {
            if (it.key.isInstance(instance)) {
                return it.value
            }
        }
        return null
    }

    fun hideViewsFromUxCam(vararg input: View) {
        for (item in input) {
            item.setBackgroundColor(ContextCompat.getColor(item.context,R.color.purple_200))
            UXCam.occludeSensitiveView(item)
        }
    }

    fun unHideViewsFromUxCam(vararg input: View) {
        for (item in input) {
            item.setBackgroundColor(ContextCompat.getColor(item.context,android.R.color.transparent))
            UXCam.unOccludeSensitiveView(item)
        }
    }

    fun stopUxCamSession() {
        if (UXCam.isRecording()) {
            UXCam.stopSessionAndUploadData()
            Log.d("TAG","UxCamSessionStopped")
        }
    }

    fun cancelUxCamCurrentSession() {
        UXCam.cancelCurrentSession()
    }

    fun pauseUxCamSession() {
        UXCam.occludeSensitiveScreen(true)
        Log.d("TAG","UxCanSessionPaused")
    }

    fun resumeUxCamSession() {
        UXCam.occludeSensitiveScreen(false)
        Log.d("TAG","UxCanSessionResumed")
    }

    fun startUxCamSession(activity: Activity?, productType: String?) {
        if (true
        ) {
            UXCam.setUserIdentity(
                "test"
            )
            UXCam.setUserProperty("app_version", BuildConfig.VERSION_CODE)
            UXCam.setUserProperty("device_id", "testdevice")
            UXCam.setUserProperty(
                "device_type",
                "${android.os.Build.MANUFACTURER}, ${android.os.Build.MODEL}"
            )
            UXCam.setUserProperty("os_type", "android")
            productType?.let {
                UXCam.setUserProperty("product_type", productType)
            }
            UXCam.startWithConfiguration(
                UXConfig.Builder()
                    .enableMultiSessionRecord(false)
                    .enableAutomaticScreenNameTagging(true)
                    .enableImprovedScreenCapture(true)
                    .build(), activity, true
            )
            Log.d("TAG","UxCamSessionStarted -${activity?.localClassName}")
        } else {
            Log.d("TAG",
                "UxCam recording is not enabled from remote config."
            )
        }
    }
}