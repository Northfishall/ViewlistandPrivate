package com.itlty.crud.db;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
/**
 * 使用步骤：
 * 1.创建ContentProvider的子类PersonDBProvider；
 * 2.配置AndroidManifest.xml文档；
 * 使用ContentProvider组件必须在表单文件中配置，配置内容如下：
 * <provider
 * android:name="com.itlty.crud.db.PersonDBProvider"：ContentProvider全路径
 * android:authorities="com.itlty.crud.personpriovider"：提供者主机类包（即权限，类似于
 * web的URL），通过context://com.itlty.crud.personpriovider,就可以访问：例如：
 * content://com.itlty.crud.personpriovider/insert,添加；
 * content://com.itlty.crud.personpriovider/delete,删除；
 * content://com.itlty.crud.personpriovider/update,更新；
 * content://com.itlty.crud.personpriovider/query,查询；
 * <p>
 * />
 * 这种配置方式，对所有需要使用内容提供者的用户，都可以使用，必须再设置permission使用者权限，
 * 此例暂不配置。
 * 从下列方法可以看出，内容提供者可以对其数据进行CRUD；
 * 这些方法都有一个Uri uri参数，我们可以理解为电话号码（即特殊的资源地址路径，也可以理解为暗号）
 */
public class PersonDBProvider extends ContentProvider {
    //定义一个uri匹配器用来匹配uri，如果路径不满足匹配条件回复-
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    //为匹配器条件一组匹配规则:
    //void addURI (String authority,←主机名
    //                String path,←匹配资源文件路径uri
    //                int code)←匹配是成功返回符
    private static final int INSERT = 1;
    private static final int DELETE = 2;
    private static final int UPDATE = 3;
    private static final int QUERY = 4;
    private PersonSqliteHelper helper;
    static {
        matcher.addURI("com.itlty.crud.personpriovider", "insert", INSERT);
        matcher.addURI("com.itlty.crud.personpriovider", "delete", DELETE);
        matcher.addURI("com.itlty.crud.personpriovider", "update", UPDATE);
        matcher.addURI("com.itlty.crud.personpriovider", "query", QUERY);
    }
    //在使用内容提供者类时首先启动此方法进行初始化
    @Override
    public boolean onCreate() {
        helper = new PersonSqliteHelper(getContext( ));
        return false;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        //判断查询路径是否存在（即是否匹配）
        if (matcher.match(uri) == QUERY)
        {
            //如果匹配成功，返回操作结果集,从数据库中获取数据
            SQLiteDatabase db = helper.getReadableDatabase( );
            /**
             * Cursor query(String table,←要查询的表
             * String[] columns,←要查询的列
             * String selection,←查询条件
             * String[] selectionArgs,←查询参数
             * String groupBy,
             * String having,
             * String orderBy)
             */
            Cursor cursor = db.query("person", strings, s, strings1, null, null, s1);
            //这里内部使用close，在数据库使用完成后系统会自动关闭
            return cursor;
        }
        else
        {
            throw new IllegalArgumentException("路径不匹配，不能执行查询操作");
        }
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues)
    {
        return null;
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings)
    {
        return 0;
    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings)
    {
        return 0;
    }
}
