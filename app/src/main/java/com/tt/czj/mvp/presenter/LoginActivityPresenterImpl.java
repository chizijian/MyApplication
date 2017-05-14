package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.LoginActivityView;
import com.tt.czj.utils.ToolsUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * The type Login activity presenter.
 */
public class LoginActivityPresenterImpl implements LoginActivityPresenter {

    private LoginActivityView loginView;

    /**
     * Instantiates a new Login activity presenter.
     *
     * @param homeView the home view
     */
    public LoginActivityPresenterImpl(LoginActivityView homeView) {
        this.loginView = homeView;
    }
    @Override
    public void login(String username, String pass, final Context context) {
        User bu2 = new User();
        if (ToolsUtils.isMobileNO(username)){
            if (ToolsUtils.isCorrectUserPwd(pass)){
                bu2.setMobilePhoneNumber(username);
                bu2.setPassword(pass);
                bu2.login(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        User user = BmobUser.getCurrentUser(context,User.class);
                        loginView.loginSuccess(user);
                        //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        loginView.loginFailed("登录失败");
                    }
                });
            }else{
                loginView.loginFailed("密码中含有非法字符");
            }
        }else {
            if (ToolsUtils.isCorrectUserPwd(pass)){
                bu2.setUsername(username);
                bu2.setPassword(pass);
                bu2.login(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        User user = BmobUser.getCurrentUser(context,User.class);
                        loginView.loginSuccess(user);
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        loginView.loginFailed("登录失败");
                    }
                });
            }else{
                loginView.loginFailed("密码中含有非法字符");
            }
        }
    }
}
