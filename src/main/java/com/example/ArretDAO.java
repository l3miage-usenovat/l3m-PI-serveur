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
@RequestMapping("/api/arrets")

public class ArretDAO implements DAO<Arret> {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public ArrayList<Arret> allArrets(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM arrets");

            ArrayList<Arret> L = new ArrayList<Arret>();

            while(rs.next()){
                Arret arret = new Arret(null, null, null, null, null, 0, 0, null);
                 arret.setNom(rs.getString("nom"));
                 arret.setCode(rs.getString("code"));
                 arret.setStreetmap(rs.getString("streetmap"));
                 arret.setGooglemap(rs.getString("googlemap"));
                 arret.setVille(rs.getString("ville"));
                 arret.setLatitude(rs.getDouble("latitude"));
                 arret.setLongitude(rs.getDouble("longitude"));
                 arret.setIdDefis(rs.getString("iddefis"));

                L.add(arret);

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

    @GetMapping("/{nomArret}")
    @Override
    public Arret read(@PathVariable(value="nomArret") String nom, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM arrets WHERE nom = '"+nom+"'");

           
              Arret arret = new Arret(null, null, null, null, null, 0, 0, null);
            if(rs.next()){
                arret.setNom(rs.getString("nom"));
                 arret.setCode(rs.getString("code"));
                 arret.setStreetmap(rs.getString("streetmap"));
                 arret.setGooglemap(rs.getString("googlemap"));
                 arret.setVille(rs.getString("ville"));
                 arret.setLatitude(rs.getDouble("latitude"));
                 arret.setLongitude(rs.getDouble("longitude"));
                 arret.setIdDefis(rs.getString("iddefis"));
                 return arret;

            }
            else{
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

    @PostMapping("/{nomArret}")
    @Override
    public Arret create(@PathVariable(value="nomArret") String nom, @RequestBody Arret a, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){

            if(!a.getNom().equals(nom)){
                response.setStatus(412);
                return null;
            }
            else if ( read(a.getNom(), response) != null){
                response.setStatus(403);
                return null;
            }
            else if( read(a.getNom(), response) == null){

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO arrets VALUES ( '"+a.getNom()+"' ,'"+a.getCode()+"','"+a.getStreetmap()+"', '"+a.getGooglemap()
                +"', '"+a.getVille()+"',"+a.getLatitude()+","+a.getLongitude()+", '"+a.getIdDefis()+"')");
                ResultSet rs = stmt.executeQuery("SELECT * FROM arrets WHERE nom = '"+a.getNom()+"'");

           
                Arret arret = new Arret(null, null, null, null, null, 0, 0, null);
                rs.next();
                 arret.setNom(rs.getString("nom"));
                 arret.setCode(rs.getString("code"));
                 arret.setStreetmap(rs.getString("streetmap"));
                 arret.setGooglemap(rs.getString("googlemap"));
                 arret.setVille(rs.getString("ville"));
                 arret.setLatitude(rs.getDouble("latitude"));
                 arret.setLongitude(rs.getDouble("longitude"));
                 arret.setIdDefis(rs.getString("iddefis"));
                 response.setStatus(200);
                 return arret;

              

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

    @PutMapping("/{nomArret}")
    @Override
    public Arret update(@PathVariable(value="nomArret") String nom, @RequestBody Arret a, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){


            if(!a.getNom().equals(nom)){
                response.setStatus(412);
                return null;
            }
            else if(read(nom, response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(a.getNom(), response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  arrets set code = '"+a.getCode()+"',streetmap = '"+ a.getStreetmap()+"', googlemap = '"+a.getGooglemap()
                +"', ville = '"+a.getVille()+"', latitude = "+a.getLatitude()+", longitude ="+a.getLongitude()+", iddefis = '"+a.getIdDefis()+"' WHERE nom = '"+a.getNom()+"'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM arrets WHERE nom = '"+a.getNom()+"'");

                 Arret arret = new Arret(null, null, null, null, null, 0, 0, null);
                rs.next();
                 arret.setNom(rs.getString("nom"));
                 arret.setCode(rs.getString("code"));
                 arret.setStreetmap(rs.getString("streetmap"));
                 arret.setGooglemap(rs.getString("googlemap"));
                 arret.setVille(rs.getString("ville"));
                 arret.setLatitude(rs.getDouble("latitude"));
                 arret.setLongitude(rs.getDouble("longitude"));
                 arret.setIdDefis(rs.getString("iddefis"));
                 response.setStatus(200);
                 return arret;
            }
            else {
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
    
    @DeleteMapping("/{nomArret}")
    @Override
    public void delete(@PathVariable(value="nomArret") String nom, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){
            if(read(nom, response) == null){
                response.setStatus(404);
            }
            else{
                
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM  arrets WHERE nom = '"+nom+"'");

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
