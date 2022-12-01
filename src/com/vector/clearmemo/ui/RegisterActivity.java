package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.service.IRegisterServie;
import com.vector.clearmemo.service.impl.UserServiceImpl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText register_victualname, register_usn, register_pwd;
	private ImageView registerBack;
	private Button btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		register_victualname = (EditText) findViewById(R.id.register_victualname);
		register_usn = (EditText) findViewById(R.id.register_usn);
		register_pwd = (EditText) findViewById(R.id.register_pwd);

		btnRegister = (Button) findViewById(R.id.register_surebtn);
		registerBack = (ImageView) findViewById(R.id.register_return_btn);

		btnRegister.setOnClickListener(new ViewOCL1());
		registerBack.setOnClickListener(new ViewOCL());

		BaseActivity.nowActivity = this;
	}

	class ViewOCL implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			RegisterActivity.this.finish();

		}
	}

	class ViewOCL1 implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			Intent intent = new Intent(RegisterActivity.this,
					LoginActivity.class);
			String nicheng = register_victualname.getText().toString().trim();
			String account = register_usn.getText().toString().trim();
			String password = register_pwd.getText().toString().trim();

			if (!nicheng.equals("")) {
				if (!account.equals("")) {
					if (!account.equals("")) {
						IRegisterServie iRegisterServie = new UserServiceImpl(
								RegisterActivity.this);
						int a = iRegisterServie.register(account, password,
								nicheng);
						switch (a) {
						case 0:
							Toast.makeText(RegisterActivity.this, "注册成功",
									Toast.LENGTH_SHORT).show();

							SharedPreferences.Editor sharedPreferencesEditor = RegisterActivity.this.getSharedPreferences(
									"clearmemo_message", MODE_APPEND).edit();
							sharedPreferencesEditor.putString("account",
									account);
							sharedPreferencesEditor.putString("password",
									password);
							sharedPreferencesEditor.commit();

							startActivity(intent);
							BaseActivity.nowActivity.finish();
							break;
						case 1:
							Toast.makeText(RegisterActivity.this,
									"账号长度必须是9到10位", Toast.LENGTH_SHORT).show();
							break;
						case 2:
							Toast.makeText(RegisterActivity.this, "您的账号已经存在了",
									Toast.LENGTH_SHORT).show();
							break;
						case 3:
							Toast.makeText(RegisterActivity.this,
									"密码长度必须是6到16位", Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
						}
					} else {
						Toast.makeText(RegisterActivity.this, "您的密码不能为空",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(RegisterActivity.this, "您的账户不能为空",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(RegisterActivity.this, "您的昵称不能为空",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

}
