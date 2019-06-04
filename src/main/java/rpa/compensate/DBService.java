package rpa.compensate;

/**
 * @author yangsiguo
 * @date 2019/6/1
 * @desc TODO add description in here
 */
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

//http://my.oschina.net/Thinkeryjgfn/blog/177283
//http://www.cnblogs.com/I-will-be-different/p/3925351.html?utm_source=tuicool&utm_medium=referral
//java jdbc使用SSH隧道连接mysql数据库demo
public class DBService {

   /* static String host = "rr-wz94dz2x304y41jn5.mysql.rds.aliyuncs.com";
    static String user = "readonly";
    static String psw = "SrEwLnzTH2g";*/

    static String Whost ;
    static String Wuser ;
    static String Wpsw ;


    static String ssh_host = "112.74.161.196";
    static String ssh_user = "readonly";
    static String ssh_psw = "QL%Zhle^Tou&2019";

    static int lport = 33103;//本地端口

    static String url ;

    static {
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (Main.ROBOT_PROFILES_ENV.getValue().equals(ENV.PRO.getValue())) {
            url = "jdbc:mysql://localhost:" + lport + "/loan";
            Whost = "rm-wz9w1c6iuh9j73983.mysql.rds.aliyuncs.com";
            Wuser = "dev";
            Wpsw = "dev1024!@#";
        } else {
            url = PropertiesUtil.getValueByKey("test-jdbc-url");
            Wuser = PropertiesUtil.getValueByKey("test-jdbc-user");
            Wpsw = PropertiesUtil.getValueByKey("test-jdbc-psw");
        }

    }


    private static void connectSSH(Session sessionSSH){
        try {

            sessionSSH.setPassword(ssh_psw);
            sessionSSH.setConfig("StrictHostKeyChecking", "no");
            sessionSSH.connect();
            System.out.println(sessionSSH.getServerVersion());//这里打印SSH服务器版本信息

            int assinged_port = sessionSSH.setPortForwardingL(lport, Whost, 3306);

            System.out.println("localhost:" + assinged_port);

        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    private static Pair<Connection,Session> establishConnection(){
        //2、创建连接
        Connection conn = null;
        Session sessionSSH = null;
        try{

            System.out.println(url);
            //生产环境才连接SSH
            if (Main.ROBOT_PROFILES_ENV.getValue().equals(ENV.PRO.getValue())){
                System.out.println("connect SSH =============");

                JSch jsch = new JSch();
                sessionSSH = jsch.getSession(ssh_user, ssh_host, 22);
                connectSSH(sessionSSH);
            }

            //ssh -R 192.168.0.102:5555:192.168.0.101:3306 yunshouhu@192.168.0.102
            //session.setPortForwardingR("192.168.0.102",5555, "192.168.0.101", 3306);
            // System.out.println("localhost:  -> ");

            System.out.println("connect DB =============");
            conn = DriverManager.getConnection(url, Wuser, Wpsw);

        } catch (Throwable e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            sessionSSH.disconnect();
        }
        return new ImmutablePair<>(conn,sessionSSH);
    }

    public static void updateStateById(String status,String id){
        Pair<Connection,Session> pair = establishConnection();
        Connection conn = pair.getLeft();
        try{
            String sql = "update t_bid_change_record t set t.status="+status+" where id="+id;
            Statement statement = conn.createStatement();
            int result = statement
                    .executeUpdate(sql);

            System.out.println(result);

        } catch (Throwable t) {
            t.printStackTrace();
            try {
                conn.close();
                pair.getRight().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {
            try {
                conn.close();
                pair.getRight().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static List<Map<String,String>> getCompensateData(String status){
        Pair<Connection,Session> pair = establishConnection();
        Connection conn = pair.getLeft();
        List<Map<String,String>> list = null;
        try{
            list = getData(conn,status);

        } catch (Throwable t) {
            t.printStackTrace();
            try {
                conn.close();
                pair.getRight().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {
            try {
                conn.close();
                pair.getRight().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static int getCountByStatus(String status){
        Pair<Connection,Session> pair = establishConnection();
        Connection conn = pair.getLeft();
        int count = 0;
        try{
            count = getCountByStatus(conn,status);
        } catch (Throwable t) {
            t.printStackTrace();
            try {
                conn.close();
                pair.getRight().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {
            try {
                conn.close();
                pair.getRight().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }


    public static void main(String[] args) {

//        updateStateById(State.FAILED.getValue(),"110");
//        List<Map<String,String>> list = getCompensateData(BidChangeRecordStatus.FAILED.getValue());
        getCountByStatus(BidChangeRecordStatus.DEFAULT.getValue());


    }


    private static List<Map<String,String>> getData(Connection conn,String status) throws SQLException {

        List<Map<String,String>> list = new ArrayList<>();


        // 获取所有表名
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement
                .executeQuery("select * from t_bid_change_record t where t.status="+status);
        // 获取列名
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            // resultSet数据下标从1开始
            String columnName = metaData.getColumnName(i + 1);
            int type = metaData.getColumnType(i + 1);
            if (Types.INTEGER == type) {
                // int
            } else if (Types.VARCHAR == type) {
                // String
            }
            System.out.print(columnName + "\t");
        }
        System.out.println();
        // 获取数据
        while (resultSet.next()) {
            Map map = new HashMap();
            map.put("id",resultSet.getString("id"));
            map.put("product_id",resultSet.getString("product_id"));
            map.put("seq_no",resultSet.getString("seq_no"));
            map.put("sign",resultSet.getString("sign"));
            map.put("tx_time",resultSet.getString("tx_time"));
            map.put("old_bail_account",resultSet.getString("old_bail_account"));
            map.put("new_bail_account",resultSet.getString("new_bail_account"));
            map.put("tx_date",resultSet.getString("tx_date"));
            list.add(map);
        }
        System.out.println(list);
        System.out.println(list.size());

        resultSet.close();
        statement.close();
        conn.close();
        return list;
    }

    private static int getCountByStatus(Connection conn,String status) throws SQLException {

        String sql = null;
        if (null == status){
            sql = "select count(*) from t_bid_change_record t";
        }else {
           sql = "select count(*) from t_bid_change_record t where t.status="+status;
        }
        // 获取所有表名
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement
                .executeQuery(sql);

        int count = 0;
        // 获取数据
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        System.out.println("count:"+count);

        resultSet.close();
        statement.close();
        conn.close();
        return count;
    }


}
