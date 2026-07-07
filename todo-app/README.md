# 待办事项管理器

一个简单的 Java 控制台项目，使用 Maven 构建。

## 功能

- 添加、查看、完成、删除待办事项
- 区分全部 / 未完成列表
- 附带 JUnit 单元测试

## 运行

```bash
cd todo-app
mvn compile exec:java -Dexec.mainClass="com.example.todo.TodoApp"
```

或打包后运行：

```bash
mvn package
java -jar target/todo-app-1.0.0.jar
```

## 测试

```bash
mvn test
```

## 项目结构

```
src/main/java/com/example/todo/
  TodoItem.java    # 数据模型
  TodoService.java # 业务逻辑
  TodoApp.java     # 控制台入口
```
