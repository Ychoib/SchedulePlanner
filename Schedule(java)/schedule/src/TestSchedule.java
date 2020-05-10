
public class TestSchedule {
	public static void main(String[] args) { 
		ScheduleList list = new ScheduleList("schedule-file.data");

		if(list != null) {
			for(int i=0;i<list.numSchedules();i++) {
				list.getSchedule(i).print();
			}
		}
		list.outputfile(list);
	}
}
