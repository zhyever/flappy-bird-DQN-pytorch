package logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogSearch {
	/**
     * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
     * 
     * @param filePath
     *            文件路径[到达文件:如： D:\aa.txt]
     * @return 将这个文件按照每一行切割成数组存放到list中。
     */
    private static List<String> readInfo()
    {
    	String filePath = "logs/log.log";
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }
    
//    private static List<String> readError()
//    {
//    	String filePath = "logs/error.log";
//        List<String> list = new ArrayList<String>();
//        try
//        {
//            String encoding = "GBK";
//            File file = new File(filePath);
//            if (file.isFile() && file.exists())
//            { // 判断文件是否存在
//                InputStreamReader read = new InputStreamReader(
//                        new FileInputStream(file), encoding);// 考虑到编码格式
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;
//
//                while ((lineTxt = bufferedReader.readLine()) != null)
//                {
//                    list.add(lineTxt);
//                }
//                bufferedReader.close();
//                read.close();
//            }
//            else
//            {
//                System.out.println("找不到指定的文件");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("读取文件内容出错");
//            e.printStackTrace();
//        }
//
//        return list;
//    }
    
    private static boolean between(String start, String end, String str) {
    	boolean res = false;
    	
    	int yearStart = Integer.parseInt(start.split("-")[0]);
    	int yearEnd = Integer.parseInt(end.split("-")[0]);
    	int monthStart = Integer.parseInt(start.split("-")[1]);
    	int monthEnd = Integer.parseInt(end.split("-")[1]);
    	int dayStart = Integer.parseInt(start.split("-")[2]);
    	int dayEnd = Integer.parseInt(end.split("-")[2]);
    	
    	int yearNow = Integer.parseInt(str.split("-")[0]);
    	int monthNow = Integer.parseInt(str.split("-")[1]);
    	int dayNow = Integer.parseInt(str.split("-")[2]);
    	
    	if(yearNow < yearEnd && yearNow > yearStart) {
    		res = true;
    	}
    	
    	if(monthNow < monthEnd && monthNow > monthStart) {
    		if(yearNow == yearEnd && yearNow == yearStart) {
        		res = true;
        	}
    	}
    	
    	if(dayNow <= dayEnd && dayNow >= dayStart) {
    		if(monthNow == monthEnd && monthNow == monthStart) {
    			if(yearNow == yearEnd && yearNow == yearStart) {
    				res = true;
    			}
        	}
    	}
    	
    	return res;
    	
    }
    public static void main(String[] args) {
    	List<String> info = readInfo();
    	//List<String> error = readError();
    	
		Scanner in = new Scanner(System.in);
		System.out.println("请输入查询类型：按时间、按类型(error类型和info类型)、按操作");
		boolean flag = true;
		while(flag) {
			String strInput = in.nextLine();
			if(strInput.equals("按时间")) {
				System.out.println("请输入开始时间：eg: 2019-05-15");
				String start = in.nextLine();
				System.out.println("请输入结束时间：eg: 2019-05-17");
				String end = in.nextLine();
				
				for(int i = 0; i < info.size(); i++) {
					if(between(start, end, info.get(i).split(" ")[0])) {
						System.out.println(info.get(i));
					}
				}
				
				flag = false;
			}else if(strInput.equals("按类型")) {
				System.out.println("请输入类型：error或info");
				String str = in.nextLine();
				
				if(str.equals("error")) {
					for(int i = 0; i < info.size(); i++) {
						if(info.get(i).contains("ERROR")) {
							System.out.println(info.get(i));
						}
					}
				}else if(str.equals("info")) {
					for(int i = 0; i < info.size(); i++) {
						if(info.get(i).contains("INFO")) {
							System.out.println(info.get(i));
						}
					}
				}else {
					System.out.println("输入错误，退出程序");
				}
				flag = false;
			}else if(strInput.equals("按操作")) {
				System.out.println("输入操作类型：添加或删除");
				String str = in.nextLine();
				
				if(str.equals("添加")) {
					for(int i = 0; i < info.size(); i++) {
						if((info.get(i).contains("添加") || info.get(i).contains("新增")) && 
								!info.get(i).contains("错误")) {
							System.out.println(info.get(i));
						}
					}
				}else if(str.equals("删除")) {
					for(int i = 0; i < info.size(); i++) {
						if(info.get(i).contains("删除")  && !info.get(i).contains("错误")) {
							System.out.println(info.get(i));
						}
					}
				}else {
					System.out.println("输入错误，退出程序");
				}
				
				flag = false;
			}else {
				flag = true;
			}
		}
		
		in.close();
	}
    
}
