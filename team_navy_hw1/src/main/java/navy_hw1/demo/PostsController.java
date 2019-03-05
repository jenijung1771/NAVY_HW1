package navy_hw1.demo;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class PostsController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlaysRepository playsRepository;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "login";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String submitPost(@ModelAttribute("post") Post post) {
        String username = post.getUsername();
        String password = post.getPassword();
        boolean createNew = post.getCreateNew();
        Integer result;

        if (createNew) {
            result = playerRepository.newLogin(username, password);

            if (result == 0) {
                return "index";
            } else {
                return "login"; // user already exists
            }

        } else {
            result = playerRepository.validateLogin(username, password);

            if (result == 1) {
                post.setGames(gameRepository.findByPlayerID(username));
                return "index";
            } else {
                return "login"; // invalid credentials
            }
        }
    }

    @RequestMapping(value = "/GameLog", method = RequestMethod.GET)
    public String getLog(Model model) {
        model.addAttribute("post", new Post());
        return "index";
    }

    @RequestMapping(value = "/GameLog", method = RequestMethod.POST)
    public String postLog(@ModelAttribute("post") Post post) {
        return "GameLog";
    }

    @RequestMapping(value = "/ViewDetails", method = RequestMethod.GET)
    public String getDetails(@RequestParam(name="gameid") int gameID, Model model) {
        List<Plays> turns = playsRepository.findByPlaysIDGameID(gameID);
        model.addAttribute("details", turns);
        return "LogPage";
    }

}