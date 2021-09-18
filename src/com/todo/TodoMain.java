package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		do {
//			int i=0;
//			Menu.displaymenu(i);
			Menu.displaymenu();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			case "help":
//				i=1;
//				Menu.displaymenu(i);
				Menu.prompt();
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

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정해진 명령어를 사용하십시오.\n도움이 필요하다면 help 명령어를 입력하시오.\n");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
