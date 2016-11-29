package com.month.monthtest.utils;


import com.google.gson.Gson;
import com.month.monthtest.bean.BeanData;
import com.month.monthtest.bean.BeanDocInfo;
import com.month.monthtest.bean.BeanOffice;

import java.util.ArrayList;


public class JsonUtils {
	
	public static ArrayList<BeanOffice> parseJson(String json){
		Gson gson = new Gson();
		BeanData beanData = gson.fromJson(json, BeanData.class);
		BeanDocInfo beanDocInfo = beanData.getData();
		ArrayList<BeanOffice> list = beanDocInfo.getDocinfo();
		return list;
	}
	
}
