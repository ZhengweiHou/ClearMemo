package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.service.IUpdateUserMessage;
import com.vector.clearmemo.service.impl.UserServiceImpl;
import com.vector.clearmemo.ui.NichengActivity.ViewOCL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class AddressActivity extends Activity {

	private ImageView address_return_btn;
	private EditText address_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);

		address_return_btn = (ImageView) findViewById(R.id.address_return_btn);
		address_edit = (EditText) findViewById(R.id.address_content);
		address_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String address1 = intent.getStringExtra("address1");
		address_edit.setText(address1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// TODO Auto-generated method stub
			String address = address_edit.getText().toString().trim();
			// 点击返回按钮后，调用底层来讲当前页面的值存到数据库中
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					AddressActivity.this);
			BaseActivity.logingUser.setAddress(address);
			iManagerService.updateAddress(BaseActivity.logingUser);
			// 从当前页面中返回到用户管理界面中去，将页面中的值返回到AdminManagerActivity中
			Intent intent = new Intent(AddressActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("address", address);
			startActivity(intent);
			AddressActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String address = address_edit.getText().toString().trim();
			// 点击返回按钮后，调用底层来讲当前页面的值存到数据库中
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					AddressActivity.this);
			BaseActivity.logingUser.setAddress(address);
			iManagerService.updateAddress(BaseActivity.logingUser);
			// 从当前页面中返回到用户管理界面中去，将页面中的值返回到AdminManagerActivity中
			Intent intent = new Intent(AddressActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("address", address);
			startActivity(intent);
			AddressActivity.this.finish();
		}
	}
}
