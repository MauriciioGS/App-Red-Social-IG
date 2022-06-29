package com.unam.appredsocialig.network

import com.google.firebase.firestore.FirebaseFirestore
import com.unam.appredsocialig.data.Post
import com.unam.appredsocialig.data.User

const val POST_COLLECTION_NAME = "posts"
const val USERS_COLLECTION_NAME = "users"

class FirestoreService(private val firebaseFirestore: FirebaseFirestore) {

    fun setDocument(data: Any, collectionName: String, id: String, callback: Callback<Void>) {
        firebaseFirestore.collection(collectionName).document(id).set(data)
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun updateUser(user: User, callback: Callback<User>?) {
        firebaseFirestore.collection(USERS_COLLECTION_NAME).document(user.username)
            .update("cryptosList", user.postsList)
            .addOnSuccessListener { result ->
                callback?.onSuccess(user)
            }
            .addOnFailureListener { exception -> callback!!.onFailed(exception) }
    }

    /* Fun de like, coment, etc
    fun updatePost(post: Post) {
        firebaseFirestore.collection(POST_COLLECTION_NAME).document(post.getDocumentId())
            .update("available", post.available)
    }*/

    fun getPosts(callback: Callback<List<Post>>?) {
        firebaseFirestore.collection(POST_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val postsList = result.toObjects(Post::class.java)
                    callback!!.onSuccess(postsList)
                    break
                }
            }
            .addOnFailureListener { exception -> callback!!.onFailed(exception) }
    }

    fun findUserById(id: String, callback: Callback<User>) {
        firebaseFirestore.collection(USERS_COLLECTION_NAME).document(id)
            .get()
            .addOnSuccessListener { result ->
                if (result.data != null) {
                    callback.onSuccess(result.toObject(User::class.java))
                } else {
                    callback.onSuccess(null)
                }
            }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

}