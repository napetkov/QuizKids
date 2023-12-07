package bg.softuni.quizkids.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalNotFoundExceptionHandler {

    @ExceptionHandler(value = {QuestionNotFoundException.class})
    public ModelAndView questionNotFound(QuestionNotFoundException questionNotFoundException) {

        ModelAndView modelAndView = new ModelAndView("object-not-found");
            modelAndView.addObject("message", questionNotFoundException.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(value = {UserNotUniqueException.class})
    public ModelAndView userNotUnique(UserNotUniqueException userNotUniqueException) {

        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("message", userNotUniqueException.getMessage());

        return modelAndView;
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ModelAndView userNotFound(UserNotFoundException userNotFoundException) {

        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("message", userNotFoundException.getMessage());

        return modelAndView;
    }

}
