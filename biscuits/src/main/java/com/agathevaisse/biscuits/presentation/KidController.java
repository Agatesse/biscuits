package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.KidService;
import com.agathevaisse.biscuits.domain.Kid;
import com.agathevaisse.biscuits.domain.Mission;
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
    public void createKid(@RequestBody Kid kid) {
        kidService.createKid(kid);
    }

    @GetMapping(value = "api/kids/all")
    public List<Kid> getKids() {
        return kidService.getKids();
    }

    @GetMapping(value = "api/kids/{id}")
    public Kid findKidById(@PathVariable("id") int id) {
        return kidService.findKidById(id);
    }

    @GetMapping(value = "api/kids/search/{nickname}")
    public List<Kid> findKidsByNickname(@PathVariable("nickname") String nickname) {
        return kidService.findKidsByNickname(nickname);
    }

    @DeleteMapping(value = "api/kids/delete/{id}")
    public void deleteKidById(@PathVariable("id") int id) {
        kidService.deleteKidById(id);
    }

    @DeleteMapping(value = "api/kids/delete")
    public void deleteKids() {
        kidService.deleteKids();
    }

    @PatchMapping(value = "api/kids/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateKid(@PathVariable("id") int id, @RequestBody Kid kid) {
        kidService.updateKid(id, kid);
    }
}