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
public class ChamiCRUD {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public ArrayList<Chami> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM chamis");

            ArrayList<Chami> L = new ArrayList<Chami>();

            while(rs.next()){
                Chami u = new Chami();
                u.age = rs.getInt("age");
                u.pseudo = rs.getString("pseudo");
                u.description = rs.getString("description");
                u.ville   = rs.getString("ville");
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
    public Chami read(@PathVariable(value="userId") String id, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();


            ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE pseudo = '"+id+"'");

           
             Chami userReaded= new Chami();
            if(rs.next()){
                
                userReaded.age = rs.getInt("age");
                userReaded.pseudo = rs.getString("pseudo");
                userReaded.ville = rs.getString("ville");
                userReaded.description = rs.getString("description");
                
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
    public Chami create(@PathVariable(value="userId") String id, @RequestBody Chami u, HttpServletResponse response){
        
        try (Connection connection = dataSource.getConnection()){

             if(! u.pseudo.equals(id) ){
                response.setStatus(412);
                return null;
            }
             
            else if( read(u.pseudo, response) != null){
                response.setStatus(403);
                return null;
            }
            else if (read(u.pseudo, response) == null){

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO chamis VALUES ( '"+u.pseudo+"' ,"+u.age+",'"+u.ville+"','"+
                                                                u.description+"')");
                ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE pseudo = '"+u.pseudo+"'");
                
                Chami userCreated= new Chami();
                rs.next();
                userCreated.age = rs.getInt("age");
                userCreated.pseudo = rs.getString("pseudo");
                userCreated.ville = rs.getString("ville");
                userCreated.description = rs.getString("description");
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
    public Chami update(@PathVariable(value="userId") String id, @RequestBody Chami u, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){

            if(! u.pseudo.equals(id) ){
                response.setStatus(412);
                return null;
            }
            else if (read(u.pseudo, response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(u.pseudo, response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  chamis set age = "+u.age+", ville = '"+u.ville+"' , description = '"+u.description+ 
                "' WHERE pseudo = '"+u.pseudo+"'");
               ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE pseudo = '"+u.pseudo+"'");

               rs.next();
               Chami userCreated= new Chami();
               userCreated.age = rs.getInt("age");
               userCreated.pseudo = rs.getString("pseudo");
               userCreated.ville =  rs.getString("ville");
               userCreated.description = rs.getString("description");
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
            stmt.executeUpdate("DELETE FROM  chamis WHERE pseudo = '"+id+"'");

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
