/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.dtos;

import co.edu.uniandes.csw.deporte.entities.PostEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Camilo Garcia
 */
public class PostDTO implements Serializable{
    
    private BlogDTO blogDTO;
    
    private Long id;
    
    private String descripcion;
    
    private ClienteDTO clienteDto;
    
    public PostDTO()
    {
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param postEntity: Es la entidad que se va a convertir a DTO
     */
    public PostDTO(PostEntity postEntity) {
        if (postEntity != null) {
            {
            this.id = postEntity.getId();
            }
            if(postEntity.getBlog() != null)
            {
                this.blogDTO = new BlogDTO(postEntity.getBlog());
            }
            else
            {
                this.blogDTO = null;
            }
            this.descripcion = postEntity.getIdentificador();
        }
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public PostEntity toEntity()
    {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(this.getId());
        postEntity.setIdentificador(descripcion);
        if(this.getBlogDTO() != null)
        {
        postEntity.setBlog(this.getBlogDTO().toEntity());
        }
        return postEntity;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the clienteDto
     */
    public ClienteDTO getClienteDto() {
        return clienteDto;
    }

    /**
     * @param clienteDto the clienteDto to set
     */
    public void setClienteDto(ClienteDTO clienteDto) {
        this.clienteDto = clienteDto;
    }
}
