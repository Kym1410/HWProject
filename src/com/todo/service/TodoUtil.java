package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category ,due_date, due_hour, level;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목추가]\n" + "제목 > ");
		title = sc.next();
		// 제목 중복 체크
		if(!list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		System.out.println("Enter the Category");
		category = sc.nextLine();
		sc.nextLine();
		
		System.out.println("enter the description");
		desc = sc.nextLine();
	
		System.out.println("enter the due_date(YYYY/MM/DD)");
		due_date = sc.nextLine();
		
		System.out.println("enter the due_hour(HH:MM)");
		due_hour = sc.nextLine();
		
		System.out.println("enter the level(easy/normal/hard)");
		level = sc.nextLine();
		TodoItem t = new TodoItem(category, title, desc, due_date, due_hour, level);
		if(list.addItem(t)>0) {
			System.out.println("추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("[Delete Item]");
		System.out.print("Enter the Delete Item number(0: exit) > ");
		int index = sc.nextInt();
		if(index == 0) {
			System.out.println("중단됨!");
			break;
		}
		if(l.deleteItem(index) > 0) {
			System.out.println("삭제되었습니다.");
		}
		}
	}
	
public static void deleteCompItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("[Delete Item]");
		System.out.print("Enter the Delete Item number > ");
		int index = sc.nextInt();
		if(l.deleteCompItem(index) > 0) {
			System.out.println("삭제되었습니다.");
		}
		else {
			System.out.println("완료되지 않은 항목입니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date, new_due_hour, new_level;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Edit Item");
		System.out.print("Enter the Edit item number > ");
		int index = sc.nextInt();
		sc.nextLine();
		System.out.print("new title > ");
		new_title = sc.nextLine();
		sc.nextLine();
		
		System.out.print("new category > ");
		new_category = sc.nextLine().trim();
	
		System.out.print("new desc > ");
		new_desc = sc.nextLine().trim();
		sc.nextLine();
		
		System.out.print("new due date > ");
		new_due_date = sc.next().trim();
		
		System.out.print("new due hour > ");
		new_due_hour = sc.next().trim();
		
		System.out.print("new level > ");
		new_level = sc.next().trim();
		
	
			
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date, new_due_hour, new_level);
		t.setId(index);
		t.setIs_completed(index);
		if(l.updateItem(t) > 0) {
			System.out.println("수정되었습니다.");
		}
		System.out.println("item updated");
	}
	

	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int is_completed) {
		int count = 0;
		for(TodoItem item : l.getList(is_completed)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목이 완료되었습니다.");
		
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	//완료 목록 출
	public static void listComp(TodoList l) {
		int count = 0;
		for (TodoItem item : l.getCompList()) {
			System.out.println(item.toString());
			count++;
		}
	}
	
	public static void listCategory(TodoList l) {
		List<String> categoryList = new ArrayList<String>();
		
		for(TodoItem item : l.getList()) {
			if(!categoryList.contains(item.getCategory())) {
				categoryList.add(item.getCategory());
			}

		}
		int size = categoryList.size();
		String cate;
		for(int i = 0; i < size; i++) {
			cate = categoryList.get(i) + " / ";
			if(i == size-1) {
				cate = cate.substring(0, cate.length()-2);
			}
			
			System.out.print(cate);
		}
		
	}
	
	public static void findKeyword(TodoList l, String word) {
		int count = 0;
		StringTokenizer st = new StringTokenizer(word, " ");
		String find = st.nextToken();
		String keyword = st.nextToken();
		
		for(TodoItem item : l.getList(keyword)) {
			if(item.toString().contains(keyword)) {
				System.out.println((l.indexOf(item)+1) + ". " + "[" + item.getCategory() + "] "+ item.getTitle() + " - "  + item.getDesc() + " - " + item.getDue_date() + " - "
			+ item.getCurrent_date() + " - " + item.getDue_hour() + " - " + item.getLevel());
				count++;
			}
			
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	//완료 체크
	public static void completeItem(TodoList l ,int index) {
		Scanner sc = new Scanner(System.in);
		if(l.completeItem(index) > 0) {
			System.out.println("완료 체크하였습니다.");
			System.out.print("Enter 1 if consider complete Item(0: exit) > ");
			int com = sc.nextInt();
			if(com == 1) {
				System.out.print("Enter the nunber to complete Item");
				int num = sc.nextInt();
				completeItem(l, num);
			}
			else {
				System.out.println("완료 체크를 종료합니다.");
			}
		}
	}
	//완료 해제
	public static void completeItemBack(TodoList l ,int index) {
		if(l.completeItemBack(index) > 0) {
			System.out.println("완료 해제하였습니다.");
		}
	}
	
	public static void findCategory(TodoList l, String cate) {
		int count = 0;
		StringTokenizer st = new StringTokenizer(cate, " ");
		String find = st.nextToken();
		String keyword = st.nextToken();
		
		for(TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println((l.indexOf(item)+1) + ". " + "[" + item.getCategory() + "] "+ item.getTitle() + " - "  + item.getDesc() + " - " + item.getDue_date() + " - " 
			+ item.getCurrent_date() + " - " + item.getDue_hour() + " - " + item.getLevel());
				count++;
			}
			
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			FileWriter w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void addCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			
			System.out.print(item + " ");
			l.addCategory(item);
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 저장되었습니다.\n", count);
	}
	
	public static void listCompCate(TodoList l) {
		int count = 0;
		for(String item : l.getCompCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 완되어 있습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategories(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
}