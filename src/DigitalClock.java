import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DigitalClock {
    static void display(){
        JFrame clockFrame = new JFrame("The Current Time Is");
        clockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clockFrame.setPreferredSize(new Dimension(400, 200));

        JLabel currentTimeLabel = new JLabel("", SwingConstants.CENTER);
        currentTimeLabel.setPreferredSize(new Dimension(400, 100));
        currentTimeLabel.setFont(new Font("Arial", Font.BOLD,50));
        clockFrame.getContentPane().add(currentTimeLabel, BorderLayout.NORTH);

        JLabel uTCTimeLabel = new JLabel("", SwingConstants.CENTER);
        uTCTimeLabel.setPreferredSize(new Dimension(400, 100));
        uTCTimeLabel.setFont(new Font("Arial", Font.BOLD,50));
        clockFrame.getContentPane().add(uTCTimeLabel, BorderLayout.SOUTH);

        clockFrame.setLocationRelativeTo(null);
        clockFrame.pack();
        clockFrame.setVisible(true);

        int delay = 100;
        Timer currentTimer = new Timer(delay, e -> {
            LocalDateTime current = LocalDateTime.now();
            DateTimeFormatter formatted = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = current.format(formatted);
            currentTimeLabel.setText("Local: " + formattedTime);
        });
        Timer uTCTimer = new Timer(delay, e -> {
            OffsetDateTime current = OffsetDateTime.now(ZoneOffset.UTC);
            DateTimeFormatter formatted = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedUTC = current.format(formatted);
            uTCTimeLabel.setText("UTC: " + formattedUTC);
        });
        currentTimer.start();
        uTCTimer.start();
    }

    public static void main(String[] args){
        display();
    }
}
