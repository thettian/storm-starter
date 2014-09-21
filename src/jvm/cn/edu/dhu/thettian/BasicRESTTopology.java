package cn.edu.dhu.thettian;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.dweb.LinearDWEBTopologyBuilder;
import backtype.storm.dweb.StaticResource;

public class BasicRESTTopology {
	 public static void main(String[] args) throws Exception {
		    Config conf = new Config();
		    if (!(args == null || args.length == 0)){
		    	LinearDWEBTopologyBuilder builder = new LinearDWEBTopologyBuilder(args[0]);
			    builder.addBolt(new StaticResource(), 3);
		    	conf.setNumWorkers(3);
		    	StormSubmitter.submitTopology(args[0], conf, builder.createRemoteTopology());
		    }
	 }
}
