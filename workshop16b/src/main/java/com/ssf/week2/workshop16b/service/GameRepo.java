package com.ssf.week2.workshop16b.service;

import com.ssf.week2.workshop16b.model.Backgammon;

public interface GameRepo {
    public int save(final Backgammon bg);

    public Backgammon findById(final String id);

    public int update(final Backgammon bg);
}
