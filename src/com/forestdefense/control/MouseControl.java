package com.forestdefense.control;

import com.forestdefense.base.AnimalType;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 * 鼠标控制类
 * 处理：点击动物按钮 → 选中 → 点击网格放置 → 点击果子拾取
 * 
 * @author 成员5 负责实现
 */
public class MouseControl {
    
    private GameManager gameManager;
    private Canvas canvas;
    private AnimalType selectedAnimal;
    private int hoverRow;
    private int hoverCol;
    private boolean isEnabled;
    
    public MouseControl(GameManager gameManager, Canvas canvas) {
        this.gameManager = gameManager;
        this.canvas = canvas;
        // TODO: 由成员5实现 - 调用initMouseListeners()
    }
    
    /**
     * 初始化鼠标监听器
     */
    private void initMouseListeners() {
        // TODO: 由成员5实现
        // 1. canvas.setOnMouseClicked(this::handleMouseClick)
        // 2. canvas.setOnMouseMoved(this::handleMouseMove)
        // 3. canvas.setOnMouseExited(this::handleMouseExit)
    }
    
    /**
     * 处理鼠标点击事件
     */
    private void handleMouseClick(MouseEvent event) {
        // TODO: 由成员5实现
        // 逻辑1：点击网格 → 放置选中的动物
        //   - 检查果子是否足够
        //   - 检查格子是否被占用
        //   - 消耗果子，创建动物
        // 逻辑2：点击果子 → 拾取
        //   - 检测点击位置是否有果子
        //   - 增加果子数量
        // 逻辑3：点击地面 → 取消选中
    }
    
    /**
     * 处理鼠标移动事件（高亮显示）
     */
    private void handleMouseMove(MouseEvent event) {
        // TODO: 由成员5实现
        // - 计算当前悬停的格子行列
        // - 更新hoverRow/hoverCol
        // - 触发画布重绘（显示高亮框）
    }
    
    /**
     * 处理鼠标离开画布
     */
    private void handleMouseExit(MouseEvent event) {
        // TODO: 由成员5实现
        // - 清除高亮
        // - 重置hoverRow/hoverCol为-1
    }
    
    /**
     * 设置选中的动物类型
     * @param type 动物类型，null表示取消选中
     */
    public void setSelectedAnimal(AnimalType type) {
        // TODO: 由成员5实现
        // - 更新selectedAnimal
    }
    
    /**
     * 获取当前选中的动物
     */
    public AnimalType getSelectedAnimal() {
        // TODO: 由成员5实现
        return selectedAnimal;
    }
    
    /**
     * 启用/禁用鼠标交互
     */
    public void setEnabled(boolean enabled) {
        // TODO: 由成员5实现
        // - 更新isEnabled
        // - 设置canvas的mouseTransparent
    }
}