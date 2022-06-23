import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DigitalClock {

    static void display(){
        JFrame clockFrame = new JFrame("The Current Time Is");
        clockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clockFrame.setPreferredSize(new Dimension(600, 200));

        JLabel currentTimeLabel = new JLabel("", SwingConstants.CENTER);
        currentTimeLabel.setPreferredSize(new Dimension(400, 100));
        currentTimeLabel.setFont(new Font("Arial", Font.BOLD,50));
        clockFrame.getContentPane().add(currentTimeLabel, BorderLayout.NORTH);

        String[] timeZonesChoice = {"UTC", "PST", "MST", "CST", "EST", "CEST"};
        JComboBox<String> jComboBox = new JComboBox<>(timeZonesChoice);
        jComboBox.setPreferredSize(new Dimension(5, 10));
        jComboBox.setSelectedIndex(0);
        clockFrame.getContentPane().add(jComboBox);

        JLabel uTCTimeLabel = new JLabel("", SwingConstants.CENTER);
        uTCTimeLabel.setPreferredSize(new Dimension(500, 50));
        uTCTimeLabel.setFont(new Font("Arial", Font.BOLD,50));
        clockFrame.getContentPane().add(uTCTimeLabel, BorderLayout.EAST);

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
        currentTimer.start();

        jComboBox.addActionListener(e -> {
            Timer uTCTimer = new Timer(delay, i -> {
                String timeZone = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                OffsetDateTime current;
            if (Objects.equals(timeZone, "PST")) {
                 current = OffsetDateTime.now(ZoneOffset.of("-08:00"));
            } else if (Objects.equals(timeZone,"MST")) {
                current = OffsetDateTime.now(ZoneOffset.of("-07:00"));
            } else if (Objects.equals(timeZone, "CST")) {
                current = OffsetDateTime.now(ZoneOffset.of("-06:00"));
            } else if (Objects.equals(timeZone, "EST")) {
                current = OffsetDateTime.now(ZoneOffset.of("-05:00"));
            } else if (Objects.equals(timeZone, "CEST")) {
                current = OffsetDateTime.now(ZoneOffset.of("+02:00"));
            } else {
                current = OffsetDateTime.now(ZoneOffset.UTC);
            }
            DateTimeFormatter formatted = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTimeZone = current.format(formatted);
            uTCTimeLabel.setText(formattedTimeZone);
            });
            uTCTimer.start();
            });
    }

    public static void main(String[] args){
        display();
    }
}
