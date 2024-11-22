package com.example.removeProduct.view;

import com.example.addProduct.observe.Subscriber;
import com.example.removeProduct.dto.RemoveProductDTO;
import com.example.ui.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveProductView extends JFrame implements Subscriber {

    private JLabel messageLabel;
    private JButton cancelButton;
    private JButton deleteButton;
    private final MainController controller;
    private RemoveProductViewModel viewModel;

    public RemoveProductView(String productId, MainController controller, RemoveProductViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.subscribe(this);

        setTitle("Xác nhận xóa sản phẩm");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        deleteButton = new JButton("Xóa");
        deleteButton.setVisible(false);
        if(productId == null){
            messageLabel = new JLabel("Vui lòng chọn mã hàng mmuốn xóa!");
        }else{
            messageLabel = new JLabel("Bạn có chắc muốn xóa sản phẩm: " + productId + "?");
            deleteButton.setVisible(true);
        }

        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện logic xóa sản phẩm tại đây
                RemoveProductDTO dto = new RemoveProductDTO(productId);
                controller.executeRemoveProduct(dto);
                dispose();
            }
        });

        add(messageLabel);
        add(cancelButton);
        add(deleteButton);

        // Thiết lập kích thước cho nút
        cancelButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setPreferredSize(new Dimension(100, 30));

        // Căn chỉnh các nút
        cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteButton.setHorizontalAlignment(SwingConstants.CENTER);
        setVisible(false);
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, viewModel.getMessage());
            viewModel.unSubscriber(this);
        });
    }
}
