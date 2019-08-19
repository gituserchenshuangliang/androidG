package com.csl.demo;

import com.csl.demo.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 保存数据---SharedPreferences
 * @author Cherry
 * @date  2019年6月12日
 */
public class ActTwo extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_two);
		this.createListenButton(R.id.b3);
		this.createListenButton(R.id.b4);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b3:
			saveKeyValue();
			break;
		case R.id.b4:
			geetKeyValue();
			break;
		default:
			break;
		}
	}
	
	/*
	 * 从SharedPreferences读取数据
	 */
	private void geetKeyValue() {
		SharedPreferences sp = getSharedPreferences("demoB", Context.MODE_PRIVATE);
		int age = sp.getInt("age", 0);
		Toast.makeText(ActTwo.this, "年龄:"+age, Toast.LENGTH_SHORT).show();
	}
	/*
	 * 使用Editor类保存数据
	 */
	private void saveKeyValue() {
		SharedPreferences sp = getSharedPreferences("demoB", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("name", "Tom");
		editor.putInt("age", 28);
		editor.putBoolean("married", false);
		/*
		 * 提交数据
		 */
		editor.commit();
		Toast.makeText(ActTwo.this, "保存成功", Toast.LENGTH_SHORT).show();
	}
}
