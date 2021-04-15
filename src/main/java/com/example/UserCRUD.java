package com.example;

import java.sql.Connection; 

import java.sql.ResultSet; 

import java.sql.Statement; 

import java.util.ArrayList; 

import javax.servlet.http.HttpServletResponse; 

import javax.sql.DataSource; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.bind.annotation.CrossOrigin; 

import org.springframework.web.bind.annotation.DeleteMapping; 

import org.springframework.web.bind.annotation.GetMapping; 

import org.springframework.web.bind.annotation.PathVariable; 

import org.springframework.web.bind.annotation.PostMapping; 

import org.springframework.web.bind.annotation.PutMapping; 

import org.springframework.web.bind.annotation.RequestBody; 

import org.springframework.web.bind.annotation.RequestMapping; 

import org.springframework.web.bind.annotation.RestController; 

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserCRUD {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public ArrayList<User> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM chamis");

            ArrayList<User> L = new ArrayList<User>();

            while(rs.next()){
                User u = new User();
                u.age = rs.getInt("age");
                u.login = rs.getString("login");
                L.add(u);

            }
            return L;

        }catch(Exception e){
            response.setStatus(500);
            try {
                response.getOutputStream().print( e.getMessage());

            }catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            System.err.println(e.getMessage());
            return null;
        }
        
    }

    @GetMapping("/{userId}")
    public User read(@PathVariable(value="userId") String id, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();


            ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE login = '"+id+"'");

           
             User userReaded= new User();
            if(rs.next()){
                
                userReaded.age = rs.getInt("age");
                userReaded.login = rs.getString("login");
                
            return userReaded;

            }else{
                response.setStatus(404);
                return null;
            }

        }catch(Exception e){
            response.setStatus(500);
            try {
                response.getOutputStream().print( e.getMessage());

            }catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            System.err.println(e.getMessage());
            return null;
        }

    }

    @PostMapping("/{userId}")
    public User create(@PathVariable(value="userId") String id, @RequestBody User u, HttpServletResponse response){
        
        try (Connection connection = dataSource.getConnection()){

             if(! u.login.equals(id) ){
                response.setStatus(412);
                return null;
            }
             
            else if( read(u.login, response) != null){
                response.setStatus(403);
                return null;
            }
            else if (read(u.login, response) == null){

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO chamis VALUES ( '"+u.login+"' ,"+u.age+")");
                ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE login = '"+u.login+"'");
                
                User userCreated= new User();
                rs.next();
                userCreated.age = rs.getInt("age");
                userCreated.login = rs.getString("login");
                response.setStatus(200);
                return userCreated;

            }
            else{
                return null;
            }
            

        }catch(Exception e){
            response.setStatus(500);
            try {
                response.getOutputStream().print( e.getMessage());

            }catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            System.err.println(e.getMessage());
            return null;
        }

    }

    @PutMapping("/{userId}")
    public User update(@PathVariable(value="userId") String id, @RequestBody User u, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){

            if(! u.login.equals(id) ){
                response.setStatus(412);
                return null;
            }
            else if (read(u.login, response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(u.login, response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  chamis set age = "+u.age+" WHERE login = '"+u.login+"'");
               ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE login = '"+u.login+"'");

               rs.next();
               User userCreated= new User();
               userCreated.age = rs.getInt("age");
               userCreated.login = rs.getString("login");
               return userCreated;

            }
           
            else{
                return null;
            }
            
                       

        }catch(Exception e){
            response.setStatus(500);
            try {
                response.getOutputStream().print( e.getMessage());

            }catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            System.err.println(e.getMessage());
            return null;
        }

    }
    
    @DeleteMapping("/{userId}")
    void delete(@PathVariable(value="userId") String id, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){

            if(read(id, response) == null ){
                response.setStatus(404);
            }
            else{
                
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM  chamis WHERE login = '"+id+"'");

            }


     
        }catch(Exception e){
            response.setStatus(500);
            try {
                response.getOutputStream().print( e.getMessage());

            }catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            System.err.println(e.getMessage());
            
        }

    }
}
