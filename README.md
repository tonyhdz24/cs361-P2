# Project 2: Non-deterministic Finite Automata

* Authors: Antonio Hernandez and Zachary Johnston
* Class: CS361 Section 1
* Semester: Spring 2025

## Overview

This Java application displays an animated marshmallow with features based on
user input specifying sponginess, color, sugar density, and burn resistance.

This java project is a way to represent NFAs. Like many classes in java: NFA requires outside 
code such as a tester or a main to be used. NFA has the capability of determining whether a given string
is valid for the NFA that you set up.

## Reflection

Zach - The most challenging part to this project for me was finding time to do it and figuring out how to program
eclosure. I've been surprisingly stretched for time for the past three weeks, and as a result that put more pressure
on Tony for the project. I felt this project was a lot better communication wise, and we did start out
with a pretty concrete plan that we stuck to. For some reason to me recursion felt like a good tool for NFAs particularly
with how it branches. 

## Compiling and Using

To compile, execute the following command in the main project directory:
```
$ javac NFA.java
```

Using the project can be done through the tester or through using NFA and its methods to set
up an NFA with the states and transitions that you desire. Through the method accepts you can also test whether a 
given input string is valid for your NFA. 

You will be prompted for integer values representing distance to the fire,
fire intensity, marshmallow burn resistance, and marshmallow sugar density.

## Sources used


