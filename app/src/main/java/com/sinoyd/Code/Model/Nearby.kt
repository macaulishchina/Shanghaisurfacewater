package com.sinoyd.Code.Model

import com.sinoyd.Code.Until.HttpListener
import com.sinoyd.Code.Until.Networkrequestaddress
import com.sinoyd.Code.Until.ShowLog
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

/**
 * 作者： scj
 * 创建时间： 2018/2/1
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Model
 */

interface NearbyModel {
    fun getnearby(loginId: String, latitude: Double, longitude: Double, tag: String, listener: HttpListener)
}


class NearbyImpl : NearbyModel {
    override fun getnearby(loginId: String, latitude: Double, longitude: Double, tag: String, listener: HttpListener) {
        val params = RequestParams(Networkrequestaddress.Getnearby + "?loginId=$loginId&latitude=${31.2197}&longitude=${121.3743}")

        x.http().get(params, object : Callback.CommonCallback<String> {
            override fun onFinished() {
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                ShowLog("xhttp").show("onError", params, ex!!.message!!)
                listener.requestFailed(ex!!.message.toString())
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
            }

            override fun onSuccess(result: String?) {
                ShowLog("xhttp").show("onSuccess", params, result!!)
                listener.requestSuccess(result!!, tag)
            }

        })
    }

}