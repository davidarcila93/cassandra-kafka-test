package com.david.phantomtest.infrastructure.database

import com.david.phantomtest.user.dao.UserDao
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._

object Defaults {
  val connector = ContactPoint.local.keySpace("my_keyspace")
}

class CassandraDatabase(override val connector: CassandraConnection) extends Database[CassandraDatabase](connector) {
  object users extends UserDao with Connector
}

object CassandraDatabase extends CassandraDatabase(Defaults.connector)