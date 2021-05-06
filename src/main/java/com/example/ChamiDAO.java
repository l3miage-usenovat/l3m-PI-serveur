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
public class ChamiDAO implements DAO<Chami>{

   

	@Autowired
    private DataSource dataSource;
	

    @GetMapping("/")
    public ArrayList<Chami> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM chamis");

            ArrayList<Chami> L = new ArrayList<Chami>();

            while(rs.next()){
                Chami u = new Chami(null,null, 0, null, null);
                u.setEmail(rs.getString("email"));
                u.setAge(rs.getInt("age"));
                u.setPseudo(rs.getString("pseudo"));
                u.setDescription(rs.getString("description"));
                u.setVille(rs.getString("ville"));
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

           
             Chami userReaded= new Chami(null,null, 0, null, null);
             
            if(rs.next()){
            	userReaded.setEmail(rs.getString("email"));
            	userReaded.setPseudo(rs.getString("pseudo"));
                userReaded.setAge(rs.getInt("age"));
                userReaded.setVille(rs.getString("ville"));
                userReaded.setDescription(rs.getString("description"));
                
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

             if(! u.getPseudo().equals(id) ){
                response.setStatus(412);
                return null;
            }
             
            else if( read(u.getPseudo(), response) != null){
                response.setStatus(403);
                return null;
            }
            else if (read(u.getPseudo(), response) == null){

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO chamis VALUES ( '"+u.getPseudo()+"' ,"+u.getAge()+",'"+u.getVille()+"','"+
                                                                u.getDescription()+"','"+u.getEmail()+"')");
                ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE pseudo = '"+u.getPseudo()+"'");
                
                Chami userCreated= new Chami(null,null, 0, null, null);
                
                rs.next();
                userCreated.setEmail(rs.getString("email"));
                userCreated.setAge(rs.getInt("age"));
                userCreated.setPseudo(rs.getString("pseudo"));
                userCreated.setVille(rs.getString("ville"));
                userCreated.setDescription(rs.getString("description"));
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

            if(! u.getPseudo().equals(id) ){
                response.setStatus(412);
                return null;
            }
            else if (read(u.getPseudo(), response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(u.getPseudo(), response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  chamis set age = "+u.getAge()+", ville = '"+u.getVille()+"' , email = '"+u.getEmail()+"' , description = '"+u.getDescription()+ 
                "' WHERE pseudo = '"+u.getPseudo()+"'");
               ResultSet rs = stmt.executeQuery("SELECT * FROM chamis WHERE pseudo = '"+u.getPseudo()+"'");

               rs.next();
               Chami userCreated= new Chami(null,null, 0, null, null);
               userCreated.setEmail(rs.getString("email"));
               userCreated.setAge(rs.getInt("age"));
               userCreated.setPseudo(rs.getString("pseudo"));
               userCreated.setVille(rs.getString("ville"));
               userCreated.setDescription(rs.getString("description"));
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
     public void delete(@PathVariable(value="userId") String id, HttpServletResponse response){
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