package JDBC;

import controller.PointController;
import model.PointVO;
import view.PointView;

public class Main {

	public static void main(String[] args) {
		PointVO pvo =  new PointVO();
		PointController pcontroller = new PointController();
		PointView pview = new PointView();
		pview.bonusStart();

	}

}
