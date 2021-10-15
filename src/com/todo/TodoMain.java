package com.todo;

import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			isList = false;
			Menu.prompt();
			String choice = sc.next();
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "del_comp":
				TodoUtil.deleteCompItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "ls_comp_cate":
				TodoUtil.listCompCate(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "comp":
				int num = sc.nextInt();
				TodoUtil.completeItem(l, num);
				break;
				
			case "edit_comp":
				int ednum = sc.nextInt();
				TodoUtil.completeItemBack(l, ednum);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l,1);
				break;
			
			case "ls_name":
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;

				
			case "ls_name_desc":
				System.out.println("제목역순으로 정렬됨.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬됨.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬됨.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
			
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("please enter one of the above mentioned command (도움말 - help)");
				break;
			}
			
			
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
		System.out.println("모든 데이터가 저장되었습니다.");
	}
}
