package com.example.help.models;

public class food_info {

    private String eat_time;
    private String food_name;

    public food_info(){

    }

    public food_info(String eat_time, String food_name){
        this.eat_time = eat_time;
        this.food_name = food_name;
    }

    public String getEat_time(){return eat_time;}
    public void setEat_time(){this.eat_time = eat_time;}

    public String getFood_name(){return food_name;}
    public void setFood_name(){this.food_name=food_name;}

    @Override
    public String toString(){

        return "food_info{" +
                "eat_time='" + eat_time + '\'' +
                ", food_name='" + food_name + '\'' +
                '}';
    }
}


