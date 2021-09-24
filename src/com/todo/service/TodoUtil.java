package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n========== 신규 항목 작성\n이름을 입력하십시오.\n>");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 있는 항목 이름입니다.\n");
			return;
		}
		System.out.print("카테고리를 입력하십시오.\n>");
		category = sc.next();
		sc.nextLine();	// 왜 필요한지 알지?
		System.out.print("세부 설명을 입력하시오.\n>");
		desc = sc.nextLine().trim();	// trim 앞뒤 공백 제거
		System.out.print("기한을 입력하십시오(YYYYMMDD)\n>");
		due_date = sc.next();
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("등록이 완료되었습니다.\n");
	}

	
	public static void deleteItem(TodoList l) {
		System.out.print("\n"
				+ "========== 기존 항목 삭제\n"
				+ "삭제할 항목의 번호를 입력하시오.\n>");
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		if (l.size() < num) System.out.println("해당 항목은 존재하지 않습니다.\n");
//		for (TodoItem item : l.getList()) {
//			if (title.equals(item.getTitle())) {
//				l.deleteItem(item);
//				System.out.println(title+"항목은 삭제되었습니다.\n");
//				break;
//			}
//		}
		else {
			l.deleteItem(num-1);
			System.out.println(num+"번 항목은 삭제되었습니다.\n");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 기존 항목 수정\n"
				+ "수정할 항목 번호를 입력하시오.\n>" );
		int num = sc.nextInt();
		if (l.size() < num) {
			System.out.println("존재하지 않는 항목입니다.\n");
			return;
		}
		System.out.print("해당 항목에서 수정하려는 이름을 입력하시오.\n>");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 항목 이름입니다.\n");   
			return;
		}
		System.out.print("카테고리 명을 입력하시오.\n>");
		String new_category = sc.next();
		sc.nextLine();
		System.out.print("세부 설명을 입력하시오.\n>");
		String new_description = sc.nextLine().trim();
		System.out.print("기한을 입력하시오(YYYY/MM/DD).\n>");
		String new_due_date = sc.next();
//		for (TodoItem item : l.getList()) {
//			if (item.getTitle().equals(title)) {
//				l.deleteItem(item);
////				SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
////		        String current_date = f.format(new Date());
////				TodoItem t = new TodoItem(new_title, new_description, current_date);
//				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
//				l.addItem(t);
//				System.out.println("수정이 완료되었습니다.\n");
//			}
//		}
		l.deleteItem(num-1);
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		l.addItem(t);
		System.out.println("수정이 완료되었습니다.\n");
	}
	

	public static void listAll(TodoList l) {
//		System.out.println("\n========== 모든 항목 출력(총 "+l.size()+"개)");
//		int serial_num = 1;
//		for (TodoItem item : l.getList()) {
//			System.out.print(serial_num+". ");
//			System.out.println(item.toString());
//		}
//		System.out.println("");	
		l.listAll();	// TodoList의 listAll메소드를 활성화하면 위의 7줄을 이 1줄로 대체할 수 있음
	}
	
	
	// 프로그램 시작 시 읽기 & 종료 시 저장
	public static void saveList(TodoList l, String filename) {
		try {
			File file = new File("todolist.txt");
			Writer w = new FileWriter(filename);
			int count=0;
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
				count++;
			}
			w.close();
			System.out.println(count+"개의 항목이 "+filename+"에 저장되었습니다.");
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String items;
			int count=0;
			while( (items = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(items, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(category, title, desc, due_date);
				t.setCurrent_date(current_date);
				l.addItem(t);
				count++;
//				System.out.println(title+desc+current_date);
			}
			br.close();
			System.out.println(count+"개의 항목이 "+filename+"에서 저장되었습니다.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
