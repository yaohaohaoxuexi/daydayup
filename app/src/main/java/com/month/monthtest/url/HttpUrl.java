package com.month.monthtest.url;


public class HttpUrl {
	
	public static String URLOffice="http://appapi.kangzhi.com/app/doctor/officeList";
	public static String URLRegion="http://appapi.kangzhi.com/app/doctor/regionList";
	public static String URLSort="http://appapi.kangzhi.com/app/doctor/sortList";

	public static String urlORS(String office,String reg,String sort){
		
		String URLORS="http://appapi.kangzhi.com/app/doctor/index?office="+office+"&reg="+reg+"&sort="+sort+"&page=1&limit=20&newVersion=2.1.0";
	
		return URLORS;
	}
}
