package navy_hw1.demo;

import entities.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import entities.PlayerRepository;

@Controller
@EnableAutoConfiguration
public class PostsController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

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
}