package cn.edu.dhu.thettian.web;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class UserAction extends BaseBasicBolt{

	public void execute(Tuple tuple, BasicOutputCollector collector) {
		//获取logicresult值
		boolean _result = tuple.getBoolean(2);
		//依据值产生不同的id,result响应结果
		if(_result)
			collector.emit(new Values(tuple.getValue(0),"success"));
		else
			collector.emit(new Values(tuple.getValue(0),"fail"));
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id", "result"));
		
	}

}
