package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.KidService;
import com.agathevaisse.biscuits.domain.kid.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class KidController {

    @Autowired
    KidService kidService;

    private static final Logger logger = LoggerFactory.getLogger(KidController.class);

    @PostMapping(value = "api/kids/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createKid(@RequestBody Kid kid) {
        return kidService.createKid(kid);
    }

    @GetMapping(value = "api/kids/all")
    public List<Kid> getKids() {
        return kidService.getKids();
    }

    @GetMapping(value = "api/kids/{id}")
    public Kid findKidById(@PathVariable("id") Long id) {
        return kidService.findKidById(id);
    }

    @GetMapping(value = "api/kids/findkidsbyuser/{id}")
    public List<Kid> findKidsByUser(@PathVariable("id") Long id) {
        return kidService.findKidsByUser(id);
    }

    @GetMapping(value = "api/kids/findkidsbynickname/{nickname}")
    public List<Kid> findKidsByNickname(@PathVariable("nickname") String nickname) {
        return kidService.findKidsByNickname(nickname);
    }

    @DeleteMapping(value = "api/kids/delete/{id}")
    public boolean deleteKidById(@PathVariable("id") Long id) {
        return kidService.deleteKidById(id);
    }

    @DeleteMapping(value = "api/kids/delete/all")
    public boolean deleteKids() {
        return kidService.deleteKids();
    }

    @PutMapping(value = "api/kids/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Kid updateKid(@PathVariable("id") Long id, @RequestBody Kid kid) {
        logger.info(kid.toString());
        return kidService.updateKid(id, kid);
    }
}