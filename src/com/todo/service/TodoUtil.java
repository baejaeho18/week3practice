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
		
		String title, desc, current_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== �ű� �׸� �ۼ�\n"
				+ "�׸� �̸��� �Է��Ͻʽÿ�.\n>");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�̹� �ִ� �׸� �̸��Դϴ�.");
			return;
		}
		sc.nextLine();	// �� �ʿ����� ����?
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		desc = sc.nextLine();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        current_date = f.format(new Date());
		TodoItem t = new TodoItem(title, desc, current_date);
		list.addItem(t);
		System.out.println("����� �Ϸ�Ǿ����ϴ�.\n");
	}

	
	public static void deleteItem(TodoList l) {
		System.out.print("\n"
				+ "========== ���� �׸� ����\n"
				+ "������ �׸��� �̸��� �Է��Ͻÿ�.\n>" );
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		if (!l.isDuplicate(title)) System.out.println("�ش� �׸��� �������� �ʽ��ϴ�.\n");
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println(title+"�׸��� �����Ǿ����ϴ�.\n");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== ���� �׸� ����\n"
				+ "������ �׸� �̸��� �Է��Ͻÿ�.\n>" );
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �׸��Դϴ�.\n");
			return;
		}

		System.out.print("�ش� �׸񿡼� �����Ϸ��� �̸��� �Է��Ͻÿ�.\n>");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� �׸� �̸��Դϴ�.\n");
			return;
		}
		sc.nextLine();
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		        String current_date = f.format(new Date());
				TodoItem t = new TodoItem(new_title, new_description, current_date);
				l.addItem(t);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
			}
		}

	}
	

	public static void listAll(TodoList l) {
		System.out.println("\n========== ��� �׸� ���");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		System.out.println("");
	}
	
	
	// ���α׷� ���� �� �б� & ���� �� ����
	public static void saveList(TodoList l, String filename) {
		try {
			File file = new File("todolist.txt");
			Writer w = new FileWriter(filename);
			int i=0;
			for (TodoItem item : l.getList()) {
				TodoItem t = new TodoItem(item.getTitle(), item.getDesc(), item.getCurrent_date());
				w.write(t.toSaveString());
				i++;
			}
			w.close();
			System.out.println(i+"���� �׸��� "+filename+"�� ����Ǿ����ϴ�.");
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
			int i=0;
			while( (items = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(items, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc, current_date);
				l.addItem(t);
				i++;
//				System.out.println(title+desc+current_date);
			}
			br.close();
			System.out.println(i+"���� �׸��� "+filename+"���� ����Ǿ����ϴ�.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
