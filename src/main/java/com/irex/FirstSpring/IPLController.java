package com.irex.FirstSpring;

import com.irex.Cricket.CricketTeam;
import com.irex.Cricket.IPL;
import com.irex.Cricket.Match;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class IPLController {

    @GetMapping("/ipl/schedules")
    public ArrayList<Match> schedules(String[] args) {
        IPL ipl = new IPL(CricketTeam.getTeams());
        return ipl.getMatchSchedules();
    }


}