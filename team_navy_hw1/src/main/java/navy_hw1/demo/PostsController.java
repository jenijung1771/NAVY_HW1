package navy_hw1.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import entities.PlayerRepository;

@Controller
@EnableAutoConfiguration
public class PostsController {
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/LogPage", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "LogPage";
    }

    @PostMapping("/LogPage")
    public String submitPost(@ModelAttribute Post post) {
        int result = playerRepository.validateLogin(post.getUsername(), post.getPassword());

        if (result == 1) {
            return post.getUsername();
        } else {
            return "LogPage";
        }
    }
}