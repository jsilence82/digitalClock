import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DigitalClock extends JFrame {

    public DigitalClock() {
        setTitle("The Time is...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 200);

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPanel);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{141, 141, 0};
        gbl_contentPane.rowHeights = new int[]{48, 46, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("Local Time", SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        contentPanel.add(lblNewLabel, gbc_lblNewLabel);


        JLabel currentTimeLabel = new JLabel("", SwingConstants.CENTER);
        currentTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        GridBagConstraints gbc_local = new GridBagConstraints();
        gbc_local.fill = GridBagConstraints.BOTH;
        gbc_local.insets = new Insets(0, 0, 5, 0);
        gbc_local.gridx = 1;
        gbc_local.gridy = 1;
        contentPanel.add(currentTimeLabel, gbc_local);


        String[] timeZonesChoice = TimeZone.getAvailableIDs();
        JComboBox<String> jComboBox = new JComboBox<>(timeZonesChoice);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox.fill = GridBagConstraints.BOTH;
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 2;
        contentPanel.add(jComboBox, gbc_comboBox);


        JLabel uTCTimeLabel = new JLabel("", SwingConstants.CENTER);
        uTCTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        GridBagConstraints gbc_utcTimelablel = new GridBagConstraints();
        gbc_utcTimelablel.gridx = 1;
        gbc_utcTimelablel.gridy = 2;
        contentPanel.add(uTCTimeLabel, gbc_utcTimelablel);

        int delay = 100;
        Timer currentTimer = new Timer(delay, e -> {
            LocalDateTime current = LocalDateTime.now();
            DateTimeFormatter formatted = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = current.format(formatted);
            currentTimeLabel.setText(formattedTime);
        });
        currentTimer.start();

        jComboBox.addActionListener(e -> {
            Timer uTCTimer = new Timer(delay, i -> {
                String timeZone = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                TimeZone zone = TimeZone.getTimeZone(timeZone);
                int offset = zone.getRawOffset() / 1000;
                int hour = offset / 3600;
                int minutes = (offset % 3600) / 60;
                OffsetDateTime current = OffsetDateTime.now(ZoneOffset.ofHoursMinutes(hour, minutes));
                DateTimeFormatter formatted = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTimeZone = current.format(formatted);
                uTCTimeLabel.setText(formattedTimeZone);
            });
            uTCTimer.start();
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DigitalClock frame = new DigitalClock();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
