package com.equidog.web.api;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.CompanyDTO;
import com.equidog.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyRestApi {

    private CompanyService service;

    @Autowired
    public CompanyRestApi(CompanyService companyService){this.service=companyService;}

    @GetMapping("/company")
    public List<CompanyDTO> getAllCompany() throws ServiceException {
        return service.getAllCompany();
    }

    @GetMapping("company/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") Long id)
            throws ServiceException {
        CompanyDTO entity = service.getCompanyById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/company")
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO entity) throws ServiceException {
        CompanyDTO created = service.createCompany(entity);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping("/company/{id}/status/{isActive}")
    public ResponseEntity<CompanyDTO> updateStatus(@PathVariable Boolean isActive, @PathVariable long id)
            throws ServiceException {
        return new ResponseEntity<>(service.updateStatusCompany(isActive, id), HttpStatus.OK);
    }

    @PutMapping("/company")
    public ResponseEntity<CompanyDTO> updateCompany (@RequestBody CompanyDTO companyDTO) throws ServiceException{
        CompanyDTO update = service.createCompany(companyDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

}
