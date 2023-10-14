package com.trivia.triviagame;

import java.util.ArrayList;
import java.util.List;

public class Trivia {
    public List<String> questions = new ArrayList<String>();
    public List<String> answers = new ArrayList<String>();
    private int points = 0;
    private int state = 0;
    public int getpoints(){
        return this.points; 
     }
    public void setpoints(int points){
      this.points = points;
    }
     public void pointcontroller(int var){
        if(var == 0){
         this.points = var;
        } else {
         this.points = var+points; 
        }
     }
     
    public int getstate(){
        return this.state; 
     }
     public void setstate(int state){
         this.state = state; 
     }
     //liikuttaa state muutujaa yhden ylöspäin
     public void stateprogress(){
        if(state<questions.size()){
         this.state = this.state +1; 
        } else {
         
        }
     }
}
