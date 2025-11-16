import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class IdealBodyWeightCalculator extends JFrame {

    private JTextField txtName, txtHeight, txtWeight;
    private JComboBox<String> cmbGender;
    private JLabel lblIdeal, lblRange, lblStatus, lblTips;

    public IdealBodyWeightCalculator() {
        setTitle("Ideal Body Weight Calculator");
        setSize(450, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 5, 5));

        // --- UI COMPONENTS ---
        add(new JLabel("Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Gender:"));
        cmbGender = new JComboBox<>(new String[]{"Male", "Female"});
        add(cmbGender);

        add(new JLabel("Height (cm):"));
        txtHeight = new JTextField();
        add(txtHeight);

        add(new JLabel("Actual Weight (kg):"));
        txtWeight = new JTextField();
        add(txtWeight);

        JButton btnCalculate = new JButton("Calculate");
        add(btnCalculate);

        JButton btnSave = new JButton("Save to Database");
        add(btnSave);

        add(new JLabel("Ideal Weight:"));
        lblIdeal = new JLabel("-");
        add(lblIdeal);

        add(new JLabel("Healthy Range:"));
        lblRange = new JLabel("-");
        add(lblRange);

        add(new JLabel("Status:"));
        lblStatus = new JLabel("-");
        add(lblStatus);

        add(new JLabel("Health Tips:"));
        lblTips = new JLabel("-");
        add(lblTips);

        // --- ACTION LISTENERS ---
        btnCalculate.addActionListener(e -> calculateWeight());
        btnSave.addActionListener(e -> saveToDatabase());

        setVisible(true);
    }

    // -------------------------------------------------------------
    // CALCULATE IDEAL WEIGHT
    // -------------------------------------------------------------
    private void calculateWeight() {
        try {
            String gender = cmbGender.getSelectedItem().toString();
            double height = Double.parseDouble(txtHeight.getText());
            double weight = Double.parseDouble(txtWeight.getText());

            double baseHeight = 152.4; // 5 feet in cm
            double ideal;

            if (gender.equals("Male")) {
                ideal = 50 + 2.3 * ((height - baseHeight) / 2.54);
            } else {
                ideal = 45.5 + 2.3 * ((height - baseHeight) / 2.54);
            }

            double lower = ideal * 0.9;
            double upper = ideal * 1.1;

            lblIdeal.setText(String.format("%.2f kg", ideal));
            lblRange.setText(String.format("%.2f kg - %.2f kg", lower, upper));

            String status;
            if (weight < lower) status = "Underweight";
            else if (weight > upper) status = "Overweight";
            else status = "Healthy";

            lblStatus.setText(status);
            lblTips.setText(getHealthTips(status));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Enter correct numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // -------------------------------------------------------------
    // HEALTH TIPS
    // -------------------------------------------------------------
    private String getHealthTips(String status) {
        switch (status) {
            case "Underweight":
                return "Eat more proteins and healthy fats.";
            case "Healthy":
                return "Maintain your routine & stay active.";
            default:
                return "Reduce sugars & increase exercise.";
        }
    }

    // -------------------------------------------------------------
    // SAVE DATA TO MYSQL DATABASE
    // -------------------------------------------------------------
    private void saveToDatabase() {
        String url = "jdbc:mysql://localhost:3306/ideal_weight_db";
        String user = "root";
        String pass = "";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            String sql = "INSERT INTO records (name, gender, height, weight, ideal_weight, status) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, txtName.getText());
            pst.setString(2, cmbGender.getSelectedItem().toString());
            pst.setDouble(3, Double.parseDouble(txtHeight.getText()));
            pst.setDouble(4, Double.parseDouble(txtWeight.getText()));
            pst.setString(5, lblIdeal.getText());
            pst.setString(6, lblStatus.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Saved Successfully!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new IdealBodyWeightCalculator();
    }
}
