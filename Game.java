import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * goal is to use
 * 1. inheritance
 * 2. polymorphism
 * 3. abstract classes / interfaces / subclasses
 * 4. objected oriented programming
 * 5. division of methods
 * 6. lisvoke substitution
 * 
 * idea:
 * 1. game play option with abstract class thus inheritance: rock, paper, scissors
 * 2. game rule checker with interface: rock checker, paper checker, scissor checker
 * 3. decides who wins, by using the rule checkers, thus applying polymorphism and lisvoke substitution
 * 
 * how game will be done
 * 1. inital screen: choose game mode, endless trial or random
 * 2. random mode:
 * 2.1 three buttons: rock, paper, scissor
 * 2.2 win/lose count
 * 2.3 celebration or boo, depending on if you lose or win
 * 
 * 3. endless trial mode:
 * 3.1 four buttons: rock, paper, scissor, stop
 * 3.2 win/lose count
 * 3.3 application of the algorithm
        what steps i took to learn stuff for github
        algorithm.       
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game {
    int BORDER_SIZE;
    int winCount = 0;
    int roundsPlayed = 0;
    final int TOTAL_ROUNDS = 3;

    JFrame frame;
    JPanel gameBackgroundPanel;
    JPanel initialPanel;
    JPanel randomModePanel;
    JPanel endlessTrialPanel;
    JLabel titleLabel;
    JLabel announcement;
    JLabel winCountLabel;

    CardLayout cardLayout;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;

    javax.swing.Timer countdownTimer;
    javax.swing.Timer nextRoundTimer;
    int countdownValue;

    public Game() {
        BORDER_SIZE = 20;
    }

    public void play() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        gameBackgroundPanel = new JPanel(cardLayout);
        gameBackgroundPanel.setBackground(Color.WHITE);
        gameBackgroundPanel.setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        // ===== Initial Panel =====
        initialPanel = new JPanel(new GridBagLayout());
        initialPanel.setBackground(Color.LIGHT_GRAY);

        titleLabel = new JLabel("My Favourite Thing");
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        initialPanel.add(titleLabel, gbc);

        JButton randomModeButton = new JButton("Random Mode");
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        initialPanel.add(randomModeButton, gbc);

        JButton endlessTrialButton = new JButton("Endless Trial Mode");
        gbc.gridy = 2;
        initialPanel.add(endlessTrialButton, gbc);

        // ===== Random Mode Panel =====
        randomModePanel = new JPanel(new BorderLayout());
        randomModePanel.setBackground(Color.PINK);

        JPanel topRandomPanel = new JPanel(new BorderLayout());
        topRandomPanel.setOpaque(false);

        JPanel statsRandomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsRandomPanel.setOpaque(false);
        winCountLabel = new JLabel("Win Count: 0");
        JLabel roundsLeftLabel = new JLabel("Rounds Left: " + TOTAL_ROUNDS);
        JLabel scoreLabel = new JLabel("Score: —");
        statsRandomPanel.add(winCountLabel);
        statsRandomPanel.add(roundsLeftLabel);
        statsRandomPanel.add(scoreLabel);

        topRandomPanel.add(statsRandomPanel, BorderLayout.WEST);

        JButton backRandomButton = new JButton("Back");
        topRandomPanel.add(backRandomButton, BorderLayout.EAST);

        randomModePanel.add(topRandomPanel, BorderLayout.NORTH);

        // Center announcement
        announcement = new JLabel("", SwingConstants.CENTER);
        announcement.setFont(new Font("Arial", Font.BOLD, 28));
        randomModePanel.add(announcement, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomRandomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomRandomPanel.setOpaque(false);
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        bottomRandomPanel.add(rockButton);
        bottomRandomPanel.add(paperButton);
        bottomRandomPanel.add(scissorsButton);
        randomModePanel.add(bottomRandomPanel, BorderLayout.SOUTH);

        disableChoices();

        // ===== Endless Trial Panel (unchanged visually) =====
        endlessTrialPanel = new JPanel(new BorderLayout());
        endlessTrialPanel.setBackground(Color.ORANGE);

        JPanel topEndlessPanel = new JPanel(new BorderLayout());
        topEndlessPanel.setOpaque(false);
        JPanel statsEndlessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsEndlessPanel.setOpaque(false);
        statsEndlessPanel.add(new JLabel("Win Count: 0"));
        statsEndlessPanel.add(new JLabel("Rounds Left: 0"));
        statsEndlessPanel.add(new JLabel("Score: 0"));
        topEndlessPanel.add(statsEndlessPanel, BorderLayout.WEST);
        JPanel buttonsEndlessPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonsEndlessPanel.setOpaque(false);
        buttonsEndlessPanel.add(new JButton("Finish Game"));
        buttonsEndlessPanel.add(new JButton("Back"));
        topEndlessPanel.add(buttonsEndlessPanel, BorderLayout.EAST);
        endlessTrialPanel.add(topEndlessPanel, BorderLayout.NORTH);

        // Add panels
        gameBackgroundPanel.add(initialPanel, "initial");
        gameBackgroundPanel.add(randomModePanel, "random");
        gameBackgroundPanel.add(endlessTrialPanel, "endless");

        // Listeners
        randomModeButton.addActionListener(e -> enterRandomMode());
        endlessTrialButton.addActionListener(e -> cardLayout.show(gameBackgroundPanel, "endless"));

        backRandomButton.addActionListener(e -> {
            stopAllTimers();
            cardLayout.show(gameBackgroundPanel, "initial");
        });

        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));

        frame.add(gameBackgroundPanel);
        frame.setVisible(true);
    }

    private void enterRandomMode() {
        stopAllTimers();
        winCount = 0;
        roundsPlayed = 0;
        winCountLabel.setText("Win Count: 0");
        announcement.setText("");
        cardLayout.show(gameBackgroundPanel, "random");
        startCountdown();
    }

    private void startCountdown() {
        stopAllTimers();
        if (roundsPlayed >= 3) return; // safety check
        countdownValue = 3;
        announcement.setText("Choose your option in... " + countdownValue);
        disableChoices();

        countdownTimer = new javax.swing.Timer(1000, e -> {
            countdownValue--;
            if (countdownValue > 0) {
                announcement.setText("Choose your option in... " + countdownValue);
            } else {
                stopCountdownTimer();
                announcement.setText("GO!");
                enableChoices();
            }
        });
        countdownTimer.setInitialDelay(1000);
        countdownTimer.start();
    }

    private void playRound(String playerChoice) {
        if (countdownTimer != null && countdownTimer.isRunning()) return;

        disableChoices();
        stopCountdownTimer();
        stopNextRoundTimer();

        String[] options = {"Rock", "Paper", "Scissors"};
        String computerChoice = options[new Random().nextInt(3)];
        String result;

        if (playerChoice.equals(computerChoice)) {
            result = "It's a Draw!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = "You Win!";
            winCount++;
        } else {
            result = "You Lose!";
        }

        winCountLabel.setText("Win Count: " + winCount);
        roundsPlayed++;

        announcement.setText("<html><div style='text-align:center; font-size:18px;'><b>" + result +
                "</b><br>You: " + playerChoice + "<br>Computer: " + computerChoice + "</div></html>");

        if (roundsPlayed >= TOTAL_ROUNDS) {
            endGame();
        } else {
            nextRoundTimer = new javax.swing.Timer(2000, e -> {
                stopNextRoundTimer();
                startCountdown();
            });
            nextRoundTimer.setRepeats(false);
            nextRoundTimer.start();
        }
    }

    private void endGame() {
        stopAllTimers();
        disableChoices();

        String finalMessage = (winCount >= 2) ? "You Win!" : "You Lose!";

        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel finalLabel = new JLabel(finalMessage, SwingConstants.CENTER);
        finalLabel.setFont(new Font("Arial", Font.BOLD, 32));
        resultPanel.add(finalLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        JLabel againLabel = new JLabel("Wanna play again?");
        againLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        resultPanel.add(againLabel, gbc);

        gbc.gridy = 2;
        JPanel buttonsPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
        resultPanel.add(buttonsPanel, gbc);

        randomModePanel.remove(announcement);
        randomModePanel.add(resultPanel, BorderLayout.CENTER);
        randomModePanel.revalidate();
        randomModePanel.repaint();

        yesButton.addActionListener(e -> {
            randomModePanel.remove(resultPanel);
            randomModePanel.add(announcement, BorderLayout.CENTER);
            randomModePanel.revalidate();
            randomModePanel.repaint();
            enterRandomMode();
        });

        noButton.addActionListener(e -> {
            randomModePanel.remove(resultPanel);
            randomModePanel.add(announcement, BorderLayout.CENTER);
            randomModePanel.revalidate();
            randomModePanel.repaint();
            cardLayout.show(gameBackgroundPanel, "initial");
        });
    }

    private void stopAllTimers() {
        stopCountdownTimer();
        stopNextRoundTimer();
    }

    private void stopCountdownTimer() {
        if (countdownTimer != null && countdownTimer.isRunning()) countdownTimer.stop();
        countdownTimer = null;
    }

    private void stopNextRoundTimer() {
        if (nextRoundTimer != null && nextRoundTimer.isRunning()) nextRoundTimer.stop();
        nextRoundTimer = null;
    }

    private void disableChoices() {
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);
    }

    private void enableChoices() {
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.play();
        });
    }
}