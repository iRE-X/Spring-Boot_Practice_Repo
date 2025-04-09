package com.irex.FirstSpring;

import com.irex.Cricket.CricketTeam;
import com.irex.Cricket.IPL;
import com.irex.Cricket.Match;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/ipl")
public class IPLController {
    private final IPL ipl;

    public IPLController() {
        ipl = new IPL(CricketTeam.getTeams());
    }

    @GetMapping("/schedules")
    public ArrayList<Match> allSchedules(String[] args) {
        return ipl.getMatchSchedules();
    }

    @GetMapping("/schedules/day/{day}")
    public Match getMatchOnDay(@PathVariable int day) {
        return ipl.getMatchOnDay(day);
    }

    @GetMapping("/schedules/{team}")
    public ArrayList<Match> getMatchesByTeamName(@PathVariable String team) {
        return ipl.getMatchesByTeamName(team);
    }

    @GetMapping("/teams")
    public CricketTeam[] getTeamList() {
        return CricketTeam.getTeams();
    }
}