package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dbutils.entity.UserEntity;
import com.example.xutilsdemo.R;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class DbUtilsActity extends Activity {
	DbUtils dbUtils;
	@ViewInject(R.id.db_name)
	private TextView dataName;
	private List<UserEntity> list = new ArrayList<UserEntity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_layout);
		ViewUtils.inject(this);
		// 创建数据库
		dbUtils = DbUtils.create(this, "dbUtils");

	}

	// 初始化数据
	public void setUserEntity() {
		for (int i = 0; i < 10; i++) {
			UserEntity user = new UserEntity();
			user.setId(1);
			user.setContent("哈哈哈哈哈哈哈哈哈哈哈");
			user.setName("李官官");
			user.setHight(170);
			user.setAge(19 + i);
			list.add(user);

		}

	}

	// 创建数据表 并且添加数据
	public void creatTable() {
		setUserEntity();
		try {
			for (int i = 0; i < list.size(); i++) {
				dbUtils.save(list.get(i));
			}

		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	// 查询数据
	public List<UserEntity> findUserEntity() {
		List<UserEntity> user = new ArrayList<UserEntity>();
		try {
			// 按条件查找数据
			// user =
			// dbUtils.findAll(Selector.from(UserEntity.class).where("name",
			// "=", "李官官"));
			// 查询所有数据
			user = dbUtils.findAll(UserEntity.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return user;
	}

	// 删除数据
	public void deleteUserEntity() {
		try {
			// 按条件删除数据
			dbUtils.delete(UserEntity.class, WhereBuilder.b("age", "=", "19"));
			// 删除所有数据
//			dbUtils.deleteAll(UserEntity.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	// 更新数据
	public void updateUserEntity() {
		UserEntity user = new UserEntity();
		user.setName("dfasf");
		user.setContent("heiheiheiehi");
		try {
			// 按照条件修改数据
			dbUtils.update(user, WhereBuilder.b("id", "=", "9"), new String[] {
					"name", "content" });
			// 修改所有的值
			// dbUtils.update(user, new String[]{"name","content"});
		} catch (DbException e) {
			LogUtils.e(e.getMessage());
			e.printStackTrace();
		}
	}

	@OnClick({ R.id.db_save_btn, R.id.db_select_btn, R.id.db_delete_btn,
			R.id.db_update_btn })
	private void operationTableDate(View v) {
		switch (v.getId()) {
		case R.id.db_save_btn:
			creatTable();
			break;
		case R.id.db_select_btn:
			String result = findUserEntity().toString();
			if (result != null) {
				dataName.setText(result);
			} else {
				LogUtils.e("数据是空的");
			}
			break;
		case R.id.db_delete_btn:
			deleteUserEntity();
			break;
		case R.id.db_update_btn:
			updateUserEntity();
			break;
		}
	}

}
