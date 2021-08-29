package customer;

import Database.DatabaseOperations;
import customer.DatasForCustomer.CustomerProfileData;
import customer.DatasForCustomer.WalletData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import main.main;

public class MoneyOrder extends JPanel implements MouseListener {

    Color background_Color = new Color(34, 34, 45);
    Color on_background_Color = new Color(254, 254, 254);
    Color primary_Color = new Color(71, 63, 145);

    JPanel moneyOrderPanel;

    JLabel moneyOrder_title;
    JButton moneyOrder_btn_confirm;

    //Left Label
    JLabel moneyOrder_toUsername;
    JLabel moneyOrder_amount;
    JLabel moneyOrder_firstName;
    JLabel moneyOrder_lastName;
    JLabel moneyOrder_type;
    JLabel moneyOrder_address;
    JLabel moneyOrder_state;
    JLabel moneyOrder_district;
    JLabel moneyOrder_pincode;
    JButton BackIconLabel;
    //Content Label
    JTextField moneyOrder_toUsername_info;
    JTextField moneyOrder_amount_info;
    JTextField moneyOrder_firstName_info;
    JTextField moneyOrder_lastName_info;
    JTextField moneyOrder_type_info;
    JTextArea moneyOrder_address_info;
    JTextField moneyOrder_state_info;
    JTextField moneyOrder_district_info;
    JTextField moneyOrder_pincode_info;
    Border emptyBorder = BorderFactory.createEmptyBorder();
    int R = 34, G = 34, B = 45;
    int X_FORCUSTOMER = 30, Y_FORCUSTOMER = 150, WIDTHFORCUSTOMER = 1260, HIGHTFORCUSTOMER = 750;

    public MoneyOrder() {

        setLayout(null);
        moneyOrder_btn_confirm = new JButton();

        //Label
        moneyOrder_title = new JLabel("Money Order");
        moneyOrder_toUsername = new JLabel("To Username");
        moneyOrder_amount = new JLabel("Amount");
        moneyOrder_firstName = new JLabel("First Name");
        moneyOrder_lastName = new JLabel("Last Name");
        moneyOrder_type = new JLabel("Type");
        moneyOrder_address = new JLabel("Address");
        moneyOrder_state = new JLabel("State");
        moneyOrder_district = new JLabel("District");
        moneyOrder_pincode = new JLabel("Pincode");

        //Content Label
        moneyOrder_toUsername_info = new JTextField(20);
        moneyOrder_amount_info = new JTextField(20);
        moneyOrder_firstName_info = new JTextField(20);
        moneyOrder_lastName_info = new JTextField(20);
        moneyOrder_type_info = new JTextField(20);
        moneyOrder_address_info = new JTextArea();
        moneyOrder_state_info = new JTextField(20);
        moneyOrder_district_info = new JTextField(20);
        moneyOrder_pincode_info = new JTextField(20);

        moneyOrder_address_info.setLineWrap(true);

        Icon BackIcon = new ImageIcon(getClass().getResource("/Images/arrow_back_button.png"));
        BackIconLabel = new JButton(BackIcon);
        BackIconLabel.setBounds(10, 10, 30, 30);
        BackIconLabel.setBackground(new Color(R, G, B));
        BackIconLabel.setBorder(emptyBorder);
        BackIconLabel.setVisible(true);
        BackIconLabel.setContentAreaFilled(false);
        BackIconLabel.addMouseListener(this);
        add(BackIconLabel);

        //Button and title
        moneyOrder_title.setBounds(555, 20, 200, 50);
        moneyOrder_btn_confirm.setBounds(570, 675, 150, 40);
        moneyOrder_btn_confirm.setBackground(primary_Color);
        moneyOrder_btn_confirm.setText("Confirm");
        moneyOrder_btn_confirm.setForeground(on_background_Color);
        moneyOrder_btn_confirm.setBorder(null);
        moneyOrder_btn_confirm.addActionListener((l) -> {
               if(!moneyOrder_toUsername_info.getText().isEmpty() && !moneyOrder_amount_info.getText().isEmpty() && !moneyOrder_firstName_info.getText().isEmpty() && !moneyOrder_lastName_info.getText().isEmpty() && !moneyOrder_type_info.getText().isEmpty() && !moneyOrder_address_info.getText().isEmpty() && !moneyOrder_state_info.getText().isEmpty() && !moneyOrder_district_info.getText().isEmpty() && !moneyOrder_pincode_info.getText().isEmpty()){
            
                if( DatabaseOperations.CheckIdPresentOrNot(moneyOrder_toUsername_info.getText()) &&  pincodeValidate(moneyOrder_amount_info.getText()) && pincodeValidate(moneyOrder_pincode_info.getText()) && (Integer.parseInt(moneyOrder_amount_info.getText())<= Integer.parseInt(CustomerProfileData.getBankBalance()))){
               
               
                        WalletData.MoneyOrderValues.add(moneyOrder_toUsername_info.getText()); //0
                        WalletData.MoneyOrderValues.add(moneyOrder_amount_info.getText()); //1
                        WalletData.MoneyOrderValues.add(moneyOrder_firstName_info.getText()); //2
                        WalletData.MoneyOrderValues.add(moneyOrder_lastName_info.getText()); //3
                        WalletData.MoneyOrderValues.add(moneyOrder_type_info.getText()); //4
                        WalletData.MoneyOrderValues.add(moneyOrder_address_info.getText());//5
                        WalletData.MoneyOrderValues.add(moneyOrder_state_info.getText());//6
                        WalletData.MoneyOrderValues.add(moneyOrder_district_info.getText());//7
                        WalletData.MoneyOrderValues.add(moneyOrder_pincode_info.getText());//8
                        new WalletAuthentication();
               
            
            //new Authentication();
                }
                else if(Integer.parseInt(moneyOrder_amount_info.getText()) > Integer.parseInt(CustomerProfileData.getBankBalance())){
                    JOptionPane.showMessageDialog(null,"Insufficient balance");
                }
                else if(!pincodeValidate(moneyOrder_amount_info.getText()) && !pincodeValidate(moneyOrder_pincode_info.getText())){
                        JOptionPane.showMessageDialog(null,"Invalid Amountand Invalid Pincode");
                }
                else if(!pincodeValidate(moneyOrder_pincode_info.getText())){
                        JOptionPane.showMessageDialog(null,"Invalid Pincode");
                }
                else if(!pincodeValidate(moneyOrder_amount_info.getText())){
                        JOptionPane.showMessageDialog(null,"Invalid amount");
                }
                else{
                        JOptionPane.showMessageDialog(null,"Invalid userId");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Enter all the Fields");
            }
            
        });

        //Label Bounds
        moneyOrder_toUsername.setBounds(120, 100, 150, 50);
        moneyOrder_amount.setBounds(750, 100, 150, 50);
        moneyOrder_firstName.setBounds(120, 200, 150, 50);
        moneyOrder_lastName.setBounds(750, 200, 150, 50);
        moneyOrder_type.setBounds(120, 300, 150, 50);
        moneyOrder_address.setBounds(120, 500, 150, 50);
        moneyOrder_state.setBounds(120, 400, 150, 50);
        moneyOrder_district.setBounds(750, 400, 150, 50);
        moneyOrder_pincode.setBounds(750, 300, 150, 50);

        //Content Label Bounds
        moneyOrder_toUsername_info.setBounds(300, 10 + 100, 200, 30);
        moneyOrder_amount_info.setBounds(880, 10 + 100, 200, 30);
        moneyOrder_firstName_info.setBounds(300, 110 + 100, 200, 30);
        moneyOrder_lastName_info.setBounds(880, 110 + 100, 200, 30);
        moneyOrder_type_info.setBounds(300, 210 + 100, 200, 30);
        moneyOrder_address_info.setBounds(300, 410 + 100, 750, 150);
        moneyOrder_state_info.setBounds(300, 310 + 100, 200, 30);
        moneyOrder_district_info.setBounds(880, 310 + 100, 200, 30);
        moneyOrder_pincode_info.setBounds(880, 210 + 100, 200, 30);

        //Label Text Color
        moneyOrder_title.setForeground(on_background_Color);
        moneyOrder_toUsername.setForeground(on_background_Color);
        moneyOrder_amount.setForeground(on_background_Color);
        moneyOrder_firstName.setForeground(on_background_Color);
        moneyOrder_lastName.setForeground(on_background_Color);
        moneyOrder_type.setForeground(on_background_Color);
        moneyOrder_address.setForeground(on_background_Color);
        moneyOrder_state.setForeground(on_background_Color);
        moneyOrder_district.setForeground(on_background_Color);
        moneyOrder_pincode.setForeground(on_background_Color);

        //Content Label Text Color
        moneyOrder_toUsername_info.setForeground(background_Color);
        moneyOrder_amount_info.setForeground(background_Color);
        moneyOrder_firstName_info.setForeground(background_Color);
        moneyOrder_lastName_info.setForeground(background_Color);
        moneyOrder_type_info.setForeground(background_Color);
        moneyOrder_address_info.setForeground(background_Color);
        moneyOrder_state_info.setForeground(background_Color);
        moneyOrder_district_info.setForeground(background_Color);
        moneyOrder_pincode_info.setForeground(background_Color);

        //Label Font
        moneyOrder_title.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        moneyOrder_toUsername.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_amount.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_firstName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_lastName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_type.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_address.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_state.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_district.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_pincode.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        //Content Label Font
        moneyOrder_toUsername_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_amount_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_firstName_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_lastName_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_type_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_address_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_state_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_district_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        moneyOrder_pincode_info.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        setBounds(50, 0, 1350, 890);

        //Add to Panel
        //moneyOrderPanel.add(moneyOrder_title);
        add(moneyOrder_toUsername);
        add(moneyOrder_amount);
        add(moneyOrder_firstName);
        add(moneyOrder_lastName);
        add(moneyOrder_type);
        add(moneyOrder_address);
        add(moneyOrder_state);
        add(moneyOrder_district);
        add(moneyOrder_pincode);

        add(moneyOrder_toUsername_info);
        add(moneyOrder_amount_info);
        add(moneyOrder_firstName_info);
        add(moneyOrder_lastName_info);
        add(moneyOrder_type_info);
        add(moneyOrder_address_info);
        add(moneyOrder_state_info);
        add(moneyOrder_district_info);
        add(moneyOrder_pincode_info);
        add(moneyOrder_btn_confirm);
        add(moneyOrder_title);

        //Panel
        setBackground(background_Color);
        setSize(1350, 925);

        setVisible(true);

    }

    //Mouse Listener For BackButton to Switch
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == BackIconLabel) {
            main.switchPage("customerPanel");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    static boolean pincodeValidate(String code){
        String s="[0-9]+";
        return code.matches(s);
    }

}
