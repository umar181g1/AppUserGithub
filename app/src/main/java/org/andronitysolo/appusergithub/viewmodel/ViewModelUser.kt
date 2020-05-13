package org.andronitysolo.appusergithub.viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.andronitysolo.appusergithub.Networking.ApiCall
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.Response.ResponseUser

class ViewModelUser(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val data = MutableLiveData<ResponseUser>()
    val isLoading = MutableLiveData<Boolean>()
    val REGULAR_KEY = "regular_key"
    val LIVE_DATE_KEY = "live_data_key"

    @SuppressLint("CheckResult")
    fun User() {
        isLoading.value = true
        ApiCall().
            instance().findUserDetailByUsername("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    isLoading.value = false
                    if (it.isSuccessful) {
                        data.postValue(it.body())
                        Log.d("oq data", Gson().toJson(it.body()))


                    }
                }, {
                    isLoading.value = false

                })



    }

    val regularText: String
        get() = savedStateHandle.get<String>(REGULAR_KEY).orEmpty()

    val dataBundle = MutableLiveData<Bundle>()
    val getDataBundle: LiveData<Bundle>? = savedStateHandle.getLiveData(LIVE_DATE_KEY)

    fun saveRegularText(text: String) {
        savedStateHandle.set(REGULAR_KEY, text)
    }

    fun saveBundle(b: Bundle) {
        savedStateHandle.set(LIVE_DATE_KEY, b)
    }
}