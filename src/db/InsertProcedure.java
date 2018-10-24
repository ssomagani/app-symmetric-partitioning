/**
 * 
 */
package db;

import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

/**
 * @author seetasomagani
 *
 */
public class InsertProcedure extends VoltProcedure {

	private final static SQLStmt INSERT = new SQLStmt("insert into test values (?, ?, ?)");
	
	public VoltTable[] run(int partitionId, int key, String value) {
		voltQueueSQL(INSERT, partitionId, key, value);
		return voltExecuteSQL();
	}
}
