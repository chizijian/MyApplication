package com.tt.czj.ui.activity.BmobPay;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tt.czj.R;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.Order;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.RecyclerViewAdapter;
import com.tt.czj.utils.ToastUtil;
import com.tt.czj.utils.ToolsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import c.b.BP;
import c.b.PListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/4/10/0010.
 */
public class BmobPayActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    /**
     * The M pay goods detail back.
     */
    @BindView(R.id.pay_goods_detail_back)
    ImageView mPayGoodsDetailBack;
    /**
     * The M bmob pay top.
     */
    @BindView(R.id.bmob_pay_top)
    RelativeLayout mBmobPayTop;
    /**
     * The M pay goods detail image grid.
     */
    @BindView(R.id.pay_goods_detail_image_grid)
    RecyclerView mPayGoodsDetailImageGrid;
    /**
     * The M pay goods detail title.
     */
    @BindView(R.id.pay_goods_detail_title)
    TextView mPayGoodsDetailTitle;
    /**
     * The M pay goods detail description.
     */
    @BindView(R.id.pay_goods_detail_description)
    TextView mPayGoodsDetailDescription;
    /**
     * The M pay goods detail location.
     */
    @BindView(R.id.pay_goods_detail_location)
    TextView mPayGoodsDetailLocation;
    /**
     * The M pay goods detail price.
     */
    @BindView(R.id.pay_goods_detail_price)
    TextView mPayGoodsDetailPrice;
    /**
     * The M alipay.
     */
    @BindView(R.id.alipay)
    RadioButton mAlipay;
    /**
     * The M wxpay.
     */
    @BindView(R.id.wxpay)
    RadioButton mWxpay;
    /**
     * The M type.
     */
    @BindView(R.id.type)
    RadioGroup mType;
    /**
     * The M go.
     */
    @BindView(R.id.go)
    Button mGo;
    /**
     * The M pay linear layout.
     */
    @BindView(R.id.pay_linearLayout)
    LinearLayout mPayLinearLayout;
    /**
     * The M pay tv.
     */
    @BindView(R.id.pay_tv)
    TextView mPay_tv;
    /**
     * The Address.
     */
    @BindView(R.id.pay_goods_address)
    EditText address;

    private Order order=new Order();

    private Goods mGoods;
    private  User mUsers;
    /**
     * The Dialog.
     */
    ProgressDialog dialog;

    /**
     * The Pluginversion.
     */
    int PLUGINVERSION = 7;

    /**
     * Instantiates a new Bmob pay activity.
     */
    public BmobPayActivity() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.bmob_pay;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData() {
        mGoods = (Goods) getIntent().getSerializableExtra("Goods");
        mUsers = (User) getIntent().getSerializableExtra("User");

        order.setBuyer(BmobUser.getCurrentUser(BmobPayActivity.this,User.class));
        order.setSeller(mUsers);
        order.setGoods(mGoods);
        order.setDetails(mGoods.getDescription());
        order.setPrice(Double.parseDouble(mGoods.getPrice()));

        mPayGoodsDetailTitle.setText(mGoods.getTitle());
        mPayGoodsDetailDescription.setText(mGoods.getDescription());
        mPayGoodsDetailLocation.setText(mGoods.getLocation());
        mPayGoodsDetailPrice.setText(mGoods.getPrice());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //gridViewAdapter = new GridViewAdapter(mGoods.getImages(),this);
        //gridView.setAdapter(gridViewAdapter);
        mPayGoodsDetailImageGrid.setLayoutManager(layoutManager);
        mPayGoodsDetailImageGrid.setAdapter(new RecyclerViewAdapter(this, mGoods.getImages()));

        mType.setOnCheckedChangeListener(this);
        mGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+address.getText().toString() );
                if(ToolsUtils.isNullOrEmpty(address.getText().toString())){
                    ToastUtil.showToast(BmobPayActivity.this,"请填写收货地址");
                }
                else {
                    User user=BmobUser.getCurrentUser(BmobPayActivity.this,User.class);
                    user.setAddress(address.getText().toString());
                    user.update(BmobPayActivity.this, new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            Log.e(TAG, "onSuccess: ");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.e(TAG, "onFailure: " );
                        }
                    });
                    if (mType.getCheckedRadioButtonId() == R.id.alipay) // 当选择的是支付宝支付时
                        pay(true);
                    else if (mType.getCheckedRadioButtonId() == R.id.wxpay) // 调用插件用微信支付
                        pay(false);
                }
            }
        });
    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.pay_goods_detail_back, R.id.bmob_pay_top, R.id.pay_goods_detail_image_grid, R.id.pay_goods_detail_title, R.id.pay_goods_detail_description, R.id.pay_goods_detail_location, R.id.pay_goods_detail_price, R.id.alipay, R.id.wxpay, R.id.type, R.id.go, R.id.pay_linearLayout, R.id.bmob_pay,R.id.pay_goods_address})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_goods_detail_back:
                finish();
                break;
            case R.id.bmob_pay_top:
                break;
            case R.id.pay_goods_detail_image_grid:
                break;
            case R.id.pay_goods_detail_title:
                break;
            case R.id.pay_goods_detail_description:
                break;
            case R.id.pay_goods_detail_location:
                break;
            case R.id.pay_goods_detail_price:
                break;
            case R.id.alipay:
                // 以下仅为控件操作，可以略过
                mGo.setText("支付宝支付");
                break;
            case R.id.wxpay:
                // 以下仅为控件操作，可以略过
                mGo.setText("微信支付");
                break;
            case R.id.type:
                break;
            case R.id.go:
                Log.e(TAG, "onClick: "+address.getText().toString() );
                if(ToolsUtils.isNullOrEmpty(address.getText().toString())){
                    ToastUtil.showToast(this,"请填写收货地址");
                }
                else {
                    User user=BmobUser.getCurrentUser(this,User.class);
                    user.setAddress(address.getText().toString());
                    user.update(this, new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            Log.e(TAG, "onSuccess: ");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.e(TAG, "onFailure: " );
                        }
                    });
                    if (mType.getCheckedRadioButtonId() == R.id.alipay) // 当选择的是支付宝支付时
                        pay(true);
                    else if (mType.getCheckedRadioButtonId() == R.id.wxpay) // 调用插件用微信支付
                        pay(false);
                }
                break;
            case R.id.pay_linearLayout:
                break;
            case R.id.bmob_pay:
                break;
            case R.id.pay_goods_address:
                break;
        }
    }

    /**
     * 检查某包名应用是否已经安装
     *
     * @param packageName 包名
     * @param browserUrl  如果没有应用市场，去官网下载
     * @return
     */
    private boolean checkPackageInstalled(String packageName, String browserUrl) {
        try {
            // 检查是否有支付宝客户端
            getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // 没有安装支付宝，跳转到应用市场
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                startActivity(intent);
            } catch (Exception ee) {// 连应用市场都没有，用浏览器去支付宝官网下载
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(browserUrl));
                    startActivity(intent);
                } catch (Exception eee) {
                    Toast.makeText(BmobPayActivity.this,
                            "您的手机上没有没有应用市场也没有浏览器，我也是醉了，你去想办法安装支付宝/微信吧",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }
    private static final int REQUESTPERMISSION = 101;

    private void installApk(String s) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
        } else {
            installBmobPayPlugin(s);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTPERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installBmobPayPlugin("bp.db");
                } else {
                    //提示没有权限，安装不了
                    Toast.makeText(BmobPayActivity.this,"您拒绝了权限，这样无法安装支付插件",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * 调用支付
     *
     * @param alipayOrWechatPay 支付类型，true为支付宝支付,false为微信支付
     */
    void pay(final boolean alipayOrWechatPay) {
        if (alipayOrWechatPay) {
            if (!checkPackageInstalled("com.eg.android.AlipayGphone",
                    "https://www.alipay.com")) { // 支付宝支付要求用户已经安装支付宝客户端
                Toast.makeText(BmobPayActivity.this, "请安装支付宝客户端", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        } else {
            if (checkPackageInstalled("com.tencent.mm", "http://weixin.qq.com")) {// 需要用微信支付时，要安装微信客户端，然后需要插件
                // 有微信客户端，看看有无微信支付插件
                int pluginVersion = BP.getPluginVersion(this);
                if (pluginVersion < PLUGINVERSION) {// 为0说明未安装支付插件,
                    // 否则就是支付插件的版本低于官方最新版
                    Toast.makeText(
                            BmobPayActivity.this,
                            pluginVersion == 0 ? "监测到本机尚未安装支付插件,无法进行支付,请先安装插件(无流量消耗)"
                                    : "监测到本机的支付插件不是最新版,最好进行更新,请先更新插件(无流量消耗)",
                            Toast.LENGTH_SHORT).show();
//                   installBmobPayPlugin("bp.db");

                    installApk("bp.db");
                    return;
                }
            } else {// 没有安装微信
                Toast.makeText(BmobPayActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion());
        final String name = getName();

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.bmob.app.sport",
                    "com.bmob.app.sport.wxapi.BmobActivity");
            intent.setComponent(cn);
            this.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        BP.pay(name, getBody(), getPrice(), alipayOrWechatPay, new PListener() {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            @Override
            public void unknow() {
                Toast.makeText(BmobPayActivity.this, "支付结果未知,请稍后手动查询", Toast.LENGTH_SHORT)
                        .show();
                mPay_tv.append(name + "'s pay status is unknow\n\n");
                hideDialog();
            }

            // 支付成功,如果金额较大请手动查询确认
            @Override
            public void succeed() {
                Toast.makeText(BmobPayActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
                mGoods.setOff_shelve(true );
                mGoods.update(BmobPayActivity.this, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "onSuccess: " );
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.e(TAG, "onFailure: "+s );
                    }
                });
                mPay_tv.append(name + "'s pay status is success\n\n");
                order.setIsSuccess("支付成功");
                saveOrder();
                hideDialog();
            }

            // 无论成功与否,返回订单号
            @Override
            public void orderId(String orderId) {
                // 此处应该保存订单号,比如保存进数据库等,以便以后查询
                //order.setText(orderId);
                order.setOrderId(orderId);
                mPay_tv.append(name + "'s orderid is " + orderId + "\n\n");
                showDialog("获取订单成功!请等待跳转到支付页面~");
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            @Override
            public void fail(int code, String reason) {

                order.setIsSuccess("支付失败");
                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    Toast.makeText(
                            BmobPayActivity.this,
                            "监测到你尚未安装支付插件,无法进行支付,请先安装插件(已打包在本地,无流量消耗),安装结束后重新支付",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");
                    installApk("bp.db");
                } else {
                    Toast.makeText(BmobPayActivity.this, "支付中断!", Toast.LENGTH_SHORT)
                            .show();
                }
                mPay_tv.append(name + "'s pay status is fail, error code is \n"
                        + code + " ,reason is " + reason + "\n\n");
                hideDialog();
                saveOrder();
            }
        });
    }

    // 以下仅为控件操作，可以略过
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.alipay:
                // 以下仅为控件操作，可以略过
                mGo.setText("支付宝支付");
                break;
            case R.id.wxpay:
                // 以下仅为控件操作，可以略过
                mGo.setText("微信支付");
                break;
            default:
                break;
        }
    }

    /**
     * Gets price.
     *
     * @return the price
     */
// 默认为0.02
    double getPrice() {
        double price = 0.02;
        try {
            price = Double.parseDouble(this.mPayGoodsDetailPrice.getText().toString());
        } catch (NumberFormatException e) {
        }
        return price;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
// 商品详情(可不填)
    String getName() {
        return this.mPayGoodsDetailTitle.getText().toString();
    }

    /**
     * Gets body.
     *
     * @return the body
     */
// 商品详情(可不填)
    String getBody() {
        return this.mPayGoodsDetailDescription.getText().toString();
    }

    // 支付订单号(查询时必填)
//    String getOrder() {
//        return this.order.getText().toString();
//    }

    /**
     * Show dialog.
     *
     * @param message the message
     */
    void showDialog(String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(this);
                dialog.setCancelable(true);
            }
           dialog.setMessage(message);
            dialog.show();
        } catch (Exception e) {
            // 在其他线程调用dialog会报错
        }
    }

    /**
     * Hide dialog.
     */
    void hideDialog() {
        if (dialog != null && dialog.isShowing())
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
    }

    /**
     * 安装assets里的apk文件
     *
     * @param fileName the file name
     */
    void installBmobPayPlugin(String fileName) {
        try {
            InputStream is = getAssets().open(fileName);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + fileName + ".apk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + file),
                    "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveOrder(){
        order.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showToast(BmobPayActivity.this,"订单保存成功");
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.showToast(BmobPayActivity.this,"订单保存失败");
            }
        });
    }
}

