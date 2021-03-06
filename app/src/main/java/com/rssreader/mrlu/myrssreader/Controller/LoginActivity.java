package com.rssreader.mrlu.myrssreader.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.rssreader.mrlu.myrssreader.R;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.md_teal_a700_color_code), 0);
        setContentView(R.layout.activity_login);

        Button btnLocalhost = (Button) findViewById(R.id.btn_localhost);
        Button btnFeedly = (Button) findViewById(R.id.btn_feedly);

        //设置本地账户按钮的点击事件
        btnLocalhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Toast.makeText(LoginActivity.this, "该功能将在后续版本提供，敬请期待", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, mainView.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

                finish();

                //插入sp文件设置值isHasFeed,再次启动app时不再显示login页面
                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("sp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器

                editor.putBoolean("isLoadLoginPage", false);

                editor.commit();//提交修改
            }
        });

        //设置feedly按钮的点击事件
        btnFeedly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "正在解决feedly的sdk问题，即将推出", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
    }

}
