package com.vector.clearmemo.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.vector.clearmemo.dao.IMemoDao;
import com.vector.clearmemo.dao.impl.MemoDaoImp;
import com.vector.clearmemo.domain.po.Memo;
import com.vector.clearmemo.service.IMemoService;

public class MemoServiceImp implements IMemoService {
	IMemoDao memodao;
	Context context;

	public MemoServiceImp(Context context) {
		super();
		this.memodao = new MemoDaoImp(context);
		this.context = context;
	}

	@Override
	public void addMemo(Memo memo) {
		memodao.insertmemo(this.saveMemoContentAndgetDbMemo(memo));

	}

	@Override
	public List<Memo> selectMemo(int uid) {

		return memodao.selectMemoByUid(uid);
	}

	@Override
	public void updateMemo(Memo memo) {

		Memo dbmemo = this.saveMemoContentAndgetDbMemo(memo);
		memodao.updatememo(dbmemo);
	}

	@Override
	public void deleteMemo(int id) {
		memodao.deleteMemoByid(id);

	}

	@Override
	public String getmemocontent(Memo memo) {
		String content = "";
		try {
			FileInputStream fis = context.openFileInput(memo.getPath());

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String buffer;
			while ((buffer = br.readLine()) != null)
				content += buffer + "\n";
			br.close();
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	@Override
	public List<Memo> selectMemobyuidandtype(int uid, int typeid) {
		return memodao.selectMemoByUidAndTypeid(uid, typeid);

	}

	private Memo saveMemoContentAndgetDbMemo(Memo memo) {
		// 将memo内容存放到一个私有文件中，取内容的前十个字段作为内容摘要，替换内容，调用dao层存储到数据库中
		// 取出内容
		String content = memo.getContentsummary();

		String contentTemp = content.replaceAll("\\s*", "");

		if (contentTemp.length() > 13) {
			// 截取内容摘要
			memo.setContentsummary(contentTemp.substring(0, 10) + "......");
		} else {
			memo.setContentsummary(contentTemp.toString());
		}

		// 将文件存到对应文件中(文件名由当前时间生成),
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 设置memo的创建时间
		String createdate = df.format(new Date());

		memo.setCreatedate(createdate);

		if (memo.getPath() == null) {
			// 文件位置
			String path = "memo-" + new Date().toString() + ".txt";
			// 设置memo中的path属性
			memo.setPath(path);
		}

		try {
			// 获取输出流
			FileOutputStream fos = context.openFileOutput(memo.getPath(),
					Context.MODE_APPEND);
			// 通过输出流写入content内容
			fos.write(content.getBytes());
			// flush一下输出流将输出流缓存写入文件
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memo;
	}

	@Override
	public List<Memo> selectMemoBySearchContent(String searchinputcontent) {

		return memodao.selectMemoSearchContent(searchinputcontent);
	}

	@Override
	public Memo selectMemobyid(int id) {
		// TODO Auto-generated method stub
		return memodao.selectMemoByid(id);
	}

}
