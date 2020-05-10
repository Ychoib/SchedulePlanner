import java.io.BufferedWriter;
import java.io.*;
import java.io.FileWriter;
import java.util.*;
import java.lang.String;
import java.text.SimpleDateFormat;

public class ScheduleList {

	   //private 변수
	   private int scheduleNum;
	   private Schedule[] scheduleList = new Schedule[101];
	   
	   //생성자
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
	             //일정 정보가 비어있을때 오류.
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
	             //시작시간이 끝 시간보다 늦을때
	             if(infor[1].compareTo(infor[2])>0) {
	     			 System.out.println("Start time is later than End time ; Skip the input line : " + infor[0] + "//" + infor[1] + "//" + infor[2] + "//" + infor[3]);
	     			 continue;
	     		 }
	     		 //시간 형식이 맞지 않을때 오류.
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
	     		 
	             //메모의 유무에 따른 객체생성.
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
	   
	   
	   // 일정 수 반환
	   public int numSchedules() {
	      return scheduleNum;
	   }
	   // 원하는 일정 반환
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
