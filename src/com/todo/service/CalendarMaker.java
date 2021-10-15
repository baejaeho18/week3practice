package com.todo.service;

import java.util.Calendar;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class CalendarMaker {
	public static void showMaker(TodoList l) {
		// 일자 밑에 카테고리, 이름, 중요도 표시
		//due_date
		//[cate]
		//title(impx*)
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), 1);
        end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH)+1, 1);
        end.add(Calendar.DATE, -1);       //어떤 달의 마지막날 = 다음달 1일에서 하루를 뺀 날
        int first_date = start.get(Calendar.DAY_OF_WEEK);
        int END_DAY = end.get(Calendar.DATE);
        
        System.out.println("당월 달력 : \t["+start.get(Calendar.YEAR)+"년 "+(start.get(Calendar.MONTH)+1)+"월]\n"	// calendar.month는 월수-1값을 반환한다
                + "\t일\t\t월\t\t화\t\t수\t\t목\t\t금\t\t토");
        int cnt = first_date - 1;
        for(int q = 0 ; q < cnt ; q++) System.out.print("\t\t");
        for(int q = 1 ; q <= END_DAY ; q++) {
            System.out.print("\t"+q+"\t");
            if((q+cnt)%7==0) {
                System.out.println("");
                for (TodoItem item : l.getDate(q)) {
                	System.out.print("\t");
                    System.out.print("["+item.getCategory()+"]");
                	System.out.print("\t");
                }
                System.out.println("");
                for (TodoItem item : l.getDate(q)) {
                	System.out.print("\t");
                	if (item.getImp() == 3) System.out.print(item.getTitle()+"(***)");
                	else if (item.getImp() == 2) System.out.print(item.getTitle()+"(**)");
                	else if (item.getImp() == 1) System.out.print(item.getTitle()+"(*)");
                	System.out.print("\t");
                }
            }
        }
		
		
		
	}
	
}
