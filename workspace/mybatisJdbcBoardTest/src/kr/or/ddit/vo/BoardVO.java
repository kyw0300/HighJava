package kr.or.ddit.vo;

public class BoardVO {
	private int BOARD_NO;
	private String BOARD_TITLE;
	private String BOARD_WRITER;
	private String BOARD_DATE;
	private int BOARD_CNT;
	private String BOARD_CONTENT;
	public int getBOARD_NO() {
		return BOARD_NO;
	}
	public void setBOARD_NO(int bOARD_NO) {
		BOARD_NO = bOARD_NO;
	}
	public String getBOARD_TITLE() {
		return BOARD_TITLE;
	}
	public void setBOARD_TITLE(String bOARD_TITLE) {
		BOARD_TITLE = bOARD_TITLE;
	}
	public String getBOARD_WRITER() {
		return BOARD_WRITER;
	}
	public void setBOARD_WRITER(String bOARD_WRITER) {
		BOARD_WRITER = bOARD_WRITER;
	}
	public String getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(String bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}
	public int getBOARD_CNT() {
		return BOARD_CNT;
	}
	public void setBOARD_CNT(int bOARD_CNT) {
		BOARD_CNT = bOARD_CNT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	@Override
	public String toString() {
		return "BoardVO [BOARD_NO=" + BOARD_NO + ", BOARD_TITLE=" + BOARD_TITLE + ", BOARD_WRITER=" + BOARD_WRITER
				+ ", BOARD_DATE=" + BOARD_DATE + ", BOARD_CNT=" + BOARD_CNT + ", BOARD_CONTENT=" + BOARD_CONTENT + "]";
	}
	
	
}
