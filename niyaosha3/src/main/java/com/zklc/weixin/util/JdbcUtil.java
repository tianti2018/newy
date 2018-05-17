package com.zklc.weixin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcUtil
{
  static Connection conn;
  static Statement st;
 

  public static void main(String[] args)
  {
  }


  public static Connection getConnection() {
    Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");

      con = DriverManager.getConnection(
        SystemMessage.getString("DBDRIVER"), SystemMessage.getString("DBLOGINNAME"), SystemMessage.getString("DBPASSWORD"));
    }
    catch (Exception e) {
      System.out.println("数据库连接失败" + e.getMessage());
    }
    return con;
  }
}