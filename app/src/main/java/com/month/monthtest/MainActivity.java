package com.month.monthtest;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.month.monthtest.adapter.MyAdapter;
import com.month.monthtest.adapter.MyAdapter3;
import com.month.monthtest.bean.Bean12;
import com.month.monthtest.bean.BeanOffice;
import com.month.monthtest.url.HttpUrl;
import com.month.monthtest.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String tag = "MainActivity--->";
    private LinearLayout linear;
    private ListView listView;
    private ListView listView2;
    private ArrayList<BeanOffice> list;
    private ArrayList<Bean12> list1;
    private ArrayList<Bean12> list2;
    private ArrayList<Bean12> list3;
    private String list1_name;
    private String list2_id;
    private Handler mHandler = new Handler() {
        private MyAdapter adapter;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    adapter = new MyAdapter(MainActivity.this, list);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 1:
                    listView2.setAdapter(new MyAdapter3(MainActivity.this, list1));
                    break;
                case 2:
                    listView2.setAdapter(new MyAdapter3(MainActivity.this, list2));
                    break;
                case 3:
                    listView2.setAdapter(new MyAdapter3(MainActivity.this, list3));
                    break;

                default:
                    break;
            }
        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        getDataFromNet("全部科室", "", "0");

    }

    //初始化视图
    private void initView() {
        linear = (LinearLayout) findViewById(R.id.activity_main);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        listView = (ListView) findViewById(R.id.listView);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    //获取网络连接
    public void getDataFromNet(final String office, final String reg, final String sort) {
        new Thread() {

            public void run() {
                try {
                    URL url1 = new URL(HttpUrl.urlORS(office, reg, sort));
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setReadTimeout(3000);
                    conn.setRequestMethod("GET");
                    Log.d(tag, "-----------------------------------------------");
                    if (conn.getResponseCode() == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String json = inputStream2String(inputStream);
                        list = JsonUtils.parseJson(json);

                        mHandler.sendEmptyMessage(0);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    //字节流转换为字符串
    public String inputStream2String(InputStream inputStream) {
        String s1 = "";
        String s2 = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s1 = br.readLine()) != null) {
                s2 += s1;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s2;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                linear.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                popupWindow1(HttpUrl.URLOffice);
                break;
            case R.id.tv2:
                linear.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                popupWindow2(HttpUrl.URLRegion);
                break;
            case R.id.tv3:
                linear.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                popupWindow3(HttpUrl.URLSort);
                break;

            default:
                break;
        }
    }

    public void getDataFomNet1(final String url) {
        list1 = new ArrayList<Bean12>();
        new Thread() {
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setReadTimeout(3000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String json = inputStream2String(inputStream);

                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            String id = jsonArray.getJSONObject(i).getString("id");
                            String name = jsonArray.getJSONObject(i).getString("name");
                            list1.add(new Bean12(id, name));
                        }

                        mHandler.sendEmptyMessage(1);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    public void getDataFomNet2(final String url) {
        list2 = new ArrayList<Bean12>();
        new Thread() {
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setReadTimeout(3000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String json = inputStream2String(inputStream);

                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String id0 = null;
                            if (i == 0) {
                                id0 = jsonArray.getJSONObject(i).getString("id");
                            } else {
                                int id = jsonArray.getJSONObject(i).getInt("id");
                                id0 = String.valueOf(id);
                            }
                            String name = jsonArray.getJSONObject(i).getString("name");
                            list2.add(new Bean12(id0, name));
                        }

                        mHandler.sendEmptyMessage(2);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    public void getDataFomNet3(final String url) {
        list3 = new ArrayList<Bean12>();
        new Thread() {
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setReadTimeout(3000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String json = inputStream2String(inputStream);

                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            int id = jsonArray.getJSONObject(i).getInt("id");
                            String id0 = String.valueOf(id);
                            String name = jsonArray.getJSONObject(i).getString("name");
                            list3.add(new Bean12(id0, name));
                        }

                        mHandler.sendEmptyMessage(3);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }


    public void popupWindow1(String url) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        listView2 = (ListView) view.findViewById(R.id.listView2);
        getDataFomNet1(url);
        final PopupWindow pw = new PopupWindow(view, 320, 240);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pw.showAtLocation(linearLayout, Gravity.TOP, 0, 110);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                getDataFromNet(list1.get(position).getName(), "", "0");
                list1_name = list1.get(position).getName();
                pw.dismiss();
                linear.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
        });
    }

    public void popupWindow2(String url) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        listView2 = (ListView) view.findViewById(R.id.listView2);
        getDataFomNet2(url);
        final PopupWindow pw = new PopupWindow(view, 320, 240);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pw.showAtLocation(linearLayout, Gravity.TOP, 0, 110);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                getDataFromNet(list1_name, list2.get(position).getId(), "0");
                list2_id = list2.get(position).getId();
                pw.dismiss();
                linear.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
        });
    }

    public void popupWindow3(String url) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        listView2 = (ListView) view.findViewById(R.id.listView2);
        getDataFomNet3(url);
        final PopupWindow pw = new PopupWindow(view, 320, 240);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pw.showAtLocation(linearLayout, Gravity.TOP, 0, 110);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                getDataFromNet(list1_name, list2_id, list3.get(position).getId());

                pw.dismiss();
                linear.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
        });
    }

}


