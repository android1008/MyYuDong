package activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import db.YuDongDB;
import tjuci.dl.myweixin.R;

/**
 * 此类 是对布局main.xml上 控件的操作
 * 
 * @author dl
 *
 */
public class LoginActivity extends Activity implements OnClickListener {

	private Button rebackBtn, loginBtn, registBtn;
	private EditText userEdit, passwdEdit;
	private YuDongDB yuDongDB;
	private RelativeLayout loginLayout;
	private String usernum, userpwd,dbPwd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		rebackBtn = (Button) findViewById(R.id.login_reback_btn);
		loginBtn = (Button) findViewById(R.id.login_login_btn);
		passwdEdit = (EditText) findViewById(R.id.login_passwd_edit);
		userEdit = (EditText) findViewById(R.id.login_user_edit);
		registBtn = (Button) findViewById(R.id.login_regist_btn);
		loginLayout = (RelativeLayout) findViewById(R.id.login_layout);

		yuDongDB = YuDongDB.getInstance(getApplicationContext());

		rebackBtn.setOnClickListener(this);// 注册监听器 一定不能忘
		loginBtn.setOnClickListener(this);// 注册监听器 一定不能忘
		registBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.login_reback_btn:// 返回按钮
			LoginActivity.this.finish();// 关闭这个Activity 返回上一个Activity
			break;
		case R.id.login_login_btn:// 点击登录按钮 进行判断 用户名和密码是否为空
			String userEditStr = userEdit.getText().toString().trim();
			String passwdEditStr = passwdEdit.getText().toString().trim();
			if (TextUtils.isEmpty(userEditStr) || TextUtils.isEmpty(passwdEditStr)) {// 只要用户名和密码有一个为空
				new AlertDialog.Builder(LoginActivity.this)
						.setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setTitle("登录失败")
						.setMessage("账号或密码不能为空，请输入账号或密码").create().show();
			}else
				//判断密码是否正确
				if (userpwd.equals(dbPwd)) {
				Intent intent = new Intent(getApplicationContext(),SiteActivity.class);
				startActivity(intent);
			}else {
				Toast.makeText(getApplicationContext(), R.string.check_pwd, Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.login_regist_btn:
			Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
			startActivity(intent);
		}

	}

	public void getText() {
		usernum = userEdit.getText().toString();
		userpwd = passwdEdit.getText().toString();
		dbPwd = yuDongDB.loadusers(usernum);
	}
}