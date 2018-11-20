package Beijing_name;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.io.File;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class datacsv {
	public static ArrayList<String[]> getDatafromcsv(String filePath) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>();
			CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
			// reader.readHeaders(); //������ͷ,��������ע�͵�

			while (reader.readRecord()) {
				csvList.add(reader.getValues()); // ���ж�ȡ������ÿһ�е�������ӵ�list����
			}
			reader.close();
			// ����ע��
			//System.out.println("��ȡ��������" + (csvList.size()-1));
			// String location[]=new String[3];
			for (int row = 1; row < csvList.size(); row++) {
				// location�Ķ���Ҫ���������棬����result���涼�����һ�ε�ֵ����֪��Ϊɶ
				// java�ı���˳�����ȸ�ֵ�����еı�����
				String location[] = new String[3];
				location[0] = csvList.get(row)[6]; // lat
				location[1] = csvList.get(row)[5]; // lng
				location[2] = Integer.toString(row - 1);
				// �ж������Ƿ�����
				if (location[0].length() == 0 || location[1].length() == 0)
					System.out.println("Data isn't completed " + (row - 1));
				// System.out.println(location);
				result.add(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//��ȡ���ŵ�md5
	public static ArrayList<String[]> getmd5fromcsv(String filePath) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>();
			CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
			// reader.readHeaders(); //������ͷ,��������ע�͵�

			while (reader.readRecord()) {
				csvList.add(reader.getValues()); // ���ж�ȡ������ÿһ�е�������ӵ�list����
			}
			reader.close();
			// ����ע��
			//System.out.println("��ȡ��������" + (csvList.size()-1));
			// String location[]=new String[3];
			for (int row = 1; row < csvList.size(); row++) {
				// location�Ķ���Ҫ���������棬����result���涼�����һ�ε�ֵ����֪��Ϊɶ
				// java�ı���˳�����ȸ�ֵ�����еı�����
				String md5[] = new String[1];
				md5[0] = csvList.get(row)[0]; //md5 
				result.add(md5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static ArrayList<String[]> getlocationfromcsv(String filePath) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>();
			CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
			// reader.readHeaders(); //������ͷ,��������ע�͵�

			while (reader.readRecord()) {
				csvList.add(reader.getValues()); // ���ж�ȡ������ÿһ�е�������ӵ�list����
			}
			reader.close();
			// ����ע��
			//System.out.println("��ȡ��������" + (csvList.size()-1));
			// String location[]=new String[3];
			for (int row = 1; row < csvList.size(); row++) {
				// location�Ķ���Ҫ���������棬����result���涼�����һ�ε�ֵ����֪��Ϊɶ
				// java�ı���˳�����ȸ�ֵ�����еı�����
				String location[] = new String[1];
				location[0] = csvList.get(row)[0]; // locationName
				result.add(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//��ȡcsv�е�label����
	public static ArrayList<String[]> getlabelfromcsv(String filePath) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>();
			CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
			// reader.readHeaders(); //������ͷ,��������ע�͵�

			while (reader.readRecord()) {
				csvList.add(reader.getValues()); // ���ж�ȡ������ÿһ�е�������ӵ�list����
			}
			reader.close();
			// ����ע��
			//System.out.println("��ȡ��������" + (csvList.size()-1));
			// String location[]=new String[3];
			for (int row = 1; row < csvList.size(); row++) {
				// location�Ķ���Ҫ���������棬����result���涼�����һ�ε�ֵ����֪��Ϊɶ
				// java�ı���˳�����ȸ�ֵ�����еı�����
				String location[] = new String[1];
				location[0] = csvList.get(row)[7]; // label
				result.add(location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void NumToCsv(ArrayList<String> name, int[] num) {
		String path="D:\\vis_data\\data\\final_data.csv";
		boolean alreadyExists = new File(path).exists();
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(path, true), ',');
			if (!alreadyExists)
			{
				csvOutput.write("name");
				csvOutput.write("num");
				csvOutput.endRecord();
			}
			for(int i=0;i<name.size();++i){
				csvOutput.write(name.get(i));
				csvOutput.write(Integer.toString(num[i]));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (FileNotFoundException e) {
			// ����File��������ʱ���쳣
			e.printStackTrace();
		} catch (IOException e) {
			// ����BufferedWriter����ر�ʱ���쳣
			e.printStackTrace();
		}
	}
	
	public static void locationToCsv(ArrayList<String> loc, String Path){
		boolean alreadyExists = new File(Path).exists();
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(Path, true), ',');
			if (!alreadyExists)
			{
				csvOutput.write("district");
				csvOutput.endRecord();
			}
			for(int i=0;i<loc.size();++i){
				csvOutput.write(loc.get(i));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (FileNotFoundException e) {
			// ����File��������ʱ���쳣
			e.printStackTrace();
		} catch (IOException e) {
			// ����BufferedWriter����ر�ʱ���쳣
			e.printStackTrace();
		}
		System.out.println("write successfully");
	}
}
