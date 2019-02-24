package navy_hw1.demo;

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

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "LogPage";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String submitPost(@ModelAttribute("post") Post post) {
        String username = post.getUsername();
        String password = post.getPassword();

        Integer result = playerRepository.validateLogin(username, password);

        if (result == 1) {
            return "Home";
        } else {
            return "LogPage"; // invalid credentials
        }
    }
}