import java.io.BufferedWriter;
import java.io.*;
import java.io.FileWriter;
import java.util.*;
import java.lang.String;
import java.text.SimpleDateFormat;

public class ScheduleList {

	   //private ����
	   private int scheduleNum;
	   private Schedule[] scheduleList = new Schedule[101];
	   
	   //������
	   public ScheduleList(String filename){
	      scheduleNum=0;
	      String[] infor;
	      File file = new File(filename);
	      try {
	         Scanner input = new Scanner(file);
	         while(input.hasNext()) { 
	             String line = input.nextLine();
	             if(!line.contains("//")) continue;
	             
	             infor = line.trim().split("//");
	             for(int i=0;i<infor.length;i++) {
	             	 infor[i] = infor[i].trim();
	             }
	             //���� ������ ��������� ����.
	             if(infor[0].equals("")) {
	            	 System.out.println("No Schedule Title ; Skip the input line : " + infor[0] + "//" + infor[1] + "//" + infor[2] + "//" + infor[3]);
	            	 continue;
	             }
	             else if(infor[1].equals("")){
	            	 System.out.println("No Schedule StartTime ; Skip the input line : " + infor[0] + "//" + infor[1] + "//" + infor[2] + "//" + infor[3]);
	            	 continue;
	             }
	             else if(infor[2].equals("")){
	            	 System.out.println("No Schedule EndTime ; Skip the input line : " + infor[0] + "//" + infor[1] + "//" + infor[2] + "//" + infor[3]);
	            	 continue;
	             }
	             //���۽ð��� �� �ð����� ������
	             if(infor[1].compareTo(infor[2])>0) {
	     			 System.out.println("Start time is later than End time ; Skip the input line : " + infor[0] + "//" + infor[1] + "//" + infor[2] + "//" + infor[3]);
	     			 continue;
	     		 }
	     		 //�ð� ������ ���� ������ ����.
	             SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	     		 dateformat.setLenient(false);
	     		 try {
	     		 	 dateformat.parse(infor[1]);
	     		 	 dateformat.parse(infor[2]);
	     		 }
	     		 catch(Exception e) {
	     			System.out.println("Wrong Date Format ; Skip the input line : " + infor[0] + "//" + infor[1] + "//" + infor[2] + "//" + infor[3]);
	     			continue;
	     		 }
	     		 
	             //�޸��� ������ ���� ��ü����.
	             if(infor.length == 3) {
	            	 scheduleList[scheduleNum]= new Schedule(infor[0],infor[1],infor[2]);
	             }
	             else if(infor.length == 4) {
	            	 scheduleList[scheduleNum]= new Schedule(infor[0],infor[1],infor[2],infor[3]);
	             }
	             scheduleNum++;
	         }
	         input.close(); 
	      }catch(Exception e){
	         System.out.println(e.toString()); 
	      }
	      
	      
	   }
	   
	   
	   // ���� �� ��ȯ
	   public int numSchedules() {
	      return scheduleNum;
	   }
	   // ���ϴ� ���� ��ȯ
	   public Schedule getSchedule(int i) {
	      return scheduleList[i];
	   }
	   
	   public void outputfile(ScheduleList name) {
	      File outputfile = new File("schedule-file(output).data");
	      FileWriter writer = null;
	      try {
	         writer = new FileWriter(outputfile,false);
	         BufferedWriter bufferedWriter = new BufferedWriter(writer);
	         for(int i=0;i<name.numSchedules();i++) {
	            writer.write(name.getSchedule(i).getname());
	            writer.write(name.getSchedule(i).getstarttime().toString());
	            writer.write(name.getSchedule(i).getendtime().toString());
	            writer.write(name.getSchedule(i).getname());
	            writer.flush();
	         }
	         bufferedWriter.close();
	      }
	      catch(Exception e){
	         System.out.println("Error - outputfile write fail");
	      }
	      
	   }

}
