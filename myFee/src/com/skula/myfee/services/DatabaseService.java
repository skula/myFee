package com.skula.myfee.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.skula.myfee.models.Category;
import com.skula.myfee.models.Fee;
import com.skula.myfee.models.Month;


public class DatabaseService {
	private static final String DATABASE_NAME = "myfee.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME_FEE = "fee";
	private static final String TABLE_NAME_CATEGORY = "category";

	private Context context;
	private SQLiteDatabase database;
	private SQLiteStatement statement;

	public DatabaseService(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.database = openHelper.getWritableDatabase();
	}

	public void bouchon() {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FEE);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORY);

		database.execSQL("create table " + TABLE_NAME_CATEGORY + "(id integer primary key, label TEXT, color TEXT, budget NUMERIC)");
		database.execSQL("create table " + TABLE_NAME_FEE + "(id integer primary key, date DATE, amount NUMERIC, label TEXT, categoryid NUMERIC)");
		
		// TODO: inserts...
		insertCategory(new Category(null,"Restaurant","#ffffff","100"));
		insertCategory(new Category(null,"Courses","#3c3c3c","200"));
		insertCategory(new Category(null,"Sorties","#21e5b3","50"));
		insertFee(new Fee(null, "Kebab", "15.25","Restaurant", "2013-11-28"));
		insertFee(new Fee(null, "Subway", "25.25","Restaurant", "2013-11-27"));
		insertFee(new Fee(null, "McDo", "9.25","Restaurant", "2013-11-26"));
		insertFee(new Fee(null, "Simply", "15.25","Courses", "2013-11-25"));
		insertFee(new Fee(null, "Paris Store", "7.25","Courses", "2013-11-24"));
		insertFee(new Fee(null, "Kebab", "20.25","Courses", "2013-11-23"));
		insertFee(new Fee(null, "Brice", "15.25","Courses", "2013-11-22"));
		insertFee(new Fee(null, "Fnac", "3.25","Courses", "2013-11-21"));
	}

	public void insertFee(Fee fee) {
		String sql = "insert into " + TABLE_NAME_FEE
				+ "(id, label, amount, categoryid, date) values (?,?,?,?,?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, getNextFeeId());
		statement.bindString(2, fee.getLabel());
		statement.bindDouble(3, Double.valueOf(fee.getAmount()));
		statement.bindLong(4, getCategoryId(fee.getCategory()));
		statement.bindString(5, fee.getDate());
		statement.executeInsert();
	}

	public void updateFee(String id, Fee newFee) {
		ContentValues args = new ContentValues();
		args.put("label", newFee.getLabel());
		args.put("amount", newFee.getAmount());
		args.put("idcategory", getCategoryId(newFee.getCategory()));
		args.put("date", newFee.getDate());
		database.update(TABLE_NAME_FEE, args, "id=" + id, null);
	}

	public void deleteFee(String id) {
		database.delete(TABLE_NAME_FEE, "id=" + id, null);
	}
	
	public int getNextFeeId() {
		Cursor cursor = database.query(TABLE_NAME_FEE, new String[] { "max(id)" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0) + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return 1;
	}
	
	public void insertCategory(Category cat) {
		String sql = "insert into " + TABLE_NAME_CATEGORY
				+ "(id, label, color, budget) values (?,?,?,?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, getNextCategoryId());
		statement.bindString(2, cat.getLabel());
		statement.bindString(3, cat.getColor());
		statement.bindDouble(4, Double.valueOf(cat.getBudget()));
		statement.executeInsert();
	}
	
	public void updateCategory(String id, Category cat) {
		ContentValues args = new ContentValues();
		args.put("label", cat.getLabel());
		args.put("budget", cat.getBudget());
		args.put("color", cat.getColor());
		database.update(TABLE_NAME_CATEGORY, args, "id=" + id, null);
	}

	public void deleteCategory(String id) {
		database.delete(TABLE_NAME_CATEGORY, "id=" + id, null);
	}
	
	public int getNextCategoryId() {
		Cursor cursor = database.query(TABLE_NAME_CATEGORY, new String[] { "max(id)" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0) + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return 1;
	}
	
	public int getCategoryId(String label){
		Cursor cursor = database.query(TABLE_NAME_CATEGORY, new String[] { "id" }, "label='" + label + "'", null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return -1;
	}
	
	/******************** REQUETES DE LA MORT QUI TUE****************************/
	// nom du mois actuel et total des depenses
	public Month getCurrentMonthDetails(){
		String req = "select sum(amount) as total, case strftime('%m', date('now')) when '01' then 'Janvier' when '02' then 'Fevrier' when '03' then 'Mars' when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' when '07' then 'Juillet' when '08' then 'Aout' when '09' then 'Septembre' when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end as label "
					+ "from fee "
					+ "where strftime('%m%Y', date) =  strftime('%m%Y', date('now'))";
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				return new Month(cursor.getString(0), cursor.getString(0));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
	}

	// liste des categories, leurs totaux et leurs pourcentages
	public List<Category> getCategoriesDetails(){
		List<Category> res = new ArrayList<Category>();
		String req = "select c.label, sum(f.amount) as total, sum(f.amount)/tmp.totmonth as percent "
					+ "from fee f, category c, (select sum(amount) as totmonth "
					+ "from fee "
					+ "where strftime('%m%Y', date) =  strftime('%m%Y', date('now'))) as tmp "
					+ "where c.id = f.categoryid "
					+ "and strftime('%m%Y', f.date) =  strftime('%m%Y', date('now')) "
					+ "group by c.id "
					+ "order by total desc;";
		
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				Category cat = new Category();
				cat.setLabel(cursor.getString(0));
				cat.setTotal(cursor.getString(1));
				cat.setPercent(cursor.getString(2));
				res.add(cat);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return res;
	}
	
	// liste des depenses pour le mois actuel par categories
	public Map<String, List<Fee>> getFeesByCategories(){
		Map<String, List<Fee>> res = new HashMap<String, List<Fee>>();
		String req="select f.id, f.date, f.amount, f.label, c.label as catlab "
					+ "from fee f, category c "
					+ "where c.id = f.categoryid "
					+ "and strftime('%m%Y', f.date) =  strftime('%m%Y', date('now')) "
					+ "order by f.date desc;";
		
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				// creer Fee
				if(res.containsKey(cursor.getString(4))){
					 // add
				}else{
					// creer list + add
				}
				
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return res;
	}
	
	/*public Map<String, Double> graphPrcCat(String dateFrom){
		String req = "select c.label, sum(f.amount) from fee f, category c ";
		req+= "where f.dateinsr>=date('"+dateFrom+"') and f.idcategory=c.id ";
		req+= "group by c.label ";
		req+= "order by c.label asc";
		Cursor cursor = database.rawQuery(req, null);

		Map<String, Double> map = new HashMap<String, Double>();
		if (cursor.moveToFirst()) {
			do {
				map.put(cursor.getString(0),cursor.getDouble(1));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return map;
	}*/
	 

	 private static class OpenHelper extends SQLiteOpenHelper {
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE_NAME_CATEGORY + "(id integer primary key, label TEXT, color TEXT, budget NUMERIC)");
			db.execSQL("create table " + TABLE_NAME_FEE + "(id integer primary key, date DATE, amount NUMERIC, label TEXT, categoryid NUMERIC)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FEE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORY);
			onCreate(db);
		}
	}
}
