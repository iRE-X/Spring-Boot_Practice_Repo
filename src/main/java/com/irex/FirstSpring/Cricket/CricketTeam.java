package com.irex.FirstSpring.Cricket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CricketTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String teamName;
    private String hometown;

    public CricketTeam() {
    }

    public CricketTeam(String teamName, String hometown) {
        this.teamName = teamName;
        this.hometown = hometown;
    }

    public CricketTeam(Integer id, String teamName, String hometown) {
        this.id = id;
        this.teamName = teamName;
        this.hometown = hometown;
    }

    public String getHometown() {
        return hometown;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getId() {
        return id;
    }

    public static CricketTeam[] getTeams() {
        CricketTeam[] teams = new CricketTeam[10];

        teams[0] = new CricketTeam("PBKS", "Punjab");
        teams[1] = new CricketTeam("RCB", "Bengaluru");
        teams[2] = new CricketTeam("GT", "Gujarat");
        teams[3] = new CricketTeam("DC", "Delhi");
        teams[4] = new CricketTeam("MI", "Mumbai");
        teams[5] = new CricketTeam("LSG", "Lucknow");
        teams[6] = new CricketTeam("CSK", "Chennai");
        teams[7] = new CricketTeam("SRH", "Hyderabad");
        teams[8] = new CricketTeam("RR", "Rajasthan");
        teams[9] = new CricketTeam("KKR", "Kolkata");

        return teams;
    }
}
