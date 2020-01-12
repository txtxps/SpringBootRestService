package ru.topjava.springboot.to;

import ru.topjava.springboot.model.Vote;

public class VoteTo {
    private final Vote vote;
    private final boolean created;

    public VoteTo(Vote vote, boolean created) {
        this.vote = vote;
        this.created = created;
    }

    public Vote getVote() {
        return vote;
    }

    public boolean isCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "vote=" + vote +
                ", created=" + created +
                '}';
    }
}
