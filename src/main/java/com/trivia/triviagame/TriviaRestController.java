package com.trivia.triviagame;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TriviaRestController {
    TriviaService Service;
    public TriviaRestController(TriviaService service){
        this.Service = service;
    }
    //Aloitus sivu ohjeilla
    @GetMapping("/")
    public String startPage(){ 
        return "Welcome to my simple trivia! You will be presented with questions about all sorts of things. By using the path /selection/1-3 you can select one of the premade question packages and /selection/0 for the custom question package that will be discussed later! if you want to see the questions in a selected package use /listofquestions/yourgivenid path with a GET method! By standard there's 3 question packs, but you can make a Custom one using the /custom/ path. After the selection you will be presented with the first question and you can answer it using a POST method to the path /question/ with the Key as answerkey and Value as \'your answer\' in the POST body. If you are unsure about the current question you can use the path /question/ with a GET method to return the current active question! Using the /listofscores/ path with a GET method will return you the list of scores from the current session! If you want to create a custom question package use the before mentioned /custom/ path POST method with the body value keys as question and answer and values for the keys as \'your question\' and \'your answer\'. There's no real limit for the amount of questions so adding more question and answer keys with values to the same POST body will add more questions to your trivia! This custom package can be selected using /selection/0 path which will start the game like the other packages!";
    }
    //valitsee yhden tehdyistä kysymys seteistä idn perusteella
    @GetMapping("/selection/{id}")
    public String selection(@PathVariable Integer id){
        Service.questioninit(id);
        return "The questions have been selected! please answer without capital letters! The first question is: " + Service.questions();
    }
    //näyttää tämän hetkisen kysymyksen
    @GetMapping("/question/" )
    public String guestion(){
        return Service.questions();
    }
    //näyttää listan kysymyksiä idn perusteella
    @GetMapping("/listofquestions/{id}")
    public Map<String,String>Json(@PathVariable Integer id){
        return Service.getQuestions(id);  
    }
    //hakee listan pisteistä jota käyttäjä on saanut tällä pelikerralla!
     @GetMapping("/listofscores/")
    public Map<String,String>ScoreJson(){
        return Service.getScores();  
    }
    //ottaa vastann ruumiissa vastauksen käyttäjältä ja esittää seuraavan kysymysken
    @PostMapping("/question/")
    public String addpost(@RequestParam String answerkey){
        Service.answertest(answerkey);
        return Service.answerhandler(); 
    }
    //asettaa käyttäjän tekemät visakysymykset ja palauttaa kokonaisuuden json muodossa
    @PostMapping("/custom/")
    public Map<String,String> jsonpost(@RequestParam String question, @RequestParam String answer){
    return Service.MakeCustom(question,answer);
    }
}
