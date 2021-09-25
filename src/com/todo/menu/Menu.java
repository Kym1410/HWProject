package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("<TodoList 관리 명령어 사용법>");
        System.out.println("1. Add a new item ( add )");
        System.out.println("2. Delete an existing item ( del )");
        System.out.println("3. Update an item  ( edit )");
        System.out.println("4. Find Keyworld ( find + keyword )");
        System.out.println("5. Show option (help) ");
        System.out.println("6. List all items ( ls )");
        System.out.println("7. sort the list by name ( ls_name_asc )");
        System.out.println("8. sort the list by name ( ls_name_desc )");
        System.out.println("9. sort the list by date ( ls_date )");
        System.out.println("10. exit (Or press escape key to exit)");
    }
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
