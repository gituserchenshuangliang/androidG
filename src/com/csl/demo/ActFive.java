package com.csl.demo;

import java.util.ArrayList;

import com.csl.demo.R;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 获取手机联系人
 * @author Cherry
 * @date 2019年6月13日
 */
public class ActFive extends BaseActivity {
	private ListView listView;
	private ConactsAdapter adp;
	private ArrayList<Conacts> list = new ArrayList<Conacts>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_five);
		listView = (ListView) findViewById(R.id.con);
		initConact();
		adp = new ConactsAdapter(this, R.layout.contacts_list, list);
		listView.setAdapter(adp);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Conacts con = list.get(position);
				Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+con.getPhone()));
				startActivity(intent);
			}
		});
	}

	private void initConact() {
		Cursor cursor = null;
		try {
			cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
					null);
			while (cursor.moveToNext()) {
				String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				Conacts c = new Conacts(displayName , number);
				list.add(c);
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
