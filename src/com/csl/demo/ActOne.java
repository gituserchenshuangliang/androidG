package com.csl.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.csl.demo.R;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 数据文件openInputFile();openOutputFile();
 * @author Cherry
 * @date  2019年6月12日
 */
public class ActOne extends BaseActivity {
	private EditText output;
	private EditText output2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_one);
		this.createListenButton(R.id.b1);
		this.createListenButton(R.id.b2);
		output = this.getEditTextObject(R.id.output);
		output2 = this.getEditTextObject(R.id.output2);
		autoInput("demoA");
	}
	
	/*
	 * 自动填入
	 */
	private void autoInput(String string) {
		String txt = openFile(string);
		if(!TextUtils.isEmpty(txt)){
			output2.setText(txt);
			output2.setSelection(txt.length());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b1:
			saveToFile(output);
			Toast.makeText(ActOne.this, "文件保存成功！", Toast.LENGTH_SHORT).show();
			break;
		case R.id.b2:
			String fileName = "demoA";
			String txt = openFile(fileName);
			Toast.makeText(ActOne.this, "读取文件："+txt, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	/*
	 * 读取本地文件
	 */
	private String openFile(String fileName) {
		FileInputStream fis = null;
		BufferedReader bw = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = openFileInput(fileName);
			bw = new BufferedReader(new InputStreamReader(fis));
			String line = "";
			while((line = bw.readLine()) != null){
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/*
	 * 保存文本到文件
	 */
	private void saveToFile(EditText tr2) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		String data = tr2.getText().toString();
		try {
			//fos = openFileOutput("demoA", Context.MODE_APPEND);
			fos = openFileOutput("demoA", Context.MODE_PRIVATE);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
