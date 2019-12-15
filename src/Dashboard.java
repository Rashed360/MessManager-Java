import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RusH
 */
public class Dashboard extends javax.swing.JFrame {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        super("LoginPage");
        initComponents();
        Calender();
        conn = JavaConnect.ConnectDb();
        loggedUser();        
        calcMessData();
        calcUserData();
        dashMessData();
        dashUserData();
        usersTable();
        Members();
        fetchMeal();
        loadProfile();
    }
    
    public void Calender(){
        Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH)+1;
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        txt_date.setText(day+"-"+month+"-"+year);
        txt_depDate.setText(day+"-"+month+"-"+year);
        txt_date1.setText(day+"-"+month+"-"+year);
        //txt_date2.setText(day+"-"+month+"-"+year);
    }
    
    public void loggedUser(){
        String name =LoginPage.txt_username.getText();
        label_loggedUser.setText(name);
        try{
            String sql="select type from users where userName='"+name+"'";
            pst=conn.prepareStatement(sql);
             rs=pst.executeQuery();
            if(rs.next()){
                String type = rs.getString("type");
                label_loggedType.setText(type);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "01 : "+e);
        }
    }
    
    public void usersTable(){
        try{
            String sql="select id,userName,email,type,meal,cost from users";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "02 : "+e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){ }
        }
    }
    
    public void Members(){
        try{
            String sql="select userName from users";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String user = rs.getString("userName");
                comboUsers.addItem(user);
            }
            rs.close();
            pst.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "03 : "+e);
        }
    }
    
    public void fetchMeal(){
        try{
            String sql="select * from meal";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "04 : "+e);
        }
    }
    
    public void loadProfile(){
         try{
            String sql="select userName,type,email,phone,address from users where userName=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, label_loggedUser.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String userName = rs.getString("userName");
                jTextField30.setText(userName);
                String type = rs.getString("type");
                jTextField32.setText(type);
                String email = rs.getString("email");
                jTextField33.setText(email);
                String phone = rs.getString("phone");
                jTextField31.setText(phone);
                String address = rs.getString("address");
                jTextField34.setText(address);
                rs.close();
                pst.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "05 : "+e);
        }
    }
    
    public void calcMessData(){
         try{
            String sql="select sum(meal), sum(deposit), sum(cost), sum(balance) ,sum(other), mess from users";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                int meal = Integer.parseInt(rs.getString("sum(meal)"));
                int deposit = Integer.parseInt(rs.getString("sum(deposit)"));
                int cost = Integer.parseInt(rs.getString("sum(cost)"));
                int balance = Integer.parseInt(rs.getString("sum(balance)"));
                int other = Integer.parseInt(rs.getString("sum(other)"));
                String mess = rs.getString("mess");
                try{
                    String sql1="update mess set totalMeal='"+meal+"',totalDeposit='"+deposit+"',totalCost='"+cost+"',totalBalance='"+balance+"',otherCost='"+other+"' where messName='"+mess+"'";
                    pst=conn.prepareStatement(sql1);
                    pst.execute();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "06 : "+e);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "07 : "+e);
        }
    }
    
    public void calcUserData(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        label_loggedUser = new javax.swing.JLabel();
        label_loggedType = new javax.swing.JLabel();
        txt_date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton45 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        panel_sidebar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_home = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        label_totalMeal = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        label_mealRate = new javax.swing.JLabel();
        label_otherCost = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        label_totalBalance = new javax.swing.JLabel();
        label_messName = new javax.swing.JLabel();
        label_mealCost = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label_myOther = new javax.swing.JLabel();
        label_myCost = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        label_myDeposit = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        label_bazarDate = new javax.swing.JLabel();
        label_myMeal = new javax.swing.JLabel();
        label_myBalance = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jLabel103 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel106 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel107 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        panel_addmember = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        panel_newmember = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        panel_extmember = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        panel_addmoney = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        txt_depDate = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        panel_addmeal = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        comboUsers = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txt_date1 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        panel_addcost = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        txt_depDate1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        panel_othcost = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        txt_depDate2 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        panel_allmembers = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel98 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mess Manager");

        panel_header.setBackground(new java.awt.Color(94, 209, 192));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Documents\\NetBeansProjects\\Mess_Management_System\\img\\logo_sm.png")); // NOI18N

        jLabel46.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("MESS MANAGER");

        jPanel4.setBackground(new java.awt.Color(94, 209, 192));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        label_loggedUser.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        label_loggedUser.setForeground(new java.awt.Color(255, 255, 255));
        label_loggedUser.setText("User Name");

        label_loggedType.setForeground(new java.awt.Color(102, 102, 102));
        label_loggedType.setText("Member");

        txt_date.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 11)); // NOI18N
        txt_date.setForeground(new java.awt.Color(255, 255, 255));
        txt_date.setText("00-00-0000");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date :");

        jButton45.setBackground(new java.awt.Color(40, 52, 72));
        jButton45.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton45.setForeground(new java.awt.Color(255, 255, 255));
        jButton45.setText("LogOut");
        jButton45.setFocusable(false);
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon("D:\\Documents\\NetBeansProjects\\Mess_Management_System\\img\\user.png")); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_headerLayout = new javax.swing.GroupLayout(panel_header);
        panel_header.setLayout(panel_headerLayout);
        panel_headerLayout.setHorizontalGroup(
            panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_headerLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jLabel46)
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_loggedUser)
                    .addComponent(label_loggedType))
                .addGap(20, 20, 20)
                .addComponent(jButton45)
                .addGap(5, 5, 5))
        );
        panel_headerLayout.setVerticalGroup(
            panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_headerLayout.createSequentialGroup()
                .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_headerLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_date)
                                .addComponent(jLabel3))
                            .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_headerLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel46))
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_headerLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_headerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(panel_headerLayout.createSequentialGroup()
                                .addComponent(label_loggedUser)
                                .addGap(0, 0, 0)
                                .addComponent(label_loggedType)))))
                .addGap(5, 5, 5))
        );

        panel_sidebar.setBackground(new java.awt.Color(40, 52, 72));

        jButton1.setBackground(new java.awt.Color(94, 209, 192));
        jButton1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(94, 209, 192));
        jButton2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add Member");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(94, 209, 192));
        jButton3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add Money");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(94, 209, 192));
        jButton4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add Meal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(94, 209, 192));
        jButton5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add Daily Cost");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(94, 209, 192));
        jButton6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Add Other Cost");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(94, 209, 192));
        jButton7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("All Members");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(94, 209, 192));
        jButton8.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton8.setText("Remove Member");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(94, 209, 192));
        jSeparator1.setForeground(new java.awt.Color(94, 209, 192));

        jButton9.setBackground(new java.awt.Color(94, 209, 192));
        jButton9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton9.setText("Change Manager");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(94, 209, 192));
        jButton10.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton10.setText("Start New Month");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(94, 209, 192));
        jButton11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton11.setText("Present Details");

        jButton12.setBackground(new java.awt.Color(94, 209, 192));
        jButton12.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton12.setText("Previous Details");

        jButton13.setBackground(new java.awt.Color(94, 209, 192));
        jButton13.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton13.setText("Delete Mess");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(94, 209, 192));
        jButton30.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton30.setText("About");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_sidebarLayout = new javax.swing.GroupLayout(panel_sidebar);
        panel_sidebar.setLayout(panel_sidebarLayout);
        panel_sidebarLayout.setHorizontalGroup(
            panel_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel_sidebarLayout.setVerticalGroup(
            panel_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_sidebarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addGap(10, 10, 10)
                .addComponent(jButton2)
                .addGap(10, 10, 10)
                .addComponent(jButton3)
                .addGap(10, 10, 10)
                .addComponent(jButton4)
                .addGap(10, 10, 10)
                .addComponent(jButton5)
                .addGap(10, 10, 10)
                .addComponent(jButton6)
                .addGap(10, 10, 10)
                .addComponent(jButton7)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton8)
                .addGap(10, 10, 10)
                .addComponent(jButton9)
                .addGap(10, 10, 10)
                .addComponent(jButton10)
                .addGap(10, 10, 10)
                .addComponent(jButton11)
                .addGap(10, 10, 10)
                .addComponent(jButton12)
                .addGap(10, 10, 10)
                .addComponent(jButton13)
                .addGap(10, 10, 10)
                .addComponent(jButton30)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "MESS DETAILS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 0, 8), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("MESS NAME");

        label_totalMeal.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_totalMeal.setForeground(new java.awt.Color(40, 52, 72));
        label_totalMeal.setText("000");

        jLabel18.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("TOTAL MEAL COST");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("TOTAL MEAL");

        label_mealRate.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_mealRate.setForeground(new java.awt.Color(40, 52, 72));
        label_mealRate.setText("00 tk");

        label_otherCost.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_otherCost.setForeground(new java.awt.Color(40, 52, 72));
        label_otherCost.setText("000 tk");

        jLabel20.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("OTHER COST PP");

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("TOTAL BALANCE");

        label_totalBalance.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_totalBalance.setForeground(new java.awt.Color(40, 52, 72));
        label_totalBalance.setText("0000 tk");

        label_messName.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_messName.setForeground(new java.awt.Color(40, 52, 72));
        label_messName.setText("Mess Name");

        label_mealCost.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_mealCost.setForeground(new java.awt.Color(40, 52, 72));
        label_mealCost.setText("0000 tk");

        jLabel12.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("LIVE MEAL RATE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_totalMeal)
                    .addComponent(jLabel4)
                    .addComponent(label_messName)
                    .addComponent(jLabel2))
                .addGap(140, 140, 140)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(label_totalBalance)
                    .addComponent(jLabel12)
                    .addComponent(label_mealRate))
                .addGap(150, 150, 150)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(label_mealCost)
                    .addComponent(jLabel20)
                    .addComponent(label_otherCost))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(label_messName)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(10, 10, 10)
                        .addComponent(label_totalMeal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(10, 10, 10)
                        .addComponent(label_mealCost)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel20)
                        .addGap(10, 10, 10)
                        .addComponent(label_otherCost))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addComponent(label_totalBalance)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(label_mealRate)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "MY DETAILS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8), new java.awt.Color(153, 153, 153))); // NOI18N

        label_myOther.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_myOther.setForeground(new java.awt.Color(40, 52, 72));
        label_myOther.setText("0000 tk");

        label_myCost.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_myCost.setForeground(new java.awt.Color(40, 52, 72));
        label_myCost.setText("0000 tk");

        jLabel24.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("OTHER");

        jLabel14.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("MY DEPOSIT");

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("MY MEAL");

        label_myDeposit.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_myDeposit.setForeground(new java.awt.Color(40, 52, 72));
        label_myDeposit.setText("0000 tk");

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("MY BAZAR DATE");

        jLabel16.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("MY BALANCE");

        jLabel22.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("MY COST");

        label_bazarDate.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_bazarDate.setForeground(new java.awt.Color(40, 52, 72));
        label_bazarDate.setText("00-00-0000");

        label_myMeal.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_myMeal.setForeground(new java.awt.Color(40, 52, 72));
        label_myMeal.setText("00");

        label_myBalance.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        label_myBalance.setForeground(new java.awt.Color(40, 52, 72));
        label_myBalance.setText("0000 tk");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(label_myMeal)
                    .addComponent(jLabel8)
                    .addComponent(label_bazarDate))
                .addGap(150, 150, 150)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(label_myDeposit)
                    .addComponent(jLabel16)
                    .addComponent(label_myBalance))
                .addGap(150, 150, 150)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(label_myCost)
                    .addComponent(jLabel24)
                    .addComponent(label_myOther))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(10, 10, 10)
                        .addComponent(label_myMeal)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addGap(10, 10, 10)
                        .addComponent(label_bazarDate))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(10, 10, 10)
                        .addComponent(label_myCost)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel24)
                        .addGap(10, 10, 10)
                        .addComponent(label_myOther))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(10, 10, 10)
                        .addComponent(label_myDeposit)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel16)
                        .addGap(10, 10, 10)
                        .addComponent(label_myBalance)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jButton40.setText("Re-calculate Mess Details");

        jButton41.setText("Re-calculate Members Details");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_homeLayout = new javax.swing.GroupLayout(panel_home);
        panel_home.setLayout(panel_homeLayout);
        panel_homeLayout.setHorizontalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_homeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_homeLayout.createSequentialGroup()
                        .addComponent(jButton40)
                        .addGap(18, 18, 18)
                        .addComponent(jButton41))
                    .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panel_homeLayout.setVerticalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_homeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton40)
                    .addComponent(jButton41))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Home", panel_home);

        jLabel99.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel99.setText("User's Profile");

        jLabel100.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel100.setText("Member's Name");

        jTextField30.setEditable(false);
        jTextField30.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel101.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel101.setText("Phone");

        jTextField31.setEditable(false);
        jTextField31.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton42.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton42.setText("EDIT");
        jButton42.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel103.setText("Member Type");

        jTextField32.setEditable(false);
        jTextField32.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel104.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel104.setText("Email");

        jTextField33.setEditable(false);
        jTextField33.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel105.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel105.setText("Address");

        jTextField34.setEditable(false);
        jTextField34.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Change Password"));

        jLabel102.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel102.setText("Old Password");

        jPasswordField1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel106.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel106.setText("New Password");

        jPasswordField2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel107.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel107.setText("Re-type new Password");

        jPasswordField3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton43.setText("Change Password");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton43)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel102)
                        .addComponent(jLabel106)
                        .addComponent(jLabel107)
                        .addComponent(jPasswordField1)
                        .addComponent(jPasswordField2)
                        .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel102)
                .addGap(10, 10, 10)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel106)
                .addGap(10, 10, 10)
                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel107)
                .addGap(10, 10, 10)
                .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton44.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton44.setText("SUBMIT");
        jButton44.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel99)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel100)
                        .addGap(16, 16, 16)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel103)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel104)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel101)
                        .addGap(11, 11, 11)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Profile", jPanel12);

        jLabel26.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel26.setText("ADD MEMBER TO MESS");

        jButton14.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jButton14.setText("NEW");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jButton15.setText("EXISTING");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel27.setText("Enter Member's Info");

        jLabel28.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel28.setText("User Name");

        jTextField1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel29.setText("E-mail");

        jTextField2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel30.setText("Select Member's Role");

        jLabel31.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel31.setText("New member's default password is: 1234, use this to login to his account, then change default password");

        jButton16.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jButton16.setText("ADD MEMBER");
        jButton16.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Member", "Manager" }));

        javax.swing.GroupLayout panel_newmemberLayout = new javax.swing.GroupLayout(panel_newmember);
        panel_newmember.setLayout(panel_newmemberLayout);
        panel_newmemberLayout.setHorizontalGroup(
            panel_newmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_newmemberLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_newmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel28)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addGroup(panel_newmemberLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel27))
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_newmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 229, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        panel_newmemberLayout.setVerticalGroup(
            panel_newmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_newmemberLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel27)
                .addGap(20, 20, 20)
                .addComponent(jLabel28)
                .addGap(10, 10, 10)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel29)
                .addGap(10, 10, 10)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel30)
                .addGap(15, 15, 15)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(30, 30, 30)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel32.setText("Enter Member's Info");

        jLabel33.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel33.setText("Select Member");

        jTextField3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel34.setText("User Name");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel35.setText("Select Member's Role");

        jLabel36.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel36.setText("Existing member now can login to view the mess information");

        jButton17.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jButton17.setText("ADD MEMBER");
        jButton17.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jButton18.setText("Search");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel37.setText("E-mail");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Member", "Manager" }));

        javax.swing.GroupLayout panel_extmemberLayout = new javax.swing.GroupLayout(panel_extmember);
        panel_extmember.setLayout(panel_extmemberLayout);
        panel_extmemberLayout.setHorizontalGroup(
            panel_extmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_extmemberLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_extmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel33)
                    .addGroup(panel_extmemberLayout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton18))
                    .addComponent(jLabel34)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addGroup(panel_extmemberLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel32))
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addGroup(panel_extmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 229, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(320, Short.MAX_VALUE))
        );
        panel_extmemberLayout.setVerticalGroup(
            panel_extmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_extmemberLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel32)
                .addGap(20, 20, 20)
                .addComponent(jLabel33)
                .addGap(10, 10, 10)
                .addGroup(panel_extmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18))
                .addGap(20, 20, 20)
                .addComponent(jLabel34)
                .addGap(10, 10, 10)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel37)
                .addGap(10, 10, 10)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel36)
                .addGap(30, 30, 30)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_newmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(panel_extmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_newmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel_extmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        jLayeredPane1.setLayer(panel_newmember, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(panel_extmember, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel_addmemberLayout = new javax.swing.GroupLayout(panel_addmember);
        panel_addmember.setLayout(panel_addmemberLayout);
        panel_addmemberLayout.setHorizontalGroup(
            panel_addmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addmemberLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_addmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addGroup(panel_addmemberLayout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        panel_addmemberLayout.setVerticalGroup(
            panel_addmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addmemberLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel26)
                .addGap(20, 20, 20)
                .addGroup(panel_addmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(514, 514, 514))
        );

        jTabbedPane1.addTab("Add Member", panel_addmember);

        jLabel38.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel38.setText("Add Member's Deposit");

        jLabel39.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel39.setText("Select Member Who will Deposit");

        jTextField6.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton19.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton19.setText("Search");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel40.setText("Selected Member");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel41.setText("Available Deposit");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton20.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton20.setText("SUBMIT");
        jButton20.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel42.setText("Date");

        txt_depDate.setEditable(false);
        txt_depDate.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel43.setText("Double Check the Amount of Money and Depositor Name before Submit.");

        jLabel44.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel44.setText("Enter Deposit Amount");

        jTextField9.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton21.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton21.setText("Total");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel45.setText("New Deposit Amount");

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panel_addmoneyLayout = new javax.swing.GroupLayout(panel_addmoney);
        panel_addmoney.setLayout(panel_addmoneyLayout);
        panel_addmoneyLayout.setHorizontalGroup(
            panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addmoneyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(txt_depDate, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_addmoneyLayout.createSequentialGroup()
                        .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                .addComponent(jLabel40)
                                .addComponent(jTextField9))
                            .addComponent(jLabel39)
                            .addComponent(jLabel38)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_addmoneyLayout.setVerticalGroup(
            panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addmoneyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addGap(20, 20, 20)
                .addComponent(jLabel39)
                .addGap(10, 10, 10)
                .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19))
                .addGap(30, 30, 30)
                .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addGap(10, 10, 10)
                .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel44)
                .addGap(10, 10, 10)
                .addGroup(panel_addmoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel45)
                .addGap(10, 10, 10)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel42)
                .addGap(10, 10, 10)
                .addComponent(txt_depDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addGap(30, 30, 30)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        jTabbedPane1.addTab("Add Money", panel_addmoney);

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel7.setText("Enter Todays meal Consumptions");

        comboUsers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel9.setText("Member Name");

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel11.setText("Meal Amount");

        jButton22.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jButton22.setText("-");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("0");
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jButton23.setText("+");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel13.setText("Date");

        txt_date1.setEditable(false);
        txt_date1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton24.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton24.setText("Submit");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel15.setText("Make Sure Everybody's meal is added");

        jButton26.setText("Edit");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel17.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel17.setText("Meal Chart :");

        jButton25.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton25.setText("Refresh");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_addmealLayout = new javax.swing.GroupLayout(panel_addmeal);
        panel_addmeal.setLayout(panel_addmealLayout);
        panel_addmealLayout.setHorizontalGroup(
            panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addmealLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(panel_addmealLayout.createSequentialGroup()
                        .addComponent(jButton22)
                        .addGap(5, 5, 5)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton23))
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9)
                    .addGroup(panel_addmealLayout.createSequentialGroup()
                        .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(panel_addmealLayout.createSequentialGroup()
                        .addComponent(jButton24)
                        .addGap(18, 18, 18)
                        .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton25)
                            .addComponent(jLabel17))))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_addmealLayout.setVerticalGroup(
            panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addmealLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_addmealLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addComponent(comboUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton22)
                                .addComponent(jButton23)))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13)
                        .addGap(10, 10, 10)
                        .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton26))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(30, 30, 30)
                        .addGroup(panel_addmealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton24)
                            .addComponent(jButton25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Meal", panel_addmeal);

        jLabel47.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel47.setText("Add Member's Daily Cost");

        jLabel48.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel48.setText("Select Member Who Spent the Money");

        jTextField12.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton27.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton27.setText("Search");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel49.setText("Selected Member");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel50.setText("Priviously Spent");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton28.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton28.setText("SUBMIT");
        jButton28.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel51.setText("Date");

        txt_depDate1.setEditable(false);
        txt_depDate1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel52.setText("Double Check the Amount of Money and Depositor Name before Submit.");

        jLabel53.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel53.setText("Enter Cost Amount");

        jTextField15.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton29.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton29.setText("Total");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel54.setText("New Cost Amount");

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panel_addcostLayout = new javax.swing.GroupLayout(panel_addcost);
        panel_addcost.setLayout(panel_addcostLayout);
        panel_addcostLayout.setHorizontalGroup(
            panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addcostLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(txt_depDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_addcostLayout.createSequentialGroup()
                        .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                .addComponent(jLabel49)
                                .addComponent(jTextField15))
                            .addComponent(jLabel48)
                            .addComponent(jLabel47)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_addcostLayout.setVerticalGroup(
            panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addcostLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel47)
                .addGap(20, 20, 20)
                .addComponent(jLabel48)
                .addGap(10, 10, 10)
                .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27))
                .addGap(30, 30, 30)
                .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50))
                .addGap(10, 10, 10)
                .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel53)
                .addGap(10, 10, 10)
                .addGroup(panel_addcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel54)
                .addGap(10, 10, 10)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel51)
                .addGap(10, 10, 10)
                .addComponent(txt_depDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel52)
                .addGap(30, 30, 30)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Cost", panel_addcost);

        jLabel58.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel58.setText("Add Member's Other Cost");

        jLabel59.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel59.setText("Select Member Who will Deposit");

        jTextField18.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton35.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton35.setText("Search");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel60.setText("Selected Member");

        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel61.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel61.setText("Total Cost");

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton36.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton36.setText("SUBMIT");
        jButton36.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel62.setText("Date");

        txt_depDate2.setEditable(false);
        txt_depDate2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel63.setText("Double Check the Amount of Money and Depositor Name before Submit.");

        jLabel64.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel64.setText("Enter Cost Amount");

        jTextField21.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton37.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton37.setText("Total");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel65.setText("New Cost Amount");

        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panel_othcostLayout = new javax.swing.GroupLayout(panel_othcost);
        panel_othcost.setLayout(panel_othcostLayout);
        panel_othcostLayout.setHorizontalGroup(
            panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_othcostLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(txt_depDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_othcostLayout.createSequentialGroup()
                        .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                .addComponent(jLabel60)
                                .addComponent(jTextField21))
                            .addComponent(jLabel59)
                            .addComponent(jLabel58)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_othcostLayout.setVerticalGroup(
            panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_othcostLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel58)
                .addGap(20, 20, 20)
                .addComponent(jLabel59)
                .addGap(10, 10, 10)
                .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35))
                .addGap(30, 30, 30)
                .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61))
                .addGap(10, 10, 10)
                .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel64)
                .addGap(10, 10, 10)
                .addGroup(panel_othcostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel65)
                .addGap(10, 10, 10)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel62)
                .addGap(10, 10, 10)
                .addComponent(txt_depDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel63)
                .addGap(30, 30, 30)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Other Cost", panel_othcost);

        jLabel5.setText("All Members of Mess :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Name", "Email", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout panel_allmembersLayout = new javax.swing.GroupLayout(panel_allmembers);
        panel_allmembers.setLayout(panel_allmembersLayout);
        panel_allmembersLayout.setHorizontalGroup(
            panel_allmembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_allmembersLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel_allmembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_allmembersLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_allmembersLayout.setVerticalGroup(
            panel_allmembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_allmembersLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Members", panel_allmembers);

        jLabel81.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel81.setText("Remove Member from Mess");

        jLabel82.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel82.setText("Select Member to Remove");

        jTextField17.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jButton31.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton31.setText("Search");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 0, 51));
        jButton32.setText("Remove Member");
        jButton32.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel84.setText("Selected Member");

        jTextField23.setEditable(false);
        jTextField23.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel85.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel85.setText("Deleted data of a member can not be retrived later");

        jLabel86.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel86.setText("Enter Admin Password");

        jTextField24.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel86)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addComponent(jLabel84))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(jLabel81)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(439, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel81)
                .addGap(20, 20, 20)
                .addComponent(jLabel82)
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31))
                .addGap(20, 20, 20)
                .addComponent(jLabel84)
                .addGap(10, 10, 10)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel86)
                .addGap(10, 10, 10)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );

        jTabbedPane1.addTab("Remove Member", jPanel5);

        jLabel88.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel88.setText("Change Mess Manager");

        jLabel90.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel90.setText("Select Member to be Manager");

        jTextField25.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel91.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel91.setText("Selected Member");

        jTextField26.setEditable(false);
        jTextField26.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel92.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel92.setText("Enter Current Manager Password");

        jTextField27.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel93.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel93.setText("Manager Can add, remove and edit daily cost and meals");

        jButton33.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton33.setForeground(new java.awt.Color(0, 0, 255));
        jButton33.setText("Change Manager");
        jButton33.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton34.setText("Search");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField26, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addComponent(jLabel91))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90)
                            .addComponent(jLabel88)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(439, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel88)
                .addGap(20, 20, 20)
                .addComponent(jLabel90)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34))
                .addGap(20, 20, 20)
                .addComponent(jLabel91)
                .addGap(10, 10, 10)
                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel92)
                .addGap(10, 10, 10)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );

        jTabbedPane1.addTab("Change Manager", jPanel6);

        jLabel94.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel94.setText("Start New Month");

        jLabel95.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel95.setText("Select Manager");

        jTextField28.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel96.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel96.setText("Selected Member");

        jTextField29.setEditable(false);
        jTextField29.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel97.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel97.setText("Select Month");

        jComboBox3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "March", "April", "May", "June", "July", "August", "September", "Octobar", "November", "December" }));

        jLabel98.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 11)); // NOI18N
        jLabel98.setText("Previous month's details will be kept in a PDF");

        jButton38.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jButton38.setText("Start New Month");
        jButton38.setPreferredSize(new java.awt.Dimension(111, 23));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton39.setText("Search");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel94))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel98)
                            .addComponent(jLabel95)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel96)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(471, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel94)
                .addGap(30, 30, 30)
                .addComponent(jLabel97)
                .addGap(15, 15, 15)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel95)
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39))
                .addGap(19, 19, 19)
                .addComponent(jLabel96)
                .addGap(10, 10, 10)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel98)
                .addGap(20, 20, 20)
                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jTabbedPane1.addTab("New Month", jPanel11);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel69.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel69.setText("Instructed by :");

        jLabel72.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel72.setText("Md Naser");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setText("6th Semester");

        jLabel75.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel75.setText("Shikh Abujar");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setText("Senior Lecturer");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setText("Dept. of CSE, DIU");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setText("Dept. of CSE, DIU");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel78)))
                            .addComponent(jLabel75))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel79))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel76)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel78))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel73)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel79)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel80.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        jLabel80.setText("Mess Manager");

        jLabel83.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel83.setText("Making Your Mess-Life Easier!");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("Version : 1.0");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText("15-12-2019");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel83)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(jLabel87))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addGap(17, 17, 17)
                .addComponent(jLabel83)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel19.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel19.setText("Developer's :");

        jLabel56.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel56.setText("Ahsanul Haque");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setText("3rd Semester");

        jLabel25.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel25.setText("AKM Monirul Haq");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setText("3rd Semester");

        jLabel55.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel55.setText("Amdadul Hasan");

        jLabel23.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel23.setText("Rashed Ahmed");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("3rd Semester");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setText("3rd Semester");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setText("Dept. of CSE, DIU");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setText("Dept. of CSE, DIU");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setText("Dept. of CSE, DIU");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setText("Dept. of CSE, DIU");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel70))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66)
                                    .addComponent(jLabel71))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel67)
                                    .addComponent(jLabel74))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addComponent(jLabel77))))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel57)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel70))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel66)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel71))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel67)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel74))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel68)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel77)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(101, 101, 101))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("About", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panel_header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panel_header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panel_sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void dashMessData(){
        try{
            String sql="select * from mess where messName=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, "The Fenian");
            rs=pst.executeQuery();
            if(rs.next()){
                String name = rs.getString("messName");
                label_messName.setText(name);
                String balance = rs.getString("totalBalance");
                label_totalBalance.setText(balance);
                String mcost = rs.getString("mealCost");
                label_mealCost.setText(mcost);
                String meal = rs.getString("totalMeal");
                label_totalMeal.setText(meal);
                String rate = rs.getString("mealRate");
                label_mealRate.setText(rate);
                String ocost = rs.getString("otherCost");
                label_otherCost.setText(ocost);
                rs.close();
                pst.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "08 : "+e);
        }
    }
    
    public void dashUserData(){
        try{
            String sql="select * from users where userName=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, label_loggedUser.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String meal = rs.getString("meal");
                label_myMeal.setText(meal);
                String deposit = rs.getString("deposit");
                label_myDeposit.setText(deposit);
                String cost = rs.getString("cost");
                label_myCost.setText(cost);
                String balance = rs.getString("balance");
                label_myBalance.setText(balance);
                String other = rs.getString("other");
                label_myOther.setText(other);
                rs.close();
                pst.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "09 : "+e);
        }
    } 
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);               
        calcMessData();
        calcUserData();
        dashMessData();
        dashUserData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        panel_newmember.setVisible(true);
        panel_extmember.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        panel_extmember.setVisible(true);
        panel_newmember.setVisible(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        String sql="select userName,deposit from users where userName=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField6.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("userName");
                jTextField7.setText(add1);
                String add2 = rs.getString("deposit");
                jTextField8.setText(add2);
                rs.close();
                pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "User doesn't exist!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "10 : "+e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        try{
            String add1 = jTextField7.getText();
            String add2 = jTextField10.getText();
            String add3 = jTextField9.getText();
            String sql="update users set deposit='"+add2+"' where userName='"+add1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deposit Successfull \n"+add1+", deposited amount of "+add3+"tk.");
            pst.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "11 : "+e);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        try{
            String a1= jTextField8.getText();
            String a2= jTextField9.getText();
            int sum=Integer.parseInt(a1)+Integer.parseInt(a2);
            String sum1=String.valueOf(sum);
            jTextField10.setText(sum1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "12 : "+e);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        String sql="select userName,email from users where userName=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField3.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("userName");
                jTextField4.setText(add1);
                String add2 = rs.getString("email");
                jTextField5.setText(add2);
                rs.close();
                pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "User doesn't exist!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "13 : "+e);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        try{
            String add = "The Fenian";
            String add1 = jTextField4.getText();
            String add2 = jTextField10.getText();
            String add3 = (String) jComboBox1.getSelectedItem();
            String sql="update users set mess='"+add+"',type='"+add3+"' where userName='"+add1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Member "+add1+", added \n He Can Now Login to Mess");
            pst.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "14 : "+e);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        String sql="insert into users (userName,password,email,type,mess) values (?,?,?,?,?)";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField1.getText());
            pst.setString(2,"1234");
            pst.setString(3,jTextField2.getText());
            pst.setString(4, (String) jComboBox2.getSelectedItem());
            pst.setString(5,"The Fenian");
            pst.execute();
            JOptionPane.showMessageDialog(null,"Congratulation! \n New member has been added.");
            pst.close();
            jTextField1.setText("");
            jTextField2.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "15 : "+e);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6); 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        try{
            String a= (String) comboUsers.getSelectedItem();
            String b= jTextField11.getText();
            String c= txt_date1.getText();
            String sql="update meal set "+a+"="+b+" where date='"+c+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            fetchMeal();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "16 : "+e);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        String s = jTextField11.getText();
        int sum=Integer.parseInt(s)+1;
        String sum1=String.valueOf(sum);
        jTextField11.setText(sum1);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        String s = jTextField11.getText();
        int sum=Integer.parseInt(s)-1;
        String sum1=String.valueOf(sum);
        jTextField11.setText(sum1);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        txt_date1.setEditable(true);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        fetchMeal();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        try{
            String a1= jTextField14.getText();
            String a2= jTextField15.getText();
            int sum=Integer.parseInt(a1)+Integer.parseInt(a2);
            String sum1=String.valueOf(sum);
            jTextField16.setText(sum1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "12 : "+e);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        try{
            String add1 = jTextField13.getText();
            String add2 = jTextField16.getText();
            String add3 = jTextField15.getText();
            String sql="update users set deposit='"+add2+"' where userName='"+add1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Bazar Cost Added \n"+add1+",  Amount of "+add3+"tk.");
            pst.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "11 : "+e);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        String sql="select userName,cost from users where userName=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField12.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("userName");
                jTextField13.setText(add1);
                String add2 = rs.getString("cost");
                jTextField14.setText(add2);
                rs.close();
                pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "User doesn't exist!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "10 : "+e);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        String sql="select userName,other from users where userName=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField18.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("userName");
                jTextField19.setText(add1);
                String add2 = rs.getString("other");
                jTextField20.setText(add2);
                rs.close();
                pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "User doesn't exist!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "10 : "+e);
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        try{
            String add1 = jTextField19.getText();
            String add2 = jTextField22.getText();
            String add3 = jTextField21.getText();
            String sql="update users set deposit='"+add2+"' where userName='"+add1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Cost Successfully Added \n"+add1+", added amount of "+add3+"tk.");
            pst.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "11 : "+e);
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        try{
            String a1= jTextField20.getText();
            String a2= jTextField21.getText();
            int sum=Integer.parseInt(a1)+Integer.parseInt(a2);
            String sum1=String.valueOf(sum);
            jTextField22.setText(sum1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "12 : "+e);
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(11);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(8);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(9);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int ip = JOptionPane.showConfirmDialog(null, "Delete Complete Mess Data?");
        if(ip==0){ JOptionPane.showMessageDialog(null, "Mess Deleted"); }
        else{ JOptionPane.showMessageDialog(null, "Mess Was Not Deleted"); }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        String sql="select userName from users where userName=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField17.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("userName");
                jTextField23.setText(add1);
                rs.close();
                pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "User doesn't exist!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "10 : "+e);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        try{
            String add1 = jTextField23.getText();
            String add2 = jTextField24.getText();
            String sql="update users set mess='0' where userName='"+add1+"'";
            String sql2="select * from users where type='Manager'";            
            pst=conn.prepareStatement(sql2);
            rs=pst.executeQuery();
            if(rs.next()){
                String pass = rs.getString("password");
                if(add2==pass){
                    pst=conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Member Removed");
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Manager Password");
                }
            }else{
                    JOptionPane.showMessageDialog(null, "Member Not Removed");
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "22 : "+e);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        try{
            String add1 = jTextField26.getText();
            String add2 = jTextField27.getText();            
            String sql2="select * from users where type='Manager'";             
            pst=conn.prepareStatement(sql2);
            rs=pst.executeQuery();
            if(rs.next()){
                String user = rs.getString("userName");
                String pass = rs.getString("password");
                if(add2==pass){
                    String sql="update users set type='Manager' where userName='"+add1+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute();
                    String sql3="update users set type='Member' where userName='"+user+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute();
                    pst.close();
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Manager Password");
                }
            }else{
                    JOptionPane.showMessageDialog(null, "Manager Not Changed");
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "22 : "+e);
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        String sql="select userName from users where userName=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField25.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("userName");
                jTextField26.setText(add1);
                rs.close();
                pst.close();
            }else{
                JOptionPane.showMessageDialog(null, "User doesn't exist!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "10 : "+e);
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(10);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        jTextField30.setEditable(true);
        jTextField32.setEditable(true);
        jTextField33.setEditable(true);
        jTextField31.setEditable(true);
        jTextField34.setEditable(true);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
        try{
            String add1 = jTextField30.getText();
            String add2 = jTextField32.getText();
            String add3 = jTextField33.getText();
            String add4 = jTextField31.getText();
            String add5 = jTextField34.getText();
            String add6 = label_loggedUser.getText();
            String sql="update users set userName='"+add1+"', type='"+add2+"', email='"+add3+"', phone='"+add4+"', address='"+add5+"' where userName='"+add6+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Updated!");
            jTextField30.setEditable(false);
            jTextField32.setEditable(false);
            jTextField33.setEditable(false);
            jTextField31.setEditable(false);
            jTextField34.setEditable(false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "17 : "+e);
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        LoginPage oLoginPage = new LoginPage();
        oLoginPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        loadProfile();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        try{
            String pass1=String.valueOf(jPasswordField1.getPassword());
            String pass2=String.valueOf(jPasswordField2.getPassword());
            String pass3=String.valueOf(jPasswordField3.getPassword());
            String add4 = label_loggedUser.getText();
            String pass =LoginPage.txt_password.getText();
            if(pass2.equals(pass3)){
                if(pass1.equals(pass)){
                    String sql="update users set password='"+pass3+"' where userName='"+add4+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute();
                    pst.close();
                    JOptionPane.showMessageDialog(null, "Password Changed");
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Old Password");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Password Doesn't Match");
            }         
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "18 : "+e);
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton41ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboUsers;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel label_bazarDate;
    private javax.swing.JLabel label_loggedType;
    private javax.swing.JLabel label_loggedUser;
    private javax.swing.JLabel label_mealCost;
    private javax.swing.JLabel label_mealRate;
    private javax.swing.JLabel label_messName;
    private javax.swing.JLabel label_myBalance;
    private javax.swing.JLabel label_myCost;
    private javax.swing.JLabel label_myDeposit;
    private javax.swing.JLabel label_myMeal;
    private javax.swing.JLabel label_myOther;
    private javax.swing.JLabel label_otherCost;
    private javax.swing.JLabel label_totalBalance;
    private javax.swing.JLabel label_totalMeal;
    private javax.swing.JPanel panel_addcost;
    private javax.swing.JPanel panel_addmeal;
    private javax.swing.JPanel panel_addmember;
    private javax.swing.JPanel panel_addmoney;
    private javax.swing.JPanel panel_allmembers;
    private javax.swing.JPanel panel_extmember;
    private javax.swing.JPanel panel_header;
    private javax.swing.JPanel panel_home;
    private javax.swing.JPanel panel_newmember;
    private javax.swing.JPanel panel_othcost;
    private javax.swing.JPanel panel_sidebar;
    private javax.swing.JLabel txt_date;
    private javax.swing.JTextField txt_date1;
    private javax.swing.JTextField txt_depDate;
    private javax.swing.JTextField txt_depDate1;
    private javax.swing.JTextField txt_depDate2;
    // End of variables declaration//GEN-END:variables
}
