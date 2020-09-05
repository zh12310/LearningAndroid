package cn.edu.jsnu.kwjw.example325;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final CommonDialog cDialog = new CommonDialog(MainActivity.this);
                cDialog.setTitle("这里设置标题");
                cDialog.setMessage("这里是设置的内容文本\n 可以换行的。。。");
                //cDialog.setNegative("取消Cancel");
                cDialog.setPositive("确定OK");
                cDialog.setOnClickButtonListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        Toast.makeText(MainActivity.this, "你点击了确定按钮！", Toast.LENGTH_SHORT).show();
                        cDialog.dismiss();
                    }

                    @Override
                    public void onNegativeButtonClick() {
                        Toast.makeText(MainActivity.this, "你点击了取消按钮！", Toast.LENGTH_SHORT).show();
                        cDialog.dismiss();
                    }
                });
                cDialog.show();
            }
        });
    }
}