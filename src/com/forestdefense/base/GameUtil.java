package com.forestdefense.base;

import javafx.geometry.Point2D;
import java.util.Random;


// * 游戏通用工具类
// * 提供坐标转换、随机生成、边界判断等静态方法

public final class GameUtil {
    
    private static final Random RANDOM = new Random();
    
    private GameUtil() {} // 防止实例化
    
    // ========== 坐标 ↔ 网格转换 ==========
    
//  屏幕坐标 → 网格列索引
    
    public static int screenXToCol(double screenX) {
        int col = (int) ((screenX - GameConstant.GRID_OFFSET_X) / GameConstant.CELL_SIZE);
        return col;
    }
    

//     屏幕坐标 → 网格行索引

    public static int screenYToRow(double screenY) {
        int row = (int) ((screenY - GameConstant.GRID_OFFSET_Y) / GameConstant.CELL_SIZE);
        return row;
    }
    

//     * 网格列索引 → 屏幕X坐标（格子左上角）

    public static double colToScreenX(int col) {
        return GameConstant.GRID_OFFSET_X + col * GameConstant.CELL_SIZE;
    }
    
    
//     * 网格行索引 → 屏幕Y坐标（格子左上角）
   
    public static double rowToScreenY(int row) {
        return GameConstant.GRID_OFFSET_Y + row * GameConstant.CELL_SIZE;
    }
    
//    获取格子中心点坐标
    
    public static Point2D getCellCenter(int row, int col) {
        double x = colToScreenX(col) + GameConstant.CELL_SIZE / 2.0;
        double y = rowToScreenY(row) + GameConstant.CELL_SIZE / 2.0;
        return new Point2D(x, y);
    }
    
// * 判断坐标是否在有效网格范围内
    
    public static boolean isValidGridPosition(int row, int col) {
        return row >= 0 && row < GameConstant.GRID_ROWS &&
               col >= 0 && col < GameConstant.GRID_COLS;
    }
    
//  * 判断屏幕坐标是否在网格区域内 
    
    public static boolean isInGridArea(double screenX, double screenY) {
        int col = screenXToCol(screenX);
        int row = screenYToRow(screenY);
        return isValidGridPosition(row, col);
    }
    
    // ========== 随机生成 ==========
    
//  * 生成 [最小, 最大] 范围内的随机整数
    public static int randomInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
    
// * 生成 [最小, 最大) 范围内的随机双精度数

    public static double randomDouble(double min, double max) {
        return RANDOM.nextDouble() * (max - min) + min;
    }
    
//  * 随机生成猛兽出现的行（0 ~ GRID_ROWS-1）
    
    public static int randomRow() {
        return RANDOM.nextInt(GameConstant.GRID_ROWS);
    }
    
//  * 随机生成果子掉落位置（在网格区域内）
    
    public static Point2D randomFruitPosition() {
        int row = randomRow();
        int col = randomInt(3, GameConstant.GRID_COLS - 3); // 偏右侧掉落
        double x = colToScreenX(col) + randomDouble(10, GameConstant.CELL_SIZE - 10);
        double y = rowToScreenY(row) + randomDouble(10, GameConstant.CELL_SIZE - 10);
        return new Point2D(x, y);
    }
    
// * 随机选择猛兽类型
    
    public static EnemyType randomEnemyType() {
        EnemyType[] types = EnemyType.values();
        return types[RANDOM.nextInt(types.length)];
    }
    
    // ========== 边界判断 ==========
    
// * 判断物体是否超出左侧边界（到达鸟巢=游戏结束）
    
    public static boolean isOutOfLeftBoundary(double x) {
        return x < GameConstant.GRID_OFFSET_X - 20;
    }
    
// * 判断物体是否超出右侧边界（猛兽出生）
    
    public static boolean isOutOfRightBoundary(double x) {
        return x > GameConstant.GRID_OFFSET_X + GameConstant.GRID_COLS * GameConstant.CELL_SIZE;
    }
    
// * 判断是否在水平范围内
    
    public static boolean isInHorizontalRange(double x1, double x2, double range) {
        return Math.abs(x1 - x2) <= range;
    }
    
    // ========== 数值计算 ==========
    
//   * 限制数值在 [最小, 最大] 范围内
    
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
    
//  * 计算两点间距离
    
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}