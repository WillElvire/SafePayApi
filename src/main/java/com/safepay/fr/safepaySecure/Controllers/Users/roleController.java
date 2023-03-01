package com.safepay.fr.safepaySecure.Controllers.Users;

import com.safepay.fr.safepaySecure.BML.Users.MRole;
import com.safepay.fr.safepaySecure.DAL.Users.ARoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class roleController {

    @Autowired
    private ARoleRepository aRoleRepository;

    @PostMapping()
    public MRole save(@Validated @RequestBody MRole  mRole) {
        return aRoleRepository.save(mRole);
    }


    @GetMapping()
    public List<MRole> findAll() {
        return (List<MRole>) aRoleRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<MRole> findById(@PathVariable(value = "id") String id) {
        Optional<MRole> competencies = aRoleRepository.findById(id);
        if(competencies.isPresent()) {
            return ResponseEntity.ok().body(competencies.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<MRole> update(@Validated @RequestBody MRole competencies){
        return new ResponseEntity<>(aRoleRepository.save(competencies), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public void delete(@Validated @RequestBody MRole competencies){
        aRoleRepository.deleteById(competencies.getId());
    }


}
