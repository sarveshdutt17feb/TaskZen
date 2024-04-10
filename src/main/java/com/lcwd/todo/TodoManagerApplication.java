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
		Todo todo = new Todo();
		 todo.setContent("wow its workign");
		 todo.setTitle("This is testing spring jdbc");
		 todo.setStatus("PENDING");
		 todo.setId(123);
		 todo.setAddedDate(new Date());
		 todo.setTodoDate(new Date());
		 todoDao.saveTodo(todo);

	}
}
