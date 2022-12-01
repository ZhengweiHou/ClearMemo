package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class AboutActivity extends Activity {

	private ImageView about_us_button, about_return_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		about_return_btn = (ImageView) findViewById(R.id.about_return_btn);
		about_us_button = (ImageView) findViewById(R.id.about_button);
		about_return_btn.setOnClickListener(new ViewOCL());
		about_us_button.setOnClickListener(new ViewOCL());
	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub"
			switch (view.getId()) {
			case R.id.about_return_btn:
				AboutActivity.this.finish();

				break;
			case R.id.about_button:
				final EditText edtName = new EditText(AboutActivity.this);
				new AlertDialog.Builder(AboutActivity.this)
						.setTitle("请输入想对我们说的话：")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(edtName)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Toast.makeText(
												getApplicationContext(),
												"提交成功",
												Toast.LENGTH_LONG).show();
									}
								}).setNegativeButton("取消", null).show();
				break;
			}

		}
	}
}
