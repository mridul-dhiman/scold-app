package com.scold.app.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

data class Post(
    val id: String,
    val userId: String,
    val username: String,
    val content: String,
    val imageUrl: String? = null,
    val createdAt: Long,
    val guiltScore: Int,
    val isAcknowledged: Boolean = false,
)

interface PostRepository {
    fun getFeedPosts(): Flow<List<Post>>
    suspend fun createPost(content: String, imageUrl: String? = null): Boolean
    suspend fun acknowledgePost(postId: String)
    suspend fun getRemainingQuota(): Int
}

@Singleton
class PostRepositoryImpl @Inject constructor() : PostRepository {

    private val samplePosts = listOf(
        Post(
            id = "1",
            userId = "user1",
            username = "marcus_w",
            content = "Ceased dopamine loop after 42 seconds of mindless shelf-refreshing. Real work resumes now. Your glance here is logged under peer ledger.",
            imageUrl = null,
            createdAt = System.currentTimeMillis() - (14 * 60 * 1000),
            guiltScore = 14,
            isAcknowledged = false,
        ),
        Post(
            id = "2",
            userId = "user2",
            username = "j_nakamura",
            content = "Day 12 of using this terminal. Haven't opened algorithmic feeds in 9 days. The shame typing bypass is genuinely too annoying to bother with.",
            imageUrl = null,
            createdAt = System.currentTimeMillis() - (60 * 60 * 1000),
            guiltScore = 8,
            isAcknowledged = false,
        ),
        Post(
            id = "3",
            userId = "user3",
            username = "clara_dev",
            content = "Shipped a critical production fix instead of scrolling. 47 minutes reclaimed. This hostile UX actually works on me.",
            imageUrl = null,
            createdAt = System.currentTimeMillis() - (120 * 60 * 1000),
            guiltScore = 5,
            isAcknowledged = false,
        )
    )

    private val _posts = MutableStateFlow(samplePosts)
    private var quotaRemaining = 1

    override fun getFeedPosts(): Flow<List<Post>> = _posts.asStateFlow()

    override suspend fun createPost(content: String, imageUrl: String?): Boolean {
        if (quotaRemaining <= 0) return false
        val newPost = Post(
            id = System.currentTimeMillis().toString(),
            userId = "me",
            username = "operator_01",
            content = content,
            imageUrl = imageUrl,
            createdAt = System.currentTimeMillis(),
            guiltScore = (1..20).random(),
            isAcknowledged = false,
        )
        _posts.value = listOf(newPost) + _posts.value
        quotaRemaining -= 1
        return true
    }

    override suspend fun acknowledgePost(postId: String) {
        _posts.value = _posts.value.map {
            if (it.id == postId) it.copy(isAcknowledged = true) else it
        }
    }

    override suspend fun getRemainingQuota(): Int = quotaRemaining
}
