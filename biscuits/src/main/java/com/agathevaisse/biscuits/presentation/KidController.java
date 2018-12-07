package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.KidService;
import com.agathevaisse.biscuits.domain.kid.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class KidController {

    @Autowired
    KidService kidService;

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

    @GetMapping(value = "api/kids/find-kids-by-user/{id}")
    public List<Kid> findKidsByUser(@PathVariable("id") Long id) {
        return kidService.findKidsByUser(id);
    }

    @GetMapping(value = "api/kids/find-kids-by-nickname/{nickname}")
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
        return kidService.updateKid(id, kid);
    }
}