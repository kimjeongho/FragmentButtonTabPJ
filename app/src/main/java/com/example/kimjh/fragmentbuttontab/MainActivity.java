package com.example.kimjh.fragmentbuttontab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Tab1Fragment.OnMessageReceiver {
//implements Tab1Fragment.OnMessageReceiver - > 인터페이스 사용 값 전달
    private static final String TAB1_TAG = "tab1";
    private static final String TAB2_TAG = "tab2";

    View selectorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn_tab1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment f = new Tab1Fragment();
                Fragment old = getSupportFragmentManager().findFragmentByTag(TAB1_TAG);// Fragment에 대한 연산을 하기 전에 Tag를 이용하여 FragmentManager에
                                                                                        // 해당 Fragment가 있는지 검사 할 수 있다.
                if(old==null) {
                    Fragment f = Tab1Fragment.newInstance("Button creation....");   //??
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, f,TAB1_TAG);
                    ft.commit();
                }
                setSelectorButton(v);

            }
        });
        if(savedInstanceState == null){//savedInstanceState : Activity가 갑자기 죽을 것을 대비해서 Activity의 상태를 저장하기 위해 호출되는 함수.
            Fragment f = Tab1Fragment.newInstance("onCreate.....");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,f,TAB1_TAG);
            ft.commit();
            setSelectorButton(btn);//??
        }

        btn = (Button)findViewById(R.id.btn_tab2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment f = new Tab2Fragment();
                Fragment old = getSupportFragmentManager().findFragmentByTag(TAB2_TAG);
                if(old == null) {
                    Fragment f = new Tab2Fragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, f);
                    ft.commit();
                    setSelectorButton(v);
                }
            }
        });
    }

    public void onMessageReceive(String message){
        Toast.makeText(this, "activity : " + message,Toast.LENGTH_SHORT).show();
    }// Fragment -> Activity로 값 전달: 값을 받고자 하는 객체에서는 Fragment에 Listener를 등록하여 값을 받을수 있다.

    private void setSelectorButton(View v) {    //??
        if(selectorButton != v){
            if(selectorButton != null){
                selectorButton.setSelected(false);
            }
            selectorButton = v;
            selectorButton.setSelected(true);

        }
    }
}
