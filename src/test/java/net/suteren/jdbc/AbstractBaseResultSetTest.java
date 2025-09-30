package net.suteren.jdbc;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AbstractBaseResultSetTest {

	@Test
	void getString_callsFindColumnAndGetStringWithIndex() throws SQLException {
		AbstractBaseResultSet resultSet = Mockito.mock(AbstractBaseResultSet.class, Mockito.CALLS_REAL_METHODS);
		when(resultSet.findColumn("columnName")).thenReturn(1);
		when(resultSet.getString(1)).thenReturn("testString");

		String result = resultSet.getString("columnName");

		assertEquals("testString", result);
		verify(resultSet).findColumn("columnName");
		verify(resultSet).getString(1);
	}

	@Test
	void getString_throwsSQLExceptionFromFindColumn() throws SQLException {
		AbstractBaseResultSet resultSet = Mockito.mock(AbstractBaseResultSet.class, Mockito.CALLS_REAL_METHODS);
		when(resultSet.findColumn("columnName")).thenThrow(new SQLException("Column not found"));

		assertThrows(SQLException.class, () -> resultSet.getString("columnName"));
	}

	@Test
	void quoteName_escapesQuotes() {
		assertEquals("table\\\"Name", AbstractBaseResultSet.quoteName("table\"Name"));
	}

	@Test
	void getWithClause_returnsEmptyStringForBlankTableName() {
		assertEquals("", AbstractBaseResultSet.getWithClause(null));
		assertEquals("", AbstractBaseResultSet.getWithClause(""));
		assertEquals("", AbstractBaseResultSet.getWithClause("   "));
	}

	@Test
	void getWithClause_returnsWithClauseWithEscapedTableName() {
		assertEquals(" FROM \"table\\\"Name\"", AbstractBaseResultSet.getWithClause("table\"Name"));
	}
}
