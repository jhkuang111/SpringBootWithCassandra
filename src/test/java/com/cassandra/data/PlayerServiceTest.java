package com.cassandra.data;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerService playerService;

    @Test
    void testGetAllPlayers() {
        Player one = new Player(1, "playerOne", "NationOne", new Date(), 10);
        Player two = new Player(2, "playerTwo", "NationTwo", new Date(), 20);
        when(playerRepository.findAll()).thenReturn(Arrays.asList(one, two));
        int expectedPlayers = 2;
        List<Player> listOfPlayers = playerService.getAllPlayers();
        assertEquals(expectedPlayers, listOfPlayers.size());
        assertEquals("playerOne", listOfPlayers.get(0).getName());
        assertEquals("playerTwo", listOfPlayers.get(1).getName());
    }
}
