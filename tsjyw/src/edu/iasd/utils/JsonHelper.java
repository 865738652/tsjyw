package edu.iasd.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JsonHelper {

	public static String JsonToSelect2(Object objectToJson,String[] saveToPropertyNames,String[] replaceToNames)
	{
		final String[] a = saveToPropertyNames;
		PropertyFilter filter = new PropertyFilter(){
			@Override
			public boolean apply(Object object, String objectName, Object objectValue) {
				/*
				 * arg0�������ڵĶ���
				 * arg1���Ե�����
				 * arg2����ֵ
				 * ��û������JsonPropertyFilterʱ�������ô���Ρ���������ˣ��͵���apply����������falseʱ�������ô���Σ�
				 * ��apply��������trueʱ������ѭ�����������������ԣ�ֱ�Ӵ�����һ���ԣ���
				 */
				if(objectName.equals(a[0]) || objectName.equals(a[1]) || objectName.equals(a[2]) || objectName.equals(a[3]))
					return false;
				else
					return true;
			}
			
		};
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(filter);
		JSONArray jsonArray = JSONArray.fromObject(objectToJson,jsonConfig);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("button",jsonArray);
		String returnJsonString = jsonArray.toString();
		returnJsonString = returnJsonString.replace(replaceToNames[0],"text")
											.replace(replaceToNames[1],"children")
											.replace(replaceToNames[2],"id")
											.replace(replaceToNames[3],"text");
		return returnJsonString;
	}
	public static String JsonToSelect2One(Object object,String[] PropertyNames)
	{
		final String[] a = PropertyNames;
		PropertyFilter filter = new PropertyFilter(){

			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				// TODO Auto-generated method stub
				if(arg1.equals(a[0]) || arg1.equals(a[1]))
					return false;
				else
					return true;
			}};
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(filter);
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("button",jsonArray);
		String returnJsonString = jsonArray.toString()
									.replace(a[0], "id")
									.replace(a[1], "text");
		return returnJsonString;
	}

	

}
