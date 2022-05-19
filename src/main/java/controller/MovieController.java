package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import model.Movie;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/movie/*")
public class MovieController {

    private final MovieService movieService;

    @PostMapping(value = "/create")
    public @ResponseBody
    String createPost(@RequestParam String name, @RequestParam String description,@RequestParam String publicyear,@RequestParam String language,@RequestParam Long categoryID, HttpServletRequest request, HttpServletResponse response){
        Movie movie = movieService.create(name,description,publicyear,language,categoryID,request);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movie);
        String json = gson.toJson(map);
        return json;
    }

    @GetMapping (value = "/getAll")
    public @ResponseBody
    String getAll(){

        List<Movie> movies = movieService.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movies);
        String json = new Gson().toJson(map);
        return json;
    }



    @GetMapping(value = "/getByCategoryID")
    public @ResponseBody String getByCategoryID(HttpServletRequest request, @RequestParam Long categoryId){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Movie> movies = movieService.getByCategoryID(categoryId);

        Map<String, Object> map = new HashMap<>();

        map.put("success", true);
        map.put("data", movies);

        String json = new Gson().toJson(map);
        return json;
    }


    @PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam String strFaultRecord){

        Movie movie = movieService.update(strFaultRecord);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", movie);
        String json = gson.toJson(map);
        return json;
    }
}
