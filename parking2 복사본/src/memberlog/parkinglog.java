package memberlog;

public class parkinglog {

	private int parking_id;
	private String car_number;
	private String in_time;
	private String out_time;
	private int mem_id;
	
	
	public parkinglog(int parking_id, String car_number, String in_time, String out_time, int mem_id) {
		super();
		this.parking_id = parking_id;
		this.car_number = car_number;
		this.in_time = in_time;
		this.out_time = out_time;
		this.mem_id = mem_id;
	}


	public int getParking_id() {
		return parking_id;
	}


	public void setParking_id(int parking_id) {
		this.parking_id = parking_id;
	}


	public String getCar_number() {
		return car_number;
	}


	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}


	public String getIn_time() {
		return in_time;
	}


	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}


	public String getOut_time() {
		return out_time;
	}


	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}


	public int getMem_id() {
		return mem_id;
	}


	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}


	@Override
	public String toString() {
		return "parkinglog [parking_id=" + parking_id + ", car_number=" + car_number + ", in_time=" + in_time
				+ ", out_time=" + out_time + ", mem_id=" + mem_id + "]";
	}
	
	
	
	
	
}
