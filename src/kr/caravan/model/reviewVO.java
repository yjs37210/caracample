package kr.caravan.model;

public class reviewVO {

	private int num;
	private int score;
	private String cmt_review;
	private String time;
	
	
	public reviewVO() {
	}
	public reviewVO(int num, int score, String cmt_review, String time) {
		this.num = num;
		this.score = score;
		this.cmt_review = cmt_review;
		this.time = time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCmt_review() {
		return cmt_review;
	}
	public void setCmt_review(String cmt_review) {
		this.cmt_review = cmt_review;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "reviewVO [num=" + num + ", score=" + score + ", cmt_review=" + cmt_review + ", time=" + time + "]";
	}
	
	
	
}
