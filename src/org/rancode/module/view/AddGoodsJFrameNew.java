package org.rancode.module.view;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.rancode.framework.util.Item;
import org.rancode.framework.util.MyFont;
import org.rancode.module.services.Impl.CategoryServiceImpl;
import org.rancode.module.services.Impl.GoodsServiceImpl;
import org.rancode.module.services.Impl.WarehouseServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AddGoodsJFrameNew extends JFrame {

    // 定义全局组件
    JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
    JLabel label_name, label_price, label_startTime, label_endTime, label_stock, label_warehouse, label_category;
    JTextField name, price, startTime, endTime, stock;
    JComboBox warehouse, category;
    JButton button_add;

    // 获得屏幕的大小
    final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    // 父面板对象
    GoodsManagerJPanel parentPanel;
    private JLabel label;

    public AddGoodsJFrameNew(GoodsManagerJPanel parentPanel) {
        this.parentPanel = parentPanel;

        initBackgroundPanel();
        getContentPane().add(backgroundPanel);

        this.setTitle("添加项目");
        this.setSize(540, 360);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    // 初始化背景面板
    public void initBackgroundPanel() {
        backgroundPanel = new JPanel();


//        initLabelPanel();
//
//        backgroundPanel.add(labelPanel);

//        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//
//        label = new JLabel("项目信息");
//        label.setVerticalAlignment(SwingConstants.BOTTOM);
//        label.setHorizontalAlignment(SwingConstants.LEFT);
//        labelPanel.add(label);


        initContentPanel();
        backgroundPanel.setLayout(new BorderLayout(0, 0));
        //         // 加入 middlePanel
        backgroundPanel.add(contentPanel,BorderLayout.CENTER);

        initButtonPanel();
        backgroundPanel.add(buttonPanel,BorderLayout.SOUTH);
    }

    // 初始化label面板
    public void initLabelPanel() {

        labelPanel = new JPanel();
        System.out.println(labelPanel.getHeight());

        JLabel title = new JLabel("项目信息");
        title.setFont(MyFont.Static);

        labelPanel.add(title, JLabel.CENTER);
    }

    // 初始化商品信息面板
    public void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));


        // 开始布局主面板
        Box namePanel = Box.createHorizontalBox();
        namePanel.setPreferredSize(new Dimension(50,15));
//        namePanel.setBorder(BorderFactory.createBevelBorder(1));
        label_name = new JLabel("项目名称", JLabel.RIGHT);
        name = new JTextField("项目名称");

//        namePanel.add(Box.createHorizontalGlue());
        namePanel.add(Box.createHorizontalStrut(30));
        namePanel.add(label_name);
        namePanel.add(Box.createHorizontalStrut(10));
        namePanel.add(name);
        namePanel.add(Box.createHorizontalStrut(20));

        namePanel.setSize(500,2);

//        namePanel.add(Box.createHorizontalStrut(20));


        Box pricePanel = Box.createHorizontalBox();
//        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.X_AXIS));// 水平
//        pricePanel.setBorder(BorderFactory.createBevelBorder(1));
        label_price = new JLabel("项目价格", JLabel.RIGHT);
        price = new JTextField("项目价格", 50);

        pricePanel.add(Box.createHorizontalStrut(30));
        pricePanel.add(label_price);
        pricePanel.add(Box.createHorizontalStrut(10));
        pricePanel.add(price);
        pricePanel.add(Box.createHorizontalStrut(20));

        Box startimePanel = Box.createHorizontalBox();//开始时间
//        startimePanel.setLayout(new BoxLayout(startimePanel, BoxLayout.X_AXIS));
//        startimePanel.setBorder(BorderFactory.createBevelBorder(1));
        label_startTime = new JLabel("开始时间", JLabel.RIGHT);
        startTime = new JTextField("单击选择日期", 50);

        startimePanel.add(Box.createHorizontalStrut(30));
        startimePanel.add(label_startTime);
        startimePanel.add(Box.createHorizontalStrut(10));
        startimePanel.add(startTime);
        startimePanel.add(Box.createHorizontalStrut(20));


        Box endTimePanel = Box.createHorizontalBox();
//        endTimePanel.setLayout(new BoxLayout(endTimePanel, BoxLayout.X_AXIS));// 水平
        endTimePanel.setBorder(BorderFactory.createBevelBorder(1));
        label_endTime = new JLabel("结束时间", JLabel.RIGHT);
        endTime = new JTextField("单击选择日期", 50);

        endTimePanel.add(Box.createHorizontalStrut(30));
        endTimePanel.add(label_endTime);
        endTimePanel.add(Box.createHorizontalStrut(10));
        endTimePanel.add(endTime);
        endTimePanel.add(Box.createHorizontalStrut(20));

        Box principalPanel = Box.createHorizontalBox();
//        endTimePanel.setLayout(new BoxLayout(endTimePanel, BoxLayout.X_AXIS));// 水平
        principalPanel.setBorder(BorderFactory.createBevelBorder(1));
        label_endTime = new JLabel("负责人", JLabel.RIGHT);
        endTime = new JTextField("负责人", 50);

//        endTimePanel.add(Box.createHorizontalStrut(30));
        principalPanel.add(label_endTime);
        principalPanel.add(Box.createHorizontalStrut(10));
        principalPanel.add(endTime);
        principalPanel.add(Box.createHorizontalStrut(20));


        // label_stock = new JLabel("商品库存", JLabel.CENTER);
        // label_warehouse = new JLabel("所属仓库", JLabel.CENTER);
        // label_category = new JLabel("所属分类", JLabel.CENTER);

        // origin = new JTextField("");
        // stock = new JTextField("");

        // 商品种类下拉框
        category = new JComboBox();
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        List<Object[]> list_category = null;
        try {
            list_category = categoryService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list_category != null) {
            int sign = 0;
            for (int i = 0; i < list_category.size(); i++) {
                String id = (String) list_category.get(i)[0];
                String name = (String) list_category.get(i)[1];
                category.addItem(new Item(id, name));
            }
        }

        // 仓库下拉框
        warehouse = new JComboBox();
        WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
        List<Object[]> list_warehouse = null;
        try {
            list_warehouse = warehouseService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list_warehouse != null) {
            int sign = 0;
            for (int i = 0; i < list_warehouse.size(); i++) {
                String id = (String) list_warehouse.get(i)[0];
                String name = (String) list_warehouse.get(i)[1];
                warehouse.addItem(new Item(id, name));
            }
        }


        contentPanel.add(namePanel);
        contentPanel.add(pricePanel);
        contentPanel.add(startimePanel);
        contentPanel.add(endTimePanel);

        // contentPanel.add(label_price);
        // contentPanel.add(price);

        // contentPanel.add(label_origin);
        // contentPanel.add(origin);
        // contentPanel.add(label_stock);
        // contentPanel.add(stock);
        // contentPanel.add(label_category);
        // contentPanel.add(category);
        // contentPanel.add(label_warehouse);
        // contentPanel.add(warehouse);

    }

    // 初始化按钮面板
    public void initButtonPanel() {
        buttonPanel = new JPanel();

        button_add = new JButton("保存");
        button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        button_add.setForeground(Color.white);
        button_add.setFont(MyFont.Static);

        buttonPanel.add(button_add);
    }

}
