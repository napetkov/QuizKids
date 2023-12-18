package bg.softuni.quizkids.controller;

import bg.softuni.quizkids.models.binding.UserChangePasswordBindingModel;
import bg.softuni.quizkids.models.binding.UserEditProfileBindingModel;
import bg.softuni.quizkids.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/")
public class UserProfileController {
    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public String getUserProfile(Model model){

        model.addAttribute("loggedUserProfileInfo",userService.loggedUserProfileInfo());

        return "profile";
    }
    @GetMapping("/profile/edit")
    public String editProfile(Model model){

        model.addAttribute("userEditInfo", userService.getLoggedUserEditInfo());

        if(!model.containsAttribute("userEditProfileBindingModel")){
            model.addAttribute("userEditProfileBindingModel", new UserEditProfileBindingModel());
        }

        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String editUserProfile(@Valid UserEditProfileBindingModel userEditProfileBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes
                                  ){

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("userEditProfileBindingModel", userEditProfileBindingModel);
                redirectAttributes
                        .addFlashAttribute("org.springframework.validation.BindingResult.userEditProfileBindingModel",bindingResult);
                return "redirect:/users/profile/edit";
            }


        userService.editProfile(userEditProfileBindingModel);

            return "redirect:/users/profile";
        }
    @GetMapping("/profile/edit/changePassword")
    public String changePassword(Model model){

        if(!model.containsAttribute("userChangePasswordBindingModel")){
            model.addAttribute("userChangePasswordBindingModel", new UserChangePasswordBindingModel());
        }

        return "change-password";
    }

    @PostMapping("/profile/edit/changePassword")
    public String changePassword(@Valid UserChangePasswordBindingModel userChangePasswordBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes
    ){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userChangePasswordBindingModel", userChangePasswordBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userChangePasswordBindingModel",bindingResult);
            return "redirect:/users/profile/edit/changePassword";
        }


        userService.changePassword(userChangePasswordBindingModel);

        return "redirect:/users/logout";
    }
}
