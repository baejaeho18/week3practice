package com.todo.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class Regular {

	public static void event(TodoList l) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		System.out.print("\n========== 정기일정 등록\n등록하려는 항목의 번호(id)를 입력하십시오\n>");
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		System.out.print("정기 반복 구간을 입력하시오(M:월, Y:연)\n>");
		String choice = s.next();
		System.out.print("반복 횟수를 입력하시오.\n>");
		int re = s.nextInt();
		TodoItem t = l.regularItem(num).get(0);
		
		if(choice.equals("Y")) {	// 연례 행사
			for(int i=1;i<re;i++) {
				int due = Integer.parseInt(t.getDue_date()) + 10000;
				String new_due_date = Integer.toString(due);
				t.setTitle(t.getTitle()+"'");
				t.setDue_date(new_due_date);
				l.addItem(t);
			}
			System.out.println(num+"번 항목이 "+re+"회 시행되는 연례행사로 등록되었습니다.\n");
		}
		else if(choice.equals("M")) {	// 월례 행사
			for(int i=1;i<re;i++) {
				int due = Integer.parseInt(t.getDue_date()) + 100;
				if((due/100)%100 >12) due = due-1200+10000;
				String new_due_date = Integer.toString(due);
				t.setDue_date(new_due_date);
				t.setTitle(t.getTitle()+"'");
				l.addItem(t);
			}
			System.out.println(num+"번 항목이 "+re+"회 시행되는 월례행사로 등록되었습니다.\n");
		}
		else System.out.println("지시된 문자(M/Y/x)를 올바르게 입력하지 않았습니다.\n");
	}

}
