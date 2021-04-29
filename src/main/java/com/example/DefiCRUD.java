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
            ResultSet rs = stmt.executeQuery("SELECT * FROM defis");

            ArrayList<Defi> L = new ArrayList<Defi>();

            while(rs.next()){
                Defi defi = new Defi();
                 defi.id= rs.getString("id");
                 defi.titre = rs.getString("titre");
                 defi.dateDeCreation = rs.getString("dateDeCreation");
                 defi.auteur = rs.getString("auteur");
                 defi.description = rs.getString("description");
                 defi.type = rs.getString("type");
                 defi.dateDeModification = rs.getString("datedemodification");
                 defi.arret = rs.getString("arret");
                 defi.distanciel = rs.getString("distanciel");
                 defi.motCles = rs.getString("motcles");
                 defi.points = rs.getInt("points");
                 defi.duree = rs.getString("duree");
                 defi.indices = rs.getString("indices");
                 defi.evaluation = rs.getString("evaluation");
                 defi.epilogue = rs.getString("epilogue");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM defis WHERE id = '"+id+"'");

           
              Defi defi = new Defi();
            if(rs.next()){
                defi.id= rs.getString("id");
                 defi.titre = rs.getString("titre");
                 defi.dateDeCreation = rs.getString("dateDeCreation");
                 defi.auteur = rs.getString("auteur");
                 defi.description = rs.getString("description");
                 defi.type = rs.getString("type");
                 defi.dateDeModification = rs.getString("datedemodification");
                 defi.arret = rs.getString("arret");
                 defi.distanciel = rs.getString("distanciel");
                 defi.motCles = rs.getString("motcles");
                 defi.points = rs.getInt("points");
                 defi.duree = rs.getString("duree");
                 defi.indices = rs.getString("indices");
                 defi.evaluation = rs.getString("evaluation");
                 defi.epilogue = rs.getString("epilogue");
                 return defi;

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

    @PostMapping("/{defiId}")
    public Defi create(@PathVariable(value="defiId") String id, @RequestBody Defi d, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){

            if(!d.id.equals(id)){
                response.setStatus(412);
                return null;
            }
            else if ( read(d.id, response) != null){
                response.setStatus(403);
                return null;
            }
            else if( read(d.id, response) == null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO defis VALUES ( '"+d.id+"' ,'"+d.titre+"',TO_TIMESTAMP('"+d.dateDeCreation+"','YYYY-MM-DD HH:MI:SS'),'"+d.auteur+"', '"+d.description 
                +"', '"+d.type+"',TO_TIMESTAMP('"+d.dateDeModification+"','YYYY-MM-DD HH:MI:SS'),'"+d.arret+"', '"+d.distanciel+"','"+d.motCles+"',"+d.points+", '"+d.duree+"', '"+
                d.indices+"','"+d.evaluation+"','"+d.epilogue+"')");
                ResultSet rs = stmt.executeQuery("SELECT * FROM defis WHERE id = '"+d.id+"'");

           
                Defi defi = new Defi();
                rs.next();
                defi.id= rs.getString("id");
                defi.titre = rs.getString("titre");
                defi.dateDeCreation = rs.getString("dateDeCreation");
                defi.description = rs.getString("description");
                defi.type = rs.getString("type");
                defi.dateDeModification = rs.getString("datedemodification");
                defi.arret = rs.getString("arret");
                defi.distanciel = rs.getString("distanciel");
                defi.motCles = rs.getString("motcles");
                defi.points = rs.getInt("points");
                defi.duree = rs.getString("duree");
                defi.indices = rs.getString("indices");
                defi.evaluation = rs.getString("evaluation");
                defi.epilogue = rs.getString("epilogue");
                return defi;

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

    @PutMapping("/{defiId}")
    public Defi update(@PathVariable(value="defiId") String id, @RequestBody Defi d, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){


            if(!d.id.equals(id)){
                response.setStatus(412);
                return null;
            }
            else if(read(id, response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(d.id, response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  defis set titre = '"+d.titre+"', dateDeCreation = TO_TIMESTAMP('"+d.dateDeCreation+"','YYYY-MM-DD HH:MI:SS'), description = '"+ d.description+"', auteur = '"+d.auteur
                +"', type = '"+d.type+"', datedemodification = TO_TIMESTAMP('"+d.dateDeModification+"','YYYY-MM-DD HH:MI:SS'), arret = '"+d.arret+"', distanciel = "+d.distanciel+"', motcles = '"+d.motCles+
                "', point = "+d.points+", duree = '"+d.duree+"', indices = '"+d.indices+"', evaluation = '"+d.evaluation+"', epilogue = '"+d.epilogue+"' WHERE id = '"+d.id+"'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM defis WHERE id = '"+d.id+"'");

           
                Defi defi = new Defi();
                rs.next();
                defi.id= rs.getString("id");
                defi.titre = rs.getString("titre");
                defi.dateDeCreation = rs.getString("dateDeCreation");
                defi.auteur = rs.getString("auteur");
                defi.description = rs.getString("description");
                defi.type = rs.getString("type");
                defi.dateDeModification = rs.getString("datedemodification");
                defi.arret = rs.getString("arret");
                defi.distanciel = rs.getString("distanciel");
                defi.motCles = rs.getString("motcles");
                defi.points = rs.getInt("points");
                defi.duree = rs.getString("duree");
                defi.indices = rs.getString("indices");
                defi.evaluation = rs.getString("evaluation");
                defi.epilogue = rs.getString("epilogue");
                return defi;

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
    
    @DeleteMapping("/{defiId}")
    void delete(@PathVariable(value="defiId") String id, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){
            if(read(id, response) == null){
                response.setStatus(404);
            }
            else{
                
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM  defis WHERE id = '"+id+"'");

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
