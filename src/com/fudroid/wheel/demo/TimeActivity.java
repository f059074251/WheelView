package com.fudroid.wheel.demo;

import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TimePicker;

import com.fudroid.wheel.OnWheelChangedListener;
import com.fudroid.wheel.OnWheelClickedListener;
import com.fudroid.wheel.OnWheelScrollListener;
import com.fudroid.wheel.WheelView;
import com.fudroid.wheel.adapter.NumericWheelAdapter;

public class TimeActivity extends Activity {
	// Time changed flag
	private boolean timeChanged = false;

	// Time scrolled flag
	private boolean timeScrolled = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.time_layout);

		final WheelView hours = (WheelView) findViewById(R.id.hour);
		NumericWheelAdapter nw = new NumericWheelAdapter(this, 0, 23);
		nw.setTextColor(Color.GRAY);
		hours.setViewAdapter(nw);
		hours.setCyclic(true);

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		NumericWheelAdapter nwMin = new NumericWheelAdapter(this, 0, 59, "%02d");
		nwMin.setTextColor(Color.GRAY);
		mins.setViewAdapter(nwMin);
		mins.setCyclic(true);

		final TimePicker picker = (TimePicker) findViewById(R.id.time);
		picker.setIs24HourView(true);

		// set current time
		Calendar c = Calendar.getInstance();
		int curHours = c.get(Calendar.HOUR_OF_DAY);
		int curMinutes = c.get(Calendar.MINUTE);

		hours.setCurrentItem(curHours);
		mins.setCurrentItem(curMinutes);

		picker.setCurrentHour(curHours);
		picker.setCurrentMinute(curMinutes);

		// add listeners
		addChangingListener(mins, "min");
		addChangingListener(hours, "hour");

		OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!timeScrolled) {
					timeChanged = true;
					picker.setCurrentHour(hours.getCurrentItem());
					picker.setCurrentMinute(mins.getCurrentItem());
					timeChanged = false;
				}
			}
		};
		hours.addChangingListener(wheelListener);
		mins.addChangingListener(wheelListener);

		OnWheelClickedListener click = new OnWheelClickedListener() {
			public void onItemClicked(WheelView wheel, int itemIndex) {
				wheel.setCurrentItem(itemIndex, true);
			}
		};
		hours.addClickingListener(click);
		mins.addClickingListener(click);

		OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
				timeScrolled = true;
			}

			public void onScrollingFinished(WheelView wheel) {
				timeScrolled = false;
				timeChanged = true;
				picker.setCurrentHour(hours.getCurrentItem());
				picker.setCurrentMinute(mins.getCurrentItem());
				timeChanged = false;
			}
		};

		hours.addScrollingListener(scrollListener);
		mins.addScrollingListener(scrollListener);

		picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				if (!timeChanged) {
					hours.setCurrentItem(hourOfDay, true);
					mins.setCurrentItem(minute, true);
				}
			}
		});
	}

	/**
	 * Adds changing listener for wheel that updates the wheel label
	 * 
	 * @param wheel
	 *            the wheel
	 * @param label
	 *            the wheel label
	 */
	private void addChangingListener(final WheelView wheel, final String label) {
		wheel.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// wheel.setLabel(newValue != 1 ? label + "s" : label);
				System.out.println("#" + oldValue + " " + newValue);
			}
		});
	}
}
