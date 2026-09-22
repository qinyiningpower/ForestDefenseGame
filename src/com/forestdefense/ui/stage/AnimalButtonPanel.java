package com.forestdefense.ui.stage;

import com.forestdefense.base.AnimalType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * 动物选择按钮面板
 * 管理所有可选动物的按钮：松鼠、刺猬、啄木鸟、狐狸、乌龟
 * 
 * @author 成员2 负责实现
 */
public class AnimalButtonPanel {
    
    private HBox panel;
    private ToggleGroup toggleGroup;
    private ToggleButton squirrelBtn;
    private ToggleButton hedgehogBtn;
    private ToggleButton woodpeckerBtn;
    private ToggleButton foxBtn;
    private ToggleButton turtleBtn;
    
    private AnimalType selectedType;
    
    public AnimalButtonPanel() {
        // TODO: 由成员2实现
        // - 创建ToggleGroup（实现互斥选择）
        // - 创建所有ToggleButton
        // - 设置按钮文字：显示名称+价格（如 "🐿 松鼠 50🍎"）
        // - 绑定选择事件
        // - 布局排版
    }
    
    /**
     * 获取面板对象
     */
    public HBox getPanel() {
        // TODO: 由成员2实现
        return panel;
    }
    
    /**
     * 获取当前选中的动物类型
     */
    public AnimalType getSelectedType() {
        // TODO: 由成员2实现
        return selectedType;
    }
    
    /**
     * 清除所有选中状态
     */
    public void clearSelection() {
        // TODO: 由成员2实现
        // - toggleGroup.selectToggle(null)
        // - selectedType = null
    }
    
    /**
     * 根据果子数量更新按钮可用状态
     * @param fruits 当前果子数量
     */
    public void updateButtonsAvailability(int fruits) {
        // TODO: 由成员2实现
        // - 果子 < 动物价格 → 按钮变灰禁用
        // - 果子 >= 动物价格 → 按钮启用
    }
}