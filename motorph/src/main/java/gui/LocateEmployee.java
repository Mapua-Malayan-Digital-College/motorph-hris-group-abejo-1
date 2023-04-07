package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;

public class LocateEmployee extends JFrame
{
    private JTextField TxtField_EmployeeNumber;
    private JButton btn_Feb;
    private JButton btn_Jan;
    private JPanel panelMain;
    private JLabel Lbl_EnterEmployee;
    private JButton btn_Mar;
    private JButton btn_Apr;
    private JButton btn_May;
    private JButton btn_Jun;
    private JButton btn_Jul;
    private JButton btn_Aug;
    private JButton btn_Sep;
    private JButton btn_Oct;
    private JButton btn_Nov;
    private JButton btn_Dec;
    private JButton btn_Clear;
    private JLabel lbl_Number;

    public JTextField
    getTxtField_EmployeeNumber ()
    {
        return TxtField_EmployeeNumber;
    }

    public void
    setTxtField_EmployeeNumber (JTextField txtField_EmployeeNumber)
    {
        TxtField_EmployeeNumber = txtField_EmployeeNumber;
    }

    public JButton
    getBtn_Feb ()
    {
        return btn_Feb;
    }

    public void
    setBtn_Feb (JButton btn_Feb)
    {
        this.btn_Feb = btn_Feb;
    }

    public JButton
    getBtn_Jan ()
    {
        return btn_Jan;
    }

    public void
    setBtn_Jan (JButton btn_Jan)
    {
        this.btn_Jan = btn_Jan;
    }

    public JPanel
    getPanelMain ()
    {
        return panelMain;
    }

    public void
    setPanelMain (JPanel panelMain)
    {
        this.panelMain = panelMain;
    }

    public JLabel
    getLbl_EnterEmployeeNumber ()
    {
        return Lbl_EnterEmployee;
    }

    public void
    setLbl_EnterEmployeeNumber (JLabel lbl_EnterEmployeeNumber)
    {
        Lbl_EnterEmployee = lbl_EnterEmployeeNumber;
    }

    public JButton
    getBtn_Mar ()
    {
        return btn_Mar;
    }

    public void
    setBtn_Mar (JButton btn_Mar)
    {
        this.btn_Mar = btn_Mar;
    }

    public JButton
    getBtn_Apr ()
    {
        return btn_Apr;
    }

    public void
    setBtn_Apr (JButton btn_Apr)
    {
        this.btn_Apr = btn_Apr;
    }

    public JButton
    getBtn_May ()
    {
        return btn_May;
    }

    public void
    setBtn_May (JButton btn_May)
    {
        this.btn_May = btn_May;
    }

    public JButton
    getBtn_Jun ()
    {
        return btn_Jun;
    }

    public void
    setBtn_Jun (JButton btn_Jun)
    {
        this.btn_Jun = btn_Jun;
    }

    public JButton
    getBtn_Jul ()
    {
        return btn_Jul;
    }

    public void
    setBtn_Jul (JButton btn_Jul)
    {
        this.btn_Jul = btn_Jul;
    }

    public JButton
    getBtn_Aug ()
    {
        return btn_Aug;
    }

    public void
    setBtn_Aug (JButton btn_Aug)
    {
        this.btn_Aug = btn_Aug;
    }

    public JButton
    getBtn_Sep ()
    {
        return btn_Sep;
    }

    public void
    setBtn_Sep (JButton btn_Sep)
    {
        this.btn_Sep = btn_Sep;
    }

    public JButton
    getBtn_Oct ()
    {
        return btn_Oct;
    }

    public void
    setBtn_Oct (JButton btn_Oct)
    {
        this.btn_Oct = btn_Oct;
    }

    public JButton
    getBtn_Nov ()
    {
        return btn_Nov;
    }

    public void
    setBtn_Nov (JButton btn_Nov)
    {
        this.btn_Nov = btn_Nov;
    }

    public JButton
    getBtn_Dec ()
    {
        return btn_Dec;
    }

    public void
    setBtn_Dec (JButton btn_Dec)
    {
        this.btn_Dec = btn_Dec;
    }

    public JButton getBtn_Clear() {
        return btn_Clear;
    }

    public void setBtn_Clear(JButton btn_Clear) {
        this.btn_Clear = btn_Clear;
    }

    public JLabel getLbl_Number() {
        return lbl_Number;
    }

    public void setLbl_Number(JLabel lbl_Number) {
        this.lbl_Number = lbl_Number;
    }

    public LocateEmployee()
    {
        getTxtField_EmployeeNumber().requestFocus();
        setContentPane (getPanelMain ());
        setDefaultCloseOperation (DISPOSE_ON_CLOSE);
        pack ();
        F:
        setLocationRelativeTo (null);
        setVisible (false);

        ActionListener actionListener = new ActionListener () {
            @Override public void actionPerformed (ActionEvent e)
            {
                switch (e.getActionCommand()){

                    case ("Jan"):
                        System.out.println ("January Clicked!");
                        break;
                    case ("Feb"):
                        System.out.println ("February Clicked!");
                        break;
                    case ("Mar"):
                        System.out.println ("March Clicked!");
                        break;
                    case ("Apr"):
                        System.out.println ("April Clicked!");
                        break;
                    case ("May"):
                        System.out.println ("May Clicked!");
                        break;
                    case ("Jun"):
                        System.out.println ("June Clicked!");
                        break;
                    case ("Jul"):
                        System.out.println ("July Clicked!");
                        break;
                    case ("Aug"):
                        System.out.println ("August Clicked!");
                        break;
                    case ("Sep"):
                        System.out.println ("September Clicked!");
                        break;
                    case ("Oct"):
                        System.out.println ("October Clicked!");
                        break;
                    case ("Nov"):
                        System.out.println ("November Clicked!");
                        break;
                    case ("Dec"):
                        System.out.println ("December Clicked!");
                        break;
                    case ("Clear"):
                        System.out.println("Clear Fields");
                        getTxtField_EmployeeNumber().setText("");
                        String [] greetings = {
                                "Hello",
                                "Hi",
                                "Hey",
                                "Nice to see you",
                                "It's great to see you",
                                "Good to see you",
                                "Long time no see",
                                "It's been a while",
                                "Yo!",
                                "Howdy!"
                        };
                        int min = 0, max = 9;
                        Random random = new Random();
                        int random_number = random.nextInt(max - min + 1);
                        getLbl_Number().setText(greetings[random_number]);
                        getTxtField_EmployeeNumber().requestFocus();
                        break;
                }
            }
        };
        getBtn_Jan ().addActionListener (actionListener);
        getBtn_Feb ().addActionListener (actionListener);
        getBtn_Mar ().addActionListener (actionListener);
        getBtn_Apr ().addActionListener (actionListener);
        getBtn_May ().addActionListener (actionListener);
        getBtn_Jun ().addActionListener (actionListener);
        getBtn_Jul ().addActionListener (actionListener);
        getBtn_Aug ().addActionListener (actionListener);
        getBtn_Sep ().addActionListener (actionListener);
        getBtn_Oct ().addActionListener (actionListener);
        getBtn_Nov ().addActionListener (actionListener);
        getBtn_Dec ().addActionListener (actionListener);
        getTxtField_EmployeeNumber().addActionListener (actionListener);
        getBtn_Clear().addActionListener (actionListener);

        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        };
        getTxtField_EmployeeNumber().addFocusListener(focusListener);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("The input is " + getTxtField_EmployeeNumber().getText());
                String checkNumber = isNumeric(getTxtField_EmployeeNumber().getText())
                        ? (getTxtField_EmployeeNumber().getText())
                        : "Invalid input number";
                getLbl_Number().setText(checkNumber);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("The input is " + getTxtField_EmployeeNumber().getText());
                String checkNumber = isNumeric(getTxtField_EmployeeNumber().getText())
                        ? (getTxtField_EmployeeNumber().getText())
                        : "Invalid input number";
                getLbl_Number().setText(checkNumber);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getLbl_Number().setText(getTxtField_EmployeeNumber().getText());
            }
        };
        getTxtField_EmployeeNumber().getDocument().addDocumentListener(documentListener);
    }

    /*  Check if the input string is numeric
     *  @params Whether or not the input is numerical will be tested
     *  @return ? true : false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}