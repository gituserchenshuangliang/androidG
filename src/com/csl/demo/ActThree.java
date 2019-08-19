package com.csl.demo;

import com.csl.demo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 保存数据---SharedPreferences
 * @author Cherry
 * @date  2019年6月12日
 */
public class ActThree extends BaseActivity {
	private SharedPreferences sp;
	private  EditText user;
	private EditText pass;
	private CheckBox check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_three);
		sp = getSharedPreferences("password", Context.MODE_PRIVATE);
		this.createListenButton(R.id.login);
		user = this.getEditTextObject(R.id.user);
		pass = this.getEditTextObject(R.id.pass);
		check = (CheckBox) findViewById(R.id.check);
		autoInput();
	}
	
	private void autoInput() {
		sp = getSharedPreferences("password", Context.MODE_PRIVATE);
		boolean isSave = sp.getBoolean("remenber", false);
		if(isSave){
			user.setText(sp.getString("username", ""));
			pass.setText(sp.getString("password", ""));
			check.setChecked(isSave);
		}
	}

	@SuppressLint("CommitPrefEdits")
	@Override
	public void onClick(View v) {
		String username = user.getText().toString();
		String password = pass.getText().toString();
		boolean remenber = check.isChecked();
		if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
			if("chen".equals(username)&&"chen".equals(password)){
				Toast.makeText(ActThree.this, "登录成功", Toast.LENGTH_SHORT).show();
				Editor editor = sp.edit();
				if(remenber){
					editor.putString("username", username);
					editor.putString("password", password);
					editor.putBoolean("remenber", remenber);
				}else{
					editor.clear();
				}
				editor.commit();
			}
		}else{
			Toast.makeText(ActThree.this, "账号密码不正确！", Toast.LENGTH_SHORT).show();
		}
	}
}
