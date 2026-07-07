package com.example.todo;

import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private final TodoService service = new TodoService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new TodoApp().run();
    }

    private void run() {
        printWelcome();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    handleAdd();
                    break;
                case "2":
                    handleListAll();
                    break;
                case "3":
                    handleListPending();
                    break;
                case "4":
                    handleComplete();
                    break;
                case "5":
                    handleRemove();
                    break;
                case "0":
                    running = false;
                    System.out.println("再见！");
                    break;
                default:
                    System.out.println("无效选项，请重新输入。");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printWelcome() {
        System.out.println("================================");
        System.out.println("     待办事项管理器 v1.0");
        System.out.println("================================\n");
    }

    private void printMenu() {
        System.out.println("请选择操作：");
        System.out.println("  1. 添加待办");
        System.out.println("  2. 查看全部");
        System.out.println("  3. 查看未完成");
        System.out.println("  4. 标记完成");
        System.out.println("  5. 删除待办");
        System.out.println("  0. 退出");
        System.out.print("> ");
    }

    private void handleAdd() {
        System.out.print("请输入待办标题: ");
        String title = scanner.nextLine();
        try {
            TodoItem item = service.add(title);
            System.out.println("已添加: " + item);
        } catch (IllegalArgumentException e) {
            System.out.println("添加失败: " + e.getMessage());
        }
    }

    private void handleListAll() {
        List<TodoItem> items = service.listAll();
        if (items.isEmpty()) {
            System.out.println("暂无待办事项。");
            return;
        }
        System.out.printf("共 %d 项，已完成 %d 项：%n",
                service.count(), service.countCompleted());
        for (TodoItem item : items) {
            System.out.println("  " + item);
        }
    }

    private void handleListPending() {
        List<TodoItem> items = service.listPending();
        if (items.isEmpty()) {
            System.out.println("没有未完成的待办。");
            return;
        }
        System.out.println("未完成的待办：");
        for (TodoItem item : items) {
            System.out.println("  " + item);
        }
    }

    private void handleComplete() {
        System.out.print("请输入要完成的编号: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (service.complete(id)) {
                System.out.println("已标记完成 #" + id);
            } else {
                System.out.println("未找到编号为 " + id + " 的待办。");
            }
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字编号。");
        }
    }

    private void handleRemove() {
        System.out.print("请输入要删除的编号: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (service.remove(id)) {
                System.out.println("已删除 #" + id);
            } else {
                System.out.println("未找到编号为 " + id + " 的待办。");
            }
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字编号。");
        }
    }
}
