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
		
		String title, desc, category ,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목추가]\n" + "제목 > ");
		title = sc.nextLine();
		sc.nextLine();
		System.out.println("Enter the Category");
		category = sc.nextLine();
		sc.nextLine();
		
		System.out.println("enter the description");
		desc = sc.nextLine();
	
		System.out.println("enter the due_date(YYYY/MM/DD)");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		if(list.addItem(t)>0) {
			System.out.println("추가되었습니다.");
		}
		//list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("[Delete Item]");
		System.out.print("Enter the Delete Item number > ");
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0) {
			System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Edit Item");
		System.out.print("Enter the Edit item number > ");
		int index = sc.nextInt();
		
		System.out.print("new title > ");
		new_title = sc.next().trim();
		
		System.out.print("new category > ");
		new_category = sc.next().trim();
		
		System.out.print("new desc > ");
		new_desc = sc.next().trim();
		sc.nextLine();
		
		System.out.print("new due date > ");
		new_due_date = sc.next().trim();
		
	
			
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date);
		t.setId(index);
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
		
		for(TodoItem item : l.getList()) {
			if(item.toString().contains(keyword)) {
				System.out.println((l.indexOf(item)+1) + ". " + "[" + item.getCategory() + "] "+ item.getTitle() + " - "  + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				count++;
			}
			
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void findCategory(TodoList l, String cate) {
		int count = 0;
		StringTokenizer st = new StringTokenizer(cate, " ");
		String find = st.nextToken();
		String keyword = st.nextToken();
		
		for(TodoItem item : l.getList()) {
			if(item.getCategory().contains(keyword)) {
				System.out.println((l.indexOf(item)+1) + ". " + "[" + item.getCategory() + "] "+ item.getTitle() + " - "  + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
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
	/*
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			try {
				String oneline;
				int num=0;
					while((oneline = br.readLine()) != null) {
						num++;
						StringTokenizer st = new StringTokenizer(oneline, "##");
						String category = st.nextToken();
						String title = st.nextToken();
						String desc = st.nextToken();
						String due_date = st.nextToken();
						String date = st.nextToken();
						TodoItem item = new TodoItem(title, desc, date, due_date, category);
						l.addItem(item);
					}
					System.out.println(num + "개의 항목을 읽었습니다.");
					br.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("파일을 찾을 수 없습니다.");
		}
	}
	*/
}
