package mqclient.ameya.com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by AMEYA on 10/9/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="brokerDB";
    public static final String TABLE_NAME="brokerInfo";
    public static final String ID="id";
    public static final String BROKERURL="brokerURL";
    public static final String PORT="port";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";


   // public String a="abc";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        Log.d("Database operations", "Database Created");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BROKERURL + " VARCHAR," + PORT + " VARCHAR," + USERNAME + " VARCHAR," + PASSWORD + " VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(DBHelper dbHelper,String brokerURL, String port, String username, String password){
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(BROKERURL,brokerURL);
        contentValues.put(PORT,port);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);


        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public long countRows(){
        long rowCount=0;

        SQLiteDatabase db=getReadableDatabase();
        rowCount = DatabaseUtils.longForQuery( db,"SELECT COUNT(*) FROM "+TABLE_NAME,null);

        return rowCount;
    }
    public void updateData(DBHelper dbHelper, int id,String brokerURL, String port, String username, String password){

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(BROKERURL,brokerURL);
        contentValues.put(PORT,port);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);

        if(countRows()>0) {
           db.update(TABLE_NAME, contentValues, " id=1 ", new String[]{ID});
            db.close();
        }

        else {
            insertData(dbHelper, brokerURL, port, username, password);
            db.close();
        }
    }
}
