package model;


public class PointVO {

	// 1. 필드
	private int limitPoint; // 오늘 획득할 수 있는 한도포인트
	private int todayPoint; // 오늘 획득한 포인트
	private int bonusPoint; // 보상 받을수 있는 포인트
	private String bonusDesc; // 보상 내용
	private String bonusPw; // 입력한 보상을 수정할수 있는 비밀번호
	
	// 생성자
	public PointVO() {
		
	}

	// 2. 메소드
	// getter, setter
	public int getLimitPoint() {
		return limitPoint;
	}

	public void setLimitPoint(int limitPoint) {
		this.limitPoint = limitPoint;
	}

	public int getTodayPoint() {
		return todayPoint;
	}

	public void setTodayPoint(int todayPoint) {
		this.todayPoint = todayPoint;
	}

	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public String getBonusDesc() {
		return bonusDesc;
	}

	public void setBonusDesc(String bonusDesc) {
		this.bonusDesc = bonusDesc;
	}

	public String getBonusPw() {
		return bonusPw;
	}

	public void setBonusPw(String bonusPw) {
		this.bonusPw = bonusPw;
	}

}
