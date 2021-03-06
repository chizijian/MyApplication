package com.tt.czj.photogallery.activity;


import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.photogallery.adapter.FolderAdapter;
import com.tt.czj.photogallery.util.PublicWay;
import com.tt.czj.photogallery.util.Res;
import com.tt.czj.ui.activity.base.BaseActivity;

/**
 * 这个类主要是用来进行显示包含图片的文件夹
 *
 * @author czj
 * @QQ:947431315
 */
public class ImageFile extends BaseActivity {

	private FolderAdapter folderAdapter;
	private Button bt_cancel;
	private Context mContext;

	@Override
	public int getContentViewId() {
		return R.layout.plugin_camera_image_file;
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		setContentView(R.layout.plugin_camera_image_file);
		PublicWay.activityList.add(this);
		mContext = this;
		bt_cancel = (Button) findViewById(Res.getWidgetID("cancel"));
		bt_cancel.setOnClickListener(new CancelListener());
		GridView gridView = (GridView) findViewById(Res.getWidgetID("fileGridView"));
		TextView textView = (TextView) findViewById(Res.getWidgetID("headerTitle"));
		textView.setText(Res.getString("photo"));
		folderAdapter = new FolderAdapter(this);
		gridView.setAdapter(folderAdapter);
	}

	@Override
	public void initData() {

	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
		public void onClick(View v) {
			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return true;
	}

}
