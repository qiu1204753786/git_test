package com.example.todo;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoServiceTest {

    @Test
    public void addAndListAll() {
        TodoService service = new TodoService();
        service.add("学习 Java");
        service.add("写单元测试");

        List<TodoItem> items = service.listAll();
        assertEquals(2, items.size());
        assertEquals("学习 Java", items.get(0).getTitle());
    }

    @Test
    public void completeTodo() {
        TodoService service = new TodoService();
        TodoItem item = service.add("完成作业");

        assertTrue(service.complete(item.getId()));
        assertTrue(item.isCompleted());
        assertEquals(0, service.listPending().size());
    }

    @Test
    public void removeTodo() {
        TodoService service = new TodoService();
        TodoItem item = service.add("临时任务");

        assertTrue(service.remove(item.getId()));
        assertEquals(0, service.count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyTitleThrows() {
        new TodoService().add("   ");
    }

    @Test
    public void completeNonExistentReturnsFalse() {
        assertFalse(new TodoService().complete(999));
    }
}
