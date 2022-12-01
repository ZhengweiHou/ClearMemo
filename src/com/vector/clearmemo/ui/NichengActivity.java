package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.domain.po.User;
import com.vector.clearmemo.service.IManagerService;
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
import android.widget.Toast;

public class NichengActivity extends Activity {

	private ImageView nicheng_return_btn;
	private EditText nicheng_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nicheng);

		nicheng_return_btn = (ImageView) findViewById(R.id.nicheng_return_btn);
		nicheng_content = (EditText) findViewById(R.id.nicheng_content);

		nicheng_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String nicheng = intent.getStringExtra("nicheng1");
		nicheng_content.setText(nicheng);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			String nicheng = nicheng_content.getText().toString().trim();
			// 点击返回按钮后，调用底层来讲当前页面的值存到数据库中
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					NichengActivity.this);
			BaseActivity.logingUser.setNicheng(nicheng);
			iManagerService.updateNicheng(BaseActivity.logingUser);
			// 从当前页面中返回到用户管理界面中去，将页面中的值返回到AdminManagerActivity中
			Intent intent = new Intent(NichengActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("nicheng", nicheng);
			startActivity(intent);
			NichengActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// 获取昵称中设置昵称的值
			String nicheng = nicheng_content.getText().toString().trim();
			// 点击返回按钮后，调用底层来讲当前页面的值存到数据库中
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					NichengActivity.this);
			BaseActivity.logingUser.setNicheng(nicheng);
			iManagerService.updateNicheng(BaseActivity.logingUser);
			// 从当前页面中返回到用户管理界面中去，将页面中的值返回到AdminManagerActivity中
			Intent intent = new Intent(NichengActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("nicheng", nicheng);
			startActivity(intent);
			NichengActivity.this.finish();
		}
	}
}
