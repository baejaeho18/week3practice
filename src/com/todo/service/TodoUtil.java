package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 신규 항목 작성\n"
				+ "항목 이름을 입력하십시오.\n>");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 있는 항목 이름입니다.");
			return;
		}
		sc.nextLine();	// 왜 필요한지 알지?
		System.out.print("세부 설명을 입력하시오.\n>");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("등록이 완료되었습니다.\n");
	}

	
	public static void deleteItem(TodoList l) {
		System.out.print("\n"
				+ "========== 기존 항목 삭제\n"
				+ "삭제할 항목의 이릅을 입력하시오.\n>" );
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		if (!l.isDuplicate(title)) System.out.println("해당 항목은 존재하지 않습니다.\n");
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println(title+"항목은 삭제되었습니다.\n");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 기존 항목 수정\n"
				+ "수정할 항목 이름을 입력하시오.\n>" );
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 항목입니다.\n");
			return;
		}

		System.out.print("해당 항목에서 수정하려는 이름을 입력하시오.\n>");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 항목 이름입니다.\n");
			return;
		}
		sc.nextLine();
		System.out.print("세부 설명을 입력하시오.\n>");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정이 완료되었습니다.\n");
			}
		}

	}
	

	public static void listAll(TodoList l) {
		System.out.println("\n========== 모든 항목 출력");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "]\t" + item.getDesc());
		}
		System.out.println("");
	}
}
