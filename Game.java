import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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

/*
 * This game class represents the graphic user interface and its components.
 * It is composed of components such as panels, buttons and frames.
 * The event listeners listen to those components and do a variety of things such as
 * changing texts, panels, updating round numbers and number of wins.
 * The actual logics behind the GUI are implemented in other classes.
 * The focus of this class is mainly the GUI.
 */
public class Game {
    User user;
    Computer computer;
    ChoiceChecker[] checkers;
    int BORDER_SIZE;
    int ROUND_DELAY_MS = 2000;
    int COUNT_DOWN_TIME_MS = 300; // keep your chosen countdown step
    int winCount = 0;
    int computerWinCount = 0;
    int roundsPlayed = 0;
    final int TOTAL_ROUNDS = 3;

    JFrame frame;
    JPanel gameBackgroundPanel;
    JPanel initialPanel;
    JPanel randomModePanel;
    JPanel endlessTrialPanel;

    // center area uses its own CardLayout so announcement and result can coexist
    CardLayout centerCardLayout;
    JPanel centerPanel;
    String resultText;

    // shared UI
    JLabel titleLabel;
    JLabel announcement;
    JLabel winCountLabel;
    JLabel roundsLeftLabel;

    CardLayout cardLayout;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton randomModeButton;
    JButton endlessTrialButton;
    JButton backRandomButton;
    JButton backEndlessButton;
    JButton finishGameButton;

    // result UI (prebuilt in constructor)
    JPanel resultPanel;
    JLabel finalLabel;
    JLabel againLabel;
    JButton yesButton;
    JButton noButton;

    javax.swing.Timer countdownTimer;
    javax.swing.Timer nextRoundTimer;
    int countdownValue;

 // === Custom UI Helper ===
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setFocusPainted(false);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorder(new LineBorder(bg.darker(), 2, true));
        btn.setPreferredSize(new Dimension(150, 45));

        // hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
    }

    private JPanel createCardPanel(Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(new CompoundBorder(
                new LineBorder(Color.DARK_GRAY, 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }

    public Game(String username) {
        BORDER_SIZE = 20;
        checkers = new ChoiceChecker[]{new RockChecker(), new PaperChecker(), new ScissorsChecker()};
        user = new User(username);
        computer = new Computer("Computer");

        // ==== Frame setup ====
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 250));

        // ==== Layout setup ====
        cardLayout = new CardLayout();
        gameBackgroundPanel = new JPanel(cardLayout);
        gameBackgroundPanel.setBackground(new Color(245, 245, 245));
        gameBackgroundPanel.setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        // ==== Common UI components ====
        titleLabel = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(66, 133, 244));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setBorder(new EmptyBorder(20, 10, 20, 10));

        announcement = new JLabel("", SwingConstants.CENTER);
        announcement.setFont(new Font("Segoe UI", Font.BOLD, 26));
        announcement.setForeground(new Color(33, 33, 33));

        winCountLabel = new JLabel("Win Count: 0");
        roundsLeftLabel = new JLabel("Rounds Left: " + TOTAL_ROUNDS);
        Font statsFont = new Font("Segoe UI", Font.PLAIN, 16);
        winCountLabel.setFont(statsFont);
        roundsLeftLabel.setFont(statsFont);

        // Buttons
        Color btnColor = new Color(52, 152, 219);
        Color accentColor = new Color(231, 76, 60);
        Color neutralColor = new Color(46, 204, 113);

        rockButton = new JButton("🪨 Rock");
        paperButton = new JButton("📄 Paper");
        scissorsButton = new JButton("✂️ Scissors");
        randomModeButton = new JButton("🎲 Random Mode");
        endlessTrialButton = new JButton("♾️ Endless Trial");
        backRandomButton = new JButton("⬅ Back");
        backEndlessButton = new JButton("⬅ Back");
        finishGameButton = new JButton("🏁 Finish Game");
        yesButton = new JButton("✅ Yes");
        noButton = new JButton("❌ No");

        // Style all buttons
        styleButton(rockButton, btnColor, Color.WHITE);
        styleButton(paperButton, btnColor, Color.WHITE);
        styleButton(scissorsButton, btnColor, Color.WHITE);
        styleButton(randomModeButton, neutralColor, Color.WHITE);
        styleButton(endlessTrialButton, neutralColor, Color.WHITE);
        styleButton(backRandomButton, accentColor, Color.WHITE);
        styleButton(backEndlessButton, accentColor, Color.WHITE);
        styleButton(finishGameButton, accentColor, Color.WHITE);
        styleButton(yesButton, neutralColor, Color.WHITE);
        styleButton(noButton, accentColor, Color.WHITE);

        // ===== Initial Panel =====
        initialPanel = new JPanel(new GridBagLayout());
        initialPanel.setBackground(new Color(250, 250, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        initialPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        initialPanel.add(randomModeButton, gbc);

        gbc.gridy = 2;
        initialPanel.add(endlessTrialButton, gbc);

        // ===== Random Mode Panel =====
        randomModePanel = createCardPanel(new Color(255, 235, 238));

        JPanel topRandomPanel = new JPanel(new BorderLayout());
        topRandomPanel.setOpaque(false);

        JPanel statsRandomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsRandomPanel.setOpaque(false);
        statsRandomPanel.add(winCountLabel);
        statsRandomPanel.add(roundsLeftLabel);
        topRandomPanel.add(statsRandomPanel, BorderLayout.WEST);
        topRandomPanel.add(backRandomButton, BorderLayout.EAST);

        randomModePanel.add(topRandomPanel, BorderLayout.NORTH);

        centerCardLayout = new CardLayout();
        centerPanel = new JPanel(centerCardLayout);
        centerPanel.setOpaque(false);

        // announce card
        JPanel announceCard = new JPanel(new BorderLayout());
        announceCard.setOpaque(false);
        announceCard.add(announcement, BorderLayout.CENTER);
        centerPanel.add(announceCard, "announce");

        // result card
        resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setOpaque(false);
        GridBagConstraints gbcResult = new GridBagConstraints();
        gbcResult.gridx = 0;
        gbcResult.gridy = 0;

        finalLabel = new JLabel("", SwingConstants.CENTER);
        finalLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        finalLabel.setForeground(new Color(33, 33, 33));
        resultPanel.add(finalLabel, gbcResult);

        gbcResult.gridy = 1;
        gbcResult.insets = new Insets(10, 0, 10, 0);
        againLabel = new JLabel("Wanna play again?");
        againLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        resultPanel.add(againLabel, gbcResult);

        gbcResult.gridy = 2;
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
        resultPanel.add(buttonsPanel, gbcResult);

        centerPanel.add(resultPanel, "result");
        centerCardLayout.show(centerPanel, "announce");

        randomModePanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomRandomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        bottomRandomPanel.setOpaque(false);
        bottomRandomPanel.add(rockButton);
        bottomRandomPanel.add(paperButton);
        bottomRandomPanel.add(scissorsButton);
        randomModePanel.add(bottomRandomPanel, BorderLayout.SOUTH);

        disableChoices();

        // ===== Endless Trial Panel =====
        endlessTrialPanel = createCardPanel(new Color(255, 249, 196));

        JPanel topEndlessPanel = new JPanel(new BorderLayout());
        topEndlessPanel.setOpaque(false);

        JPanel statsEndlessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsEndlessPanel.setOpaque(false);
        statsEndlessPanel.add(new JLabel("Win Count: 0"));
        statsEndlessPanel.add(new JLabel("Rounds Left: 0"));
        statsEndlessPanel.add(new JLabel("Score: 0"));

        JPanel buttonsEndlessPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonsEndlessPanel.setOpaque(false);
        buttonsEndlessPanel.add(finishGameButton);
        buttonsEndlessPanel.add(backEndlessButton);

        topEndlessPanel.add(statsEndlessPanel, BorderLayout.WEST);
        topEndlessPanel.add(buttonsEndlessPanel, BorderLayout.EAST);
        endlessTrialPanel.add(topEndlessPanel, BorderLayout.NORTH);

        // ==== Add panels to CardLayout ====
        gameBackgroundPanel.add(initialPanel, "initial");
        gameBackgroundPanel.add(randomModePanel, "random");
        gameBackgroundPanel.add(endlessTrialPanel, "endless");

        frame.add(gameBackgroundPanel);
    }

    public void play() {
        // Only listeners and runtime logic — no UI layout here!
        randomModeButton.addActionListener(e -> enterRandomMode());
        endlessTrialButton.addActionListener(e -> {
            stopAllTimers();
            cardLayout.show(gameBackgroundPanel, "endless");
        });

        backRandomButton.addActionListener(e -> {
            stopAllTimers();
            cardLayout.show(gameBackgroundPanel, "initial");
        });

        backEndlessButton.addActionListener(e -> {
            stopAllTimers();
            cardLayout.show(gameBackgroundPanel, "initial");
        });
        finishGameButton.addActionListener(e -> {
            stopAllTimers();
            cardLayout.show(gameBackgroundPanel, "initial");
        });

        // player choice listeners (added once)
        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));

        // result panel choices
        yesButton.addActionListener(e -> {
            resultPanel.setVisible(false);
            centerCardLayout.show(centerPanel, "announce");
            enterRandomMode();
        });

        noButton.addActionListener(e -> {
            resultPanel.setVisible(false);
            centerCardLayout.show(centerPanel, "announce");
            cardLayout.show(gameBackgroundPanel, "initial");
        });

        frame.setVisible(true);
    }

    private void enterRandomMode() {
        stopAllTimers();
        winCount = 0;
        roundsPlayed = 0;
        winCountLabel.setText("Win Count: 0");
        roundsLeftLabel.setText("Rounds Left: " + TOTAL_ROUNDS);
        announcement.setText("");
        centerCardLayout.show(centerPanel, "announce");
        cardLayout.show(gameBackgroundPanel, "random");
        startCountdown();
    }

    private void startCountdown() {
        stopAllTimers();
        if (roundsPlayed >= TOTAL_ROUNDS) return;
        countdownValue = 3;
        announcement.setText("Choose your option in... " + countdownValue);
        disableChoices();

        countdownTimer = new javax.swing.Timer(COUNT_DOWN_TIME_MS, e -> {
            countdownValue--;
            if (countdownValue > 0) {
                announcement.setText("Choose your option in... " + countdownValue);
            } else {
                stopCountdownTimer();
                announcement.setText("GO!");
                enableChoices();
            }
        });
        countdownTimer.setInitialDelay(COUNT_DOWN_TIME_MS);
        countdownTimer.start();
    }

    private void playRound(String playerChoice) {
        if (countdownTimer != null && countdownTimer.isRunning()) return;

        disableChoices();
        stopCountdownTimer();
        stopNextRoundTimer();

        Choice computerChoice = computer.play();
        user.setChoice(playerChoice);
        Choice userChoice = user.play();

        Round round = new Round(checkers);
        
        boolean result = false;
        result = round.go(userChoice, computerChoice);

        if (result) {
            resultText = "You win!";
            winCount++;
        } else {
            computerWinCount++;
            resultText = "You Lose!";
        }

        if (userChoice.getName().equals(computerChoice.getName())) {
            resultText = "draw!";
        }

        winCountLabel.setText("Win Count: " + winCount);
        roundsPlayed++;
        roundsLeftLabel.setText("Rounds Left: " + (TOTAL_ROUNDS - roundsPlayed));

        announcement.setText("<html><div style='text-align:center; font-size:18px;'><b>" + resultText +
                "</b><br>You: " + userChoice.getName() + "<br>Computer: " + computerChoice.getName() + "</div></html>");

        nextRoundTimer = new javax.swing.Timer(ROUND_DELAY_MS, e -> {
            stopNextRoundTimer();
            centerCardLayout.show(centerPanel, "announce"); // show announcement

            if (roundsPlayed >= TOTAL_ROUNDS) {
                endGame(); // trigger endGame after showing announcement
            } else {
                startCountdown(); // start next round countdown
            }
        });
        nextRoundTimer.setRepeats(false);
        nextRoundTimer.start();
    }

    private void endGame() {
        stopAllTimers();
        disableChoices();

        String finalMessage = "";
        if (winCount > computerWinCount) {
            finalMessage = "You win!";
        } else if (winCount == computerWinCount) {
            finalMessage = "Draw!";
        } else {
            finalMessage = "You lose!";
        }
        finalLabel.setText(finalMessage);

        // switch to result card
        centerCardLayout.show(centerPanel, "result");
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
            Game game = new Game("Mario");
            game.play();
        });
    }
}
