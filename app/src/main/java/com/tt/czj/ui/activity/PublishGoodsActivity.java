package com.tt.czj.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.tt.czj.R;
import com.tt.czj.R2;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerPublishActivityComponent;
import com.tt.czj.di.modules.PublishActivityModule;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.presenter.PublishActivityPresenter;
import com.tt.czj.mvp.views.PublishView;
import com.tt.czj.photogallery.util.Bimp;
import com.tt.czj.photogallery.util.ImageItem;
import com.tt.czj.photogallery.util.Res;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.PublishGoodsGridAdapter;
import com.tt.czj.utils.GlobalDefineValues;
import com.tt.czj.utils.ToastUtil;
import com.tt.czj.utils.ToolsUtils;
import com.tt.czj.widget.LoadingDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


/**
 * 首页面activity
 *
 * @version 2014年10月18日 下午11:48:34
 */
@SuppressLint("HandlerLeak")
public class PublishGoodsActivity extends BaseActivity implements PublishView, TakePhoto.TakeResultListener, InvokeListener {
    /**
     * The No scrollgridview.
     */
    @BindView(R2.id.noScrollgridview)
    GridView noScrollgridview;
    /**
     * The M goods kind text.
     */
    @BindView(R2.id.goods_kind_text)
    TextView mGoodsKindText;
    private PublishGoodsGridAdapter adapter;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    /**
     * The constant bimap.
     */
    public static Bitmap bimap;
    /**
     * The M publish goods text view.
     */
    @BindView(R2.id.activity_selectimg_send)
    TextView mPublishGoodsTextView;
    /**
     * The M goods description.
     */
    @BindView(R2.id.word_message)
    EditText mGoodsDescription;
    /**
     * The M goods title.
     */
    @BindView(R2.id.title)
    EditText mGoodsTitle;
    /**
     * The M goods price.
     */
    @BindView(R2.id.price)
    EditText mGoodsPrice;
    /**
     * The M goods new degree.
     */
    @BindView(R2.id.new_degree)
    EditText mGoodsNewDegree;
    /**
     * The M goods location.
     */
    @BindView(R2.id.location)
    TextView mGoodsLocation;
    /**
     * The M goods mount.
     */
    @BindView(R2.id.mount)
    EditText mGoodsMount;
    /**
     * The M goods kind.
     */
    @BindView(R2.id.kind)
    RelativeLayout mGoodsKind;
    /**
     * The Market pub.
     */
    @BindView(R2.id.market_pub_category_spinner)
    Spinner market_pub;

    /**
     * The M publish xiaoqu.
     */
    @BindView(R2.id.publish_xiaoqu)
    Spinner mPublish_xiaoqu;

    /**
     * The Xiaoqu list.
     */
    @BindArray(R2.array.xiaoqu)
    String[] xiaoquList;

    // private Uri photoUri; //得到清晰的图片
    private LoadingDialog mLoadingDialog;
    /**
     * The Presenter.
     */
    @Inject
    PublishActivityPresenter presenter;
    private String mCurrentLocation;
    private String mCurrentPrince;
    /**
     * The M loc client.
     */
// 定位相关
    LocationClient mLocClient;
    /**
     * The My listener.
     */
    public MyLocationListenner myListener = new MyLocationListenner();
    //private MyLocationConfiguration.LocationMode mCurrentMode;
    private String kind, secondkind;
    private static final int TAKE_PICTURE = 1;
    private boolean qiugou = false;


    private String source = null;//发布商品或回复求购
    private Goods qiugou_goods;//求购的商品
    private Boolean is_qiugou_seller=false;//是否为求购商品回复
    private String qiugou_goods_id=null;//求购商品id
    private String xiaoqu;//校区

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private final int MAX_SELECT = 9;//照片最大选择数
    private int HAS_SELECT = 0;//已选择图片数

    @Override
    public int getContentViewId() {
        return R.layout.activity_selectimg;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Res.init(this);
        bimap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused);
        initBaiduLocation();
        setupComponent(((MyApp) getApplicationContext()).getAppComponent());
        pop = new PopupWindow(PublishGoodsActivity.this);
    }

    /**
     * Init baidu location.
     */
    public void initBaiduLocation() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);  //开启位置信息包括city
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R2.id.kind})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.kind:
                if (ToolsUtils.isNullOrEmpty(source))
                  processChooseKind();//选择分类
                else {
                    ToastUtil.showToast(getApplicationContext(),"不可修改分类");
                }
                break;
        }
    }

    /**
     * On item selected.
     *
     * @param position the position
     */
    @OnItemSelected(R2.id.publish_xiaoqu)
    void onItemSelected(int position) {
        xiaoqu = mPublish_xiaoqu.getItemAtPosition(position).toString();
        Log.e(TAG, "onItemSelected: xiaoqu=" + xiaoqu);
    }

    /**
     * Process choose kind.
     */
    public void processChooseKind() {
        Intent intent = new Intent(this, AllKindActivity.class);
        startActivityForResult(intent, GlobalDefineValues.CHOOSE_KIND);
    }

    @Override
    public void initData() {

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            qiugou = bundle.containsKey("type") && bundle.getBoolean("type");
            if (bundle.containsKey("source") && bundle.containsKey("Goods")) {
                source = bundle.getString("source");
                qiugou_goods = (Goods) getIntent().getSerializableExtra("Goods");
                qiugou_goods_id=qiugou_goods.getObjectId();
                is_qiugou_seller=true;

                kind = qiugou_goods.getKind();
                secondkind = qiugou_goods.getSecondkind();
                mGoodsKindText.setText(kind + "  " + secondkind);

                //Log.e(TAG, "initData: source=" + source);
            }
        }
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);
        ll_popup = ButterKnife.findById(view, R.id.ll_popup);
        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = ButterKnife.findById(view, R.id.parent);
        Button bt1 = ButterKnife.findById(view, R.id.item_popupwindows_camera);
        Button bt2 = ButterKnife.findById(view, R.id.item_popupwindows_Photo);
        Button bt3 = ButterKnife.findById(view, R.id.item_popupwindows_cancel);

        //整个线性布局
        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //拍照
        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //相册
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
              /*  Intent intent = new Intent(PublishGoodsActivity.this, AlbumActivity.class);
                startActivity(intent);*/
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                HAS_SELECT = Bimp.tempSelectBitmap.size();
                if (HAS_SELECT < MAX_SELECT)
                    getTakePhoto().onPickMultiple(MAX_SELECT - HAS_SELECT);
                else
                    ToastUtil.showToast(PublishGoodsActivity.this, "最多选取九张照片");
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //取消
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //发布商品
        mPublishGoodsTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                processPublish();
            }
        });
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new PublishGoodsGridAdapter(this);
        adapter.setListener(new PublishGoodsGridAdapter.OnPublishGridDeleteItemListener() {
            @Override
            public void deleteOnClick(View v, int position) {
                Bimp.tempSelectBitmap.remove(position);
                HAS_SELECT = Bimp.tempSelectBitmap.size();
                adapter.notifyDataSetChanged();
            }
        });
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new OnItemClickListener() {//查看某个照片

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    ll_popup.startAnimation(AnimationUtils.loadAnimation(PublishGoodsActivity.this, R.anim.activity_translate_in));
                    pop.showAtLocation(LayoutInflater.from(PublishGoodsActivity.this).inflate(R.layout.activity_selectimg, null), Gravity.CENTER, 0, 0);
                } else {
                    Toast.makeText(PublishGoodsActivity.this, Bimp.tempSelectBitmap.get(arg2).imagePath, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerPublishActivityComponent
                .builder()
                .appComponent(appComponent)
                .publishActivityModule(new PublishActivityModule(this))
                .build()
                .inject(this);
    }

    /**
     * Process publish.
     */
    public void processPublish() {

        if (ToolsUtils.isNullOrEmpty(mGoodsTitle.getText().toString())) {
            Toast.makeText(PublishGoodsActivity.this, "主题不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (ToolsUtils.isNullOrEmpty(mGoodsDescription.getText().toString())) {
            Toast.makeText(PublishGoodsActivity.this, "描述不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (ToolsUtils.isNullOrEmpty(mGoodsPrice.getText().toString()) || ToolsUtils.isNullOrEmpty(mGoodsNewDegree.getText().toString()) || ToolsUtils.isNullOrEmpty(mGoodsLocation.getText().toString())) {
            Toast.makeText(PublishGoodsActivity.this, "请输入必要的产品信息", Toast.LENGTH_LONG).show();
            return;
        }
        String[] imagePath = new String[Bimp.tempSelectBitmap.size()];
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            imagePath[i] = Bimp.tempSelectBitmap.get(i).getImagePath();
        }
        mLoadingDialog = new LoadingDialog();
        mLoadingDialog.show(getFragmentManager(), "Loading");
        presenter.uploadPicture(PublishGoodsActivity.this, imagePath);
    }

    @Override
    protected void onDestroy() {
        mLocClient.stop();
        super.onDestroy();
    }

    @Override
    public void publishSuccess() {
        mLoadingDialog.dismiss();
        ToastUtil.showToast(this, "发布成功");
        finish();
    }

    @Override
    public void publishError(String str) {
        Log.e(TAG, "publishError: " + str);
        ToastUtil.showToast(this, "发布失败");
    }

    @Override
    public void uploadSuccess(List<String> images) {
        ToastUtil.showToast(this, "上传成功");
        /*图片上传成功后加上左右的商品信息发布商品*/
        presenter.publishGoods(this, mGoodsTitle.getText().toString(),
                mGoodsDescription.getText().toString(), images, kind, secondkind,
                mGoodsPrice.getText().toString(), mGoodsNewDegree.getText().toString(),
                mCurrentLocation, mCurrentPrince, qiugou, xiaoqu,is_qiugou_seller,qiugou_goods_id,false);
    }

    @Override
    public void uploadFailed(String str) {
        ToastUtil.showToast(this, str);
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

    /**
     * Photo.
     */
    public void photo() {
        //getTakePhoto().onPickFromGallery();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap bm = extras.getParcelable("data");
                        Uri uri = saveBitmap(bm);
                        ImageItem takePhoto = new ImageItem();
                        takePhoto.setBitmap(bm);
                        if (uri != null) {
                            takePhoto.setImagePath(uri.getPath());
                        }
                        Bimp.tempSelectBitmap.add(takePhoto);
                    }
                }
                break;
            case GlobalDefineValues.CHOOSE_KIND:
                if (ToolsUtils.isNullOrEmpty(source)) {
                    if (data == null) {
                        return;
                    } else {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            kind = extras.getString(GlobalDefineValues.GOODS_KIND);
                            secondkind = extras.getString(GlobalDefineValues.GOODS_SECOND_KIND);
                            Log.e("kind", kind + "  " + secondkind);
                            mGoodsKindText.setText(kind + "  " + secondkind);
                        }
                    }
                }
                break;
        }
        adapter.notifyDataSetChanged();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Uri saveBitmap(Bitmap bm) {
        File tmpDir;
        if (hasSD()) {
            tmpDir = new File(Environment.getExternalStorageDirectory() + "/tradein/");
        } else {
            tmpDir = new File(Environment.getDataDirectory() + "/tradein/");
        }

        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + System.currentTimeMillis() + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否有SD卡
     */
    private boolean hasSD() {
        //如果有SD卡 则下载到SD卡中
        //如果没有SD卡
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void takeSuccess(TResult result) {
        for (TImage t : result.getImages()
                ) {
            ImageItem item = new ImageItem();
            item.setImagePath(t.getOriginalPath());
            if (!Bimp.Contains(item)) {
                Bimp.tempSelectBitmap.add(item);
            }
            Log.e(TAG, "takeSuccess: " + t.getOriginalPath());
        }


    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.e(TAG, "takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
        Log.e(TAG, getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 定位SDK监听函数
     */
    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder sb = new StringBuilder(256);

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果

            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
           mCurrentLocation = location.getAddrStr();
            mCurrentPrince = location.getCity();
            mGoodsLocation.setText(mCurrentLocation);
            ToastUtil.showToast(PublishGoodsActivity.this, sb.toString());
            mGoodsLocation.postInvalidate();
        }

    }

    /**
     * 获取TakePhoto实例
     *
     * @return take photo
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }
}