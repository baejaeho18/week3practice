package com.todo.menu;
public class Menu {
	
	public static void prompt() {   
        System.out.println();
        System.out.print(">> Enter command (if u need, type 'help')\n>> ");
    }
    
    public static void displaymenu() {
    	System.out.println("\n========== 도움말");
    	System.out.println("1. add\t\t신규 항목 등록");
        System.out.println("2. del\t\t항목 제거(복수 가능)");
        System.out.println("3. edit\t\t항목 수정");
        System.out.println("4. comp <id들>\t완료 체크(복수 가능)");
        System.out.println("5. ls\t\t모든 항목 출력");
        System.out.println("6. ls_name\t이름순으로 정렬");
        System.out.println("7. ls_name_desc\t이름 역순으로 정렬");
        System.out.println("8. ls_date\t마감일 순서대로 정렬");
        System.out.println("9. ls_date_desc\t마감일 역순으로 정렬");
        System.out.println("10. ls_cate\t등록된 카테고리 출력");
        System.out.println("11. ls_comp\t완료된 항목들 출력");
        System.out.println("12. find <키워드>\t제목과 내용에서 키워드 검색");
        System.out.println("13. find_cate <키워드>\t카테고리에서 키워드 검색");
        System.out.println("new! 14. importance<번호>\t해당 id 항목의 중요도 지정");
        System.out.println("new! 15. ls_importance\t중요도 순서대로 검색");
        System.out.println("new! 16. mate\t\t참여할 인원수 등록");
        System.out.println("new! 17. find_mate <명>\t입력한 참여자수를 가진 항목 출력");
        System.out.println("new! 18. calendar\t당월 일정을 달력에 표시");
        System.out.println("new! 19. regular\t정기일정 등록(연례, 월례)");
        System.out.println("20. exit\t나가기\n");
    }
}

// calendar 만들기
// https://github.com/daheewoo/calendar_javaFx


