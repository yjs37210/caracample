package kr.caravan.model;

public class consultVO {

	private int num;
	private String cmt;
	private String time;
	private String car_name;
	private String tel;
	
	public consultVO() {
	}
	public consultVO(int num, String cmt, String time) {
		this.num = num;
		this.cmt = cmt;
		this.time = time;
	}
	public consultVO(int num, String time, String car_name, String cmt, String tel) {
		this.num = num;
		this.time = time;
		this.car_name = car_name;
		this.cmt = cmt;
		this.tel = tel;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "consultVO [num=" + num + ", cmt=" + cmt + ", time=" + time + ", car_name=" + car_name + ", tel=" + tel
				+ "]";
	}

	
	
}
