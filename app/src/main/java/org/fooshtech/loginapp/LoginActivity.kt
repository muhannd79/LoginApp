package org.fooshtech.loginapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.toolbar.*


class LoginActivity : AppCompatActivity() {

    var emailIsReady = false
    var passIsReady = false
    var repPassIsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Create an account"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_left_32)
        }

        editTxt_login_email.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                checkIfEmailReady()

            }

        })



        editTxt_login_create_pass.onFocusChangeListener = View.OnFocusChangeListener { _, b ->

            if (!b) {
                checkIfPasswordReady()
            }
        }

        editTxt_login_repeat_pass.onFocusChangeListener = View.OnFocusChangeListener { _, b ->

            if (!b) {
                repPassIsReady = editTxt_login_create_pass.text.toString()
                    .contentEquals(editTxt_login_repeat_pass.text.toString())
                if (repPassIsReady) {
                    editTxt_login_repeat_pass.setBackgroundResource(R.drawable.edit_text_normal_green)
                    editTxt_login_repeat_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.check_mark,0)
                    isbtnReady()
                } else {
                    text_login_err_password.visibility = View.VISIBLE
                    text_login_err_password.text = "Your Password don't match"
                    editTxt_login_repeat_pass.setBackgroundResource(R.drawable.edit_text_error_red)
                    editTxt_login_repeat_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
                }
            }
        }


        btn_login_enter.setOnClickListener {

        }

    }

    private fun checkIfPasswordReady() {
        passIsReady = Regex("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,20}").matches(
            editTxt_login_create_pass.text
        )
        if (!passIsReady) {
            text_login_err_password.visibility = View.VISIBLE
            text_login_err_password.text = "Your Password doesn't match the Rule"
            editTxt_login_create_pass.setBackgroundResource(R.drawable.edit_text_error_red)
            editTxt_login_create_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
        } else {
            text_login_err_password.visibility = View.GONE
            editTxt_login_create_pass.setBackgroundResource(R.drawable.edit_text_normal_green)
            editTxt_login_create_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.check_mark,0)
        }
        isbtnReady()
    }

    private fun checkIfEmailReady() {
      val  emaiPattern = Patterns.EMAIL_ADDRESS.matcher(editTxt_login_email.text).matches()
        val emails = arrayOf("soso@gmail.com","user@gmail.com")
        if(emaiPattern){
            for (i in emails){
                if(i.equals(editTxt_login_email.text.toString())) {
                    text_login_err_email.visibility = View.VISIBLE
                    editTxt_login_email.setBackgroundResource(R.drawable.edit_text_error_red)
                    editTxt_login_email.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
                    emailIsReady = false
                    break
                } else {
                    text_login_err_email.visibility = View.GONE
                    editTxt_login_email.setBackgroundResource(R.drawable.edit_text_normal_green)
                    editTxt_login_email.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.check_mark,0)
                    emailIsReady = true
                }
            }
        } else{
            editTxt_login_email.setBackgroundResource(R.drawable.edit_text_normal_black)
            editTxt_login_email.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
        }
        //  Log.d("soso","emailIsReady="+emailIsReady)
        isbtnReady()
    }

    private fun isbtnReady() {
        btn_login_enter.isEnabled = emailIsReady && passIsReady && repPassIsReady

    }


}