package kr.or.ddit.batch.yoghurt.model;

public class CycleVo {
	private int cid;	// 고객번호
	private int pid;	// 제품번호
	private int day;	// 주간요일 ( 1이 일요일 , 2가 월요일 ... 7이 토요일)
	private int cnt;	// 수량 

	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
