package cn.edu.dhu.thettian.service;

import java.util.Map;

import org.json.simple.JSONValue;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class UserLoginService extends BaseBasicBolt{
	
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		//获取请求参数中password值
		Map _request = (Map) JSONValue.parse(tuple.getString(1));
		String password = (String) _request.get("password");
		//获取dbresult的password值
		String _password = tuple.getString(2);
		//业务逻辑
		boolean _result = false;
		if(_password.equals(password)){
			_result = true;
		}
		//产生id,param,logicresult元组
		collector.emit(new Values(tuple.getValue(0), JSONValue.toJSONString(_request) ,_result));
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id", "param" , "logicresult"));
		
	}

}
