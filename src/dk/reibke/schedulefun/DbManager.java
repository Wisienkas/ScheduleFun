package dk.reibke.schedulefun;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.style.SuperscriptSpan;

public class DbManager extends SQLiteOpenHelper{

	private String username;
	private SQLiteDatabase databaseWrite;
	private SQLiteDatabase databaseRead;
	private Context context;
	
	private static boolean initialized = false;
	private static DbManager instance;
	
	public static boolean isInitialized(){
		return initialized;
	}
	
	public static DbManager InitiateDatabase(Context context, String name, CursorFactory factory,
			int version, String username){
		if(instance == null){
			instance = new DbManager(context, name, factory, version, username);
			initialized = true;
		}
		return instance;
	}
	
	public static DbManager getInstance(Context context){
		if(instance != null){
			instance.setContext(context);
			return instance;
			
		}
		return null;
		
	}
	

	private void setContext(Context context) {
		this.context = context;
		
	}

	private DbManager(Context context, String name, CursorFactory factory,
			int version, String username) {
		super(context, name, factory, version);
		this.context = context;
		this.username = username;
		this.databaseWrite = getWritableDatabase();
		this.databaseRead = getReadableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE users (username TEXT UNIQUE, " +
				"password TEXT NOT NULL" +
				"UId INTEGER PRIMARY KEY)");
		db.execSQL("CREATE TABLE schedule(name TEXT, " +
				"SId INTEGER PRIMARY KEY, " +
				"interval INTEGER NOT NULL, " +
				"repeatTimes INTEGER NOT NULL)");
		db.execSQL("CREATE TABLE specielschedule(SId Integer NOT NULL, " +
				"time INTEGER NOT NULL)");
		db.execSQL("CREATE TABLE ids (UId INTEGER UNIQUE, SId INTEGER UNIQUE)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasUser(String username) {
		String[] fields = {"username"};
		Cursor result = this.databaseRead.rawQuery("SELECT ? " +
				"FROM users" +
				"WHERE ? = " + username + ";", fields);
		if(result.getCount() < 1){
			return false;
		}
		return true;
	}

	public void addUser(String username, String password) {
		String sql = "INSERT INTO users VALUES(?,?)";
		SQLiteStatement statement = this.databaseWrite.compileStatement(sql);
		this.databaseWrite.beginTransaction();
		try{
			statement.clearBindings();
			statement.bindString(1, username);
			statement.bindString(2, password);
			statement.execute();
			this.databaseWrite.setTransactionSuccessful();
		}catch (SQLException e){
			throw new Error("Something went wrong");
		}finally{
			this.databaseWrite.endTransaction();
		}
	}

	public Cursor confirmUser(String username, String password) {
		String[] fields = {"username", "password"};
		Cursor result = this.databaseRead.rawQuery("Select username" +
				"FROM users" +
				"WHERE username = ? " +
				"AND password = ?;", 
				fields);
		return result;
	}
	
}
