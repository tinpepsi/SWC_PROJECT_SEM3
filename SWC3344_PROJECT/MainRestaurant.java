/* 
 * STUDENT NAME: NUR IZZAH BINTI BURHANUDDIN  
 * STUDENT NAME: NUR ATHIRAH BINTI HILALLUDDIN 
 * STUDENT ID: AM2307013907
 * STUDENT ID: AM2307013911
 * LECTURER NAME: MADAM FARAH FARZANA BINTI ABDUL AZIZ
 * PROGRAM DESCRIPTION: 4 FINGERS QUEUE SYSTE
 */

//import package
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Stack;

public class MainRestaurant extends JFrame 
{ //start of class
    // Declaration of a queue to hold CustomerList objects
    private Queue<CustomerList> qCustomer;
    private Queue<CustomerList> qCounter1;
    private Queue<CustomerList> qCounter2;
    private Queue<CustomerList> qCounter3;
    private Queue<CustomerList> Receipt1;
    private Queue<CustomerList> Receipt2;
    private Queue<CustomerList> Receipt3;

    // Declaration of a stack to hold CustomerList objects
    private Stack<CustomerList> completeStack;

    // Declaration of a linkedList to hold CustomerList objects
    private LinkedList<CustomerList> custList;

    //declaration of JButton
    private JButton QCButton, P1_Button, R1_Button, P2_Button, R2_Button, P3_Button, R3_Button;
    
    //for disable queue button 
    private boolean counter1Paid = false, counter2Paid = false, counter3Paid = false ;

    public MainRestaurant() {
        // Initialize queues, linked list, stack
        qCustomer = new LinkedList<>();
        qCounter1 = new LinkedList<>();
        qCounter2 = new LinkedList<>();
        qCounter3 = new LinkedList<>();
        Receipt1 = new LinkedList<>();
        Receipt2 = new LinkedList<>();
        Receipt3 = new LinkedList<>();
        completeStack = new Stack<>();
        custList = new LinkedList<>();

        try {
            // Create a BufferedReader to read from the file CustomerInformation amd OrderInformation txt
            BufferedReader in = new BufferedReader(new FileReader("CustomerInformation.txt"));
            BufferedReader input = new BufferedReader(new FileReader("OrderInformation.txt"));

            // String variable to store data read from the file
            String indataCustomer;
            String indataOrder;

            while ((indataCustomer = in.readLine()) != null && (indataOrder = input.readLine()) != null) {
                // Read customer info
                StringTokenizer stc = new StringTokenizer(indataCustomer, ";");
                String custId = stc.nextToken().trim();
                String custName = stc.nextToken().trim();
                int tableNumber = Integer.parseInt(stc.nextToken().trim());

                // Read order info
                StringTokenizer sto = new StringTokenizer(indataOrder, ";");
                String orderId = sto.nextToken().trim();
                String itemName = sto.nextToken().trim();
                double itemPrice = Double.parseDouble(sto.nextToken().trim());
                int quantity = Integer.parseInt(sto.nextToken().trim());
                String orderTime = sto.nextToken().trim();

                // Create objects from customerInfo and orderInfo class
                CustomerInformation CI = new CustomerInformation(custId, custName, tableNumber);
                OrderInformation OI = new OrderInformation(orderId, itemName, itemPrice, quantity, orderTime);

                //Create CustomerList object and add to linked list
                CustomerList customerList = new CustomerList(CI, OI);
                custList.add(customerList);
            }
            in.close();
            input.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        setTitle("3 FINGERS!"); //set title for the system
        setSize(1000, 800); //set size of the frame 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom color for the background
        Color customColorLeft = new Color(200, 232, 224);

        //////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////MAIN PANEL/////////////////////////////////////////////////
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        add(mainPanel);// Add mainPanel to the frame
        /////////////////////////////////////MAIN PANEL/////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////NORTH PANEL/////////////////////////////////////////////////
        // NORTH Panel with image and customer count
        JPanel northPanel = new JPanel();
        northPanel.setBackground(customColorLeft);// Set the background color 
        northPanel.setLayout(new BorderLayout());

        // Image
        ImageIcon image_R1 = new ImageIcon("3_FINGERS.png");
        JLabel imageLabel = new JLabel(image_R1);
        northPanel.add(imageLabel, BorderLayout.NORTH);

        // Customer count label
        JLabel countCustomer = new JLabel(); 
        countCustomer.setText("Total Customers: 0");
        countCustomer.setPreferredSize(new Dimension(250, 30));
        countCustomer.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(countCustomer, BorderLayout.SOUTH);

        // Add northPanel to mainPanel's NORTH
        mainPanel.add(northPanel, BorderLayout.NORTH);
        /////////////////////////////////////NORTH PANEL/////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////CENTER PANEL/////////////////////////////////////////////////
        //CENTER panel show data, add customer, queue customer and counter 1,2,3
        JPanel centerPanel_N = new JPanel();
        centerPanel_N.setLayout(new BorderLayout());

        //north panel for show data, add customer, queue customer
        JPanel N_Panel = new JPanel();
        N_Panel.setBackground(customColorLeft);// Set the background color 

        /////////////////////////////////////BUTTON PANEL/////////////////////////////////////////////////
        JButton DButton = new JButton("DELETE CUSTOMER");
        DButton.setPreferredSize(new Dimension(200, 30));
        N_Panel.add(DButton);
        JButton SDButton = new JButton("SHOW DATA");
        SDButton.setPreferredSize(new Dimension(200, 30));
        N_Panel.add(SDButton);
        JButton ACButton = new JButton("ADD CUSTOMER");
        ACButton.setPreferredSize(new Dimension(200, 30));
        N_Panel.add(ACButton);
        QCButton = new JButton("QUEUE CUSTOMER");
        QCButton.setPreferredSize(new Dimension(200, 30));
        N_Panel.add(QCButton);
        centerPanel_N.add(N_Panel, BorderLayout.NORTH);

        /////////////////////////////////////BUTTON PANEL/////////////////////////////////////////////////
        /////////////////////////////////////COUNTER PANEL/////////////////////////////////////////////////
        //center panel for counter 1,2,3
        JPanel centerPanel_C = new JPanel();
        centerPanel_C.setLayout(new GridLayout(1, 3));

        //////////////////////////COUNTER 1/////////////////////////////////
        JPanel contentPanel_C1 = new JPanel();
        contentPanel_C1.setLayout(new BoxLayout(contentPanel_C1, BoxLayout.Y_AXIS)); // Use BoxLayout for contentPanel_C1
        JScrollPane scrollPane_C1 = new JScrollPane(contentPanel_C1);
        scrollPane_C1.setBorder(BorderFactory.createTitledBorder("COUNTER 1"));
        scrollPane_C1.setBackground(customColorLeft);// Set the background color 
        scrollPane_C1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_C1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //////////////////////////COUNTER 1/////////////////////////////////

        //////////////////////////COUNTER 2/////////////////////////////////
        JPanel contentPanel_C2 = new JPanel();
        contentPanel_C2.setLayout(new BoxLayout(contentPanel_C2, BoxLayout.Y_AXIS)); // Use BoxLayout for contentPanel_C2
        JScrollPane scrollPane_C2 = new JScrollPane(contentPanel_C2);
        scrollPane_C2.setBorder(BorderFactory.createTitledBorder("COUNTER 2"));
        scrollPane_C2.setBackground(customColorLeft);// Set the background color 
        scrollPane_C2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_C2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //////////////////////////COUNTER 2/////////////////////////////////

        //////////////////////////COUNTER 3/////////////////////////////////
        JPanel contentPanel_C3 = new JPanel();
        contentPanel_C3.setLayout(new BoxLayout(contentPanel_C3, BoxLayout.Y_AXIS)); // Use BoxLayout for contentPanel_C3
        JScrollPane scrollPane_C3 = new JScrollPane(contentPanel_C3);
        scrollPane_C3.setBorder(BorderFactory.createTitledBorder("COUNTER 3"));
        scrollPane_C3.setBackground(customColorLeft);// Set the background color 
        scrollPane_C3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_C3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //////////////////////////COUNTER 3/////////////////////////////////
        //adding 3 content panel on the center panel
        centerPanel_C.add(scrollPane_C1);
        centerPanel_C.add(scrollPane_C2);
        centerPanel_C.add(scrollPane_C3);
        centerPanel_N.add (centerPanel_C, BorderLayout.CENTER);
        /////////////////////////////////////COUNTER PANEL/////////////////////////////////////////////////

        /////////////////////////////////////BUTTON PANEL/////////////////////////////////////////////////
        JPanel southPanel_S = new JPanel();
        southPanel_S.setLayout(new GridLayout(1, 6));

        P1_Button = new JButton("PAYMENT");
        P1_Button.setPreferredSize(new Dimension(250, 30));
        southPanel_S.add(P1_Button);
        R1_Button = new JButton("RECEIPT");
        R1_Button.setPreferredSize(new Dimension(250, 30));
        southPanel_S.add(R1_Button);
        P2_Button = new JButton("PAYMENT");
        P2_Button.setPreferredSize(new Dimension(250, 30));
        southPanel_S.add(P2_Button);
        R2_Button = new JButton("RECEIPT");
        R2_Button.setPreferredSize(new Dimension(250, 30));
        southPanel_S.add(R2_Button);
        P3_Button = new JButton("PAYMENT");
        P3_Button.setPreferredSize(new Dimension(250, 30));
        southPanel_S.add(P3_Button);
        R3_Button = new JButton("RECEIPT");
        R3_Button.setPreferredSize(new Dimension(250, 30));
        southPanel_S.add(R3_Button);
        centerPanel_N.add(southPanel_S, BorderLayout.SOUTH);

        /////////////////////////////////////BUTTON PANEL/////////////////////////////////////////////////
        mainPanel.add(centerPanel_N, BorderLayout.CENTER);

        /////////////////////////////////////CENTER PANEL/////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////SOUTH PANEL/////////////////////////////////////////////////
        // south Panel for record button
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        JPanel R_panel = new JPanel();
        R_panel.setBackground(customColorLeft);// Set the background color 

        //record button 
        JButton record_button = new JButton("RECORD");
        record_button.setPreferredSize(new Dimension(600, 30));
        R_panel.add(record_button);
        southPanel.add(R_panel);

        mainPanel.add(southPanel, BorderLayout.SOUTH);
        /////////////////////////////////////SOUTH PANEL/////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////ACTION LISTENER///////////////
        // ActionListener for ACButton (add customer button)
        ACButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //initialization
                    boolean dataAdded = false;
                    int count = 0;

                    // Iterate through the list of customers
                    for (CustomerList customerList : custList) {
                        if (qCustomer.contains(customerList)) {
                            dataAdded = true;
                        } else {
                            // Enqueue customerList into qCustomer
                            qCustomer.add(customerList);
                            // Increment the counter by 1 for each customer added
                            count++;
                        }
                    }

                    if (dataAdded) {
                        // If data has already been added, show "data already added" message
                        JOptionPane.showMessageDialog(null, "Data already added");
                    } else {
                        // Show the count message only if dataAdded is false
                        JOptionPane.showMessageDialog(null, count + " customers added successfully");

                        // Update countCustomer with the quantity of data added
                        countCustomer.setText("Total Customers: " + qCustomer.size());
                    }
                }
            });

        // ActionListener for SDButton (show data button)
        SDButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (qCustomer.isEmpty()) {
                        // Show message if there is no data
                        JOptionPane.showMessageDialog(null, "There Is No Data", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Create a StringBuilder to accumulate all data
                        StringBuilder sb = new StringBuilder();
                        for (CustomerList customerList : qCustomer) {
                            CustomerInformation custInfo = customerList.getCustomerInfo();
                            OrderInformation orderInfo = customerList.getOrderInfo();

                            sb.append("===========================================\n")
                            .append(custInfo.toString())
                            .append(orderInfo.toString())
                            .append("\n\n");
                        }

                        // Create a JTextArea with the accumulated data
                        JTextArea textArea = new JTextArea(sb.toString());
                        textArea.setEditable(false); // Make it non-editable

                        // Wrap the text area in a scroll pane
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        scrollPane.setPreferredSize(new Dimension(400, 400)); // Set preferred size for the scroll pane

                        // Show the scrollable text area in a JOptionPane
                        JOptionPane.showMessageDialog(null, scrollPane, "CUSTOMER DATA", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

        // ActionListener for QCButton (queue customer button)
        QCButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Initialization
                    int counter1Count = 0;
                    int counter2Count = 0;
                    int counter3Count = 0;

                    // Clear existing displays in contentPanel_C1, contentPanel_C2, contentPanel_C3
                    contentPanel_C1.removeAll();
                    contentPanel_C2.removeAll();
                    contentPanel_C3.removeAll();

                    // Temporary list to hold customers to be removed from qCustomer
                    List<CustomerList> toRemove = new ArrayList<>();

                    // Check if there are customers in qCustomer
                    if (qCustomer.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Customer", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return; // Exit the method if no data is present
                    }

                    // Iterate through the list of customers
                    for (CustomerList customerList : qCustomer) {
                        OrderInformation orderInfo = customerList.getOrderInfo();
                        int quantity = orderInfo.getQuantity();

                        // Check to see if the quantity is less than 5 && can only hold 5 customers per counter
                        if (quantity <= 5 && counter1Count < 5) {
                            // Transfer customer to Counter 1
                            qCounter1.add(customerList);
                            counter1Count++;

                            // Create JTextArea to display customer info
                            String displayText = "============================================\n" +
                                "\n" +
                                customerList.getCustomerInfo().toString() +
                                "Order Name: " + customerList.getItem_Name() + "\n" +
                                "Total Amount: " + customerList.getFormattedTotalAmount() + "\n";
                            JTextArea textArea = new JTextArea(displayText);
                            textArea.setEditable(false);
                            textArea.setBorder(BorderFactory.createEmptyBorder());
                            textArea.setBackground(contentPanel_C1.getBackground());
                            contentPanel_C1.add(textArea);

                            // Add customer to the list to be removed
                            toRemove.add(customerList);

                        } else if (quantity <= 5 && counter2Count < 5) {
                            // Transfer customer to Counter 2
                            qCounter2.add(customerList);
                            counter2Count++;

                            // Create JTextArea to display customer info
                            String displayText = "============================================\n" +
                                "\n" +
                                customerList.getCustomerInfo().toString() +
                                "Order Name: " + customerList.getItem_Name() + "\n" +
                                "Total Amount: " + customerList.getFormattedTotalAmount() + "\n";
                            JTextArea textArea = new JTextArea(displayText);
                            textArea.setEditable(false);
                            textArea.setBorder(BorderFactory.createEmptyBorder());
                            textArea.setBackground(contentPanel_C2.getBackground());
                            contentPanel_C2.add(textArea);

                            // Add customer to the list to be removed
                            toRemove.add(customerList);

                        } else if (quantity > 5 && counter3Count < 5) {
                            // Transfer customer to Counter 3
                            qCounter3.add(customerList);
                            counter3Count++;

                            // Create JTextArea to display customer info
                            String displayText = "============================================\n" +
                                "\n" +
                                customerList.getCustomerInfo().toString() +
                                "Order Name: " + customerList.getItem_Name() + "\n" +
                                "Total Amount: " + customerList.getFormattedTotalAmount() + "\n";
                            JTextArea textArea = new JTextArea(displayText);
                            textArea.setEditable(false);
                            textArea.setBorder(BorderFactory.createEmptyBorder());
                            textArea.setBackground(contentPanel_C3.getBackground());
                            contentPanel_C3.add(textArea);

                            // Add customer to the list to be removed
                            toRemove.add(customerList);
                        }
                    }

                    // Remove the customers that have been transferred from qCustomer
                    qCustomer.removeAll(toRemove);

                    // Update qCustomer count display
                    countCustomer.setText("Total Customers: " + qCustomer.size());

                    // Refresh contentPanel_C1, contentPanel_C2, contentPanel_C3 after queuing customers
                    contentPanel_C1.revalidate();
                    contentPanel_C1.repaint();
                    contentPanel_C2.revalidate();
                    contentPanel_C2.repaint();
                    contentPanel_C3.revalidate();
                    contentPanel_C3.repaint();

                    // Disable the queue button
                    QCButton.setEnabled(false);
                }
            });

        // ActionListener for DButton (delete button)
        DButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Check if qCustomer is empty
                    if (qCustomer.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Customer", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return; // Exit the method if no customers are present
                    }

                    //prompt user to enter their ID to be deleted from queue
                    String custId = JOptionPane.showInputDialog(null, "Enter Customer ID to delete:");

                    if (custId != null && !custId.isEmpty()) {
                        boolean found = false;

                        // Create a temporary list to hold customers to retain
                        List<CustomerList> tempList = new LinkedList<>();

                        // Check qCustomer queue
                        while (!qCustomer.isEmpty()) {
                            CustomerList customerList = qCustomer.poll();
                            if (customerList.getCustomerInfo().getCustId().equalsIgnoreCase(custId)) {
                                found = true;
                            } else {
                                tempList.add(customerList);
                            }
                        }

                        // Restore qCustomer with retained customers
                        qCustomer.addAll(tempList);

                        // Updated GUI here to reflect the changes in the queue
                        countCustomer.setText("Total Customers: " + qCustomer.size());

                        if (found) {
                            JOptionPane.showMessageDialog(null, "Customer ID " + custId + " deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Customer ID " + custId + " not found.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer ID cannot be empty.");
                    }
                }
            });

        // ActionListener for P1_Button (counter1 payment button)
        P1_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Process customers in qCounter1
                    while (!qCounter1.isEmpty()) {
                        Receipt1.add(qCounter1.remove());
                    }

                    // Clear existing displays in contentPanel_C1
                    contentPanel_C1.removeAll();

                    // Refresh contentPanel_C1 after clearing
                    contentPanel_C1.revalidate();
                    contentPanel_C1.repaint();
                    
                    //enable the queue button
                    counter1Paid = true;
                    enableQ_Button();
                }
            });

        // ActionListener for R1_Button (counter1 receipt button)
        R1_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StringBuilder sb = new StringBuilder();

                    // Iterate through Receipt1 and build the receipt content
                    for (CustomerList customerList : Receipt1) {
                        CustomerInformation custInfo = customerList.getCustomerInfo();
                        OrderInformation orderInfo = customerList.getOrderInfo();

                        sb.append("============================================\n")
                        .append("\n")
                        .append(custInfo)
                        .append("Order Name: ")
                        .append(customerList.getItem_Name())
                        .append("\nTotal Amount:")
                        .append(customerList.getFormattedTotalAmount())
                        .append("\n\n");
                    }

                    // Display receipt in a dialog
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 400));

                    JOptionPane.showMessageDialog(null, scrollPane, "RECEIPT COUNTER 1", JOptionPane.INFORMATION_MESSAGE);

                    // Add unique entries from Receipt1 to completeStack
                    for (CustomerList customerList : Receipt1) {
                        if (!completeStack.contains(customerList)) {
                            completeStack.push(customerList);
                        }
                    }
                }
            });

        // ActionListener for P2_Button (counter2 payment button)
        P2_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Process customers in qCounter1
                    while (!qCounter2.isEmpty()) {
                        Receipt2.add(qCounter2.remove());
                    }

                    // Clear existing displays in contentPanel_C1
                    contentPanel_C2.removeAll();

                    // Refresh contentPanel_C1 after clearing
                    contentPanel_C2.revalidate();
                    contentPanel_C2.repaint();
                    
                    //enable the queue button
                    counter2Paid = true;
                    enableQ_Button();
                }
            });

        // ActionListener for R2_Button (counter2 receipt button)
        R2_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StringBuilder sb = new StringBuilder();

                    // Iterate through Receipt2 and build the receipt content
                    for (CustomerList customerList : Receipt2) {
                        CustomerInformation custInfo = customerList.getCustomerInfo();
                        OrderInformation orderInfo = customerList.getOrderInfo();

                        sb.append("============================================\n")
                        .append("\n")
                        .append(custInfo)
                        .append("Order Name: ")
                        .append(customerList.getItem_Name())
                        .append("\nTotal Amount:")
                        .append(customerList.getFormattedTotalAmount())
                        .append("\n\n");
                    }

                    // Display receipt in a dialog
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 400));

                    JOptionPane.showMessageDialog(null, scrollPane, "RECEIPT COUNTER 2", JOptionPane.INFORMATION_MESSAGE);

                    // Add to completeStack
                    for (CustomerList customerList : Receipt2) {
                        if (!completeStack.contains(customerList)) {
                            completeStack.push(customerList);
                        }
                    }
                }
            });

        // ActionListener for P3_Button (counter3 payment button)
        P3_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Process customers in qCounter1
                    while (!qCounter3.isEmpty()) {
                        Receipt3.add(qCounter3.remove());
                    }

                    // Clear existing displays in contentPanel_C1
                    contentPanel_C3.removeAll();

                    // Refresh contentPanel_C1 after clearing
                    contentPanel_C3.revalidate();
                    contentPanel_C3.repaint();
                    
                    //enable the queue button
                    counter3Paid = true;
                    enableQ_Button();
                }
            });
        // ActionListener for R3_Button (counter3 receipt button)
        R3_Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StringBuilder sb = new StringBuilder();

                    // Iterate through Receipt2 and build the receipt content
                    for (CustomerList customerList : Receipt3) {
                        CustomerInformation custInfo = customerList.getCustomerInfo();
                        OrderInformation orderInfo = customerList.getOrderInfo();

                        sb.append("============================================\n")
                        .append("\n")
                        .append(custInfo)
                        .append("Order Name: ")
                        .append(customerList.getItem_Name())
                        .append("\nTotal Amount:")
                        .append(customerList.getFormattedTotalAmount())
                        .append("\n\n");
                    }

                    // Display receipt in a dialog
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 400));

                    JOptionPane.showMessageDialog(null, scrollPane, "RECEIPT COUNTER 3", JOptionPane.INFORMATION_MESSAGE);

                    // Add to completeStack
                    for (CustomerList customerList : Receipt3) {
                        if (!completeStack.contains(customerList)) {
                            completeStack.push(customerList);
                        }
                    }
                }
            });

        // ActionListener for record_button (record button)
        record_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Prepare a StringBuilder to accumulate the content
                    StringBuilder sb = new StringBuilder();

                    // Iterate through completeStack to build the content
                    for (CustomerList customerList : completeStack) {
                        CustomerInformation custInfo = customerList.getCustomerInfo();
                        OrderInformation orderInfo = customerList.getOrderInfo();

                        sb.append("============================================\n")
                        .append("\n")
                        .append(custInfo)
                        .append(orderInfo)
                        .append("\n")
                        .append("Total Amount:" + customerList.getFormattedTotalAmount() + "\n")
                        .append("\n\n");
                    }

                    // Create a JTextArea to display the content
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 900));

                    // Show the scrollable text area in a JOptionPane
                    JOptionPane.showMessageDialog(null, scrollPane, "RECORD DATA FROM ALL COUNTER", JOptionPane.INFORMATION_MESSAGE);
                }
            });

        // Set the frame to be visible
        setVisible(true);
    }

    private void enableQ_Button() {
        if (counter1Paid && counter2Paid && counter3Paid) {
            QCButton.setEnabled(true);
            counter1Paid = false;
            counter2Paid = false;
        }
    }
} //end of class
