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
//	        System.out.println("��ɾ ���� ������ �ʿ��ϸ� help ��ɾ �Է��Ͻʽÿ�.");
//        }    
        System.out.println();
        System.out.print(">> Enter command (if u need, type 'help')\n>> ");
    }
    
    public static void displaymenu() {
    	System.out.println("\n========== ����");
    	System.out.println("1. add\t\t�ű� �׸� ���");
        System.out.println("2. del\t\t�׸� ����");
        System.out.println("3. edit\t\t�׸� ����");
        System.out.println("4. ls\t\t��� �׸� ���");
        System.out.println("5. ls_name_asc\t�̸������� ����");
        System.out.println("6. ls_name_desc\t�̸� �������� ����");
        System.out.println("7. ls_date\t�Է��� ������� ����");
        System.out.println("8. exit\t\t������\n");
    }



}


