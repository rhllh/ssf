package com.ssf.week2.workshop16.service;

import com.ssf.week2.workshop16.model.Backgammon;

public interface GameRepo {
    public int save(final Backgammon bg);

    public Backgammon findById(final String id);

    public int update(final Backgammon bg);
}
