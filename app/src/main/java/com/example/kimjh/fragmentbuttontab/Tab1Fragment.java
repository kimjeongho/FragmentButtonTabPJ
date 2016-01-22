package com.example.kimjh.fragmentbuttontab;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

    //Fragment에서 Fragment가 속한 Activity로 값 전달------------------------------------------------------------------------

    public interface OnMessageReceiver{
        public void onMessageReceive(String message);
    }
    OnMessageReceiver mCallback;    // 콜백 인터페이스 정의
    public static Tab1Fragment newInstance(String message){
        Tab1Fragment f = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(PARAM_MESSAGE, message);
        f.setArguments(args);
        return f;
    }
    // Fragment에 Listener를 정의하여 Listener를 등록할 수 있도록 만들어 주는 방법 이다.

    //Fragment에서 Fragment가 속한 Activity로 값 전달------------------------------------------------------------------------

    public Tab1Fragment() {
        // Required empty public constructor
    }

    public static final String PARAM_MESSAGE = "message"; // 전달할 키값 상수 정의
    String message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null){
            message = b.getString(PARAM_MESSAGE);
        }   //Buncle을 getArgments()로 가져온다음 Bundle에 저장된 값을 얻어 와서 사용한다.
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMessageReceiver){
            mCallback = (OnMessageReceiver)context;
        }   //??
    }

    EditText inputView;
    TextView messageView;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        inputView = (EditText)v.findViewById(R.id.edit_input);
        messageView = (TextView)v.findViewById(R.id.text_message);
        Button btn = (Button)v.findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputView.getText().toString();
                messageView.setText(message);
                //--------------------------------------------------------------------------------
                if (mCallback != null){
                    mCallback.onMessageReceive(message);
                }// Listener가 등록되어 있으면 Listener로 값을 보내 준다.
            }
        });
        return v;
    }

}
