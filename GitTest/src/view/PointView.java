package view;

import java.util.Scanner;

import controller.PointController;

public class PointView {

	Scanner sc = new Scanner(System.in);

	PointController pcontroller = new PointController();

	public void bonusStart() {
		while (true) {
			bonusMenu();
			int num = sc.nextInt();

			if (num == 1) {
                if (pcontroller.isBonusSet()) {
                    System.out.println("⚠️ 이미 보상이 설정되어 있습니다. [2] 보상 수정 메뉴를 이용하세요.");
                } else {
                    bonusInfo();
                }
            } else if (num == 2) {
				bonusUpdate();
			} else if (num == 3) {
				bonusShow();
			} else if (num == 4) {
				System.out.println("ℹ️ 프로그램을 종료합니다. 👋");
				break;
			} else {
				System.out.println("❌ 1~4 중에서 입력해주세요.");
			}
		}
	}

	// 보상 설정하는 메소드
	public void bonusMenu() {
		System.out.println();
        System.out.println("========================================");
        System.out.println("           🎁 보상 관리 시스템 🎁");
        System.out.println("========================================");
        System.out.println("  [1] 보상 입력  (최초 1회만 가능)");
        System.out.println("  [2] 보상 수정  (비밀번호 필요)");
        System.out.println("  [3] 보상 조회");
        System.out.println("  [4] 종료");
        System.out.println("----------------------------------------");
        System.out.print("👉 메뉴 선택 >> ");
	}

	// 보상 정보 입력하는 메소드
	public void bonusInfo() {
		sc.nextLine();

		System.out.print("보상 내용: ");
		String desc = sc.nextLine();

		System.out.print("보상 포인트: ");
		int pt = sc.nextInt();
		sc.nextLine();

		System.out.print("비밀번호: ");
		String pw = sc.nextLine();

		boolean ok = pcontroller.bonusInfo(desc, pt, pw);
		if (ok) {
			System.out.println("✅ 보상 최초 설정 완료.");
        } else {
        	System.out.println("⚠️ 이미 보상이 설정되어 있습니다.");
        }
	}

	public void bonusUpdate() {
		sc.nextLine();

		System.out.print("현재 비밀번호 입력 >> ");
		String currentPw = sc.nextLine();
		
		 if (!pcontroller.verifyBonusPw(currentPw)) {
		        System.out.println("❌ 비밀번호가 일치하지 않습니다.");
		        return; 
		    }

		System.out.print("새 보상 내용 >> ");
		String newDesc = sc.nextLine();
		if (newDesc.isBlank())
			newDesc = null;

		System.out.print("새 보상 포인트 >> ");
		String inputPt = sc.nextLine();
		Integer newPt = null;
		if (!inputPt.isBlank()) {
			try {
				newPt = Integer.parseInt(inputPt);
			} catch (NumberFormatException e) {
				System.out.println("❌ 숫자만 입력하세요.");
				return;
			}
		}

		System.out.print("새 비밀번호: ");
		String newPw = sc.nextLine();
		if (newPw.isBlank())
			newPw = null;

		boolean ok = pcontroller.bonusUpdate(currentPw, newDesc, newPt, newPw);
		 if (ok) {
			 System.out.println("✅ 보상 수정 완료.");
	        } else {
	        	System.out.println("❌ 수정에 실패했습니다.");
	        }	}

	public void bonusShow() {
		System.out.println("\n📌 현재 설정된 보상 정보");
	    System.out.println("----------------------------------------");
		String info = pcontroller.getBonusDesc();
		int point = pcontroller.getBonusPoint();
		if (info == null || point == 0) {
            System.out.println("보상이 아직 설정되지 않았습니다.");
        } else {
            System.out.println("보상내용   : " + info);
            System.out.println("보상포인트 : " + point);
        }
        System.out.println("----------------------------------------\n");
	}
}
