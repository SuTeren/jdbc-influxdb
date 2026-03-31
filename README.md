# InfluxDB JDBC Driver

This project is a continuation of the original [jdbc-influxdb](https://github.com/SuTeren/jdbc-influxdb) driver, created by Petr Vranik, and keeps the same core idea: provide practical JDBC access to InfluxDB for IDEs, BI tools, and Java applications.

The current codebase is still focused on read/query workflows and metadata discovery. The long-term direction is to make this driver work reliably across all InfluxDB generations (1.x, 2.x, 3.x), while preserving a transparent contract about what is and is not supported.

> This driver does **not** provide full JDBC compatibility **yet**.

## Project Status

- Current version in this repository: `0.2.6`.
- Usable for querying and metadata inspection.
- Best fit today: database tooling that needs JDBC metadata plus Influx query execution.
- Compatibility expansion is an active goal; broad version support is a roadmap item, not a completed claim.

## What Works Today

- Basic statement execution through JDBC `Statement` (`executeQuery`, `executeUpdate`, `execute`).
- Influx query execution via driver-native SQL conversion (`nativeSQL`) and Influx query APIs.
- Multiple result set handling for query responses.
- Metadata discovery for:
  - catalogs (Influx databases),
  - tables (measurements),
  - columns (fields and tags representation),
  - indexes (tag/index metadata projection).

## Known Pain Points and Limitations

These are intentionally documented to match real behavior and avoid surprises:

- Query language: this is not a general SQL engine; behavior is aligned with Influx query capabilities.
- JDBC coverage is partial, not complete.
- `PreparedStatement` exists, but parameter binding methods are not implemented (most setter methods throw `UnsupportedOperationException`).
- `CallableStatement`/stored procedure semantics are not supported.
- Batch APIs are not implemented.
- Write-path behavior is limited and not positioned as full JDBC DML support.
- Transactions, generated keys, updatable result sets, and many advanced metadata contracts are not supported.
- Ordering and relational features are constrained by InfluxDB semantics rather than classic RDBMS behavior.

## Roadmap

1. Stabilize and harden current query + metadata behavior for existing users.
2. Improve JDBC feature coverage incrementally (starting with the highest-impact API gaps).
3. Introduce a clear compatibility matrix for different InfluxDB versions and modes.
4. Add targeted adapters/tests so behavior differences between InfluxDB versions are explicit and predictable.

## Why This Exists

The goal is pragmatic interoperability: make InfluxDB easier to inspect and query from JDBC-capable tools, while being explicit about limitations and steadily reducing the biggest integration pain points over time.

## Updates
- 2026-31-03: Original project fork created, dependecies updated and README updated to reflect current status and direction.

## Supporting
If you find this project useful and want to support its development, consider:
- Star the repository to increase visibility.
- Contribute code, tests, or documentation improvements.
- Report issues or feature requests to help prioritize development efforts.
- Share your use cases and feedback to guide future enhancements.

<a href='https://ko-fi.com/V7V51X08KL' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://storage.ko-fi.com/cdn/kofi6.png?v=6' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

We appreciate your support in helping us continue to improve and maintain this project!

#### Notes:
- This README is a living document and will be updated as the project evolves. Please check back for the latest information on features, limitations, and roadmap progress.
- All code will be written by hand and will be tested against real InfluxDB instances to ensure accuracy and reliability. No AI generated code will be used in this project.

