package dsw.sigconbackend.service;

import dsw.sigconbackend.model.Ubigeo;
import dsw.sigconbackend.repository.UbigeoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbigeoService {
    @Autowired
    UbigeoRepository ubigeoRepository;
    
    public List<Ubigeo> getUbigeo(){
        return (List<Ubigeo>)ubigeoRepository.findAll();
    }
}
