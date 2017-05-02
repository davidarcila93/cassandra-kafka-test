package com.david.phantomtest.infrastructure.database

import com.david.phantomtest.user.persistence.ConcreteUsersModel
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._

object Defaults {
  val connector = ContactPoint.local.keySpace("my_keyspace")
}

class MyDatabase(override val connector: CassandraConnection) extends Database[MyDatabase](connector) {
  object users extends ConcreteUsersModel with Connector
}

object MyDatabase extends MyDatabase(Defaults.connector)