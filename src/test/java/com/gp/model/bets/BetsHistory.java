package com.gp.model.bets;

import lombok.Data;

import java.util.Map;

@Data
public class BetsHistory {
    private Map<String, Bet> bet;
}
