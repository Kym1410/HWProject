package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("<TodoList 관리 명령어 사용법>");
        System.out.println("1. Add a new item ( add )");
        System.out.println("1. Add all category ( add_cate )");
        System.out.println("2. Delete an existing item ( del )");
        System.out.println("2. Delete an completed item ( del_comp )");
        System.out.println("3. Update an item  ( edit )");
        System.out.println("3. Update an completed item  ( edit_comp )");
        System.out.println("4. Find Keyworld ( find + keyword )");
        System.out.println("5, Find Category ( find_cate + category )");
        System.out.println("6. Show option (help) ");
        System.out.println("7. Check complete Item (comp + ID) ");
        System.out.println("8. List all items ( ls )");
        System.out.println("9. List all Category ( ls_cate )");
        System.out.println("9. List all completed Category ( ls_comp_cate )");
        System.out.println("10. List all complete list ( ls_comp )");
        System.out.println("11. sort the list by name ( ls_name_asc )");
        System.out.println("12. sort the list by name ( ls_name_desc )");
        System.out.println("13. sort the list by date ( ls_date )");
        System.out.println("14. sort the list by date_desc (ls_date_desc)");
        System.out.println("15. exit (Or press escape key to exit)");
    }
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
