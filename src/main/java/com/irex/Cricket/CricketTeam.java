package com.irex.Cricket;

public class CricketTeam {
    private final String teamName;
    private final String hometown;

    public CricketTeam(String teamName, String hometown) {
        this.teamName = teamName;
        this.hometown = hometown;
    }

    public String getHometown() {
        return hometown;
    }

    public String getTeamName() {
        return teamName;
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
