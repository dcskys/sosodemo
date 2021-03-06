package com.tencent.example.location;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tencent.map.geolocation.TencentLocationManager;

public class SdkInfoActivity extends Activity {

	private LocationManager mLocationManager;
	private WifiManager mWifiManager;

	private TextView mTvBuild;
	private TextView mTvVersion;
	private TextView mTvKey;
	private TextView mGps;
	private TextView mWifi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdk_info);

		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		initUi();

		// 刷新 ui
		updateUi();
	}

	private void initUi() {
		mTvBuild = (TextView) findViewById(R.id.build);
		mTvVersion = (TextView) findViewById(R.id.version);
		mTvKey = (TextView) findViewById(R.id.key);
		mGps = (TextView) findViewById(R.id.gps);
		mWifi = (TextView) findViewById(R.id.wifi);
	}

	public void onClick(View view) {
		updateUi();
	}

	private void updateUi() {
		// 显示 build 号
		mTvBuild.setText(String.format(getString(R.string.build),
				TencentLocationManager.BUILD));

		// 显示 版本号
		mTvVersion.setText(String.format(getString(R.string.version),
				TencentLocationManager.VERSION));

		// 显示 key
		mTvKey.setText(String.format(getString(R.string.key),
				DemoUtils.getKey(this)));

		// 显示 gps 状态
		mGps.setText(String.format(getString(R.string.gps), mLocationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER) ? "开启" : "关闭"));

		// 显示 wifi 状态
		mWifi.setText(String.format(getString(R.string.wifi), mWifiManager
				.isWifiEnabled() ? "开启" : "关闭", isWifiConnected() ? "连接"
				: "未连接"));
	}

	private boolean isWifiConnected() {
		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
		return wifiInfo != null && wifiInfo.getBSSID() != null;
	}
}
