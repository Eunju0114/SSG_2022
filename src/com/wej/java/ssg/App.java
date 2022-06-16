package com.wej.java.ssg;

import java.util.ArrayList;
import java.util.Scanner;

import com.wej.java.ssg.dto.Article;
import com.wej.java.ssg.util.Util;

public class App {

	private ArrayList<Article> articles;

	public App() {
		articles = new ArrayList<>();
	}

	public void start() {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);

		makeTestData();

		while (true) {
			System.out.print("명령어 : ");
			String cmd = sc.nextLine();

			cmd = cmd.trim();

			if (cmd.length() == 0) {
				continue;
			}

			if (cmd.equals("exit")) {

				System.out.println("==프로그램 끝==");
				sc.close();
				break;

			} else if (cmd.equals("write")) {

				int id = articles.size() + 1;

				System.out.print("제목 : ");
				String title = sc.nextLine();

				System.out.print("내용 : ");
				String body = sc.nextLine();

				String regDate = Util.getNowDateStr();
				Article article = new Article(id, title, body, regDate);

				articles.add(article);

				System.out.println(id + "번 글이 생성되었습니다.");

			} else if (cmd.equals("list")) {

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;

				}

				System.out.println("번호 | 조회 | 제목");
				for (int i = 0; i < articles.size(); i++) {

					Article article = articles.get(i);

					System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);
				}

			} else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				foundArticle.increaseHit();

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("조회수 : " + foundArticle.hit);

			} else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {

					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}

				}

				if (foundIndex == -1) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundIndex);
				System.out.println(id + "번 게시물이 삭제되었습니다.");

			} else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}

				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.print("수정할 제목 : ");
				String modifyTitle = sc.nextLine();

				System.out.print("수정할 내용 : ");
				String modifybody = sc.nextLine();

				foundArticle.title = modifyTitle;
				foundArticle.body = modifybody;
				foundArticle.regDate = Util.getNowDateStr();

				System.out.println(id + "번 게시물이 수정되었습니다.");

			} else {
				System.out.println(cmd + "(은)는 존재하지 않는 명령어 입니다.");

			}

		}

	}

	private void makeTestData() {
		articles.add(new Article(1, "제목1", "내용1", Util.getNowDateStr(), 10));
		articles.add(new Article(2, "제목2", "내용2", Util.getNowDateStr(), 42));
		articles.add(new Article(3, "제목3", "내용3", Util.getNowDateStr(), 21));
	}

}
