package Beijing_name;


import java.util.ArrayList;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 百度工具类
 * 
 * @author xuyw
 * @email xyw10000@163.com
 * @date 2014-06-22
 */
public class Cityname {
	public static JSONObject getCity(String lat, String lng) {
		JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result").getJSONObject("addressComponent");
		return obj;
	}

	public static JSONObject getLocationInfo(String lat, String lng) {
		String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + "," + lng + "&output=json&ak="
				+ "nYDfYsRHuoizqZFsHOp9z83qfDb9aulX" + "&pois=0";
		JSONObject obj = JSONObject.fromObject(HttpUtil.getRequest(url));
		return obj;
	}

	// 返回一个ArrayList,里面是所有csv文件的路径
	public static ArrayList<String> getcsvPath() {
		ArrayList<String> path = new ArrayList<String>();
		// 2017年2月份
		for (int j = 0; j < 6; ++j) {
			path.add("D:\\vis_data\\data\\" + Integer.toString(20170223 + j) + ".csv");
		}
		// 2017年3月份
		for (int j = 0; j < 31; ++j) {
			path.add("D:\\vis_data\\data\\" + Integer.toString(20170301 + j) + ".csv");
		}
		// 2017年4月份
		for (int j = 0; j < 26; ++j) {
			path.add("D:\\vis_data\\data\\" + Integer.toString(20170401 + j) + ".csv");
		}
		return path;
	}
	// 返回一个ArrayList,里面是所有分区文件的路径
		public static ArrayList<String> getlocationPath() {
			ArrayList<String> path = new ArrayList<String>();
			// 2017年2月份
			for (int j = 0; j < 6; ++j) {
				path.add("D:\\vis_data\\data\\" + Integer.toString(20170223 + j) + "_location.csv");
			}
			// 2017年3月份
			for (int j = 0; j < 31; ++j) {
				path.add("D:\\vis_data\\data\\" + Integer.toString(20170301 + j) + "_location.csv");
			}
			// 2017年4月份
			for (int j = 0; j < 26; ++j) {
				path.add("D:\\vis_data\\data\\" + Integer.toString(20170401 + j) + "_location.csv");
			}
			return path;
		}
		//label数据的path
		public static ArrayList<String> getlabelPath() {
			ArrayList<String> path = new ArrayList<String>();
			// 2017年2月份
			for (int j = 0; j < 6; ++j) {
				path.add("D:\\vis_data\\data_label\\label_" + Integer.toString(20170223 + j) + ".csv");
			}
			// 2017年3月份
			for (int j = 0; j < 31; ++j) {
				path.add("D:\\vis_data\\data_label\\label_" + Integer.toString(20170301 + j) + ".csv");
			}
			// 2017年4月份
			for (int j = 0; j < 26; ++j) {
				path.add("D:\\vis_data\\data_label\\label_" + Integer.toString(20170401 + j) + ".csv");
			}
			return path;
		}

	public static void main(String[] args) {
		//label的路径ArrayList
		ArrayList<String> labelpath = new ArrayList<String>();
		labelpath=Cityname.getlabelPath();
		//label的名称
		ArrayList<String> labelName = new ArrayList<String>();
		//每个label的数量
		int labelNum[] = new int[30];
		for (int i = 0; i < labelNum.length; ++i)
			labelNum[i] = 0;
		//每个文件中的数据
		ArrayList<String[]> labelInEachCsv = new ArrayList<String[]>();
		for(int i=0;i<labelpath.size();++i){ //每一个文件
			System.out.println(labelpath.get(i));
			labelInEachCsv = datacsv.getlabelfromcsv(labelpath.get(i));
			for(int j=0;j<labelInEachCsv.size();++j){ //每个文件中的每个数据
				String label=labelInEachCsv.get(j)[0];
				if(labelName.indexOf(label)==-1) labelName.add(label);  //添加区名
	    		++labelNum[labelName.indexOf(label)]; //信息
			}
			System.out.println("over");
		}
		System.out.println("label名称");
		for(int i=0;i<labelName.size();++i){
			System.out.print(labelName.get(i)+" ");
		}
		System.out.println(" ");
		System.out.println("label数量");
		for(int i=0;i<labelName.size();++i){
			System.out.print(labelNum[i]+" ");
		}
			
	}
}
