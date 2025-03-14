# Project 2: Non-deterministic Finite Automata

* Authors: Antonio Hernandez and Zachary Johnston
* Class: CS361 Section 1
* Semester: Spring 2025

## Overview

This java project is a way to represent NFAs. Like many classes in java: NFA requires outside 
code such as a tester or a main to be used. NFA has the capability of determining whether a given string
is valid for the NFA that you set up.

## Reflection

Zach - The most challenging part to this project for me was finding time to do it and figuring out how to program
eclosure. I've been surprisingly stretched for time for the past three weeks, and as a result that put more pressure
on Tony for the project. I felt this project was a lot better communication wise, and we did start out
with a pretty concrete plan that we stuck to. For some reason to me recursion felt like a good tool for NFAs particularly
with how it branches. 

Tony - I feel like completing this project went was a lot easier than the completing project 1. This is a result of us planning ahead more, understanding what was required and communicating to each other on our progress. One of the biggest things that worked well this time around was actually creating a project board with lots of tasks. This helped us stay organized and see at a glance who was working on what and what still needed to be competed. This is in contrast to project 1 where we kinda just did stuff. Setting that up did take some time especially writing good clear and informative task description but I think it was well worth it and it helped me personally really understand what was need for each task. For the most part in order to make things easy to debug we made sure to comment alot and really explain whats going on. If I could go back I would probably tell myself to think more about how each method interacts with each other. I tackled certain methods with out thinking about how other ones we've written could be used and that slowed me down a bit.

## Compiling and Using

To compile, execute the following command in the main project directory:
```
$ javac NFA.java
```

To compile test.nfa.NFATest on onyx from the top directory of these files:
```
[you@onyx]$ javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java
```
To run test.nfa.NFATest on onyx type in one single line:
```
[you@onyx]$ java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar
org.junit.runner.JUnitCore test.nfa.NFATest
```
No input required, test results will be output in the terminal

Using the project can be done through the tester or through using NFA and its methods to set
up an NFA with the states and transitions that you desire. Through the method accepts you can also test whether a 
given input string is valid for your NFA. 

Note: 'e' is a reserved symbol for an epsilon transition since epsilon isn't an ASCII character.

## Sources used
* Starter Code
* [Java Set Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html)
* [Java Map Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)


