package com.itlty.crud;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itlty.crud.dao.PersonDao;
import com.itlty.crud.domain.Person;

import java.util.List;
public class MainActivity extends Activity {
    private ListView lv;
    List<Person> persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PersonDao dao = new PersonDao(this);
        dao.add("name1",15,1550466);
        dao.add("name2",17,1869587);
        persons = dao.findAll();

        ListView lv = findViewById(R.id.lv);
        //实现ListView接口，使用接口子类适配器；
        //这样只实现需要的接口方法即可
        lv.setAdapter(new MyAdapter());
    }
    //一般一个接口给出多个方法，都会提供一些抽象适配器子类
    //通常以simpleXXX、defaultXXX、baseXXX
    //如果直接实现ListAdapter接口，其方法特别多
    //因为ListView前台与后台交互特别频繁，Android系统将该控件设计为MVC模式
    /*
     * M相当于List<Person>（数据模型）
     * V相当于listview（视图）
     * C相当于adapter（控制器，即数据适配器）
     * */
    private class MyAdapter extends BaseAdapter {
        private static final String TAG = "MyAdapter";
        @Override
        public int getCount() {
            //控制lisrview中总共有多少个条目
            //条目个数为集合中元素总数
            return persons.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int position, View v, ViewGroup viewGroup) {
            //得到某个位置对应的person对象
            Person person = persons.get(position);
            //实现list_item布局文件显示方法
            //1.将list_item布局文件转换为对象，
            //2.使用inflate打气筒为需要显示的组件充气
            //（1）context:获取当前环境对象（注意：获取当前内部类的context）
            //（2）给哪个资源文件充气
            // (3) 如果不想为该布局文件指定父窗体，则使用adapter来加载，一般使用null
            // null就是将此布局文件作为独立窗体使用
            // 3.view对象就是使用inflate转化出来的视图对象
            View view = View.inflate(MainActivity.this, R.layout.list_item, null);
            //编辑view使用显示对象
            //我们是在内部类中获取非activity_main文件文件，即获取自定义view布局文件
            //应该使用veiw对象来获取(要注意获取自定义布局文件组件的路径)
            TextView tv_id = view.findViewById(R.id.tv_id);
            //将personid写入到tv_id组件中
            tv_id.setText("id: " + person.getPersonid());
            //将personid写入到tv_name组件中
            TextView tv_name = view.findViewById(R.id.tv_name);
            tv_name.setText("姓名: " + person.getName());
            TextView tv_phone = view.findViewById(R.id.tv_phone);
            //此表没有电话号码属性，使用age替代
            tv_phone.setText("电话：" + person.getAge());
            return view;
        }
    }
}
