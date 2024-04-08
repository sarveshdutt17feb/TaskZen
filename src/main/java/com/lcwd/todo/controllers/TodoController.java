package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    //create
    Logger logger = LoggerFactory.getLogger(TodoController.class);
    Random random=new Random();
    @Autowired
    private TodoService todoService;
    //create
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        //create todo
        int id = random.nextInt(999999);
        todo.setId(id);
        logger.info("create todo");
        //call service to create todo
        Todo todo1=todoService.createTodo(todo);
        return todo1;
    }
}
