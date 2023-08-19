package com.keyin.User;

import com.keyin.BinaryTree.BinarySearchTree;
import com.keyin.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/enter-numbers")
    public String showUserInputPage() {
        return "enter-numbers";
    }

    @GetMapping("/")
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @PostMapping("/process-numbers")
    public <exception extends Throwable> String processUserInput(@RequestParam("numbers") String numbers, Model model) {
        // Process the user input (you can add your logic here)
        System.out.println("User input numbers: " + numbers);

        // Construct the binary search tree
        BinarySearchTree bst = new BinarySearchTree();
        String[] numberArray = numbers.split("\\s+");
        for (String number : numberArray) {
            try {
                int value = Integer.parseInt(number);
                bst.insert(value);
            } catch (NumberFormatException e) {
                // Handle invalid input (e.g., non-integer values)
            }
        }

        // Convert the tree structure to JSON format
        String treeJson = bst.toJson();

        // Save the user input and tree structure to the database
        User user = new User();
        user.setInput(numbers);
        user.setTree(treeJson);
        try {
            userRepository.save(user);
            System.out.println("User saved successfully!");
        } catch (Exception e) {
            System.out.println("Couldn't save.");
        }


        // Add the JSON data to the model
        model.addAttribute("jsonData", treeJson);

        return "process-numbers"; // Show the "process-numbers" page with JSON data
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<User> userInputList = userRepository.findAll();

        List<String> treeJsonList = new ArrayList<>();

        for (User userInput : userInputList) {
            BinarySearchTree bst = constructBinarySearchTree(userInput.getInput());
            String treeJson = bst.toJson();
            treeJsonList.add(treeJson);
        }

        model.addAttribute("treeJsonList", treeJsonList);

        return "previous-trees";
    }

    private BinarySearchTree constructBinarySearchTree(String numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        String[] numberArray = numbers.split("\\s+");
        for (String number : numberArray) {
            try {
                int value = Integer.parseInt(number);
                bst.insert(value);
            } catch (NumberFormatException e) {
            }
        }
        return bst;
    }

}
