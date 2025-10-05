import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game {
    int BORDER_SIZE;

    JFrame frame;
    JPanel gameBackgroundPanel;
    JPanel initialPanel;
    JPanel randomModePanel;
    JPanel endlessTrialPanel;

    JLabel titleLabel;

    CardLayout cardLayout;

    public Game() {
        BORDER_SIZE = 20;
    }

    public void play() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setLocationRelativeTo(null);

        // Background panel with CardLayout
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

        // Top panel: stats left, back right
        JPanel topRandomPanel = new JPanel(new BorderLayout());
        topRandomPanel.setOpaque(false);

        JPanel statsRandomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsRandomPanel.setOpaque(false);
        JLabel winCountLabel = new JLabel("Win Count: 0");
        JLabel roundsLeftLabel = new JLabel("Rounds Left: 0");
        JLabel scoreLabel = new JLabel("Score: 0");
        statsRandomPanel.add(winCountLabel);
        statsRandomPanel.add(roundsLeftLabel);
        statsRandomPanel.add(scoreLabel);

        topRandomPanel.add(statsRandomPanel, BorderLayout.WEST);

        JButton backRandomButton = new JButton("Back");
        topRandomPanel.add(backRandomButton, BorderLayout.EAST);

        randomModePanel.add(topRandomPanel, BorderLayout.NORTH);

        // Bottom panel: Rock / Paper / Scissors
        JPanel bottomRandomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomRandomPanel.setOpaque(false);
        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        bottomRandomPanel.add(rockButton);
        bottomRandomPanel.add(paperButton);
        bottomRandomPanel.add(scissorsButton);

        randomModePanel.add(bottomRandomPanel, BorderLayout.SOUTH);

        // ===== Endless Trial Panel =====
        endlessTrialPanel = new JPanel(new BorderLayout());
        endlessTrialPanel.setBackground(Color.ORANGE);

        // Top panel: stats left, Finish Game + Back right
        JPanel topEndlessPanel = new JPanel(new BorderLayout());
        topEndlessPanel.setOpaque(false);

        // Stats labels on the left
        JPanel statsEndlessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsEndlessPanel.setOpaque(false);
        JLabel winCountLabelEndless = new JLabel("Win Count: 0");
        JLabel roundsLeftLabelEndless = new JLabel("Rounds Left: 0");
        JLabel scoreLabelEndless = new JLabel("Score: 0");
        statsEndlessPanel.add(winCountLabelEndless);
        statsEndlessPanel.add(roundsLeftLabelEndless);
        statsEndlessPanel.add(scoreLabelEndless);
        topEndlessPanel.add(statsEndlessPanel, BorderLayout.WEST);

        // Buttons on the right
        JPanel buttonsEndlessPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonsEndlessPanel.setOpaque(false);
        JButton finishButton = new JButton("Finish Game");
        JButton backEndlessButton = new JButton("Back");
        buttonsEndlessPanel.add(finishButton);
        buttonsEndlessPanel.add(backEndlessButton);

        topEndlessPanel.add(buttonsEndlessPanel, BorderLayout.EAST);

        endlessTrialPanel.add(topEndlessPanel, BorderLayout.NORTH);

        // Bottom panel: Rock / Paper / Scissors
        JPanel bottomEndlessPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomEndlessPanel.setOpaque(false);
        JButton rockButtonEndless = new JButton("Rock");
        JButton paperButtonEndless = new JButton("Paper");
        JButton scissorsButtonEndless = new JButton("Scissors");
        bottomEndlessPanel.add(rockButtonEndless);
        bottomEndlessPanel.add(paperButtonEndless);
        bottomEndlessPanel.add(scissorsButtonEndless);

        endlessTrialPanel.add(bottomEndlessPanel, BorderLayout.SOUTH);

        // Add panels to the CardLayout
        gameBackgroundPanel.add(initialPanel, "initial");
        gameBackgroundPanel.add(randomModePanel, "random");
        gameBackgroundPanel.add(endlessTrialPanel, "endless");

        // Action listeners for switching panels
        randomModeButton.addActionListener(e -> cardLayout.show(gameBackgroundPanel, "random"));
        endlessTrialButton.addActionListener(e -> cardLayout.show(gameBackgroundPanel, "endless"));
        backRandomButton.addActionListener(e -> cardLayout.show(gameBackgroundPanel, "initial"));
        backEndlessButton.addActionListener(e -> cardLayout.show(gameBackgroundPanel, "initial"));

        // Add gameBackgroundPanel to frame
        frame.add(gameBackgroundPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}