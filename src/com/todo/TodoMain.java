package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolist.txt");
		boolean quit = false;
		// 3_2 파일 저장
//		TodoUtil.loadList(l, "todolist.txt");			// ?????? 왜 자꾸 L로 자동변환되지???
		do {
			Menu.prompt();
			String choice = sc.next();
			switch (choice) {
			case "help":
				Menu.displaymenu();
				break;

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				TodoUtil.listAll(l, "due_date", 0);
				break;
			
			case "find":
				String keyword = sc.next().trim();
				TodoUtil.find(l, keyword);
				break;
				
			case "find_cate":
				String cate_keyword = sc.next().trim();
				TodoUtil.find_cate(l, cate_keyword);
				break;
				
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;
			
			case "comp":
				int complete = sc.nextInt();
				TodoUtil.comp(l, complete);
				break;
				
			case "ls_comp":
				TodoUtil.find_comp(l);
				break;
			
			case "importance":
				int impo_id = sc.nextInt();
				TodoUtil.importance(l, impo_id);
				break;
			case "mate":
				TodoUtil.mate(l);
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정해진 명령어를 사용하십시오.\n도움이 필요하다면 help 명령어를 입력하시오.\n");
				break;
			}
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
		sc.close();
	}
}
