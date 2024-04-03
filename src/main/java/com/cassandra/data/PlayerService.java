package com.cassandra.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepo;

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    public Player addPlayer(Player newPlayer) {
        return playerRepo.save(newPlayer);
    }

    public Player getPlayerById(int id) {
        Optional<Player> player = playerRepo.findById(id);
        if (player.isEmpty()) {
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");
        }
        return player.get();
    }

}
