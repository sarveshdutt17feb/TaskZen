package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        //create todo
        int id = random.nextInt(999999);
        todo.setId(id);
        logger.info("create todo");
        //call service to create todo
        Todo todo1=todoService.createTodo(todo);
        ResponseEntity response = new ResponseEntity(todo1, HttpStatus.CREATED);
        return response;
    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodohandler(){
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity(allTodos,HttpStatus.OK);
    }

    //get single todo

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
       Todo todo = todoService.getTodo(todoId);
       return ResponseEntity.ok(todo);

    }

    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int todoId){
        Todo todo = todoService.updateTodo(todoId,todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }
}
