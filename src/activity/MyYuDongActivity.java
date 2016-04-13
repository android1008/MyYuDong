package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import tjuci.dl.myweixin.R;
/**
 * ����  �ǶԲ���main.xml�� �ؼ��Ĳ���
 * @author dl
 *
 */
public class MyYuDongActivity extends Activity implements OnClickListener{
	 private Button loginBtn,registBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        loginBtn = (Button)findViewById(R.id.main_login_btn);
        registBtn = (Button)findViewById(R.id.main_regist_btn);
        loginBtn.setOnClickListener(this);
        registBtn.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		int btnId = v.getId();
		switch (btnId) {//�жϵ���İ�ť
		case R.id.main_login_btn://��¼��ť
			Intent intent = new Intent(MyYuDongActivity.this, LoginActivity.class);
			startActivity(intent);//������Ӧ��Activity  �˴�ΪӲ����  ��������ôд  д��action��ʽ ���
			Log.i("-------------", "------------------");
			break;

		case R.id.main_regist_btn://ע�ᰴť
			Intent reintent = new Intent(getApplicationContext(), RegistActivity.class);
			startActivity(reintent);
			break;
		}
	}
}