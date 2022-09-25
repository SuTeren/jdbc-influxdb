package net.suteren.jdbc.influxdb.statement;

import java.sql.ResultSet;
import java.sql.SQLWarning;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import net.suteren.jdbc.influxdb.InfluxDbConnection;
import net.suteren.jdbc.influxdb.resultset.InfluxDbResultSet;

public class InfluxDbStatement extends AbstractInfluxDbStatement {

	public InfluxDbStatement(InfluxDbConnection influxDbConnection, InfluxDB client) {
		super(influxDbConnection, client);
	}

	@Override public ResultSet executeQuery(String sql) {
		QueryResult query = client.query(new Query(sql));
		error = new SQLWarning(query.getError());
		resultSet = new InfluxDbResultSet(this, query.getResults());
		return resultSet;
	}

	@Override public int executeUpdate(String sql) {
		return 0;
	}

	@Override public boolean execute(String sql) {
		return false;
	}

	@Override public void addBatch(String sql) {

	}

	@Override public void clearBatch() {

	}

	@Override public int[] executeBatch() {
		return new int[0];
	}

	@Override public int executeUpdate(String sql, int autoGeneratedKeys) {
		return 0;
	}

	@Override public int executeUpdate(String sql, int[] columnIndexes) {
		return 0;
	}

	@Override public int executeUpdate(String sql, String[] columnNames) {
		return 0;
	}

	@Override public boolean execute(String sql, int autoGeneratedKeys) {
		return false;
	}

	@Override public boolean execute(String sql, int[] columnIndexes) {
		return false;
	}

	@Override public boolean execute(String sql, String[] columnNames) {
		return false;
	}

}
