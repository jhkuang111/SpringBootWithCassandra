package com.cassandra.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepo;

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

}
