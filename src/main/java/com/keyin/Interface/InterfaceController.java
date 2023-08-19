package com.keyin.Interface;

import com.keyin.BinaryTree.BinarySearchTree;
import com.keyin.Entity.Interface;
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
public class InterfaceController {

    private final InterfaceRepository interfaceRepository;

    @Autowired
    public InterfaceController(InterfaceRepository interfaceRepository) {
        this.interfaceRepository = interfaceRepository;
    }

    @GetMapping("/enter-numbers")
    public String showInterfaceInputPage() {
        return "enter-numbers";
    }

    @GetMapping("/")
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @PostMapping("/process-numbers")
    public String processInterfaceInput(@RequestParam("numbers") String numbers, Model model) {
        System.out.println("User input numbers: " + numbers);

        BinarySearchTree bst = constructBinarySearchTree(numbers);

        String treeJson = bst.toJson();

        saveInterfaceInputAndTree(numbers, treeJson);

        model.addAttribute("jsonData", treeJson);

        return "process-numbers";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<Interface> interfaceInputList = interfaceRepository.findAll();

        List<String> treeJsonList = new ArrayList<>();

        for (Interface interfaceInput : interfaceInputList) {
            BinarySearchTree bst = constructBinarySearchTree(interfaceInput.getInput());
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

    private void saveInterfaceInputAndTree(String numbers, String treeJson) {
        Interface interfaceInput = new Interface();
        interfaceInput.setInput(numbers);
        interfaceInput.setTree(treeJson);
        interfaceRepository.save(interfaceInput);
    }
}
