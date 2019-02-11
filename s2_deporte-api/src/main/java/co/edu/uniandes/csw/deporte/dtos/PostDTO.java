/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class PostDTO implements Serializable{
    private BlogDTO blogDTO;
    public PostDTO()
    {
        
    }

    /**
     * @return the blogDTO
     */
    public BlogDTO getBlogDTO() {
        return blogDTO;
    }

    /**
     * @param blogDTO the blogDTO to set
     */
    public void setBlogDTO(BlogDTO blogDTO) {
        this.blogDTO = blogDTO;
    }
}
