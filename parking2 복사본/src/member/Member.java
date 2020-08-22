package member;

import java.sql.Date;

public class Member {

	private int mem_id;
	private String member_time;
	private String member_car;
	private Boolean hybird_car;
	private Boolean reguler_car;
	
	
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public String getMember_time() {
		return member_time;
	}
	public void setMember_time(String member_time) {
		this.member_time = member_time;
	}
	public String getMember_car() {
		return member_car;
	}
	public void setMember_car(String member_car) {
		this.member_car = member_car;
	}
	public Boolean getHybird_car() {
		return hybird_car;
	}
	public void setHybird_car(Boolean hybird_car) {
		this.hybird_car = hybird_car;
	}
	public Boolean getReguler_car() {
		return reguler_car;
	}
	public void setReguler_car(Boolean reguler_car) {
		this.reguler_car = reguler_car;
	}
	
	
	
}
