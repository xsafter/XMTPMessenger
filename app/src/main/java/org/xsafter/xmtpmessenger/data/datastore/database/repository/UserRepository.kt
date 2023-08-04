package org.xsafter.xmtpmessenger.data.datastore.database.repository

import androidx.paging.PagingSource
import org.xmtp.android.library.Client
import org.xsafter.xmtpmessenger.data.datastore.database.dao.UserDao
import org.xsafter.xmtpmessenger.data.model.User
import org.xsafter.xmtpmessenger.ui.components.createFromObject
import java.util.Date

class UserRepository(private val userDao: UserDao, private val client: Client) {
    // Local query to get users
    fun getLocalUsers(): PagingSource<Int, User> = userDao.getAllUsers()

    // Remote query to get users
    suspend fun getAndSaveRemoteUsers() {
        // fetch data from remote
        val remoteUsers = client.conversations.list().map {
            User(
                it.conversationId!!,
                it.conversationId!!,
                createFromObject(it.conversationId!!),
                it.messages(limit = 1).firstOrNull()?.body?:"",
                it.messages(limit = 1).firstOrNull()?.senderAddress?:"",
                it.messages(limit = 1).firstOrNull()?.sent?: Date()
            )
        }

        userDao.insertUsers(remoteUsers)
    }

    // Use this function to refresh all the data on a pull to refresh
    suspend fun refreshUsers() {
        userDao.clearUsers()
        getAndSaveRemoteUsers()
    }
}
