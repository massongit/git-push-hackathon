package io.github.massongit.hackathon.push.git.main.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import io.github.massongit.hackathon.push.git.R
import io.github.massongit.hackathon.push.git.application.MainApplication
import io.github.massongit.hackathon.push.git.main.helper.MainHelper

/**
 * メイン画面のActivity
 */
class MainActivity : AppCompatActivity() {
    companion object {
        /**
         * ログ用タグ
         */
        private val TAG = MainActivity::class.simpleName
    }

    /**
     * Helper
     */
    private lateinit var helper: MainHelper

    /**
     * 認証後のURI
     */
    private var authorizedUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(MainActivity.TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        val application = this.application

        if (application is MainApplication) {
            this.helper = MainHelper(application.service)
        }

        Log.d(MainActivity.TAG, "data=" + this.intent.dataString)
        this.authorizedUri = this.intent.data
    }

    override fun onResume() {
        Log.v(MainActivity.TAG, "onResume called")
        super.onResume()
        if (this.authorizedUri != null) {
            val getUserInformationButton = this.findViewById(R.id.get_user_information_button)

            if (getUserInformationButton is TextView) {
                this.helper.setAccessToken(this, this.authorizedUri, getUserInformationButton)
            }

            this.authorizedUri = null
        }
    }

    /**
     * ユーザー情報取得ボタン押下時のイベント
     */
    fun onGetUserInformationButtonClick(v: View) {
        Log.v(MainActivity.TAG, "onGetUserInformationButtonClick called")
        val resultView = this.findViewById(R.id.result_view)
        if (resultView is TextView) {
            this.helper.getUserInformation(resultView)
        }
    }
}
