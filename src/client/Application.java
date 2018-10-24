package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ProcCallException;

public class Application {

	static Client client;
	static HashMap<Integer, Integer> instanceId_PartitionKey_Map = new HashMap<>();
	
	public static void main(String[] args) throws UnknownHostException, IOException, ProcCallException {
		
		client = ClientFactory.createClient();
		client.createConnection("localhost");
		
		VoltTable partKeyIdMapping = client.callProcedure("@GetPartitionKeys", "integer").getResults()[0];
		int instanceCount = partKeyIdMapping.getRowCount();
		int instanceId = 0;
		while(partKeyIdMapping.advanceRow()) {
			int partKey = (int) partKeyIdMapping.getLong(1);
			instanceId_PartitionKey_Map.put(instanceId++, partKey);
		}
		generateLoad(instanceCount);
	}
	
	private static void generateLoad(int instanceCount) {
		for(int instanceId=0; instanceId<instanceCount; instanceId++) {
			generatePartLoad(instanceId_PartitionKey_Map.get(instanceId));
		}
	}
	
	private static void generatePartLoad(int partKey) {
		for(int i=0; i<1000; i++) {
			try {
				client.callProcedure("InsertProcedure", partKey, i, "abcd");
			} catch (IOException | ProcCallException e) {
				e.printStackTrace();
			}
		}
	}
}
