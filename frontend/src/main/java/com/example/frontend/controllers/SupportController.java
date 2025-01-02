// package com.example.frontend.controllers;

// import com.example.frontend.services.SupportService;
// import javafx.fxml.FXML;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;

// public class SupportController {
//     @FXML
//     private ListView<String> ticketList;

//     @FXML
//     private TextField ticketSubject;

//     @FXML
//     private TextArea ticketDescription;

//     @FXML
//     private Label statusLabel;

//     private final SupportService supportService = new SupportService();

//     @FXML
//     private void initialize() {
//         loadTickets();
//     }

//     private void loadTickets() {
//         try {
//             ticketList.getItems().setAll(supportService.getAllTickets());
//         } catch (Exception e) {
//             statusLabel.setText("Failed to load tickets.");
//         }
//     }

//     @FXML
//     private void handleCreateTicket() {
//         String subject = ticketSubject.getText();
//         String description = ticketDescription.getText();

//         if (!subject.isEmpty() && !description.isEmpty()) {
//             supportService.createTicket(subject, description);
//             loadTickets();
//             statusLabel.setText("Ticket created.");
//         } else {
//             statusLabel.setText("Subject and description are required.");
//         }
//     }
// }
