package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@SessionAttributes("name")
public class ToDoControllerJpa {


    private TodoRepository todoRepository;

    public ToDoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping(value="todo")
    public String listAllTodos(ModelMap model){
        String username = getLoggedInUserName(model);
        List<Todo> todosList = todoRepository.findByUsername(username);
        model.addAttribute("todos", todosList);
        return "todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap model){
        Todo todo = new Todo(0, getLoggedInUserName(model), " ", LocalDate.now(), false);
        model.put("todo",todo);
        return "add-todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewToDo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "add-todo";
        }
        String username = getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todo";
    }

    @RequestMapping(value="delete-todo")
    public String deleteToDo(@RequestParam int id){
        todoRepository.deleteById(id);
        return "redirect:todo";
    }
    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateToDoPage(@RequestParam int id, ModelMap model){
        Todo todo = todoRepository.findById(id).get();
        model.put("todo",todo);
        return "add-todo";
    }
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateToDo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "update-todo";
        }
        String username = getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todo";
    }

    private static String getLoggedInUserName(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }


}
