package com.z1max.lunchvotingsystem.to;

public class RestaurantWithVotes {
    private Integer id;
    private String name;
    private int votes;

    public RestaurantWithVotes(Integer id, String name, int votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", votes=").append(votes);
        sb.append('}');
        return sb.toString();
    }
}
