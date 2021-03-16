package kr.caravan.model;
public class carsVO {
	private String car_name;
	private String pw;
	
	public carsVO() {
		
	}
	public carsVO(String car_name, String pw) {
		this.car_name = car_name;
		this.pw = pw;
	}
	
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "carsVO [car_name=" + car_name + ", pw=" + pw + "]";
	}
	
}
