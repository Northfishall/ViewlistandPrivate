package com.itlty.crud;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.itlty.crud.dao.PersonDao;
import com.itlty.crud.db.PersonSqliteHelper;
import com.itlty.crud.domain.Person;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        //assertEquals("com.itlty.crud", appContext.getPackageName());
        PersonSqliteHelper helper = new PersonSqliteHelper(appContext);
        helper.getWritableDatabase();
    }
    @Test
    public void add() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonDao dao = new PersonDao(appContext);
        //测试数据库，添加50条记录
        //int age=10;
        //Random random=new Random();
        for (int i = 0; i < 50; i++) dao.add("AAA" + i, 10 + i, 5000 + i);

        /*dao.add("fff", 25, "5000");
        dao.add("ggg", 25, "5000");*/
    }
    @Test
    public void testFind() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonDao dao = new PersonDao(appContext);
        boolean result = dao.find("aaa");
        System.out.println("******************************" + result);
        Assert.assertEquals(true, result);
    }
    @Test
    public void testUpdate() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonDao dao = new PersonDao(appContext);
        dao.update("aaa", 2222);
    }
    @Test
    public void testDelete() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonDao dao = new PersonDao(appContext);
        dao.delete("aaa");
    }
    @Test
    public void testFindAll() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonDao dao = new PersonDao(appContext);
        List<Person> persons = dao.findAll();
        Log.i(TAG, "输出内容：");
        for (Person p : persons) {
            System.out.println("=============================================");
            System.out.println(p.toString());
        }
    }
    //事务操作
    //实现transection事务操作
    @Test
    public void testTransection() throws Exception {
        //String s = null; //测试事务异常变量
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonSqliteHelper helper = new PersonSqliteHelper(appContext);
        //1.获取数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //开启数据库的事务
        db.beginTransaction();
        try {
            db.execSQL("update person set account=account-1000 where name=?", new Object[]{"fff"});
            //模拟出现异常
            //s.equals("okokok");
            db.execSQL("update person set account=account+1000 where name=?", new Object[]{"ggg"});
            //标记数据库事务执行成功
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //如果执行不超过，则回滚事务
            db.endTransaction();
            db.close();
        }
    }
}
