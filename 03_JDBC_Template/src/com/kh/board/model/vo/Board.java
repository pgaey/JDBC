package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo;	// BNO NUMBER
	private String title;	// TITLE VARCHAR2(50 BYTE)
	private String content;	// CONTENT VARCHAR2(1000 BYTE)
	private Date createDate; // CREATE_DATE DATE
	private String writer;		 // WRITER NUMBER
	private String deleteYN; // DELETE_YN CHAR(1 BYTE)
	public Board() {
		super();
	}
	public Board(int boardNo, String title, String content, Date createDate, String writer, String deleteYN) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.writer = writer;
		this.deleteYN = deleteYN;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", writer=" + writer + ", deleteYN=" + deleteYN + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
