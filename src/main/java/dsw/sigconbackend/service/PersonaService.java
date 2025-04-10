package dsw.sigconbackend.service;

import dsw.sigconbackend.dto.PersonaRequest;
import dsw.sigconbackend.dto.PersonaResponse;
import dsw.sigconbackend.model.Persona;
import dsw.sigconbackend.model.TipoDocumento;
import dsw.sigconbackend.model.Ubigeo;
import dsw.sigconbackend.repository.PersonaRepository;
import dsw.sigconbackend.repository.TipoDocumentoRepository;
import dsw.sigconbackend.repository.UbigeoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    UbigeoRepository ubigeoRepository;
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    
    public List<PersonaResponse> listPersonas(){
        return PersonaResponse.fromEntities(personaRepository.findAll());
    }
    
    public PersonaResponse insertPersona(PersonaRequest personaRequest){
        //Integer idTipoDocumento = personaRequest.getIdTipoDocumento();
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(personaRequest.getIdTipoDocumento()).orElse(null);
        if(tipoDocumento == null) return new PersonaResponse();
        
        //String idUbigeo = personaRequest.getIdUbigeo();
        Ubigeo ubigeo = ubigeoRepository.findById(personaRequest.getIdUbigeo()).orElse(null);
        if(ubigeo == null) return new PersonaResponse();
        
        Persona persona = new Persona(
            personaRequest.getIdPersona(),
            personaRequest.getApellidoPaterno(),
            personaRequest.getApellidoMaterno(),
            personaRequest.getNombres(),
            personaRequest.getFechaNacimiento(),
            personaRequest.getNDocumento(),
            personaRequest.getDireccion(),
            tipoDocumento,
            ubigeo
        );        
        persona = personaRepository.save(persona);
        return PersonaResponse.fromEntity(persona);
    }
    
    public PersonaResponse updatePersona(PersonaRequest personaRequest){
         //el id es obligatorio
        if(personaRequest.getIdPersona() == null) return new PersonaResponse();
        
        //Revisar si existe la persona o no
        Persona personaExistente = personaRepository.findById(personaRequest.getIdPersona()).orElse(null);
        if (personaExistente == null) return new PersonaResponse();
        
        //tipo de documento invalido
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(personaRequest.getIdTipoDocumento()).orElse(null);
        if (tipoDocumento == null) return new PersonaResponse();
        
        //ubigeo invalido
        Ubigeo ubigeo = ubigeoRepository.findById(personaRequest.getIdUbigeo()).orElse(null);
        if (ubigeo == null) return new PersonaResponse();
        
        personaExistente.setApellidoPaterno(personaRequest.getApellidoPaterno());
        personaExistente.setApellidoMaterno(personaRequest.getApellidoMaterno());
        personaExistente.setNombres(personaRequest.getNombres());
        personaExistente.setFechaNacimiento(personaRequest.getFechaNacimiento());
        personaExistente.setNDocumento(personaRequest.getNDocumento());
        personaExistente.setDireccion(personaRequest.getDireccion());
        personaExistente.setTipoDocumento(tipoDocumento);
        personaExistente.setUbigeo(ubigeo);
        
        personaExistente = personaRepository.save(personaExistente);
        return PersonaResponse.fromEntity(personaExistente);
    }
    
    public boolean deletePersonaById(Long idPersona){
        Persona persona = personaRepository.findById(idPersona).orElse(null);
        if (persona == null) return false;
        
        personaRepository.delete(persona);
        return true;
    }
    
}
