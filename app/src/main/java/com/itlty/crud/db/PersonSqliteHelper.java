package com.itlty.crud.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 由于Android系统提供了内部的sqlite数据库，
 * 所以，我们不需要在JDBC中连接数据库的前两种步骤，
 * 只需要使用期中方法实现CRUD
 */
public class PersonSqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "PersonSqliteHelper";
    public PersonSqliteHelper(Context context) {
        super(context, "person.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement,name varchar(20),age INTEGER,account INTEGER) ");
    }
    //修改数据库的表结构方法
    //只要在构造方法中修改（只能增加）版本号，即数据库版本号发生变化时，此方法就会执行
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       /* Log.i(TAG, "数据库版本号增加了...");
        //修改数据库表结构：给person表添加account属性
        db.execSQL("alter table person add account integer");*/
    }
}
