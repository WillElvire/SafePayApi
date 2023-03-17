package com.safepay.fr.safepaySecure.Controllers.Users;
import com.safepay.fr.safepaySecure.BLL.Seeder.LRoleSeederService;
import com.safepay.fr.safepaySecure.BLL.Users.LRoleService;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Users.MRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/role")
public class roleController {

    @Autowired
    private LRoleService lRoleService;
    @Autowired
    private LRoleSeederService lRoleSeederService;

    roleController(LRoleSeederService lRoleSeederService){
        lRoleSeederService.seedRole();
    }

    
    @PostMapping()
    public ReturnMessage save(@Validated @RequestBody MRole  mRole) {
        ReturnMessage message = new ReturnMessage();
        var responce  = lRoleService.save(mRole);
        message = responce;
        return message;
    }


    @GetMapping()
    public ResponseEntity<ReturnMessage> findAll() {
        var  message = lRoleService.findAll();
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnMessage> findById(@PathVariable(value = "id") String id) {
        var  message  = lRoleService.findById(id);
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@Validated @RequestBody MRole competencies){
        var response = lRoleService.save(competencies);
        if(response.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping()
    public void delete(@Validated @RequestBody MRole competencies){
        lRoleService.deleteById(competencies.getId());
    }


}
