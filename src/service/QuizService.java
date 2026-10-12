package service;

import model.Question;
import model.QuestionBank;

public class QuizService {
    private Question[] questions;
    private int currentQuestionIndex;
    private int score;
    private int[] userAnswers;

    public QuizService() {
        questions = QuestionBank.getQuestions(); // Fetching from QuestionBank
        userAnswers = new int[questions.length];
        resetQuiz();
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.length) {
            return questions[currentQuestionIndex];
        }
        return null;
    }

    public boolean checkAnswer(int selectedOption) {
        userAnswers[currentQuestionIndex] = selectedOption;
        Question current = getCurrentQuestion();
        if (current != null && current.getCorrectAnswer() == selectedOption) {
            score++;
            return true;
        }
        return false;
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public boolean isFinished() {
        return currentQuestionIndex >= questions.length;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.length;
    }

    public void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = 0;
        }
    }

    public String getDetailedReviewHTML() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            int userAns = userAnswers[i];
            int correctAns = q.getCorrectAnswer();
            String[] opts = q.getOptions();

            sb.append("<b>").append(q.getQuestionText()).append("</b><br>");
            
            for (int j = 0; j < opts.length; j++) {
                int optionNum = j + 1;
                String optionText = opts[j];
                
                if (optionNum == correctAns) {
                    sb.append("&nbsp;&nbsp;&#10004; <font color='green'><b>").append(optionText).append(" (Correct)</b></font><br>");
                } else if (optionNum == userAns && userAns != correctAns) {
                    sb.append("&nbsp;&nbsp;&#10008; <font color='red'><s>").append(optionText).append("</s> (Your Answer)</font><br>");
                } else {
                    sb.append("&nbsp;&nbsp;&bull; ").append(optionText).append("<br>");
                }
            }
            sb.append("<br>");
        }
        return sb.toString();
    }
}