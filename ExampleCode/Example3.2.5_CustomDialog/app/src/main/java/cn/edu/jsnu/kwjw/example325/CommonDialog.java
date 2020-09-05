package cn.edu.jsnu.kwjw.example325;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CommonDialog extends AlertDialog {

    private TextView titleTv;
    private TextView messageTv;
    private Button negativeBtn, positiveBtn;

    private OnClickBottomListener onClickListener;

    protected CommonDialog(Context context) {
        super(context);
    }

    private String title, message, negative, positive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        initView(); //初始化界面控件
        initEvent();   //初始化界面控件的点击事件
    }

    //初始化界面控件
    private void initView(){
        negativeBtn = findViewById(R.id.negative);
        positiveBtn = findViewById(R.id.positive);
        messageTv = findViewById(R.id.message);
        titleTv = findViewById(R.id.title);
    }

    //初始化界面控件的点击事件
    private void initEvent(){
        //确定按钮的点击事件
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickListener != null){
                    onClickListener.onPositiveButtonClick();
                }
            }
        });
        //取消按钮的点击事件
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickListener != null){
                    onClickListener.onNegativeButtonClick();
                }
            }
        });
    }

    @Override
    public void show(){
        super.show();
        refreshView();
    }

    public interface OnClickBottomListener{
        void onPositiveButtonClick();
        void onNegativeButtonClick();
    }

    private void refreshView(){

        if(TextUtils.isEmpty(title)){
            titleTv.setVisibility(View.GONE);
        }else{
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }

        if(TextUtils.isEmpty(message)){
            messageTv.setVisibility(View.GONE);
        }else{
            messageTv.setText(message);
            messageTv.setVisibility(View.VISIBLE);
        }

        if(!TextUtils.isEmpty(positive)){
            positiveBtn.setText(positive);
        }else{
            positiveBtn.setText(R.string.custom_dialog_positive);  //因在xml中已设置默认显示的文本，此处的else内容可省略
        }

        if(!TextUtils.isEmpty(negative)){
            negativeBtn.setText(negative);
        }
    }

    public void setOnClickButtonListener(OnClickBottomListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPositive(String positive){
        this.positive = positive;
    }

    public void setNegative(String negative){
        this.negative = negative;
    }

}
