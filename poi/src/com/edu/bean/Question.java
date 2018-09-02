package com.edu.bean;

import java.util.Date;

public class Question {
	private Integer question_id;
    private String question_chapter;
    private Integer question_difficult;
    private String question_title;
    private Date question_time;
    private String question_author;
    private String question_answer;
    private String question_para1;
    private String question_para2;
    private String question_para3;
    
	public Integer getQuestionId() {
		return question_id;
	}
	public void setQuestionId(Integer questionId) {
		this.question_id = questionId;
	}
	public String getQuestionChapter() {
		return question_chapter;
	}
	public void setQuestionChapter(String questionChapter) {
		this.question_chapter = questionChapter;
	}
	public Integer getQuestionDifficult() {
		return question_difficult;
	}
	public void setQuestionDifficult(Integer questionDifficult) {
		this.question_difficult = questionDifficult;
	}
	public String getQuestionTitle() {
		return question_title;
	}
	public void setQuestionTitle(String questionTitle) {
		this.question_title = questionTitle;
	}
	public Date getQuestionTime() {
		return question_time;
	}
	public void setQuestionTime(Date questionTime) {
		this.question_time = questionTime;
	}
	public String getQuestionAuthor() {
		return question_author;
	}
	public void setQuestionAuthor(String questionAuthor) {
		this.question_author = questionAuthor;
	}
	public String getQuestionAnswer() {
		return question_answer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.question_answer = questionAnswer;
	}
	public String getQuestionPara1() {
		return question_para1;
	}
	public void setQuestionPara1(String questionPara1) {
		this.question_para1 = questionPara1;
	}
	public String getQuestionPara2() {
		return question_para2;
	}
	public void setQuestionPara2(String questionPara2) {
		this.question_para2 = questionPara2;
	}
	public String getQuestionPara3() {
		return question_para3;
	}
	public void setQuestionPara3(String questionPara3) {
		this.question_para3 = questionPara3;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + question_id + ", questionChapter=" + question_chapter + ", questionDifficult="
				+ question_difficult + ", questionTitle=" + question_title + ", questionTime=" + question_time
				+ ", questionAuthor=" + question_author + ", questionAnswer=" + question_answer + ", questionPara1="
				+ question_para1 + ", questionPara2=" + question_para2 + ", questionPara3=" + question_para3 + "]";
	}
}
