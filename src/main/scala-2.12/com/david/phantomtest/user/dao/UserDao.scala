package com.david.phantomtest.user.dao

import com.david.phantomtest.user.User
import com.outworkers.phantom.CassandraTable
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

class UserModel extends CassandraTable[UserModel, User] {
  override def tableName = "users_by_id"

  object id extends UUIDColumn(this) with PartitionKey
  object name extends StringColumn(this)
}

abstract class UserDao extends UserModel with RootConnector {
  def store(user: User): Future[User] = {
    insert
      .value(_.id, user.id)
      .value(_.name, user.name)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
      .map( _ => user)
  }

  def getById(id: UUID): Future[Option[User]] = {
    select
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .one()
  }

}