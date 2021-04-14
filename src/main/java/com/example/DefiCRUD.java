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
@RequestMapping("/api/defis")

public class DefiCRUD {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public ArrayList<Defi> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELCT * FROM defis");

            ArrayList<Defi> L = new ArrayList<Defi>();

            while(rs.next()){
                Defi defi = new Defi();
                 defi.id= rs.getString("id");
                 defi.titre = rs.getString("titre");
                 defi.dateDeCreation = rs.getTime("dateDeCreation");
                 defi.description = rs.getString("description");
                L.add(defi);

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

    @GetMapping("/{defiId}")
    public Defi read(@PathVariable(value="defiId") String id, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELCT * FROM users WHERE id = '"+id+"'");

           
              Defi defi = new Defi();
            while(rs.next()){
                defi.id= rs.getString("id");
                 defi.titre = rs.getString("titre");
                 defi.dateDeCreation = rs.getTime("dateDeCreation");
                 defi.description = rs.getString("description");;

            }
            return defi;

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

    @PostMapping("/{defiId}")
    public Defi create(@PathVariable(value="defiId") String id, @RequestBody Defi d, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO defis VALUE ( '"+d.id+"' ,'"+d.titre+"',"+d.dateDeCreation+", ' "+d.description+"')");
            ResultSet rs = stmt.executeQuery("SELCT * FROM defis WHERE id = '"+id+"'");

           
            Defi defi = new Defi();
            while(rs.next()){
                defi.id= rs.getString("id");
                 defi.titre = rs.getString("titre");
                 defi.dateDeCreation = rs.getTime("dateDeCreation");
                 defi.description = rs.getString("description");;

            }
            return defi;
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

    @PutMapping("/{defiId}")
    public Defi update(@PathVariable(value="defiId") String id, @RequestBody Defi d, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE  defis set titre = '"+d.titre+"', dateDeCreation ="+d.dateDeCreation+", description = '"+ d.description+"' WHERE id = '"+id+"'");
            ResultSet rs = stmt.executeQuery("SELCT * FROM defis WHERE login = '"+id+"'");

           
            Defi defi = new Defi();
            while(rs.next()){
                defi.id= rs.getString("id");
                 defi.titre = rs.getString("titre");
                 defi.dateDeCreation = rs.getTime("dateDeCreation");
                 defi.description = rs.getString("description");;

            }
            return defi;

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
    
    @DeleteMapping("/{defiId}")
    void delete(@PathVariable(value="defiId") String id, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM  defi WHERE id = '"+id+"'");

     
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
