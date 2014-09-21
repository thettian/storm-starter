package cn.edu.dhu.thettian;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.dweb.DWEBTopologyBuilder;
import backtype.storm.tuple.Fields;
import cn.edu.dhu.thettian.dao.UserDAO;
import cn.edu.dhu.thettian.service.UserLoginService;
import cn.edu.dhu.thettian.web.UserAction;

public class DynamicRESTTopology {
	public static void main(String[] args) throws Exception {
	    Config conf = new Config();
	    if (!(args == null || args.length == 0)){
	    	DWEBTopologyBuilder builder = new DWEBTopologyBuilder(args[0]);
		    builder.addBolt("/user",new UserDAO(), 3).fieldsGrouping(new Fields("id"));
		    builder.addBolt("/user",new UserLoginService(), 3).fieldsGrouping(new Fields("id"));
		    builder.addBolt("/user",new UserAction(), 3).fieldsGrouping(new Fields("id"));
	    	conf.setNumWorkers(3);
	    	StormSubmitter.submitTopology(args[0], conf, builder.createRemoteTopology());
	    }
 }
}
