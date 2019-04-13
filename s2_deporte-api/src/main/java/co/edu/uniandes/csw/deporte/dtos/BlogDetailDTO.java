/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.BlogEntity;
import co.edu.uniandes.csw.deporte.entities.CampeonatoEntity;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Camilo Garcia
 */
public class BlogDetailDTO extends BlogDTO implements Serializable{
    
    public List<PostDTO> postDTO;
    
   
    public BlogDetailDTO()
    {
        
    }

     /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param blogEntity La entidad de la editorial para transformar a DTO.
     */
    public BlogDetailDTO(BlogEntity bloglEntity) {
        super(bloglEntity);
        if (bloglEntity != null) {
            if (bloglEntity.getPosts() != null) 
            {
                postDTO = new ArrayList<>();
                for(PostEntity entityPost : bloglEntity.getPosts()){
                   postDTO.add(new PostDTO(entityPost));
                }
            }

        }
    }
    

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO del blog para transformar a Entity
     */

    @Override
    public BlogEntity toEntity() {
        BlogEntity bloglEntity = super.toEntity();
        if (getPostDTO() != null) {
            List<PostEntity> booksEntity = new ArrayList<>();
            for (PostDTO dtoBook : postDTO) {
                booksEntity.add(dtoBook.toEntity());
            }
            bloglEntity.setPosts(booksEntity);
        }
        return bloglEntity;
    }
    /**
     * @return the postDTO
     */
    public List<PostDTO> getPostDTO() {
        return postDTO;
    }

    /**
     * @param postDTO the postDTO to set
     */
    public void setPostDTO(List<PostDTO> postDTO) {
        this.postDTO = postDTO;
    }

    }
