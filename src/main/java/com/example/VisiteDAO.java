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
@RequestMapping("/api/visites")

public class VisiteDAO implements DAO<Visite>{
	@Autowired
    private DataSource dataSource;
	
	
	@GetMapping("/")
    public ArrayList<Visite> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM visites");

            ArrayList<Visite> L = new ArrayList<Visite>();

            while(rs.next()){
                Visite v = new Visite(null,null, null, null, null, null, 0, 0, null, null);
                v.setVisiteId(rs.getString("visiteId"));
                v.setDefiId(rs.getString("defiId"));
                v.setVisiteur(rs.getString("visiteur"));
                v.setDateVisite(rs.getString("dateVisite"));
                v.setModeVisite(rs.getString("modeVisite"));
                v.setStatus(rs.getString("status"));
                v.setScore(rs.getInt("score"));
                v.setTemps(rs.getInt("temps"));
                v.setEvalution(rs.getString("evaluation"));
                v.setCommentaire(rs.getString("commentaire"));
                
                L.add(v);

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
        
	
	
	
	

	@PostMapping("/{visiteId}")
	   
    public Visite create(@PathVariable(value="userId") String id, @RequestBody Visite v, HttpServletResponse response){
        
        try (Connection connection = dataSource.getConnection()){

             if(! v.getVisiteId().equals(id) ){
                response.setStatus(412);
                return null;
            }
             
            else if( read(v.getVisiteId(), response) != null){
                response.setStatus(403);
                return null;
            }
            else if (read(v.getVisiteId(), response) == null){

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO visites VALUES ( '"+v.getVisiteId()+"' ,'"+v.getDefiId()+"','"+v.getVisiteur()+"',TO_TIMESTAMP('"+v.getDateVisite()+"','YYYY-MM-DD HH:MI:SS'),'"+v.getModeVisite()+"','"+v.getStatus()+"','"+v.getScore()+"','"+v.getTemps()+"','"+v.getEvalution()+"','"+v.getCommentaire()+"')");
                
                
                ResultSet rs = stmt.executeQuery("SELECT * FROM visites WHERE visiteId = '"+v.getVisiteId()+"'");
                
                Visite visiteCreated = new Visite(null,null, null, null, null, null, 0, 0, null, null);
                
                rs.next();
                visiteCreated.setVisiteId(rs.getString("visiteId"));
                visiteCreated.setDefiId(rs.getString("defiId"));
                visiteCreated.setVisiteur(rs.getString("visiteur"));
                visiteCreated.setDateVisite(rs.getString("dateVisite"));
                visiteCreated.setModeVisite(rs.getString("modeVisite"));
                visiteCreated.setStatus(rs.getString("status"));
                visiteCreated.setScore(rs.getInt("score"));
                visiteCreated.setTemps(rs.getInt("temps"));
                visiteCreated.setEvalution(rs.getString("evaluation"));
                visiteCreated.setCommentaire(rs.getString("commentaire"));
                
                
                response.setStatus(200);
                return visiteCreated;

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
	

	@Override
	@GetMapping("/{visiteId}")    
    public Visite read(@PathVariable(value="userId") String id, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();


            ResultSet rs = stmt.executeQuery("SELECT * FROM visites WHERE visiteId = '"+id+"'");

           
             Visite visiteReaded= new Visite(null,null, null, null, null, null, 0, 0, null, null);
             
            if(rs.next()){
            	
            	visiteReaded.setVisiteId(rs.getString("visiteId"));
                visiteReaded.setDefiId(rs.getString("defiId"));
                visiteReaded.setVisiteur(rs.getString("visiteur"));
                visiteReaded.setDateVisite(rs.getString("dateVisite"));
                visiteReaded.setModeVisite(rs.getString("modeVisite"));
                visiteReaded.setStatus(rs.getString("status"));
                visiteReaded.setScore(rs.getInt("score"));
                visiteReaded.setTemps(rs.getInt("temps"));
                visiteReaded.setEvalution(rs.getString("evaluation"));
                visiteReaded.setCommentaire(rs.getString("commentaire"));
            	
            return visiteReaded;

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

	
	@PutMapping("/{visiteId}")   
    @Override
    public Visite update(@PathVariable(value="visiteId") String id, @RequestBody Visite v, HttpServletResponse response){
        try (Connection connection = dataSource.getConnection()){


            if(!v.getVisiteId().equals(id)){
                response.setStatus(412);
                return null;
            }
            else if(read(id, response) == null){
                response.setStatus(403);
                return null;
            }
            else if(read(v.getVisiteId(), response) != null){
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE  visites set visiteId = '"+v.getVisiteId()+"', defiId = '"+v.getDefiId()+"', visiteur = '"+v.getVisiteur()
                +"', dateVisite = TO_TIMESTAMP('"+v.getDateVisite()+"','YYYY-MM-DD HH:MI:SS'), modeVisite = '"+v.getModeVisite()+"', status = "+v.getStatus()+"', score = '"+v.getScore()+
                "', temps = "+v.getTemps()+", evaluation = '"+v.getEvalution()+"', commentaire = '"+v.getCommentaire()+"' WHERE visiteId = '"+v.getVisiteId()+"'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM visites WHERE visiteId = '"+v.getVisiteId()+"'");

           
                Visite visite = new Visite(null,null, null, null, null, null, 0, 0, null, null);
                rs.next();
                visite.setVisiteId(rs.getString("visiteId"));
                visite.setDefiId(rs.getString("defiId"));
                visite.setVisiteur(rs.getString("visiteur"));
                visite.setDateVisite(rs.getString("dateVisite"));
                visite.setModeVisite(rs.getString("modeVisite"));
                visite.setStatus(rs.getString("status"));
                visite.setScore(rs.getInt("score"));
                visite.setTemps(rs.getInt("temps"));
                visite.setEvalution(rs.getString("evaluation"));
                visite.setCommentaire(rs.getString("commentaire"));

                return visite;

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

	@DeleteMapping("/{visiteId}")
    public void delete(@PathVariable(value="userId") String id, HttpServletResponse response){
       try (Connection connection = dataSource.getConnection()){

           if(read(id, response) == null ){
               response.setStatus(404);
           }
           else{
               
           Statement stmt = connection.createStatement();
           stmt.executeUpdate("DELETE FROM  visites WHERE visiteId = '"+id+"'");

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
