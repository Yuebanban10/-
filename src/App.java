import User.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame {
	private CardLayout cardLayout;
	private JPanel cardPanel;
	public App(){
		setTitle("小学数学口算软件");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		// 创建登录页面
		JPanel mainPanel = createLoginPanel();
		cardPanel.add(mainPanel, "login");

		// 创建第二个页面
		JPanel loginPanel = createSecondPanel();
		cardPanel.add(loginPanel, "second");

		cardLayout.show(cardPanel, "login");

		add(cardPanel);
		setVisible(true);
	}

	private JPanel createLoginPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("登录页面");
		panel.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(JLabel.CENTER); // 设置文本居中
		label.setVerticalAlignment(JLabel.CENTER); // 设置垂直居中对齐
		label.setFont(new Font("Dialog",Font.BOLD,16));
		JButton loginButton = new JButton("登录");
		JButton registerButton = new JButton("注册");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		panel.add(buttonPanel, BorderLayout.CENTER);

		// 点击登录按钮时，切换到第二个页面
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "second");
			}
		});

		return panel;
	}

	private JPanel createSecondPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("登录");
		panel.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(JLabel.CENTER); // 设置文本居中
		JPanel inputPanel = new JPanel(new GridLayout(2, 2));

		JLabel usernameLabel = new JLabel("账号:");
		JTextField usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("密码:");
		usernameField.setPreferredSize(new Dimension(100, usernameField.getPreferredSize().height));
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(5,0));

		inputPanel.add(usernameLabel);
		inputPanel.add(usernameField);
		inputPanel.add(passwordLabel);
		inputPanel.add(passwordField);

		panel.add(inputPanel, BorderLayout.CENTER);

		JButton loginButton = new JButton("确定登录");
		panel.add(loginButton, BorderLayout.SOUTH);

		// 在第二个页面点击登录按钮时，打印输入的账号和密码
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				User user = new User();
				user.setUserPassword(password);
				user.setUserID(username);

			}
		});
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new App();
			}
		});
	}

}
