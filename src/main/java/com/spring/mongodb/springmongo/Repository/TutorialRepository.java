package com.spring.mongodb.springmongo.Repository;


import com.spring.mongodb.springmongo.Model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TutorialRepository extends MongoRepository<Tutorial,String>{


    //List<Tutorial> findTutorialByName(String title);
    //List<Tutorial> findTutorialIfPublished(boolean publish);

}
