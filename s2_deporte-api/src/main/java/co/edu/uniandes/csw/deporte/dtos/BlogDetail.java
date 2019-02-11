/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class BlogDetail extends BlogDTO implements Serializable{
    
    private ArrayList<PostDTO> postDTO;
   
    public BlogDetail()
    {
        
    }

    /**
     * @return the postDTO
     */
    public ArrayList<PostDTO> getPostDTO() {
        return postDTO;
    }

    /**
     * @param postDTO the postDTO to set
     */
    public void setPostDTO(ArrayList<PostDTO> postDTO) {
        this.postDTO = postDTO;
    }
    
}
