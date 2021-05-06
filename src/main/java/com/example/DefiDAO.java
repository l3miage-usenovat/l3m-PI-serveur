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

public class DefiDAO implements DAO<Defi>{
	
	

	@Autowired
    private DataSource dataSource;
	

    @GetMapping("/")
    public ArrayList<Defi> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM defis");

            ArrayList<Defi> L = new ArrayList<Defi>();

            while(rs.next()){
            	
                Defi defi = new Defi(rs.getString("id"), rs.getString("titre"),rs.getString("dateDeCreation"),
                		rs.getString("auteur"),rs.getString("description"),rs.getString("type"),
                		rs.getString("datedemodification"), rs.getString("arret"),rs.getString("distanciel"),
                		rs.getString("motcles"),rs.getInt("points"),rs.getString("duree"),rs.getString("indices"),rs.getString("evaluation"),
                		rs.getString("epilogue"),rs.getDouble("latitude"),rs.getDouble("longitude"));
                 
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
    @Override
    public Defi read(@PathVariable(value="defiId") String id, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM defis WHERE id = '"+id+"'");

           
              Defi defi = new Defi(null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, 0);
            if(rs.next()){
                defi.setId(rs.getString("id"));
                 defi.setTitre(rs.getString("titre"));
                 defi.setDateDeCreation(rs.getString("dateDeCreation"));
                 defi.setAuteur(rs.getString("auteur"));
                 defi.setDescription(rs.getString("description"));
                 defi.setType(rs.getString("type"));
                 defi.setDateDeModification(rs.getString("datedemodification"));
                 defi.setArret(rs.getString("arret"));
                 defi.setDistanciel(rs.getString("distanciel"));
                 defi.setMotCles(rs.getString("motcles"));
                 defi.setPoints(rs.getInt("points"));
                 defi.setDuree(rs.getString("duree"));
                 defi.setIndices(rs.getString("indices"));
                 defi.setEvaluation(rs.getString("evaluation"));
                 defi.setEpilogue(rs.getString("epilogue"));
                 defi.setLatitude(rs.getDouble("latitude"));
                 defi.setLongitude(rs.getDouble("longitude"));
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
    @Override
    public Defi create(@PathVariable(value="defiId") String id, @RequestBody Defi d, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){

            if(!d.getId().equals(id)){
                response.setStatus(412);
                return null;
            }
            else if ( read(d.getId(), response) != null){
                response.setStatus(403);
                return null;
            }
            else if( read(d.getId(), response) == null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO defis VALUES ( '"+d.getId()+"' ,'"+d.getTitre()+"',TO_TIMESTAMP('"+d.getDateDeCreation()+"','YYYY-MM-DD HH:MI:SS'),'"+d.getAuteur()+"', '"+d.getDescription() 
                +"', '"+d.getType()+"',TO_TIMESTAMP('"+d.getDateDeModification()+"','YYYY-MM-DD HH:MI:SS'),'"+d.getArret()+"', '"+d.getDistanciel()+"','"+d.getMotCles()+"',"+d.getPoints()+", '"+d.getDuree()+"', '"+
                d.getIndices()+"','"+d.getEvaluation()+"','"+d.getEpilogue()+"',"+d.getLatitude()+","+d.getLongitude()+")");
                ResultSet rs = stmt.executeQuery("SELECT * FROM defis WHERE id = '"+d.getId()+"'");

           
                Defi defi = new Defi(null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, 0);
                rs.next();
                defi.setId(rs.getString("id"));
                defi.setTitre(rs.getString("titre"));
                defi.setDateDeCreation(rs.getString("dateDeCreation"));
                defi.setDescription(rs.getString("description"));
                defi.setType(rs.getString("type"));
                defi.setDateDeModification(rs.getString("datedemodification"));
                defi.setArret(rs.getString("arret"));
                defi.setDistanciel(rs.getString("distanciel"));
                defi.setMotCles(rs.getString("motcles"));
                defi.setPoints(rs.getInt("points"));
                defi.setDuree(rs.getString("duree"));
                defi.setIndices(rs.getString("indices"));
                defi.setEvaluation(rs.getString("evaluation"));
                defi.setEpilogue(rs.getString("epilogue"));
                defi.setLatitude(rs.getDouble("latitude"));
                defi.setLongitude(rs.getDouble("longitude"));
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
    @Override
    public Defi update(@PathVariable(value="defiId") String id, @RequestBody Defi d, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){


            if(!d.getId().equals(id)){
                response.setStatus(412);
                return null;
            }
            else if(read(id, response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(d.getId(), response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  defis set titre = '"+d.getTitre()+"', dateDeCreation = TO_TIMESTAMP('"+d.getDateDeCreation()+"','YYYY-MM-DD HH:MI:SS'), description = '"+ d.getDescription()+"', auteur = '"+d.getAuteur()
                +"', type = '"+d.getType()+"', datedemodification = TO_TIMESTAMP('"+d.getDateDeModification()+"','YYYY-MM-DD HH:MI:SS'), arret = '"+d.getArret()+"', distanciel = "+d.getDistanciel()+"', motcles = '"+d.getMotCles()+
                "', point = "+d.getPoints()+", duree = '"+d.getDuree()+"', indices = '"+d.getIndices()+"', evaluation = '"+d.getEvaluation()+"', epilogue = '"+d.getEpilogue()+"', latitude = "+d.getLatitude()+", longitude ="+d.getLongitude()+" WHERE id = '"+d.getId()+"'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM defis WHERE id = '"+d.getId()+"'");

           
                Defi defi = new Defi(null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, 0);
                rs.next();
                defi.setId(rs.getString("id"));
                defi.setTitre(rs.getString("titre"));
                defi.setDateDeCreation(rs.getString("dateDeCreation"));
                defi.setAuteur(rs.getString("auteur"));
                defi.setDescription(rs.getString("description"));
                defi.setType(rs.getString("type"));
                defi.setDateDeModification(rs.getString("datedemodification"));
                defi.setArret(rs.getString("arret"));
                defi.setDistanciel(rs.getString("distanciel"));
                defi.setMotCles(rs.getString("motcles"));
                defi.setPoints(rs.getInt("points"));
                defi.setDuree(rs.getString("duree"));
                defi.setIndices(rs.getString("indices"));
                defi.setEvaluation(rs.getString("evaluation"));
                defi.setEpilogue(rs.getString("epilogue"));
                defi.setLatitude(rs.getDouble("latitude"));
                defi.setLongitude(rs.getDouble("longitude"));

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
    @Override
    public void delete(@PathVariable(value="defiId") String id, HttpServletResponse response){
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
