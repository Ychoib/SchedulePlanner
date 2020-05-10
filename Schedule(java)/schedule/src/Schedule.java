
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
	private String name;
	private LocalDateTime starttime;
	private LocalDateTime endtime;
	private String memo;
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public LocalDateTime getstarttime() {
		return starttime;
	}
	public void setstarttime(String starttime) {
		LocalDateTime start;
		start = LocalDateTime.parse(starttime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.starttime = start;
	}
	public LocalDateTime getendtime() {
		return endtime;
	}
	public void setendtime(String endtime) {
		LocalDateTime end;
		end = LocalDateTime.parse(endtime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.endtime = end;
	}
	public String getmemo() {
		return memo;
	}
	public void setmemo(String memo) {
		this.memo = memo;
	}
	//생성자.
	public Schedule(String name,String starttime,String endtime,String memo) { 
		this.name = name;
		this.starttime = LocalDateTime.parse(starttime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.endtime = LocalDateTime.parse(endtime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.memo = memo;
	}
	//메모가 없을때 사용하는 생성자
	public Schedule(String name,String starttime,String endtime) { 
		this.name = name;
		this.starttime = LocalDateTime.parse(starttime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.endtime = LocalDateTime.parse(endtime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	public void print() {
		System.out.println(name + "//" + starttime + "//" + endtime + "//" + memo);
	}
}
