package dk.reibke.schedulefun.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;

public class Utilities {
	
	private static String DATABASE_NAME = "Schedule";
	
	public static String SHA256 (String text) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		md.update(text.getBytes());
		byte[] digest = md.digest();
		
		return Base64.encodeToString(digest, Base64.DEFAULT);
		
	}
	
	public static int getVersion(Context context){
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			throw new Error("Name not found from context");
		}
	}
	
	public static String getDatabaseName(){
		return DATABASE_NAME;
	}
	
}
