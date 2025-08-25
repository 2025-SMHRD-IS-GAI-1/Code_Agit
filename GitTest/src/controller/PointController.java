package controller;

import java.time.LocalDate;

import model.PointVO;

public class PointController {
    PointVO model = new PointVO(); // 실제 데이터 저장소
    LocalDate lastResetDate; // 날짜 리셋 상태는 컨트롤러가 관리

    public PointController() {
        this.model = model;
   
        model.setLimitPoint(200); // 한도는 항상 200으로 고정
        model.setTodayPoint(0); // 오늘 포인트는 0부터 시작
        
        lastResetDate = LocalDate.now(); // 다음날은 포인트 리셋

    }
    
    // 날짜가 바뀌면 포인트를 초기화하는 메소드
    public void resetNewDay() {
        LocalDate today = LocalDate.now();
        if (!today.equals(lastResetDate)) {
            model.setTodayPoint(0);
            model.setLimitPoint(200);
            lastResetDate = today;
        }
    }
    
    // 포인트 획득
    public void addPoint(int point) {
    	resetNewDay();  // 날짜가 바뀌면 포인트를 초기화하는 메소드
    	int limit = model.getLimitPoint(); // 항상 200
    	int current = model.getTodayPoint(); // 현재 포인트
    	int newPoint = current + point; // 포인트 더한 값

        if (newPoint > limit) {
            model.setTodayPoint(limit);   // limitPoint까지만 저장
        } else {
            model.setTodayPoint(newPoint); // 제한이 넘지 않으면 그대로 저장
        }
    }
    
    // 포인트 차감
    public void subtractPoint(int point) {
    	resetNewDay(); // 날짜가 바뀌면 포인트를 초기화하는 메소드
        int current = model.getTodayPoint();
        int newPoint = current - point;

        if (newPoint < 0) {
            model.setTodayPoint(0); // 포인트가 0이 최소값이 되게
        } else {
            model.setTodayPoint(newPoint); // 아니면 그대로 저장
        }
    }
    
	// 보상 내용·비밀번호 설정
    public boolean bonusInfo(String desc, int bonusPoint, String pw) {
    	resetNewDay(); // 날짜가 바뀌면 포인트를 초기화하는 메소드
        String savedPw = model.getBonusPw();
        if (savedPw != null && !savedPw.isBlank()) {
        	return false; // 비밀번호가 이미 설정되어 있다면 보상을 새로 만들수 없음
        }
        model.setBonusDesc(desc != null ? desc : "");
        if (bonusPoint != 0) {
            if (bonusPoint < 0) {
                model.setBonusPoint(0);
            } else {
                model.setBonusPoint(bonusPoint);
            }
        }
        model.setBonusPw((pw != null && !pw.isBlank()) ? pw : "0000");
        return true;
    }
    
    // 보상 내용·비밀번호 수정
    public boolean bonusUpdate(String currentPw, String newDesc, Integer newBonusPoint, String newPw) {
    	resetNewDay(); // 날짜가 바뀌면 포인트를 초기화하는 메소드
        if (!model.getBonusPw().equals(currentPw)) {
        	return false; // 비번 다르면 실패
        }
        if (newDesc != null) {
        	model.setBonusDesc(newDesc);
        }
        if (newBonusPoint != null) {
        	model.setBonusPoint(Math.max(0, newBonusPoint));
        }
        if (newPw != null && !newPw.isBlank()) {
        	model.setBonusPw(newPw);
        }
        
        return true;
    }
    
    // 보상 포인트 PointView에서 조회
    public int getBonusPoint() {
    	resetNewDay(); // 날짜가 바뀌면 포인트를 초기화하는 메소드
    	return model.getBonusPoint();
    }
    
    // 보상 내용 PointView에서 조회
    public String getBonusDesc() {
    	resetNewDay(); // 날짜가 바뀌면 포인트를 초기화하는 메소드
    	return model.getBonusDesc();
    }
    
    // 보상이 등록되어 있는지 확인하는 메소드
    public boolean isBonusSet() {
        return model.getBonusPw() != null && !model.getBonusPw().isBlank();
    }
    
    // 입력받은 비밀번호와 현재 보상 비밀번호가 같은지 확인
    public boolean verifyBonusPw(String pw) {
        resetNewDay();
        return isBonusSet() && model.getBonusPw().equals(pw);
    }
}
