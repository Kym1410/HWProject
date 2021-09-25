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
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			isList = false;
			Menu.prompt();
			String choice = sc.nextLine()+" .";
			String choiceOp[] = choice.split(" ");
			
			switch (choiceOp[0]) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				TodoUtil.findKeyword(l, choice);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.println("제목순으로 정렬됨.");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				System.out.println("제목역순으로 정렬됨.");
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				System.out.println("날짜순으로 정렬됨.");
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
