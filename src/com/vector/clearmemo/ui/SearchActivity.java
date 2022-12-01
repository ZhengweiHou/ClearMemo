package com.vector.clearmemo.ui;

import java.util.ArrayList;
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
import com.vector.clearmemo.ui.MainActivity.ListItemOCL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class SearchActivity extends Activity {

	private EditText searchinput;
	private ListView searchadaptermemolistview;

	private List<Memo> memolist;
	private List<Map<String, ?>> adaptermemolist;
	private List<Type> memotypes;
	private IMemoService memoService;
	private ITypeService typeService;

	private String searchinputcontent;

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		setadaptermemolist();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);

		typeService = new TypeServiceImp(this);
		memoService = new MemoServiceImp(this);

		searchinput = (EditText) findViewById(R.id.search_edt_searchinput);
		searchadaptermemolistview = (ListView) findViewById(R.id.search_listview_searchmemo);

		searchinput.addTextChangedListener(new SearchTextChangeListener());

		// 获取所需数据库内容
		this.setneeddbdate();

		searchadaptermemolistview.setOnItemClickListener(new ListItemOCL());
		searchadaptermemolistview.setOnItemLongClickListener(new ListItemOCL());

		setadaptermemolist();
	}

	// 获取所需数据库内容
	private void setneeddbdate() {
		// 获取分类
		memotypes = typeService.selectType();
		// 获取当前用户的所有备忘录
		memolist = memoService.selectMemo(BaseActivity.logingUser.getId());

	}

	// 标题栏按钮点击事件
	public void titleonclick(View view) {
		switch (view.getId()) {
		case R.id.search_imgb_searchbreak:
			// 返回
			this.finish();

			break;
		case R.id.search_imgb_searchinputdelet:
			// 删除输入
			searchinput.setText("");

			break;

		default:
			break;
		}
	}

	// search输入改变监听
	class SearchTextChangeListener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable arg0) {

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			searchinputcontent = searchinput.getText().toString().trim();

			setadaptermemolist();

		}

	}

	// ListView memo条目点击监听
	class ListItemOCL implements OnItemClickListener, OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {

			memoitemOnItemLongClick(arg2);

			return true;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			// 跳转到展示界面，携带选择的memo对象

			jumpToActivity(arg2, DetailActivity.class);

		}

	}

	// memoitem长按监听弹框
	private void memoitemOnItemLongClick(final int itemindex) {

		Map<String, ?> item = adaptermemolist.get(itemindex);

		// ///////////
		new AlertDialog.Builder(this)
				.setTitle("选择对" + item.get("title") + "的操作")

				.setItems(new String[] { "删除", "修改" },
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									// 确认删除
									itemdeletensure(itemindex);

									break;
								case 1:
									// 修改
									itemEdit(itemindex);
									break;
								default:
									break;
								}

							}
						}).setNegativeButton("取消", null).show();
	}

	// 确认删除memo
	private void itemdeletensure(final int itemindex) {

		new AlertDialog.Builder(this).setTitle("确定删除？")
				.setMessage("您确定删除该条信息吗？").setIcon(R.drawable.ic_launcher)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						memoService.deleteMemo(memolist.get(itemindex).getId());

						setadaptermemolist();
					}

				}).setNegativeButton("取消", null).show();
	}

	// 跳转修改界面，传入当前选择条目
	private void itemEdit(int itemindex) {

		// 跳转到编辑界面，携带选择的memo对象
		this.jumpToActivity(itemindex, UpdateActivity.class);

	}

	/**
	 * 跳转方法
	 * 
	 * @author HZW_922
	 * 
	 * @param itemindex
	 * @param toActivity
	 */
	private void jumpToActivity(int itemindex, Class toActivity) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("memoitem", memolist.get(itemindex));
		Intent intent = new Intent(SearchActivity.this, toActivity);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	// 根据查找内容设置ListView适配器
	private void setadaptermemolist() {

		memolist = memoService.selectMemoBySearchContent(searchinputcontent);

		adaptermemolist = new ArrayList<Map<String, ?>>();
		for (int i = 0; i < memolist.size(); i++) {
			Type type = null;

			for (Type typetemp : memotypes) {
				if (typetemp.getId() == memolist.get(i).getType_id()) {
					type = typetemp;
					break;
				}
			}
			String title = null;
			if (memolist.get(i).getTitle().length() > 7) {
				title = memolist.get(i).getTitle().substring(0, 6) + "...";
			} else {
				title = memolist.get(i).getTitle();
			}

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("type", type.getIcon());
			item.put("title", title);
			item.put("createdate", memolist.get(i).getCreatedate());
			item.put("content", memolist.get(i).getContentsummary());

			adaptermemolist.add(item);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, adaptermemolist,
				R.layout.listitem_style1, new String[] { "type", "title",
						"createdate", "content" }, new int[] {
						R.id.memoitem_img_memotype,
						R.id.txtview_memoitem_title,
						R.id.txtview_memoitem_date,
						R.id.txtview_memoitem_context });

		searchadaptermemolistview.setAdapter(adapter);
	}
}
