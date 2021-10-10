package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;

public class TodoList {
//	private List<TodoItem> list;
	Connection conn;
	
	public TodoList() {
//		this.list = new ArrayList<TodoItem>();
		this.conn = DbConnect.getConnection();
	}
	
	public void importData(String filename) {	// 가장 처음 시작할 때 1회용
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, memo, category, current_date, due_date)"+
					" values (?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, desc);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				int count = pstmt.executeUpdate();
				if(count>0) records++;
				pstmt.close();
			}
			System.out.println(records+" records read!!");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, current_date, due_date)"+
				" values (?,?,?,?,?);";
		PreparedStatement pstmt;
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return count;
	}

	public int deleteItem(int id) {
		String sql = "delete from list where id=?;";
		int count=0;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=? where id = ?;";
		PreparedStatement pstmt;
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return count;
	}

	public ArrayList<TodoItem> getList() {						// 전체 항목 출력
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt=conn.createStatement();
			String sql = "Select * from list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(category, title, desc, due_date);	// java:카테고리##이름##설명##마감##등록시간	db:번호##이름##설명##카테고리##마감##등록시간
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return list;
	}
	public int getCount() {	// 이전의 size()
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "Select count(id) from list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		} return count;
	}
	public ArrayList<TodoItem> getList(String keyword) { 	// 제목과 내용으로 검색해서 출력
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		String new_keyword = "%"+keyword+"%"; 
		try {
			String sql = "Select * from list where title like ? or memo like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, new_keyword);
			pstmt.setString(2, new_keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(category, title, desc, due_date);	// java:카테고리##이름##설명##마감##등록시간	db:번호##이름##설명##카테고리##마감##등록시간
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select * from list order by" + orderby;	//title, due_date, desc....
			if (ordering==0) sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(category, title, desc, due_date);	// java:카테고리##이름##설명##마감##등록시간	db:번호##이름##설명##카테고리##마감##등록시간
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return list;
	}
	
	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "Select distinct category from list;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("category"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return list;
	}
	public ArrayList<TodoItem> getCategory(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement prtmt;
		String sql = "Select * from list where cateogry=?;";
		try {
			prtmt = conn.prepareStatement(sql);
			prtmt.setString(1, keyword);
			ResultSet rs = prtmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(category, title, desc, due_date);	// java:카테고리##이름##설명##마감##등록시간	db:번호##이름##설명##카테고리##마감##등록시간
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			prtmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} return list;
	}
	
	/*
	 * public void sortByName() { Collections.sort(list, new TodoSortByName()); }
	 * 
	 * // ls와 find 모두 쓸수 있게 하자 public void listAll(TodoItem item) {
	 * System.out.print(list.indexOf(item)+1 + ". ");
	 * System.out.println(item.toString()); }
	 * 
	 * public void reverseList() { Collections.reverse(list); }
	 * 
	 * public void sortByDate() { Collections.sort(list, new TodoSortByDate()); }
	 * ㄴ
	 * public int indexOf(TodoItem t) { return list.indexOf(t); }
	 */
	public Boolean isDuplicate(String new_title) {
		ArrayList<TodoItem> l = new ArrayList<TodoItem>();
		Statement stmt;
		String sql = "Select * from list;";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(category, title, desc, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				l.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (TodoItem item : l ) {
			if (new_title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
