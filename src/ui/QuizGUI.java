package ui;

import service.QuizService;
import model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGUI {
    private JFrame frame;
    private JLabel label;
    private JLabel lblError;
    private JRadioButton rb1, rb2, rb3;
    private ButtonGroup bg;
    private JButton btnNext;
    private QuizService quizService;

    public QuizGUI() {
        quizService = new QuizService();

        frame = new JFrame("Java Mini Quiz Application");
        frame.setSize(520, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        frame.getContentPane().setBackground(new Color(245, 247, 250));

        label = new JLabel("", JLabel.CENTER);
        label.setBounds(30, 25, 440, 60);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(new Color(44, 62, 80));
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255));
        label.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        frame.add(label);

        rb1 = new JRadioButton();
        rb1.setBounds(40, 110, 420, 35);
        rb1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rb1.setBackground(new Color(245, 247, 250));
        rb1.setForeground(new Color(52, 73, 94));

        rb2 = new JRadioButton();
        rb2.setBounds(40, 155, 420, 35);
        rb2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rb2.setBackground(new Color(245, 247, 250));
        rb2.setForeground(new Color(52, 73, 94));

        rb3 = new JRadioButton();
        rb3.setBounds(40, 200, 420, 35);
        rb3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rb3.setBackground(new Color(245, 247, 250));
        rb3.setForeground(new Color(52, 73, 94));

        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);

        frame.add(rb1);
        frame.add(rb2);
        frame.add(rb3);

        lblError = new JLabel("", JLabel.CENTER);
        lblError.setBounds(40, 245, 420, 25);
        lblError.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblError.setForeground(new Color(231, 76, 60));
        frame.add(lblError);

        btnNext = new JButton("Next");
        btnNext.setBounds(180, 285, 140, 40);
        btnNext.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNext.setBackground(new Color(41, 128, 185));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusPainted(false);
        btnNext.setBorder(BorderFactory.createEmptyBorder());
        frame.add(btnNext);

        loadQuestionToUI();

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rb1.isSelected() && !rb2.isSelected() && !rb3.isSelected()) {
                    lblError.setText("Please select an option before proceeding!");
                    return;
                }
                
                lblError.setText("");

                int selected = 0;
                if (rb1.isSelected()) selected = 1;
                else if (rb2.isSelected()) selected = 2;
                else if (rb3.isSelected()) selected = 3;

                quizService.checkAnswer(selected);
                quizService.nextQuestion();

                if (!quizService.isFinished()) {
                    loadQuestionToUI();
                    bg.clearSelection();
                } else {
                    int score = quizService.getScore();
                    int total = quizService.getTotalQuestions();
                    String remark = "";
                    
                    if (score >= 8) {
                        remark = "Outstanding! Excellent knowledge.";
                    } else if (score >= 5) {
                        remark = "Good Job! Keep improving.";
                    } else {
                        remark = "Needs Improvement. Try again!";
                    }

                    // 1. Review Summary Popup Window
                    String reviewHtml = "<html><body style='font-family: sans-serif; font-size: 11px; padding: 10px;'>" +
                                         "<h2 style='color: #2c3e50;'>Quiz Results & Detailed Review</h2>" +
                                         "<b>Score: " + score + " / " + total + "</b><br>" +
                                         "<i>Remark: " + remark + "</i><br><br>" +
                                         "<b>=== Question-by-Question Review ===</b><br><br>" +
                                         quizService.getDetailedReviewHTML() +
                                         "</body></html>";

                    JLabel reviewLabel = new JLabel(reviewHtml);
                    JScrollPane scrollPane = new JScrollPane(reviewLabel);
                    scrollPane.setPreferredSize(new Dimension(440, 300));

                    JOptionPane.showMessageDialog(
                        frame, 
                        scrollPane, 
                        "Final Quiz Summary", 
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    // 2. Separate Yes/No Dialog for Restarting
                    int choice = JOptionPane.showConfirmDialog(
                        frame, 
                        "Do you want to restart the quiz?", 
                        "Restart Quiz", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        quizService.resetQuiz();
                        loadQuestionToUI();
                        bg.clearSelection();
                    } else {
                        System.exit(0);
                    }
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadQuestionToUI() {
        Question q = quizService.getCurrentQuestion();
        if (q != null) {
            label.setText("<html><div style='padding: 5px; text-align: center;'>" + q.getQuestionText() + "</div></html>");
            String[] options = q.getOptions();
            rb1.setText(options[0]);
            rb2.setText(options[1]);
            rb3.setText(options[2]);
        }
    }
}