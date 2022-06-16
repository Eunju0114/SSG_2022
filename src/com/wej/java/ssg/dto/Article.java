package com.wej.java.ssg.dto;

public class Article {
	public int id;
	public String title;
	public String body;
	public String regDate;
	public int hit;

	public Article(int id, String title, String body, String regDate) {

		this(id, title, body, regDate, 0);
	}

	public Article(int id, String title, String body, String regDate, int hit) {

		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}

}