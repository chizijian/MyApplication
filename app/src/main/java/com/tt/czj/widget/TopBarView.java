package com.tt.czj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tt.czj.R;


/**
 * 头部bar
 * Created by Explorer on 2016/4/5.
 */
public class TopBarView extends LinearLayout {

	private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";
	private ImageView ivBack;
	private TextView tvTitle;
	private TextView tvMidTitle;
	private TextView tvSubTitle;

	private int topIcon;
	private String mTitle;
	private String mMidTitle;
	private String mSubTitle;
	private int iconVisibity;


    /**
     * Instantiates a new Top bar view.
     *
     * @param context the context
     */
    public TopBarView(Context context) {
		super(context);
		initViews();
	}

    /**
     * Instantiates a new Top bar view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public TopBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTitle = attrs.getAttributeValue(NAMESPACE, "topTitle");
		mSubTitle = attrs.getAttributeValue(NAMESPACE, "topSubtitle");
		mMidTitle = attrs.getAttributeValue(NAMESPACE, "topMidTitle");
		topIcon = attrs.getAttributeResourceValue(NAMESPACE, "topIcon", R.mipmap.back);
//		iconVisibity = attrs.getAttributeIntValue(NAMESPACE, "iconVisibity", View.INVISIBLE);
		initViews();
	}

    /**
     * Instantiates a new Top bar view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews();
	}

	private void initViews() {
		// 将自定义好的布局文件设置给当前的TopBarView
		View.inflate(getContext(), R.layout.top_bar, this);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvMidTitle = (TextView) findViewById(R.id.tv_mid_title);
		tvSubTitle = (TextView) findViewById(R.id.tv_subtitle);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivBack.setImageResource(topIcon);
//		ivBack.setVisibility(iconVisibity);
		ivBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mIconListener.onClickIcon();
			}
		});

//		tvSubTitle.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mListener.onClickSubTitle();
//			}
//		});

		setTitle(mTitle);
		setMidTitle(mMidTitle);
		setSubTitle(mSubTitle);
	}

    /**
     * The M listener.
     */
    OnSubTitleListener mListener;

    /**
     * Sets on sub title listener.
     *
     * @param subTitleListener the sub title listener
     */
    public void setOnSubTitleListener(OnSubTitleListener subTitleListener) {
		mListener = subTitleListener;
	}

    /**
     * The interface On sub title listener.
     */
    public interface OnSubTitleListener {
        /**
         * On click sub title.
         */
        public void onClickSubTitle();
	}

    /**
     * The M icon listener.
     */
    OnIconClickListener mIconListener;

    /**
     * Sets on icon click listener.
     *
     * @param iconClickListener the icon click listener
     */
    public void setOnIconClickListener(OnIconClickListener iconClickListener) {
		mIconListener = iconClickListener;
	}


    /**
     * The interface On icon click listener.
     */
    public interface OnIconClickListener {
        /**
         * On click icon.
         */
        public void onClickIcon();
	}


    /**
     * Sets sub title.
     *
     * @param subTitle the sub title
     */
    public void setSubTitle(String subTitle) {
		if (subTitle != null) {
			tvSubTitle.setText(subTitle);
		}
	}

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
		if (title != null) {
			tvTitle.setText(title);
		}
	}

    /**
     * Sets mid title.
     *
     * @param title the title
     */
    public void setMidTitle(String title) {
		if (title != null) {
			tvMidTitle.setText(title);
		}
	}

}
