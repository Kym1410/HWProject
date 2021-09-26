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
		
		System.out.println("\n"
				+ "========== Create item Section\n"
				+ "enter the title\n");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		sc.nextLine();
		System.out.println("Enter the Category");
		category = sc.nextLine();
		
		System.out.println("enter the description");
		desc = sc.nextLine();
		
		System.out.println("enter the due_date(YYYY/MM/DD)");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, due_date, category);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		/*
		System.out.println("\n"
				+ "========== Delete Item Section\n"
				+ "enter the title of item to remove\n"
				+ "\n"); */
		//String title = sc.nextLine();
		/*
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		*/
		System.out.println("[Delete Item]");
		System.out.print("Enter the Delete Item number > ");
		int num = sc.nextInt();
		String check;
		for(TodoItem item : l.getList()) {
			if(num == (l.indexOf(item) + 1)) {
				System.out.println(item.toString());
				System.out.print("위 항목을 삭제하시겠습니까? (y/n) > ");
				check = sc.next().trim();
				if(check.equals("y")) {
					l.deleteItem(item);
					System.out.print("삭제되었습니다.");
				}
				else {
					System.out.print("취소되었습니다.");
				}
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		/*
		System.out.println("\n"
				+ "========== Edit Item Section\n"
				+ "enter the title of the item you want to update\n"
				+ "\n");
				*/
		System.out.println("Edit Item");
		System.out.print("Enter the Edit item number > ");
		int num = sc.nextInt();
		//String title = sc.nextLine().trim();
		
		for(TodoItem item : l.getList()) {
			if(num == (l.indexOf(item)+1)) {
				System.out.println((l.indexOf(item)+1) + ". " + "[" + item.getTitle() + "] "+ item.getCategory() + " - "  + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				sc.nextLine();
				System.out.println("enter the new title of the item");
				String new_title = sc.nextLine().trim();
				if (l.isDuplicate(new_title)) {
					System.out.println("title can't be duplicate");
					return;
				}
				
				System.out.println("enter the new category ");
				String new_category = sc.nextLine().trim();
				
				System.out.println("enter the new description ");
				String new_description = sc.nextLine().trim();
				
				System.out.println("enter the new due_date");
				String new_duedate = sc.nextLine().trim();
				
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_duedate, new_category);
				l.addItem(t);
				System.out.println("item updated");
			}
			
		}
		/*
		if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}

		System.out.println("enter the new title of the item");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		System.out.println("enter the new category ");
		String new_category = sc.nextLine();
		
		System.out.println("enter the new description ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("enter the new due_date");
		String new_duedate = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_duedate, new_category);
				l.addItem(t);
				System.out.println("item updated");
			}
		}
		*/

	}

	public static void listAll(TodoList l) {
		int size;
		size = l.getList().size();
		System.out.println("전체 목록, " + "총 " + size + "개");
		for (TodoItem item : l.getList()) {
			System.out.println((l.indexOf(item)+1) + ". " + "[" + item.getCategory() + "] "+ item.getTitle() + " - "  + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
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
}
