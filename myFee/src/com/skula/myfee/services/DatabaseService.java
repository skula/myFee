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

import com.skula.myfee.models.Budget;
import com.skula.myfee.models.Category;
import com.skula.myfee.models.CurveGraphic;
import com.skula.myfee.models.Fee;
import com.skula.myfee.models.Month;
import com.skula.myfee.models.RingGraphic;
import com.skula.myfee.models.TimeUnit;


public class DatabaseService {
	private static final String DATABASE_NAME = "myfee.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME_FEE = "fee";
	private static final String TABLE_NAME_CATEGORY = "category";
	private static final String TABLE_NAME_PARAMETER = "parameter";

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
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PARAMETER);

		database.execSQL("create table " + TABLE_NAME_CATEGORY + "(id integer primary key, label TEXT, color TEXT, budget NUMERIC)");
		database.execSQL("create table " + TABLE_NAME_FEE + "(id integer primary key, date DATE, amount NUMERIC, label TEXT, categoryid NUMERIC)");
		database.execSQL("create table " + TABLE_NAME_PARAMETER + "(key TEXT primary key, val TEXT)");
		
		// TODO: inserts...
		insertParameter("passwd", "1789");
		insertCategory(new Category(null,"Restaurant","#046A26","20"));
		insertCategory(new Category(null,"Courses","#015B72","200"));
		insertCategory(new Category(null,"Sorties","#903522","50"));
		
		insertFee(new Fee(null, "Kebab", "15.50","Restaurant", "2013-12-28"));
		insertFee(new Fee(null, "Subway", "25.25","Restaurant", "2013-11-27"));
		insertFee(new Fee(null, "McDo", "9.25","Restaurant", "2013-12-26"));
		insertFee(new Fee(null, "Simply", "15.25","Courses", "2013-12-25"));
		insertFee(new Fee(null, "Paris Store", "7.25","Courses", "2013-12-24"));
		insertFee(new Fee(null, "Kebab", "20.25","Courses", "2013-12-23"));
		insertFee(new Fee(null, "Brice", "15.25","Courses", "2013-12-22"));
		insertFee(new Fee(null, "Fnac", "3.25","Courses", "2013-12-21"));
	}
	
	public void insertParameter(String key, String value) {
		String sql = "insert into " + TABLE_NAME_PARAMETER
				+ "(key, val) values (?,?)";
		statement = database.compileStatement(sql);
		statement.bindString(1, key);
		statement.bindString(2, value);
		statement.executeInsert();
	}

	public void updateParameter(String key, String value) {
		ContentValues args = new ContentValues();
		args.put("val", value);
		database.update(TABLE_NAME_PARAMETER, args, "key=" + key, null);
	}
	
	public String getParameter(String key) {
		String req = "select val from parameter where key='" + key + "'";
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				return cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
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
	
	public Fee getFee(String id) {
		String req = "select f.label, f.amount, f.date, c.label, c.color "
						+ "from fee f, category c "
						+ "where f.id=" + id;
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				Fee fee = new Fee();
				fee.setId(id);
				fee.setLabel(cursor.getString(0));
				fee.setAmount(cursor.getString(1));
				fee.setDate(cursor.getString(2));
				fee.setCategory(cursor.getString(3));
				fee.setColor(cursor.getString(4));
				return fee;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
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
	
	public List<Category> getCategories(){
		List<Category> res = new ArrayList<Category>();
		Cursor cursor = database.query(TABLE_NAME_CATEGORY, new String[] { "id, label, color, budget" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Category cat = new Category();
				cat.setId(cursor.getString(0));
				cat.setLabel(cursor.getString(1));
				cat.setColor(cursor.getString(2));
				cat.setBudget(cursor.getString(3));
				res.add(cat);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return res;
	}
	
	public Category[] getSimpleCategories(){
		List<Category> res = new ArrayList<Category>();
		Cursor cursor = database.query(TABLE_NAME_CATEGORY, new String[] { "id, label, color, budget" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Category cat = new Category();
				cat.setId(cursor.getString(0));
				cat.setLabel(cursor.getString(1));
				cat.setColor(cursor.getString(2));
				cat.setBudget(cursor.getString(3));
				res.add(cat);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return res.toArray(new Category[res.size()]);
	}
	
	public Category getCategory(String id){
		Cursor cursor = database.query(TABLE_NAME_CATEGORY, new String[] { "id, label, color, budget" }, "id=" + id, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Category cat = new Category();
				cat.setId(cursor.getString(0));
				cat.setLabel(cursor.getString(1));
				cat.setColor(cursor.getString(2));
				cat.setBudget(cursor.getString(3));
				return cat;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
	}
	
	/******************** LES REQUETES DE LA MORT QUI TUE ****************************/
	// nom du mois actuel et total des depenses
	public Month getCurrentMonthDetails(){
		String req = "select sum(amount) as total, case strftime('%m', date('now')) when '01' then 'Janvier' when '02' then 'Fevrier' when '03' then 'Mars' when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' when '07' then 'Juillet' when '08' then 'Aout' when '09' then 'Septembre' when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end as label "
					+ "from fee "
					+ "where strftime('%m%Y', date) =  strftime('%m%Y', date('now'))";
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				return new Month(cursor.getString(1), cursor.getString(0));
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
		String req = "select c.label, sum(f.amount) as total, sum(f.amount)/tmp.totmonth as percent, c.color "
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
				cat.setColor(cursor.getString(3));
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
				Fee fee = new Fee();
				fee.setId(cursor.getString(0));
				fee.setDate(cursor.getString(1));
				fee.setAmount(cursor.getString(2));
				fee.setLabel(cursor.getString(3));	
				if(res.containsKey(cursor.getString(4))){
					 res.get(cursor.getString(4)).add(fee);
				}else{
					List<Fee> list = new ArrayList<Fee>();
					list.add(fee);
					res.put(cursor.getString(4), list);
				}
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return res;
	}
	
	// liste des mois: libellé + total
	public List<Month> getMonthsDetails(){
		List<Month> res = new ArrayList<Month>();
		String req = "select sum(amount) as total,  case strftime('%m', date) when '01' then 'Janvier' when '02' then 'Fevrier' when '03' "
						+ "then 'Mars' when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' when '07' then 'Juillet' when '08' "
						+ "then 'Aout' when '09' then 'Septembre' when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end "
						+ "as label "
						+ "from fee "
						+ "where strftime('%Y', date) =  strftime('%Y', date('now')) "
						+ "group by strftime('%m-%Y', date) "
						+ "order by strftime('%m-%Y', date) desc;";
						
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				Month month = new Month();
				month.setTotal(cursor.getString(0));
				month.setLabel(cursor.getString(1));
				res.add(month);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return res;
	}
	
	// liste des depenses pour l'année actuelle par mois
	public Map<String, List<Fee>> getFeesByMonths(){
		Map<String, List<Fee>> res = new HashMap<String, List<Fee>>();
		String req="select f.id, f.date, f.amount, f.label, c.color, c.label, case strftime('%m', date) "
					+ "when '01' then 'Janvier' when '02' then 'Fevrier' when '03' then 'Mars' "
					+ "when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' "
					+ "when '07' then 'Juillet' when '08' then 'Aout' when '09' then 'Septembre' "
					+ "when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end as label "
					+ "from fee f, category c "
					+ "where c.id = f.categoryid "
					+ "and strftime('%Y', f.date) =  strftime('%Y', date('now')) "
					+ "order by f.date desc;";
		
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				Fee fee = new Fee();
				fee.setId(cursor.getString(0));
				fee.setDate(cursor.getString(1));
				fee.setAmount(cursor.getString(2));
				fee.setLabel(cursor.getString(3));
				fee.setColor(cursor.getString(4));	
				fee.setCategory(cursor.getString(5));
				if(res.containsKey(cursor.getString(6))){
					 res.get(cursor.getString(6)).add(fee);
				}else{
					List<Fee> list = new ArrayList<Fee>();
					list.add(fee);
					res.put(cursor.getString(6), list);
				}
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return res;
	}
	
	// liste des budget par catégories pour le mois
	public Budget[] getBudgetDetails(){
		List<Budget> res = new ArrayList<Budget>();
		String req="select c.label, c.color, c.budget as goal, sum(f.amount) as total "
					+ "from fee f, category c "
					+ "where c.id = f.categoryid "
					+ "and strftime('%m%Y', f.date) =  strftime('%m%Y', date('now')) "
					+ "group by c.id "
					+ "order by total desc;";
		
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				Budget b = new Budget();
				b.setCategory(cursor.getString(0));
				b.setColor(cursor.getString(1));
				b.setGoal(cursor.getString(2));
				b.setTotal(cursor.getString(3));
				res.add(b);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return res.toArray(new Budget[res.size()]);
	}
	
	// GRAPHIQUE : variation de la somme des dépenses (y) de chaque catégorie 
	// sur une durée (x) selon une unité de temps.
	public CurveGraphic getGraphByWeek(int wStart, int wEnd){
		Map<String, List<TimeUnit>> res = new HashMap<String, List<TimeUnit>>();
		String req="select sum(ifnull(f.amount,0.0)) as total, d.week, c.label, c.color "
					+ "from datelist d, category c LEFT JOIN fee f on f.date = d.date and c.id = f.categoryid "
					+ "where  d.week between " + wStart + " and " + wEnd + " "
					+ "group by c.id, d.week "
					+ "order by c.label, d.week";
					
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				TimeUnit tu = new TimeUnit();
				tu.setValue(cursor.getDouble(0));
				tu.setLabel("s" + cursor.getString(1));
				tu.setColor(cursor.getString(3));
				if(res.containsKey(cursor.getString(2))){
					 res.get(cursor.getString(2)).add(tu);
				}else{
					List<TimeUnit> list = new ArrayList<TimeUnit>();
					list.add(tu);
					res.put(cursor.getString(2), list);
				}
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		CurveGraphic graph = new CurveGraphic();
		graph.setTimeUnits(res);
		if(!res.isEmpty()){
			graph.setTitle("Coucou!");
		}
		return graph;
	}
	
	// GRAPHIQUE: pourcentage de la somme de chaque catégorie pour 
	// une durée donnée (camembert ou anneaux)
	public RingGraphic getGraphCircle(String dStart, String dEnd){
		List<Category> res = new ArrayList<Category>();
		String req = "select sum(ifnull(f.amount,0.0)) as total, sum(ifnull(f.amount,0.0))/tmp.totmonth*100 as percent, c.label, c.color "
						+ "from fee f, category c, (select sum(amount) as totmonth "
						+ "from fee "
						+ "where date>='" + dStart + "' and date<='" + dEnd + "') as tmp "
						+ "where f.date>='2013-12-01' and f.date<='2013-12-31' and f.categoryid = c.id "
						+ "group by c.label";
						
		Cursor cursor = database.rawQuery(req, null);				
		if (cursor.moveToFirst()) {
			do {
				Category cat = new Category();
				cat.setTotal(cursor.getString(0));
				cat.setPercent(cursor.getString(1));
				cat.setLabel(cursor.getString(2));
				cat.setColor(cursor.getString(3));
				res.add(cat);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		RingGraphic graph = new RingGraphic();
		graph.setCategories(res);
		graph.setTitle("Coucou");
		return graph;
	}
	
	private static class OpenHelper extends SQLiteOpenHelper {
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE_NAME_CATEGORY + "(id integer primary key, label TEXT, color TEXT, budget NUMERIC)");
			db.execSQL("create table " + TABLE_NAME_FEE + "(id integer primary key, date DATE, amount NUMERIC, label TEXT, categoryid NUMERIC)");
			db.execSQL("create table " + TABLE_NAME_PARAMETER + "(key TEXT primary key, val TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FEE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORY);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PARAMETER);
			onCreate(db);
		}
	}
}
