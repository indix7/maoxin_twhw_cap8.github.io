package com.thoughtworks;

import java.util.Scanner;
import java.util.Arrays;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    String[] choiced = selectedItems.split(",");
    String result = "============= 订餐明细 =============\n";
    int originPrices = 0;
    int halfPrices = 0;
    for (String item: choiced){
      String id = item.split(" x ")[0];
      int count = Integer.parseInt(item.split(" x ")[1]);
      int currentPrice = (int)getItemPrices()[getIndexFromItemsIds(id)] * count;
      if (isHalfPrices(id)){
        halfPrices += currentPrice / 2;
      }else{
        halfPrices += currentPrice;
      }
      if (getIndexFromItemsIds(id) < getItemIds().length){
        result += getItemNames()[getIndexFromItemsIds(id)] + " x " + String.valueOf(count) + " = "
                + String.valueOf(currentPrice).split("[.]")[0] + "元\n";
        originPrices += currentPrice;
      }
    }
    int payment = originPrices;
    if (originPrices >= 30 && originPrices-6 <= halfPrices){
      result +=  "-----------------------------------\n" + "使用优惠:\n" + "满30减6元，省6元\n";
      payment -= 6;
    }
    if (originPrices != halfPrices && originPrices-6 > halfPrices){
      result +=  "-----------------------------------\n"
              + "使用优惠:\n" + "指定菜品半价(黄焖鸡，凉皮)，省" + String.valueOf(originPrices-halfPrices) + "元\n";
      payment = halfPrices;
    }
    result += "-----------------------------------\n" +
            "总计："+String.valueOf(payment)+"元\n" +
            "===================================";
    return result;
  }

  /**
   * 获取每个菜品依次的编号
   */
  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }

  /**
   * 获取每个菜品依次的名称
   */
  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  /**
   * 获取每个菜品依次的价格
   */
  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  /**
   * 获取半价菜品的编号
   */
  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }

  /**
   * 接收菜品编号, 返回该菜品是否半价销售
   * @param id 菜品编号
   * @return 该菜品是否半价销售
   */
  public static boolean isHalfPrices(String id){
    boolean isHalfPrice = false;
    for (String halfPriceId: getHalfPriceIds()){
      if (halfPriceId.equals(id)){
        isHalfPrice = true;
        break;
      }
    }
    return isHalfPrice;
  }

  /**
   * 接收菜品编号，返回该编号在getItemsIds中的序号
   * @param id 菜品编号
   * @return 在数组中的序号
   */
  public static int getIndexFromItemsIds(String id){
    for (int index = 0; index < getItemIds().length; index ++){
      if (getItemIds()[index].equals(id)){
        return index;
      }
    }
    return getItemIds().length;
  }
}
