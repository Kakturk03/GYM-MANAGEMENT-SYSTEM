package CMPE232;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ManageClassesPanelMenu extends JPanel {
    private ClassDAO classDAO;
    private EnrollmentDAO enrollmentDAO;
    private AssignmentDAO assignmentDAO;

    public ManageClassesPanelMenu(JPanel mainPanel, CardLayout cardLayout, ClassDAO classDAO, EnrollmentDAO enrollmentDAO, AssignmentDAO assignmentDAO) {
        this.classDAO = classDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.assignmentDAO = assignmentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addButton = new JButton("Add Class");
        JButton updateButton = new JButton("Update Class");
        JButton deleteButton = new JButton("Delete Class");
        JButton listButton = new JButton("List Classes");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> handleAddClass());
        updateButton.addActionListener(e -> handleUpdateClass());
        deleteButton.addActionListener(e -> handleDeleteClass());
        listButton.addActionListener(e -> handleListClasses());
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "StartPage"));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);

        gbc.gridy = 1;
        add(updateButton, gbc);

        gbc.gridy = 2;
        add(deleteButton, gbc);

        gbc.gridy = 3;
        add(listButton, gbc);

        gbc.gridy = 4;
        add(homeButton, gbc);
    }

    private void handleAddClass() {
        JTextField classIDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField scheduleField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField capacityField = new JTextField();

        Object[] message = {
            "Class ID:", classIDField,
            "Name:", nameField,
            "Description:", descriptionField,
            "Schedule (YYYY-MM-DD HH:MM:SS):", scheduleField,
            "Duration (minutes):", durationField,
            "Capacity:", capacityField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Class", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int classID = Integer.parseInt(classIDField.getText());
                String name = nameField.getText();
                String description = descriptionField.getText();
                Timestamp schedule = Timestamp.valueOf(scheduleField.getText());
                int duration = Integer.parseInt(durationField.getText());
                int capacity = Integer.parseInt(capacityField.getText());

                Class cls = new Class(classID, name, description, schedule, duration, capacity);
                classDAO.addClass(cls);
                JOptionPane.showMessageDialog(this, "Class added successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error adding class: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUpdateClass() {
        String input = JOptionPane.showInputDialog(this, "Enter Class ID:", "Update Class", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int classID = Integer.parseInt(input);
                Class cls = classDAO.getClass(classID);
                if (cls == null) {
                    JOptionPane.showMessageDialog(this, "Class not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField nameField = new JTextField(cls.getName());
                JTextField descriptionField = new JTextField(cls.getDescription());
                JTextField scheduleField = new JTextField(cls.getSchedule().toString());
                JTextField durationField = new JTextField(String.valueOf(cls.getDuration()));
                JTextField capacityField = new JTextField(String.valueOf(cls.getCapacity()));

                Object[] message = {
                    "Name:", nameField,
                    "Description:", descriptionField,
                    "Schedule (YYYY-MM-DD HH:MM:SS):", scheduleField,
                    "Duration (minutes):", durationField,
                    "Capacity:", capacityField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Update Class", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    cls.setName(nameField.getText());
                    cls.setDescription(descriptionField.getText());
                    cls.setSchedule(Timestamp.valueOf(scheduleField.getText()));
                    cls.setDuration(Integer.parseInt(durationField.getText()));
                    cls.setCapacity(Integer.parseInt(capacityField.getText()));

                    classDAO.updateClass(cls);
                    JOptionPane.showMessageDialog(this, "Class updated successfully.");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error updating class: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeleteClass() {
        String input = JOptionPane.showInputDialog(this, "Enter Class ID:", "Delete Class", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int classID = Integer.parseInt(input);
                classDAO.deleteClass(classID);
                JOptionPane.showMessageDialog(this, "Class deleted successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error deleting class: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleListClasses() {
        try {
            List<Class> classes = classDAO.getAllClasses();
            StringBuilder sb = new StringBuilder("Classes:\n");
            for (Class cls : classes) {
                sb.append(cls.getClassID()).append(": ").append(cls.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "List Classes", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing classes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

