package com.spring.mongodb.springmongo.Controller;

import com.spring.mongodb.springmongo.Model.Tutorial;
import com.spring.mongodb.springmongo.Repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

   /*
    public Tutorial createEmployee(@RequestBody Tutorial employee){
        return tutorialRepository.save(employee);

    }*/
   @PostMapping("/tutorials")
    public ResponseEntity<Tutorial>createTutorial(@RequestBody Tutorial tutorial){
      try {
           Tutorial tut = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
           return new ResponseEntity<>(tut,HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
       }
       // Tutorial tut=tutorialRepository.save(tutorial);
        //return new ResponseEntity<>(tut, HttpStatus.CREATED);
    }


    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>>getTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tutorial> tutorials = new ArrayList<>();

            if (title == null) {
                tutorialRepository.findAll().forEach(tutorials::add);
            } else if (title != null) {
                //          tutorialRepository.findTutorialByName(title).forEach(tutorials::add);
            } else if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("tutorials/{id}")
    public ResponseEntity<HttpStatus>deleteTutorial(@PathVariable("id")String id){
       try{
           tutorialRepository.deleteById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("/tutorial")
    public ResponseEntity<HttpStatus>deleteAllTutorials(){
       try{
           tutorialRepository.deleteAll();
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial>updateTutorial(@PathVariable("id")String id,@RequestBody Tutorial tuto){

           Optional<Tutorial> tut = tutorialRepository.findById(id);
           if(tut.isPresent()){
               Tutorial tutorial = tut.get();
               tutorial.setTitle(tuto.getTitle());
               tutorial.setDescription(tuto.getDescription());
               tutorial.setPublished(tuto.isPublished());
               return new ResponseEntity<>(tutorialRepository.save(tutorial),HttpStatus.OK);
           }else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }


    }

}
