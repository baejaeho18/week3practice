package com.todo.dao;

public class TodoItem {
    private String title;
    private String desc;
//    private Date current_date;
    private String current_date;


    public TodoItem(String title, String desc, String current_date){
        this.title=title;
        this.desc=desc;
        // 3_2 포맷 바꾸기
        this.current_date=current_date;
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
    
    @Override
	public String toString() {
		return "[" + title + "]\t" + desc + " ( " + current_date + " )";
	}
    
    public String toSaveString() {
    	return title+"##"+desc+"##"+current_date+"\n";
    }
}
