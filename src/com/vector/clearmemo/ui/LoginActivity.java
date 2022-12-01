package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.domain.po.User;
import com.vector.clearmemo.service.ILoginService;
import com.vector.clearmemo.service.impl.UserServiceImpl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText login_usn, login_pwd;
	private Button login_btn;
	private TextView txt_signUp, txt_forgetpwd;
	private String account,password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		SharedPreferences sharedPreferences = getSharedPreferences(
				"clearmemo_message", MODE_APPEND);
		Boolean isfirstinsert = sharedPreferences.getBoolean("isfirstinsert",
				true);
		account = sharedPreferences.getString("account", "");
		password = sharedPreferences.getString("password", "");

		login_usn = (EditText) findViewById(R.id.login_usn);
		login_pwd = (EditText) findViewById(R.id.login_pwd);
		txt_signUp = (TextView) findViewById(R.id.txt_signUp);
		txt_forgetpwd = (TextView) findViewById(R.id.txt_forgetpwd);
		login_usn.setText(account);
		login_pwd.setText(password);

		txt_signUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);

			}
		});

	}
	
	

	public void click(View view) {
		switch (view.getId()) {
		case R.id.login_btn:
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			// 获取当前页edit中的值
			 account = login_usn.getText().toString().trim();
			password = login_pwd.getText().toString().trim();
			// 首先，判断密码是否为空
			if (!account.equals("")) {

				if (!password.equals("")) {
					ILoginService iLoginService = new UserServiceImpl(
							LoginActivity.this);
					Boolean flag = iLoginService.Login(account, password);
					if (flag == true) {

						startActivity(intent);
						SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(
								"clearmemo_message", MODE_APPEND).edit();
						sharedPreferencesEditor.putString("account", account);
						sharedPreferencesEditor.putString("password", password);
						sharedPreferencesEditor.commit();
						this.finish();

					}

					else if (flag == false) {
						Toast.makeText(getApplicationContext(), "您的账号或者密码不正确",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(getApplicationContext(), "您的密码不能为空",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "您的账号不能为空",
						Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.txt_forgetpwd:
			break;

		}
	}
}
