package cn.edu.dhu.thettian.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.json.simple.JSONValue;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class UserDAO extends BaseBasicBolt{

	public void execute(Tuple tuple, BasicOutputCollector collector) {
		Map _request = (Map) JSONValue.parse(tuple.getString(1));
		String username = (String) _request.get("username");
		
		//获取数据库连接
		Connection conn = null;
		try{
			String url = "jdbc:mysql://218.193.154.47:3306/mybatis";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
		}catch(Exception e){
			//
		}
		//进行数据库查询，获取数据库中密码
		String _password = "";
		try{
			String sql = "select username, password from user where username='"+username+"'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				_password = rs.getString("password");
			}
			conn.close();
		}catch(Exception e){
			//
		}
		//产生id,param,dbresult元组
		collector.emit(new Values(tuple.getValue(0), JSONValue.toJSONString(_request) ,_password));
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id", "param","dbresult"));
		
	}

}
