package cn.edu.jsnu.kwjw.example34;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] names = {"小猫", "哈士奇", "小黄鸭", "小鹿", "老虎"};
    private int[] icons = {R.drawable.cat, R.drawable.siberiankusky, R.drawable.yellowduck, R.drawable.fawn, R.drawable.tiger};
    private String[] introductions = {
            "猫，属于猫科动物，分家猫、野猫，是全世界家庭中较为广泛的宠物。",
            "西伯利亚雪橇犬，常见别名哈士奇，昵称为二哈。",
            "鸭的体型相对较小，颈短，一些属的嘴要大些，退位于身体后方，因而步态蹒跚",
            "鹿科是哺乳纲偶蹄目下的一科动物，体型大小不等，为有角的反刍类。",
            "虎，大型猫科动物，毛色浅黄或者棕黄色，满有黑色横纹，头圆，耳短，耳背面黑色，中央有一白斑甚显著，四肢健壮有力，尾粗长，具黑色环纹，尾端黑色。"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = findViewById(R.id.my_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter(icons, names, introductions);
        //item的点击事件
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.MyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "你点击了" + names[position] + "!", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    static class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private String[] mNames;
        private String[] mIntroductions;
        private int[] mIcons;

        private MyOnItemClickListener onItemClickListener;

        public MyRecyclerViewAdapter(int[] icons, String[] names, String[] introductions){
            this.mNames = names;
            this.mIntroductions = introductions;
            this.mIcons = icons;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder)holder).iconImageView.setImageResource(mIcons[position]);
            ((MyViewHolder)holder).nameTextView.setText(mNames[position]);
            ((MyViewHolder)holder).introductionTextView.setText(mIntroductions[position]);

            holder.itemView.setOnClickListener(new MyOnClickListener(position));
        }

        @Override
        public int getItemCount() {
            return mNames.length;
        }

        static class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView iconImageView;
            TextView nameTextView;
            TextView introductionTextView;

            MyViewHolder(View itemView){
                super(itemView);
                iconImageView = itemView.findViewById(R.id.iv);
                nameTextView = itemView.findViewById(R.id.name);
                introductionTextView = itemView.findViewById(R.id.introduce);
            }
        }


        //点击事件
        public void setOnItemClickListener(MyOnItemClickListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
        }

        //接口
        public interface MyOnItemClickListener{
            void onItemClick(View view, int position);
        }
        private class MyOnClickListener implements View.OnClickListener{
            private int position;

            MyOnClickListener(int position){
                this.position = position;
            }

            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, position);
            }
        }
    }
}