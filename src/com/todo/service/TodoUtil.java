package com.todo.service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		String category, title, desc, due_date;
		Scanner s = new Scanner(System.in);
		System.out.print("\n========== 신규 항목 작성\n이름을 입력하십시오.\n>");
		title = s.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 있는 항목 이름입니다.\n");
			return;
		}
		System.out.print("카테고리를 입력하십시오.\n>");
		category = s.next();
		s.nextLine();	// 왜 필요한지 알지?
		System.out.print("세부 설명을 입력하시오.\n>");
		desc = s.nextLine().trim();	// trim 앞뒤 공백 제거
		System.out.print("기한을 입력하십시오(YYYYMMDD).\n>");
		due_date = s.next();
		TodoItem t = new TodoItem(category, title, desc, due_date);
		if(list.addItem(t) > 0) System.out.println("등록이 완료되었습니다.\n");
	}
	/// TA세션 질문!!!! sc.close() -> s.close() 안되는 이유?

	public static void deleteItem(TodoList l) {
		System.out.print("\n========== 기존 항목 삭제\n삭제할 항목의 번호를 입력하시오.\n>");
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		if (l.getCount() < id) {
			System.out.println("해당 항목은 존재하지 않습니다.\n");
			return;
		}
		if(l.deleteItem(id)>0) System.out.println(id+"번 항목은 삭제되었습니다.\n");	// id는 db일련번호로 1부터 시작
	}

	public static void updateItem(TodoList l) {
		Scanner s = new Scanner(System.in);
		System.out.print("\n========== 기존 항목 수정\n수정할 항목 번호를 입력하시오.\n>" );
		int id = s.nextInt();
//		for (TodoItem item : l.getList()) {
//			int cnt=0;
//			if (item.getId() != id) cnt++;
//			if(l.getCount() == cnt) {
//				System.out.println("존재하지 않는 항목입니다.\n");
//				return;
//			}
//		}
		System.out.print("해당 항목에서 수정하려는 이름을 입력하시오.\n>");
		String new_title = s.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 항목 이름입니다.\n");
			return;
		}
		System.out.print("카테고리 명을 입력하시오.\n>");
		String new_category = s.next();
		s.nextLine();
		System.out.print("세부 설명을 입력하시오.\n>");
		String new_description = s.nextLine().trim();
		System.out.print("기한을 입력하시오(YYYYMMDD).\n>");
		String new_due_date = s.next();
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		if(l.updateItem(t, id) > 0) System.out.println("수정이 완료되었습니다.\n");
		else System.out.println("수정이 실패하였습니다. 입력이 제대로 되었는지 확인하십시오.\n");
	}
	
	public static void listAll(TodoList l) {
		System.out.println("\n========== 모든 항목 출력(총 "+l.getCount()+"개)\n");
//		int serial_num = 1;
		for (TodoItem item : l.getList()) {
			System.out.print(item.toString());
//			l.listAll(item);
		}
		System.out.println("");
	}
	public static void listAll(TodoList l, String orderby, int odering) {
		System.out.println("\n========== 모든 항목 출력(총 "+l.getCount()+"개)\n");
		for (TodoItem item : l.getOrderedList(orderby, odering)) {
			System.out.print(item.toString());
		}
		System.out.println("");
	}
	
	public static void find(TodoList l, String keyword) {
		System.out.print("\n========== 제목 내용 검색\n");
		int count=0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.print(item.toString());
			count++;
		}
		System.out.println("\n검색 결과 총 " + count + "개.\n");
	}
	public static void find_cate(TodoList l, String cate_keyword) {
		System.out.print("\n========== 카테고리 검색\n");
		int count =0;
		for (TodoItem item : l.getCategory(cate_keyword)) {
			System.out.print(item.toString());
			count ++;
		}
		System.out.println("\n검색 결과 총 "+ count + "개.\n");
	}
	public static void ls_cate(TodoList l) {
		System.out.print("\n========== 카테고리 종류\n");
		int count =0;
		for (String item : l.getCategories()) {
			System.out.println(item + " ");
			count ++;
		}
		System.out.println("\n총 "+ count + "개의 카테고리가 등록되어 있습니다.\n");
	}
	
	public static void comp(TodoList l, int complete) {
		int count = l.completeItem(complete);
		if(l.completeItem(complete) == 0) System.out.println("\n해당 번호로 등록된 항목이 존재하지않습니다.\n");
		else System.out.println("\n총 "+ count + "개의 항목이 완료체크되었습니다.\n");
		// 나중엔 번호 여러개 입력 받아서 한번에? while(sc.next();)?
	}
	public static void find_comp(TodoList l) {
		System.out.print("\n========== 완료된 항목만 확인\n");
		int count=0;
		for (TodoItem item : l.getComp()) {
			System.out.print(item.toString());
			count ++;
		}
		System.out.println("\n검색 결과 총 "+ count + "개.\n");
	}

	public static void importance(TodoList l, int impo_id) {
		System.out.print("\n========== 중요도 지정\n해당 항목의 중요도를 입력하시오(0~3)\n>");
		Scanner s = new Scanner(System.in);
		int important = s.nextInt();
		if (l.importance(impo_id, important) > 0) System.out.println("\n"+impo_id+"번 항목이 중요도 "+important+"(으)로 지정되었습니다.\n");
		else System.out.println("\n중요도 지정 실패. 숫자를 알맞게 입력했는지 확인하시오.\n");
	}

	public static void mate(TodoList l) {
		Scanner s = new Scanner(System.in);
		System.out.print("\n========== 참여 인원수 기입\n기입할 항목 번호를 입력하시오\n>");
		int mate_id = s.nextInt();
		System.out.print("최대 참여 인원수를 입력하시오\n>");
		int member = s.nextInt();
		if(l.mate(mate_id, member) > 0) System.out.println("\n인원수 기입이 완료되었습니다!\n");
		else System.out.println("\n존재하지 않는 번호입니다.\n");
	}
		
	
	 // 프로그램 시작 시 읽기 & 종료 시 저장
	public static void saveList(TodoList l, String filename) {
		try {
			//File file = new File("todolist.txt");
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
//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String items;
//			int count=0;
//			while( (items = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(items, "##");
//				String category = st.nextToken();
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				String due_date = st.nextToken();
//				String current_date = st.nextToken();
//				TodoItem t = new TodoItem(category, title, desc, due_date);
//				t.setCurrent_date(current_date);
//				l.addItem(t);
//				count++;
////				System.out.println(title+desc+current_date);
//			}
//			br.close();
//			System.out.println(count+"개의 항목이 "+filename+"에서 저장되었습니다.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
}
