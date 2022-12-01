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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UpdateActivity extends Activity {

	private LinearLayout llTime;
	private Gallery addGallery;
	private ImageView imgBack, imgSave;
	private EditText update_edtTitle, update_edtContent;
	private TextView update_time01, update_time02;

	private ITypeService typeservice;
	private IMemoService memoservice;

	private Memo creatememo;
	private Memo receivememo;

	// memo类型集合
	private List<Type> memotypes;

	// addGallery适配器源
	private List<Map<String, ?>> typelist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		typeservice = new TypeServiceImp(this);
		memoservice = new MemoServiceImp(this);
		creatememo = new Memo();

		// 获取控件
		llTime = (LinearLayout) findViewById(R.id.update_linear01);
		imgBack = (ImageView) findViewById(R.id.update_imgView_back);
		imgSave = (ImageView) findViewById(R.id.update_imgView_save);
		addGallery = (Gallery) findViewById(R.id.update_type_gallery);
		update_edtTitle = (EditText) findViewById(R.id.update_edtTitle);
		update_edtContent = (EditText) findViewById(R.id.update_edtContent);
		update_time01 = (TextView) findViewById(R.id.update_time01);
		update_time02 = (TextView) findViewById(R.id.update_time02);

		// 绑定控件监听
		llTime.setOnClickListener(new ViewOCL());
		imgBack.setOnClickListener(new ViewOCL());
		imgSave.setOnClickListener(new ViewOCL());

		// addGallery绑定适配器
		setaddGalleryAdapter();

		// 给gallery设置监听（内部类OnitemclickListen。。。）
		addGallery.setOnItemSelectedListener(new typeGalleryItemSelectListener());

		// 获取传入memo并设置界面中的数据
		setActivityDate();

		// 初始化creatememo
		creatememo = receivememo;

	}

	@Override
	protected void onRestart() {

		super.onRestart();

		SharedPreferences sharedPreferences = getSharedPreferences(
				"clearmemo_message", MODE_APPEND);

		String selectdate = sharedPreferences.getString("selectdate",
				getnowdate());

		update_time01.setText(getnowdate());
		update_time02.setText(selectdate);

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
	 * 获取传入memo并设置界面中的数据
	 */
	private void setActivityDate() {
		// 获取intent并取出memo对象
		Intent receiveintent = getIntent();
		// 获取该Intent所携带的数据
		Bundle bundle = receiveintent.getExtras();
		// 从bundle数据包中取出数据
		receivememo = (Memo) bundle.getSerializable("memoitem");

		// 设置标题
		update_edtTitle.setText(receivememo.getTitle());
		addGallery.setSelection(getMemosTypeIndexInmemotypes(receivememo));
		update_edtContent.setText(memoservice.getmemocontent(receivememo));
		update_time01.setText(receivememo.getCreatedate());
		update_time02.setText(receivememo.getClockdate());

	}

	/**
	 * 获取memotype在memotypes集合中的位置
	 * 
	 * @return
	 */
	private int getMemosTypeIndexInmemotypes(Memo memo) {
		for (int i = 0; i < memotypes.size(); i++) {
			if (memotypes.get(i).getId() == memo.getType_id()) {
				return i;
			}
		}
		return 0;

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

			switch (view.getId()) {
			case R.id.update_linear01:
				Intent intent = new Intent(UpdateActivity.this,
						TimeActivity.class);
				startActivity(intent);
				break;
			case R.id.update_imgView_back:
				UpdateActivity.this.finish();

				break;
			case R.id.update_imgView_save:
				// 保存
				// 通过控件获取memo信息，完成memo对象的属性的设置，memo对象传入后台保存数据库
				creatememo
						.setTitle(update_edtTitle.getText().toString().trim());
				creatememo.setContentsummary(update_edtContent.getText()
						.toString());
				
				creatememo.setUser_id(BaseActivity.logingUser.getId());
				creatememo.setCreatedate(getnowdate());

				String str = creatememo.getTitle();

				if (creatememo.getTitle().equals("")) {
					Toast.makeText(UpdateActivity.this, "Title不能为空",
							Toast.LENGTH_LONG).show();
				} else if (creatememo.getTitle().length() > 15) {
					Toast.makeText(UpdateActivity.this, "Title长度超过15",
							Toast.LENGTH_LONG).show();
				} else {

					memoservice.updateMemo(creatememo);

					UpdateActivity.this.finish();

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
