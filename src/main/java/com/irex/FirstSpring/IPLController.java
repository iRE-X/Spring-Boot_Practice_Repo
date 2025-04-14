package com.irex.FirstSpring;

import com.irex.FirstSpring.Cricket.CricketTeam;
import com.irex.FirstSpring.Cricket.CricketTeamService;
import com.irex.FirstSpring.Cricket.IPL;
import com.irex.FirstSpring.Cricket.Match;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ipl")
public class IPLController {
    private final CricketTeamService cricketTeamService;
    private IPL ipl;

    public IPLController(CricketTeamService cricketTeamService) {
        this.cricketTeamService = cricketTeamService;
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Match>> allSchedules(String[] args) {
        refresh();
        List<Match> matches = ipl.getMatchSchedules();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/schedules/day/{day}")
    public Match getMatchOnDay(@PathVariable int day) {
        refresh();
        return ipl.getMatchOnDay(day);
    }

    @GetMapping("/schedules/{team}")
    public List<Match> getMatchesByTeamName(@PathVariable String team) {
        refresh();
        return ipl.getMatchesByTeamName(team);
    }

    @GetMapping("/teams")
    public List<CricketTeam> getTeamList() {
        refresh();
        return ipl.getTeams();
    }

    @GetMapping("/insert-teams-into-database")
    public String fetchTeams() {
        for(CricketTeam team : CricketTeam.getTeams())
            cricketTeamService.createCricketTeam(team);

        return "Successfully Inserted all Cricket Teams into Database...\nYou can now play around..";
    }

    private void refresh() {
        List<CricketTeam> teams = cricketTeamService.findAll();
        if(ipl == null || (ipl.getTeams().size() != teams.size()))
            ipl = new IPL(teams);
    }
}