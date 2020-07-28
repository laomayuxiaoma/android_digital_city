package com.example.muheda.citylocation.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amap.api.location.AMapLocation;
import com.example.amapservice.LocationPolicy;
import com.example.amapservice.LocationManager;
import com.example.amapservice.OnLocationListener;
import com.example.muheda.citylocation.R;
import com.example.muheda.citylocation.adapter.CityListAdapter;
import com.example.muheda.citylocation.db.DBHelper;
import com.example.muheda.citylocation.db.DBManager;
import com.example.muheda.citylocation.db.DatabaseHelper;
import com.example.muheda.citylocation.entity.City;
import com.example.muheda.citylocation.utils.AppUtil;
import com.example.muheda.citylocation.utils.DensityUtil;
import com.example.muheda.citylocation.utils.PingYinUtil;
import com.example.muheda.citylocation.utils.ResultUtil;
import com.example.muheda.citylocation.view.LetterListView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/*
* 城市选择 定位
* ps
*               Intent intent=new Intent(MainActivity.this, CityLocationActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("activityAction","android.intent.action.MAIN");
                intent.putExtras(bundle);
                startActivityForResult(intent,100);
* */
public class CityLocationActivity extends AppCompatActivity implements LetterListView.OnTouchingLetterChangedListener, AbsListView.OnScrollListener {
    private Toolbar toolbar;

    private ListView city_container;
    private LetterListView letter_container;

    private List<City> allCities = new ArrayList<>();
    private List<City> hotCities = new ArrayList<>();
    private List<String> historyCities = new ArrayList<>();
    private List<City> citiesData;
    private Map<String, Integer> letterIndex = new HashMap<>();
    private CityListAdapter cityListAdapter;


    private TextView letterOverlay; // 对话框首字母textview
    private OverlayThread overlayThread; // 显示首字母对话框
    private DatabaseHelper databaseHelper;

    private boolean isScroll;
    private boolean isOverlayReady;
    private Handler handler;
    private String lCity;
    private final String[][] selectTitle = {{"定位", "0"}, {"最近", "1"}, {"热门", "2"}, {"全部", "3"}};
    private final String[][] hotTitle = {{"北京", "2"}, {"上海", "2"}, {"广州", "2"}, {"深圳", "2"}, {"武汉", "2"}, {"天津", "2"}, {"西安", "2"}, {"南京", "2"}, {"杭州", "2"}, {"成都", "2"}, {"重庆", "2"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_location);
        try {
            getAction();
            initPackage();
            initView();

            initSqlite();
            setupActionBar();

            initCity();
            initHotCity();

            initHistoryCity();
            setupView();
            initOverlay();
            dispose();
        }catch (Exception e){

        }
    }

    private void dispose() {
        city_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0 && !"全部".equals(allCities.get(i).getName())) {
                    ResultUtil.resultBundle(CityLocationActivity.this, allCities.get(i).getName(), 200);
                    addHistoryCity(allCities.get(i).getName());
                }

            }
        });
        new LocationManager.Builder()
                        .setLocationPolicy(LocationPolicy.ONCE)
                        .setOnLocationListener(new OnLocationListener() {
                            @Override
                            public void getData(AMapLocation aMapLocation) {
                                cityListAdapter.setCity(aMapLocation.getCity());
                                cityListAdapter.notifyDataSetChanged();
                            }
                        }).build().start(this);

    }

    private void initPackage() {
        ResultUtil.packageName = AppUtil.getPackageName(CityLocationActivity.this);
    }

    private void initSqlite() {
        databaseHelper = new DatabaseHelper(this);
        handler = new Handler();
    }

    private void initView() {
        city_container = (ListView) findViewById(R.id.city_container);
        letter_container = (LetterListView) findViewById(R.id.letter_container);
    }

    private void getAction() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            ResultUtil.activityAction = bundle.getString("activityAction");
        }
    }

    private void initCity() {
        addCity(selectTitle);
//        citiesData = getCityList();
        DBManager dbManager = new DBManager(this);
        dbManager.copyDBFile();
        citiesData = dbManager.getAllCities();
        allCities.addAll(citiesData);
    }

    private void addCity(String[][] selectTitle) {
        for (String[] select : selectTitle) {
            City city = new City(select[0], select[1]);
            allCities.add(city);
        }
    }

    /**
     * 热门城市
     */
    public void initHotCity() {
        addHotCity(hotTitle);
    }

    private void addHotCity(String[][] selectTitle) {
        for (String[] select : selectTitle) {
            City city = new City(select[0], select[1]);
            hotCities.add(city);
        }
    }

    private void initHistoryCity() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recent_city order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            historyCities.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
    }

    public void addHistoryCity(String name) {
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from recent_city where name = '" + name + "'", null);
            if (cursor.getCount() > 0) {
                db.delete("recent_city", "name = ?", new String[]{name});
            }
            db.execSQL("insert into recent_city(name, date) values('" + name + "', " + System.currentTimeMillis() + ")");
            db.close();
        }catch (Exception e){

        }
    }


    private ArrayList<City> getCityList() {
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<City> list = new ArrayList<>();
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from city", null);
            City city;
            while (cursor.moveToNext()) {
                city = new City(cursor.getString(1), cursor.getString(2));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * a-z排序
     */
    Comparator comparator = new Comparator<City>() {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    private void setupView() {
        city_container.setOnScrollListener(this);
        letter_container.setOnTouchingLetterChangedListener(this);

        cityListAdapter = new CityListAdapter(this, allCities, hotCities, historyCities, letterIndex, lCity);
        city_container.setAdapter(cityListAdapter);
    }

    private void setupActionBar() {
        toolbar =findViewById(R.id.toolbar);
        TextView mTitle = findViewById(R.id.toolbar_title);
        mTitle.setText("选择城市");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        overlayThread = new OverlayThread();
        isOverlayReady = true;
        LayoutInflater inflater = LayoutInflater.from(this);
        letterOverlay = (TextView) inflater.inflate(R.layout.v_letter_overlay, null);
        letterOverlay.setVisibility(View.INVISIBLE);

        int width = DensityUtil.dp2px(this, 65);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                width, width,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(letterOverlay, lp);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isScroll) {
            return;
        }
        if (isOverlayReady) {
            String text;
            String name = allCities.get(firstVisibleItem).getName();
            String pinyin = allCities.get(firstVisibleItem).getPinyin();

            text = firstVisibleItem < 4 ? name : PingYinUtil.converterToFirstSpell(pinyin).substring(0, 1).toUpperCase();
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            letterOverlay.setTextSize(pattern.matcher(text).matches() ? 40 : 20);
            letterOverlay.setText(text);
            letterOverlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            // 延迟一秒后执行，让overlay为不可见
            handler.postDelayed(overlayThread, 1000);
        }
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        isScroll = false;
        if (letterIndex.get(s) != null) {
            int position = letterIndex.get(s);
            city_container.setSelection(position);
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            letterOverlay.setTextSize(pattern.matcher(s).matches()?40:20);
            letterOverlay.setText(s);
            letterOverlay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            handler.postDelayed(overlayThread, 1000);
        }
    }

    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            letterOverlay.setVisibility(View.GONE);
        }
    }
}
