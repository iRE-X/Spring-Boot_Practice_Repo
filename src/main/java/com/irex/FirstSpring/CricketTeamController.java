package com.irex.FirstSpring;

import com.irex.FirstSpring.Cricket.CricketTeam;
import com.irex.FirstSpring.Cricket.CricketTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CricketTeamController {
    private final CricketTeamService cricketTeamService;

    public CricketTeamController(CricketTeamService cricketTeamService) {
        this.cricketTeamService = cricketTeamService;
    }

    @GetMapping("/teams")
    public List<CricketTeam> findAll() {
        return cricketTeamService.findAll();
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<CricketTeam> findById(@PathVariable Integer id) {
        CricketTeam team = cricketTeamService.findById(id);
        if(team != null) return new ResponseEntity<>(team, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/insert-teams-into-database")
    public String fetchTeams() {
        for(CricketTeam team : CricketTeam.getTeams())
            cricketTeamService.createCricketTeam(team);

        return "Successfully Inserted all Cricket Teams into Database...\nYou can now play around..";
    }

    @PostMapping("/teams")
    public ResponseEntity<String> createCricketTeam(@RequestBody CricketTeam team) {
        cricketTeamService.createCricketTeam(team);
        return new ResponseEntity<>("Successfully Created...", HttpStatus.CREATED);
    }
}
