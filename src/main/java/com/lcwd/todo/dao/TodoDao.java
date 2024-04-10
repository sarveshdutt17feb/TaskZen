package com.lcwd.todo.dao;

import com.lcwd.todo.helper.Helper;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TodoDao {
   Logger logger = LoggerFactory.getLogger(TodoDao.class);




   private JdbcTemplate template;
   public TodoDao( @Autowired JdbcTemplate template) {
      this.template = template;

      //create table if not exist
      String createTable = "create table IF NOT EXISTS todos(id int primary key,title varchar(100) not null,content varchar(500),status varchar(10) not null,addedDate datetime,todoDate datetime)";
      int update =template.update(createTable);
      logger.info("TODO TABLE CREATED {}   ",update);

   }

   public JdbcTemplate getTemplate() {
      return template;
   }

   public void setTemplate(JdbcTemplate template) {
      this.template = template;
   }

   //save todo in db
   public Todo saveTodo(Todo todo){
      String insertQuery = "insert into todos(id,title,content,status,addedDate,todoDate) values(?,?,?,?,?,?)";
      int rows=template.update(insertQuery,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getAddedDate(),todo.getTodoDate());
      logger.info("JDBC OPERATION {} inserted",rows);
      return todo;
   }

   //get single todo from db
   public Todo getTodo(int id) throws ParseException {
      //used ? parameter for dynamic values
      String query = "select * from todos where id= ? ";
      Todo todo = template.queryForObject(query,new TodoRowMapper(),id);
//      Todo todo = new Todo();
//      todo.setId((int)todoData.get("id"));
//      todo.setTitle((String)todoData.get("title"));
//      todo.setContent((String)todoData.get("content"));
//      todo.setStatus((String)todoData.get("status"));
//      todo.setTodoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));
//      todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));

      return todo;
   }
   //get all todo from db
   public List<Todo> getAllTodos(){
      String query = "select * from todos";
      List<Todo> todos = template.query(query,new TodoRowMapper());
//      List<Map<String,Object>> maps = template.queryForList(query);
      //coverting into List<Todo>
//      List<Todo> todos = maps.stream().map((map)->{
//         Todo todo = new Todo();
//         todo.setId((int)map.get("id"));
//         todo.setTitle((String)map.get("title"));
//         todo.setContent((String)map.get("content"));
//         todo.setStatus((String)map.get("status"));
//          try {
//             todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//             todo.setTodoDate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
//          } catch (ParseException e) {
//              throw new RuntimeException(e);
//          }
//         return todo;
//      }).collect(Collectors.toList());
      //we reduced code with the help of rowMapper
      return todos;
   }

   //update todo
   public Todo updateTodo(int id,Todo newTodo){
      String query = "update todos set title=?,content=?,status=?,addedDate=?,todoDate=? where id =?";
      int update = template.update(query,newTodo.getTitle(),newTodo.getContent(),newTodo.getStatus(),newTodo.getAddedDate(),newTodo.getTodoDate(),newTodo.getId());
      logger.info("UPDATED {} ",update);
      newTodo.setId(id);
      return newTodo;
   }

   //delete todo from db
   public void deleteTodo(int id){
      String query ="delete from todos where id=?";
      int update = template.update(query,id);
      logger.info("DELETED {} ",update);
   }

   public void deleteMultiple(int[] ids){
      String query = "delete from todos where id=?";
      int[] ints = template.batchUpdate(query, new BatchPreparedStatementSetter() {
         @Override
         public void setValues(PreparedStatement ps, int i) throws SQLException {
            int id = ids[i];
            ps.setInt(1, id);
         }

         @Override
         public int getBatchSize() {
            return ids.length;
         }
      });
      for(int i:ints){
         logger.info("DELETED {} ",i);
      }
   }
}
