package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import db.YuDongDB;
import model.User;
import tjuci.dl.myweixin.R;

public class RegistActivity extends Activity {
	private String num_text = "";
	private String pwd_text = "";
	private String Cpwd_text = "";
	// private TextView chp;
	private Button regist_rbtn;
	private Button regist_cbtn;
	private EditText regist_num;
	private EditText regist_pwd;
	private EditText regist_Cpwd;
	private YuDongDB yuDongDB;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.regist);
		// chp = (TextView) findViewById(R.id.chp);
		regist_rbtn = (Button) findViewById(R.id.regist_regist_btn);
		regist_cbtn = (Button) findViewById(R.id.regist_cancel_btn);
		regist_num = (EditText) findViewById(R.id.regist_num);
		regist_pwd = (EditText) findViewById(R.id.regist_pwd);
		regist_Cpwd = (EditText) findViewById(R.id.regist_check_pwd);
	
		yuDongDB = YuDongDB.getInstance(this);
		user = new User();
		
		// Resources resources = getBaseContext().getResources();
		// final Drawable dtrue = resources.getDrawable(R.drawable.ttrue);
		// final Drawable dfalse = resources.getDrawable(R.drawable.ffalse);
		
		//获取EditText焦点
//		 regist_pwd.setOnFocusChangeListener(new OnFocusChangeListener() {

//		        @Override
//		        public void onFocusChange(View v, boolean hasFocus) {
//		            // TODO Auto-generated method stub
//		            if(hasFocus){
//		                //获得焦点处理
//		            }
//		            else {
//		                //失去焦点处理
//		            }
//		        }
//		    });
       
		regist_rbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setText();
				if (TextUtils.isEmpty(num_text)) {
					Toast.makeText(getApplicationContext(), "帐号不能为空", Toast.LENGTH_LONG).show();
				}
				if ( ! ( pwd_text.equals(Cpwd_text))) {	
					Toast.makeText(getApplicationContext(), "密码错误，请从新输入密码。", Toast.LENGTH_LONG).show();
				}else {
					yuDongDB.saveUser(user);
					Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
					Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
					startActivity(intent);
				}
				
					
			}
		});

		regist_cbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MyYuDongActivity.class);
				startActivity(intent);
			}
		});

	}
	public void setText(){
		num_text = regist_num.getText().toString();
		pwd_text = regist_pwd.getText().toString();
		Cpwd_text = regist_Cpwd.getText().toString();
		user.setNumber(num_text);
		user.setPassword(pwd_text);
	}
}
