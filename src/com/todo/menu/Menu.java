package com.todo.menu;
public class Menu {
	
	public static void prompt() {   
        System.out.println();
        System.out.print(">> Enter command (if u need, type 'help')\n>> ");
    }
    
    public static void displaymenu() {
    	System.out.println("\n========== 도움말");
    	System.out.println("1. add\t\t신규 항목 등록");
        System.out.println("2. del\t\t항목 제거");
        System.out.println("3. edit\t\t항목 수정");
        System.out.println("4. comp <번호>\t완료 체크");
        System.out.println("5. ls\t\t모든 항목 출력");
        System.out.println("6. ls_name\t이름순으로 정렬");
        System.out.println("7. ls_name_desc\t이름 역순으로 정렬");
        System.out.println("8. ls_date\t마감일 순서대로 정렬");
        System.out.println("9. ls_date_desc\t마감일 역순으로 정렬");
        System.out.println("10. ls_cate\t등록된 카테고리 출력");
        System.out.println("11. ls_comp\t완료된 항목들 출력");
        System.out.println("12. find <키워드>\t제목과 내용에서 키워드 검색");
        System.out.println("13. find_cate <키워드>\t카테고리에서 키워드 검색");
        System.out.println("new! 14. importance <번호> \t중요도 지정");
        System.out.println("new! 15. mate <번호> \t참여할 인원수 입력");
        System.out.println("14. exit\t나가기\n");
    }
}


