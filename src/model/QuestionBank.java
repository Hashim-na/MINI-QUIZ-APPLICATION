package model;

public class QuestionBank {
    public static Question[] getQuestions() {
        return new Question[] {
            new Question("1. Which of the following is not a Java feature?", 
                         new String[]{"1. Object-oriented", "2. Use of pointers", "3. Portable"}, 2),
            new Question("2. Which component is used to compile, debug and execute?", 
                         new String[]{"1. JRE", "2. JIT", "3. JDK"}, 3),
            new Question("3. What is the extension of Java byte code?", 
                         new String[]{"1. .java", "2. .class", "3. .txt"}, 2),
            new Question("4. Which of these is a reserved keyword in Java?", 
                         new String[]{"1. class", "2. object", "3. method"}, 1),
            new Question("5. Which method must be defined in every Java application class?", 
                         new String[]{"1. start()", "2. main()", "3. run()"}, 2),
            new Question("6. Which package contains the Java Swing library?", 
                         new String[]{"1. java.io", "2. javax.swing", "3. java.net"}, 2),
            new Question("7. What is the size of an int data type in Java?", 
                         new String[]{"1. 2 bytes", "2. 4 bytes", "3. 8 bytes"}, 2),
            new Question("8. Which concept means wrapping data and methods into a single unit?", 
                         new String[]{"1. Inheritance", "2. Polymorphism", "3. Encapsulation"}, 3),
            new Question("9. Which of these access specifiers provides the widest accessibility?", 
                         new String[]{"1. private", "2. protected", "3. public"}, 3),
            new Question("10. What is the default value of a boolean variable in Java?", 
                         new String[]{"1. true", "2. false", "3. null"}, 2)
        };
    }
}