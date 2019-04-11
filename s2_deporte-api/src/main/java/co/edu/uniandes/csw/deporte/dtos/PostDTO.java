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
    
    private String nombre;
    
    private String contenido;
    
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
            this.nombre = postEntity.getNombre();
            this.contenido = postEntity.getContenido();
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
           
        }
    }

     /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
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
        postEntity.setNombre(this.getNombre());
        postEntity.setContenido(this.getContenido());
        if(this.getBlogDTO() != null)
        {
        postEntity.setBlog(this.getBlogDTO().toEntity());
        }
        return postEntity;
    }

    /**
     * @return the descripcion
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setNombre(String descripcion) {
        this.nombre = descripcion;
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
