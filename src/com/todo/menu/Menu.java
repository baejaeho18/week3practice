package com.todo.menu;
public class Menu {
	
	public static void prompt() {
//    public static void displaymenu(int i)
//    {
//        System.out.println();
//        if (i==1) {
//	        System.out.println("1. Add a new item ( add )");
//	        System.out.println("2. Delete an existing item ( del )");
//	        System.out.println("3. Update an item  ( edit )");
//	        System.out.println("4. List all items ( ls )");
//	        System.out.println("5. sort the list by name ( ls_name_asc )");
//	        System.out.println("6. sort the list by name ( ls_name_desc )");
//	        System.out.println("7. sort the list by date ( ls_date )");
//	        System.out.println("8. exit (Or press escape key to exit)");
//        } else {
// 	        System.out.println("Enter your command >");
//	        System.out.println("명령어에 대한 설명이 필요하면 help 명령어를 입력하십시오.");
//        }    
        System.out.println();
        System.out.print(">> Enter command (if u need, type 'help')\n>> ");
    }
    
    public static void displaymenu() {
    	System.out.println("\n========== 도움말");
    	System.out.println("1. add\t\t신규 항목 등록");
        System.out.println("2. del\t\t항목 제거");
        System.out.println("3. edit\t\t항목 수정");
        System.out.println("4. ls\t\t모든 항목 출력");
        System.out.println("5. ls_name_asc\t이름순으로 정렬");
        System.out.println("6. ls_name_desc\t이름 역순으로 정렬");
        System.out.println("7. ls_date\t입력한 순서대로 정렬");
        System.out.println("8. ls_date_desc\t입력한 역순으로 정렬");
        System.out.println("9. find\t\t제목과 내용을 기준으로 검색");
        System.out.println("10. find_cate\t카테고리를 기준으로 검색");
        System.out.println("11. ls_cate\t등록된 카테고리 출력");
        System.out.println("12. exit\t나가기\n");
    }



}


