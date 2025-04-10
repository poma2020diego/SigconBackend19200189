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
import java.util.Optional;
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
        Integer idTipoDocumento = personaRequest.getIdTipoDocumento();
        TipoDocumento tipoDocumento =tipoDocumentoRepository.findById(idTipoDocumento).get();
        if(tipoDocumento==null) return new PersonaResponse();
        
        String idUbigeo=personaRequest.getIdUbigeo();
        Ubigeo ubigeo=ubigeoRepository.findById(idUbigeo).get();
        if(ubigeo==null) return new PersonaResponse();
        
        Persona persona=new Persona(
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
        persona=personaRepository.save(persona);
        return PersonaResponse.fromEntity(persona);
    }
    public PersonaResponse updatePersona(PersonaRequest personaRequest){
        Integer idTipoDocumento = personaRequest.getIdTipoDocumento();
        TipoDocumento tipoDocumento =tipoDocumentoRepository.findById(idTipoDocumento).get();
        if(tipoDocumento==null) return new PersonaResponse();
        
        String idUbigeo=personaRequest.getIdUbigeo();
        Ubigeo ubigeo=ubigeoRepository.findById(idUbigeo).get();
        if(ubigeo==null) return new PersonaResponse();
        
        Persona persona=new Persona(
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
        persona=personaRepository.save(persona);
        return PersonaResponse.fromEntity(persona);
    }
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }
    
    public PersonaResponse findPersona(Long id){                
        Optional<Persona> result=personaRepository.findById(id);
        if(!result.isPresent())
            return null;
        return PersonaResponse.fromEntity(result.get());
    }
    
}
