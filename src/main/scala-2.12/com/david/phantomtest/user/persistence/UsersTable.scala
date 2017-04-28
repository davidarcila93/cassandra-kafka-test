package com.david.phantomtest.user.persistence

import com.david.phantomtest.user.User
import com.outworkers.phantom.CassandraTable
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

class UsersModel extends CassandraTable[UsersModel, User] {
  override def tableName = "users_by_id"

  object id extends UUIDColumn(this) with PartitionKey
  object name extends StringColumn(this)
}

abstract class ConcreteUsersModel extends UsersModel with RootConnector {
  def store(user: User): Future[ResultSet] = {
    insert
      .value(_.id, user.id)
      .value(_.name, user.name)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
  }

  def getById(id: UUID): Future[Option[User]] = {
    select.where(_.id eqs id).one()
  }

  def getAll: Future[List[User]] = {
    select.all().fetch()
  }

}