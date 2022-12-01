package com.vector.clearmemo.ui;

import java.io.Serializable;
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 定义控件
	private SlidingMenu mMenu;
	private ListView mainadaptermemolistView;
	private ImageView slimenutypeimg, main_imgv_userphone;
	private TextView slimenutypecontent, main_useraccount;
	public static ImageView showSlid;
	boolean isChanged = false;

	// 备忘录记录
	private List<Memo> memolist;
	private List<Map<String, ?>> adaptermemolist;
	// 选择类型的下标
	private int selectedmemotypeIndex = 0;
	// 备忘类型
	private List<Type> memotypes;
	// service
	private ITypeService typeService;
	private IMemoService memoService;

	@Override
	protected void onRestart() {

		super.onRestart();

		main_useraccount.setText(BaseActivity.logingUser.getNicheng());

		this.updateListViewContent();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 获取控件
		this.findView();

		typeService = new TypeServiceImp(this);
		memoService = new MemoServiceImp(this);

		main_useraccount.setText(BaseActivity.logingUser.getNicheng());

		// 获取所需数据库内容
		this.setneeddbdate();

		// 设置适配器数据源
		this.updateListViewContent();

		mainadaptermemolistView.setOnItemClickListener(new ListItemOCL());
		mainadaptermemolistView.setOnItemLongClickListener(new ListItemOCL());

	}

	// 获取控件
	private void findView() {
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		mainadaptermemolistView = (ListView) findViewById(R.id.main_listview_memoitem);
		slimenutypeimg = (ImageView) findViewById(R.id.main_imgv_slimenutypeimg);
		slimenutypecontent = (TextView) findViewById(R.id.main_txt_slimenutypecontent);
		main_imgv_userphone = (ImageView) findViewById(R.id.main_imgv_userphone);
		main_useraccount = (TextView) findViewById(R.id.main_useraccount);
		showSlid = (ImageView) findViewById(R.id.main_showsolidmenu);
	}

	// 获取所需数据库内容
	private void setneeddbdate() {
		// 获取分类
		memotypes = typeService.selectType();
		// 获取当前用户的所有备忘录
		memolist = memoService.selectMemo(BaseActivity.logingUser.getId());

	}

	// 通过制定memo集合设置ListView适配器
	private void setadaptermemolist(List<Memo> memolist) {
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

		mainadaptermemolistView.setAdapter(adapter);
	}

	// 布局默认单击事件
	public void onclick(View view) {

		switch (view.getId()) {
		case R.id.main_showsolidmenu:

			if (isChanged) {
				mMenu.toggle();
				showSlid.setImageResource(R.drawable.slipmenu2);
			} else {
				mMenu.toggle();
				showSlid.setImageResource(R.drawable.slipmenu1);
			}

			isChanged = !isChanged;

			break;

		// 账户
		case R.id.main_usermessage:
			Intent intent = new Intent(MainActivity.this,
					AdminManagerActivity.class);
			startActivity(intent);
			break;
		case R.id.main_selecttype:
			// 选择记录类型
			selectType();
			break;
		case R.id.main_search:
			// 搜索
			startActivity(new Intent(MainActivity.this, SearchActivity.class));
			break;
		case R.id.main_aboutuse:
			// 关于我们
			startActivity(new Intent(MainActivity.this, AboutActivity.class));
			break;
		case R.id.main_zhuxiao:
			// 注销
			this.logout();
			break;
		case R.id.main_exit:
			// 退出
			this.finish();
			break;

		case R.id.main_imgbtn_addmemo:

			// 添加备忘
			// ////////////////////////////////////////////
			intent = new Intent(MainActivity.this, AddActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("typeindex", selectedmemotypeIndex - 1);
			intent.putExtras(bundle);

			// ////////////////////////////////////
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	/**
	 * 注销方法
	 */
	private void logout() {
		BaseActivity.logingUser = null;
		startActivity(new Intent(MainActivity.this, LoginActivity.class));
		this.finish();

	}

	// 选择类型方法
	private void selectType() {
		// ////////////////初始化类型
		// final String[] arrayType = new String[] { "All", "学习", "工作", "生活" };

		String[] arrayType = new String[memotypes.size() + 1];
		arrayType[0] = "All";

		for (int i = 1; i < arrayType.length; i++) {
			arrayType[i] = memotypes.get(i - 1).getName();
		}

		Dialog dialog = new AlertDialog.Builder(this)
				.setTitle("选择备忘类型")
				.setSingleChoiceItems(arrayType, selectedmemotypeIndex,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								selectedmemotypeIndex = which;
								updateListViewContent();
							}
						}).setPositiveButton("确认", null).create();

		if (dialog != null) {
			dialog.show();
			dialog = null;
		}
	}

	/**
	 * 更新当前界面中显示的memo内容
	 */
	private void updateListViewContent() {

		if (selectedmemotypeIndex == 0) {
			memolist = memoService.selectMemo(BaseActivity.logingUser.getId());
			slimenutypeimg.setImageResource(R.drawable.allmemo);
			slimenutypecontent.setText("All");

		} else {
			memolist = memoService.selectMemobyuidandtype(
					BaseActivity.logingUser.getId(),
					memotypes.get(selectedmemotypeIndex - 1).getId());

			slimenutypeimg.setImageResource(memotypes.get(
					selectedmemotypeIndex - 1).getIcon());
			slimenutypecontent.setText(memotypes.get(selectedmemotypeIndex - 1)
					.getName());

		}
		// 更新ListView适配器
		setadaptermemolist(memolist);
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

						updateListViewContent();
					}

				}).setNegativeButton("取消", null).show();
	}

	// 跳转修改界面，传入当前选择条目
	private void itemEdit(int itemindex) {

		// 跳转到编辑界面，携带选择的memo对象
		this.jumpToActivity(itemindex, UpdateActivity.class);

	}

	/**
	 * 主界面跳转方法
	 * 
	 * @author HZW_922
	 * 
	 * @param itemindex
	 * @param toActivity
	 */
	private void jumpToActivity(int itemindex, Class toActivity) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("memoitem", memolist.get(itemindex));
		Intent intent = new Intent(MainActivity.this, toActivity);
		intent.putExtras(bundle);
		startActivity(intent);
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

	public void toggleMenu(View view) {
		mMenu.toggle();
	}
}
