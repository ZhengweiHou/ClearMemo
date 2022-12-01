package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.service.IUpdateUserMessage;
import com.vector.clearmemo.service.impl.UserServiceImpl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class TelActivity extends Activity {

	private ImageView tel_return_btn;
	private EditText tel_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel);

		tel_return_btn = (ImageView) findViewById(R.id.tel_return_btn);
		tel_edit = (EditText) findViewById(R.id.tel_content);
		tel_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String tel1 = intent.getStringExtra("tel1");
		tel_edit.setText(tel1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			String tel = tel_edit.getText().toString().trim();
			// 点击返回按钮后，调用底层来讲当前页面的值存到数据库中
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					TelActivity.this);
			BaseActivity.logingUser.setTel(tel);
			iManagerService.updateTel(BaseActivity.logingUser);
			// 从当前页面中返回到用户管理界面中去，将页面中的值返回到AdminManagerActivity中
			Intent intent = new Intent(TelActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("tel", tel);
			startActivity(intent);
			TelActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			String tel = tel_edit.getText().toString().trim();
			// 点击返回按钮后，调用底层来讲当前页面的值存到数据库中
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					TelActivity.this);
			BaseActivity.logingUser.setTel(tel);
			iManagerService.updateTel(BaseActivity.logingUser);
			// 从当前页面中返回到用户管理界面中去，将页面中的值返回到AdminManagerActivity中
			Intent intent = new Intent(TelActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("tel", tel);
			startActivity(intent);
			TelActivity.this.finish();
		}
	}
}
