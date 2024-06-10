package com.example.NetflixConductorTask.AtheleteController;

import com.example.NetflixConductorTask.AtheletePojo.Athelete;
import com.example.NetflixConductorTask.Service.AtheleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class AtheleteRestController {

    @Autowired
    private AtheleteService atheleteService;

    @GetMapping("/atheletes")
    public ResponseEntity<List<Athelete>> getAllAthelete() {
        return atheleteService.getAllAthelete();
    }

    @PostMapping("/athelete")
    public ResponseEntity<Athelete> createAthelete(@Valid @RequestBody Athelete athelete) {
        Athelete atheleteSaved = atheleteService.createAthelete(athelete);
        return ResponseEntity.status(HttpStatus.CREATED).body(atheleteSaved);
    }

    @PutMapping("/athelete/{id}")
    public ResponseEntity<String> getAthelete(@RequestBody Athelete getAthelete, @PathVariable("id") String id) {
        String atheleteUpdated=atheleteService.updateAthelete(getAthelete,id);
        return ResponseEntity.ok(atheleteUpdated);
    }

    @DeleteMapping("/athelete/{id}")
    public ResponseEntity<String> deleteAthelete(@PathVariable("id") String id) {
        String atheleteDeleted=atheleteService.deleteAthelete(id);
        return ResponseEntity.ok(atheleteDeleted);
    }
}
