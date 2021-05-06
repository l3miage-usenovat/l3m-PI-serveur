package com.example;

import java.sql.Connection; 

import java.sql.ResultSet; 

import java.sql.Statement; 

import java.util.ArrayList; 

import javax.servlet.http.HttpServletResponse; 

import javax.sql.DataSource; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.bind.annotation.CrossOrigin; 

import org.springframework.web.bind.annotation.GetMapping; 

import org.springframework.web.bind.annotation.PathVariable; 

import org.springframework.web.bind.annotation.RequestMapping; 

import org.springframework.web.bind.annotation.RestController; 

@RestController
@CrossOrigin
@RequestMapping("/api/descriptions")

public class DescriptionDAO implements DAO<Description>{
	
	@Autowired
    private DataSource dataSource;
	
	
	@GetMapping("/")
    public ArrayList<Description> allUsers(HttpServletResponse response) {
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM descriptions");

            ArrayList<Description> L = new ArrayList<Description>();

            while(rs.next()){
            	
                Description d = new Description(0, null, null, null,0.0,null,false);
                d.setNum(rs.getInt("num"));
                d.setId(rs.getString("id"));
                d.setNom(rs.getString("nom"));
                d.setLabele(rs.getString("labele"));
                d.setDescriptions(rs.getString("descriptions"));
                d.setPoints(rs.getDouble("points"));
                d.setEncouragement(rs.getString("encouragement"));
                d.setClicked(rs.getBoolean("clicked"));
                L.add(d);
                
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
	
	

	@Override
	public Description create(String id, Description d, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/{descritionId}")    
    public Description read(@PathVariable(value="descritionId") String id, HttpServletResponse response){
         try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();


            ResultSet rs = stmt.executeQuery("SELECT * FROM descriptions WHERE id = '"+id+"'");

           
             Description descriptionReaded = new Description(0, null, null, null,0.0,null,false);
             
            if(rs.next()){
            	
            	descriptionReaded.setNum(rs.getInt("num"));
                descriptionReaded.setId(rs.getString("id"));
                descriptionReaded.setNom(rs.getString("nom"));
                descriptionReaded.setLabele(rs.getString("labele"));
                descriptionReaded.setDescriptions(rs.getString("descriptions"));
                descriptionReaded.setPoints(rs.getDouble("points"));
                descriptionReaded.setEncouragement(rs.getString("encouragement"));
                descriptionReaded.setClicked(rs.getBoolean("clicked"));
            	
     
                
            return descriptionReaded;

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

	@Override
	public Description update(String id, Description d, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	

}