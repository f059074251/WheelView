package com.fudroid.wheel.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class WheelDemo extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new SimpleAdapter(this, getData(),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));
	}

	protected List getData() {
		List<Map> myData = new ArrayList<Map>();
		addItem(myData, CitiesActivity.class.getSimpleName(), new Intent(this,
				CitiesActivity.class));
		addItem(myData, DateActivity.class.getSimpleName(), new Intent(this,
				DateActivity.class));
		addItem(myData, PasswActivity.class.getSimpleName(), new Intent(this,
				PasswActivity.class));
		addItem(myData, SlotMachineActivity.class.getSimpleName(), new Intent(
				this, SlotMachineActivity.class));
		addItem(myData, SpeedActivity.class.getSimpleName(), new Intent(this,
				SpeedActivity.class));
		addItem(myData, Time2Activity.class.getSimpleName(), new Intent(this,
				Time2Activity.class));
		addItem(myData, TimeActivity.class.getSimpleName(), new Intent(this,
				TimeActivity.class));
		return myData;
	}

	protected void addItem(List<Map> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map map = (Map) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}
}
