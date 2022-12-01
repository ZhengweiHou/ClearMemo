package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.service.IUpdateUserMessage;
import com.vector.clearmemo.service.impl.UserServiceImpl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminManagerActivity extends Activity {

	private RelativeLayout manager_nicheng1, manager_usn1, manager_tel1,
			manager_sex1, manager_address1, manager_gexingqianming1;
	private TextView manager_nicheng1_content, manager_usn1_content,
			manager_tel1_content, manager_sex1_content,
			manager_address1_content, manager_gexingqianming1_content;
	private ImageView manager_return_btn;
	private Dialog dialog;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_manager);

		manager_nicheng1 = (RelativeLayout) findViewById(R.id.manager_nicheng1);
		manager_usn1 = (RelativeLayout) findViewById(R.id.manager_usn1);
		manager_tel1 = (RelativeLayout) findViewById(R.id.manager_tel1);
		manager_sex1 = (RelativeLayout) findViewById(R.id.manager_sex1);
		manager_address1 = (RelativeLayout) findViewById(R.id.manager_address1);
		manager_gexingqianming1 = (RelativeLayout) findViewById(R.id.manager_gexingqianming1);

		manager_nicheng1_content = (TextView) findViewById(R.id.manager_nicheng1_content);
		manager_usn1_content = (TextView) findViewById(R.id.manager_usn1_content);
		manager_tel1_content = (TextView) findViewById(R.id.manager_tel1_content);
		manager_sex1_content = (TextView) findViewById(R.id.manager_sex1_content);
		manager_address1_content = (TextView) findViewById(R.id.manager_address1_content);
		manager_gexingqianming1_content = (TextView) findViewById(R.id.manager_gexingqianming1_content);

		manager_return_btn = (ImageView) findViewById(R.id.manager_return_btn);

		manager_nicheng1.setOnClickListener(new OVClick());
		manager_usn1.setOnClickListener(new OVClick());
		manager_tel1.setOnClickListener(new OVClick());
		manager_sex1.setOnClickListener(new OVClick());
		manager_address1.setOnClickListener(new OVClick());
		manager_gexingqianming1.setOnClickListener(new OVClick());
		manager_return_btn.setOnClickListener(new OVClick());

		// 获取Intent
		Intent intent = getIntent();
		// 昵称
		String nicheng = intent.getStringExtra("nicheng");
		manager_nicheng1_content.setText(nicheng);
		// 电话
		String tel = intent.getStringExtra("tel");
		manager_tel1_content.setText(tel);
		// 地址
		String address = intent.getStringExtra("address");
		manager_address1_content.setText(address);
		// 个性签名
		String gexingqianming = intent.getStringExtra("gexingqianming");
		manager_gexingqianming1_content.setText(gexingqianming);
		messageAdd();

	}

	// 设置用户信息管理界面的信息
	public void messageAdd() {
		manager_nicheng1_content.setText(BaseActivity.logingUser.getNicheng());
		manager_usn1_content.setText(BaseActivity.logingUser.getAccount());
		manager_tel1_content.setText(BaseActivity.logingUser.getTel());
		manager_sex1_content.setText(BaseActivity.logingUser.getSex());
		manager_address1_content.setText(BaseActivity.logingUser.getAddress());
		manager_gexingqianming1_content.setText(BaseActivity.logingUser
				.getGexingqianming());
	}

	// 添加适配器，为用户选择男女的属性
	public void init() {
		final String[] arraySex = new String[] { "男", "女" };

		new AlertDialog.Builder(AdminManagerActivity.this)
				.setTitle("请选择性别")
				.setIcon(R.drawable.ic_launcher)
				.setItems(arraySex, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						IUpdateUserMessage iManagerService = new UserServiceImpl(
								AdminManagerActivity.this);
						BaseActivity.logingUser.setSex(arraySex[which]);
						iManagerService.updateSex(BaseActivity.logingUser);
						manager_sex1_content.setText(BaseActivity.logingUser
								.getSex());
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create().show();
	}

	class OVClick implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (view.getId()) {
			case R.id.manager_return_btn:
				AdminManagerActivity.this.finish();
				break;
			case R.id.manager_nicheng1:
				String nicheng1 = manager_nicheng1_content.getText().toString()
						.trim();
				intent = new Intent(AdminManagerActivity.this,
						NichengActivity.class);
				intent.putExtra("nicheng1", nicheng1);
				startActivity(intent);
				AdminManagerActivity.this.finish();
				break;
			case R.id.manager_usn1:
				Toast.makeText(AdminManagerActivity.this, "账号不可更改",
						Toast.LENGTH_LONG).show();
				break;
			case R.id.manager_tel1:
				String tel1 = manager_tel1_content.getText().toString().trim();
				intent = new Intent(AdminManagerActivity.this,
						TelActivity.class);
				intent.putExtra("tel1", tel1);
				startActivity(intent);
				AdminManagerActivity.this.finish();
				break;
			case R.id.manager_sex1:
				init();
				break;
			case R.id.manager_address1:
				String address1 = manager_address1_content.getText().toString()
						.trim();
				intent = new Intent(AdminManagerActivity.this,
						AddressActivity.class);
				intent.putExtra("address1", address1);
				startActivity(intent);
				AdminManagerActivity.this.finish();
				break;
			case R.id.manager_gexingqianming1:
				String gexingqianming1 = manager_gexingqianming1_content
						.getText().toString().trim();
				intent = new Intent(AdminManagerActivity.this,
						GexingqianmingActivity.class);
				intent.putExtra("gexingqianming1", gexingqianming1);
				startActivity(intent);
				AdminManagerActivity.this.finish();
				break;
			}
		}
	}
}
