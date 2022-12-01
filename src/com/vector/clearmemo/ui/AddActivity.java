package com.vector.clearmemo.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.firstandroidprojectclearmemo.R;
import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.domain.po.Memo;
import com.vector.clearmemo.domain.po.Type;
import com.vector.clearmemo.service.IMemoService;
import com.vector.clearmemo.service.ITypeService;
import com.vector.clearmemo.service.impl.MemoServiceImp;
import com.vector.clearmemo.service.impl.TypeServiceImp;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class AddActivity extends Activity {

	private ImageView add_imgView_back, add_imgView_save;
	private TextView time02, time01;
	private EditText add_edtTitle, add_edtContent;
	private LinearLayout llTime;
	private Gallery addGallery;

	private ITypeService typeservice;
	private IMemoService memoservice;

	private Memo creatememo;

	// memo类型集合
	private List<Type> memotypes;

	// addGallery适配器源
	private List<Map<String, ?>> typelist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		typeservice = new TypeServiceImp(this);
		memoservice = new MemoServiceImp(this);
		creatememo = new Memo();

		// 获取控件
		add_imgView_back = (ImageView) findViewById(R.id.add_imgView_back);
		add_imgView_save = (ImageView) findViewById(R.id.add_imgView_save);
		llTime = (LinearLayout) findViewById(R.id.add_linear01);
		addGallery = (Gallery) findViewById(R.id.add_type_gallery);
		time02 = (TextView) findViewById(R.id.time02);
		time01 = (TextView) findViewById(R.id.time01);
		add_edtTitle = (EditText) findViewById(R.id.add_edtTitle);
		add_edtContent = (EditText) findViewById(R.id.add_edtContent);

		// 绑定控件监听
		llTime.setOnClickListener(new ViewOCL());
		add_imgView_back.setOnClickListener(new ViewOCL());
		add_imgView_save.setOnClickListener(new ViewOCL());

		// addGallery绑定适配器
		setaddGalleryAdapter();

		// 给gallery设置监听（内部类OnitemclickListen。。。）
		addGallery
				.setOnItemSelectedListener(new typeGalleryItemSelectListener());
		
		// //////////////////////////////////////
		Intent receiveintent = getIntent();
		Bundle bundle = receiveintent.getExtras();
		int typeindex = bundle.getInt("typeindex");
		addGallery.setSelection(typeindex);

		// ///////////////////////////////////////
		// // 获取该Intent所携带的数据
		// Bundle bundle = receiveintent.getExtras();
		// // // 从bundle数据包中取出数据
		// Memo receivememo = (Memo) bundle.getgetSerializable("memoitem");

		String nowdate = new SimpleDateFormat("HH:mm").format(new Date());
		time01.setText(nowdate);

		time02.setText(getnowdate());

		creatememo.setClockdate(getnowdate());
		creatememo.setType_id(memotypes.get(0).getId());
	}

	@Override
	protected void onRestart() {

		super.onRestart();

		SharedPreferences sharedPreferences = getSharedPreferences(
				"clearmemo_message", MODE_APPEND);

		String selectdate = sharedPreferences.getString("selectdate",
				getnowdate());

		time01.setText(getnowdate());
		time02.setText(selectdate);

		creatememo.setClockdate(selectdate);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return YYYY-MM-DD hh：mm
	 */
	private String getnowdate() {

		String nowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date());
		return nowdate;

	}

	/**
	 * 给addGallery添加适配器
	 */
	private void setaddGalleryAdapter() {
		// 设置适配器SimpleAdapter（添加一个适配器item布局文件）

		// 获取数据库类型数据
		memotypes = typeservice.selectType();

		typelist = new ArrayList<Map<String, ?>>();

		for (Type type : memotypes) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("typeImg", type.getIcon());
			typelist.add(item);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, typelist,
				R.layout.typegallery_item, new String[] { "typeImg" },
				new int[] { R.id.typeitem_img });

		// 给gallery绑定适配器
		addGallery.setAdapter(adapter);

	}

	class ViewOCL implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (view.getId()) {
			case R.id.add_linear01:
				intent = new Intent(AddActivity.this, TimeActivity.class);
				startActivity(intent);
				break;
			case R.id.add_imgView_back:
				intent = new Intent(AddActivity.this, MainActivity.class);
				startActivity(intent);
				break;
			case R.id.add_imgView_save:
				// 保存
				// 通过控件获取memo信息，完成memo对象的属性的设置，memo对象传入后台保存数据库
				creatememo.setTitle(add_edtTitle.getText().toString().trim());
				creatememo.setContentsummary(add_edtContent.getText()
						.toString());
				creatememo.setUser_id(BaseActivity.logingUser.getId());
				creatememo.setCreatedate(getnowdate());

				String str = creatememo.getTitle();

				if (creatememo.getTitle().equals("")) {
					Toast.makeText(AddActivity.this, "Title不能为空",
							Toast.LENGTH_LONG).show();
				} else if (creatememo.getTitle().length() > 15) {
					Toast.makeText(AddActivity.this, "Title长度超过15",
							Toast.LENGTH_LONG).show();
				} else {

					memoservice.addMemo(creatememo);

					AddActivity.this.finish();

				}

				break;
			}
		}
	}

	class typeGalleryItemSelectListener implements
			AdapterView.OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			creatememo.setType_id(memotypes.get(arg2).getId());

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}
}
