package com.david.phantomtest.user.repository

import com.david.phantomtest.common.Repository
import com.david.phantomtest.infrastructure.database.CassandraDatabase
import com.david.phantomtest.user.User
import com.outworkers.phantom.dsl.UUID

import scala.concurrent.Future

trait UserRepository extends Repository[User, UUID]{

}

class UserRepositoryCassandra extends UserRepository {
  def query(id: UUID): Future[Option[User]] = CassandraDatabase.users.getById(id)

  def store(user: User): Future[User] = CassandraDatabase.users.store(user)
}