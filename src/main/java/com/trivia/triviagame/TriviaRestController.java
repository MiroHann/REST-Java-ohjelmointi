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
        Service.questioninit(1);
    }
    //Aloitus sivu ohjeilla
    @GetMapping("/")
    public String startPage(){ 
        return "Welcome to my simple trivia! You will be presented with questions about all sorts of things. By using the path /selection/thenumberyouwant you can select one of the question packages! By standard there's 3 question packs, but you can make a Custom one using the /custom/ path  You will be presented with the first question and you can answer it using a POST method to the path /question/ with the Key as answerkey and Value as \'your answer\'";
    }
    //valitsee yhden tehdyistä kysymys seteistä idn perusteella
    @GetMapping("/selection/{id}")
    public String selection(@PathVariable Integer id){
        Service.questioninit(id);
        return "The questions have been selected! Use the /question/ path post with the answer in the body! The first question is: " + Service.questions();
    }
    //näyttää tämän hetkisen kysymyksen
    @GetMapping("/question/" )
    public String guestion(){
        return Service.questions();
    }
    //näyttää valitun listan kysymyksiä
    @GetMapping("/listofquestions/")
    public Map<String,String>Json(){
        return Service.getQuestions();  
    }
    //ottaa vastann ruumiissa vastauksen käyttäjältä
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
