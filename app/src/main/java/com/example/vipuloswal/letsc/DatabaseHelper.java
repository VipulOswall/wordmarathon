package com.example.vipuloswal.letsc;

/**
 * Created by Vipul Oswal on 24-06-2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname="info.db";
    private static final int dbversion=1;
    private static final String tbname="GameInfo";
    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context,dbname,null,dbversion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="Create table "+tbname+"(name TEXT, value NUMBER)";
        sqLiteDatabase.execSQL(query);
    }
    public void create()
    {

        ContentValues c=new ContentValues();
        String str[]={"Game","Word","HS","Time","Skip"};
        for(int j=0;j<str.length;j++) {
            c.put("name", str[j]);
            c.put("value", 0);
            SQLiteDatabase d = this.getWritableDatabase();
            d.insert(tbname, null, c);
            System.out.print("djbhbfd");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query="Drop table if EXISTS "+tbname;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
    public int getData(String str)
    {
        String query="Select * from "+tbname;
        SQLiteDatabase d=this.getReadableDatabase();
        Cursor c=d.rawQuery(query,null);
        if(c!=null)
        {
            c.moveToFirst();
            do {
                String name=c.getString(0);
                if(name.equals(str))
                return c.getInt(1);
            }while(c.moveToNext());
            c.close();
        }
        return 0;
    }
    public int update(String name,int number)
    {
        SQLiteDatabase d=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("value",number);
        d.update(tbname,c,"name = '"+name+"'",null);
        return 1;
    }
}
