package com.irex.FirstSpring.Cricket;

import com.irex.FirstSpring.Cricket.CricketTeam;
import com.irex.FirstSpring.Cricket.CricketTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  CricketTeamService {
    private final CricketTeamRepository cricketTeamRepository;

    public CricketTeamService(CricketTeamRepository cricketTeamRepository) {
        this.cricketTeamRepository = cricketTeamRepository;
    }

    public List<CricketTeam> findAll() {
        return cricketTeamRepository.findAll();
    }

    public void createCricketTeam(CricketTeam team) {
        cricketTeamRepository.save(team);
    }

    public CricketTeam findById(Integer id) {
        return cricketTeamRepository.findById(id).orElse(null);
    }
}
