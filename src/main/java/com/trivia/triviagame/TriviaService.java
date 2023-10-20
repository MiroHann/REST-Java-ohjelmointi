package com.trivia.triviagame;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TriviaService {
    Trivia TriviaOOP = new Trivia();
    Trivia TriviaOOP1 = new Trivia();
    Trivia TriviaOOP2 = new Trivia();
    Trivia TriviaOOP3 = new Trivia();
    Trivia[] objects = new Trivia[4];
     Map<String,String> scores = new HashMap<>();
    int currentSelection = 0; 
    String returnval;
    public TriviaService(){
        TriviaOOP1.questions.add("What is the capital of Australia?");
        TriviaOOP1.questions.add("What's the biggest import from Brazil?");
        TriviaOOP1.questions.add("Where did the tomten legend start?");
        TriviaOOP1.answers.add("canberra");
        TriviaOOP1.answers.add("coffee");
        TriviaOOP1.answers.add("sweden");
        TriviaOOP1.setName("Questions 1");
        //
        TriviaOOP2.questions.add("What animals does england not have?");
        TriviaOOP2.questions.add("What was the first soft drink in space?");
        TriviaOOP2.questions.add("Which animal can be seen on the Porche logo?");
        TriviaOOP2.questions.add("What is the tallest mountain in the world?");
        TriviaOOP2.questions.add("What is the capital of France?");
        TriviaOOP2.questions.add("Which planet is known as the \"Red Planet\"?");
        TriviaOOP2.answers.add("snakes");
        TriviaOOP2.answers.add("coca-cola");
        TriviaOOP2.answers.add("horse");
        TriviaOOP2.answers.add("mount everest");
        TriviaOOP2.answers.add("paris");
        TriviaOOP2.answers.add("Mars");
        TriviaOOP2.setName("Questions 2");
        //
        TriviaOOP3.questions.add("What sport is dubbed the king of sports?");
        TriviaOOP3.questions.add("Who is the patron saint of Ireland?");
        TriviaOOP3.questions.add("What color is a ruby?");
        TriviaOOP3.questions.add("What color is a sapphire?");
        TriviaOOP3.questions.add("What color is an emerald?");
        TriviaOOP3.answers.add("football");
        TriviaOOP3.answers.add("st patrick");
        TriviaOOP3.answers.add("red");
        TriviaOOP3.answers.add("blue");
        TriviaOOP3.answers.add("green");
        TriviaOOP3.setName("Questions 3");
        //
        TriviaOOP.setName("CustomQuestions");
        //
        objects[0] = TriviaOOP;
        objects[1] = TriviaOOP1;
        objects[2] = TriviaOOP2;
        objects[3] = TriviaOOP3;
    }
    void questioninit(int selection){
    objects[currentSelection].reset();
    this.currentSelection = selection;  
    }
    String questions(){
        if(objects[currentSelection].answers.isEmpty()){
            return "Oops it seems like you haven't selected a question package! Use the /selection/ path to select a question package!";
        }
          if(objects[currentSelection].getstate()<objects[currentSelection].questions.size()){
          return objects[currentSelection].questions.get(objects[currentSelection].getstate());
          } else {
            return "Oops there's been an error please use the selection or custom paths again to reset the game";
          }   
    }
    Map<String,String> getQuestions(int val){
        Map<String,String> strings = new HashMap<>();
        if(val<=3 && val>=0){
        for(int a = 0; a<objects[val].questions.size(); a++){
         strings.put("Question " + (a+1) + ": " ,objects[val].questions.get(a));   
        }
    }   
        return strings;
    }
    Map<String,String> getScores(){
        int scorestate = 0;
        for(int a = 3; scorestate<=a; scorestate++){
        for(int i = 0; i<objects[scorestate].scores.size();i++){
        scores.put(objects[scorestate].toString() + " Attempt " + objects[scorestate].getscoreid() + " Score:", objects[scorestate].scores.get(i));
        objects[scorestate].getscoreid();
        }
      }
      System.out.println(scores);
        return scores;
    }
    void answertest(String answer){
    if(objects[currentSelection].getstate()<objects[currentSelection].answers.size()){
        if(answer.equals(objects[currentSelection].answers.get(objects[currentSelection].getstate()))){
        objects[currentSelection].pointcontroller(1);
        }
    }
    objects[currentSelection].stateprogress();
    }
    String answerhandler(){
        if(objects[currentSelection].questions.size()>objects[currentSelection].getstate()){
        returnval = "Answer recieved! next question is: " + questions();  
        } else if (returnval.contains("Answer")) {
        objects[currentSelection].advance();
        returnval = "Good Job, You scored " + objects[currentSelection].getpoints(objects[currentSelection].questions.size()) + "/"+ objects[currentSelection].questions.size()+". " + "If you wish to play again use the /selection/(yournumber) again!";
        } else {
        returnval = "Good Job, You scored " + objects[currentSelection].getpoints(objects[currentSelection].questions.size()) + "/"+ objects[currentSelection].questions.size()+". " + "If you wish to play again use the /selection/(yournumber) again!";    
        }
        return returnval;
    }
Map<String,String> MakeCustom(String Question, String Answer){ 
    TriviaOOP.pointcontroller(0);
    TriviaOOP.setstate(0);
    TriviaOOP.questions.clear();
    TriviaOOP.answers.clear();
    String[] Questions = Question.split(",");
    String[] Answers = Answer.split(",");
    for(int i = 0; Questions.length>i; i++){
    TriviaOOP.questions.add(Questions[i]);
    TriviaOOP.answers.add(Answers[i]);
    }
    currentSelection = 0;
    return getQuestions(0); 
}
}

