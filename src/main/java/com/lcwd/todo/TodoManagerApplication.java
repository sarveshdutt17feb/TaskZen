package com.lcwd.todo;

import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);
	@Autowired
	private TodoDao todoDao;
	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Application Satrted");
//		JdbcTemplate template = todoDao.getTemplate();
//		logger.info("template Object {} ",template);
//		Todo todo = new Todo();
//		 todo.setContent("I have to learn java ");
//		 todo.setTitle("Java Placement course");
//		 todo.setStatus("PENDING");
//		 todo.setId(124);
//		 todo.setAddedDate(new Date());
//		 todo.setTodoDate(new Date());
//		 todoDao.saveTodo(todo);
//		Todo todo = todoDao.getTodo(123);
//		logger.info("Todo : {} ",todo);
//		todo.setStatus("Done");
//		todo.setContent("I have to learn springBoot");
//		todo.setTitle("Learn SpringBoot");
//		todo.setAddedDate(new Date());
//		todo.setTodoDate(new Date());
//		Todo todo1 = todoDao.updateTodo(124,todo);
//		logger.info("updated TODO {} ",todo1);
//		List<Todo> allTodos = todoDao.getAllTodos();
//		logger.info("All Todos: {} ",allTodos);
//		todoDao.deleteTodo(124);
//		todoDao.deleteMultiple(new int[]{124,125});

	}
}
