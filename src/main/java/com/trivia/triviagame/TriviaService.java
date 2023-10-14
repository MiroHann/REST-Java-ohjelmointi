package com.trivia.triviagame;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TriviaService {
    Trivia TriviaOOP = new Trivia();

    //kysymysten alustus valinnan perusteella
    void questioninit(int var){
        if(var>0){
        //nollauksen esto itse tehtyä triviaa varten
        TriviaOOP.pointcontroller(0);
        TriviaOOP.questions.clear();
        TriviaOOP.answers.clear();
        }
        if(var == 1){
        TriviaOOP.questions.add("What is the capital of Australia?");
        TriviaOOP.questions.add("What's the biggest import from Brazil?");
        TriviaOOP.questions.add("Where did the tomten legend start?");
        TriviaOOP.answers.add("Canberra");
        TriviaOOP.answers.add("Coffee");
        TriviaOOP.answers.add("Sweden");
        } else if(var == 2) {
        TriviaOOP.questions.add("What animals does england not have?");
        TriviaOOP.questions.add("What was the first soft drink in space?");
        TriviaOOP.questions.add("Which animal can be seen on the Porche logo?");
        TriviaOOP.answers.add("Snakes");
        TriviaOOP.answers.add("Coca-cola");
        TriviaOOP.answers.add("Horse");
        } else if(var == 3) {
        TriviaOOP.questions.add("What sport is dubbed the king of sports?");
        TriviaOOP.questions.add("Who is the patron saint of Ireland?");
        TriviaOOP.questions.add("What color is a ruby?");
        TriviaOOP.questions.add("What color is a sapphire?");
        TriviaOOP.questions.add("What color is an emerald?");
        TriviaOOP.answers.add("Football");
        TriviaOOP.answers.add("St Patrick");
        TriviaOOP.answers.add("Red");
        TriviaOOP.answers.add("Blue");
        TriviaOOP.answers.add("Green");
        } else {
           
        }
    }
    String questions(){
           //tarkistestaan koot jotta ei yritettä hakea tyhjää paikkaa ja kaadeta koko ohjelmaa 
          if(TriviaOOP.getstate()<TriviaOOP.questions.size()){
          return TriviaOOP.questions.get(TriviaOOP.getstate());
          } else {
            //Errorien hallinta tilanteessa jos joku menee päin honkia 
            return "Oops there's been an error please use the selection or custom paths again to reset the game";
          }   
    }
    //hakee kysymykset Trivia.java tiedostosta ja laittaa ne json muottoon jossa ne palautettaan 
    Map<String,String> getQuestions(){
        Map<String,String> strings = new HashMap<>();
        for(int i = 0; i<TriviaOOP.questions.size();i++){
        strings.put("Question " + (i+1),TriviaOOP.questions.get(i));
        }
        return strings;
    }
    //ottaa vastaan answer muutujan jota tarkistetaan answer list muuttujasta onko annettu answer sama
    void answertest(String answer){
    //varmistetaan staten koko jotta ei haeta tyhjää paikka listasta
    if(TriviaOOP.getstate()<TriviaOOP.answers.size()){
        //verrataan vastausta
        if(answer.equals(TriviaOOP.answers.get(TriviaOOP.getstate()))){
        TriviaOOP.pointcontroller(1);
        }
    }
    //lisätään state muuttujaa jotta voidaan liikua seuraavaan kysymykseen
    TriviaOOP.stateprogress();
    }
    //katsoo pelin tilanteen ja palauttaa oikean vastauksen 
    String answerhandler(){
        String returnval = "I'm not sure about the return value?";
        //katsoo kokoon ja tilaan verrattuna milloin antaa käyttäjälle pisteet ja ohjeet uuden pelin aloitukseen 
        if(TriviaOOP.questions.size()>TriviaOOP.getstate()){
        returnval = "Answer recieved! next question is: " + questions();  
        } else {
        returnval = "Good Job, You scored " + TriviaOOP.getpoints() + "/"+ TriviaOOP.questions.size()+". " + "If you wish to play again use the /selection/(yournumber) again! or you can keep trying the same quiz by using the";
        }
        return returnval;
    }
//mahdollistaa oman visan tekemisen
Map<String,String> MakeCustom(String Question, String Answer){ 
    //resetoi arvoja ennen uusien kysymyksien asettamista
    TriviaOOP.pointcontroller(0);
    TriviaOOP.setstate(0);
    TriviaOOP.questions.clear();
    TriviaOOP.answers.clear();
    //ottaa ruumista vastaan tiedot jotka asetetaan paloitettuna kysymyksiin ja vastauksiin jolloin ne viedään muutettavaksi 
    //json tiedostoksi joka näytettään käyttäjälle.
    String[] Questions = Question.split(",");
    String[] Answers = Answer.split(",");
    for(int i = 0; Questions.length>i; i++){
    TriviaOOP.questions.add(Questions[i]);
    TriviaOOP.answers.add(Answers[i]);
    }
    //menee tekemään annetuista kysymyksistä json mutoisen palautusta varten. 
    return getQuestions(); 
}
}

