package service;

import com.google.gson.Gson;
import dao.MainDao;
import dao.MovieRepository;
import lombok.RequiredArgsConstructor;
import model.Category;
import model.Movie;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class MovieService {

    private final MainDao mainDAO;
    private final MovieRepository movieRepository;



    @Transactional(readOnly = false)
    public Movie create(String name, String description, String publicyear, String language, Long categoryid,HttpServletRequest request) {

        Category category = (Category) mainDAO.loadObject(Category.class, categoryid);

        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setCategory(category);
        movie.setLanguage(language);
        movie.setPublicyear(publicyear);


        HttpSession session = request.getSession();

        Object object = mainDAO.saveObject(movie);
        return movie;
    }


    @Transactional
    public Movie update(String strFaultRecord) {
        Gson gson = new Gson();
        Movie movie = gson.fromJson(strFaultRecord, Movie.class);
        mainDAO.updateObject(movie);
        return movie;
    }

    public List<Movie> getAll() {
        List<Movie> movie=movieRepository.getAll();
        return movie;
    }

    @Transactional
    public boolean delete(Movie movie){
        try {
            mainDAO.delete(movie);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Movie> getByCategoryID(Long categoryID) {

        return movieRepository.findByCategoryID(categoryID);
    }





}

