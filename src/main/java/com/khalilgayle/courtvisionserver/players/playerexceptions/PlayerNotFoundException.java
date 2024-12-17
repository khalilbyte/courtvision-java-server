package com.khalilgayle.courtvisionserver.players.playerexceptions;

public class PlayerNotFoundException extends PlayerException {
    public PlayerNotFoundException(String message) {
        super(message);
    }

    public PlayerNotFoundException(Long playerId) {
        super("Player not found with the ID" + playerId);
    }
}
