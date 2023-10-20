package com.trivia.triviagame;

import java.util.ArrayList;
import java.util.List;

public class Trivia {
    public List<String> questions = new ArrayList<String>();
    public List<String> answers = new ArrayList<String>();
    public List<String> scores = new ArrayList<String>();
    private int points = 0;
    private int state = 0;
    private int scoreid = 0; 
    String name = ""; 
    public int getpoints(int amount){
        scores.add(points + "/" + amount);
        return this.points; 
     }
    public void reset(){
      this.points = 0;
      this.state = 0; 
    }
    public void setpoints(int points){
      this.points = points;
    }
    public void setScoreid(int scoreid){
      this.scoreid = scoreid;
    }
    public int getscoreid(){
      return this.scoreid;
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
     public void stateprogress(){
        if(state<questions.size()){
         this.state = this.state +1; 
        } else {
         
        }
     }
       public void setName(String name){
      this.name = name;
      }
        public void advance(){
        this.scoreid = scoreid+1;
      }
     @Override public String toString(){
         return this.name;
     }
}
