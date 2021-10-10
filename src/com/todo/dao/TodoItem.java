package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
	private int id;
	private String category;
    private String title;
    private String desc;
    private String current_date;
	private String due_date;
	private int comp;

    public TodoItem(String category, String title, String desc, String due_date){
    	this.category = category;
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.due_date = due_date;
        this.comp = 0;
    }
    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}
	
    public int getComp() {
		return comp;
	}
	public void setComp(int comp) {
		this.comp = comp;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

	public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }
    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
//  due_date를 YYMMDD로 입력받을 때 사용하기
//	public String toString() {
//    	String pre_year="";
//		switch (due_date.substring(0)) {
//		case "0": case "1": case"2": case"3": case"4": case"5":
//			pre_year = "20";
//			break;
//		case"6": case"7": case"8": case"9":
//			pre_year = "19";
//			break;
//		default: 
//			System.out.println("년도를 알맞게 입력했는지 확인하십시오.\n");
//			pre_year = "??";
//			break;
//		}
//    	return "["+ category +"]\t"+ title +"\t"+ desc +"\t"+ 
//				 pre_year+due_date.substring(0,2)+"/"+due_date.substring(2,4)+"/"+due_date.substring(4,6)
//				+"\t( "+ current_date +" )";
//	}    
//    
//    public String toSaveString() {
//    	return category+"##"+title+"##"+desc+"##"+due_date+"##"+current_date+"\n\n";
//    }
	@Override
	public String toString() {
		String iscomp = "";
		if(comp != 0) iscomp = "(완료)";
		return id + "  ["+ category +"]\t"+ title + iscomp +"\t"+ desc +"\t"+ 
				 due_date.substring(0,4)+"/"+due_date.substring(4,6)+"/"+due_date.substring(6,8)
   			+"\t( "+ current_date +" )\n";
	}
}
