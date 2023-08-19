// An application that runs a webpage where a user can input a series of numbers to get the resulting Binary Tree.
// It also stores previous trees and displays all the previous trees on one page.
// TODO: Database is connected but trees and numbers aren't saving to the database. The Code says that it is being saved but I don't know where.
// TODO: Code has be rewritten many times to try and find what is wrong. I can't find the problem.
//https://github.com/darlamward/FinalSprint_BSTree
// Completed by: Darla Ward
// Completed on August 18, 2023
package com.keyin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan("com.keyin.Entity")
public class BinarySearchTreeApp {
    public static void main(String[] args) {
        SpringApplication.run(BinarySearchTreeApp.class, args);
    }
}
